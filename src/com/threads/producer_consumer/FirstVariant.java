package com.threads.producer_consumer;

import java.util.concurrent.LinkedBlockingDeque;

public class FirstVariant {

  public static void main(String[] args) throws InterruptedException {
    final int CAPACITY = 10;
    LinkedBlockingDeque list = new LinkedBlockingDeque(CAPACITY);
    Thread producer = new Thread(new Producer(list, CAPACITY));
    Thread consumer = new Thread(new Consumer(list));

    producer.start();
    consumer.start();

    producer.join();
    consumer.join();
  }
}
