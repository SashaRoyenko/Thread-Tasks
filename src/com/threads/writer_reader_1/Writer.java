package com.threads.writer_reader_1;

import java.util.concurrent.locks.ReentrantLock;

public class Writer implements Runnable {

  private ReentrantLock reentrantLock;

  public Writer() {
//    reentrantLock = new ReentrantLock();
  }

  public Writer(ReentrantLock reentrantLock) {
    this.reentrantLock = reentrantLock;
  }

  @Override
  public void run() {
    reentrantLock.lock();
    System.out.println("Thread " + Thread.currentThread().getName() + " is writing");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Thread " + Thread.currentThread().getName() + " has FINISHED WRITING");

//    if (reentrantLock.isHeldByCurrentThread()) {
      reentrantLock.unlock();
//    }

  }
}
