package Actividad3;

public class BinarySearchIterativo {

    // Método que implementa la búsqueda binaria de forma iterativa
    int binarySearch(int arr[], int x) {
        int lo = 0, hi = arr.length - 1; // Inicializa los límites inferior y superior

        // Mientras el rango de búsqueda sea válido
        while (lo <= hi) {
            // Calcula el índice medio del rango actual
            int mid = lo + (hi - lo) / 2;

            // Si el valor del medio es igual al que se busca, retorna su posición
            if (arr[mid] == x)
                return mid;

            // Si el valor del medio es menor que x, busca en la mitad derecha
            if (arr[mid] < x)
                lo = mid + 1;
            else
                // Si el valor del medio es mayor que x, busca en la mitad izquierda
                hi = mid - 1;
        }

        // Si no se encontró el valor, retorna -1
        return -1;
    }

    public static void main(String[] args) {
        BinarySearchIterativo ob = new BinarySearchIterativo();
        int arr[] = { 1, 2, 3, 4, 5 };
        int x = 3;

        int position = ob.binarySearch(arr, x);
        if (position == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at index: " + position);
    }
}
