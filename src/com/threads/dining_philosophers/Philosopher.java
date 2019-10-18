package com.threads.dining_philosophers;

import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {

  private final Object leftFork;
  private final Object rightFork;
  private Semaphore semaphore1 = new Semaphore(1);
  private Semaphore semaphore2 = new Semaphore(1);

  public Philosopher(Object leftFork, Object rightFork) {
    this.leftFork = leftFork;
    this.rightFork = rightFork;
  }

  private void doAction(String action) throws InterruptedException {
    System.out.println(
        Thread.currentThread().getName() + " " + action);
    Thread.sleep(((int) (Math.random() * 100)));
  }

  @Override
  public void run() {
    try {

      // thinking
      doAction(System.nanoTime() + ": Thinking");
      semaphore1.acquire();
      doAction(
          System.nanoTime()
              + ": Picked up left fork");
      semaphore2.acquire();
      // eating
      doAction(
          System.nanoTime()
              + ": Picked up right fork - eating");

      doAction(
          System.nanoTime()
              + ": Put down right fork");
      semaphore2.release();

      // Back to thinking
      doAction(
          System.nanoTime()
              + ": Put down left fork. Back to thinking");
      semaphore1.release();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
