package ListLinked;

public class ListLinked<T> {
    private Node<T> head;
    private int size;

    // Constructor
    public ListLinked() {
        this.head = null;
        this.size = 0;
    }

    // Clase interna de nodo
    public class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }
    }
    private Node<T> current;

    // Agrega un elemento al final de la lista 
    public void insertLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Verifica si un valor existe en la lista
    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Elimina el primer nodo con el valor especificado
    public boolean remove(T data) {
        if (head == null) return false;

        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }

        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    // Retorna el primer nodo (para recorrer la lista desde afuera)
    public Node<T> getFirst() {
        return head;
    }

    // Retorna el tamaño de la lista
    public int size() {
        return size;
    }

    // Representación en texto de la lista
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data.toString());
            current = current.next;
        }
        return sb.toString();
    }

    // Mostrar elementos (método de depuración opcional)
    public void display() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    
 // Mueve el cursor al inicio de la lista
    public void goToStart() {
        current = head;
    }

    // Verifica si el cursor está al final (no hay más elementos)
    public boolean isAtEnd() {
        return current == null;
    }

    // Obtiene el elemento actual en la posición del cursor
    public T getElement() {
        if (current != null)
            return current.data;
        return null;
    }

    // Avanza el cursor al siguiente nodo
    public void next() {
        if (current != null)
            current = current.next;
    }
    
    public int indexOf(T data) {
        Node<T> current = getFirst();
        int index = 0;

        while (current != null) {
            if (current.getData().equals(data)) {
                return index;
            }
            current = current.getNext();
            index++;
        }

        return -1; // No encontrado
    }
    
    public T get(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("Índice invalido: " + index);

        Node<T> current = getFirst();
        int i = 0;

        while (current != null) {
            if (i == index) {
                return current.getData();
            }
            current = current.getNext();
            i++;
        }

        throw new IndexOutOfBoundsException("Índice fuera de los límites de la lista.");
    }

	public boolean isEmpty() {
		return head == null;
	}

}

