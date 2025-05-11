package Ejercicio2;

import Actividad1.ExceptionIsEmpty;
import Actividad2.Queue;

public class QueueArray<E> implements Queue<E> {

    private E[] array;
    private int first;
    private int last;
    private int tamano;
    private int capacidad;

    @SuppressWarnings("unchecked")
    public QueueArray(int capacity) {
        this.capacidad = capacity;
        this.array = (E[]) new Object[capacity];
        this.first = 0;
        this.last = 0;
        this.tamano = 0;
    }

    @Override
    public void enqueue(E x) {
        if (tamano == capacidad) {
        	System.out.println("Cola llena");
        }else {
        array[last] = x;
        last = (last + 1) % capacidad;
        tamano++;
        }
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacía");
        }
        E value = array[first];
        first = (first + 1) % capacidad;
        tamano--;
        return value;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacía");
        }
        return array[first];
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacía");
        }
        int backIndex = (last - 1 + capacidad) % capacidad;
        return array[backIndex];
    }

    @Override
    public boolean isEmpty() {
        return tamano == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Queue: ");
        for (int i = 0; i < tamano; i++) {
            sb.append(array[(first + i) % capacidad]).append(" ");
        }
        return sb.toString().trim();
    }
}
