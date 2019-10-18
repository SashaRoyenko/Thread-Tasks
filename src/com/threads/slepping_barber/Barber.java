package com.threads.slepping_barber;

import java.util.concurrent.Semaphore;

public class Barber extends Thread{
  private Semaphore customers;
  private Semaphore barber;
  private Semaphore accessSeats;
  private int numberOfFreeSeats;

  public Barber(Semaphore customers, Semaphore barber, Semaphore accessSeats,
      int numberOfFreeSeats) {
    this.customers = customers;
    this.barber = barber;
    this.accessSeats = accessSeats;
    this.numberOfFreeSeats = numberOfFreeSeats;
  }

  @Override
  public void run() {
    while (true){
      try {
        customers.acquire();
        accessSeats.release();
        numberOfFreeSeats++;
        barber.release();
        cutHair();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void cutHair(){
    System.out.println("The barber is cutting hair");
    try {
      sleep(5000);
    } catch (InterruptedException ex){ }
  }
}
