package graph;

import ListLinked.ListLinked;

public class Vertex<E> {
    private E data; 
    ListLinked<Edge<E>> listAdj; // Lista de aristas adyacentes al vértice

    public Vertex(E data) {
        this.data = data;
        listAdj = new ListLinked<Edge<E>>(); // Crea una lista vacía de aristas
    }

    public E getData() {
        return data;
    }

    // Método para comparar si dos vértices son iguales basándose en sus datos
    public boolean equals(Object o) {
        if (o instanceof Vertex<?>) {
            Vertex<E> v = (Vertex<E>) o;
            return this.data.equals(v.data); 
        }
        return false;
    }

    // Representación en texto del vértice y sus aristas adyacentes
    public String toString() {
        return this.data + " --> " + this.listAdj.toString() + "\n";
    }
}
