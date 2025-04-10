package UsoVehiculos;

import java.util.Scanner;
public class main {
    static boolean finalizado= false;
    static int press=0;
    static Scanner sc= new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("Bienvenido a la estacion de reservacion de vehiculos");
        while(finalizado!=true){
  
     System.out.println(" por favor elija una opcion: ");
     System.out.println("1. Agregar o Eliminar Vehiculos");
     System.out.println("2. Agregar o Eliminar Usuarios");
     System.out.println("3. Agregar o Eliminar Reservaciones");
     System.out.println("4. Agregar o Eliminar Usuarios en la Lista de Espera");
     System.out.println("5. Mostrar Reservaciones");
     System.out.println("6. Mostrar Usuarios");
     System.out.println("7. Mostrar Vehiculos");
     System.out.println("8. Mostrar Lista de Espera");
     System.out.println("9. Modificaciones");
     System.out.println("10. Finalizar");
     press= sc.nextInt();
    
     if(press==1){
      int press2=0; 
        System.out.println("1. Agregar vehiculo");
        System.out.println("2. Eliminar vehiculo");
        press2= sc.nextInt();
         if(press2==1){
            Vehiculos.Agregar();
         }
         else if(press2==2){
            Vehiculos.Eliminar();
         }
         else{
            System.out.println("Opcion no valida, por favor intente de nuevo.");
         }
     }
     if(press==7){
        Vehiculos.Mostrar();
     }
     if (press==10){
        finalizado=true;
        System.out.println("Gracias por usar nuestro servicio, hasta luego.");
        }
    }
}
}