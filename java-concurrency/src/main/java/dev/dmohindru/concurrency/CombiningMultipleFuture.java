package dev.dmohindru.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CombiningMultipleFuture {
    public static void main(String[] args) {
        long startTime = 0;
        long endTime = 0;

        System.out.println("---- Start of sequential computation ----");
        System.out.println("Main thread: " + Thread.currentThread().getName());
        startTime = System.currentTimeMillis();
        Integer firstResult = Util.getResult("First number computation", 2000, 1);
        Integer secondResult = Util.getResult("Second number computation", 4000, 2);
        Integer thirdResult = Util.getResult("Third number computation", 6000, 3);
        int result = firstResult + secondResult + thirdResult;
        endTime = System.currentTimeMillis();
        System.out.println("Result of computation: " + result);
        System.out.printf("Total computation time (Sequential): %d\n", (endTime-startTime));


        System.out.println("---- Start of parallel computation ----");
        System.out.println("Main thread: " + Thread.currentThread().getName());
        CompletableFuture<Integer> futureOne =
                Util.fromSupplier("First number computation", 2000, 1);

        CompletableFuture<Integer> futureSecond =
                Util.fromSupplier("Second number computation",4000, 2);

        CompletableFuture<Integer> futureThird =
                Util.fromSupplier("Third number computation", 6000, 3);


        startTime = System.currentTimeMillis();
        result = Stream.of(futureOne, futureSecond, futureThird)
                .map(CompletableFuture::join)
                .reduce(0, Integer::sum);
        endTime = System.currentTimeMillis();

        System.out.println("Result of computation: " + result);
        System.out.printf("Total computation time (Parallel): %d\n", (endTime-startTime));
    }
}
