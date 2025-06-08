package graph;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.*;

public class JGraphTEjemplo {
    
    public static void main(String[] args) {
        
        DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        
        graph.setEdgeWeight(graph.addEdge("A", "B"), 5);
        graph.setEdgeWeight(graph.addEdge("A", "C"), 10);
        graph.setEdgeWeight(graph.addEdge("B", "C"), 3);
        graph.setEdgeWeight(graph.addEdge("B", "D"), 2);
        graph.setEdgeWeight(graph.addEdge("C", "D"), 7);
        
        System.out.println("Grafo: " + graph);
        
        // Calcular el camino más corto entre A y D usando Dijkstra
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graph);
        
        // Obtener el camino más corto y su peso
        GraphPath<String, DefaultWeightedEdge> path = dijkstra.getPath("A", "D");
        
        // Mostrar el camino más corto
        if (path != null) {
            System.out.println("Camino más corto de A a D: " + path.getVertexList());
            System.out.println("Peso total: " + path.getWeight());
        } else {
            System.out.println("No hay camino entre A y D.");
        }
        
        System.out.println("Vecinos de B: " + graph.outgoingEdgesOf("B"));
        
        
    }
}
