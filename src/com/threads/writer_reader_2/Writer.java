package com.threads.writer_reader_2;

import java.util.concurrent.locks.ReentrantLock;

public class Writer implements Runnable {

  private ReentrantLock reentrantLock2;
  private ReentrantLock reentrantLock1;

  public Writer(ReentrantLock reentrantLock1, ReentrantLock reentrantLock2) {
    this.reentrantLock1 = reentrantLock1;
    this.reentrantLock2 = reentrantLock2;
  }

  @Override
  public void run() {
    reentrantLock1.lock();
    reentrantLock2.lock();
    System.out.println("Thread " + Thread.currentThread().getName() + " is writing");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Thread " + Thread.currentThread().getName() + " has FINISHED WRITING");
    reentrantLock2.unlock();
    reentrantLock1.unlock();
  }
}
