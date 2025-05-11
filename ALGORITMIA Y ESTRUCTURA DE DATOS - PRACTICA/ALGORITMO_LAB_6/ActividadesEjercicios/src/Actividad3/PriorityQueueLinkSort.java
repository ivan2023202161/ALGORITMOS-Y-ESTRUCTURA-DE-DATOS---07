package Actividad3;
import Actividad1.ExceptionIsEmpty;
import Actividad2.Node;

public class PriorityQueueLinkSort<E,N extends Comparable<N>> implements PriorityQueue<E,N>{
	
	class EntryNode{
		E data;
		N priority;
		EntryNode(E data, N priority){
			this.data = data;
			this.priority = priority;
		}
		public String toString() {
            return "(" + data + ", prio: " + priority + ")";
        }
    }

    private Node<EntryNode> first;
    private Node<EntryNode> last;

    public PriorityQueueLinkSort() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void enqueue(E x, N pr) {
        EntryNode newEntry = new EntryNode(x, pr);
        Node<EntryNode> newNode = new Node<>(newEntry);

        if (isEmpty()) {
            first = last = newNode;
        } else if (pr.compareTo(first.getData().priority) > 0) {
            // Insertar al inicio
            newNode.setNext(first);
            first = newNode;
        } else {
            Node<EntryNode> current = first;
            Node<EntryNode> prev = null;

            while (current != null && pr.compareTo(current.getData().priority) <= 0) {
                prev = current;
                current = current.getNext();
            }

            newNode.setNext(current);
            if (prev != null) {
                prev.setNext(newNode);
            }

            if (current == null) {
                last = newNode;
            }
        }
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cannot remove from an empty queue");
        }
        E aux = first.getData().data;
        first = first.getNext();
        if (first == null) {
            last = null;
        }
        return aux;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Queue is empty");
        }
        return first.getData().data;
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Queue is empty");
        }
        return last.getData().data;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PriorityQueue: ");
        Node<EntryNode> current = first;
        while (current != null) {
            sb.append(current.getData().toString()).append(" ");
            current = current.getNext();
        }
        return sb.toString().trim();
    }
}

