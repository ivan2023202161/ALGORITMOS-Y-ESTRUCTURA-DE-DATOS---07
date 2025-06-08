
package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ListLinked.ListLinked;
import Tda.Queue;

public class GraphLink<E> {
    protected ListLinked<Vertex<E>> listVertex; // Lista de vértices en el grafo

    // Constructor que inicializa la lista de vértices
    public GraphLink() {
        listVertex = new ListLinked<Vertex<E>>();
    }

    // Inserta un nuevo vértice solo si no existe en el grafo
    public void insertVertex(E data) {
        if (!searchVertex(data)) {
            listVertex.insertLast(new Vertex<E>(data));
        }
    }

    // Inserta una arista sin peso (peso por defecto es -1)
    public void insertEdge(E verOri, E verDes) {
        insertEdgeP(verOri, verDes, -1);
    }

    // Inserta una arista con un peso específico
    public void insertEdgeP(E verOri, E verDes, int weight) {
        Vertex<E> ori = getVertex(verOri); // Vértice de origen
        Vertex<E> des = getVertex(verDes); // Vértice de destino
        if (ori != null && des != null) {
            Edge<E> edge = new Edge<E>(des, weight); // Crea la arista
            if (!ori.listAdj.contains(edge)) { // Si la arista no existe, la agrega
                ori.listAdj.insertLast(edge);
            }
        }
    }

    // Busca si un vértice existe en el grafo
    public boolean searchVertex(E v) {
        return getVertex(v) != null;
    }

    // Busca si una arista existe entre los vértices v -> z
    public boolean searchEdge(E v, E z) {
        Vertex<E> ori = getVertex(v);
        Vertex<E> des = getVertex(z);
        if (ori != null && des != null) {
            Edge<E> target = new Edge<>(des); // Crea una arista sin peso para comparación
            return ori.listAdj.contains(target); // Verifica si la arista existe
        }
        return false;
    }

