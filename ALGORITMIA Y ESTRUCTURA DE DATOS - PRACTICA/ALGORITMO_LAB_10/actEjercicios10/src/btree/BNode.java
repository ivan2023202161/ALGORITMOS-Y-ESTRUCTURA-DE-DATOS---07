package btree;

import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {
    protected ArrayList<E> keys;               // Lista de claves almacenadas en el nodo
    protected ArrayList<BNode<E>> childs;      // Lista de hijos del nodo
    protected int count;                       // Cantidad actual de claves en el nodo
    protected int idNode;                      // ID único para identificar el nodo

    private static int idCounter = 0;          // Contador estático para asignar IDs únicos

    public BNode(int n) {
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n + 1);
        this.count = 0;
        this.idNode = idCounter++;             // Asigna un ID único incrementando el contador

        // Inicializa las claves con 'null' para reservar espacio
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
        }

        // Inicializa los hijos con 'null' para reservar espacio
        for (int i = 0; i <= n; i++) {
            this.childs.add(null);
        }
    }

    // Verifica si el nodo está lleno según el número máximo de claves permitido
    public boolean nodeFull(int maxKeys) {
        return count == maxKeys;
    }

    // Verifica si el nodo está vacío (sin claves)
    public boolean nodeEmpty() {
        return count == 0;
    }

    // Busca una clave en el nodo y retorna su posición mediante el array pos[0]
    public boolean searchNode(E key, int[] pos) {
        int i = 0;
        while (i < count && key.compareTo(keys.get(i)) > 0) {
            i++;
        }
        pos[0] = i;
        return (i < count && key.compareTo(keys.get(i)) == 0);
    }

    // Representación del nodo como String, mostrando su ID y claves
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nodo ID ").append(idNode).append(": [ ");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));
            if (i < count - 1) {
                sb.append(", ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }
}

