package general;

public class StateMachine {

    enum State {
        FIRST,
        SECOND,
        THIRD
    }

    State state = State.FIRST;

    public static void main(String[] args) throws InterruptedException {

        StateMachine test = new StateMachine();
        test.test();
    }

    private void test() throws InterruptedException {
        System.out.println(state);
        Runnable r1 = () -> state = State.SECOND;
        Thread t1 = new Thread(r1);
        t1.start();
        t1.join();
        System.out.println(state);

        Runnable r2 = () -> state = State.THIRD;
        Thread t2 = new Thread(r2);
        t2.start();
        t2.join();
        System.out.println(state);
    }
}
