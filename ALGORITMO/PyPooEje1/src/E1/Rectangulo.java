package E1;

import java.util.*;
public class Rectangulo{
    private Coordenada esquina1;
    private Coordenada esquina2;

    // Constructor
    public Rectangulo(Coordenada c1, Coordenada c2) {
        setEsquina1(c1);
        setEsquina2(c2);
    }

    public void setEsquina1 (Coordenada coo) {
        this.esquina1 = coo;
    }
    public void setEsquina2 (Coordenada coo) {
        this.esquina2 = coo;
    }
    public Coordenada getEsquina1 () {
        return esquina1;
    }
    public Coordenada getEsquina2 () {
        return esquina2;
    }
    public double calculoArea() {
        double base = Math.abs(this.esquina1.getX() - this.esquina2.getX());
        double altura = Math.abs(this.esquina1.getY() - this.esquina2.getY());
        return base * altura;
    }

    public String toString() {
        return "(" + esquina1 + "," + esquina2 + ")";
    }
}
