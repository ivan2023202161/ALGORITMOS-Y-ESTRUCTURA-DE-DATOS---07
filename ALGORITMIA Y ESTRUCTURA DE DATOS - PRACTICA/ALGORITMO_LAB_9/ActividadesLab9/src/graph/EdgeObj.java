package graph;

public class EdgeObj<V, E> {
    protected E info;  // Información de la arista (peso o alguna propiedad)
    protected VertexObj<V, E> endVertex1;
    protected VertexObj<V, E> endVertex2;
    protected int position;

    public EdgeObj(VertexObj<V, E> vert1, VertexObj<V, E> vert2, E info, int position) {
        this.endVertex1 = vert1;
        this.endVertex2 = vert2;
        this.info = info;
        this.position = position;
    }

    // Método para comparar dos aristas. Compara los vértices y la información
    @Override
    public boolean equals(Object o) {
        if (o instanceof EdgeObj) {
            EdgeObj<?, ?> otherEdge = (EdgeObj<?, ?>) o;
            return (this.endVertex1.equals(otherEdge.endVertex1) && 
                    this.endVertex2.equals(otherEdge.endVertex2)) ||
                   (this.endVertex1.equals(otherEdge.endVertex2) && 
                    this.endVertex2.equals(otherEdge.endVertex1));  // Grafo no dirigido
        }
        return false;
    }

    // Método para representar la arista como una cadena
    @Override
    public String toString() {
        return "(" + endVertex1.info + " - " + endVertex2.info + ", " + info + ")";
    }

    // Getters
    public VertexObj<V, E> getEndVertex1() {
        return endVertex1;
    }

    public VertexObj<V, E> getEndVertex2() {
        return endVertex2;
    }

    public E getInfo() {
        return info;
    }
}
