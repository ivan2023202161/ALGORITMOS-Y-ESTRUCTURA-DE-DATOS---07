package Actividad5;

public class naiveSolution {

    // Método recursivo para obtener el valor máximo de cortar una barra
    static int getValue(int[] values, int length) {
        if (length <= 0) // Caso base: si la longitud es 0, no se puede cortar más
            return 0;

        int tmpMax = -1; // Inicializa el valor máximo temporal
        for (int i = 0; i < length; i++) {
            // Calcula el valor máximo para la longitud actual usando recursión
            tmpMax = Math.max(tmpMax, values[i] + getValue(values, length - i - 1));
        }
        return tmpMax; // Retorna el valor máximo encontrado
    }

    public static void main(String[] args) {
        int[] values = new int[]{3, 7, 1, 3, 9}; // Precios de las longitudes de la barra
        int rodLength = values.length; // Longitud total de la barra
        System.out.println("El valor maximo: " + getValue(values, rodLength)); // Imprime el valor máximo
    }
}
