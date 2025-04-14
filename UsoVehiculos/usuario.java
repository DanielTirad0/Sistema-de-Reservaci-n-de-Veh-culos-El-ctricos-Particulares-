package UsoVehiculos;

public class Usuario {
    private int numerodeestudinte;
    private String name;
    private String email;
    private String telefono;
    private int saldo;

    //contructor

    Usuario(int numerodeestudinte, String name, String email, String telefono, int saldo){
        this.numerodeestudinte=numerodeestudinte;
        this.name=name;
        this.email=email;
        this.telefono=telefono;
        this.saldo=saldo;
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
    public int getSaldo(){
        return this.saldo;
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
    public void setSaldo(int saldo){
        this.saldo=saldo; 
    }
    //methods
}
