package actividad;


public class Tarea implements Comparable<Tarea> { //Implementa la interfaz Comparable

    String titulo; 
    int prioridad; 

    // Constructor 
    public Tarea(String titulo, int prioridad) {
        this.titulo = titulo;
        this.prioridad = prioridad;
    }
    
    // Getters
    public String getTitulo(){
        return this.titulo;
    }
    
    public int getPrioridad(){
        return this.prioridad;
    }
    
    // Setters
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public void setPrioridad(int prioridad ){
        this.prioridad = prioridad;
    }
    
    // Representación en forma de cadena de la tarea
    public String toString() {
        return titulo + " (Prioridad " + prioridad + ")";
    }
    
    // Método de comparación para ordenar tareas por prioridad
    @Override
    public int compareTo(Tarea otra) {
        return Integer.compare(this.prioridad, otra.prioridad); // Compara prioridades
    }

    // Compara si dos tareas son iguales (por título y prioridad)
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tarea) {
            Tarea otra = (Tarea) obj;
            return this.titulo.equals(otra.titulo) && this.prioridad == otra.prioridad;
        }
        return false;
    }
}

