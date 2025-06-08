package graph;

public class VertexObj<V, E> {
    protected V info;  // Información del vértice
    protected int position;

    public VertexObj(V info, int position) {
        this.info = info;
        this.position = position;
    }

    // Método para comparar dos vértices
    @Override
    public boolean equals(Object o) {
        if (o instanceof VertexObj) {
            VertexObj<?, ?> otherVertex = (VertexObj<?, ?>) o;
            return this.info.equals(otherVertex.info); // Compara la info del vértice
        }
        return false;
    }

    // Método para representar el vértice como una cadena
    @Override
    public String toString() {
        return info.toString();
    }

    // Getters
    public V getInfo() {
        return info;
    }

    public int getPosition() {
        return position;
    }
}
