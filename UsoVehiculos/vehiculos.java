package UsoVehiculos;

import java.util.Scanner;
import java.util.HashSet;
public class Vehiculos {

    private int id;
    private String vehiculo;
    private Estacion lugar;
    private boolean reservado;
    private Usuario dueño;
    static HashSet<Vehiculos> vehiculos= new HashSet<>();
    static Scanner sc=new Scanner(System.in);

//contructor
Vehiculos(int id,String vehiculo,Estacion lugar){
this.id=id;
this.vehiculo=vehiculo;
this.lugar=lugar;
    }

//getter
public int getId(){
    return this.id;
}

public Usuario getDueño() {
    return dueño;
}

public void setDueño(Usuario dueño) {
    this.dueño = dueño;
}

public String getVehiculo(){
    return this.vehiculo;
}

public Estacion getLugar(){
    return this.lugar;
}

public boolean getEstado() {
    return this.reservado;
}


//setter

public void setId(int id){
    this.id=id;
}
public void setVehiculos(String vehiculos){
    this.vehiculo=vehiculos;
}

public void setLugar(Estacion lugar){
    this.lugar=lugar;
}

public void setEstado(boolean reservacion) {
    this.reservado=reservacion;
}
 
//methods
// Override equals and hashCode
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Vehiculos vehiculo = (Vehiculos) obj;
    return id == vehiculo.id;
}

@Override
public int hashCode() {
    return Integer.hashCode(id);
}
public static void Agregar() {
    System.out.print("\n¿Cuántos vehículos desea agregar? ");
    int cantidad = sc.nextInt();
    for (int i = 0; i < cantidad; i++) {
        System.out.println("\n--------------------------------------");
        System.out.print("Ingrese el ID del vehículo: ");
        int id = sc.nextInt();
        System.out.println("\nSeleccione el tipo de vehículo: ");
        System.out.print("1. Bicicleta\n2. Scooter\n3. Skateboard\nOpción: ");
        int tipo = sc.nextInt();
        String vehiculo = null;
       while(tipo<1 || tipo>3){
            System.out.println("\nOpcion no valida, por favor intente de nuevo.");
            tipo = sc.nextInt();
        }
        if(tipo==1){
            vehiculo="Bicicleta";
        }else if(tipo==2){
            vehiculo="Scooter";
        }else if(tipo==3){
            vehiculo="Skateboard";
        } 

        System.out.println("\n--------------------------------------");
        Estacion lugar = Estacion.escogaestacion();

        if (lugar.getCapacidad() <= 0 ) { 
            System.out.println("Capacidad máxima alcanzada en esta estación. Elija otra estación disponible.");
            System.out.println("--------------------------------------");
            return;
        }

        Vehiculos vehiculo1 = new Vehiculos(id, vehiculo, lugar);
        if (vehiculos.add(vehiculo1)) {
            lugar.getVehiculos().add(vehiculo1);
            System.out.println("Vehículo agregado con éxito.");
            System.out.println("--------------------------------------");
            lugar.setCapacidad(lugar.getCapacidad() - 1);
        } else {
            System.out.println("El vehículo con ID " + id + " ya existe y no se puede agregar.");
            System.out.println("--------------------------------------");
        }
    }
}

public static void Eliminar() {
    System.out.println("\n--------------------------------------");
    System.out.print("Ingrese el ID del vehículo a eliminar: ");
    int id = sc.nextInt();

    for (Vehiculos vehiculo : vehiculos) {
        if (vehiculo.getId() == id) {
            vehiculos.remove(vehiculo);
            vehiculo.getLugar().getVehiculos().remove(vehiculo); 
            vehiculo.getLugar().setCapacidad(vehiculo.getLugar().getCapacidad() + 1); 
            System.out.println("Vehículo con ID " + id + " eliminado con éxito.");
            System.out.println("--------------------------------------\n");
            return;
        }
    }

    System.out.println("No se encontró ningún vehículo con el ID " + id + ".");
    System.out.println("--------------------------------------");
}

public static void Mostrar(){
    for (Vehiculos vehiculo: vehiculos){
    System.out.println("ID: "+vehiculo.getId()+" Vehiculo: "+ vehiculo.getVehiculo()+" Estacion: "+vehiculo.getLugar().getUbicacion());
    }
}

}