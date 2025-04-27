package UsoVehiculos;

import java.util.Set;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.HashSet;


public class Estacion {

    // Atributos 
    private String ubicacion;
    private int capacidad;   
    private Set<Vehiculos> vehiculos;
    private List<Reservaciones> reservaciones;
    static double costo;
    private double tiempoUso;
    static HashSet<Estacion> estaciones= new HashSet<>();
    static Scanner input = new Scanner(System.in);

    // Constructor
    public Estacion(String ubicacion, int capacidad) {

        this.ubicacion= ubicacion;
        this.capacidad= capacidad;
        this.vehiculos= new HashSet<>();
        this.reservaciones= new LinkedList<>();
    
    }

    // Getters
    public double getTiempoUso() {
        return tiempoUso;
    }

    public int getCapacidad() {
        return this.capacidad;
    }

    public int getVehiculosActuales() {
        return this.vehiculos.size(); 
    }
    public int getEspaciosDisponibles() {
        return this.capacidad - this.vehiculos.size(); 
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
    
    // Setters
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setTiempoUso(double tiempoUso) {
        this.tiempoUso = tiempoUso;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    // Agregar estaciones predeterminadas
    static {
        estaciones.add(new Estacion("Edificio Stefani", 40));
        estaciones.add(new Estacion("Centro de Estudiantes", 90));
        estaciones.add(new Estacion("Edificio de Biología", 35));
        estaciones.add(new Estacion("Edificio Ingeniería Química", 45));
        estaciones.add(new Estacion("Edificio de Administración de Empresas", 45));
    }
    
    // Método para agregar vehículo el parametro es el vehiculo a agregar
    public boolean agregarVehiculo(Vehiculos vehiculo) {
        if (vehiculos.size() < capacidad) {
            vehiculos.add(vehiculo);
            return true; 
        } else {
            System.out.println("No hay espacio disponible en la estación " + ubicacion + ".");
            return false; 
        }
    }

    // Método para eliminar	vehiculo el parametro es el vehiculo a eliminar
    public boolean removerVehiculo(Vehiculos vehiculo) {
        if (vehiculos.remove(vehiculo)) {
            return true; 
        } else {
            System.out.println("El vehículo no se encuentra en la estación " + ubicacion + ".");
            return false; 
        }
    }

    // Método para preguntar al usuario por una estación y devolverla
    public static Estacion escogaestacion() {
        Scanner sc = new Scanner(System.in);
        int index = 1;
        System.out.println("Por favor, elija una estación:");
        for (Estacion estacion : estaciones) {
            System.out.println(index + ". " + estacion.getUbicacion() + " (Capacidad: " + estacion.getCapacidad() +
                    ", Espacios disponibles: " + estacion.getEspaciosDisponibles() + ")");
            index++;
        }

        System.out.print("\nOpción: ");
        int choice = sc.nextInt();
        while (choice < 1 || choice > estaciones.size()) {
            System.out.println("Opción no válida. Intente de nuevo.");
            System.out.print("Opción: ");
            choice = sc.nextInt();
        }
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

    // Método para mostrar los vehículos disponibles en la estación	seleccionada
    // Los parametros son la estación, el día y la hora de consulta
    // con el motivo de determinar si el vehiculo esta reservado o no.
    public static void vehiculosEnLaEstacion(Estacion ubicacion, String diaConsulta, int horaConsulta) {
        Set<Vehiculos> vehiculos = ubicacion.getVehiculos();

        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos disponibles en esta estación.");
            return;
        }
        
        // Preguntar al usuario el tiempo estimado de uso
        // y validar que no sea mayor a 6 horas
        while (true) {
            try {
                System.out.print("Ingrese el tiempo estimado de uso en horas (máximo 6): ");
                double tiempo = input.nextDouble();
                System.out.println();
                if (tiempo > 6) {
                    System.out.println("El tiempo de uso no puede ser mayor a 6 horas.");
                    continue;
                } else if (tiempo <= 0) {
                    System.out.println("El tiempo de uso no puede ser negativo o cero.");
                    continue;
                }

                // Asignar el tiempo de uso a la estación
                ubicacion.setTiempoUso(tiempo);
                break;
            } catch (Exception e) {
                System.out.println("Por favor, introduzca un número válido.");
                input.nextLine();
            }
        }

        // Verificar si el vehículo está reservado o no
        for (Vehiculos vehiculo : vehiculos) {
            boolean reservado = false;
            for (Reservaciones reservacion : Reservaciones.historialDeReservaciones) {
                if (reservacion.getVehiculo().equals(vehiculo) &&
                    reservacion.getDiaDeReserva().equalsIgnoreCase(diaConsulta) &&
                    reservacion.getEstado().equals("Reservado") &&
                    !(horaConsulta >= reservacion.getHoraDeFin() || (horaConsulta + ubicacion.getTiempoUso()) <= reservacion.getHoraDeReserva())) {
                    // Si el vehículo está reservado en la misma fecha y hora
                    // se marca como reservado
                    reservado = true;
                    break;
                }
            }
            String estado = reservado ? "Reservado" : "Disponible";
        
            // Calcular el costo estimado según el tipo de vehículo y el tiempo de uso
            double costo = 0;
            if (vehiculo.getVehiculo().equals("Bicicleta")) {
                costo = 3 + (ubicacion.getTiempoUso() > 1 ? (ubicacion.getTiempoUso() - 1) * 2 : 0);
            } else if (vehiculo.getVehiculo().equals("Scooter")) {
                costo = 2 + (ubicacion.getTiempoUso() > 1 ? (ubicacion.getTiempoUso() - 1) * 1 : 0);
            } else if (vehiculo.getVehiculo().equals("Skateboard")) {
                costo = 1 + (ubicacion.getTiempoUso() > 1 ? (ubicacion.getTiempoUso() - 1) * 0.5 : 0);
            }
        
            System.out.println("- ID: " + vehiculo.getId() + ", Tipo: " + vehiculo.getVehiculo() +
                ", Estado: " + estado + ", Costo estimado: $" + costo);
        }
    }

}


