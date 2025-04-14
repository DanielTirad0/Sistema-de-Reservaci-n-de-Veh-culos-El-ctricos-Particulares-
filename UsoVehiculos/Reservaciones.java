package UsoVehiculos;

import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;

public class Reservaciones {

    private String estado;
    private Estacion estacion;
    private Vehiculos vehiculo; 
    private Usuario usuario;
    private double costo;
    private double tiempo;
    static Scanner input= new Scanner(System.in);
    private static HashMap<Usuario, Vehiculos> reservas = new HashMap<>();
    private static LinkedList<Reservaciones> historialDeReservaciones = new LinkedList<>();

    public Reservaciones(String estado, Vehiculos vehiculo, Estacion estacion, Usuario usuario, double costo, double tiempo) {
        this.tiempo= tiempo;
        this.estacion= estacion;
        this.estado= estado;
        this.vehiculo= vehiculo;
        this.usuario= usuario;
        this.costo= costo;
    }

    //Getters

    public String getEstado() {
        return estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public double getCosto() {
        return costo;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public Vehiculos getVehiculo() {
        return vehiculo;
    }
    public double getTiempo() {
        return tiempo;
    }
    //methods   
    public static void mostrarReservaciones(Reservaciones reservacion){
        System.out.println("Estado: "+ reservacion.getEstado());
        System.out.println("Vehiculo: "+ reservacion.getVehiculo().getVehiculo());
        System.out.println("Estacion: "+ reservacion.getEstacion().getUbicacion());
        System.out.println("Usuario: "+ reservacion.getUsuario().getName());
        System.out.println("Costo: "+ reservacion.getCosto());
        System.out.println("Tiempo: "+ reservacion.getTiempo());
        
    }

  public static void Agregar() {
    System.out.print("Introduzca su nombre: ");
    String name= input.nextLine();

    System.out.print("Introduzca su número de estudiante: ");
    int numEstudiante= input.nextInt();
    input.nextLine();

    System.out.print("Introduzca su email: ");
    String email= input.nextLine();

    System.out.print("Introduzca su numero de teléfono: ");
    String numTelefono= input.nextLine();

    Usuario persona= new Usuario(numEstudiante, name, email, numTelefono, 10);

    System.out.println("En cual estación desea hacer la reserva: ");
    Estacion estacionSeleccionada= Estacion.escogaestacion();

    Estacion.vehiculosEnLaEstacion(estacionSeleccionada);
    System.out.print("Introduzca el ID del vehículo que desea: ");
    int idSeleccionado= input.nextInt();
    input.nextLine();


    Vehiculos vehiculoSeleccionado= null;
    for (Vehiculos vehiculo: estacionSeleccionada.getVehiculos()) {
        if (vehiculo.getId() == idSeleccionado) {
            vehiculoSeleccionado= vehiculo;
            break;
        }
    }

    if (vehiculoSeleccionado.getEstado() == false) {
        vehiculoSeleccionado.setEstado(true);
        System.out.println("Reservación exitosa");
        Reservaciones reservacion = new Reservaciones("Pendiente", vehiculoSeleccionado, estacionSeleccionada, persona, 0.0, 0.0);
        historialDeReservaciones.add(reservacion);

        reservas.put(persona, vehiculoSeleccionado);
    }

    else {
        System.out.println("El vehículo seleccionado ya ha sido reservado");
    }
  }  

  public static void mostrarTodasLasReservaciones() {
    if (historialDeReservaciones.isEmpty()) {
        System.out.println("No hay reservaciones registradas.");
        return;
    }

    System.out.println("----- Historial de Reservaciones -----");

    for (Reservaciones reservacion : historialDeReservaciones) {
        System.out.println("--------------------------------------");
        System.out.println("Usuario: " + reservacion.getUsuario().getName());
        System.out.println("Número de Estudiante: " + reservacion.getUsuario().getNumeroDeEstudiante());
        System.out.println("Estación: " + reservacion.getEstacion().getUbicacion());
        System.out.println("Vehículo: " + reservacion.getVehiculo().getVehiculo());
        System.out.println("Estado: " + reservacion.getEstado());
        System.out.println("Costo: $" + reservacion.getCosto());
        System.out.println("Tiempo: " + reservacion.getTiempo() + " horas");
    }

    System.out.println("--------------------------------------");
    }

}