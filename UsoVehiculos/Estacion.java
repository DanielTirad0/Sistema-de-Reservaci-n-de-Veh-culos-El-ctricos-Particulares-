package UsoVehiculos;

import java.util.Set;
import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;


public class Estacion {

    private String ubicacion;
    private int capacidad;   
    private Set<Vehiculos> vehiculos;
    private List<Reservaciones> reservaciones;

    public Estacion(String ubicacion, int capacidad) {

        this.ubicacion= ubicacion;
        this.capacidad= capacidad;
        this.vehiculos= new HashSet<>();
        this.reservaciones= new LinkedList<>();
    }

    //Getters

    public int getCapacidad() {
        return capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Set<Vehiculos> getVehiculos() {
        return vehiculos;
    }

    public List<Reservaciones> getReservaciones() {
        return reservaciones;
    }
}