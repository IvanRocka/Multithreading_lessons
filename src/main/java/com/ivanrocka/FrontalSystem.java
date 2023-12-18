package main.java.com.ivanrocka;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class FrontalSystem {
    private final int REQUEST_QUEUE_SIZE = 2;
    private ArrayDeque<Request> requestQueue = new ArrayDeque<>(REQUEST_QUEUE_SIZE);

    public synchronized void addRequest(Request request) throws InterruptedException {
        while (requestQueue.size() > 2) {
            wait();
        }
        requestQueue.add(request);
        System.out.println(request.toString());
        notifyAll();
    }

    public synchronized Request getRequest() throws InterruptedException {
        while (requestQueue.isEmpty()) {
            wait();
        }
        var receivedRequest = requestQueue.poll();
        notifyAll();
        return receivedRequest;
    }
}
