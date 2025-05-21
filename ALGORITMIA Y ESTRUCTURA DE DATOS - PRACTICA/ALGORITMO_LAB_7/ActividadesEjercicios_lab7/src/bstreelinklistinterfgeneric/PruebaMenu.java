package bstreelinklistinterfgeneric;

import java.util.Scanner;

import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;

public class PruebaMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedBST<Integer> bst = new LinkedBST<>();

        int option;
        
        do {
            // Mostrar el menú
            System.out.println("\n---- MENÚ ----");
            System.out.println("1. Insertar nodo");
            System.out.println("2. Mostrar árbol");
            System.out.println("3. Mostrar árbol en formato paréntesis");
            System.out.println("4. Calcular área del árbol");
            System.out.println("5. Dibujar el árbol");
            System.out.println("6. Contar el número total de nodos");
            System.out.println("7. Contar nodos no-hojas");
            System.out.println("8. Calcular altura de un nodo");
            System.out.println("9. Calcular amplitud en un nivel");
            System.out.println("10. Buscar nodo por valor");
            System.out.println("11. Eliminar nodo");
            System.out.println("12. Comparar áreas de dos árboles");
            System.out.println("13. Recorrer el árbol (in-order)");
            System.out.println("14. Recorrer el árbol (pre-order)");
            System.out.println("15. Recorrer el árbol (post-order)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("\nIngrese el valor del nodo a insertar: ");
                    int insertValue = scanner.nextInt();
                    try {
                        bst.insert(insertValue);
                        System.out.println("Nodo " + insertValue + " insertado correctamente.");
                    } catch (ItemDuplicated e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\nÁrbol binario de búsqueda:");
                    System.out.println(bst.toString());
                    break;

                case 3:
                    System.out.println("\nÁrbol binario en formato de paréntesis:");
                    bst.parenthesize();
                    break;

                case 4:
                    System.out.println("\nÁrea del árbol:");
                    System.out.println(bst.areaBST());
                    break;

                case 5:
                    System.out.println("\nDibujando el árbol:");
                    bst.drawBST();
                    break;

                case 6:
                    System.out.println("\nNúmero total de nodos en el árbol:");
                    System.out.println(bst.countAllNodes());
                    break;

                case 7:
                    System.out.println("\nNúmero de nodos no-hojas:");
                    System.out.println(bst.countNodes());
                    break;

                case 8:
                    System.out.print("\nIngrese el valor del nodo para calcular su altura: ");
                    int nodeValue = scanner.nextInt();
                    System.out.println("\nAltura del nodo con valor " + nodeValue + ":");
                    System.out.println(bst.height(nodeValue));
                    break;

                case 9:
                    System.out.print("\nIngrese el nivel para calcular la amplitud: ");
                    int level = scanner.nextInt();
                    System.out.println("\nAmplitud en el nivel " + level + ":");
                    System.out.println(bst.amplitude(level));
                    break;

                case 10:
                    System.out.print("\nIngrese el valor del nodo a buscar: ");
                    int searchValue = scanner.nextInt();
                    System.out.println("\nBuscar nodo con valor " + searchValue + ":");
                    try {
                        System.out.println(bst.search(searchValue));
                    } catch (ItemNoFound e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 11:
                    System.out.print("\nIngrese el valor del nodo a eliminar: ");
                    int deleteValue = scanner.nextInt();
                    System.out.println("\nEliminar nodo con valor " + deleteValue + ":");
                    try {
                        bst.delete(deleteValue);
                        System.out.println("Nodo " + deleteValue + " eliminado correctamente.");
                        System.out.println(bst.toString()); // Ver el árbol después de eliminar
                    } catch (ExceptionIsEmpty e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 12:
                    LinkedBST<Integer> bst2 = new LinkedBST<>();
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
                    Prueba prueba = new Prueba();
                    System.out.println("\nComparando áreas de dos árboles:");
                    System.out.println("¿Los árboles tienen la misma área? " + prueba.sameArea(bst, bst2));
                    break;

                case 13:
                    System.out.println("\nRecorrido In-Order:");
                    System.out.println(bst.inOrder());
                    break;

                case 14:
                    System.out.println("\nRecorrido Pre-Order:");
                    System.out.println(bst.preOrder());
                    break;

                case 15:
                    System.out.println("\nRecorrido Post-Order:");
                    System.out.println(bst.postOrder());
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);

        scanner.close();
    }

    // Método que compara las áreas de dos árboles
    public boolean sameArea(LinkedBST<?> tree1, LinkedBST<?> tree2) {
        return tree1.areaBST() == tree2.areaBST();
    }
}

