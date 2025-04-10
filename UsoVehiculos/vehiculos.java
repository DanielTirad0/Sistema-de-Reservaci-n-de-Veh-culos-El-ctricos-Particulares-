package UsoVehiculos;

public class Vehiculos {

    private int id;
    private String vehiculo;
    private Estacion lugar;

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

}