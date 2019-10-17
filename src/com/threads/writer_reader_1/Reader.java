package com.threads.writer_reader_1;

import java.util.concurrent.locks.ReentrantLock;

public class Reader implements Runnable {

  private int readers = 0;
  private ReentrantLock reentrantLock1;
  private ReentrantLock reentrantLock2;

  public Reader() {
//    reentrantLock1 = new ReentrantLock();
//    reentrantLock2 = new ReentrantLock();
  }

  public Reader(ReentrantLock reentrantLock1, ReentrantLock reentrantLock2) {
    this.reentrantLock1 = reentrantLock1;
    this.reentrantLock2 = reentrantLock2;
  }

  @Override
  public void run() {
    reentrantLock1.lock();
    readers++;
    if (readers == 1) {
      reentrantLock2.lock();
    }
    reentrantLock1.unlock();

    System.out.println("Thread " + Thread.currentThread().getName() + " is READING");
    try {
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Thread " + Thread.currentThread().getName() + " has FINISHED READING");

    reentrantLock1.lock();
    readers--;
    if (readers == 0) {
      if(reentrantLock2.isLocked()){
        if (reentrantLock2.isHeldByCurrentThread()) {
          reentrantLock2.unlock();
        }
      }
    }
    reentrantLock1.unlock();
  }
}
