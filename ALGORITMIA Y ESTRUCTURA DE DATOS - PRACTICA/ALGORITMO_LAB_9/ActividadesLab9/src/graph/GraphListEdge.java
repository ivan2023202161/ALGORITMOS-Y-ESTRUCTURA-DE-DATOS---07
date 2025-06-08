package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphListEdge<V,E> {

    ArrayList<VertexObj<V,E>> secVertex;
    ArrayList<EdgeObj<V,E>> secEdge;

    public GraphListEdge(){
        this.secVertex = new ArrayList<VertexObj<V,E>>();
        this.secEdge = new ArrayList<EdgeObj<V,E>>();
    }

    // Método para agregar un vértice
    public void insertVertex(V info) {
        secVertex.add(new VertexObj<V,E>(info, secVertex.size()));
    }

    // Método para agregar una arista
    public void insertEdge(V info1, V info2, E edgeInfo) {
        VertexObj<V,E> vert1 = findVertex(info1);
        VertexObj<V,E> vert2 = findVertex(info2);

        if (vert1 != null && vert2 != null) {
            EdgeObj<V,E> newEdge = new EdgeObj<>(vert1, vert2, edgeInfo, secEdge.size());
            secEdge.add(newEdge);
        }
    }

    // Método para buscar un vértice
    public boolean searchVertex(V info) {
        for (VertexObj<V,E> vertex : secVertex) {
            if (vertex.info.equals(info)) {
                return true; // El vértice existe
            }
        }
        return false; // El vértice no existe
    }

    // Método para buscar una arista entre dos vértices
    public boolean searchEdge(V info1, V info2) {
        for (EdgeObj<V,E> edge : secEdge) {
            if ((edge.endVertex1.info.equals(info1) && edge.endVertex2.info.equals(info2)) ||
                (edge.endVertex1.info.equals(info2) && edge.endVertex2.info.equals(info1))) {
                return true; // La arista existe
            }
        }
        return false; // La arista no existe
    }

    // Método para realizar un recorrido en anchura (BFS)
    public void bfs(V start) {
        VertexObj<V,E> startVertex = findVertex(start);
        if (startVertex == null) {
            System.out.println("Vértice de inicio no encontrado.");
            return;
        }

        // Estructuras para BFS
        ArrayList<VertexObj<V,E>> visited = new ArrayList<>();
        Queue<VertexObj<V,E>> queue = new LinkedList<>();

        queue.add(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            VertexObj<V,E> current = queue.poll();
            System.out.println(current.info); // Imprime el vértice visitado

            // Recorre las aristas que conectan con el vértice actual
            for (EdgeObj<V,E> edge : secEdge) {
                if (edge.endVertex1.equals(current) && !visited.contains(edge.endVertex2)) {
                    visited.add(edge.endVertex2);
                    queue.add(edge.endVertex2);
                }
                if (edge.endVertex2.equals(current) && !visited.contains(edge.endVertex1)) {
                    visited.add(edge.endVertex1);
                    queue.add(edge.endVertex1);
                }
            }
        }
    }

    // Método auxiliar para buscar un vértice por su información
    private VertexObj<V,E> findVertex(V info) {
        for (VertexObj<V,E> vertex : secVertex) {
            if (vertex.info.equals(info)) {
                return vertex;
            }
        }
        return null; // Si no se encuentra el vértice
    }

    // Método toString para representar el grafo
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (VertexObj<V,E> vertex : secVertex) {
            sb.append(vertex.info).append(" --> ");
            for (EdgeObj<V,E> edge : secEdge) {
                if (edge.endVertex1.equals(vertex)) {
                    sb.append(edge.endVertex2.info).append(" ");
                }
                if (edge.endVertex2.equals(vertex)) {
                    sb.append(edge.endVertex1.info).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

