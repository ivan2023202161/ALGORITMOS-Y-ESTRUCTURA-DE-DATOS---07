package E1;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedir al usuario la cantidad máxima de rectángulos a almacenar
        System.out.print("Ingrese el número máximo de rectángulos que desea almacenar: ");
        int capacidad = sc.nextInt();

        // Crear un contenedor de rectángulos con la capacidad indicada
        ContainerRect container = new ContainerRect(capacidad);

        // Añadir rectángulos al contenedor
        for (int i = 0; i < capacidad; i++) {
            System.out.println("\nIngrese los datos para el rectángulo " + (i + 1) + ":");

            System.out.print("Ingrese una esquina del rectángulo: ");
            double esquina1X = sc.nextDouble();
            double esquina1Y = sc.nextDouble();
            System.out.print("Ingrese la esquina opuesta del rectángulo: ");
            double esquina1oX = sc.nextDouble();
            double esquina1oY = sc.nextDouble();

            // Crear un nuevo rectángulo usando las coordenadas proporcionadas
            Rectangulo rect = new Rectangulo(
                new Coordenada(Math.min(esquina1X, esquina1oX), Math.min(esquina1Y, esquina1oY)),
                new Coordenada(Math.max(esquina1X, esquina1oX), Math.max(esquina1Y, esquina1oY))
            );

            // Usar el método addRectangulo para agregar el rectángulo al contenedor
            container.addRectangulo(rect);
        }

        System.out.println("\nInformación de los rectángulos almacenados:");
        System.out.println(container.toString());

    }
}

