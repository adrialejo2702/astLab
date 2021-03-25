package practica2.Protocol;

import ast.protocols.tcp.TCPSegment;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.w3c.dom.html.HTMLOListElement;
import practica1.CircularQ.CircularQueue;
import utils.Channel;

public class MonitorChannel implements Channel {
    private CircularQueue<TCPSegment> cola;
    private Lock lock;
    private Condition colaLlena, colaVacia;

    public MonitorChannel(int N) {
        cola = new CircularQueue<>(N);
        lock = new ReentrantLock();
        colaLlena = lock.newCondition();
        colaVacia = lock.newCondition();
    }

    @Override
    public void send(TCPSegment seg) {
        lock.lock();
        try {
            while (cola.full()){
                colaLlena.await();
            }
            cola.put(seg);
            colaVacia.signal();

        } catch (InterruptedException exception) {

        } finally {
            lock.unlock();
        }
    }

    @Override
    public TCPSegment receive() {
        lock.lock();
        TCPSegment toAdd = null;

        try {
            while (cola.empty()){
                colaVacia.await();
            }
            toAdd = cola.get();
            colaLlena.signal();
        } catch (InterruptedException exception) {
            
        } finally{
            lock.unlock();
            return toAdd;
        }
    }

    @Override
    public int getMMS() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
