package avltree;

import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;
import bstreelinklistinterfgeneric.LinkedBST;

public class AvlTree<E extends Comparable<E>> extends LinkedBST<E> {
	class NodeAVL extends Node {
        protected int bf; // Factor de balance

        public NodeAVL(E data) {
            super(data);
            this.bf = 0;
        }

        @Override
        public String toString() {
            return "Data: " + data + ", Balance Factor: " + bf;
        }
    }

    private boolean height;  //indicador de cambio altura
    
    public void insert(E x) throws ItemDuplicated {
        this.height = false;  // Inicializamos la altura como no cambiada
        this.root = insert(x, (NodeAVL) this.root);
    }

    protected Node insert(E x, NodeAVL node) throws ItemDuplicated {
        NodeAVL fat = node;

        if (node == null) {
            this.height = true;  // Indicamos que la altura ha cambiado
            fat = new NodeAVL(x); // nuevo nodo
        } else {
            int resC = node.data.compareTo(x);
            if (resC == 0) {
                throw new ItemDuplicated(x + " ya se encuentra en el árbol.");
            }

            if (resC < 0) {  // Si x es mayor que node.data, va al subárbol derecho
                fat.right = insert(x, (NodeAVL) node.right);  
                if (this.height) {  // Si la altura del subárbol derecho cambió
                    switch (fat.bf) {
                        case -1:  // El nodo estaba balanceado a la derecha
                            fat.bf = 0;  // Ahora está equilibrado
                            this.height = false;  // No se necesita cambiar más
                            break;
                        case 0:  // El nodo estaba equilibrado
                            fat.bf = 1;  // El nodo ahora está desbalanceado a la izquierda
                            this.height = true;  // El cambio de altura continúa
                            break;
                        case 1:  // El nodo estaba balanceado a la izquierda
                            fat = balanceToLeft(fat);  // Realizamos rotación para balancear
                            this.height = false;  // La altura ya está balanceada
                            break;
                    }
                }
            } else {  // Si x es menor que node.data, va al subárbol izquierdo
                fat.left = insert(x, (NodeAVL) node.left);  

                if (this.height) {  // Si la altura del subárbol izquierdo cambió
                    switch (fat.bf) {
                        case 1:  // El nodo estaba balanceado a la izquierda
                            fat.bf = 0;  // Ahora está equilibrado
                            this.height = false;  // No se necesita cambiar más
                            break;
                        case 0:  // El nodo estaba equilibrado
                            fat.bf = -1;  // El nodo ahora está desbalanceado a la derecha
                            this.height = true;  // El cambio de altura continúa
                            break;
                        case -1:  // El nodo estaba balanceado a la derecha
                            fat = balanceToRight(fat);  // Realizamos rotación para balancear
                            this.height = false;  
                            break;
                    }
                }
            }
        }
        return fat;
    }
    
    private NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.right;  // Hijo derecho de node

