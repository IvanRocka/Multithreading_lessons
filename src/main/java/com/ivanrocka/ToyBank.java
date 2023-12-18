package main.java.com.ivanrocka;

public class ToyBank {


    public static void main(String[] args) {
        BackBankSystem backBankSystem = new BackBankSystem(10000);
        FrontalSystem frontalSystem = new FrontalSystem();

        Thread clientHandler_1 = new Thread(new ClientHandler(frontalSystem,"1",backBankSystem));
        Thread clientHandler_2 = new Thread(new ClientHandler(frontalSystem,"2",backBankSystem));

        Thread client_1 = new Thread(new Client("Клиент №1", OPERATION.CREDIT, frontalSystem));
        Thread client_2 = new Thread(new Client("Клиент №2", OPERATION.PAYMENT, frontalSystem));
        Thread client_3 = new Thread(new Client("Клиент №3", OPERATION.CREDIT, frontalSystem));
        Thread client_4 = new Thread(new Client("Клиент №4", OPERATION.PAYMENT, frontalSystem));
        Thread client_5 = new Thread(new Client("Клиент №5", OPERATION.CREDIT, frontalSystem));


        client_1.start();
        client_2.start();
        client_3.start();
        client_4.start();
        client_5.start();

        clientHandler_1.start();
        clientHandler_2.start();
    }
}
