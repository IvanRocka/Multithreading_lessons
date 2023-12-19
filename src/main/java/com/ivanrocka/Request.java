package main.java.com.ivanrocka;

import java.util.concurrent.atomic.AtomicLong;

public class Request {
    private final String clientName;
    private final long amount;
    private final Operation operation;

    public Request(String clientName, long amount, Operation operation) {
        this.clientName = clientName;
        this.amount = amount;
        this.operation = operation;
    }

    public String getClientName() {
        return clientName;
    }

    public Operation getOperation() {
        return operation;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Заявка{" + "clientThreadName='" + clientName + "', " +
                "amount=" + amount + ", " + "requestType=" + operation + "} отправлена в банк \n";
    }
}
