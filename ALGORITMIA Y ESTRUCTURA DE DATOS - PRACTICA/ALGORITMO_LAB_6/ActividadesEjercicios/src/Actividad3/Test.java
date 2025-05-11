package Actividad3;
import Actividad1.ExceptionIsEmpty;

public class Test {
    public static void main(String[] args) {
        try {
            PriorityQueue<String, Integer> colaPrioridad = new PriorityQueueLinkSort<>();

            colaPrioridad.enqueue("Tarea A", 2);
            colaPrioridad.enqueue("Tarea B", 5); 
            colaPrioridad.enqueue("Tarea C", 1); 

            System.out.println(colaPrioridad); // B, A, C

            System.out.println("Frente: " + colaPrioridad.front()); // Tarea B
            System.out.println("Final: " + colaPrioridad.back());   // Tarea C

            System.out.println("Desencolado: " + colaPrioridad.dequeue()); // Tarea B
            System.out.println("Cola actual: " + colaPrioridad); // A, C

        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
