package dev.dmohindru.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelMultipleFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(0);
        CompletableFuture<Integer> futureOne =
                Util.fromSupplier("First number computation", atomicInteger, 2000, 1);

        CompletableFuture<Integer> futureSecond =
                Util.fromSupplier("Second number computation", atomicInteger,4000, 2);

        CompletableFuture<Integer> futureThird =
                Util.fromSupplier("Third number computation", atomicInteger, 6000, 3);

        CompletableFuture<Void> combinedFuture =
                CompletableFuture.allOf(futureOne, futureSecond, futureThird);

        System.out.println("Main thread name: " + Thread.currentThread().getName());
        long startTime = System.currentTimeMillis();
        combinedFuture.get();
        long endTime = System.currentTimeMillis();
        System.out.printf("Total computation time (in millisecond): %d\n", (endTime - startTime));
//        System.out.println("futureOne done: " + futureOne.isDone());
//        System.out.println("futureSecond done: " + futureSecond.isDone());
//        System.out.println("futureThird done: " + futureThird.isDone());
        System.out.println("atomicInteger value: " + atomicInteger.get());

    }
}
