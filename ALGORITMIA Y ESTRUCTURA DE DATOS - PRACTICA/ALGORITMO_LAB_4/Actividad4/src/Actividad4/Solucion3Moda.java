package Actividad4;
import java.util.*;

public class Solucion3Moda {

	// Método principal para hallar la moda usando divide y vencerás
	public static int moda3(int[] a, int prim, int ult) {
		// Primero, ordenamos el arreglo para facilitar la búsqueda de la moda
		Arrays.sort(a);

		// Ahora, usamos una técnica de dividir el arreglo en tres partes.
		return modaRecursiva(a, prim, ult);
	}

	// Método recursivo para calcular la moda
	public static int modaRecursiva(int[] a, int prim, int ult) {
		if (prim == ult) {
			return a[prim];  // Solo hay un elemento, es la moda
		}

		// Calcular la mediana
		int mediana = a[(prim + ult) / 2];

		// Dividir el arreglo en tres partes usando la mediana
		int izq = prim;
		int der = ult;

		while (izq <= der) {
			while (a[izq] < mediana) izq++;
			while (a[der] > mediana) der--;
			if (izq <= der) {
				// Intercambiar elementos para agrupar valores iguales al pivote
				int temp = a[izq];
				a[izq] = a[der];
				a[der] = temp;
				izq++;
				der--;
			}
		}

		// Realizamos la recursión solo sobre los subrangos relevantes
		int modaIzq = -1, modaDer = -1;

		// Recursión sobre el subvector izquierdo
		if (der >= prim) {
			modaIzq = modaRecursiva(a, prim, der);
		}

		// Recursión sobre el subvector derecho
		if (izq <= ult) {
			modaDer = modaRecursiva(a, izq, ult);
		}

		// Combina los resultados para obtener la moda
		return contarModa(a, prim, ult, modaIzq, modaDer);
	}

	// Método para contar la cantidad de veces que aparece un valor en el arreglo
	public static int contarModa(int[] a, int prim, int ult, int modaIzq, int modaDer) {
		int countIzq = 0, countDer = 0;

		// Contamos la moda en la sublista izquierda
		for (int i = prim; i <= ult; i++) {
			if (a[i] == modaIzq) countIzq++;
			if (a[i] == modaDer) countDer++;
		}

		// Retorna la moda más frecuente
		if (countIzq >= countDer) {
			return modaIzq;
		} else {
			return modaDer;
		}
	}

	public static void main(String[] args) {
		int[] datos = {3, 3, 2, 3, 4, 5, 6};
		System.out.println("Moda: " + moda3(datos, 0, datos.length - 1)); 
	}
}

