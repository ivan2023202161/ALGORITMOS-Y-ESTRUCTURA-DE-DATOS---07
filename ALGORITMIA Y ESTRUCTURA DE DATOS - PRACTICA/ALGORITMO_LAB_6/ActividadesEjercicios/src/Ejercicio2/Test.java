package Ejercicio2;

import Actividad2.Queue;
import Actividad1.ExceptionIsEmpty;

public class Test {
    public static void main(String[] args) {
        try {
            Queue<String> cola = new QueueArray<>(5);
            cola.enqueue("A");
            cola.enqueue("B");
            cola.enqueue("C");

            System.out.println(cola); // Queue: A B C
            System.out.println("Frente: " + cola.front()); // A
            System.out.println("Final: " + cola.back());   // C

            cola.dequeue(); // Elimina A
            System.out.println("Después de dequeue: " + cola); // B C

            cola.enqueue("D");
            cola.enqueue("E");
            cola.enqueue("F"); // Llena la cola

            System.out.println("Cola completa: " + cola); // B C D E F
            cola.enqueue("Z"); // Error: Cola llena


        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
