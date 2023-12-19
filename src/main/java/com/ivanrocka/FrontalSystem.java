package main.java.com.ivanrocka;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class FrontalSystem {
    private final int REQUEST_QUEUE_SIZE = 2;
    private ArrayBlockingQueue<Request> requestQueue = new ArrayBlockingQueue<>(REQUEST_QUEUE_SIZE);

    public void addRequest(Request request) throws InterruptedException {
            requestQueue.put(request);
            System.out.println(request.toString());
    }

    public  Request getRequest() throws InterruptedException {
            var receivedRequest = requestQueue.take();
            return receivedRequest;
    }
}
