package Actividad2;
import Actividad1.ExceptionIsEmpty;  // Importa la excepción personalizada de la Actividad 1

public interface Queue<E> {
	void enqueue(E x);
	E dequeue() throws ExceptionIsEmpty;
	E front() throws ExceptionIsEmpty;
	E back() throws ExceptionIsEmpty;
	boolean isEmpty();
} 