package dev.dmohindru.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

public class CompletableFutureTest {

    public static ForkJoinPool fjp = new ForkJoinPool(10);

    public static int compute() {

        System.out.println("compute: " + Thread.currentThread());
        return 2;
    }

    public static void printIt(int value) {
        System.out.println(value);
        System.out.println("printIt: " + Thread.currentThread());
    }

    public static CompletableFuture<Integer> create()  {
        return CompletableFuture.supplyAsync(() -> compute(), fjp);
        //return CompletableFuture.supplyAsync(() -> compute());
    }

    public static void main(String[] args) {
        System.out.println("main: " + Thread.currentThread());
        create()
                .thenApply(data -> data * 2)
                .thenApply(data -> data + 1)
                .thenAccept(data -> printIt(data));

//        CompletableFuture<Integer> cf = create();
//        sleep(1000);
//        cf.thenAccept(data -> printIt(data));
//        //System.out.println("After create: " + Thread.currentThread());
//        sleep(1000);
    }

    private static boolean sleep(int ms) {
        try {
            Thread.sleep(ms);
            return true;
        } catch (InterruptedException ex) {
            return false;
        }
    }
}
