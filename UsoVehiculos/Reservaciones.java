package UsoVehiculos;

import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;

public class Reservaciones {

    private String estado;
    private Estacion estacion;
    private Vehiculos vehiculo; 
    private Usuario usuario;
    private double costoPorReservar;
    private double tiempo;
    static Scanner input= new Scanner(System.in);
    private static HashMap<Usuario, Vehiculos> reservas = new HashMap<>();
    private static LinkedList<Reservaciones> historialDeReservaciones = new LinkedList<>();

    public Reservaciones(String estado, Vehiculos vehiculo, Estacion estacion, Usuario usuario, double costoPorReservar, double tiempo) {
        this.tiempo= tiempo;
        this.estacion= estacion;
        this.estado= estado;
        this.vehiculo= vehiculo;
        this.usuario= usuario;
        this.costoPorReservar= costoPorReservar;
        }

    //Getters

    public String getEstado() {
        return estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public double getCostoPorReservar() {
        return costoPorReservar;
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
        System.out.println("Costo: "+ reservacion.getCostoPorReservar());
        System.out.println("Tiempo: "+ reservacion.getTiempo());
        
    }

    public static void Agregar() {
        try {
            System.out.println("\n----- Reservación de Vehículos -----\n");
    
            System.out.println("Usuarios disponibles:");
            if (Usuario.usuarios.isEmpty()) {
                System.out.println("No hay usuarios registrados. Por favor, registre un usuario antes de hacer una reservación.");
                return;
            }
            for (Usuario usuario : Usuario.usuarios) {
                System.out.println("Número de Estudiante: " + usuario.getNumeroDeEstudiante() + ", Nombre: " + usuario.getName());
            }
    
            System.out.print("\nIngrese el número de estudiante del usuario que hará la reservación: ");
            int numeroDeEstudiante = input.nextInt();
            input.nextLine(); 
    
            Usuario persona = null;
            for (Usuario usuario : Usuario.usuarios) {
                if (usuario.getNumeroDeEstudiante() == numeroDeEstudiante) {
                    persona = usuario;
                    break;
                }
            }
    
            if (persona == null) {
                System.out.println("No se encontró un usuario con el número de estudiante proporcionado.");
                return;
            }

            for (Reservaciones reservacion : historialDeReservaciones) {
                if (reservacion.getUsuario().equals(persona) && reservacion.getEstado().equals("Activa")) {
                    System.out.println("El usuario ya tiene una reservación activa. No puede reservar más de un vehículo al mismo tiempo.");
                    return;
                }
            }
    
    
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
            
            double tiempoUso = estacionSeleccionada.getTiempoUso();  

            double costoPorReservar = 0;
            
            if (vehiculoSeleccionado.getVehiculo().equals("Bicicleta")) {
                costoPorReservar = 3 + (tiempoUso > 1 ? (tiempoUso - 1) * 2 : 0);
            } else if (vehiculoSeleccionado.getVehiculo().equals("Scooter")) {
                costoPorReservar = 2 + (tiempoUso > 1 ? (tiempoUso - 1) * 1 : 0);
            } else if (vehiculoSeleccionado.getVehiculo().equals("Skateboard")) {
                costoPorReservar = 1 + (tiempoUso > 1 ? (tiempoUso - 1) * 0.5 : 0);
            }

            if (persona.getSaldo() < costoPorReservar) {
                System.out.println("El usuario no tiene suficiente saldo para realizar esta reservación.");
                System.out.println("Saldo disponible: " + persona.getSaldo() + " créditos.");
                System.out.println("Costo de la reservación: " + costoPorReservar + " créditos.");
                return;
            } else {
                System.out.println("El costo total de la reservación es: " + costoPorReservar + " créditos.");
            }

            if (!vehiculoSeleccionado.getEstado()) {
                vehiculoSeleccionado.setEstado(true);
                System.out.println("Reservación exitosa.");
                persona.setSaldo(persona.getSaldo() - costoPorReservar);

                Reservaciones reservacion = new Reservaciones("Activa", vehiculoSeleccionado, estacionSeleccionada, persona, costoPorReservar, tiempoUso);
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
        System.out.println("Costo: $" + reservacion.getCostoPorReservar());
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