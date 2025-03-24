package ActUno;

import java.util.*;

public class Main {

    public static void main (String[] args) {
        
        Scanner sc = new Scanner(System.in);
        

        System.out.print("Ingrese una esquina del 1er rectángulo: ");
        double esquina1X = sc.nextDouble();
        double esquina1Y = sc.nextDouble();
        System.out.print("Ingrese la esquina opuestas del 1er rectángulo: ");
        double esquina1oX = sc.nextDouble();
        double esquina1oY = sc.nextDouble();
        
        Rectangulo A = new Rectangulo( new Coordenada(Math.min(esquina1X, esquina1oX), Math.min(esquina1Y, esquina1oY)), new Coordenada(Math.max(esquina1X, esquina1oX), Math.max(esquina1Y, esquina1oY)));
        
    
        System.out.print("Ingrese una esquina del 2do rectángulo: ");
        double esquina2X = sc.nextDouble();
        double esquina2Y = sc.nextDouble();
        System.out.print("Ingrese la esquina opuesta del 2do rectángulo: ");
        double esquina2oX = sc.nextDouble();
        double esquina2oY = sc.nextDouble();
        
        Rectangulo B = new Rectangulo(new Coordenada(Math.min(esquina2X, esquina2oX), Math.min(esquina2Y, esquina2oY)),new Coordenada(Math.max(esquina2X, esquina2oX), Math.max(esquina2Y, esquina2oY)));
        
        
        mostrarInformacionRectangulo("A",A);
        mostrarInformacionRectangulo("B",B);
        
        
        if (Verificador.esSobrePos(A, B)) {
            
            System.out.println("Rectangulos A y B se sobreponen.");
            Rectangulo sobreposicion = rectanguloSobre(A, B);
            System.out.println("Area de sobreposicion: " + sobreposicion.calculoArea());

            
        } else if (Verificador.esJunto(A, B)) {
            System.out.println("Rectangulos A y B se juntan");
        } else if (Verificador.esDisjunto(A, B)) {
            System.out.println("Rectangulos A y B son disjuntos");
        } else {
            System.out.println("Error en la verificación.");
        }
    }
    public static void mostrarInformacionRectangulo(String nombre, Rectangulo r) {
        System.out.println("Rectángulo " + nombre + ": " + r);
    }
    
    public static Rectangulo rectanguloSobre(Rectangulo r1, Rectangulo r2) {

        double r1X1 = r1.getEsquina1().getX();
        double r1Y1 = r1.getEsquina1().getY();
        double r1X2 = r1.getEsquina2().getX();
        double r1Y2 = r1.getEsquina2().getY();
    
        double r2X1 = r2.getEsquina1().getX();
        double r2Y1 = r2.getEsquina1().getY();
        double r2X2 = r2.getEsquina2().getX();
        double r2Y2 = r2.getEsquina2().getY();
    
        double interseccionX1 = Math.max(r1X1, r2X1);
        double interseccionY1 = Math.max(r1Y1, r2Y1);
        double interseccionX2 = Math.min(r1X2, r2X2);
        double interseccionY2 = Math.min(r1Y2, r2Y2);
    
        return new Rectangulo(new Coordenada(interseccionX1, interseccionY1), new Coordenada(interseccionX2, interseccionY2));
    }

}
