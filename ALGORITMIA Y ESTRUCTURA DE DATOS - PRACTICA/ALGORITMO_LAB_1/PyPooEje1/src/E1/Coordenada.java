package E1;

public class Coordenada{
    private double x;
    private double y;

    public Coordenada( ){
        this.x = 0;
        this.y = 0;
    }

    //Constructor
    public Coordenada(double x, double y ){
        this.x = x;
        this.y = y;
    }
    //Constructor
    public Coordenada(Coordenada c ){
        this.x = c.getX();
        this.y = c.getY();
    }
    //métodos setter
    void setX(double x) {
        this.x= x;
    }
    void setY(double y){
        this.y = y;
    }
    //métodos getter
    double getX(){
        return x;
    }
    double getY(){
        return y;
    }
    //método de instancia que calcula la distancia euclideana
    double distancia(Coordenada c){
        double dx = this.x - c.getX();
        double dy = this.y - c.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }
    //método de clase que calcula la distancia euclideana
    static double distancia(Coordenada c1, Coordenada c2){
        double dx = c1.getX() - c2.getX();
        double dy = c1.getY() - c2.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }
    public String toString(){
        return "[" + x + "," + y + "]";
    }
}
