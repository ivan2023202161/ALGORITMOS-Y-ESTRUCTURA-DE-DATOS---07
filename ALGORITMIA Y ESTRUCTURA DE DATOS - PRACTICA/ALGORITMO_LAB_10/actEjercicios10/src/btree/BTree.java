package btree;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


import Exceptions.ItemNoFound;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E cl) {
        up = false;
        E mediana;
        BNode<E> pnew;
        mediana = push(this.root, cl);
        if (up) {
            pnew = new BNode<E>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
        int[] pos = new int[1];
        E mediana;

        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean fl = current.searchNode(cl, pos);
            if (fl) {
                System.out.println("Item duplicado\n");
                up = false;
                return null;
            }

            mediana = push(current.childs.get(pos[0]), cl);

            if (up) {
                if (current.nodeFull(this.orden - 1)) {
                    mediana = dividedNode(current, mediana, pos[0]);
                } else {
                    putNode(current, mediana, nDes, pos[0]);
                    up = false;
                }
            }

            return mediana;
        }
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        int i;
        for (i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;

        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nDes = new BNode<E>(this.orden);

        for (i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }

        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;

        if (k <= this.orden / 2) {
            putNode(current, cl, rd, k);
        } else {
            putNode(nDes, cl, rd, k - posMdna);
        }

        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;

        up = true;
        return median;
    }

	@Override
	public String toString() {
	    String s = "";
	    if (isEmpty())
	        s += "BTree is empty...";
	    else {
	        s = "Id.Nodo\tClaves Nodo\tId.Padre\tId.Hijos\n";
	        s += writeTree(this.root, "--");
	    }
	    return s;
	}

	private String writeTree(BNode<E> current, String idPadre) {
	    StringBuilder sb = new StringBuilder();

	    // Claves del nodo actual
	    StringBuilder claves = new StringBuilder("(");
	    for (int i = 0; i < current.count; i++) {
	        claves.append(current.keys.get(i));
	        if (i < current.count - 1) {
	            claves.append(", ");
	        }
	    }
	    claves.append(")");

	    // Hijos del nodo actual
	    ArrayList<Integer> hijosIds = new ArrayList<>();
	    for (int i = 0; i <= current.count; i++) {
	        BNode<E> hijo = current.childs.get(i);
	        if (hijo != null) {
	            hijosIds.add(hijo.idNode);
	        }
	    }

	    // Línea actual
	    sb.append(current.idNode).append("\t")
	      .append(claves).append("\t")
	      .append("[").append(idPadre).append("]").append("\t");

	    if (hijosIds.isEmpty()) {
	        sb.append("--");
	    } else {
	        sb.append(hijosIds.toString());
	    }
	    sb.append("\n");

	    // Recursión en los hijos
	    for (int i = 0; i <= current.count; i++) {
	        BNode<E> hijo = current.childs.get(i);
	        if (hijo != null) {
	            sb.append(writeTree(hijo, String.valueOf(current.idNode)));
	        }
	    }

	    return sb.toString();
	}
	
	public boolean search(E cl) {
	    return searchRecursive(this.root, cl);
	}

	private boolean searchRecursive(BNode<E> current, E cl) {
	    if (current == null) {
	        return false;
	    }

	    int[] pos = new int[1];
	    boolean found = current.searchNode(cl, pos);

	    if (found) {
	        System.out.println(cl + " se encuentra en el nodo " + current.idNode + " en la posición " + pos[0]);
	        return true;
	    } else {
	        return searchRecursive(current.childs.get(pos[0]), cl);
	    }
	}
	


	public static BTree<Integer> building_BTree(String filename) throws ItemNoFound {
	    BTree<Integer> tree = null;

	    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
	        String line;

	        // 1. Leer el orden del árbol
	        int orden = Integer.parseInt(br.readLine().trim());
	        tree = new BTree<>(orden);

	        // 2. Mapas para nodos por ID y nodos por nivel
	        Map<Integer, BNode<Integer>> nodos = new HashMap<>();
	        TreeMap<Integer, List<BNode<Integer>>> niveles = new TreeMap<>();

	        // 3. Leer nodos desde el archivo
	        while ((line = br.readLine()) != null) {
	            String[] parts = line.trim().split(",");
	            if (parts.length < 3) throw new ItemNoFound("Formato inválido: " + line);

	            int nivel = Integer.parseInt(parts[0].trim());
	            int id = Integer.parseInt(parts[1].trim());

	            BNode<Integer> nodo = new BNode<>(orden);
	            nodo.idNode = id;

	            // Insertar claves
	            for (int i = 2; i < parts.length; i++) {
	                int clave = Integer.parseInt(parts[i].trim());
	                nodo.keys.set(i - 2, clave);
	                nodo.count++;
	            }

	            // Validación de claves máximas
	            if (nodo.count > orden - 1) {
	                throw new ItemNoFound("Nodo con demasiadas claves en línea: " + line);
	            }

	            nodos.put(id, nodo);
	            niveles.computeIfAbsent(nivel, k -> new ArrayList<>()).add(nodo);
	        }

	        // 4. Reconectar jerarquía de padres e hijos
	        for (int nivel = 0; nivel < niveles.size() - 1; nivel++) {
	            List<BNode<Integer>> padres = niveles.get(nivel);
	            List<BNode<Integer>> hijos = niveles.get(nivel + 1);
	            int hijoIdx = 0;

	            for (BNode<Integer> padre : padres) {
	                for (int i = 0; i <= padre.count; i++) {
	                    if (hijoIdx >= hijos.size()) {
	                        throw new ItemNoFound("Faltan hijos para el nodo " + padre.idNode);
	                    }
	                    padre.childs.set(i, hijos.get(hijoIdx++));
	                }
	            }

	            if (hijoIdx < hijos.size()) {
	                throw new ItemNoFound("Hijos sobrantes no asignados en nivel " + (nivel + 1));
	            }
	        }

	        //  raíz
	        tree.root = niveles.firstEntry().getValue().get(0);

	        return tree;

	    } catch (IOException e) {
	        throw new ItemNoFound("Error leyendo archivo: " + e.getMessage());
	    } catch (NumberFormatException e) {
	        throw new ItemNoFound("Error de formato numérico: " + e.getMessage());
	    }
	}
	
	
	public void remove(E cl) {
	    if (root == null) {
	        System.out.println("El árbol está vacío");
	        return;
	    }
	    removeRecursive(root, cl);

	    // Si la raíz quedó sin claves, hacer que la nueva raíz sea su hijo 0
	    if (root.count == 0 && root.childs.get(0) != null) {
	        root = root.childs.get(0);
	    } else if (root.count == 0) {
	        root = null;
	    }
	}
	
	private boolean removeRecursive(BNode<E> current, E cl) {
	    int idx = findKey(current, cl);

	    if (idx < current.count && current.keys.get(idx).compareTo(cl) == 0) {
	        // La clave está en este nodo
	        if (current.childs.get(0) == null) {
	            // Nodo hoja: eliminar clave directamente
	            removeFromLeaf(current, idx);
	        } else {
	            // Nodo interno: eliminar clave y reemplazar
	            removeFromNonLeaf(current, idx);
	        }
	        return true;
	    } else {
	        // La clave no está en este nodo
	        if (current.childs.get(0) == null) {
	            // Nodo hoja y no está la clave -> no existe
	            System.out.println("Clave no encontrada");
	            return false;
	        }

	        boolean flag = (idx == current.count);

	        // Asegurar que el hijo donde buscaremos tenga al menos el mínimo de claves
	        if (current.childs.get(idx).count < (orden - 1) / 2 + 1) {
	            fill(current, idx);
	        }

	        if (flag && idx > current.count) {
	            return removeRecursive(current.childs.get(idx - 1), cl);
	        } else {
	            return removeRecursive(current.childs.get(idx), cl);
	        }
	    }
	}
	
	

	private void removeFromLeaf(BNode<E> node, int idx) {
	    for (int i = idx + 1; i < node.count; i++) {
	        node.keys.set(i - 1, node.keys.get(i));
	    }
	    node.count--;
	}

	private void removeFromNonLeaf(BNode<E> node, int idx) {
	    E key = node.keys.get(idx);

	    // Si el hijo anterior tiene al menos el mínimo de claves, reemplazar por su predecesor
	    if (node.childs.get(idx).count >= (orden - 1) / 2 + 1) {
	        E pred = getPredecessor(node, idx);
	        node.keys.set(idx, pred);
	        removeRecursive(node.childs.get(idx), pred);
	    } else if (node.childs.get(idx + 1).count >= (orden - 1) / 2 + 1) {
	        E succ = getSuccessor(node, idx);
	        node.keys.set(idx, succ);
	        removeRecursive(node.childs.get(idx + 1), succ);
	    } else {
	        merge(node, idx);
	        removeRecursive(node.childs.get(idx), key);
	    }
	}
	
	private int findKey(BNode<E> node, E key) {
	    int idx = 0;
	    while (idx < node.count && node.keys.get(idx).compareTo(key) < 0) {
	        idx++;
	    }
	    return idx;
	}

	private E getPredecessor(BNode<E> node, int idx) {
	    BNode<E> cur = node.childs.get(idx);
	    while (cur.childs.get(cur.count) != null) {
	        cur = cur.childs.get(cur.count);
	    }
	    return cur.keys.get(cur.count - 1);
	}

	private E getSuccessor(BNode<E> node, int idx) {
	    BNode<E> cur = node.childs.get(idx + 1);
	    while (cur.childs.get(0) != null) {
	        cur = cur.childs.get(0);
	    }
	    return cur.keys.get(0);
	}

	private void fill(BNode<E> node, int idx) {
	    if (idx != 0 && node.childs.get(idx - 1).count >= (orden - 1) / 2 + 1) {
	        borrowFromPrev(node, idx);
	    } else if (idx != node.count && node.childs.get(idx + 1).count >= (orden - 1) / 2 + 1) {
	        borrowFromNext(node, idx);
	    } else {
	        if (idx != node.count) {
	            merge(node, idx);
	        } else {
	            merge(node, idx - 1);
	        }
	    }
	}

	private void borrowFromPrev(BNode<E> node, int idx) {
	    BNode<E> child = node.childs.get(idx);
	    BNode<E> sibling = node.childs.get(idx - 1);

	    for (int i = child.count - 1; i >= 0; i--) {
	        child.keys.set(i + 1, child.keys.get(i));
	    }

	    if (child.childs.get(0) != null) {
	        for (int i = child.count; i >= 0; i--) {
	            child.childs.set(i + 1, child.childs.get(i));
	        }
	    }

	    child.keys.set(0, node.keys.get(idx - 1));
	    if (sibling.childs.get(0) != null) {
	        child.childs.set(0, sibling.childs.get(sibling.count));
	    }

	    node.keys.set(idx - 1, sibling.keys.get(sibling.count - 1));

	    child.count++;
	    sibling.count--;
	}

	private void borrowFromNext(BNode<E> node, int idx) {
	    BNode<E> child = node.childs.get(idx);
	    BNode<E> sibling = node.childs.get(idx + 1);

	    child.keys.set(child.count, node.keys.get(idx));
	    if (child.childs.get(0) != null) {
	        child.childs.set(child.count + 1, sibling.childs.get(0));
	    }

	    node.keys.set(idx, sibling.keys.get(0));

	    for (int i = 1; i < sibling.count; i++) {
	        sibling.keys.set(i - 1, sibling.keys.get(i));
	    }

	    if (sibling.childs.get(0) != null) {
	        for (int i = 1; i <= sibling.count; i++) {
	            sibling.childs.set(i - 1, sibling.childs.get(i));
	        }
	    }

	    child.count++;
	    sibling.count--;
	}

	private void merge(BNode<E> node, int idx) {
	    BNode<E> child = node.childs.get(idx);
	    BNode<E> sibling = node.childs.get(idx + 1);

	    child.keys.set(child.count, node.keys.get(idx));

	    for (int i = 0; i < sibling.count; i++) {
	        child.keys.set(child.count + 1 + i, sibling.keys.get(i));
	    }

	    if (child.childs.get(0) != null) {
	        for (int i = 0; i <= sibling.count; i++) {
	            child.childs.set(child.count + 1 + i, sibling.childs.get(i));
	        }
	    }

	    for (int i = idx + 1; i < node.count; i++) {
	        node.keys.set(i - 1, node.keys.get(i));
	    }

	    for (int i = idx + 2; i <= node.count; i++) {
	        node.childs.set(i - 1, node.childs.get(i));
	    }

	    child.count += sibling.count + 1;
	    node.count--;
	}
	
	public String buscarNombre(int codigo) {
	    E clave = (E) new RegistroEstudiante(codigo, ""); 
	    return buscarNombreRecursivo(this.root, clave);
	}

	private String buscarNombreRecursivo(BNode<E> current, E clave) {
	    if (current == null) {
	        return "No encontrado";
	    }

	    int[] pos = new int[1];
	    boolean found = current.searchNode(clave, pos);

	    if (found) {
	        return ((RegistroEstudiante) current.keys.get(pos[0])).getNombre(); // cast porque E no garantiza que tenga getNombre()
	    } else {
	        return buscarNombreRecursivo(current.childs.get(pos[0]), clave);
	    }
	}




}
