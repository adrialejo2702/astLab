package practica1.CircularQ;

import java.util.Iterator;
import utils.Queue;

public class CircularQueue<E> implements Queue<E> {

    private final E[] queue;
    private final int N;
    //Completar...

    public CircularQueue(int N) {
        this.N = N;
        queue = (E[]) (new Object[N]);
    }

    @Override
    public int size() {
        throw new RuntimeException("Aquest mètode s'ha de completar...");
    }

    @Override
    public int free() {
        throw new RuntimeException("Aquest mètode s'ha de completar...");
    }

    @Override
    public boolean hasFree(int n) {
        throw new RuntimeException("Aquest mètode s'ha de completar...");
    }

    @Override
    public boolean empty() {
        throw new RuntimeException("Aquest mètode s'ha de completar...");
    }

    @Override
    public boolean full() {
        throw new RuntimeException("Aquest mètode s'ha de completar...");
    }

    @Override
    public E peekFirst() {
        throw new RuntimeException("Aquest mètode s'ha de completar...");
    }

    @Override
    public E peekLast() {
        throw new RuntimeException("Aquest mètode s'ha de completar...");
    }

    @Override
    public E get() {
        throw new RuntimeException("Aquest mètode s'ha de completar...");
    }

    @Override
    public void put(E e) {
        throw new RuntimeException("Aquest mètode s'ha de completar...");
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();

    }

    class MyIterator implements Iterator {

        //Completar...

        @Override
        public boolean hasNext() {
            throw new RuntimeException("Aquest mètode s'ha de completar...");
        }

        @Override
        public E next() {
            throw new RuntimeException("Aquest mètode s'ha de completar...");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
