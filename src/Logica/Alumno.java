
package Logica;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;


public class Alumno {
    
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String mail;
    private String dni;

    public Alumno() {
    }

    public Alumno(String nombre, String apellido, String direccion, String telefono, String mail, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.mail = mail;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public void crearAlumno (Alumno alumno){
            Conection conexion = new Conection();
            conexion.conectar();
            try {
            String sql = "INSERT INTO `alumnos`(`ID_Alumno`, `nombre`, `apellido`,`Dirección`,`Telefono`,`E-Mail`, `Dni`) VALUES (NULL, ?, ?, ?, ?, ?, ? );";
            PreparedStatement statement = conexion.conn.prepareStatement(sql);
            statement.setString(1, alumno.getNombre());
            statement.setString(2, alumno.getApellido());
            statement.setString(3, alumno.getDireccion());
            statement.setString(4, alumno.getTelefono());
            statement.setString(5, alumno.getMail());
            statement.setString(6, alumno.getDni());
            statement.executeUpdate();
            System.out.println("alumno creado exitosamente");
            statement.close();
            
                
        } catch (Exception e) {
        }
    
    }
    public void consultarAlumno(){
            Conection conexion = new Conection();
            conexion.conectar();
            try {
            String sql = "SELECT * FROM alumnos";
            Statement statement = conexion.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()){
                System.out.println("El id del alumno es :"+resultSet.getInt("ID_Alumno"));
                System.out.println("Nombre y Apellido: "+resultSet.getString("nombre")+" "+resultSet.getString("apellido"));
                System.out.println("Dirección : "+resultSet.getString("Dirección"));
                System.out.println("Telefono: "+resultSet.getString("Telefono"));
            }
            resultSet.close();
            statement.close();
            
                
        } catch (Exception e) {
        }
    
    
    }
    
    public void modificarAlumno(){
        Conection conexion = new Conection();
        conexion.conectar();
        Scanner leer = new Scanner(System.in);
        try {
            System.out.println("Ingrese el ID del alumno");
            int id= leer.nextInt();leer.nextLine();
            System.out.println("Ingresa el Telefono del alumno");
            String telefono = leer.nextLine();
            System.out.println("Ingresa el E-mail del alumno");
            String mail = leer.nextLine();
            
            //String sql = "SELECT * FROM `usuarios` WHERE `usuario`=`"+user+"` ";
            String sql= "UPDATE `alumnos` SET `Telefono` = '"+telefono+"',`E-Mail` ='"+mail+"'  WHERE `ID_Alumno` = '"+id+"' ";
            
            PreparedStatement statement = conexion.conn.prepareStatement(sql);
            /*
            statement.setString(1, telefono);
            statement.setString(2, mail);
            */
            //statement.setInt(1, id);
            
            statement.executeUpdate();
            System.out.println("alumno modificado exitosamente");
            statement.close();
            
            
            
        } catch (Exception e) {
            System.out.println("no se pudo modificar, error: "+e.toString());
        }
        
    
    
    }
    
}
