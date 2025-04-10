package UsoVehiculos;

public class Reservaciones {

    private String estado;
    private Estacion estacion;
    private Vehiculos vehiculo; 
    private Usuario usuario;
    private double costo;
    private double tiempo;

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
}