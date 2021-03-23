package practica1.CircularQ;

import java.util.Iterator;
import java.util.Queue;

public class TestCQ {

    public static void main(String[] args) {
        CircularQueue<Integer> cola = new CircularQueue<>(8);
        for (int i=0; i<8; i++) {
            cola.put(i);
        }

        System.out.println("Total de elemento:" + cola.size());

        cola.get();
        cola.get();

        Iterator iterator = cola.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());

        System.out.println("Nuevo número de elemetos: " + cola.size());

    }
}
