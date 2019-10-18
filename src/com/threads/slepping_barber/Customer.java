package com.threads.slepping_barber;

import java.util.concurrent.Semaphore;

public class Customer extends Thread {

  private int id;
  private boolean cut = false;

  private Semaphore customers;
  private Semaphore barber;
  private Semaphore accessSeats;
  private int numberOfFreeSeats;

  public Customer(int id, Semaphore customers, Semaphore barber,
      Semaphore accessSeats, int numberOfFreeSeats) {
    this.id = id;
    this.customers = customers;
    this.barber = barber;
    this.accessSeats = accessSeats;
    this.numberOfFreeSeats = numberOfFreeSeats;
  }

  @Override
  public void run() {
    while (!cut) {
      try {
        accessSeats.acquire();
        if (numberOfFreeSeats > 0) {
          System.out.println("Customer " + id + " just sat down.");
          numberOfFreeSeats--;
          customers.release();
          accessSeats.release();

          barber.acquire();
          cut = true;
          get_haircut();
        } else {
          System.out
              .println("There are no free seats. Customer " + id + " has left the barbershop.");
          accessSeats.release();
          cut = false;
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void get_haircut() {
    System.out.println("Customer " + id + " is getting his hair cut");
    try {
      sleep(5050);
    } catch (InterruptedException ex) {
    }
  }
}
