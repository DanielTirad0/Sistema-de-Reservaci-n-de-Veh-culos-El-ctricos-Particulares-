package UsoVehiculos;

import java.util.Scanner;
//clase pricipal que contiene el menu principal y la logica del programa.
public class main {
    //variable para implementar logica de menu.
    static boolean finalizado = false;
    static int press = 0;
    static Scanner sc = new Scanner(System.in);
    //metodo main que contiene el menu principal y la logica del programa.
    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("Bienvenido a la Estación de Reservación de Vehículos");
        System.out.println("======================================");
        //loop infinito hasta que el usuario decida salir del programa.
        while (!finalizado) {
            System.out.println("\nMenú Principal:");
            System.out.println("--------------------------------------");
            System.out.println("1. Agregar o Eliminar Vehículos");
            System.out.println("2. Agregar o Eliminar Usuarios");
            System.out.println("3. Agregar o Eliminar Reservaciones");
            System.out.println("4. Mostrar Reservaciones");
            System.out.println("5. Mostrar Usuarios");
            System.out.println("6. Mostrar Vehículos");
            System.out.println("7. Mostrar Lista de Espera");
            System.out.println("8. Modificaciones");
            System.out.println("9. Deshacer última acción en la lista de reservaciones");
            System.out.println("10. Finalizar");
            System.out.println("--------------------------------------");
            System.out.print("Ingrese una opción: ");
            press = sc.nextInt();
            //se utiliza switch para determinar que opcion se selecciono.
            switch (press) {
                case 1:
                    System.out.println("\nGestión de Vehículos");
                    System.out.println("--------------------------------------");
                    System.out.println("1. Agregar Vehículo");
                    System.out.println("2. Eliminar Vehículo");
                    System.out.println("--------------------------------------");
                    System.out.print("Seleccione una opción: ");
                    int opcionVehiculo = sc.nextInt();
                    // al selecionar 1 o 2 se llama a los metodos de agregar o eliminar vehiculo.
                    if (opcionVehiculo == 1) Vehiculos.Agregar();
                    else if (opcionVehiculo == 2) Vehiculos.Eliminar();
                    else System.out.println("Opción no válida. Intente de nuevo.");
                    break;

                case 2:
                    System.out.println("\nGestión de Usuarios");
                    System.out.println("--------------------------------------");
                    System.out.println("1. Agregar Usuario");
                    System.out.println("2. Eliminar Usuario");
                    System.out.println("--------------------------------------");
                    System.out.print("Seleccione una opción: ");
                    int opcionUsuario = sc.nextInt();
                    // al selecionar 1 o 2 se llama a los metodos de agregar o eliminar usuario.
                    if (opcionUsuario == 1) Usuario.AgregarUsuario();
                    else if (opcionUsuario == 2) Usuario.EliminarUsuario();
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
                    // al selecionar 1 o 2 se llama a los metodos de agregar o eliminar reservacion.
                    if (opcionReserva == 1) Reservaciones.Agregar();
                    else if (opcionReserva == 2) Reservaciones.Eliminar();
                    else System.out.println("Opción no válida. Intente de nuevo.");
                    break;

                case 4:
                    //al selecionar 4 se llama al metodo de mostrar todas las reservaciones.
                    System.out.println("\nMostrando Reservaciones");
                    Reservaciones.mostrarTodasLasReservaciones();
                    break;
                case 5:
                    //al selecionar 5 se llama al metodo de mostrar todos los usuarios.
                    System.out.println("\nMostrando Usuarios");
                    Usuario.MostrarUsuario();
                    break;    

                case 6:
                    //al selecionar 6 se llama al metodo de mostrar todos los vehiculos.
                    System.out.println("\nMostrando Vehículos");
                    Vehiculos.Mostrar();
                    break;
                case 7:
                    //al selecionar 7 se llama al metodo de mostrar la lista de espera.
                    System.out.println("\nMostrando Lista de Espera");
                    Reservaciones.mostrarListaDeEspera();
                    break;
                case 8:
                    //al selecionar 8 se llama al metodo de modificaciones.
                    System.out.println("\nGestión de Modificaciones");
                    System.out.println("--------------------------------------");
                    System.out.println("1. Modificar Usuario");
                    System.out.println("2. Modificar Vehículo");
                    System.out.println("3. Modificar Reservación");
                    System.out.println("4. Modificar Lista de Espera");
                    System.out.println("--------------------------------------");
                    System.out.print("Seleccione una opción: ");
                    int opcionModificacion = sc.nextInt();
                    if (opcionModificacion == 1) {
                        Usuario.ModificaciondeUsuario();
                    } else if (opcionModificacion == 2) {
                        Vehiculos.ModificaciondeVehiculo();
                    } else if (opcionModificacion == 3) {
                        Reservaciones.ModificaciondeReservacion();
                    } else if (opcionModificacion==4){
                        Reservaciones.modificarListaDeEspera();
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;
                case 9:
                    //al selecionar 9 se llama al metodo de deshacer la ultima accion en la lista de reservaciones.
                    System.out.println("\nDeshaciendo última acción...");
                    Reservaciones.Undo();
                    break; 
                case 10:
                    //al selecionar 10 se termina el programa.
                    System.out.println("Las reservaciones de hoy son:");
                    Reservaciones.mostrarTodasLasReservaciones();
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
