package hash;

import java.util.LinkedList;

public class HashO {
	private LinkedList<Register>[] table;
	private int size;
	
	public HashO(int size) {
		this.size = size;
		this.table = new LinkedList[size];
		
	    for (int i = 0; i < size; i++) {
	        table[i] = new LinkedList<>();
	    }
	}
	
	public int hash(int key) {
        return key % size;
    }

    public void insert(Register reg) {
        int index = hash(reg.getKey());  
        LinkedList<Register> bucket = table[index];
        
        // Revisar si ya existe un registro con la misma clave y reemplazarlo
        for (Register r : bucket) {
            if (r.getKey() == reg.getKey()) {
                bucket.remove(r);  // Eliminar el registro viejo si existe
                break;
            }
        }

        bucket.add(reg);
    }
	
    public Register search(int key) {
        int index = hash(key);
        LinkedList<Register> bucket = table[index];
        
        // Buscar el registro en el bucket correspondiente
        for (Register reg : bucket) {
            if (reg.getKey() == key) {
                return reg;
            }
        }
        
        // Si no se encuentra el registro
        return null;
    }
	
    public void delete(int key) {
        int index = hash(key);
        LinkedList<Register> bucket = table[index];
        
        // Iterar y eliminar el registro si se encuentra
        for (Register reg : bucket) {
            if (reg.getKey() == key) {
                bucket.remove(reg);
                break;
            }
        }
    }
	
    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.print("Bucket " + i + ": ");
            if (table[i].isEmpty()) {
                System.out.println("Vacío");
            } else {
                for (Register reg : table[i]) {
                    System.out.print(reg + " ");
                }
                System.out.println();
            }
        }
    }
}