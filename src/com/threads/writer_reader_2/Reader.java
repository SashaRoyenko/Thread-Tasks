package com.threads.writer_reader_2;

import java.util.concurrent.locks.ReentrantLock;

public class Reader implements Runnable {

  private ReentrantLock reentrantLock2;
  private ReentrantLock reentrantLock1;
  private ReentrantLock internalReentrantLock1;
  private ReentrantLock internalReentrantLock2;

  private int readers = 0;

  public Reader(ReentrantLock reentrantLock2, ReentrantLock reentrantLock1) {
    internalReentrantLock1 = new ReentrantLock();
    internalReentrantLock2 = new ReentrantLock();
    this.reentrantLock2 = reentrantLock2;
    this.reentrantLock1 = reentrantLock1;
  }

  @Override
  public void run() {
    internalReentrantLock1.lock();
    reentrantLock1.lock();

    internalReentrantLock2.lock();
    readers++;
    if (readers == 1) {
      reentrantLock2.lock();
    }
    internalReentrantLock2.unlock();

    System.out.println("Thread " + Thread.currentThread().getName() + " is READING");
    try {
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Thread " + Thread.currentThread().getName() + " has FINISHED READING");

    internalReentrantLock2.lock();
    readers--;
    if (readers == 0) {
      while (reentrantLock2.isLocked()) {
        if (reentrantLock2.isHeldByCurrentThread()) {
          reentrantLock2.unlock();
        }
      }
    }
    internalReentrantLock2.unlock();
    reentrantLock1.unlock();
    internalReentrantLock1.unlock();
  }
}
