package hash;

public class HashC {
    private static class Element {
        Register register;
        boolean isAvailable;

        public Element() {
            this.register = null;
            this.isAvailable = true;
        }
    }

    private Element[] table;
    private int size;

    public HashC(int size) {
        this.size = size;
        this.table = new Element[size];
        for (int i = 0; i < size; i++) {
            table[i] = new Element();
        }
    }
    

    public int hash(int key) {
        return key % size;
    }

    public void insert(Register reg) {
        int index = hash(reg.getKey());

        for (int i = 0; i < size; i++) {
            int newIndex = (index + i) % size;
            if (table[newIndex].isAvailable) {
                table[newIndex].register = reg;
                table[newIndex].isAvailable = false;
                return;
            }
        }

        System.out.println("Tabla llena. No se pudo insertar.");
    }

    public Register search(int key) {
        int index = hash(key);

        for (int i = 0; i < size; i++) {
            int newIndex = (index + i) % size;
            Element element = table[newIndex];

            if (element == null || element.isAvailable) {
                continue;
            }

            if (element.register != null && element.register.getKey() == key) {
                return element.register;
            }
        }

        return null;
    }

    public void delete(int key) {
        int index = hash(key);

        for (int i = 0; i < size; i++) {
            int newIndex = (index + i) % size;
            Element element = table[newIndex];

            if (element == null || element.isAvailable) {
                continue;
            }

            if (element.register != null && element.register.getKey() == key) {
                element.isAvailable = true;
                element.register = null;
                return;
            }
        }
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.print("[" + i + "]: ");
            if (!table[i].isAvailable && table[i].register != null) {
                System.out.println("Key: " + table[i].register.getKey() + ", Value: " + table[i].register);
            } else {
                System.out.println("Vacío");
            }
        }
    }

}
