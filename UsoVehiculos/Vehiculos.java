package UsoVehiculos;

import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
public class Vehiculos {

    private int id;
    private String vehiculo;
    private Estacion lugar;
    private String ownerName; 
    private static HashMap<String, Integer> ownerVehicleCount = new HashMap<>(); 
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

public String getOwnerName() {
    return ownerName;
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
public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
}
public void setVehiculos(String vehiculos){
    this.vehiculo=vehiculos;
}

public void setLugar(Estacion lugar){
    this.lugar=lugar;
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
    System.out.print("¿Cuántos vehículos desea agregar? ");
    int cantidad = sc.nextInt();
    for (int i = 0; i < cantidad; i++) {
        System.out.println("--------------------------------------");
        System.out.print("Ingrese el ID del vehículo: ");
        int id;
        try {
            id = sc.nextInt();
            if (id <= 0) {
                System.out.println("ID inválido. Debe ser un número positivo.");
                return;
            }
        } catch (Exception e) {
            System.out.println("ID inválido. Debe ser un número entero.");
            sc.next();
            return;
        }

        System.out.print("Ingrese el nombre del dueño del vehículo: ");
        sc.nextLine(); 
        String ownerName = sc.nextLine();

        
        int currentCount = ownerVehicleCount.getOrDefault(ownerName, 0);
        if (currentCount >= 2) {
            System.out.println("El dueño " + ownerName + " ya tiene dos vehículos asignados. No puede tener más.");
            return;
        }

        int tipo = 0;
        String vehiculo = null;
        while (true) {
            try {
                System.out.println("\nSeleccione el tipo de vehículo:");
                System.out.println("1. Bicicleta");
                System.out.println("2. Scooter");
                System.out.println("3. Skateboard");
                System.out.print("Opción: ");
                tipo = sc.nextInt();
                sc.nextLine();

                if (tipo < 1 || tipo > 3) {
                    System.out.println("Opción no válida, por favor intente de nuevo.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Entrada no válida. Por favor, introduzca un número entre 1 y 3.");
                sc.nextLine();
            }
        }

        switch (tipo) {
            case 1:
                vehiculo = "Bicicleta";
                break;
            case 2:
                vehiculo = "Scooter";
                break;
            case 3:
                vehiculo = "Skateboard";
                break;
        }

        System.out.println("\n--------------------------------------");
        Estacion lugar = Estacion.escogaestacion();

        if (lugar == null) {
            System.out.println("No se seleccionó una estación válida.");
            return;
        }

        Vehiculos vehiculo1 = new Vehiculos(id, vehiculo, lugar);
        vehiculo1.setOwnerName(ownerName); 
        if (lugar.agregarVehiculo(vehiculo1)) {
            vehiculos.add(vehiculo1);

            ownerVehicleCount.put(ownerName, currentCount + 1);

            System.out.println("Vehículo agregado con éxito.");
        } else {
            System.out.println("No se pudo agregar el vehículo. Capacidad máxima alcanzada.");
        }
    }
}

public static void Eliminar() {
    System.out.println("\n--------------------------------------");
    System.out.print("Ingrese el ID del vehículo a eliminar: ");
    int id = sc.nextInt();

    for (Vehiculos vehiculo : vehiculos) {
        if (vehiculo.getId() == id) {
            String ownerName = vehiculo.getOwnerName(); 
            vehiculos.remove(vehiculo);

            vehiculo.getLugar().removerVehiculo(vehiculo);

            int currentCount = ownerVehicleCount.getOrDefault(ownerName, 0);
            if (currentCount > 0) {
                ownerVehicleCount.put(ownerName, currentCount - 1);
            }

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
    System.out.println("ID: "+vehiculo.getId()+", Vehiculo: "+ vehiculo.getVehiculo()+", Estacion: "+vehiculo.getLugar().getUbicacion()+", Dueño: "+vehiculo.getOwnerName());
    }
}
public static void ModificaciondeVehiculo() {
    Scanner sc = new Scanner(System.in);
    try {
        System.out.println("Mostrando vehículos para modificar:\n");
        Mostrar(); 
        System.out.print("\nPor favor introduzca el ID del vehículo que desea modificar: ");
        int id = sc.nextInt();
        Vehiculos vehiculo = null;
        for (Vehiculos v : vehiculos) {
            if (v.getId() == id) {
                vehiculo = v;
                break;
            }
        }

            while(vehiculo == null) {
                System.out.println("ID no válido. Por favor, intente de nuevo.");
                System.out.print("Ingrese el ID del vehículo a modificar: ");
                id = sc.nextInt();
                for (Vehiculos v : vehiculos) {
                    if (v.getId() == id) {
                        vehiculo = v;
                        break;
                    }
                }
            }     

        System.out.println("¿Qué desea modificar?");
        System.out.println("1. ID");
        System.out.println("2. Tipo de Vehículo");
        System.out.println("3. Estación");
        System.out.println("===============================");
        System.out.print("Su opción por favor: ");
        int opcionespecifica = sc.nextInt();
        sc.nextLine();

        if (opcionespecifica == 1) {
            System.out.println("ID actual: " + vehiculo.getId());
            System.out.print("Nuevo ID: ");
            int nuevoId = sc.nextInt();
            boolean idExistente = false;
            for (Vehiculos v : vehiculos) {
                if (v.getId() == nuevoId) {
                    idExistente = true;
                    break;
                }
            }

            if (idExistente) {
                System.out.println("Error: El ID " + nuevoId + " ya está en uso. No se puede modificar.");
            } else {
                vehiculo.setId(nuevoId);
                System.out.println("ID modificado con éxito.");
            }
        } else if (opcionespecifica == 2) {
            System.out.println("Tipo de Vehículo actual: " + vehiculo.getVehiculo());
            System.out.println("Seleccione el nuevo tipo de vehículo:");
            System.out.println("1. Bicicleta");
            System.out.println("2. Scooter");
            System.out.println("3. Skateboard");
            System.out.print("Opción: ");
            int tipo = sc.nextInt();
            String nuevoTipo = null;

            while (tipo < 1 || tipo > 3) {
                System.out.println("Opción no válida. Por favor, intente de nuevo.");
                System.out.print("Seleccione el nuevo tipo de vehículo: ");
                tipo = sc.nextInt();
            }

            if (tipo == 1) {
                nuevoTipo = "Bicicleta";
            } else if (tipo == 2) {
                nuevoTipo = "Scooter";
            } else if (tipo == 3) {
                nuevoTipo = "Skateboard";
            }

            vehiculo.setVehiculos(nuevoTipo);
            System.out.println("Tipo de Vehículo modificado con éxito.");
        } else if (opcionespecifica == 3) {
            System.out.println("Estación actual: " + vehiculo.getLugar().getUbicacion());
            System.out.println("Seleccione la nueva estación:");
            Estacion nuevaEstacion = Estacion.escogaestacion();

            if (nuevaEstacion.getCapacidad() <= 0) {
                System.out.println("Error: La estación seleccionada no tiene capacidad disponible.");
            } else {
                vehiculo.getLugar().getVehiculos().remove(vehiculo);  

                vehiculo.setLugar(nuevaEstacion); 
                nuevaEstacion.getVehiculos().add(vehiculo);  

                System.out.println("Estación modificada con éxito.");
            }
        } else {
            System.out.println("Opción no válida.");
        }
    } catch (Exception e) {
        System.out.println("Error al modificar el vehículo. Intente de nuevo.");
    }
}

}
