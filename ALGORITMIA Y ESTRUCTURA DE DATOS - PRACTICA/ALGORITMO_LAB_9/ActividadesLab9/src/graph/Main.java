package graph;

import java.util.ArrayList;
import java.util.Stack;



public class Main {

	public static void main(String[] args) {
		GraphLink<Integer> graph = new GraphLink<>();
		
		graph.insertVertex(0);
		graph.insertVertex(1);
		graph.insertVertex(2);
		graph.insertVertex(3);
		graph.insertVertex(4);
		graph.insertVertex(5);


		
		graph.insertEdge(0,1);
		graph.insertEdge(1,2);
		graph.insertEdge(2,0);
		graph.insertEdge(2,3);
		graph.insertEdge(1,4);
		graph.insertEdge(4,3);
		graph.insertEdge(3,5);
		graph.insertEdge(4,5);
		



		
		System.out.println(graph);
		System.out.println("Lista de Adyacencia:");
        System.out.println(graph.adjacencyList());
        System.out.println("Matriz de Adyacencia del Grafo Dirigido:");
        int[][] matrix = graph.adjacencyMatrixDi();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

		
		
		ArrayList<Integer> path = graph.bfsPath(1, 3);

        if (path != null) {
            System.out.println("Camino de 1 a 3 (BFS):");
            for (Integer vertex : path) {
                System.out.print(vertex + " ");
            }
        } else {
            System.out.println("No existe un camino de 1 a 3.");
        }
        
        GraphLink<String> graphABC = new GraphLink<>();
        
        graphABC.insertVertex("A");
        graphABC.insertVertex("B");
        graphABC.insertVertex("C");
        graphABC.insertVertex("D");
        graphABC.insertVertex("E");

        graphABC.insertEdgeWeight("A", "B", 1);
        graphABC.insertEdgeWeight("A", "C", 4);
        graphABC.insertEdgeWeight("B", "C", 2);
        graphABC.insertEdgeWeight("B", "D", 5);
        graphABC.insertEdgeWeight("C", "D", 1);
        graphABC.insertEdgeWeight("C", "E", 3);
        graphABC.insertEdgeWeight("D", "E", 2);

        // Imprimir el grafo
        System.out.println("\nGrafo no dirigido (formal): ");
        System.out.println(graphABC);
        
     // Mostramos lista de adyacencia
        System.out.println("Lista de Adyacencia:");
        System.out.println(graph.adjacencyList());
        
        // Mostramos matriz de adyacencia
        System.out.println("Matriz de Adyacencia:");
        int[][] matrixx = graph.adjacencyMatrix();
        for (int[] row : matrixx) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        
     // Verificar si el grafo es un camino
        System.out.println("¿Es un Camino?: " + graph.isPathDi());

        // Verificar si el grafo es un ciclo
        System.out.println("¿Es un Ciclo?: " + graph.isCycleDi());

        // Verificar si el grafo es una rueda
        System.out.println("¿Es una Rueda?: " + graph.isWheelDi());

        // Verificar si el grafo es completo
        System.out.println("¿Es Completo?: " + graph.isCompleteDi());
        
        System.out.println("recorrido dfs: ");
        graph.dfs(0);

        

        // Probar el método Dijkstra
        System.out.println("\nCamino más corto de A a E usando Dijkstra POR PESO: ");
        Stack<String> shortestPath = graphABC.Dijkstra("A", "E");
        if (shortestPath != null) {
        	graphABC.printDijkstraPath(shortestPath);  // Usamos el método para imprimir en orden
        } else {
            System.out.println("No hay camino disponible.");
        }

        // Verificar si el grafo es conexo
        System.out.println("\n\n¿Es el grafo conexo?");
        System.out.println(graphABC.isConexo() ? "Sí, es conexo." : "No, no es conexo.");
        
        
        System.out.println("Grado de A: " + graphABC.getNodeDegree("A"));
        System.out.println("Grado de B: " + graphABC.getNodeDegree("B"));
        System.out.println("Grado de C: " + graphABC.getNodeDegree("C"));
        System.out.println("Grado de D: " + graphABC.getNodeDegree("D"));
        System.out.println("Grado de E: " + graphABC.getNodeDegree("E"));

        // Verificar si el grafo es de tipo camino
        System.out.println("¿El grafo es un camino? " + graphABC.isPath());

        // Verificar si el grafo es de tipo ciclo
        System.out.println("¿El grafo es un ciclo? " + graphABC.isCycle());

        // Verificar si el grafo es de tipo rueda
        System.out.println("¿El grafo es una rueda? " + graphABC.isWheel());

        // Verificar si el grafo es completo
        System.out.println("¿El grafo es completo? " + graphABC.isComplete());
        
        
        System.out.println("\n\nEliminando el vértice B:");
        graphABC.removeVertex("B");
        System.out.println(graphABC);
        
        
        GraphLink<String> graph1 = new GraphLink<>();
        GraphLink<String> graph2 = new GraphLink<>();

        graph1.insertVertex("A");
        graph1.insertVertex("B");
        graph1.insertVertex("C");
        graph1.insertEdge("A", "B");
        graph1.insertEdge("B", "C");
        graph1.insertEdge("C", "A");

        graph2.insertVertex("X");
        graph2.insertVertex("Y");
        graph2.insertVertex("Z");
        graph2.insertEdge("X", "Y");
        graph2.insertEdge("Y", "Z");
        graph2.insertEdge("Z", "X");

        boolean isIsomorphic = graph2.isIsomorphic(graph1);

        // Mostrar el resultado
        System.out.println("¿Son los grafos isomorfos? " + (isIsomorphic ? "Sí" : "No"));

	}
}
