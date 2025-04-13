package Actividad4;

public class Solucion1Moda {

    // Método para encontrar la moda del arreglo
    public static int moda1(int[] array) {
        int first = 0;
        int end = array.length - 1;

        // Si el arreglo tiene un solo elemento, ese es la moda
        if (first == end) {
            return array[first]; 
        }

        int moda = array[first];
        int maxfrec = frecuencia(array, first, end, moda);

        // Recorre el arreglo y actualiza la moda si encuentra una mayor frecuencia
        for (int i = first + 1; i <= end; i++) {
            int frec = frecuencia(array, i, end, array[i]);
            if (frec > maxfrec) {
                maxfrec = frec;
                moda = array[i];
            }
        }
        
        return moda;
    }

    // Calcula la frecuencia de un elemento en el rango [first, end]
    private static int frecuencia(int[] array, int first, int end, int ele) {
        int suma = 0;
        for (int i = first; i <= end; i++) {
            if (array[i] == ele) {
                suma++;
            }
        }
        return suma;
    }

    public static void main(String[] args) {
        int[] arreglo = {2, 6, 6, 5, 6, 2, 4, 3, 6};
        int moda = moda1(arreglo);
        System.out.println("La moda del arreglo es: " + moda);
    }
}


