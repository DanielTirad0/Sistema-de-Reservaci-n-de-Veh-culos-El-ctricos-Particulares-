package UsoVehiculos;

public class vehiculos {

    private int id;
    private String vehiculo;
    private String lugar;

//contructor
vehiculos(int id,String vehiculo,String lugar){
this.id=id;
this.vehiculo=vehiculo;
this.lugar=lugar;
    }

//getter
public int getId(){
    return id;
}

public String getVehiculo(){
    return vehiculo;
}

public String getLugar(){
    return lugar;
}

//setter

public void setId(int id){
    this.id=id;
}
public void setVehiculos(String vehiculos){
    this.vehiculo=vehiculos;
}

public void setLugar(String lugar){
    this.lugar=lugar;
}
//methods

}