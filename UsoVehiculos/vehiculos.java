package UsoVehiculos;

import java.util.Scanner;
import java.util.HashSet;
public class Vehiculos {

    private int id;
    private String vehiculo;
    private Estacion lugar;
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

public String getVehiculo(){
    return this.vehiculo;
}

public Estacion getLugar(){
    return this.lugar;
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
 
//methods
public static void Agregar() {
    System.out.println("¿Cuántos vehículos desea agregar?");
    int cantidad = sc.nextInt();
    for (int i = 0; i < cantidad; i++) {
        System.out.println("Ingrese el ID del vehículo: ");
        int id = sc.nextInt();
        System.out.println("Ingrese el nombre del vehículo: ");
        String vehiculo = sc.next();
        System.out.println("Ingrese la estación del vehículo: ");
        String estacion = sc.next();
        Estacion lugar = new Estacion(estacion, 10);
        Vehiculos vehiculo1 = new Vehiculos(id, vehiculo, lugar);
        if (vehiculos.add(vehiculo1)) {
            System.out.println("Vehículo agregado con éxito.");
        } else {
            System.out.println("El vehículo con ID " + id + " ya existe y no se puede agregar.");
        }
    }
}

public static void Eliminar(){
    System.out.println("Ingrese el ID del vehiculo a eliminar: ");
    int id= sc.nextInt();
    for (Vehiculos vehiculo: vehiculos){
        if (vehiculo.getId()==id){
            vehiculos.remove(vehiculo);
            System.out.println("Vehiculo eliminado con exito");
            return;
        }
    }
    System.out.println("Vehiculo no encontrado");
}
public static void Mostrar(){
    for (Vehiculos vehiculo: vehiculos){
        System.out.println("ID: "+vehiculo.getId()+" Vehiculo: "+vehiculo.getVehiculo()+" Estacion: "+vehiculo.getLugar().getUbicacion());
    }
}
}