package Actividad3;

//Clase que implementa el algoritmo de búsqueda binaria
public class BinarySearch {

	// Método recursivo para realizar la búsqueda binaria
	int binarySearch(int arr[], int lo, int hi, int x) {
		// Verifica que los índices sean válidos y que 'lo' no exceda el largo del arreglo
		if (hi >= lo && lo < arr.length - 1) {

			// Calcula el índice medio para dividir el arreglo
			int mid = lo + (hi - lo) / 2;

			// Si el elemento del medio es el que se esta buscando, lo retorna
			if (arr[mid] == x)
				return mid;

			// Si el elemento del medio es mayor que x, busca en la mitad izquierda
			if (arr[mid] > x)
				return binarySearch(arr, lo, mid - 1, x);

			// Si el elemento del medio es menor que x, busca en la mitad derecha
			return binarySearch(arr, mid + 1, hi, x);
		}

		// Si no se encuentra el elemento, retorna -1
		return -1;
	}

	// Método principal
	public static void main(String[] args) {
		
		BinarySearch ob = new BinarySearch();
		int arr[] = { 1, 2, 3, 4, 5 };

		int n = arr.length;
		int x = 4;

		// Llamada al método de búsqueda binaria
		int position = ob.binarySearch(arr, 0, n - 1, x);

		if (position == -1)
			System.out.println("Element not found !!!");
		else
			System.out.println("Element found at index: " + position);
	}
}

