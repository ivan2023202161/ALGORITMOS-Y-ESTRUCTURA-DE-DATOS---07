package bstreelinklistinterfgeneric;

import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;

public class Prueba {
    public static void main(String[] args) {
        // Crear una instancia de LinkedBST
        LinkedBST<Integer> bst = new LinkedBST<>();
        
        try {
            bst.insert(400);
            bst.insert(100);
            bst.insert(700);
            bst.insert(50);
            bst.insert(75);
            bst.insert(200);
        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }
        // Mostrar el árbol usando el método toString()
        System.out.println("Árbol binario de búsqueda:");
        System.out.println(bst.toString());
        
        // Mostrar el árbol en formato de paréntesis
        System.out.println("Árbol binario en formato de paréntesis:");
        bst.parenthesize();
        
        // Calcular el área del árbol
        System.out.println("Área del árbol:");
        System.out.println(bst.areaBST());
        
        // Dibujar el árbol
        System.out.println("Dibujando el árbol:");
        bst.drawBST();
        
        // Contar todos los nodos
        System.out.println("Número total de nodos en el árbol:");
        System.out.println(bst.countAllNodes());
        
        // Contar nodos no-hojas
        System.out.println("Número de nodos no-hojas:");
        System.out.println(bst.countNodes());
        
        // Calcular la altura del árbol para un nodo
        System.out.println("Altura del nodo con valor 75:");
        System.out.println(bst.height(75));
        
        // Calcular la amplitud en el nivel 2
        System.out.println("Amplitud en el nivel 2:");
        System.out.println(bst.amplitude(2));
        
        // Buscar un nodo
        System.out.println("Buscar nodo con valor 200:");
        try {
            System.out.println(bst.search(200));
        } catch (ItemNoFound e) {
            System.out.println(e.getMessage());
        }
        
        // Eliminar un nodo
        System.out.println("Eliminar nodo con valor 100:");
        try {
            bst.delete(100);
            System.out.println(bst.toString()); // Ver el árbol después de eliminar
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }

        // Comparar áreas de dos árboles
        System.out.println("Comparando áreas de dos árboles:");
        LinkedBST<Integer> bst2 = new LinkedBST<>();
        
        try {
        	bst2.insert(300);
            bst2.insert(150);
            bst2.insert(500);
            bst2.insert(120);
            bst2.insert(180);
        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }
        Prueba prueba = new Prueba();
        System.out.println("¿Los árboles tienen la misma área? " + prueba.sameArea(bst, bst2));
    
     // Probar recorridos: in-order, pre-order, post-order
        System.out.println("\nRecorridos del árbol:");
        
        System.out.println("\nRecorrido In-Order:");
        System.out.println(bst.inOrder());
        
        System.out.println("\nRecorrido Pre-Order:");
        System.out.println(bst.preOrder());
        
        System.out.println("\nRecorrido Post-Order:");
        System.out.println(bst.postOrder());
    }

    // Método que compara las áreas de dos árboles
    public boolean sameArea(LinkedBST<?> tree1, LinkedBST<?> tree2) {
        return tree1.areaBST() == tree2.areaBST();
    }
}
