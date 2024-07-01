package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

class MyTask implements Callable<String> {

    private static int nextId = 0;
    private int jobId = nextId++;

    @Override
    public String call() throws Exception {
        System.out.println("Job " + jobId + " starting!");
        try {
            Thread.sleep((int)(Math.random() * 2000 + 1000));
        } catch (InterruptedException ie) {
            System.out.println("Job " + jobId + " received shutdown request!");
            return "Job " + jobId + " received shutdown request!";
        }
        if(Math.random() > 0.7) {
            System.out.println("Job " + jobId + " throwing exception");
            throw new SQLException("Job " + jobId + " Database broke!");
        }
        System.out.println("Job " + jobId + " completed normally!");
        return "Job " + jobId + " completed normally!";
    }
}

public class Main {
    public static void main(String[] args) {
        final int JOB_COUNT = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> handels = new ArrayList<>();
        for (int i = 0; i < JOB_COUNT; i++) {
            handels.add(executorService.submit(new MyTask()));
        }

        try {
            Thread.sleep(800);
            handels.get(1).cancel(true);
        } catch (InterruptedException ie) {
            System.out.println("Main thread interrupted??");
        }

        while (handels.size() > 0) {
            Iterator<Future<String>> ifs = handels.iterator();
            while (ifs.hasNext()) {
                Future<String> fs = ifs.next();
                if (fs.isDone()) {
                    ifs.remove();
                    try {
                        String jobResult = fs.get();
                        System.out.println("Got a job result: " + jobResult);
                    } catch (InterruptedException ie) {
                        System.out.println("Main thread interupted??");
                    } catch (ExecutionException ee) {
                        System.out.println("Job threw an exception: " + ee.getCause());
                    } catch (CancellationException ce) {
                        System.out.println("Job was cancelled!");
                    }
                }
            }
        }

//        executorService.shutdownNow();

        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException ie) {
            System.out.println("Main thread interrupted??");
        }
        executorService.shutdown();

        System.out.println("Hello world!");
    }
}