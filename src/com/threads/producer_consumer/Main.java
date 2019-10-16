package com.threads.producer_consumer;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LinkedList<Integer> list = new LinkedList<>();
        final int CAPACITY = 2;
        Thread producer = new Thread(new Producer(list, CAPACITY));
        Thread consumer = new Thread(new Consumer(list, CAPACITY));

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
