package Actividad2;
import Actividad1.ExceptionIsEmpty;

public class Test {
    public static void main(String[] args) {
        try {
            Queue<String> cola = new QueueLink<>();
            cola.enqueue("a");
            cola.enqueue("b");
            cola.enqueue("c");

            System.out.println(cola); 
            System.out.println("Primero: " + cola.front()); // a
            System.out.println("Último: " + cola.back());   // c

            System.out.println("Eliminado: " + cola.dequeue()); // a
            System.out.println("Después del dequeue: " + cola); 

            Queue<Integer> colaNumeros = new QueueLink<>();
            colaNumeros.enqueue(1);
            colaNumeros.enqueue(2);
            colaNumeros.enqueue(3);
            System.out.println(colaNumeros); // 1 2 3

        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
