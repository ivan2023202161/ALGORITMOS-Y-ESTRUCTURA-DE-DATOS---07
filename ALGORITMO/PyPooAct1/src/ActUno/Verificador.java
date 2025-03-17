package ActUno;

import java.util.*;

public class Verificador {
    
    // Verifica si los rectángulos se sobreponen
    public static boolean esSobrePos(Rectangulo r1, Rectangulo r2) {
        // Obtiene las esquinas de los rectángulos
        double r1X1 = r1.getEsquina1().getX();
        double r1Y1 = r1.getEsquina1().getY();
        double r1X2 = r1.getEsquina2().getX();
        double r1Y2 = r1.getEsquina2().getY();
        
        double r2X1 = r2.getEsquina1().getX();
        double r2Y1 = r2.getEsquina1().getY();
        double r2X2 = r2.getEsquina2().getX();
        double r2Y2 = r2.getEsquina2().getY();
        
        // Verifica si hay superposición
        return (r1X2 > r2X1 && r1X1 < r2X2 && r1Y2 > r2Y1 && r1Y1 < r2Y2);
    }
    
    // Verifica si los rectángulos están juntos (tocándose)
    public static boolean esJunto(Rectangulo r1, Rectangulo r2) {
        // Obtiene las esquinas de los rectángulos
        double r1X1 = r1.getEsquina1().getX();
        double r1Y1 = r1.getEsquina1().getY();
        double r1X2 = r1.getEsquina2().getX();
        double r1Y2 = r1.getEsquina2().getY();
        
        double r2X1 = r2.getEsquina1().getX();
        double r2Y1 = r2.getEsquina1().getY();
        double r2X2 = r2.getEsquina2().getX();
        double r2Y2 = r2.getEsquina2().getY();
        
        // Verifica si los rectángulos están tocándose (alineados en uno de sus lados)
        return (r1X2 == r2X1 || r1X1 == r2X2 || r1Y2 == r2Y1 || r1Y1 == r2Y2) && !esSobrePos(r1, r2);
    }
    
    // Verifica si los rectángulos son disjuntos (no se tocan ni se superponen)
    public static boolean esDisjunto(Rectangulo r1, Rectangulo r2) {
        // Obtiene las esquinas de los rectángulos
        double r1X1 = r1.getEsquina1().getX();
        double r1Y1 = r1.getEsquina1().getY();
        double r1X2 = r1.getEsquina2().getX();
        double r1Y2 = r1.getEsquina2().getY();
        
        double r2X1 = r2.getEsquina1().getX();
        double r2Y1 = r2.getEsquina1().getY();
        double r2X2 = r2.getEsquina2().getX();
        double r2Y2 = r2.getEsquina2().getY();
        
        // Verifica si los rectángulos no se tocan ni se superponen
        return (r1X2 < r2X1 || r1X1 > r2X2 || r1Y2 < r2Y1 || r1Y1 > r2Y2);
    }
}