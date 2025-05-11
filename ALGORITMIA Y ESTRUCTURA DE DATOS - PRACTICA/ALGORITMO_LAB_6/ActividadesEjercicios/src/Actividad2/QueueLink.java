package Actividad2;
import Actividad1.ExceptionIsEmpty;

public class QueueLink<E> implements Queue<E> {
	private Node<E> first;
    private Node<E> last;

    public QueueLink() {
        this.first = null;
        this.last = null;
    }

	@Override
	public void enqueue(E x) {
		Node<E> aux = new Node<>(x);
        if (isEmpty()) {
            this.first = aux;
        } else {
            this.last.setNext(aux);
        }
        this.last = aux;
    }

	@Override
	public E dequeue() throws ExceptionIsEmpty {
		if (isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacía");
        }
        E data = first.getData();
        first = first.getNext();
        if (first == null) {
            last = null;
        }
        return data;
    }

	@Override
	public E front() throws ExceptionIsEmpty {
		if (isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacía");
        }
        return first.getData();
    }

	@Override
	public E back() throws ExceptionIsEmpty {
		if (isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacía");
        }
        return last.getData();
    }

	@Override
	public boolean isEmpty() {
		return first==null;
	}
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Queue: ");
        Node<E> current = first;
        while (current != null) {
            sb.append(current.getData()).append(" ");
            current = current.getNext();
        }
        return sb.toString().trim();
    }
}
