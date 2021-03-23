package practica1.LinkedQ;

import java.util.Iterator;

public class TestLQ {

    public static void main(String[] args) {
        LinkedQueue<Integer> cola = new LinkedQueue<>();
        System.out.println("Num elementos: "+cola.size());
        for (int i=0; i < 9; i++ ){
            cola.put(i);
        }
        System.out.println("Num elementos: "+cola.size());
        Iterator<Integer> iterator = cola.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        cola.get();
        cola.put(99);
        cola.get();
        System.out.println("Nuevo num elementos: " + cola.size());

        System.out.println("Primer elemento:" + cola.peekFirst() + " \t Ultimo elemento: " + cola.peekLast());


    }
}
