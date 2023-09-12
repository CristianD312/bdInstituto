
package Logica;

import Pantalla.Login;
import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) {
        Login lg = new Login();
        lg.setVisible(true);
        
        
 

    }
    
    
    public static void activarMenu() {
        Scanner Leer = new Scanner(System.in);
        try {           
            Alumno alumno= new Alumno();
            System.out.println("Elija la opcion:\n1. cargar alumno \n2. Consultar alumno \n3. Modificar alumno \n4. finalizar ");
            int decision = Leer.nextInt();Leer.nextLine();
            switch (decision) {
                case 1:
                     alumno.crearAlumno(alumno);
                     activarMenu();
                    break;
                case 2:
                     alumno.consultarAlumno();
                     activarMenu();
                    break;
                case 3:
                     alumno.modificarAlumno();
                     activarMenu();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema, saludos!");
                    break;
                default:
                    throw new AssertionError();
            }
            
            
           
            
        } catch (Exception e) {
        }

}
    
}
