package UsoVehiculos;
import java.util.HashSet;
import java.util.Scanner;

public class Usuario {
    private int numerodeestudiante;
    private String name;
    private String email;
    private String telefono;
    private int saldo;
    static HashSet<Usuario> usuarios = new HashSet();

    //contructor

    Usuario(int numerodeestudiante, String name, String email, String telefono, int saldo){
        this.numerodeestudiante=numerodeestudiante;
        this.name=name;
        this.email=email;
        this.telefono=telefono;
        this.saldo=saldo;
    }

    //getters

    public int getNumeroDeEstudiante(){
    return this.numerodeestudiante;
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
        this.numerodeestudiante=id; 
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

    static void MostrarUsuario(){
        if(usuarios.isEmpty()){ System.out.println("no hay usuarios disponibles");}
        for(Usuario usuario:usuarios){
        System.out.println(usuario.getEmail());
        System.out.println(usuario.getName());
        System.out.println(usuario.getNumeroDeEstudiante());
        System.out.println(usuario.getTelefono());
        System.out.println(usuario.getSaldo());
        System.out.println("=======================================");
        }
        
      }  

      static void AgregarUsuario() {
        int add = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cuántos usuarios desea agregar?");
        add = sc.nextInt();
        for (int i = 0; i < add; i++) {
            System.out.println("Ingrese el número de estudiante:");
            int numerodeestudinte = sc.nextInt();
            sc.nextLine();
            System.out.println("Ingrese el nombre del usuario:");
            String name = sc.nextLine();
            System.out.println("Ingrese el email del usuario:");
            String email = sc.nextLine();
            System.out.println("Ingrese el teléfono del usuario:");
            String telefono = sc.nextLine();
            System.out.println("Ingrese el saldo del usuario:");
            int saldo = sc.nextInt();
    
            Usuario usuario = new Usuario(numerodeestudinte, name, email, telefono, saldo);
            if (usuarios.add(usuario)) {
                System.out.println("Usuario agregado con éxito.");
            } else {
                System.out.println("El usuario con número de estudiante " + numerodeestudinte + " ya existe.");
            }
        }
    }

    static void EliminarUsuario(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el número de estudiante del usuario a eliminar:");
        int numerodeestudinte = sc.nextInt();
        Usuario usuarioAEliminar = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getNumeroDeEstudiante() == numerodeestudinte) {
                usuarioAEliminar = usuario;
                break;
            }
        }
        if (usuarioAEliminar != null) {
            usuarios.remove(usuarioAEliminar);
            System.out.println("Usuario eliminado con éxito.");
        } else {
            System.out.println("No se encontró un usuario con ese número de estudiante.");
        }
    }
        // override equals and hashcode
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Usuario usuario = (Usuario) obj;
            return numerodeestudiante == usuario.numerodeestudiante; 
        }
        
        @Override
        public int hashCode() {
            return Integer.hashCode(numerodeestudiante); 
        }
      }
    

