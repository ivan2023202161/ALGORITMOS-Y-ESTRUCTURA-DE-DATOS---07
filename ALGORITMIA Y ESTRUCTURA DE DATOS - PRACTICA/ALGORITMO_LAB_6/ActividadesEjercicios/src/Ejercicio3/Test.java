package Ejercicio3;
import Actividad3.PriorityQueue;
import Actividad1.ExceptionIsEmpty;

public class Test {
    public static void main(String[] args) {
        try {
            PriorityQueue<String, Integer> colaPrio = new PriorityQueueLinked<>(3); // 0 = alta, 1 = media, 2 = baja

            colaPrio.enqueue("Tarea A", 1);
            colaPrio.enqueue("Tarea B", 2);
            colaPrio.enqueue("Tarea C", 2);
            colaPrio.enqueue("Tarea D", 0);

            System.out.println(colaPrio);
            System.out.println("Frente: " + colaPrio.front()); // Tarea D
            System.out.println("Final: " + colaPrio.back());   // Tarea C

            System.out.println("Dequeue: " + colaPrio.dequeue()); // Tarea D
            System.out.println("Después de dequeue:\n" + colaPrio);

        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