        switch (hijo.bf) {
            case 1:  // Derecha-Izquierda: rotación simple a la izquierda
                node.bf = 0; 
                hijo.bf = 0;
                node = rotateSL(node);
                break;

            case -1:  // Derecha-Derecha: rotación doble a la izquierda
                NodeAVL nieto = (NodeAVL) hijo.left;
                switch (nieto.bf) {
                    case 1: node.bf = 0; hijo.bf = 1; break;
                    case 0: node.bf = 0; hijo.bf = 0; break;
                    case -1: node.bf = 1; hijo.bf = 0; break;
                }
                nieto.bf = 0;
                node.right = rotateSR(hijo);
                node = rotateSL(node);
                break;
        }
        return node;  // Nodo balanceado
    }

    
    private NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.left;  // El hijo izquierdo de node

        switch (hijo.bf) {
            case -1:  // Caso 1: Desbalance tipo Izquierda-Derecha (rotación simple a la derecha)
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSR(node);  // Rotación simple a la derecha
                break;

            case 1:  // Caso 2: Desbalance tipo Izquierda-Izquierda (rotación doble a la derecha)
                NodeAVL nieto = (NodeAVL) hijo.right;  // El nieto derecho de hijo
                switch (nieto.bf) {
                    case 1: node.bf = 0; hijo.bf = -1; break;
                    case 0: node.bf = 0; hijo.bf = 0; break;
                    case -1: node.bf = -1; hijo.bf = 0; break;
                }
                nieto.bf = 0;
                node.left = rotateSL(hijo);  // Rotación simple a la izquierda en el hijo
                node = rotateSR(node);  // Rotación simple a la derecha
                break;
        }
        return node;
    }
    
    private NodeAVL rotateSL(NodeAVL node) {
        NodeAVL p = (NodeAVL) node.right;  // Hijo derecho de node
        node.right = p.left;  // El hijo izquierdo de p se convierte en el hijo derecho de node
        p.left = node;  // node se convierte en el hijo izquierdo de p
        node = p;  // p es el nuevo nodo raíz
        return node;  // Devolvemos el nuevo nodo raíz
    }
    
    private NodeAVL rotateSR(NodeAVL node) {
        NodeAVL p = (NodeAVL) node.left;  // Hijo izquierdo de node
        node.left = p.right;  // El hijo derecho de p se convierte en el hijo izquierdo de node
        p.right = node;  // node se convierte en el hijo derecho de p
        node = p;  // p es el nuevo nodo raíz
        return node;  // Devolvemos el nuevo nodo raíz
    }
    
    public void delete(E x) throws ItemNoFound {
        root = delete(x, (NodeAVL) root);
    }

    // Método recursivo para eliminar en AVL
    protected Node delete(E x, NodeAVL node) throws ItemNoFound {
        if (node == null) {
            throw new ItemNoFound(x + " no se encuentra en el árbol.");
        }

        int cmp = x.compareTo(node.data);
        if (cmp < 0) {
            node.left = delete(x, (NodeAVL) node.left);  // Eliminar en el subárbol izquierdo
        } else if (cmp > 0) {
            node.right = delete(x, (NodeAVL) node.right);  // Eliminar en el subárbol derecho
        } else {
            // Caso 1: El nodo tiene cero o un hijo
            if (node.left == null) {
                node = (NodeAVL) node.right;  // Nodo reemplazado por su hijo derecho
            } else if (node.right == null) {
                node = (NodeAVL) node.left;   // Nodo reemplazado por su hijo izquierdo
            } else {
                // Caso 2: El nodo tiene dos hijos
                // Encontrar el sucesor en orden (mínimo en el subárbol derecho)
                NodeAVL minNode = (NodeAVL) findMin(node.right);
                node.data = minNode.data;  // Copiar el valor del sucesor
                node.right = delete(minNode.data, (NodeAVL) node.right);  // Eliminar el sucesor
            }
        }

        // Actualizar el factor de balance y realizar las rotaciones necesarias
        if (node != null) {
            updateBalanceFactor(node);
            
            // Si el árbol está desbalanceado, realizar la rotación correspondiente
            if (node.bf == 2) {
                if (node.left.bf >= 0) {
                    node = rotateSR(node);  // Rotación simple a la derecha
                } else {
                    node = balanceToRight(node);  // Rotación doble a la derecha (izquierda-derecha)
                }
            } else if (node.bf == -2) {
                if (node.right.bf <= 0) {
                    node = rotateSL(node);  // Rotación simple a la izquierda
                } else {
                    node = balanceToLeft(node);  // Rotación doble a la izquierda (derecha-izquierda)
                }
            }
        }

        return node;
    }

    // Método para actualizar el factor de balance de un nodo
    private void updateBalanceFactor(NodeAVL node) {
        int leftHeight = (node.left == null) ? 0 : height(node.left);
        int rightHeight = (node.right == null) ? 0 : height(node.right);
        node.bf = leftHeight - rightHeight;
    }
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    private int height(Node node) {
        return (node == null) ? -1 : ((NodeAVL) node).bf;
    }
    
    // Método para iniciar el recorrido por amplitud
    public void breadthFirstTraversal() {
        int height = treeHeight(root);  // Calculamos la altura del árbol
        for (int i = 0; i <= height; i++) {
        	printLevel(root, i);  // Llamamos a la función recursiva para cada nivel
        }
    }

    // Método recursivo para calcular la altura del árbol
    private int treeHeight(Node node) {
        if (node == null) {
            return -1;  // Si el nodo es nulo, no hay altura
        }
        int leftHeight = treeHeight(node.left);  // Altura del subárbol izquierdo
        int rightHeight = treeHeight(node.right); // Altura del subárbol derecho
        return Math.max(leftHeight, rightHeight) + 1;  // Altura total del árbol
    }
    
    private void printLevel(Node node, int level) {
        if (node == null) {
            return;  // Si el nodo es nulo, no hacemos nada
        }

        if (level == 0) {
            System.out.print(node.data + " ");  // Imprimir el nodo en el nivel 0
        } else {
            printLevel(node.left, level - 1);  // Recursión para el subárbol izquierdo
            printLevel(node.right, level - 1);  // Recursión para el subárbol derecho
        }
    }
    
    public void preOrderTraversal() {
        preOrderTraversal(root);  // Llamamos a la función recursiva pasando la raíz
    }

    // Método recursivo para recorrer en preorden
    private void preOrderTraversal(Node node) {
        if (node == null) {
            return;  // Si el nodo es nulo, no hacemos nada
        }
        
        // Visitar el nodo raíz
        System.out.print(node.data + " ");  
        
        // Recursión para el subárbol izquierdo
        preOrderTraversal(node.left);  
        
        // Recursión para el subárbol derecho
        preOrderTraversal(node.right);  
    }

}
