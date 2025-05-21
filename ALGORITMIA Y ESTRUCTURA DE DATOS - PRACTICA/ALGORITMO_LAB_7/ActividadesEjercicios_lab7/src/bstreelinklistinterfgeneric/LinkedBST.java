package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

import Exceptions.*;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

	// Nodo interno para el árbol binario
	public class Node {
		public E data;
		public Node left, right;

		public Node(E data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
		
		public Node(E data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	private Node root;

	public LinkedBST() {
		this.root = null;
	}


	@Override
	public void insert(E data) throws ItemDuplicated {
		root = insertRecursive(root, data);
	}

	private Node insertRecursive(Node node, E data) throws ItemDuplicated {
	    if (node == null) {
	        return new Node(data); // Insertamos el nuevo nodo aquí
	    }

	    int cmp = data.compareTo(node.data); // Comparamos el valor con el nodo actual

	    if (cmp < 0) {
	        node.left = insertRecursive(node.left, data); // Insertamos en el subárbol izquierdo
	    } else if (cmp > 0) {
	        node.right = insertRecursive(node.right, data); // Insertamos en el subárbol derecho
	    } else {
	        throw new ItemDuplicated("El ítem " + data + " ya existe."); // Si ya existe, lanzamos la excepción
	    }

	    return node;
	}

	@Override
	public E search(E data) throws ItemNoFound {
		return searchRecursive(root, data);
	}

	private E searchRecursive(Node node, E data) throws ItemNoFound {
	    if (node == null) {
	        throw new ItemNoFound("El ítem " + data + " no fue encontrado.");
	    }

	    int cmp = data.compareTo(node.data);

	    if (cmp < 0) {
	        return searchRecursive(node.left, data); // Buscar en el subárbol izquierdo
	    } else if (cmp > 0) {
	        return searchRecursive(node.right, data); // Buscar en el subárbol derecho
	    } else {
	        return node.data; // Si encontramos el nodo, lo devolvemos
	    }
	}

	@Override
	public void delete(E data) throws ExceptionIsEmpty {
	    // Si el árbol está vacío, lanza una excepción
	    if (isEmpty()) {
	        throw new ExceptionIsEmpty("El árbol está vacío.");
	    }
	    root = deleteRecursive(root, data);
	}

	// Método recursivo para eliminar un nodo en el árbol binario
	private Node deleteRecursive(Node node, E data) throws ExceptionIsEmpty {
	    if (node == null) {
	        // Si el nodo es nulo, lanza la excepción
	        throw new ExceptionIsEmpty("El ítem " + data + " no fue encontrado.");
	    }

	    // Compara el dato con el nodo actual
	    int cmp = data.compareTo(node.data);

	    if (cmp < 0) {
	        // Si el dato es menor, busca en el subárbol izquierdo
	        node.left = deleteRecursive(node.left, data);
	    } else if (cmp > 0) {
	        // Si el dato es mayor, busca en el subárbol derecho
	        node.right = deleteRecursive(node.right, data);
	    } else {
	        // Si encuentra el nodo a eliminar
	        // si el nodo tiene un solo hijo o no tiene hijos
	        if (node.left == null) {
	            return node.right; // Devuelve el hijo derecho
	        } else if (node.right == null) {
	            return node.left; // Devuelve el hijo izquierdo 
	        }

	        // si el nodo tiene dos hijos
	        node.data = minValue(node.right);
	        // Elimina el valor mínimo en el subárbol derecho
	        node.right = deleteRecursive(node.right, node.data);
	    }

	    return node;
	}


	private E minValue(Node node) {
	    E minValue = node.data;
	    while (node.left != null) {
	        minValue = node.left.data;
	        node = node.left;
	    }
	    return minValue;
	}
	
	@Override
	public boolean isEmpty() {
		return root == null;
	}
	
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    buildString(root, sb, "", true);
	    return sb.toString();
	}

	private void buildString(Node node, StringBuilder sb, String indent, boolean last) {
	    if (node != null) {
	        sb.append(indent);  // Imprimir la indentación actual
	        if (last) {
	            sb.append("└── ");  // Si es el último nodo en el nivel, usamos una línea "└──"
	            indent += "    ";   // La indentación para los nodos hijos se aumenta
	        } else {
	            sb.append("├── ");  // Si no es el último nodo, usamos "├──"
	            indent += "|   ";   // La indentación para los nodos hijos se mantiene
	        }
	        sb.append(node.data).append("\n");  // Imprimimos el dato del nodo

	        // Recursivamente imprimimos los hijos (izquierdo y derecho)
	        buildString(node.left, sb, indent, false);
	        buildString(node.right, sb, indent, true);
	    }
	}
	
	// Método público que realiza el recorrido en orden y retorna los elementos como una lista
	public String inOrder() {
	    StringBuilder sb = new StringBuilder();
	    // Llama al método recursivo para hacer el recorrido en orden
	    inOrderTraversal(root, sb);
	    return sb.toString(); // Devuelve los elementos del árbol en orden
	}

	// Método privado recursivo que realiza el recorrido en orden
	private void inOrderTraversal(Node node, StringBuilder sb) {
	    if (node != null) {
	        // Primero recorre el subárbol izquierdo
	        inOrderTraversal(node.left, sb);
	        
	        // Luego visita el nodo actual (procesa su dato)
	        sb.append(node.data).append(" ");
	        
	        // recorre el subárbol derecho
	        inOrderTraversal(node.right, sb);
	    }
	} 

    
    public String preOrder() {
        StringBuilder sb = new StringBuilder();
        preOrderTraversal(root, sb);
        return sb.toString();
    }

    // Método privado recursivo que realiza el recorrido en preorden
    private void preOrderTraversal(Node node, StringBuilder sb) {
        if (node != null) {
            // Primero visita el nodo actual (procesamos su dato)
            sb.append(node.data).append(" ");
            
            // Luego recorre el subárbol izquierdo
            preOrderTraversal(node.left, sb);
            
            // Finalmente, recorre el subárbol derecho
            preOrderTraversal(node.right, sb);
        }
    }
    
 // Método público que realiza el recorrido en postorden y retorna los elementos como una lista
    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        postOrderTraversal(root, sb);
        return sb.toString();
    }

    private void postOrderTraversal(Node node, StringBuilder sb) {
        if (node != null) {
            // Primero recorre el subárbol izquierdo
            postOrderTraversal(node.left, sb);

            // Luego recorre el subárbol derecho
            postOrderTraversal(node.right, sb);

            // Finalmente, procesa el nodo actual 
            sb.append(node.data).append(" ");
        }
    }
    
 // Método público para encontrar el nodo con el valor mínimo en el subárbol con raíz node
    public E findMin() throws ItemNoFound {
        return findMinNode(root); // Llamamos al método privado con el nodo raíz
    }
    
 // Método privado para encontrar el nodo mínimo
    private E findMinNode(Node node) throws ItemNoFound {
        if (node == null) {
            throw new ItemNoFound("El árbol está vacío.");
        }

        Node actual = node;
        while (actual.left != null) {
        	actual = actual.left; // Ir al subárbol izquierdo hasta encontrar el mínimo
        }
        return actual.data; // Retornar el valor del nodo mínimo
    }
    
    
    

    // Método público para encontrar el nodo con el valor máximo en el subárbol con raíz node
    public E findMax() throws ItemNoFound {
        return findMaxNode(root); // Llama al método privado con el nodo raíz
    }

    // Método privado para encontrar el nodo máximo
    private E findMaxNode(Node node) throws ItemNoFound {
        if (node == null) {
            throw new ItemNoFound("El árbol está vacío.");
        }

        Node actual = node;
        while (actual.right != null) {
        	actual = actual.right; // Ir al subárbol derecho hasta encontrar el máximo
        }
        return actual.data; // Retorna el valor del nodo máximo
    }
    
    public void destroyNodes() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El árbol está vacío.");
        }
        root = null; // Eliminar todos los nodos
    }
    
    public int countAllNodes() {
        return countAllNodesRecursive(root);
    }

    // Método recursivo que cuenta los nodos
    private int countAllNodesRecursive(Node node) {
        if (node == null) {
            return 0; // Si el nodo es nulo, no hay nodos que contar
        }
        // Contamos el nodo actual y sumamos los nodos del subárbol izquierdo y derecho
        return 1 + countAllNodesRecursive(node.left) + countAllNodesRecursive(node.right);
    }
    
    public int countNodes() {
        return countNodesRecursive(root);
    }

    private int countNodesRecursive(Node node) {
        if (node == null) {
            return 0;
        }

        int count = 0;

        // Si el nodo tiene al menos un hijo, es un nodo no-hoja
        if (node.left != null || node.right != null) {
            count = 1;  // Este es un nodo no-hoja
        }

        // Recursivamente contamos los nodos no-hojas en los subárboles izquierdo y derecho
        count += countNodesRecursive(node.left);
        count += countNodesRecursive(node.right);

        return count;
    }
    
    public int height(E x) {
        Node node = searchNode(root, x); // Buscar el nodo con el valor 'x'
        if (node == null) {
            return -1; // Si no encontramos el nodo, retornamos -1
        }
        
        // Usamos BFS para calcular la altura del subárbol desde 'node'
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        int height = -1;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;
            
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        return height;
    }

    // Método auxiliar para buscar el nodo
    private Node searchNode(Node root, E data) {
        if (root == null) return null;
        int cmp = data.compareTo(root.data);
        if (cmp < 0) {
            return searchNode(root.left, data);
        } else if (cmp > 0) {
            return searchNode(root.right, data);
        } else {
            return root; // Nodo encontrado
        }
    }
    
    public int amplitude(int level) {
        if (root == null) {
            return 0; // Si el árbol está vacío, retornamos 0
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int currentLevel = 0;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            if (currentLevel == level) {
                return levelSize; // Retornamos la amplitud del nivel especificado
            }
            
            // Procesamos el nivel actual
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            currentLevel++;
        }
        return 0; // Si el nivel no existe, retornamos 0
    }
    
    
    public int areaBST() {
        if (root == null) {
            return 0; // Si el árbol está vacío, el área es 0
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int leafCount = 0;
        int height = -1;

        // Recorrido por niveles (BFS) para contar hojas y calcular la altura
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++; // Incrementamos la altura por cada nivel

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();

                // Si el nodo no tiene hijos, es una hoja
                if (current.left == null && current.right == null) {
                    leafCount++;
                }

                // Añadimos los hijos del nodo actual a la cola
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        
        // El área es el número de hojas multiplicado por la altura del árbol
        return leafCount * height;
    }
    
    public void drawBST() {
        // Llamamos al método toString() para representar el árbol como un texto
        System.out.println(toString());
    }

    public void parenthesize() {
        parenthesizeRecursive(root, 0);
    }

    private void parenthesizeRecursive(Node node, int indent) {
        if (node == null) {
            return; // Si el nodo es nulo, no hacemos nada
        }
        
        // Imprimir indentación
        for (int i = 0; i < indent; i++) {
            System.out.print("    "); // Espacios para la indentación
        }
        
        // Imprimir el nodo actual entre paréntesis
        System.out.print("(" + node.data);
        
        // Si el nodo tiene hijos, los procesamos
        if (node.left != null || node.right != null) {
            System.out.println();
            
            // Llamar recursivamente para el hijo izquierdo
            if (node.left != null) {
                parenthesizeRecursive(node.left, indent + 1);
            }
            
            // Llamar recursivamente para el hijo derecho
            if (node.right != null) {
                parenthesizeRecursive(node.right, indent + 1);
            }
            
            // Finalizamos el paréntesis
            for (int i = 0; i < indent; i++) {
                System.out.print("    ");
            }
        }
        
        // Cerrar el paréntesis en la misma línea si no tiene hijos
        System.out.print(")");
    }

}