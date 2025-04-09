package UsoVehiculos;

public class Usuario {
    private int numerodeestudinte;
    private String name;
    private String email;
    private String telefono;

    //contructor

    Usuario(int numerodeestudinte, String name, String email, String telefono){
        this.numerodeestudinte=numerodeestudinte;
        this.name=name;
        this.email=email;
        this.telefono=telefono;
    }

    //getters

    public int getNumeroDeEstudiante(){
    return this.numerodeestudinte;
    }
    public String getName(){
    return this.name;
    }
    public String getEmail(){
    return this.email;
    }
    public String getTelefono(){
    return this.telefono;
    }
    //setters
    public void setNumeroDeEstudiante(int id){
        this.numerodeestudinte=id; 
    }
    public void setName(String name){
        this.name=name; 
    }
    public void setEmail(String email){
        this.email=email; 
    }
    public void setTelefono(String telefono){
        this.telefono=telefono; 
    }
    //methods
}
