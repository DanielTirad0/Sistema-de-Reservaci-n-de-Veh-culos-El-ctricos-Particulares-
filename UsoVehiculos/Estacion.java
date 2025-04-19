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
    static double costo;
    private double tiempoUso;
    static HashSet<Estacion> estaciones= new HashSet<>();
    static Scanner input = new Scanner(System.in);

    public Estacion(String ubicacion, int capacidad) {

        this.ubicacion= ubicacion;
        this.capacidad= capacidad;
        this.vehiculos= new HashSet<>();
        this.reservaciones= new LinkedList<>();
    
    }
    //Getters

    public double getTiempoUso() {
        return tiempoUso;
    }

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


    // Setter para tiempoUso
    public void setTiempoUso(double tiempoUso) {
        this.tiempoUso = tiempoUso;
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

            while (true) {
                try {
                    System.out.print("Ingrese el tiempo estimado de uso en horas (máximo 6): ");
                    double tiempo = input.nextDouble();
                    if (tiempo > 6) {
                        System.out.println("El tiempo de uso no puede ser mayor a 6 horas.");
                        continue;
                    } else if (tiempo <= 0) {
                        System.out.println("El tiempo de uso no puede ser negativo o cero.");
                        continue;
                    }
                    ubicacion.setTiempoUso(tiempo);  
                    break;
                } catch (Exception e) {
                    System.out.println("Por favor, introduzca un número válido.");
                    input.nextLine();
                }
            }
        
            for (Vehiculos vehiculo : vehiculos) {
                String estado = vehiculo.getEstado() ? "Reservado" : "Disponible";
        
                double costo = 0;
                if (vehiculo.getVehiculo().equals("Bicicleta")) {
                    costo = 3 + (ubicacion.getTiempoUso() > 1 ? (ubicacion.getTiempoUso() - 1) * 2 : 0);
                } else if (vehiculo.getVehiculo().equals("Scooter")) {
                    costo = 2 + (ubicacion.getTiempoUso() > 1 ? (ubicacion.getTiempoUso() - 1) * 1 : 0);
                } else if (vehiculo.getVehiculo().equals("Skateboard")) {
                    costo = 1 + (ubicacion.getTiempoUso() > 1 ? (ubicacion.getTiempoUso() - 1) * 0.5 : 0);
                }
        
                System.out.println("- ID: " + vehiculo.getId() + ", Tipo: " + vehiculo.getVehiculo() + ", Estado: " + estado + ", Costo estimado: $" + costo);
            }
        }

    }

}


