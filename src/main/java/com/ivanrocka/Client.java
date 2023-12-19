package main.java.com.ivanrocka;

import java.util.Random;

public class Client implements Runnable {
    private String clientName;
    private Operation operationType;
    private long cash;
    private FrontalSystem frontalSystem;

    public Client(String name, Operation operationType, FrontalSystem frontalSystem) {
        this.clientName = name;
        this.operationType = operationType;
        this.frontalSystem = frontalSystem;
    }

    @Override
    public void run() {
        this.cash = new Random().nextInt(10) * 990;
        try {
            frontalSystem.addRequest(new Request(clientName,cash,operationType));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
