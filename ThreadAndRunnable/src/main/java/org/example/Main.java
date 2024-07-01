package org.example;

public class Main {

    private static int j;

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " i is " + i);
            }
        };
        r.run();

        Runnable r2 = () -> {
            for (j = 100; j < 200; j++) {
                System.out.println(Thread.currentThread().getName() + " J is " + j);
            }
        };

        var thread1 = new Thread(r, "Workker thread");
        thread1.start();
        thread1.join();

        var thread2 = new Thread(r,"Another workek thread");
        thread2.start();
        thread2.join();


        var thread3 = new Thread(r2, "Workker thread2");
        var thread4 = new Thread(r2,"Another workek thread2");
        thread3.start();
        thread4.start();

        System.out.println("Main exiting");
    }
}