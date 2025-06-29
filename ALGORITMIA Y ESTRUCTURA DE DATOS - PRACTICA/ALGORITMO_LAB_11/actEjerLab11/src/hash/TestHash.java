package hash;

public class TestHash {
    public static void main(String[] args) {
        HashC hashTable = new HashC(29);

        hashTable.insert(new Register(34, "ivan1"));
        hashTable.insert(new Register(3, "ivan2"));
        hashTable.insert(new Register(7, "ivan3"));
        hashTable.insert(new Register(30, "ivan4"));
        hashTable.insert(new Register(11, "ivan5"));
        hashTable.insert(new Register(8, "ivan6"));
        hashTable.insert(new Register(7, "ivan7")); 
        hashTable.insert(new Register(23, "ivan8"));
        hashTable.insert(new Register(41, "ivan9"));
        hashTable.insert(new Register(16, "ivan10"));
        hashTable.insert(new Register(34, "ivan11"));

        System.out.println("TablaAntes de eliminar:");
        hashTable.printTable();

        hashTable.delete(30);

        System.out.println("\nDespués de eliminar la clave 30:");
        hashTable.printTable();

        Register result = hashTable.search(23);
        System.out.println("\nResultado de busqueda para clave 23:");
        if (result != null) {
            System.out.println("Clave encontrada: " + result.getKey() + ", Valor: " + result);
        } else {
            System.out.println("Clave 23 no encontrada.");
        }
        
        
        
        
        
        HashO hashTableo = new HashO(10);
        
        hashTableo.insert(new Register(1, "Alice"));
        hashTableo.insert(new Register(2, "Bob"));
        hashTableo.insert(new Register(11, "Charlie"));
        
        System.out.println("Tabla de hash después de inserciones:");
        hashTableo.printTable();
        
        System.out.println("\nBuscar clave 2: " + hashTableo.search(2));
        System.out.println("Buscar clave 5: " + hashTableo.search(5));  // No existe
        
        hashTableo.delete(2);
        
        System.out.println("\nTabla de hash después de eliminar clave 2:");
        hashTableo.printTable();
    }
}