package UsoVehiculos;

import java.util.Scanner;

public class main {
    static boolean finalizado = false;
    static int press = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("Bienvenido a la Estación de Reservación de Vehículos");
        System.out.println("======================================");

        while (!finalizado) {
            System.out.println("\nMenú Principal:");
            System.out.println("--------------------------------------");
            System.out.println("1. Agregar o Eliminar Vehículos");
            System.out.println("2. Agregar o Eliminar Usuarios");
            System.out.println("3. Agregar o Eliminar Reservaciones");
            System.out.println("4. Agregar o Eliminar Usuarios en la Lista de Espera");
            System.out.println("5. Mostrar Reservaciones");
            System.out.println("6. Mostrar Usuarios");
            System.out.println("7. Mostrar Vehículos");
            System.out.println("8. Mostrar Lista de Espera");
            System.out.println("9. Modificaciones");
            System.out.println("10. Finalizar");
            System.out.println("--------------------------------------");
            System.out.print("Ingrese una opción: ");
            press = sc.nextInt();

            switch (press) {
                case 1:
                    System.out.println("\nGestión de Vehículos");
                    System.out.println("--------------------------------------");
                    System.out.println("1. Agregar Vehículo");
                    System.out.println("2. Eliminar Vehículo");
                    System.out.println("--------------------------------------");
                    System.out.print("Seleccione una opción: ");
                    int opcionVehiculo = sc.nextInt();

                    if (opcionVehiculo == 1) Vehiculos.Agregar();
                    else if (opcionVehiculo == 2) Vehiculos.Eliminar();
                    else System.out.println("Opción no válida. Intente de nuevo.");
                    break;

                case 3:
                    System.out.println("\nGestión de Reservaciones");
                    System.out.println("--------------------------------------");
                    System.out.println("1. Añadir Reservación");
                    System.out.println("2. Eliminar Reservación");
                    System.out.println("--------------------------------------");
                    System.out.print("Seleccione una opción: ");
                    int opcionReserva = sc.nextInt();

                    if (opcionReserva == 1) Reservaciones.Agregar();
                    else if (opcionReserva == 2) Reservaciones.Eliminar();
                    else System.out.println("Opción no válida. Intente de nuevo.");
                    break;

                case 5:
                    System.out.println("\nMostrando Reservaciones");
                    Reservaciones.mostrarTodasLasReservaciones();
                    break;

                case 7:
                    System.out.println("\nMostrando Vehículos");
                    Vehiculos.Mostrar();
                    break;

                case 10:
                    System.out.println("\nGracias por usar nuestro servicio. Hasta luego.");
                    finalizado = true;
                    break;

                default:
                    System.out.println("Opción no reconocida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }
}
