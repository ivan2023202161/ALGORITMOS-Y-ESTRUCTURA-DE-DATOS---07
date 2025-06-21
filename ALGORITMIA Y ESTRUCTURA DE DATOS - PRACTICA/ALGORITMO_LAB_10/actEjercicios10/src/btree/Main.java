package btree;

import Exceptions.ItemNoFound;

public class Main {
    public static void main(String[] args) {
        BTree<Integer> arbol1 = new BTree<>(5);

        int[] claves = {100,50,20, 70,10, 30, 80, 90, 200, 25,15,5,65,35,60,18,93,94};
        for (int clave : claves) {
            arbol1.insert(clave);
        }

        System.out.println(arbol1);
        
        
        try {
            BTree<Integer> arbol = BTree.building_BTree("archivo.txt");
            System.out.println(arbol);
        } catch (ItemNoFound e) {
            System.err.println("Error al construir el árbol: " + e.getMessage());
        }
    }
}
