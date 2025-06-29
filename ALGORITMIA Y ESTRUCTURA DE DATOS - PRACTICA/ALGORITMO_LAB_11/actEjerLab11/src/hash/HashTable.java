package hash;

public class HashTable {
    private int[] table;  
    private int size;

    // Constructor de la clase HashC
    public HashTable(int size) {
        this.size = size;
        this.table = new int[size];

        for (int i = 0; i < size; i++) {
            table[i] = -1;  // -1 indica que la celda está vacía
        }
    }

    public int hash(int key) {
        return key % size;
    }

    public void insert(int value) {
        int index = hash(value);  

        while (table[index] != -1) {  
            index = (index + 1) % size;  
        }

        table[index] = value;
    }

    public void printTable() {
        System.out.println("Tabla hash actual:");
        for (int i = 0; i < size; i++) {
            System.out.print("Índice " + i + ": ");
            if (table[i] == -1) {
                System.out.println("Vacío");
            } else {
                System.out.println(table[i]);
            }
        }
        System.out.println();
    }
    
    public void delete(int key) {
        int index = hash(key);
        
        while (table[index] != -1) {
            if (table[index] == key) {
                table[index] = -1;  // Marcamos la celda como vacía (-1)
                System.out.println("Clave " + key + " eliminada.");
                return;
            }
            index = (index + 1) % size;  
        }

        System.out.println("Clave " + key + " no encontrada para eliminar.");
    }

    // Método para buscar un valor en la tabla
    public boolean search(int key) {
        int index = hash(key);
        
        while (table[index] != -1) {
            if (table[index] == key) {
                return true; 
            }
            index = (index + 1) % size;  
        }

        return false;  
    }
}

