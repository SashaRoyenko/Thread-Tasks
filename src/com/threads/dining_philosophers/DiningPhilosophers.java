package com.threads.dining_philosophers;

public class DiningPhilosophers {

  public static void main(String[] args) {
    final int PHILOSOPHERS_COUNT = 5;
    Philosopher[] philosophers = new Philosopher[PHILOSOPHERS_COUNT];
    Object[] forks = new Object[PHILOSOPHERS_COUNT];

    for (int i = 0; i < PHILOSOPHERS_COUNT; i++) {
      forks[i] = new Object();
    }

    for (int i = 0; i < PHILOSOPHERS_COUNT; i++) {
      Object leftFork = forks[i];
      Object rightFork = forks[(i + 1) % PHILOSOPHERS_COUNT];

      if (i == philosophers.length - 1) {
        philosophers[i] = new Philosopher(rightFork, leftFork);
      } else {
        philosophers[i] = new Philosopher(leftFork, rightFork);
      }

      Thread t
          = new Thread(philosophers[i], "Philosopher " + (i + 1));
      t.start();
    }
  }
}
