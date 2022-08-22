package dev.dmohindru.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestOne {



    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long startTime = 0;
        long endTime = 0;

        System.out.println("Main thread: " + Thread.currentThread().getName());
        CompletableFuture<Integer> firstFuture =
                Util.fromSupplier("First argument computation", 1000, 1);

        CompletableFuture<Integer> secondFutureThenApply =
                firstFuture.thenApply(i -> {
                    return i + Util.getResult("Second argument (thenApply) computation", 3000, 2);
                });

        CompletableFuture<Integer> secondFutureThenCompose =
                firstFuture.thenCompose(i ->  {
                    return Util.fromSupplier(
                            "Second argument (thenCompose) computation",
                            3000,
                            i + 2);
                });



//        System.out.println("-----thenApply() stats-------");
//        startTime = System.currentTimeMillis();
//        Integer resultThenApply = secondFutureThenApply.get();
//        endTime = System.currentTimeMillis();
//        System.out.printf("Result [%s] took [%d] milliseconds to execute\n", resultThenApply, endTime - startTime);

        System.out.println("-----thenCompose() stats-------");
        startTime = System.currentTimeMillis();
        Integer resultThenCompose = secondFutureThenCompose.get();
        endTime = System.currentTimeMillis();
        System.out.printf("Result [%s] took [%d] milliseconds to execute", resultThenCompose, endTime - startTime);

    }
}
