package com.threads.producer_consumer;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

  private final BlockingQueue<Integer> list;
  private int capacity;

  public Consumer(BlockingQueue<Integer> list) {
    this.list = list;
  }

  @Override
  public void run() {
    while (true) {
      try {
        System.out.println("Consumer consumed: " + list.take());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
