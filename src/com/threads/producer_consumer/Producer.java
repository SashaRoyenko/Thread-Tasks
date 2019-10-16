package com.threads.producer_consumer;

import java.util.LinkedList;

public class Producer implements Runnable {
    private LinkedList<Integer> list;
    private int capacity;

    public Producer(LinkedList<Integer> list, int capacity) {
        this.list = list;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        int value = 0;
        while (true) {
            synchronized (this) {
                // producer thread waits while list
                // is full
                while (list.size() == capacity) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Producer produced: " + value);

                // to insert the jobs in the list
                list.add(value++);

                // notifies the consumer thread that
                // now it can start consuming
                notify();

                // makes the working of program easier
                // to  understand
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
