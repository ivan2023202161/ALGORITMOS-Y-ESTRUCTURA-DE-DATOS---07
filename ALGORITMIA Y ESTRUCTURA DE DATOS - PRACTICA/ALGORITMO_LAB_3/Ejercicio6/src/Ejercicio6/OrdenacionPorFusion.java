package Ejercicio6;

import java.util.Arrays;

public class OrdenacionPorFusion {

    public static void ordenarPorFusion(int[] arreglo) {  // O(n log n)
        if (arreglo.length < 2) {  // O(1)
            return;  // O(1)
        }

        int mitad = arreglo.length / 2;  // O(1)
        int[] izquierdo = Arrays.copyOfRange(arreglo, 0, mitad);  // O(n)
        int[] derecho = Arrays.copyOfRange(arreglo, mitad, arreglo.length);  // O(n)

        ordenarPorFusion(izquierdo);  // O(n log n)
        ordenarPorFusion(derecho);  // O(n log n)

        fusionar(arreglo, izquierdo, derecho);  // O(n)
    }

    public static void fusionar(int[] arreglo, int[] izquierdo, int[] derecho) {  // O(n)
        int i = 0, j = 0, k = 0;  // O(1)

        while (i < izquierdo.length && j < derecho.length) {  // O(n)
            if (izquierdo[i] <= derecho[j]) {  // O(1)
                arreglo[k++] = izquierdo[i++];  // O(1)
            } else {
                arreglo[k++] = derecho[j++];  // O(1)
            }
        }

        while (i < izquierdo.length) {  // O(n)
            arreglo[k++] = izquierdo[i++];  // O(1)
        }

        while (j < derecho.length) {  // O(n)
            arreglo[k++] = derecho[j++];  // O(1)
        }
    }

    public static void main(String[] args) {  
        int[] arreglo = {1, 3, 5, 4, 2, 12, 10};  
        System.out.println("Arreglo sin ordenar: " + Arrays.toString(arreglo));  

        ordenarPorFusion(arreglo);  

        System.out.println("Arreglo ordenado: " + Arrays.toString(arreglo));  
    }
}
