package btree;

public class Main2 {
    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<>(5);

        System.out.println("Insertando");
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(5);
        bTree.insert(6);
        bTree.insert(12);
        bTree.insert(30);
        bTree.insert(7);
        bTree.insert(17);
        bTree.insert(15);

        System.out.println("Árbol después de insertar elementos:");
        System.out.println(bTree);

        bTree.remove(6);

        System.out.println("Árbol después de eliminar 6:");
        System.out.println(bTree);


        bTree.remove(10);

        System.out.println("Árbol después de eliminar 10:");
        System.out.println(bTree);
        bTree.search(15);
        
        
        BTree<RegistroEstudiante> arbol = new BTree<>(4);


        arbol.insert(new RegistroEstudiante(103, "Ana"));
        arbol.insert(new RegistroEstudiante(110, "Luis"));
        arbol.insert(new RegistroEstudiante(101, "Carlos"));
        arbol.insert(new RegistroEstudiante(120, "Lucía"));
        arbol.insert(new RegistroEstudiante(115, "David"));
        arbol.insert(new RegistroEstudiante(125, "Jorge"));
        arbol.insert(new RegistroEstudiante(140, "Camila"));
        arbol.insert(new RegistroEstudiante(108, "Rosa"));
        arbol.insert(new RegistroEstudiante(132, "Ernesto"));
        arbol.insert(new RegistroEstudiante(128, "Denis"));
        arbol.insert(new RegistroEstudiante(145, "Enrique"));
        arbol.insert(new RegistroEstudiante(122, "Karina"));
        arbol.insert(new RegistroEstudiante(108, "Juan"));
        
        System.out.println("Buscar 115: " + arbol.buscarNombre(115));   
        System.out.println("Buscar 132: " + arbol.buscarNombre(132));   
        System.out.println("Buscar 999: " + arbol.buscarNombre(999));   

        arbol.remove(new RegistroEstudiante(101, "")); 
        arbol.insert(new RegistroEstudiante(106, "Sara"));

        System.out.println("Buscar 106: " + arbol.buscarNombre(106));   


    }
    
    
}
