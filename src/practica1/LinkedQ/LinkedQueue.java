package practica1.LinkedQ;

import java.util.Iterator;
import utils.Queue;

public class LinkedQueue<E> implements Queue<E> {

    private int numElem;
    private Node<E> primero;
    private Node<E> ultimo;

    public LinkedQueue(){
        numElem = 0;
        primero = null;
        ultimo = null;
    }


    @Override
    public int size() {
        return numElem;
    }

    @Override
    public int free() {
        throw new UnsupportedOperationException("Not applies");
    }

    @Override
    public boolean hasFree(int n) {
        if (n<0) throw new IllegalArgumentException("Valor negativo");
        return true;
    }

    @Override
    public boolean empty() {
        if (numElem == 0) return true;
        return false;
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public E peekFirst() {
        return primero.getValue();
    }

    @Override
    public E peekLast() {
        return ultimo.getValue();
    }

    @Override
    public E get() {
        E temp;
        if (empty()) throw new IllegalStateException("Cola vacía");
        numElem--;
        temp = primero.getValue();
        primero = primero.getNext();
        if (empty()) ultimo = null; //caso quitamos el único elemento que hay
        return temp;
    }

    @Override
    public void put(E value) {
        Node newNode = new Node();
        newNode.setValue(value);

        if (size() == 0) {
            primero = newNode;
        }else {
            ultimo.setNext(newNode);

        }
        ultimo = newNode;
        numElem++;

    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator {
        private int numIteraciones = 0;
        private Node<E> actual = primero;

        @Override
        public boolean hasNext() {
            return  numIteraciones < numElem;
        }

        @Override
        public E next() {
            E temp;
            Node<E> auxiliar;
            if(!hasNext()) return null;
            temp = actual.getValue();
            numIteraciones++;
            auxiliar = actual;
            actual = actual.getNext();
            return temp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
