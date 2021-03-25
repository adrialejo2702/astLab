package practica2.P0CZ.Monitor;

public class TestSumCZ {

    public static void main(String[] args) throws InterruptedException {
        MonitorCZ monitorCZ = new MonitorCZ();
        CounterThreadCZ counterThreadCZ1 = new CounterThreadCZ(monitorCZ);
        CounterThreadCZ counterThreadCZ2 = new CounterThreadCZ(monitorCZ);

        counterThreadCZ1.start();
        counterThreadCZ2.start();

        counterThreadCZ1.join();
        counterThreadCZ2.join();

        System.out.println("Final value of x: "+ monitorCZ.getX());
    }
}
