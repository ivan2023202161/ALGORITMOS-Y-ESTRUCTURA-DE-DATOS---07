package avltree;

import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;

public class PruebaEjer6 {
    public static void main(String[] args) {
        AvlTree<Integer> tree = new AvlTree<>();

        // Insertar los elementos uno por uno
        try {
            tree.insert(50);  // Inserción de 50 - No se necesita rotación
            tree.insert(30);  // Inserción de 30 - No se necesita rotación
            tree.insert(70);  // Inserción de 70 - No se necesita rotación
            tree.insert(55);  // Inserción de 55 - No se necesita rotación
            System.out.println("insertar 50, 30, 70,55");
            tree.drawBST();
            
            tree.insert(40);  // Inserción de 40 - No se necesita rotación
            tree.insert(60);  // Inserción de 60 - No se necesita rotación
            tree.insert(80);  // Inserción de 80 - No se necesita rotación
            tree.insert(25);  // Inserción de 25 - No se necesita rotación
            System.out.println("insertar 40, 60, 80,25");

            tree.drawBST();
            System.out.println("insertar 10");

            tree.insert(10);  // Inserción de 10 - Rotación a la derecha (SR) en 30
            
            tree.drawBST();

            System.out.println("insertar 90");

            tree.insert(90);  // Inserción de 90 - No se necesita rotación
            
            tree.drawBST();
            System.out.println("insertar 5");

            tree.insert(5);   // Inserción de 5 - Rotación a la derecha (SR) en 25
            tree.drawBST();
            System.out.println("insertar 15");

            tree.insert(15);  // Inserción de 15 - Rotación a la izquierda (SL) en 10

        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }
        
        // Mostrar el árbol después de las inserciones
        System.out.println("Árbol después de las inserciones:");
        tree.drawBST();

        System.out.println();
        
        // Eliminaciones
        try {
            tree.delete(30);  // Eliminación de 30 - No se necesita rotación
            System.out.println("eliminar 30");
            tree.drawBST();
            tree.delete(55);  // Eliminación de 55 - No se necesita rotación
            System.out.println("eliminar 55");
            tree.drawBST();
            System.out.println("eliminar 70");
            tree.delete(70);  // Eliminación de 70 - Rotación simple a la izquierda (SL) en 50
            tree.drawBST();
            System.out.println("eliminar 40");
            tree.delete(40);  // Eliminación de 40 - No se necesita rotación
            tree.drawBST();

            System.out.println("eliminar 10");

            tree.delete(10);  // Eliminación de 10 - Rotación simple a la izquierda (SL) en 50

        } catch (ItemNoFound e) {
            System.out.println(e.getMessage());
        }
        
        // Mostrar el árbol después de las eliminaciones
        System.out.println("Árbol después de las eliminaciones:");
        tree.drawBST();

        System.out.println();
    }
}
