package practica2.P0CZ;

public class TestSum {

    public static void main(String[] args) throws InterruptedException {
        CounterThread counterThread1 = new CounterThread();
        CounterThread counterThread2 = new CounterThread();

        counterThread1.start();
        counterThread2.start();

        counterThread1.join();
        counterThread2.join();

        System.out.println("Final value of x: "+ CounterThread.x);

    }
}

