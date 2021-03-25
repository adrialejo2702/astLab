package practica2.P1Sync.Monitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorSync {

    private final int N;
    private int currentID;
    private Lock lock;
    private Condition yourTurn;

    public MonitorSync(int N) {
        this.N = N;
        currentID = 0;
        lock = new ReentrantLock();
        yourTurn = lock.newCondition();
    }

    public void waitForTurn(int id) {
        lock.lock();
        try {
            while (currentID != id) {
                yourTurn.await();
            }
        }catch (InterruptedException ex){

        }
        finally {
            lock.unlock();
        }

    }

    public void transferTurn() {
        lock.lock();
        try{
            yourTurn.signalAll();
            currentID++;
            currentID =  currentID % N;

        } finally {
            lock.unlock();
        }
    }
}
