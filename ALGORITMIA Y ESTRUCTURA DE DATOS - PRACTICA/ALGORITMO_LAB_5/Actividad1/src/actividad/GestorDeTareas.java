package actividad;


import java.util.ArrayList;
import java.util.List;

// Clase generica que gestiona tareas de tipo T, donde T debe ser un tipo que implemente la interfaz Comparable.
public class GestorDeTareas<T extends Comparable<T>> { 
    private Node<T> cabeza; // Nodo cabeza de la lista enlazada
    private List<T> tareasCompletadas = new ArrayList<>(); // Lista de tareas completadas

    // agregar una tarea a la lista
    public void agregarTarea(T tarea) {
        Node<T> nueva = new Node<>(tarea); // Crea un nuevo nodo con la tarea
        if (cabeza == null) { // Si la lista está vacía, la nueva tarea es la cabeza
            cabeza = nueva;
        } else { // Si no está vacía, recorre hasta el final y agrega la tarea
            Node<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nueva; // Añade la tarea al final
        }
    }

    // eliminar una tarea de la lista
    public boolean eliminarTarea(T tarea) {
        if (cabeza == null) {
            return false;
        }
        if (cabeza.valor.equals(tarea)) { // Si la tarea está en la cabeza, se eliina directamente
            cabeza = cabeza.siguiente;
            return true;
        }

        Node<T> actual = cabeza;
        while (actual.siguiente != null && !actual.siguiente.valor.equals(tarea)) { // busca la tarea
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) { // si la tarea fue encontrada se elimina
            actual.siguiente = actual.siguiente.siguiente;
            return true;
        }

        return false; // si no se encuentra la tarea, no se elimina
    }

    // verificar si una tarea está en la lista
    public boolean contieneTarea(T tarea) {
        Node<T> actual = cabeza;
        while (actual != null) {
            if (actual.valor.equals(tarea)) { // si esta retorna true
            }
            actual = actual.siguiente;
        }
        return false; // si no se encuentra, retorna false
    }

    // imprimir todas las tareas
    public void imprimirTareas(){
        if (cabeza == null) { 
            System.out.println("la lista esta vacia");
        } else {
            Node<T> actual = cabeza;
            while(actual != null) { // recorre la lista e imprime cada tarea
                System.out.println(actual.valor);
                actual = actual.siguiente;
            }
        }
    }

    // contar el número de tareas en la lista
    public int contarTareas() {
        int contador = 0;
        Node<T> actual = cabeza;
        while (actual != null) { // Recorre la lista y cuenta los nodos
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    // obtener la tarea con mayor prioridad (la menor es mayor)
    public T obtenerTareaMasPrioritaria() {
        if (cabeza == null) { 
            return null;
        }
        Node<T> actual = cabeza;
        T mejor = actual.valor;
        while (actual != null) { // Compara cada tarea para encontrar la de mayor prioridad
            if (actual.valor.compareTo(mejor) < 0) {
                mejor = actual.valor;
            }
            actual = actual.siguiente;
        }
        return mejor;
    }

    // invertir el orden de las tareas en la lista
    public void invertir() { 
        Node<T> nodoAnterior = null; // Inicializa null
        Node<T> nodoActual = cabeza; // El nodo actual comienza en la cabeza de la lista
        Node<T> nodoSiguiente; // Variable para almacenar el siguiente nodo temporalmente

        while (nodoActual != null) { 
            nodoSiguiente = nodoActual.siguiente; // Guarda el siguiente nodo para no perderlo al cambiar los enlaces
            nodoActual.siguiente = nodoAnterior; // Reinvierte el enlace del nodo actual, apuntando al nodo anterior en lugar del siguiente
            nodoAnterior = nodoActual; // Mueve el nodoAnterior a nodoActual (hacia adelante en la lista)
            nodoActual = nodoSiguiente; // Mueve el nodoActual al siguiente nodo guardado previamente
        }

        cabeza = nodoAnterior; // último nodo se convierte en la nueva cabeza
    }

    // marcar una tarea como completada y moverla a la lista de tareas completadas
    public boolean completarTarea(T tarea) {
        if (eliminarTarea(tarea)) { // Elimina la tarea de la lista activa
            tareasCompletadas.add(tarea); // agrega a las completadas
            return true;
        }
        return false; // Si no se elimina, retorna false
    }

    // imprimir las tareas completadas
    public void imprimirTareasCompletadas() {
        System.out.println("Tareas completadas:");
        for (T tarea : tareasCompletadas) {
            System.out.println(tarea);
        }
    }
}

