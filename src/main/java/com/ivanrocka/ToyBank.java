package main.java.com.ivanrocka;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ToyBank {


    public static void main(String[] args) {
        BackBankSystem backBankSystem = new BackBankSystem(10000);
        FrontalSystem frontalSystem = new FrontalSystem();

        ExecutorService executorService = Executors.newFixedThreadPool(7);
        ExecutorService executorService_backSystem = Executors.newFixedThreadPool(3);

        List<BackBankSystemHandler> backSystemTimeOuts = new ArrayList<>();
        backSystemTimeOuts.add( new BackBankSystemHandler(5000, backBankSystem));
        backSystemTimeOuts.add( new BackBankSystemHandler(7000, backBankSystem));
        backSystemTimeOuts.add( new BackBankSystemHandler(10000, backBankSystem));

        ClientHandler clientHandler_1 = new ClientHandler(frontalSystem,"1",backBankSystem);
        ClientHandler clientHandler_2 = new ClientHandler(frontalSystem,"2",backBankSystem);

        Client client_1 = new Client("Клиент №1", Operation.CREDIT, frontalSystem);
        Client client_2 = new Client("Клиент №2", Operation.PAYMENT, frontalSystem);
        Client client_3 = new Client("Клиент №3", Operation.CREDIT, frontalSystem);
        Client client_4 = new Client("Клиент №4", Operation.PAYMENT, frontalSystem);
        Client client_5 = new Client("Клиент №5", Operation.CREDIT, frontalSystem);

        executorService_backSystem.submit(new BackBankSystemHandler(7000, backBankSystem));
        executorService_backSystem.submit(new BackBankSystemHandler(5000, backBankSystem));
        executorService_backSystem.submit(new BackBankSystemHandler(9000, backBankSystem));


        executorService_backSystem.shutdown();

        try {
            while(!executorService_backSystem.awaitTermination(10, SECONDS));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        executorService.submit(clientHandler_1);
        executorService.submit(clientHandler_2);
        executorService.submit(client_1);
        executorService.submit(client_2);
        executorService.submit(client_3);
        executorService.submit(client_4);
        executorService.submit(client_5);


    }
}
