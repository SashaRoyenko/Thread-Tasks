package com.threads.producer_consumer;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

  private final BlockingQueue<Integer> list;
  private int capacity;

  public Producer(BlockingQueue<Integer> list, int capacity) {
    this.list = list;
    this.capacity = capacity;
  }

  @Override
  public void run() {
    int value = 0;
    while (value < capacity) {
      synchronized (list) {
          try {
              System.out.println("Producer produced: " + value);
              list.put(value++);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
    }
  }

}
