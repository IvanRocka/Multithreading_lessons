package main.java.com.ivanrocka;

public class ClientHandler implements Runnable{

    private FrontalSystem frontalSystem;
    private String currentHandlerNumber;
    private BackBankSystem backBankSystem;

    public ClientHandler(FrontalSystem frontalSystem, String currentHandlerNumber, BackBankSystem backBankSystem) {
        this.frontalSystem = frontalSystem;
        this.currentHandlerNumber = currentHandlerNumber;
        this.backBankSystem = backBankSystem;

    }

    public String getCurrentHandlerNumber() {
        return currentHandlerNumber;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request currentRequest = frontalSystem.getRequest();
                System.out.println("Обработчик заявок №" + currentHandlerNumber +
                        ": Получена заявка на обработку по клиенту - " + currentRequest.getClientName() + "\n");
                backBankSystem.processingRequest(currentRequest, currentHandlerNumber);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
