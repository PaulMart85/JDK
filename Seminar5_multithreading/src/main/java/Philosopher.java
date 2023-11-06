/* Классическая задача про обедающих философов
Условие:
● Есть пять философов (потоки), которые могут либо
обедать (выполнение кода) либо размышлять
(ожидание).
● Каждый философ должен пообедать три раза. Каждый
прием пищи длиться 500 миллисекунд
● После каждого приема пищи философ должен
размышлять
● Не должно возникнуть общей блокировки
● Философы не должны есть больше одного раза подряд
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable {
    private static Lock[] forks;
    private int id;

    public Philosopher(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            think();
            eat();
        }
    }

    private void think() {
        System.out.println("Philosopher " + (id+1) + " is thinking.");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat() {
        int fork = id;

        Lock forkLock = forks[fork];
        forkLock.lock();

        try {
            System.out.println("Philosopher " + (id+1) + " is eating.");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            forkLock.unlock();
        }
    }

    public static void main(String[] args) {
        int numPhilosophers = 5;
        forks = new Lock[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new ReentrantLock();
        }

        Thread[] philosophers = new Thread[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Thread(new Philosopher(i));
            philosophers[i].start();
        }

        for (int i = 0; i < numPhilosophers; i++) {
            try {
                philosophers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
