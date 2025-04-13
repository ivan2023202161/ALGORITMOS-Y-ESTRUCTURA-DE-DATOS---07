package Ejercicio2;

import java.util.Random;

public class QuickSelect {

    // Método principal para obtener el k-ésimo menor elemento
    public static int quickSelect(int[] arr, int k) {
        return quickSelectHelper(arr, 0, arr.length - 1, k - 1); // k-1 porque los índices empiezan en 0
    }

    private static int quickSelectHelper(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left]; // Caso base: el array tiene un solo elemento
        }

        int pivotIndex = partition(arr, left, right); // Dividir el array con partición
        
        if (k == pivotIndex) {
            return arr[k]; // Si el pivote está en la posición k, hemos encontrado el elemento
        } else if (k < pivotIndex) {
            return quickSelectHelper(arr, left, pivotIndex - 1, k); // Buscar en la parte izquierda
        } else {
            return quickSelectHelper(arr, pivotIndex + 1, right, k); // Buscar en la parte derecha
        }
    }

    // Método para particionar el array en base al pivote
    private static int partition(int[] arr, int left, int right) {
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1); // Selección aleatoria del pivote
        int pivotValue = arr[pivotIndex];

        swap(arr, pivotIndex, right); // Mover el pivote al final temporalmente

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) { // Si el elemento es menor que el pivote
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }

        swap(arr, storeIndex, right); // Mover el pivote a su posición final
        return storeIndex; // Retorna la posición del pivote
    }

    // Método auxiliar para intercambiar dos elementos
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] entrada1 = {4, 2, 7, 10, 4, 17};
        int k1 = 3;
        System.out.println("Resultado 1: " + quickSelect(entrada1, k1)); 
        int[] entrada2 = {4, 2, 7, 10, 4, 1, 6};
        int k2 = 5;
        System.out.println("Resultado 2: " + quickSelect(entrada2, k2)); 
    }
}
