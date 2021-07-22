package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Integer> result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;}).thenApply(s -> {
            System.out.println("Result is : " + s);
            return s;
        });

        result.get();
    }
}
