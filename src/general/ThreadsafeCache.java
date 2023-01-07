package general;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
//import java.util.concurrent.locks.ReentrantLock;

public class ThreadsafeCache {

    private Map<String, CompletableFuture<String>> cache = new ConcurrentHashMap<>();
    //ReentrantLock lock = new ReentrantLock();

    private String getItem(String key) {
        cache.computeIfAbsent(key, (k) -> {
            CompletableFuture completableFuture = new CompletableFuture();
            String value = getFromDB(k);
            completableFuture.complete(value);
            return completableFuture;
        });
        try {
            return cache.get(key).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
//        String value = null;
//        lock.lock();
//        if (!cache.containsKey(key)) {
//            CompletableFuture completableFuture = new CompletableFuture();
//            cache.put(key, completableFuture);
//            lock.unlock();
//            value = getFromDB(key);
//            completableFuture.complete(value);
//        } else {
//            CompletableFuture<String> valueFuture = cache.get(key);
//            lock.unlock();
//            try {
//                value = valueFuture.get();
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        return value;
    }

    private String getFromDB(String key) {
        try {
            System.out.println("hi");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return key+"1";
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ThreadsafeCache threadsafeCache = new ThreadsafeCache();
        Thread t1 = new Thread(() -> System.out.println("Threadname is "+Thread.currentThread().getName()+" value is "+threadsafeCache.getItem("key1")));
        Thread t2 = new Thread(() -> System.out.println("Threadname is "+Thread.currentThread().getName()+" value is "+threadsafeCache.getItem("key1")));
        Thread t3 = new Thread(() -> System.out.println("Threadname is "+Thread.currentThread().getName()+" value is "+threadsafeCache.getItem("key2")));
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Time taken is "+(System.currentTimeMillis()-startTime));
    }


}
