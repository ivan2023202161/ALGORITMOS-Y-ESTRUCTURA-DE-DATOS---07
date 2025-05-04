package ejercicio;

import java.util.Scanner;


public class ListaEnlazada {
	public static <T> boolean buscarElemento(Node<T> cabeza, T valor) {
        Node<T> actual = cabeza;
        while (actual != null) {
            if (actual.valor.equals(valor)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    
    public static <T> Node<T> invertirLista(Node<T> cabeza) {
        Node<T> nodoAnterior = null;
        Node<T> nodoActual = cabeza;
        Node<T> nodoSiguiente;

        while (nodoActual != null) {
            nodoSiguiente = nodoActual.siguiente;
            nodoActual.siguiente = nodoAnterior;
            nodoAnterior = nodoActual;
            nodoActual = nodoSiguiente;
        }

        return nodoAnterior;
    }
    
    public static <T> Node<T> insertarAlFinal(Node<T> cabeza, T valor) {
        Node<T> nueva = new Node<>(valor); 
        if (cabeza == null) {
            cabeza = nueva; // Si la lista está vacía, la cabeza es el nuevo nodo
        } else {
            Node<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nueva; // Une el último nodo con el nuevo nodo
        }
        return cabeza; // Retorna la cabeza de la lista (actualizada si se insertó un nodo)
    }

    
    public static <T> int contarNodos(Node<T> cabeza) {
        int contador = 0;
        Node<T> actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }
    
    public static <T> boolean sonIguales(Node<T> cabeza1, Node<T> cabeza2) {
        Node<T> actual1 = cabeza1;
        Node<T> actual2 = cabeza2;

        while (actual1 != null && actual2 != null) {
            if (!actual1.valor.equals(actual2.valor)) {
                return false;  // Si los valores no son iguales, las listas no son iguales
            }
            actual1 = actual1.siguiente;
            actual2 = actual2.siguiente;
        }

        // Si ambas listas llegaron al final al mismo tiempo, son iguales
        return actual1 == null && actual2 == null;
    }
    
    public static <T> Node<T> concatenarListas(Node<T> cabeza1, Node<T> cabeza2) {
        if (cabeza1 == null) return cabeza2;  // Si la primera lista esta vacia, devuelve la segunda
        if (cabeza2 == null) return cabeza1;  // Si la segunda lista esta vacia, devuelve la primera

        Node<T> actual = cabeza1;
        while (actual.siguiente != null) {
            actual = actual.siguiente;  // Vamos al último nodo de la primera lista
        }
        actual.siguiente = cabeza2;  // Unimos el último nodo de la primera lista con la cabeza de la segunda
        return cabeza1;
    }

    public static <T> void imprimirLista(Node<T> cabeza) {
        Node<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.valor + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("vacia");
    }


     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicializamos las dos listas
        Node<Integer> lista1 = null;
        Node<Integer> lista2 = null;

        int opcion;
        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Insertar al final de Lista 1");
            System.out.println("2. Insertar al final de Lista 2");
            System.out.println("3. Buscar elemento en Lista 1");
            System.out.println("4. Buscar elemento en Lista 2");
            System.out.println("5. Invertir Lista 1");
            System.out.println("6. Invertir Lista 2");
            System.out.println("7. Contar nodos en Lista 1");
            System.out.println("8. Contar nodos en Lista 2");
            System.out.println("9. Verificar si Lista 1 y Lista 2 son iguales");
            System.out.println("10. Concatenar Lista 1 y Lista 2");
            System.out.println("11. Imprimir Lista 1");
            System.out.println("12. Imprimir Lista 2");
            System.out.println("13. Salir");

            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el valor para insertar en Lista 1: ");
                    int valor1 = scanner.nextInt();
                    lista1 = insertarAlFinal(lista1, valor1);
                    break;
                case 2:
                    System.out.print("Ingrese el valor para insertar en Lista 2: ");
                    int valor2 = scanner.nextInt();
                    lista2 = insertarAlFinal(lista2, valor2);
                    break;
                case 3:
                    System.out.print("Ingrese el valor a buscar en Lista 1: ");
                    int buscar1 = scanner.nextInt();
                    if (buscarElemento(lista1, buscar1)) {
                        System.out.println("El valor " + buscar1 + " se encuentra en Lista 1.");
                    } else {
                        System.out.println("El valor " + buscar1 + " no se encuentra en Lista 1.");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el valor a buscar en Lista 2: ");
                    int buscar2 = scanner.nextInt();
                    if (buscarElemento(lista2, buscar2)) {
                        System.out.println("El valor " + buscar2 + " se encuentra en Lista 2.");
                    } else {
                        System.out.println("El valor " + buscar2 + " no se encuentra en Lista 2.");
                    }
                    break;
                case 5:
                    lista1 = invertirLista(lista1);
                    System.out.println("Lista 1 invertida.");
                    break;
                case 6:
                    lista2 = invertirLista(lista2);
                    System.out.println("Lista 2 invertida.");
                    break;
                case 7:
                    System.out.println("Cantidad de nodos en Lista 1: " + contarNodos(lista1));
                    break;
                case 8:
                    System.out.println("Cantidad de nodos en Lista 2: " + contarNodos(lista2));
                    break;
                case 9:
                    if (sonIguales(lista1, lista2)) {
                        System.out.println("Las listas son iguales.");
                    } else {
                        System.out.println("Las listas no son iguales.");
                    }
                    break;
                case 10:
                    lista1 = concatenarListas(lista1, lista2);
                    System.out.println("Listas concatenadas.");
                    break;
                case 11:
                    System.out.println("Lista 1: ");
                    imprimirLista(lista1);
                    break;
                case 12:
                    System.out.println("Lista 2: ");
                    imprimirLista(lista2);
                    break;
                case 13:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 13);

        scanner.close();
    }
}