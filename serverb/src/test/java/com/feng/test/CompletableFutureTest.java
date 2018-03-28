package com.feng.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureTest {

    @Test
    public void test1() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            System.out.println("isDaemon:"+Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("in ok!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(cf.isDone());
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(cf.isDone());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        CompletableFuture<String>cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            System.out.println("isDaemon:"+Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        System.out.println(cf.getNow(null));
    }

    @Test
    public void test3() {
        CompletableFuture<String>cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            System.out.println("isDaemon:"+Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        });
        System.out.println(cf.getNow(null));
        System.out.println(cf.join());
    }

    @Test
    public void test4() {
        ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
            int count = 1;
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "custom-executor-" + count++);
            }
        });

        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("isDaemon" + Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        }, executor);
        System.out.println("getNow:"+cf.getNow(null));
        System.out.println(cf.join());
    }

    @Test
    public void test5() {
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("thenAccept message")
                .thenAccept(s ->result.append(s));
        System.out.println("length:"+result.length());
    }

    @Test
    public void test6() {
        StringBuilder result = new StringBuilder();
        CompletableFuture<Void> cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(s ->result.append(s));
        cf.join();
        System.out.println("length:"+result.length());
    }

    @Test
    public void test7() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").
                thenApplyAsync(s -> s.substring(10));
        CompletableFuture<String> exceptionHandler = cf.handle((s, th) -> (th != null) ? "message upon cancel" : "");
        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
        System.out.println("Was not completed exceptionally:"+cf.isCompletedExceptionally());
        try {
            cf.join();
            System.out.println("Should have thrown an exception");
        } catch(CompletionException ex) { // just for testing
            System.out.println("completed exceptionally:"+ex.getCause().getMessage());
        }
        System.out.println(exceptionHandler.join());
    }

    @Test
    public void test8() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase);
        CompletableFuture cf2 = cf.exceptionally(throwable -> "canceled message");
        System.out.println("Was not canceled:"+ cf.cancel(true));
        System.out.println("Was not completed exceptionally:"+cf.isCompletedExceptionally());
        System.out.println("canceled message:"+ cf2.join());
    }

    @Test
    public void test9() {
        String original = "Message";
        CompletableFuture cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(s));
        CompletableFuture<Object> cf2 = cf1.applyToEither(
                CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                s -> s + " from applyToEither");
        System.out.println(cf2.join());
    }

    private String delayedLowerCase(String s) {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s.toLowerCase();
    }

    private String delayedUpperCase(String s) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s.toUpperCase();
    }

    @Test
    public void test10() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture<Void> cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(this::delayedUpperCase)
                .acceptEither(CompletableFuture.completedFuture(original).thenApplyAsync(this::delayedLowerCase),
                        s -> result.append(s).append("acceptEither"));
        cf.join();
        System.out.println("result:"+result);
    }

    @Test
    public void test11() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).runAfterBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                () -> result.append("done"));
        System.out.println("Result:"+ result);
    }

    @Test
    public void test12() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).thenAcceptBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                (s1, s2) -> result.append(s1).append(s2));
        System.out.println("msg:"+result.toString());
    }

    @Test
    public void test13() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(this::delayedUpperCase)
                .thenCombine(CompletableFuture.completedFuture(original).thenApply(this::delayedLowerCase),
                        (s1, s2) -> s1 + s2);
        System.out.println((cf.getNow(null)));
    }

    @Test
    public void test14() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(this::delayedUpperCase)
                .thenCombine(CompletableFuture.completedFuture(original).thenApplyAsync(this::delayedLowerCase),
                (s1, s2) -> s1 + s2);
        System.out.println(cf.join());
    }

    @Test
    public void test15() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(this::delayedUpperCase)
                .thenCompose(upper -> CompletableFuture.completedFuture(original).thenApply(this::delayedLowerCase)
                        .thenApply(s -> upper + s));
        System.out.println(cf.join());
    }

    @Test
    public void test16() {
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg)
                        .thenApply(this::delayedUpperCase))
                .collect(Collectors.toList());
        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, th) -> {
            if(th == null) {
                System.out.println(res);
            }
        });
    }

    @Test
    public void test17() {
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(this::delayedUpperCase))
                .collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((v, th) -> {
            futures.forEach(cf -> System.out.println(cf.getNow(null)));
        });
    }

    @Test
    public void test18() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(s -> delayedUpperCase(s)))
                .collect(Collectors.toList());
        CompletableFuture allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((v, th) -> {
                    futures.forEach(cf -> System.out.println(cf.getNow(null)));
                    result.append("done");
                });
        allOf.join();
        System.out.println(result);
    }
}
