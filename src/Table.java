package COMP3432.Project;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Table {
    public static Lock[] forks;
    public static Philosopher[] philosophers;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting simulation...");
        philosophers = new Philosopher[5];
        forks = new Lock[5];

        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(i);
            forks[i] = new ReentrantLock();
        }

        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i].start();
        }

        Thread.sleep(200);

        System.out.println("Stopping simulation...");

        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i].stopEat();
        }

        Thread.sleep(100);

        for (int i = 0; i < philosophers.length; i++) {
            System.out.println("Philosopher " + i + " = " + philosophers[i].getEatCount());
        }
    }


    public static boolean takeFork(int fork) {
        return forks[fork].tryLock();
    }

   
    public static void putDownFork(int fork) {
        forks[fork].unlock();
    }
}
