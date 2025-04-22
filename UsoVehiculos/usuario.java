package UsoVehiculos;
import java.util.HashSet;
import java.util.Scanner;

public class Usuario {
    private int numerodeestudiante;
    private String name;
    private String email;
    private String telefono;
    private double saldo;
    static HashSet<Usuario> usuarios = new HashSet();

    //contructor

    Usuario(int numerodeestudiante, String name, String email, String telefono, double saldo){
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
    public double getSaldo(){
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
    public void setSaldo(double saldo){
        this.saldo=saldo; 
    }
    //methods

    static void MostrarUsuario(){
        if(usuarios.isEmpty()){ System.out.println("No hay usuarios disponibles");}
        for(Usuario usuario:usuarios){
        System.out.println(usuario.getEmail());
        System.out.println(usuario.getName());
        System.out.println(usuario.getNumeroDeEstudiante());
        System.out.println(usuario.getTelefono());
        System.out.println(usuario.getSaldo());
        System.out.println("=======================================");
        }
        
      }  

      static Usuario AgregarUsuario() {
        Usuario usuario = null;
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("¿Cuántos usuarios desea agregar? ");
            int add = sc.nextInt();
            sc.nextLine();
    
            for (int i = 0; i < add; i++) {
                System.out.print("\nIngrese el número de estudiante: ");
                int numerodeestudiante = sc.nextInt();
                sc.nextLine();
    
                System.out.print("Ingrese el nombre del usuario: ");
                String name = sc.nextLine();
    
                System.out.print("Ingrese el email del usuario: ");
                String email = sc.nextLine();
    
                System.out.print("Ingrese el teléfono del usuario: ");
                String telefono = sc.nextLine();
    
                System.out.print("Ingrese el saldo del usuario: ");
                int saldo = sc.nextInt();
                sc.nextLine();
    
                usuario = new Usuario(numerodeestudiante, name, email, telefono, saldo);
                if (usuarios.add(usuario)) {
                    System.out.println("Usuario agregado con éxito.");
                } else {
                    System.out.println("El usuario con número de estudiante " + numerodeestudiante + " ya existe.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al agregar el usuario. Por favor, intente de nuevo.");
        }
    
        return usuario;
    }
    

    static void EliminarUsuario() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nUsuarios disponibles para eliminar:");

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios disponibles para eliminar.");
            return;
        }

        for (Usuario usuario : usuarios) {
            System.out.println("Número de estudiante: " + usuario.getNumeroDeEstudiante() + ", Nombre: " + usuario.getName());
        }

        try {
            System.out.print("\nIngrese el número de estudiante del usuario a eliminar: ");
            int numerodeestudiante = sc.nextInt();
            Usuario usuarioAEliminar = null;
    
            for (Usuario usuario : usuarios) {
                if (usuario.getNumeroDeEstudiante() == numerodeestudiante) {
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
        } catch (Exception e) {
            System.out.println("Error al eliminar el usuario. Intente de nuevo.");
        }
    }

        static void ModificaciondeUsuario(){
            Scanner sc = new Scanner(System.in);
            try{
                System.out.println("Mostrando Usuarios Para Modificar");
                MostrarUsuario();
         System.out.print("Por favor introduzca el numero de estudiante tal y como se agrego:");
         int numerodeestudiante = sc.nextInt(); 
         Usuario user=null;
         for (Usuario usuario : usuarios) {
            if (usuario.getNumeroDeEstudiante() == numerodeestudiante) {
                user = usuario;
                break;
            }
        }
        
    
        System.out.println("Que desea Modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Numero de Estudiante");
        System.out.println("3. Email");
        System.out.println("4. Telefono");
        System.out.println("5. Saldo");
        System.out.println("===============================");
        System.out.print("su opcion porfavor:");
        int opcionespecifica = sc.nextInt();
        sc.nextLine();
        if(opcionespecifica==1){
            System.out.println("Nombre de Usuario: " + user.getName());
            System.out.println("Nombre a modificar: ");
            String name= sc.nextLine();
            user.setName(name);
            System.out.println("Nombre modificado con exito");
         }
         else if (opcionespecifica == 2) {
            System.out.println("Número de Estudiante actual: " + user.getNumeroDeEstudiante());
            System.out.print("Número de Estudiante a modificar: ");
            int nuevoNumero = sc.nextInt();
            boolean numeroExistente = false;
            for (Usuario usuario : usuarios) {
                if (usuario.getNumeroDeEstudiante() == nuevoNumero) {
                    numeroExistente = true;
                    break;
                }
            }
        
            if (numeroExistente) {
                System.out.println("Error: El número de estudiante " + nuevoNumero + " ya está en uso. No se puede modificar.");
            } else {
                user.setNumeroDeEstudiante(nuevoNumero);
                System.out.println("Número de Estudiante modificado con éxito.");
            }
        }
            else if(opcionespecifica==3){
                System.out.println("Email de Usuario: " + user.getEmail());
                System.out.print("Email a modificar: ");
                String email= sc.nextLine();
                user.setEmail(email);
                System.out.println("Email modificado con exito");
            }
            else if(opcionespecifica==4){
                System.out.println("Telefono de Usuario: " + user.getTelefono());
                System.out.print("Telefono a modificar: ");
                String telefono= sc.nextLine();
                user.setTelefono(telefono);
                System.out.println("Telefono modificado con exito");
            }
            else if(opcionespecifica==5){
                System.out.println("Saldo de Usuario: " + user.getSaldo());
                System.out.print("Saldo a modificar: ");
                double saldo= sc.nextDouble();
                user.setSaldo(saldo);
                System.out.println("Saldo modificado con exito");
            }
        } 
        catch (Exception e) {
            System.out.println("Error al modificar el usuario. Intente de nuevo.");
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
    