    // Método privado para buscar un vértice en la lista de vértices
    private Vertex<E> getVertex(E data) {
        ListLinked<Vertex<E>>.Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            if (current.getData().getData().equals(data)) {
                return current.getData(); // Devuelve el vértice si lo encuentra
            }
            current = current.getNext();
        }
        return null; // Si no lo encuentra, devuelve null
    }

    // Representación en texto del grafo
    public String toString() {
        return listVertex.toString(); // Muestra todos los vértices y sus adyacentes
    }
    
    public void removeVertex(E v) {
        Vertex<E> target = getVertex(v); // Busca el vértice a eliminar
        if (target == null) return; // Si no se encuentra, no hace nada

        // Eliminar todas las aristas que llegan a este vértice
        ListLinked<Vertex<E>>.Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            current.getData().listAdj.remove(new Edge<>(target)); // Elimina las aristas apuntando al vértice
            current = current.getNext();
        }

        listVertex.remove(target); // Elimina el vértice de la lista de vértices
    }

    public void removeEdge(E v, E z) {
        Vertex<E> ori = getVertex(v); // Busca el vértice de origen
        Vertex<E> des = getVertex(z); // Busca el vértice de destino
        if (ori != null && des != null) {
            Edge<E> target = new Edge<>(des); // Crea la arista entre origen y destino
            ori.listAdj.remove(target); // Elimina la arista de la lista de adyacencia
        }
    }


    

    public void dfs(E start) {
        Vertex<E> startVertex = getVertex(start); // Obtiene el vértice de inicio
        if (startVertex == null) return; 

        ListLinked<Vertex<E>> visitados = new ListLinked<>(); 
        dfsRecursive(startVertex, visitados); // Llama al método recursivo para hacer el DFS
    }

    private void dfsRecursive(Vertex<E> current, ListLinked<Vertex<E>> visitados) {
        System.out.println(current.getData()); // Imprime el vértice actual
        visitados.insertLast(current); // Marca el vértice como visitado

        current.listAdj.goToStart(); // Inicia en el primer vecino
        while (!current.listAdj.isAtEnd()) { // Recorre todos los vecinos del vértice
            Edge<E> edge = current.listAdj.getElement(); // Obtiene la arista
            Vertex<E> vecino = edge.getRefDest(); // Obtiene el vértice vecino

            // Si el vecino no ha sido visitado, se llama recursivamente
            if (!visitados.contains(vecino)) {
                dfsRecursive(vecino, visitados);
            }
            current.listAdj.next(); // Avanza al siguiente vecino
        }
        
    }

    
    
    public void bfs(E start) {
        Vertex<E> startVertex = getVertex(start);
        if (startVertex == null) return;

        ListLinked<Vertex<E>> visited = new ListLinked<>();
        Queue<Vertex<E>> queue = new Queue<>(); 

        queue.enqueue(startVertex);
        visited.insertLast(startVertex);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.dequeue();
            System.out.println(current.getData());

            current.listAdj.goToStart();
            while (!current.listAdj.isAtEnd()) {
                Edge<E> edge = current.listAdj.getElement();
                Vertex<E> neighbor = edge.getRefDest();

                if (!visited.contains(neighbor)) {
                    visited.insertLast(neighbor);
                    queue.enqueue(neighbor);
                }
                current.listAdj.next();
            }
        }
    }

    
    public ArrayList<E> bfsPath(E start, E goal) {
        Vertex<E> startVertex = getVertex(start); 
        Vertex<E> goalVertex = getVertex(goal); 

        if (startVertex == null || goalVertex == null) return null; 

        ListLinked<Vertex<E>> visited = new ListLinked<>();
        ListLinked<Vertex<E>> predecessors = new ListLinked<>(); // Lista de predecesores para reconstruir el camino
        Queue<Vertex<E>> queue = new Queue<>(); // Cola para la búsqueda en amplitud

        queue.enqueue(startVertex); // Encola el vértice de inicio
        visited.insertLast(startVertex); // Marca el vértice de inicio como visitado
        predecessors.insertLast(null); // El vértice de inicio no tiene predecesor

        // Búsqueda en amplitud hasta encontrar el objetivo
        while (!queue.isEmpty()) {
            Vertex<E> current = queue.dequeue();

            if (current.equals(goalVertex)) {
                break;
            }

            current.listAdj.goToStart();
            while (!current.listAdj.isAtEnd()) { // Recorre los vecinos del vértice actual
                Edge<E> edge = current.listAdj.getElement();
                Vertex<E> neighbor = edge.getRefDest();

                if (!visited.contains(neighbor)) { // Si el vecino no ha sido visitado
                    queue.enqueue(neighbor); // Encola el vecino
                    visited.insertLast(neighbor); // Marca como visitado
                    predecessors.insertLast(current); // Guarda el predecesor del vecino
                }

                current.listAdj.next(); // Avanza al siguiente vecino
            }
        }

        // Si no se ha encontrado el objetivo, retorna null
        if (!visited.contains(goalVertex)) {
            return null;
        }

        // Reconstrucción del camino desde el objetivo hasta el inicio
        ArrayList<E> path = new ArrayList<>();
        Vertex<E> current = goalVertex;

        while (current != null) {
            path.add(0, current.getData()); // Inserta al inicio para invertir el orden del camino
            int index = visited.indexOf(current); // Obtiene el índice del vértice actual
            current = predecessors.get(index); // Retrocede al predecesor
        }

        return path; // Retorna el camino 
    }
    
    public void insertEdgeWeight(E v, E z, int w) {
        insertEdgeP(v, z, w); // Inserta la arista v -> z con peso
        insertEdgeP(z, v, w); // Inserta la arista z -> v para hacer el grafo no dirigido
    }
    
    public ArrayList<E> shortPath(E v, E z) {
        Vertex<E> start = getVertex(v);
        Vertex<E> end = getVertex(z);

        if (start == null || end == null) return null;

        // Listas para almacenar el camino y los vértices visitados
        ListLinked<Vertex<E>> visited = new ListLinked<>();
        ListLinked<Vertex<E>> predecessors = new ListLinked<>();
        ListLinked<Integer> distances = new ListLinked<>();
        Queue<Vertex<E>> queue = new Queue<>();

        // Inicializar el vértice de inicio
        visited.insertLast(start);
        predecessors.insertLast(null);  // El inicio no tiene predecesor
        distances.insertLast(0);  // La distancia al inicio es 0
        queue.enqueue(start);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.dequeue();
            int currentDistance = distances.get(visited.indexOf(current));

            // Si encontramos el vértice de destino, reconstruimos el camino
            if (current.equals(end)) {
                ArrayList<E> path = new ArrayList<>();
                while (current != null) {
                    path.add(0, current.getData());
                    int index = visited.indexOf(current);
                    current = predecessors.get(index);
                }
                return path;
            }

            // Revisar los vecinos del vértice actual
            current.listAdj.goToStart();
            while (!current.listAdj.isAtEnd()) {
                Edge<E> edge = current.listAdj.getElement();
                Vertex<E> neighbor = edge.getRefDest();

                // Si el vecino no ha sido visitado, lo agregamos
                if (!visited.contains(neighbor)) {
                    visited.insertLast(neighbor);
                    predecessors.insertLast(current);
                    distances.insertLast(currentDistance + edge.getWeight());
                    queue.enqueue(neighbor);
                }

                current.listAdj.next();
            }
        }

        return null;  // Si no se encuentra un camino
    }
    
    public boolean isConexo() {
        if (listVertex.isEmpty()) {
            return true;
        }

        // Tomamos el primer vértice
        Vertex<E> start = listVertex.getFirst().getData();
        ListLinked<Vertex<E>> visited = new ListLinked<>();

        // Usamos el DFS existente para marcar los vértices visitados
        dfsRecursive(start, visited); // dfs es tu método existente

        // Comprobamos si hemos visitado todos los vértices
        return visited.size() == listVertex.size();
    }
    

    public Stack<E> Dijkstra(E v, E w) {
        ArrayList<E> path = shortPath(v, w); // Usamos el método shortPath para obtener el camino
        if (path == null) return null; // Si no hay camino, retornamos null

        Stack<E> stack = new Stack<>();
        for (E vertex : path) {
            stack.push(vertex); // Agregamos los vértices en orden a la pila
        }

        return stack;
    }
    
 // Método para imprimir el camino más corto de Dijkstra en orden correcto
    public void printDijkstraPath(Stack<E> path) {
        // Invertir el stack para obtener el orden correcto
        Stack<E> reversedStack = new Stack<>();
        
        while (!path.isEmpty()) {
            reversedStack.push(path.pop());
        }

        // Imprimir el camino en orden correcto
        while (!reversedStack.isEmpty()) {
            System.out.print(reversedStack.pop() + " ");
        }
    }
    
    // ejercicio 5
    
    public int getNodeDegree(E nodeData) {
        Vertex<E> vertex = getVertex(nodeData); // Obtener el vértice con el dato
        if (vertex != null) {
            return vertex.listAdj.size(); // El tamaño de la lista de adyacencias es el grado del nodo
        }
        return 0; // Si el nodo no existe
    }
    
 // Método para determinar si el grafo es un camino (P)
    public boolean isPath() {
        int count = 0;
        ArrayList<Vertex<E>> vertices = getAllVertices();
        for (Vertex<E> vertex : vertices) {
            // Si un vértice tiene más de 2 aristas, no es un camino
            if (vertex.listAdj.size() > 2) {
                return false;
            }
            // Si tiene exactamente 1 arista, es un extremo del camino
            if (vertex.listAdj.size() == 1) {
                count++;
            }
        }
        // Un camino tiene dos extremos (con 1 arista) y los demás vértices con 2 aristas
        return count == 2;
    }

    // Método para determinar si el grafo es un ciclo (C)
    public boolean isCycle() {
    	ArrayList<Vertex<E>> vertices = getAllVertices();
        for (Vertex<E> vertex : vertices) {
            // Si un vértice tiene más de 2 aristas, no es un ciclo
            if (vertex.listAdj.size() != 2) {
                return false;
            }
        }
        return true;
    }

    // Método para determinar si el grafo es una rueda (W)
    public boolean isWheel() {
        int centerCount = 0;
        int cycleCount = 0;
        ArrayList<Vertex<E>> vertices = getAllVertices();
        for (Vertex<E> vertex : vertices) {
            if (vertex.listAdj.size() == vertices.size() - 1) {
                centerCount++; // El vértice central conecta con todos
            } else if (vertex.listAdj.size() == 2) {
                cycleCount++; // Los demás vértices forman un ciclo
            }
        }
        return centerCount == 1 && cycleCount == vertices.size() - 1;
    }

    // Método para determinar si el grafo es completo (K)
    public boolean isComplete() {
    	ArrayList<Vertex<E>> vertices = getAllVertices();
        for (Vertex<E> vertex : vertices) {
            // Un grafo completo tiene todos los vértices conectados a todos los demás
            if (vertex.listAdj.size() != vertices.size() - 1) {
                return false;
            }
        }
        return true;
    }
    
 // Método para obtener todos los vértices en un ArrayList
    public ArrayList<Vertex<E>> getAllVertices() {
        ArrayList<Vertex<E>> vertices = new ArrayList<>();
        ListLinked<Vertex<E>>.Node<Vertex<E>> current = listVertex.getFirst();
        
        while (current != null) {
            vertices.add(current.getData());
            current = current.getNext();
        }
        return vertices;
    }
    
    //ejercicio 6
    //a) la presentacion formal de un grafo ya fue implemetda a tavez del toString
    //b)
    public String adjacencyList() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Vertex<E>> vertices = getAllVertices(); // Obtener todos los vértices
        
        for (Vertex<E> vertex : vertices) {
            sb.append(vertex.getData()).append(": ");
            
            // Iteramos sobre la lista de adyacencias de este vértice
            vertex.listAdj.goToStart(); // Asegúrate de estar en el inicio
            while (!vertex.listAdj.isAtEnd()) {
                Edge<E> edge = vertex.listAdj.getElement();
                sb.append(edge.getRefDest().getData()).append(" "); // Agregar vértice destino
                vertex.listAdj.next(); // Avanzamos al siguiente vecino
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }

    
    //c)
    public int[][] adjacencyMatrix() {
        int size = listVertex.size();
        int[][] matrix = new int[size][size];
        
        // Inicializamos la matriz en 0s
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                matrix[row][col] = 0;
            }
        }

        ArrayList<Vertex<E>> vertices = getAllVertices(); // Obtener todos los vértices
        
        // Recorrer todos los vértices
        for (int i = 0; i < vertices.size(); i++) {
            Vertex<E> vertex = vertices.get(i);
            
            // Iteramos sobre las aristas del vértice
            vertex.listAdj.goToStart();
            while (!vertex.listAdj.isAtEnd()) {
                Edge<E> edge = vertex.listAdj.getElement();
                int j = indexOf(edge.getRefDest().getData()); // Obtener el índice del vértice destino
                matrix[i][j] = 1;
                matrix[j][i] = 1; // Grafo no dirigido, así que se marca simétrico
                vertex.listAdj.next(); // Avanzamos al siguiente vecino
            }
        }

        return matrix;
    }
    
    private int indexOf(E data) {
        ArrayList<Vertex<E>> vertices = getAllVertices(); // Obtener todos los vértices
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getData().equals(data)) {
                return i;
            }
        }
        return -1; // Si no se encuentra el vértice
    }
    
    //ejrcicio 7 (dirigido)
    public int[] getNodeDegreeDi(E vertexData) {
        Vertex<E> vertex = getVertex(vertexData); // Buscar el vértice
        if (vertex == null) {
            return new int[] {0, 0}; // Si no existe el vértice, retorna 0 para ambos grados
        }

        // Grado de salida: Número de aristas salientes
        int outDegree = 0;
        for (int i = 0; i < vertex.listAdj.size(); i++) {
            outDegree++; // Incrementamos el grado de salida por cada arista
        }

        // Grado de entrada: Contamos cuántos vértices apuntan a este vértice
        int inDegree = 0;
        for (Vertex<E> v : getAllVertices()) {
            for (int i = 0; i < v.listAdj.size(); i++) {
                Edge<E> edge = v.listAdj.get(i);
                if (edge.getRefDest().equals(vertex)) {
                    inDegree++; // Si el vértice es el destino de la arista, sumamos al in-degree
                }
            }
        }

        return new int[] {inDegree, outDegree}; // Regresar los grados de entrada y salida
    }



    
 // Verificar si el grafo es un Camino
    public boolean isPathDi() {
        ArrayList<Vertex<E>> vertices = getAllVertices(); 
        int startCount = 0, endCount = 0;

        for (Vertex<E> vertex : vertices) {
            int[] degrees = getNodeDegreeDi(vertex.getData());
            if (degrees[0] == 1 && degrees[1] == 1) {
                // Nodo intermedio
            } else if (degrees[0] == 0 && degrees[1] == 1) {
                startCount++; // Nodo de inicio
            } else if (degrees[0] == 1 && degrees[1] == 0) {
                endCount++; // Nodo de fin
            } else {
                return false; // Si tiene más de 2 nodos con más aristas, no es un camino
            }
        }

        return (startCount == 1 && endCount == 1); // Solo debe haber 1 nodo de inicio y 1 de fin
    }

    // Verificar si el grafo es un Ciclo
    public boolean isCycleDi() {
        ArrayList<Vertex<E>> vertices = getAllVertices();

        for (Vertex<E> vertex : vertices) {
            int[] degrees = getNodeDegreeDi(vertex.getData());
            // En un ciclo, todos los vértices deben tener grado de entrada y salida igual a 1
            if (degrees[0] != 1 || degrees[1] != 1) {
                return false;
            }
        }

        return true; // Todos los vértices tienen 1 arista entrante y 1 saliente
    }

    // Verificar si el grafo es una Rueda
    public boolean isWheelDi() {
        ArrayList<Vertex<E>> vertices = getAllVertices();
        int centerCount = 0;

        for (Vertex<E> vertex : vertices) {
            int[] degrees = getNodeDegreeDi(vertex.getData());
            // El centro de la rueda tiene n-1 aristas salientes (conectado a todos los demás)
            if (degrees[1] == vertices.size() - 1) {
                centerCount++;
            }
            // Todos los demás vértices deben tener un grado de salida = 1
            if (degrees[1] != 1 && degrees[1] != vertices.size() - 1) {
                return false;
            }
        }

        return (centerCount == 1); // Solo debe haber un vértice central con grado n-1
    }

    // Verificar si el grafo es Completo
    public boolean isCompleteDi() {
        ArrayList<Vertex<E>> vertices = getAllVertices();
        int size = vertices.size();

        for (Vertex<E> vertex : vertices) {
            int[] degrees = getNodeDegreeDi(vertex.getData());
            // En un grafo completo, cada vértice debe tener aristas de entrada y salida hacia todos los demás
            if (degrees[0] != size - 1 || degrees[1] != size - 1) {
                return false;
            }
        }

        return true; // Todos los vértices están conectados entre sí
    }
    
    public int[][] adjacencyMatrixDi() {
        int size = listVertex.size();
        int[][] matrix = new int[size][size];
        
        // Inicializamos la matriz en 0s
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                matrix[row][col] = 0; // Inicializamos en 0 por defecto
            }
        }

        ArrayList<Vertex<E>> vertices = getAllVertices(); // Obtener todos los vértices
        
        // Recorrer todos los vértices
        for (int i = 0; i < vertices.size(); i++) {
            Vertex<E> vertex = vertices.get(i);
            
            // Iteramos sobre las aristas del vértice
            vertex.listAdj.goToStart();
            while (!vertex.listAdj.isAtEnd()) {
                Edge<E> edge = vertex.listAdj.getElement();
                int j = indexOf(edge.getRefDest().getData()); // Obtener el índice del vértice destino
                matrix[i][j] = 1; // Grafo dirigido, se marca 1 en la dirección de la arista
                vertex.listAdj.next(); // Avanzamos al siguiente vecino
            }
        }

        return matrix;
    }
    
    public boolean isIsomorphic(GraphLink<E> otherGraph) {
        if (this.listVertex.size() != otherGraph.listVertex.size()) {
            return false; // Los grafos no pueden ser isomorfos si no tienen el mismo número de vértices
        }

        // Compara listas de adyacencia para verificar isomorfismo
        ListLinked<Vertex<E>> vertices1 = this.listVertex;
        ListLinked<Vertex<E>> vertices2 = otherGraph.listVertex;

        // Si las listas de adyacencia son iguales, se considera isomórfico
        return compareAdjacencyLists(vertices1, vertices2);
    }

    private boolean compareAdjacencyLists(ListLinked<Vertex<E>> list1, ListLinked<Vertex<E>> list2) {
        ListLinked<Vertex<E>>.Node<Vertex<E>> node1 = list1.getFirst();
        ListLinked<Vertex<E>>.Node<Vertex<E>> node2 = list2.getFirst();

        while (node1 != null && node2 != null) {
            // Verificar que los vértices y sus adyacencias sean iguales
            if (!node1.getData().listAdj.equals(node2.getData().listAdj)) { // Corregido aquí
                return false;
            }
            node1 = node1.getNext();
            node2 = node2.getNext();
        }

        return true;
    }

    private GraphLink<E> getComplementGraph() {
        GraphLink<E> complementGraph = new GraphLink<>();

        // Copia los vértices del grafo original al complemento
        for (Vertex<E> vertex : this.getAllVertices()) {
            complementGraph.insertVertex(vertex.getData());
        }

        // Agrega las aristas del complemento (eliminar las existentes y agregar las faltantes)
        for (Vertex<E> vertex : this.getAllVertices()) {
            for (Vertex<E> other : this.getAllVertices()) {
                if (!vertex.equals(other) && !searchEdge(vertex.getData(), other.getData())) {
                    complementGraph.insertEdge(vertex.getData(), other.getData());
                }
            }
        }

        return complementGraph;
    }


}
