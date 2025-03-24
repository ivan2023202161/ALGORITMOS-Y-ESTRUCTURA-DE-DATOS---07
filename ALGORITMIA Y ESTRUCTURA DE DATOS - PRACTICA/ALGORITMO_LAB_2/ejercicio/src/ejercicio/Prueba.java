package ejercicio;

import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Usamos un solo objeto de OperacionesMat con tipo genérico
        OperacionesMat<Integer> operacionesMatInteger = new OperacionesMat<>();
        OperacionesMat<Double> operacionesMatDouble = new OperacionesMat<>();
                    
        // Menú de operaciones
        System.out.println("\nMenú de operaciones");
        System.out.println("1. Suma.");
        System.out.println("2. Resta.");
        System.out.println("3. Producto.");
        System.out.println("4. División.");
        System.out.println("5. Potencia.");
        System.out.println("6. Raíz Cuadrada.");
        System.out.println("7. Raíz Cúbica.");
        System.out.println("8. Salir.");
        
        int opcion = 0;
        int tipoDato = 0;
        
        // Bucle para el menú de operaciones
        while (opcion != 8) { 
            System.out.print("\nElija una opción del menú de operaciones: ");
            opcion = sc.nextInt();
            
            if (opcion == 8) {
                System.out.println("Saliendo...");
                break; // Salir del bucle exterior si la opción es 8
            }

            // Elección del tipo de datos
            System.out.println("\n¿Con qué tipo de datos desea trabajar?\n1. Enteros \n2. Decimales");
            int opcionTipoDato = sc.nextInt();

            if (opcionTipoDato == 1) {
                tipoDato = 1;
                System.out.println("Trabajando con Enteros.");
            } else if (opcionTipoDato == 2) {
                tipoDato = 2;
                System.out.println("Trabajando con Decimales.");
            } else {
                System.out.println("Opción inválida.");
                continue; // Volver a pedir la opción si el tipo de dato es inválido
            }
            
            switch (opcion) {
                case 1: // Suma
                    System.out.println("Ingrese el primer número: ");
                    double n1 = sc.nextDouble();
                    System.out.println("Ingrese el segundo número: ");
                    double n2 = sc.nextDouble();
                    
                    if (tipoDato == 1) {
                        System.out.println("Suma: " + operacionesMatInteger.suma((int) n1, (int) n2));
                    } else {
                        System.out.println("Suma: " + operacionesMatDouble.suma(n1, n2));
                    }
                    break;
                
                case 2: // Resta
                    System.out.println("Ingrese el primer número: ");
                    n1 = sc.nextDouble();
                    System.out.println("Ingrese el segundo número: ");
                    n2 = sc.nextDouble();
                    
                    if (tipoDato == 1) {
                        System.out.println("Resta: " + operacionesMatInteger.resta((int) n1, (int) n2));
                    } else {
                        System.out.println("Resta: " + operacionesMatDouble.resta(n1, n2));
                    }
                    break;
                
                case 3: // Producto
                    System.out.println("Ingrese el primer número: ");
                    n1 = sc.nextDouble();
                    System.out.println("Ingrese el segundo número: ");
                    n2 = sc.nextDouble();
                    
                    if (tipoDato == 1) {
                        System.out.println("Producto: " + operacionesMatInteger.producto((int) n1, (int) n2));
                    } else {
                        System.out.println("Producto: " + operacionesMatDouble.producto(n1, n2));
                    }
                    break;
                
                case 4: // División
                    System.out.println("Ingrese el primer número: ");
                    n1 = sc.nextDouble();
                    System.out.println("Ingrese el segundo número: ");
                    n2 = sc.nextDouble();
                    
                    if (n2 == 0) {
                        System.out.println("Error: No se puede dividir entre 0.");
                    } else {
                        if (tipoDato == 1) {
                            System.out.println("División: " + operacionesMatInteger.division((int) n1, (int) n2));
                        } else {
                            System.out.println("División: " + operacionesMatDouble.division(n1, n2));
                        }
                    }
                    break;
                
                case 5: // Potencia
                    System.out.println("Ingrese la base: ");
                    n1 = sc.nextDouble();
                    System.out.println("Ingrese el exponente: ");
                    double exponente = sc.nextDouble();
                    
                    if (tipoDato == 1) {
                        System.out.println("Potencia: " + operacionesMatInteger.potencia((int) n1, (int) exponente));
                    } else {
                        System.out.println("Potencia: " + operacionesMatDouble.potencia(n1, (int)exponente));
                    }
                    break;
                
                case 6: // Raíz Cuadrada
                    System.out.println("Ingrese un número: ");
                    n1 = sc.nextDouble();
                    
                    if (n1 < 0) {
                        System.out.println("No se puede calcular la raíz cuadrada de un número negativo.");
                    } else {
                        if (tipoDato == 1) {
                            System.out.println("Raíz Cuadrada: " + operacionesMatInteger.raizCuadrada((int) n1));
                        } else {
                            System.out.println("Raíz Cuadrada: " + operacionesMatDouble.raizCuadrada(n1));
                        }
                    }
                    break;
                
                case 7: // Raíz Cúbica
                    System.out.println("Ingrese un número: ");
                    n1 = sc.nextDouble();
                    
                    if (tipoDato == 1) {
                        System.out.println("Raíz Cúbica: " + operacionesMatInteger.raizCubica((int) n1));
                    } else {
                        System.out.println("Raíz Cúbica: " + operacionesMatDouble.raizCubica(n1));
                    }
                    break;

                
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
        

        sc.close();  // Cerrar el scanner después de usarlo
    }
}


