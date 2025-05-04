package actividad;

public class Node<T> {  //Clase generica
    T valor; 
    Node<T> siguiente; // Referencia al siguiente nodo en la estructura

    public Node(T valor) {
        this.valor = valor; // Inicializa el valor del nodo
        this.siguiente = null; // Inicializa con null (ultimo nodo)
    }
}

