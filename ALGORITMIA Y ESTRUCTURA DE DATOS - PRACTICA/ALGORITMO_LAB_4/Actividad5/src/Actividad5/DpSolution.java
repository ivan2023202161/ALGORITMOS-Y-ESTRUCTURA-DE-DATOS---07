package Actividad5;

public class DpSolution {

    // Método para obtener el valor máximo utilizando programación dinámica
    static int getValue(int[] values, int rodLength) {
        int[] subSolutions = new int[rodLength + 1]; // Arreglo para soluciones de subproblemas

        for (int i = 1; i <= rodLength; i++) {
            int tmpMax = -1;
            for (int j = 0; j < i; j++) {
                tmpMax = Math.max(tmpMax, values[j] + subSolutions[i - j - 1]);
            }
            subSolutions[i] = tmpMax; // Guarda la mejor solución para la longitud i
        }

        return subSolutions[rodLength]; // Retorna la solución óptima para la barra completa
    }

    public static void main(String[] args) {
        int[] values = new int[]{3, 7, 1, 3, 9};
        int rodLength = values.length;
        System.out.println("El valor maximo: " + getValue(values, rodLength));
    }
}
