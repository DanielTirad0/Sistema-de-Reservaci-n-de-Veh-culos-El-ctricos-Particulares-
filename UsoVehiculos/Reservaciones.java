package UsoVehiculos;

public class Reservaciones {

    private String estado;
    private Estacion estacion;
    private Vehiculos vehiculo; 
    private Usuario usuario;
    private double costo;

    public Reservaciones(String estado, Vehiculos vehiculo, Estacion estacion, Usuario usuario, double costo) {

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
    


}