package com.threads.slepping_barber;

import java.util.concurrent.Semaphore;

public class SleepingBarberProblem {

  public static void main(String[] args) {
    Semaphore customers = new Semaphore(0);
    Semaphore barbers = new Semaphore(0);
    Semaphore accessSeats = new Semaphore(1);
    final int CHAIRS = 5;

    Barber barber = new Barber(customers, barbers, accessSeats, CHAIRS);
    barber.start();

    /* This method will create new customers for a while */

    for (int i = 1; i < 16; i++) {
      Customer customer = new Customer(i, customers, barbers, accessSeats, CHAIRS);
      customer.start();
      try {
        Thread.sleep(2000);
      } catch (InterruptedException ex) {
      }
      ;
    }
  }
}
