package practica1.CircularQ;

import java.util.Iterator;
import utils.Queue;

public class CircularQueue<E> implements Queue<E> {

    private final E[] queue;
    private final int N;
    private int pWrite; //puntero de escritura
    private int pRead;  //puntero de lectura
    private int numElem; //número de elementos

    public CircularQueue(int N) {
        this.N = N;
        queue = (E[]) (new Object[N]);
        pWrite = 0;
        pRead = 0;
        numElem = 0;
    }

    @Override
    public int size() {
        return numElem;
    }

    @Override
    public int free() {
        return N-numElem;
    }

    @Override
    public boolean hasFree(int n) {
        if (n < 0) throw new IllegalArgumentException("Valor negativo");
        return free() >= n;
    }

    @Override
    public boolean empty() {
        return size() == 0;
    }

    @Override
    public boolean full() {
        return numElem == N;
    }

    @Override
    public E peekFirst() {
        return queue[pRead];
    }

    @Override
    public E peekLast() {
        if (empty()) throw new IllegalStateException("Cola vacía");
        return queue[pWrite-1];
    }

    @Override
    public E get() {
        pRead ++;
        pRead = pRead % N;
        numElem--;
        return peekFirst();
    }

    @Override
    public void put(E e) {
        if (full()) throw new IllegalStateException("Cola llena");
        queue[pWrite] = e;
        numElem++;
        pWrite++;
        pWrite = pWrite%N;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();

    }

    class MyIterator implements Iterator {

        private int indice = pRead; //desde donde empezamos a recorrer la cola
        private int numIteraciones = 0; //cuenta en num de saltos que damos

        @Override
        public boolean hasNext() {
            if (empty()) throw new IllegalStateException("Cola vacía");
            return numIteraciones < numElem;
        }

        @Override
        public E next() {
            E temp;
            if (!hasNext()) return null;
            numIteraciones++;
            temp = queue[indice];
            indice++;
            indice = indice%N;
            return temp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
