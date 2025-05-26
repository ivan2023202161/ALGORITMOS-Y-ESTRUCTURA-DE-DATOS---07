package avltree;

import Exceptions.ItemDuplicated;

public class TestAVL {
    public static void main(String[] args) {
        AvlTree<Integer> tree = new AvlTree<>();

        try {
            //Insertamos 10, 20
            System.out.println("Inserción de 10, 20 y 30 provocando rotación simple a la izquierda (RSL)");
            tree.insert(10); 
            tree.insert(20); 
            
            //Insertamos 10, 20, 30 (esto causará una rotación simple a la izquierda - RSL)
            tree.insert(30); 
            System.out.println("Rotación: RSL (rotación simple a la izquierda)");
            tree.drawBST(); 

            //Insertamos 25 y 29 (esto causará una rotación para balancear el árbol)
            System.out.println("\nInserción de 25 y 29 provocando rotación para balanceo");
            tree.insert(25); 
            tree.insert(29); // Esto provoca un balanceo y el árbol se reestructura
            System.out.println("Rotación: Balanceo con rotación doble a la izquierda (RDL)");
            tree.drawBST(); 

            // Insertamos 8, 5 y 3 (esto provoca una rotación y balanceo)
            System.out.println("\nInserción de 8, 5 y 3 provocando rotaciones y balanceo");
            tree.insert(8);  
            tree.insert(5); // Esto provoca una rotación para balancear el árbol
            
            tree.insert(3);  
            System.out.println("Rotación: RSR ");
            tree.drawBST();  

            // Insertamos 50, 60 y 55 (esto causa rotación para balancear)
            System.out.println("\nInserción de 50, 60 y 55 provocando rotación y balanceo");
            tree.insert(50); 
            tree.insert(60); 
            tree.insert(55); // Esto provoca una rotación para balancear
            System.out.println("Rotación: RDR (rotación doble a la derecha)");
            tree.drawBST(); 

            // Insertamos 70, 80
            System.out.println("\nInserción de 70, 80 y 90 provocando rotación para balancear");
            tree.insert(70); 
            tree.insert(80); 
            
         	// Insertamos 90 (esto causa rotación para balancear)
            tree.insert(90); // Esto provoca rotación para balancear
            System.out.println("Rotación: RSl (rotación simple a la izquierda)");
            
            System.out.println("\nAVL 1 ");

            tree.drawBST(); 
            
            System.out.println("\nRecorrido en preorden avl 1: ");
            tree.preOrderTraversal();
            
            AvlTree<Integer> tree2 = new AvlTree<>();
            AvlTree<Integer> tree3 = new AvlTree<>();

            System.out.println("\nAVL 2 ");

            // Insertar elementos en el árbol
            tree2.insert(50);
            tree2.insert(30);
            tree2.insert(70);
            tree2.insert(20);
            tree2.insert(40);
            tree2.drawBST();
            
            System.out.println("\nRecorrido en preorden avl 2: ");
            tree2.preOrderTraversal(); 
            
            
            System.out.println("\nAVL 3 ");

            tree3.insert(60);
            tree3.insert(80);
            tree3.insert(10);
            tree3.insert(25);
            tree3.insert(65);
            
            tree3.drawBST();
            
            System.out.println("\nRecorrido en preorden avl 3: ");
            tree3.preOrderTraversal(); 

            // Realizar el recorrido por amplitud
            System.out.println("\nRecorrido por amplitud (BFS): ");
            tree2.breadthFirstTraversal();  // Mostrar los nodos por niveles
            
            

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

