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
        try {
            System.out.print("\nIntroduzca su nombre: ");
            String name = input.nextLine();
    
            int numEstudiante = 0;
            while (true) {
                try {
                    System.out.print("Introduzca su número de estudiante: ");
                    numEstudiante = input.nextInt();
                    input.nextLine();
                    break;
                } catch (Exception e) {
                    System.out.println("Por favor, introduzca un número válido.");
                    input.nextLine();
                }
            }
    
            System.out.print("Introduzca su email: ");
            String email = input.nextLine();
    
            System.out.print("Introduzca su número de teléfono: ");
            String numTelefono = input.nextLine();
    
            Usuario persona = new Usuario(numEstudiante, name, email, numTelefono, 10);
    
            boolean todasVacias = true;
            for (Estacion estacion : Estacion.estaciones) {
                if (!estacion.getVehiculos().isEmpty()) {
                    todasVacias = false;
                    break;
                }
            }
    
            if (todasVacias) {
                System.out.println("No hay vehículos disponibles en ninguna estación. Intente más tarde.");
                return; 
            }
    
            Estacion estacionSeleccionada = null;
    
            while (true) {
                System.out.println("\n¿En cuál estación desea hacer la reserva?\n");
                estacionSeleccionada = Estacion.escogaestacion();
    
                Estacion.vehiculosEnLaEstacion(estacionSeleccionada);
    
                if (estacionSeleccionada.getVehiculos().isEmpty()) {
                    System.out.println("No hay vehículos disponibles en esta estación. Intente con otra estación.");
                } else {
                    break; 
                }
            }
    
            int idSeleccionado = 0;
            while (true) {
                try {
                    System.out.print("\nIntroduzca el ID del vehículo que desea: ");
                    idSeleccionado = input.nextInt();
                    input.nextLine();
                    break;
                } catch (Exception e) {
                    System.out.println("Por favor, introduzca un número válido.");
                    input.nextLine();
                }
            }
    
            Vehiculos vehiculoSeleccionado = null;
            for (Vehiculos vehiculo : estacionSeleccionada.getVehiculos()) {
                if (vehiculo.getId() == idSeleccionado) {
                    vehiculoSeleccionado = vehiculo;
                    break;
                }
            }
    
            if (vehiculoSeleccionado == null) {
                System.out.println("Vehículo no encontrado en esta estación.");
                return;
            }
    
            if (!vehiculoSeleccionado.getEstado()) {
                vehiculoSeleccionado.setEstado(true);
                System.out.println("Reservación exitosa.");
                Reservaciones reservacion = new Reservaciones("Pendiente", vehiculoSeleccionado, estacionSeleccionada, persona, 0.0, 0.0);
                historialDeReservaciones.add(reservacion);
                reservas.put(persona, vehiculoSeleccionado);
            } else {
                System.out.println("El vehículo seleccionado ya ha sido reservado.");
            }
    
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado. Intente de nuevo.");
            input.nextLine();
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

    public static void Eliminar() {

        System.out.println("\n----- Vehículos reservados -----\n");

        if (reservas.isEmpty()) {
            System.out.print("No hay vehículos reservados actualmente.\n");
        }

        else {
            for (Usuario usuario : reservas.keySet()) {
                Vehiculos vehiculos = reservas.get(usuario);
                System.out.println("Usuario: " + usuario.getName() + ", Vehículo: " + vehiculos.getVehiculo() + ", ID: " + vehiculos.getId());
            }

            try {
                System.out.println("\n¿Cuál reservación desea eliminar?");
                System.out.print("Introduzca el ID: ");
                int idVehiculoEliminado= input.nextInt();

                Usuario usuarioAEliminar = null;
                Vehiculos vehiculoActualizado = null;
                for (Usuario usuario : reservas.keySet()) {
                    Vehiculos vehiculo = reservas.get(usuario);
                    if (vehiculo.getId() == idVehiculoEliminado) {
                        usuarioAEliminar = usuario;
                        vehiculoActualizado	= vehiculo;
                        break;
                    }
                }

                if (usuarioAEliminar != null && vehiculoActualizado != null) {
                    vehiculoActualizado.setEstado(false);
                    for (Reservaciones reservacion : historialDeReservaciones) {
                        if (reservacion.getUsuario().equals(usuarioAEliminar) && reservacion.getVehiculo().equals(vehiculoActualizado)) {
                            reservacion.estado = "Cancelado";
                            break;
                        }
                    }

                    reservas.remove(usuarioAEliminar);
                    System.out.println("Reservación eliminada exitosamente. El vehículo ahora está disponible para reservar.");
                } else {
                    System.out.println("No se encontró una reservación con el ID proporcionado.");
                }
    
            }
    
            catch (Exception e) {
                System.out.print("Introduzca un ID válido.\n");
            }
        }

    }

}