package general;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {

    AtomicInteger no = new AtomicInteger(1);
    public static void main(String[] args) throws InterruptedException {
        new ThreadTest().test();
    }

    private void test() throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("Resetting");
            no = new AtomicInteger(2);
        });
        t.start();
        t.join();
        t.stop();
        System.out.println(no);
    }
}
