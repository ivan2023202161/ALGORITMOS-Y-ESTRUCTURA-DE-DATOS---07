package graph;

import java.util.ArrayList;

public class MainListEdge {
    public static void main(String[] args) {
        GraphListEdge<String, Integer> graph = new GraphListEdge<>();

        System.out.println("Prueba de inserción de vértices:");
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        System.out.println("¿Existe el vértice A? " + graph.searchVertex("A")); // true
        System.out.println("¿Existe el vértice F? " + graph.searchVertex("F")); // false

        System.out.println("\nPrueba de inserción de aristas:");
        graph.insertEdge("A", "B", 1);
        graph.insertEdge("A", "C", 4);
        graph.insertEdge("B", "C", 2);
        graph.insertEdge("C", "D", 3);
        graph.insertEdge("D", "E", 5);

        System.out.println("¿Existe una arista entre A y B? " + graph.searchEdge("A", "B")); // true
        System.out.println("¿Existe una arista entre A y D? " + graph.searchEdge("A", "D")); // false

        System.out.println("\nPrueba de recorrido BFS desde A:");
        graph.bfs("A");

        System.out.println("\nVisualización del grafo:");
        System.out.println(graph.toString());


    }
}
