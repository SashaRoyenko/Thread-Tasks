package com.threads.producer_consumer;

import java.awt.*;
import java.util.LinkedList;

public class Consumer implements Runnable {
    private LinkedList<Integer> list;
    private int capacity;

    public Consumer(LinkedList<Integer> list, int capacity) {
        this.list = list;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (this) {
                while (list.size() == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int value = list.removeFirst();
                    System.out.println("Consumer consume: " + value);
                    notify();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
