package Ejercicio7;

import java.util.Arrays;

public class OrdenacionPorFusionMejorado {

    private static final int UMBRAL = 10;  // Constante, O(1)

    public static void ordenarPorFusion(int[] arreglo) {  // O(n log n) en el peor caso
        if (arreglo.length < 2) {  // O(1)
            return;  // O(1)
        }
        int[] temp = new int[arreglo.length];  // O(n)
        ordenarPorFusionRecursivo(arreglo, temp, 0, arreglo.length - 1);  // O(n log n) en el peor caso
    }

    private static void ordenarPorFusionRecursivo(int[] arreglo, int[] temp, int inicio, int fin) {  // O(n log n) en el peor caso
        if (fin - inicio <= UMBRAL) {  // O(1)
            insertionSort(arreglo, inicio, fin);  // O(n^2) en el peor caso
        } else {
            int mitad = (inicio + fin) / 2;  // O(1)
            ordenarPorFusionRecursivo(arreglo, temp, inicio, mitad);  // O(n log n) en el peor caso
            ordenarPorFusionRecursivo(arreglo, temp, mitad + 1, fin);  // O(n log n) en el peor caso
            fusionar(arreglo, temp, inicio, mitad, fin);  // O(n)
        }
    }

    private static void fusionar(int[] arreglo, int[] temp, int inicio, int mitad, int fin) {  // O(n)
        for (int i = inicio; i <= fin; i++) {  // O(n)
            temp[i] = arreglo[i];  // O(1) por cada iteración
        }

        int i = inicio, j = mitad + 1, k = inicio;  // O(1)

        while (i <= mitad && j <= fin) {  // O(n) en el peor caso
            if (temp[i] <= temp[j]) {  // O(1)
                arreglo[k++] = temp[i++];  // O(1)
            } else {
                arreglo[k++] = temp[j++];  // O(1)
            }
        }

        while (i <= mitad) {  // O(n)
            arreglo[k++] = temp[i++];  // O(1)
        }

        while (j <= fin) {  // O(n)
            arreglo[k++] = temp[j++];  // O(1)
        }
    }

    private static void insertionSort(int[] arreglo, int inicio, int fin) {  // O(n^2) en el peor caso
        for (int i = inicio + 1; i <= fin; i++) {  // O(n)
            int clave = arreglo[i];  // O(1)
            int j = i - 1;  // O(1)
            while (j >= inicio && arreglo[j] > clave) {  // O(n) en el peor caso
                arreglo[j + 1] = arreglo[j];  // O(1)
                j--;  // O(1)
            }
            arreglo[j + 1] = clave;  // O(1)
        }
    }

    public static void main(String[] args) {  
        int[] arreglo = {1, 3, 5, 4, 2, 12, 10};  
        System.out.println("Arreglo sin ordenar: " + Arrays.toString(arreglo));  

        ordenarPorFusion(arreglo);  

        System.out.println("Arreglo ordenado: " + Arrays.toString(arreglo));  
    }
}
