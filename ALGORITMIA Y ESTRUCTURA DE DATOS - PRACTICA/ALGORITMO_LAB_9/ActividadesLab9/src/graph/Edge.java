package graph;

public class Edge<E> {
    private Vertex<E> refDest; // Referencia al vértice destino de la arista
    private int weight; // Peso de la arista, -1 indica que no tiene peso definido

    // Constructor sin peso
    public Edge(Vertex<E> refDest) {
        this(refDest, -1);
    }

    // Constructor con peso
    public Edge(Vertex<E> refDest, int weight) {
        this.refDest = refDest;
        this.weight = weight;
    }

    // Método para comparar si dos aristas son iguales basándose en el vértice destino
    public boolean equals(Object o) {
        if (o instanceof Edge<?>) {
            Edge<E> e = (Edge<E>) o;
            return this.refDest.equals(e.refDest); // Compara el vértice destino
        }
        return false;
    }

    // Devuelve la referencia al vértice destino de la arista
    public Vertex<E> getRefDest() {
        return refDest;
    }

    // Representación en texto de la arista con o sin peso
    public String toString() {
        if (this.weight > -1)
            return refDest.getData() + " [" + this.weight + "], "; // Con peso
        else
            return refDest.getData() + ", "; // Sin peso
    }

	public int getWeight() {
		return weight;
	}
}
