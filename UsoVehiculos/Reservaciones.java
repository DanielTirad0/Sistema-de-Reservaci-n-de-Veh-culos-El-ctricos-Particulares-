package UsoVehiculos;

import java.util.Scanner;
import java.util.Stack;

import javax.swing.Action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Reservaciones {

    private String estado;
    private Estacion estacion;
    private Vehiculos vehiculo; 
    private Usuario usuario;
    private static Stack<Action> undoStack = new Stack<>(); 
    private double costoPorReservar;
    private double tiempo;
    private int horaDeReserva;
    private String diaDeReserva;
    private int horaDeFin;
    static Scanner input = new Scanner(System.in);
    private static HashMap<Usuario, Vehiculos> reservas = new HashMap<>();
    public static LinkedList<Reservaciones> historialDeReservaciones = new LinkedList<>();
    public static Queue<Reservaciones> listaDeEspera = new LinkedList<>();

    private static class Action {
        String type; 
        Reservaciones reservacion;

        Action(String type, Reservaciones reservacion) {
            this.type = type;
            this.reservacion = reservacion;
        }
    }

    public Reservaciones(String estado, Vehiculos vehiculo, Estacion estacion, Usuario usuario, double costoPorReservar, double tiempo, int horaDeReserva, String diaDeReserva) {
        this.horaDeReserva = horaDeReserva;  
        this.diaDeReserva = diaDeReserva;
        this.tiempo = tiempo;
        this.estacion = estacion;
        this.estado = estado;
        this.vehiculo = vehiculo;
        this.usuario = usuario;
        this.costoPorReservar = costoPorReservar;
        this.horaDeFin = horaDeReserva + (int) tiempo;
    }

    // Getters
    public String getEstado() {
        return estado;
    }

    public String getDiaDeReserva() {
        return diaDeReserva;
    }

    public int getHoraDeReserva() {
        return horaDeReserva;
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

    public int getHoraDeFin() {
        return horaDeFin;
    }

    public double getTiempo() {
        return tiempo;
    }
    //setters
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setDiaDeReserva(String diaDeReserva) {
        this.diaDeReserva = diaDeReserva;
    }
    public void setHoraDeReserva(int horaDeReserva) {
        this.horaDeReserva = horaDeReserva;
    }
    public void setVehiculo(Vehiculos vehiculo) {
        this.vehiculo = vehiculo;
    }
    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }
    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }
    public void setCostoPorReservar(double costoPorReservar) {
        this.costoPorReservar = costoPorReservar;
    }
    public void setHoraDeFin(int horaDeFin) {
        this.horaDeFin = horaDeFin;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
  
    // Methods
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
                if (reservacion.getUsuario().equals(persona) && reservacion.getEstado().equals("Reservado")) {
                    System.out.println("El usuario ya tiene una reservación activa. No puede reservar más de un vehículo al mismo tiempo.");
                    return;
                }
            }

            Estacion estacionSeleccionada = null;
            String diaDeReserva = "";
            int horaDeReserva = 0;

            while (true) {
                System.out.println("\n¿En cuál estación desea hacer la reserva?\n");
                estacionSeleccionada = Estacion.escogaestacion();

                try {
                    System.out.print("\nIntroduzca el día de su reservación (Lunes a Viernes): ");
                    diaDeReserva = input.nextLine();

                    if (!diaDeReserva.equalsIgnoreCase("Lunes") &&
                        !diaDeReserva.equalsIgnoreCase("Martes") &&
                        !diaDeReserva.equalsIgnoreCase("Miércoles") &&
                        !diaDeReserva.equalsIgnoreCase("Jueves") &&
                        !diaDeReserva.equalsIgnoreCase("Viernes")) {
                        System.out.println("Día no válido. Por favor, introduzca un día entre Lunes y Viernes.");
                        continue;
                    }

                    System.out.print("Introduzca la hora en la que comenzará su reservación (7:00 AM - 18:00 PM): ");
                    horaDeReserva = input.nextInt();
                    input.nextLine();

                    if (horaDeReserva < 7 || horaDeReserva > 18) {
                        System.out.println("Hora no válida. Por favor, introduzca una hora entre 7:00 AM y 6:00 PM.");
                        continue;
                    }

                } catch (Exception e) {
                    System.out.println("Por favor, introduzca un valor válido.");
                    input.nextLine();
                }

                Estacion.vehiculosEnLaEstacion(estacionSeleccionada, diaDeReserva, horaDeReserva);

                if (estacionSeleccionada.getVehiculos().isEmpty()) {
                    System.out.print("Desea ser añadido a la lista de espera? (Si/No): ");
                    String respuesta = input.nextLine();
                    if (respuesta.equalsIgnoreCase("Si")) {
                            Reservaciones reservacionEspera = new Reservaciones("En Espera", null, estacionSeleccionada, persona, 0, 0, horaDeReserva, diaDeReserva);
                            listaDeEspera.add(reservacionEspera);
                            System.out.println("Usted ha sido añadido a la lista de espera.");
                            return;
                    } else {
                        System.out.println("No se ha realizado ninguna reservación.");
                        return;
                    }
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
                    System.out.println("Por favor, introduzca un valor válido.");
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

            for (Reservaciones reservacion : historialDeReservaciones) {
                if (reservacion.getVehiculo().equals(vehiculoSeleccionado) &&
                    reservacion.getDiaDeReserva().equalsIgnoreCase(diaDeReserva) &&
                    reservacion.getEstado().equals("Reservado") &&
                    !(horaDeReserva >= reservacion.getHoraDeFin() || (horaDeReserva + tiempoUso) <= reservacion.getHoraDeReserva())) {
                    System.out.println("El vehículo ya está reservado el " + diaDeReserva + " de " + reservacion.getHoraDeReserva() + ":00 a " + reservacion.getHoraDeFin() + ":00.");
                    System.out.print("¿Desea ser añadido a la lista de espera? (Si/No): ");
                    String respuesta = input.nextLine();
                    if (respuesta.equalsIgnoreCase("Si")) {
                        Reservaciones reservacionEspera = new Reservaciones("En Espera", null, estacionSeleccionada, persona, 0, 0, horaDeReserva, diaDeReserva);
                        listaDeEspera.add(reservacionEspera);
                        System.out.println("Usted ha sido añadido a la lista de espera.");
                    } else {
                        System.out.println("No se ha realizado ninguna reservación.");
                    }
                    return;
                }
            }
  
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

            System.out.println("Reservación exitosa.");
            persona.setSaldo(persona.getSaldo() - costoPorReservar);

            Reservaciones reservacion = new Reservaciones("Reservado", vehiculoSeleccionado, estacionSeleccionada, persona, costoPorReservar, tiempoUso, horaDeReserva, diaDeReserva);
            historialDeReservaciones.add(reservacion);
            reservas.put(persona, vehiculoSeleccionado);
            undoStack.push(new Action("add", reservacion));
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado. Intente de nuevo.");
        }
    }

    
    public static void Undo() {
        if (undoStack.isEmpty()) {
            System.out.println("No hay acciones para deshacer.");
            return;
        }
    
        Action lastAction = undoStack.pop();
        Reservaciones reservacion = lastAction.reservacion;
    
        if (lastAction.type.equals("add")) {
            historialDeReservaciones.remove(reservacion);
            reservas.remove(reservacion.getUsuario());
            System.out.println("Se ha deshecho la última acción: Reservación añadida eliminada.");
        } else if (lastAction.type.equals("delete")) {
            historialDeReservaciones.add(reservacion);
            reservas.put(reservacion.getUsuario(), reservacion.getVehiculo());
            reservacion.getUsuario().setSaldo(reservacion.getUsuario().getSaldo() - reservacion.getCostoPorReservar());
            reservacion.estado = "Reservado";
            System.out.println("Se ha deshecho la última acción: Reservación eliminada restaurada.");
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
        System.out.println("Vehículo: " + reservacion.getVehiculo().getVehiculo() + " (ID: " + reservacion.getVehiculo().getId() + ")");
        System.out.println("Estado: " + reservacion.getEstado());
        System.out.println("Costo: $" + reservacion.getCostoPorReservar());
        System.out.println("Tiempo: " + reservacion.getTiempo() + " horas");
        System.out.println("Hora de Reserva: " + reservacion.getHoraDeReserva() + ":00");
        System.out.println("Hora de Fin: " + reservacion.getHoraDeFin() + ":00");
        System.out.println("Día de Reserva: " + reservacion.getDiaDeReserva());
    }

    System.out.println("--------------------------------------");
    }

    public static void Eliminar() {
        System.out.println("\n----- Vehículos reservados -----\n");
    
        if (reservas.isEmpty()) {
            System.out.print("No hay vehículos reservados actualmente.\n");
            return;
        }
    
        for (Usuario usuario : reservas.keySet()) {
            Vehiculos vehiculo = reservas.get(usuario);
            System.out.println("Usuario: " + usuario.getName() + ", Vehículo: " + vehiculo.getVehiculo() + ", ID: " + vehiculo.getId());
        }
    
        try {
            System.out.println("\n¿Cuál reservación desea eliminar?");
            System.out.print("Introduzca el ID: ");
            int idVehiculoEliminado = input.nextInt();
    
            Usuario usuarioAEliminar = null;
            Vehiculos vehiculoAEliminar = null;
            Reservaciones reservacionAEliminar = null;
    
            for (Usuario usuario : reservas.keySet()) {
                Vehiculos vehiculo = reservas.get(usuario);
                if (vehiculo.getId() == idVehiculoEliminado) {
                    usuarioAEliminar = usuario;
                    vehiculoAEliminar = vehiculo;
                    break;
                }
            }
    
            if (usuarioAEliminar != null && vehiculoAEliminar != null) {
                for (Reservaciones reservacion : historialDeReservaciones) {
                    if (reservacion.getUsuario().equals(usuarioAEliminar) && reservacion.getVehiculo().equals(vehiculoAEliminar)) {
                        reservacionAEliminar = reservacion;
                        reservacion.estado = "Cancelado";
                        double costoReservacion = reservacion.getCostoPorReservar();
                        usuarioAEliminar.setSaldo(usuarioAEliminar.getSaldo() + costoReservacion);
                        System.out.println("Se han reembolsado " + costoReservacion + " créditos al usuario " + usuarioAEliminar.getName() + ".");
                        break;
                    }
                }
    
                reservas.remove(usuarioAEliminar);
                historialDeReservaciones.remove(reservacionAEliminar);
                undoStack.push(new Action("delete", reservacionAEliminar));
    
                System.out.println("Reservación eliminada exitosamente. El vehículo ahora está disponible para reservar.");
            } else {
                System.out.println("No se encontró una reservación con el ID proporcionado.");
            }
    
        } catch (Exception e) {
            System.out.print("Introduzca un ID válido.\n");
        }
    }

    public static void mostrarListaDeEspera() {
        if (listaDeEspera.isEmpty()) {
            System.out.println("No hay usuarios en la lista de espera.");
            return;
        }

        System.out.println("----- Lista de Espera -----");

        for (Reservaciones reservacion : listaDeEspera) {
            System.out.println("--------------------------------------");
            System.out.println("Usuario: " + reservacion.getUsuario().getName());
            System.out.println("Número de Estudiante: " + reservacion.getUsuario().getNumeroDeEstudiante());
            System.out.println("Estación: " + reservacion.getEstacion().getUbicacion());
            System.out.println("Estado: " + reservacion.getEstado());
            System.out.println("Hora de Reserva: " + reservacion.getHoraDeReserva() + ":00");
            System.out.println("Día de Reserva: " + reservacion.getDiaDeReserva());
        }

        System.out.println("--------------------------------------");
    }
    
    public static void ModificaciondeReservacion() {
        Scanner sc = new Scanner(System.in);
    
        if (historialDeReservaciones.isEmpty()) {
            System.out.println("No hay reservaciones disponibles para modificar.");
            return;
        }
    
        System.out.println("\n----- Reservaciones Disponibles -----");
        
        mostrarTodasLasReservaciones();
    
        System.out.print("\nIngrese el ID del vehículo de la reservación que desea modificar: ");
        int idVehiculo = sc.nextInt();
        sc.nextLine(); 
    
        Reservaciones reservacionSeleccionada = null;
        for (Reservaciones reservacion : historialDeReservaciones) {
            if (reservacion.getVehiculo().getId() == idVehiculo) {
                reservacionSeleccionada = reservacion;
                break;
            }
        }
    
        if (reservacionSeleccionada == null) {
            System.out.println("No se encontró una reservación con el ID proporcionado.");
            return;
        }
    
        System.out.println("\n¿Qué desea modificar?");
        System.out.println("1. Vehículo");
        System.out.println("2. Día de la Reserva");
        System.out.println("3. Hora de Inicio");
        System.out.println("4. Tiempo de Uso");
        System.out.println("===============================");
        System.out.print("Su opción: ");
        int opcion = sc.nextInt();
        sc.nextLine(); 
    
        switch (opcion) {
            case 1:
                System.out.println("Vehículo actual: " + reservacionSeleccionada.getVehiculo().getVehiculo());
                System.out.print("Ingrese el ID del nuevo vehículo: ");
                int nuevoIdVehiculo = sc.nextInt();
                sc.nextLine();
    
                Vehiculos nuevoVehiculo = null;
                for (Vehiculos vehiculo : reservacionSeleccionada.getEstacion().getVehiculos()) {
                    if (vehiculo.getId() == nuevoIdVehiculo) {
                        nuevoVehiculo = vehiculo;
                        break;
                    }
                }
    
                if (nuevoVehiculo == null) {
                    System.out.println("No se encontró un vehículo con el ID proporcionado en esta estación.");
                    return;
                }
    
                reservacionSeleccionada.setVehiculo(nuevoVehiculo);
                System.out.println("Vehículo modificado con éxito.");
                break;
    
            case 2:
                System.out.println("Día actual: " + reservacionSeleccionada.getDiaDeReserva());
                System.out.print("Ingrese el nuevo día de la reserva (Lunes a Viernes): ");
                String nuevoDia = sc.nextLine();
    
                if (!nuevoDia.equalsIgnoreCase("Lunes") &&
                    !nuevoDia.equalsIgnoreCase("Martes") &&
                    !nuevoDia.equalsIgnoreCase("Miércoles") &&
                    !nuevoDia.equalsIgnoreCase("Jueves") &&
                    !nuevoDia.equalsIgnoreCase("Viernes")) {
                    System.out.println("Día no válido. Por favor, introduzca un día entre Lunes y Viernes.");
                    return;
                }
    
                reservacionSeleccionada.setDiaDeReserva(nuevoDia);
                System.out.println("Día de la reserva modificado con éxito.");
                break;
    
            case 3:
                System.out.println("Hora de inicio actual: " + reservacionSeleccionada.getHoraDeReserva() + ":00");
                System.out.print("Ingrese la nueva hora de inicio (7:00 AM - 18:00 PM): ");
                int nuevaHora = sc.nextInt();
                sc.nextLine();
    
                if (nuevaHora < 7 || nuevaHora > 18) {
                    System.out.println("Hora no válida. Por favor, introduzca una hora entre 7:00 AM y 6:00 PM.");
                    return;
                }
    
                reservacionSeleccionada.setHoraDeReserva(nuevaHora);
                System.out.println("Hora de inicio modificada con éxito.");
                break;
    
            case 4:
                System.out.println("Tiempo de uso actual: " + reservacionSeleccionada.getTiempo() + " horas");
                System.out.print("Ingrese el nuevo tiempo de uso (máximo 6 horas): ");
                double nuevoTiempo = sc.nextDouble();
                sc.nextLine();
    
                if (nuevoTiempo <= 0 || nuevoTiempo > 6) {
                    System.out.println("Tiempo no válido. Por favor, introduzca un tiempo entre 1 y 6 horas.");
                    return;
                }
    
                reservacionSeleccionada.setTiempo(nuevoTiempo);
                System.out.println("Tiempo de uso modificado con éxito.");
                break;
    
            default:
                System.out.println("Opción no válida.");
                return;
        }
        boolean conflicto = false;
        for (Reservaciones reservacion : historialDeReservaciones) {
            if (!reservacion.equals(reservacionSeleccionada) &&
                reservacion.getVehiculo().equals(reservacionSeleccionada.getVehiculo()) &&
                reservacion.getDiaDeReserva().equalsIgnoreCase(reservacionSeleccionada.getDiaDeReserva()) &&
                reservacion.getEstado().equals("Reservado") &&
                !(reservacionSeleccionada.getHoraDeReserva() >= reservacion.getHoraDeFin() ||
                  (reservacionSeleccionada.getHoraDeReserva() + reservacionSeleccionada.getTiempo()) <= reservacion.getHoraDeReserva())) {
                conflicto = true;
                break;
            }
        }
    
        if (conflicto) {
            System.out.println("El nuevo horario entra en conflicto con otra reservación.");
            System.out.print("¿Desea añadir esta reservación a la lista de espera? (Si/No): ");
            String respuesta = sc.nextLine();
    
            if (respuesta.equalsIgnoreCase("Si")) {
                reservacionSeleccionada.setEstado("En Espera");
                listaDeEspera.add(reservacionSeleccionada);
                System.out.println("La reservación ha sido añadida a la lista de espera.");
            } else {
                System.out.println("No se realizaron cambios en la reservación.");
            }
        } else {
            System.out.println("Reservación modificada con éxito.");
        }
    }

    public static void modificarListaDeEspera() {
        if (listaDeEspera.isEmpty()) {
            System.out.println("No hay usuarios en la lista de espera.");
            return;
        }

        mostrarListaDeEspera();

        System.out.print("\nIngrese el número de estudiante del usuario que desea modificar: ");
        int numeroDeEstudiante = input.nextInt();
        input.nextLine(); 

        Reservaciones reservacionSeleccionada = null;
        for (Reservaciones reservacion : listaDeEspera) {
            if (reservacion.getUsuario().getNumeroDeEstudiante() == numeroDeEstudiante) {
                reservacionSeleccionada = reservacion;
                break;
            }
        }

        if (reservacionSeleccionada == null) {
            System.out.println("No se encontró un usuario con el número de estudiante proporcionado en la lista de espera.");
            return;
        }

        System.out.println("Que desea Modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Numero de Estudiante");
        System.out.println("3. Estación");
        System.out.println("4. Hora de Reserva");
        System.out.println("5. Día de Reserva");
        System.out.println("===============================");
        System.out.print("Su opcion por favor: ");
        int opcionespecifica = input.nextInt();
        input.nextLine();

        if(opcionespecifica==1){
            System.out.println("Nombre de Usuario: " + reservacionSeleccionada.getUsuario().getName());
            System.out.print("Nombre a modificar: ");
            String name= input.nextLine();
            reservacionSeleccionada.getUsuario().setName(name);
            System.out.println("Nombre modificado con éxito.");

         } else if (opcionespecifica == 2) {
            System.out.println("Número de Estudiante actual: " + reservacionSeleccionada.getUsuario().getNumeroDeEstudiante());
            System.out.print("Número de Estudiante a modificar: ");
            int nuevoNumero = input.nextInt();
            boolean numeroExistente = false;
            for (Usuario usuario : Usuario.usuarios) {
                if (usuario.getNumeroDeEstudiante() == nuevoNumero) {
                    numeroExistente = true;
                    break;
                }
            }
        
            if (numeroExistente) {
                System.out.println("Error: El número de estudiante " + nuevoNumero + " ya está en uso. No se puede modificar.");
            } else {
                reservacionSeleccionada.getUsuario().setNumeroDeEstudiante(nuevoNumero);
                System.out.println("Número de estudiante modificado con éxito.");
            }
            
        } else if(opcionespecifica == 3) {
            System.out.println("Estación actual: " + reservacionSeleccionada.getEstacion().getUbicacion());
            System.out.println("\nEstaciones disponibles:");
            for (Estacion estacion : Estacion.estaciones) { 
                System.out.println("- " + estacion.getUbicacion());
            }
        
            System.out.print("Ingrese la nueva estación: ");
            String nuevaEstacion = input.nextLine();
        
            Estacion estacionSeleccionada = null;
            for (Estacion estacion : Estacion.estaciones) {
                if (estacion.getUbicacion().equalsIgnoreCase(nuevaEstacion)) {
                    estacionSeleccionada = estacion;
                    break;
                }
            }
        
            if (estacionSeleccionada == null) {
                System.out.println("La estación ingresada no es válida. Por favor, seleccione una estación de la lista.");
                return;
            }

            reservacionSeleccionada.setEstacion(estacionSeleccionada);
            System.out.println("Estación modificada con éxito.");

        } else if(opcionespecifica==4){
            System.out.println("Hora de Reserva actual: " + reservacionSeleccionada.getHoraDeReserva() + ":00");
            System.out.print("Ingrese la nueva hora de reserva (7:00 AM - 18:00 PM): ");
            int nuevaHora = input.nextInt();
            input.nextLine();
    
            if (nuevaHora < 7 || nuevaHora > 18) {
                System.out.println("Hora no válida. Por favor, introduzca una hora entre 7:00 AM y 6:00 PM.");
                return;
            }
    
            reservacionSeleccionada.setHoraDeReserva(nuevaHora);
            System.out.println("Hora de reserva modificada con éxito.");
        } else if(opcionespecifica==5){
            System.out.println("Día de Reserva actual: " + reservacionSeleccionada.getDiaDeReserva());
            System.out.print("Ingrese el nuevo día de reserva (Lunes a Viernes): ");
            String nuevoDia = input.nextLine();
    
            if (!nuevoDia.equalsIgnoreCase("Lunes") &&
                !nuevoDia.equalsIgnoreCase("Martes") &&
                !nuevoDia.equalsIgnoreCase("Miércoles") &&
                !nuevoDia.equalsIgnoreCase("Jueves") &&
                !nuevoDia.equalsIgnoreCase("Viernes")) {
                System.out.println("Día no válido. Por favor, introduzca un día entre Lunes y Viernes.");
                return;
            }
    
            reservacionSeleccionada.setDiaDeReserva(nuevoDia);
            System.out.println("Día de reserva modificado con éxito.");
        } else {
            System.out.println("Opción no válida.");
        }

       
    }

}