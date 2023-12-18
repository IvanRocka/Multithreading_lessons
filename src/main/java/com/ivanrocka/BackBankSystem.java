package main.java.com.ivanrocka;

public class BackBankSystem {
    private double balance;

    public BackBankSystem(double balance) {
        this.balance = balance;
    }

    public void processingRequest(Request request, String clientHandlerNumber ) {
        switch (request.getOperation()) {
            case CREDIT -> decrease(request, clientHandlerNumber);
            case PAYMENT -> increase(request,clientHandlerNumber);
        }
    }

    private synchronized void increase(Request request, String clientHandlerNumber) {
        balance += request.getAmount();
        System.out.println("Бэк система: " + request.toString() + "УСПЕШНО ВЫПОЛНЕНА." +
                "Получена от Обработчик заявок №" + clientHandlerNumber +
                "  Баланс банка: " + balance + "\n");
    }

    private synchronized void decrease(Request request, String clientHandlerNumber) {
        if(balance-request.getAmount() > 0) {
            balance -= request.getAmount();
            System.out.println("Бэк система: " + request.toString() + "УСПЕШНО ВЫПОЛНЕНА." +
                    "Получена от Обработчик заявок №" + clientHandlerNumber +
                    " Баланс банка: " + balance);
        }
        else {
            System.out.println("Бэк система: " + request.toString() + "НЕ ВЫПОЛНЕНА." +
                    "Получена от Обработчик заявок №" + clientHandlerNumber +
                    " Сумма больше баланса банка. Баланс банка: " + balance);
        }
    }
}
