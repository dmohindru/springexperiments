package dev.dmohindru.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Util {

    public static <T> T getResult(String computationName, long delay, T value) {
        System.out.printf("Running computation [%s] in thread [%s]%n",
                computationName,
                Thread.currentThread().getName());
        sleep(delay);
        return value;

    }
    public static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * *
     * @return
     * @throws InterruptedException
     * This method returns value from a completable future via creating its instance and using Executors
     * framework
     */
    public static Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            System.out.println("CompletableFuture executing in thread:" + Thread.currentThread().getName());
            Thread.sleep(5000);
            completableFuture.complete("Hello");
            return null;

        });

        return completableFuture;
    }

    /**
     * *
     * @return
     * CompletableFuture from an async supplier
     */

    public static <T> CompletableFuture<T> fromSupplier(String computationName, long delay, T data ) {
        return CompletableFuture.supplyAsync(() -> Util.getResult(computationName, delay, data));
    }
}
