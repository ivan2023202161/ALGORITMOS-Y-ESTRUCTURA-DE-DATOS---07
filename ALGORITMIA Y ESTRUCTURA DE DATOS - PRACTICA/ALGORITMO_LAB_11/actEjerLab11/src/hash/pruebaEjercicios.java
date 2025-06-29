package hash;

public class PruebaEjercicios {
    public static void main(String[] args) {
    	
        // Ejercicio 1----------------------------------------------------------------------------
    	HashTable hashTable = new HashTable(7);

        System.out.println("Insertando los valores: 3, 10, 17, 24");
        int[] values = {3, 10, 17, 24};
        for (int value : values) {
            hashTable.insert(value);
        }

        System.out.println("\nTabla de hash después de las inserciones:");
        hashTable.printTable();
        
        
        
        
        // Ejercicio 2----------------------------------------------------------------------------
        HashTable hashTablex = new HashTable(6);

        int[] valuess = {12, 18, 24, 30};
        for (int value : valuess) {
            System.out.println("Insertando valor: " + value);
            hashTablex.insert(value);  
            hashTablex.printTable();    
        }
        
        
        //Ejercicio 3-----------------------------------------------------------------------------
        HashO hashTable3 = new HashO(5);

        // Insertar elementos en la tabla hash
        hashTable3.insert(new Register(10, "Juan"));
        hashTable3.insert(new Register(15, "Ana"));
        hashTable3.insert(new Register(20, "Luis"));
        hashTable3.insert(new Register(25, "Rosa"));
        
        hashTable3.printTable();    


        Register result = hashTable3.search(20);
        if (result != null) {
            System.out.println("El nombre asociado a la clave 20 es: " + result.getName());
        } else {
            System.out.println("No se encontro un registro con la clave 20.");
        }

        result = hashTable3.search(30);
        if (result != null) {
            System.out.println("El nombre asociado a la clave 30 es: " + result.getName());
        } else {
            System.out.println("No se encontro un registro con la clave 30.");
        }
        
        
        //Ejercicio 4-------------------------------------------------------------------------------

        HashTable hashTable4 = new HashTable(7);

        int[] valuex = {12, 18, 24, 30};
        for (int value : valuex) {
            System.out.println("Insertando valor: " + value);
            hashTable4.insert(value);  
            hashTable4.printTable();    
        }

        System.out.println("\nEliminando la clave 12:");
        hashTable4.delete(12);
        hashTable4.printTable();

        // Buscar la clave 19 después de la eliminación de 12
        System.out.println("\nBuscando la clave 19 después de eliminar 12:");
        boolean found = hashTable4.search(19);
        if (found) {
            System.out.println("Clave 19 encontrada.");
        } else {
            System.out.println("Clave 19 no encontrada.");
        }
    }
}
