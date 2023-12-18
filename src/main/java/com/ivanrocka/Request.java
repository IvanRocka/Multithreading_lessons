package main.java.com.ivanrocka;

public class Request {
    private final String clientName;
    private final double amount;
    private final OPERATION operation;

    public Request(String clientName, double amount, OPERATION operation) {
        this.clientName = clientName;
        this.amount = amount;
        this.operation = operation;
    }

    public String getClientName() {
        return clientName;
    }

    public OPERATION getOperation() {
        return operation;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Заявка{" + "clientThreadName='" + clientName + "', " +
                "amount=" + amount + ", " + "requestType=" + operation + "} отправлена в банк \n";
    }
}
