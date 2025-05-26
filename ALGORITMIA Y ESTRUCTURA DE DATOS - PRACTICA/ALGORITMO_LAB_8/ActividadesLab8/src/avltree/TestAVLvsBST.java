package avltree;

import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;
import bstreelinklistinterfgeneric.LinkedBST;

public class TestAVLvsBST {
    public static void main(String[] args) throws ItemDuplicated {
        // Árbol Binario de Búsqueda (BST) con inserción ascendente
    	LinkedBST<Integer> bstAscendente = new LinkedBST<>();
        System.out.println("Inserción ascendente en un BST:");
        bstAscendente.insert(10);
        bstAscendente.insert(20);
        bstAscendente.insert(30);
        bstAscendente.insert(40);
        bstAscendente.insert(50);

        System.out.println("Estructura del BST después de inserción ascendente:");
        System.out.println(bstAscendente.toString()); // Esperamos una estructura lineal

        // Árbol AVL con inserción ascendente
        AvlTree<Integer> avlAscendente = new AvlTree<>();
        System.out.println("\nInserción ascendente en un árbol AVL:");
        avlAscendente.insert(10);
        avlAscendente.insert(20);
        avlAscendente.insert(30);
        avlAscendente.insert(40);
        avlAscendente.insert(50);

        System.out.println("Estructura del AVL después de inserción ascendente:");
        avlAscendente.drawBST(); // AVL debe estar balanceado

        // Árbol Binario de Búsqueda (BST) con inserción descendente
        LinkedBST<Integer> bstDescendente = new LinkedBST<>();
        System.out.println("\nInserción descendente en un BST:");
        bstDescendente.insert(50);
        bstDescendente.insert(40);
        bstDescendente.insert(30);
        bstDescendente.insert(20);
        bstDescendente.insert(10);

        System.out.println("Estructura del BST después de inserción descendente:");
        System.out.println(bstDescendente.toString()); // Esperamos una estructura lineal

        // Árbol AVL con inserción descendente
        AvlTree<Integer> avlDescendente = new AvlTree<>();
        System.out.println("\nInserción descendente en un árbol AVL:");
        avlDescendente.insert(50);
        avlDescendente.insert(40);
        avlDescendente.insert(30);
        avlDescendente.insert(20);
        avlDescendente.insert(10);

        System.out.println("Estructura del AVL después de inserción descendente:");
        avlDescendente.drawBST(); // AVL debe estar balanceado
    }
}

