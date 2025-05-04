package actividad;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>();
        int opcion;

        do {
            System.out.println("\n=== Menú de Gestión de Tareas ===");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Eliminar tarea");
            System.out.println("3. Verificar si cierta tarea existe");
            System.out.println("4. Mostrar todas las tareas actuales");
            System.out.println("5. Obtener cantidad de tareas");
            System.out.println("6. Mostrar la tarea más prioritaria");
            System.out.println("7. Invertir la lista");
            System.out.println("8. Transferir tarea a tareas completadas");
            System.out.println("9. Mostrar tareas completadas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Agregar tarea
                    System.out.print("Ingrese el título de la tarea: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese la prioridad de la tarea (un número entero): ");
                    int prioridad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    gestor.agregarTarea(new Tarea(titulo, prioridad));
                    System.out.println("Tarea agregada correctamente.");
                    break;

                case 2:
                    // Eliminar tarea
                    System.out.print("Ingrese el título de la tarea a eliminar: ");
                    titulo = scanner.nextLine();
                    System.out.print("Ingrese la prioridad de la tarea a eliminar: ");
                    prioridad = scanner.nextInt();
                    scanner.nextLine(); 
                    Tarea tareaEliminar = new Tarea(titulo, prioridad);
                    if (gestor.eliminarTarea(tareaEliminar)) {
                        System.out.println("Tarea eliminada correctamente.");
                    } else {
                        System.out.println("No se encontró la tarea.");
                    }
                    break;

                case 3:
                    // Verificar si una tarea existe
                    System.out.print("Ingrese el título de la tarea a verificar: ");
                    titulo = scanner.nextLine();
                    System.out.print("Ingrese la prioridad de la tarea a verificar: ");
                    prioridad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    Tarea tareaVerificar = new Tarea(titulo, prioridad);
                    if (gestor.contieneTarea(tareaVerificar)) {
                        System.out.println("La tarea existe en la lista.");
                    } else {
                        System.out.println("La tarea no existe en la lista.");
                    }
                    break;

                case 4:
                    // Mostrar todas las tareas actuales
                    gestor.imprimirTareas();
                    break;

                case 5:
                    // Obtener cantidad de tareas
                    System.out.println("Cantidad de tareas: " + gestor.contarTareas());
                    break;

                case 6:
                    // Mostrar la tarea más prioritaria
                    Tarea tareaPrioritaria = gestor.obtenerTareaMasPrioritaria();
                    if (tareaPrioritaria != null) {
                        System.out.println("La tarea más prioritaria es: " + tareaPrioritaria);
                    } else {
                        System.out.println("No hay tareas en la lista.");
                    }
                    break;

                case 7:
                    // Invertir la lista
                    gestor.invertir();
                    System.out.println("La lista de tareas ha sido invertida.");
                    break;

                case 8:
                    // Transferir tarea a tareas completadas
                    System.out.print("Ingrese el título de la tarea a completar: ");
                    titulo = scanner.nextLine();
                    System.out.print("Ingrese la prioridad de la tarea a completar: ");
                    prioridad = scanner.nextInt();
                    scanner.nextLine(); 
                    Tarea tareaCompletar = new Tarea(titulo, prioridad);
                    if (gestor.completarTarea(tareaCompletar)) {
                        System.out.println("Tarea completada y movida a la lista de tareas completadas.");
                    } else {
                        System.out.println("No se pudo completar la tarea.");
                    }
                    break;

                case 9:
                    // Mostrar tareas completadas
                    gestor.imprimirTareasCompletadas();
                    break;

                case 0:
                    // Salir
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}
