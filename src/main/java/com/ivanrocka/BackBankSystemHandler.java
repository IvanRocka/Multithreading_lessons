package main.java.com.ivanrocka;

import java.util.Random;
import java.util.concurrent.Callable;

public class BackBankSystemHandler implements Callable {

    private final int processingTime;
    private final BackBankSystem backBankSystem;

    public BackBankSystemHandler(int processingTime, BackBankSystem backBankSystem) {
        this.processingTime = processingTime;
        this.backBankSystem = backBankSystem;
    }

    @Override
    public Object call() throws Exception {
        try {
            Thread.sleep(processingTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long old_balance = backBankSystem.getBalance().get();
        backBankSystem.addBalance(new Random().nextInt(10) * 990);
        long new_balance = backBankSystem.getBalance().get();
        System.out.println("Изменение баланса банка. Старый баланс банка: " +  old_balance +
                " Новый баланс банка: " + new_balance);
        return null;
    }
}
