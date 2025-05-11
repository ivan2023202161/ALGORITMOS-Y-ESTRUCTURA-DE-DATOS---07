package Actividad1;

public class Test {
    public static void main(String[] args) {
        try {
            Stack<Integer> pilaEnteros = new StackArray<>(3);
            pilaEnteros.push(10);
            pilaEnteros.push(20);
            pilaEnteros.push(30);
            
            System.out.println(pilaEnteros);
            pilaEnteros.push(40); // manda un error

            System.out.println("Elemento en tope: " + pilaEnteros.top()); // 30
            System.out.println("Elemento eliminado: " + pilaEnteros.pop()); // 30
            System.out.println("Pila actual: " + pilaEnteros); // 20 10

            Stack<String> pilaCadenas = new StackArray<>(3);
            pilaCadenas.push("a");
            pilaCadenas.push("b");
            System.out.println(pilaCadenas); 
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}