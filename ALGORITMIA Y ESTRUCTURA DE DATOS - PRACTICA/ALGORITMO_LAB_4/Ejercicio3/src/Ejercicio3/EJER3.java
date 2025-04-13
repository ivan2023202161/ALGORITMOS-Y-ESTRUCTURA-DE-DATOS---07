package Ejercicio3;

public class EJER3 {

	public static int[][] calcularCostosMinimos(int[][] T) {
		int n = T.length;
		int[][] C = new int[n][n];

		// Inicialización: C[i][i] = 0
		for (int i = 0; i < n; i++) {
			C[i][i] = 0;
		}

		// Cálculo dinámico de C[i][j]
		for (int longitud = 1; longitud < n; longitud++) {
			for (int i = 0; i + longitud < n; i++) {
				int j = i + longitud;
				C[i][j] = T[i][j]; // opción directa
				for (int k = i + 1; k < j; k++) {
					C[i][j] = Math.min(C[i][j], T[i][k] + C[k][j]);
				}
			}
		}

		return C;
	}

	public static void imprimirMatriz(int[][] matriz) {
		for (int[] fila : matriz) {
			for (int val : fila) {
				System.out.print((val == Integer.MAX_VALUE ? "∞" : val) + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] T = {
				{0, 3, 1, 6, Integer.MAX_VALUE},
				{0, 0, 1, 2, 7},
				{0, 0, 0, 1, 2},
				{0, 0, 0, 0, 3},
				{0, 0, 0, 0, 0}
		};

		int[][] C = calcularCostosMinimos(T);
		System.out.println("Matriz de Costos Mínimos:");
		imprimirMatriz(C);
	}
}
