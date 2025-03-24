package E1;

public class ContainerRect {
    private Rectangulo[] rectangulos;
    private double[] distancias;
    private double[] areas;
    private int n; 
    private int numRec = 0; 

    // Constructor
    public ContainerRect(int n) {
        this.n = n;
        this.rectangulos = new Rectangulo[n];
        this.distancias = new double[n];
        this.areas = new double[n];
    }

    // Método para agregar un rectángulo al final del arreglo
    public void addRectangulo(Rectangulo rect) {
        if (numRec < n) {
            this.rectangulos[numRec] = rect;

            // Calcular y almacenar la distancia euclidiana entre las dos esquinas
            double distancia = rect.getEsquina1().distancia(rect.getEsquina2());
            this.distancias[numRec] = distancia;

            // Calcular y almacenar el área del rectángulo
            double area = rect.calculoArea();
            this.areas[numRec] = area;

            numRec++; 
            System.out.println("Rectángulo agregado exitosamente.");
        } else {
            
            System.out.println("Error: capacidad alcanzada.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rectangulo Coordenadas Distancia Area\n");

        
        for (int i = 0; i < numRec; i++) {
            sb.append(i + 1)  // Número del rectángulo
              .append(" (")
              .append(rectangulos[i].getEsquina1()) // Coordenada de la esquina 1
              .append(", ")
              .append(rectangulos[i].getEsquina2()) // Coordenada de la esquina 2
              .append(") ")
              .append(String.format("%.3f", distancias[i])) // Distancia con 3 decimales
              .append(" ")
              .append(String.format("%.2f", areas[i])) // Área con 2 decimales
              .append("\n");
        }

        return sb.toString();
    }
}
