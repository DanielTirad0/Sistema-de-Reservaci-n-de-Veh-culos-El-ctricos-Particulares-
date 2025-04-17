package UsoVehiculos;

import java.util.Set;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.HashSet;


public class Estacion {

    private String ubicacion;
    private int capacidad;   
    private Set<Vehiculos> vehiculos;
    private List<Reservaciones> reservaciones;
    static HashSet<Estacion> estaciones= new HashSet<>();

    public Estacion(String ubicacion, int capacidad) {

        this.ubicacion= ubicacion;
        this.capacidad= capacidad;
        this.vehiculos= new HashSet<>();
        this.reservaciones= new LinkedList<>();
    
    }
    //Getters

    public int getCapacidad() {
        return this.capacidad;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public Set<Vehiculos> getVehiculos() {
        return this.vehiculos;
    }

    public List<Reservaciones> getReservaciones() {
        return this.reservaciones;
    }
    //methods

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    static {
        estaciones.add(new Estacion("Edificio Stefani", 40));
        estaciones.add(new Estacion("Centro de Estudiantes", 90));
        estaciones.add(new Estacion("Edificio de Biología", 35));
        estaciones.add(new Estacion("Edificio Ingeniería Química", 45));
        estaciones.add(new Estacion("Edificio de Administración de Empresas", 45));
    }
    
    public static Estacion escogaestacion() {
    Scanner sc = new Scanner(System.in);
    int index = 1;
    System.out.println("Por favor, elija una estación:");
    for (Estacion estacion : estaciones) {
        System.out.println(index + ". " + estacion.getUbicacion() + " (Capacidad: " + estacion.getCapacidad() + ")");
        index++;
    }

    System.out.print("\nOpción: ");
    int choice = sc.nextInt();
    while(choice < 1 || choice > estaciones.size()) {
        System.out.println("Opción no válida. Intente de nuevo.");
        System.out.print("Opción: ");
        choice = sc.nextInt();}
    if (choice > 0 && choice <= estaciones.size()) {
        int i = 1;
        for (Estacion estacion : estaciones) {
            if (i == choice) {
                return estacion;
            }
            i++;
        }
    }
    
    return null;
}

    public static void vehiculosEnLaEstacion(Estacion ubicacion) {

        System.out.println("--------------------------------------");
        System.out.println("Estación seleccionada: " + ubicacion.getUbicacion());
        System.out.println("Vehículos disponibles en esta estación:\n");
    
        Set<Vehiculos> vehiculos = ubicacion.getVehiculos();
    
        if (vehiculos.isEmpty()) {
            return;

        } else {
            for (Vehiculos vehiculo : vehiculos) {
                String estado = vehiculo.getEstado() ? "Reservado" : "Disponible";
                System.out.println("- ID: " + vehiculo.getId() + ", Tipo: " + vehiculo.getVehiculo() + ", Estado: " + estado);
            }
        }

    }

}


