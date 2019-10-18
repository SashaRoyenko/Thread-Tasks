package com.threads.writer_reader_2;

import java.util.concurrent.Semaphore;

public class Test {
  static int readerCount = 0;
  static Semaphore internalReentrantLock2 = new Semaphore(1);
  static Semaphore internalReentrantLock1 = new Semaphore(1);
  static Semaphore reentrantLock1 = new Semaphore(1);
  static Semaphore reentrantLock2 = new Semaphore(1);

  static class Read implements Runnable {
    @Override
    public void run() {
      try {
        internalReentrantLock1.acquire();
        reentrantLock1.acquire();
        internalReentrantLock2.acquire();
        readerCount++;
        if (readerCount == 1) reentrantLock2.acquire();
        internalReentrantLock2.release();

        System.out.println("Thread "+Thread.currentThread().getName() + " is READING");
        Thread.sleep(1500);
        System.out.println("Thread "+Thread.currentThread().getName() + " has FINISHED READING");

        internalReentrantLock2.acquire();
        readerCount--;
        if (readerCount == 0) reentrantLock2.release();
        internalReentrantLock2.release();
        reentrantLock1.release();
        internalReentrantLock1.release();

      } catch (InterruptedException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  static class Write implements Runnable {
    @Override
    public void run() {
      try {
        reentrantLock1.acquire();
        reentrantLock2.acquire();
        System.out.println("Thread "+Thread.currentThread().getName() + " is WRITING");
        Thread.sleep(2500);
        System.out.println("Thread "+Thread.currentThread().getName() + " has finished WRITING");
        reentrantLock2.release();
        reentrantLock1.release();
      } catch (InterruptedException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public static void main(String[] args) throws Exception {
    Read read = new Read();
    Write write = new Write();
    Thread t1 = new Thread(read);
    t1.setName("thread1");
    Thread t2 = new Thread(read);
    t2.setName("thread2");
    Thread t3 = new Thread(write);
    t3.setName("thread3");
    Thread t4 = new Thread(read);
    t4.setName("thread4");
    t3.start();
    t1.start();
    t2.start();
    t4.start();
  }
}
