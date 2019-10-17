package com.threads.writer_reader_1;

import java.util.concurrent.locks.ReentrantLock;

public class WritersReadersProblem {

  public static void main(String[] args) {
    Config.writersCount = 1;
    Config.readersCount = 4;

    ReentrantLock reentrantLock1 = new ReentrantLock();
    ReentrantLock reentrantLock2 = new ReentrantLock();

    Writer writer = new Writer(reentrantLock2);
    Reader reader = new Reader(reentrantLock1, reentrantLock2);

    for (int i = 0; i < Config.writersCount; i++) {
      new Thread(writer).start();
    }

    for (int i = 0; i < Config.readersCount; i++) {
      new Thread(reader).start();
    }

  }

}
