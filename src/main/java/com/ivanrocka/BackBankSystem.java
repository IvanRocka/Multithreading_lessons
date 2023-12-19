package main.java.com.ivanrocka;

import java.util.concurrent.atomic.AtomicLong;

public class BackBankSystem {
    //private double balance;
    private AtomicLong balance;
    public BackBankSystem(long balance) {
        this.balance = new AtomicLong(balance);
    }

    public AtomicLong getBalance() {
        return balance;
    }

    public void addBalance(long balance_shift) {
        this.balance = new AtomicLong(balance.addAndGet(balance_shift));
    }

    public void processingRequest(Request request, String clientHandlerNumber ) {
        switch (request.getOperation()) {
            case CREDIT -> decrease(request, clientHandlerNumber);
            case PAYMENT -> increase(request,clientHandlerNumber);
        }
    }

    private void increase(Request request, String clientHandlerNumber) {
        boolean success = false;
        while (!success) {
            long expValue = balance.longValue();
            long newValue = expValue + request.getAmount();
            success = balance.compareAndSet(expValue,newValue);
            System.out.println("Бэк система: " + request.toString() + "УСПЕШНО ВЫПОЛНЕНА." +
                    "Получена от Обработчик заявок №" + clientHandlerNumber +
                    "  Баланс банка: " + balance + "\n");
        }
    }

    private void decrease(Request request, String clientHandlerNumber) {
        boolean success = false;
        while (!success) {
            long expValue = balance.longValue();
            if (expValue - request.getAmount() > 0) {
                long newValue = expValue + request.getAmount();
                success = balance.compareAndSet(expValue, newValue);
                System.out.println("Бэк система: " + request.toString() + "УСПЕШНО ВЫПОЛНЕНА." +
                        "Получена от Обработчик заявок №" + clientHandlerNumber +
                        " Баланс банка: " + balance);
            } else {
                System.out.println("Бэк система: " + request.toString() + "НЕ ВЫПОЛНЕНА." +
                        "Получена от Обработчик заявок №" + clientHandlerNumber +
                        " Сумма больше баланса банка. Баланс банка: " + balance);
            }
        }
    }
}
