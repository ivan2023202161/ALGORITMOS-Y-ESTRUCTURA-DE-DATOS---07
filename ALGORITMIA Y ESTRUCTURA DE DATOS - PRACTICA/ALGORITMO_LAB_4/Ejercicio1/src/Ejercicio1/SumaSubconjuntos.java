package Ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class SumaSubconjuntos {


	public static boolean esPotenciaDe2(int num) {
		return (num & (num - 1)) == 0 && num > 0;
	}

	public static boolean puedeFormarSuma(int[] arreglo, int objetivo) {
		List<Integer> obligatorios = new ArrayList<>();
		List<Integer> opcionales = new ArrayList<>();

		for (int i = 0; i < arreglo.length; i++) {
			int actual = arreglo[i];

			if (esPotenciaDe2(actual)) {
				obligatorios.add(actual);
			} else if (actual % 5 == 0) {
				// Regla del múltiplo de 5
				if (i + 1 < arreglo.length && arreglo[i + 1] % 2 == 1) {
					continue; // No se puede incluir
				}
				opcionales.add(actual);
			} else {
				opcionales.add(actual);
			}
		}

		int sumaObligatorios = obligatorios.stream().mapToInt(Integer::intValue).sum();
		int nuevoObjetivo = objetivo - sumaObligatorios;

		// Si ya pasamos el objetivo con obligatorios, no es posible
		if (nuevoObjetivo < 0) return false;
		if (nuevoObjetivo == 0) return true;

		return puedeAlcanzarSuma(opcionales, nuevoObjetivo, 0);
	}

	private static boolean puedeAlcanzarSuma(List<Integer> nums, int objetivo, int index) {
		if (objetivo == 0) return true;
		if (index == nums.size()) return false;

		// Tomar o no tomar el número actual
		return puedeAlcanzarSuma(nums, objetivo - nums.get(index), index + 1) ||
				puedeAlcanzarSuma(nums, objetivo, index + 1);
	}

	public static void main(String[] args) {

		int[] arreglo = { 2, 16, 5, 3, 10};
		int objetivo = 33;

		boolean resultado = puedeFormarSuma(arreglo, objetivo);
		System.out.println(resultado ? "true" : "false"); 
	}
}

