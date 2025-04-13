package Actividad4;

import java.util.Arrays;

public class Solucion2Moda {

    // Encuentra la moda en un arreglo ordenado
    public static int moda2(int array[]) {
        int first = 1, p = 0, end = array.length - 1;
        int moda = array[0], frec = 1, maxfrec = 0;

        while (first <= end) {
            if (array[p] == array[first]) {
                frec++; // Incrementa frecuencia si los elementos son iguales
                first++;
            } else {
                if (frec > maxfrec) { // Actualiza moda si se encuentra una mayor frecuencia
                    maxfrec = frec;
                    moda = array[p];
                }
                p = first; first = p + 1; frec = 1;
            }
        }

        // Verifica la última frecuencia
        if (frec > maxfrec) {
            moda = array[p];
        }

        return moda;
    }

    public static void main(String[] args) {
        int[] arreglo = {2, 6, 6, 5, 6, 2, 4, 3, 6};
        Arrays.sort(arreglo); // Ordena para agrupar elementos iguales
        int moda = moda2(arreglo);
        System.out.println("La moda del arreglo es: " + moda);
    }
}

