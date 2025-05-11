package Ejercicio1;
import Actividad1.Stack;
import Actividad1.ExceptionIsEmpty;

public class Test {
    public static void main(String[] args) {
        try {
            Stack<Integer> pila = new StackLink<>();
            pila.push(1);
            pila.push(10);
            pila.push(100);

            System.out.println(pila); 
            System.out.println("Tope: " + pila.top()); // 100

            System.out.println("Pop: " + pila.pop()); // 100
            System.out.println("Pila actual: " + pila); // 10 1

            Stack<String> pilaStr = new StackLink<>();
            pilaStr.push("A");
            pilaStr.push("B");
            pilaStr.push("C");
            System.out.println(pilaStr); // Stack: C B A

        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
