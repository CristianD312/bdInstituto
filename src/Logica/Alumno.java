 package Logica;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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

    public void crearAlumno(JTextField paramNombre, JTextField paramApellido, JTextField paramTel) {
        Conection con = new Conection();
        Alumno alu = new Alumno();
        alu.setNombre(paramNombre.getText());
        alu.setApellido(paramApellido.getText());
        alu.setTelefono(paramTel.getText());
        try {
            String sql="";
            sql = "INSERT INTO `alumnos` (`ID_Alumno`, `nombre`, `apellido`, `telefono`) VALUES (NULL, ?, ?, ?)";
            PreparedStatement st =con.conectar().prepareCall(sql);
            st.setString(1, alu.getNombre());
            st.setString(2, alu.getApellido());
            st.setString(3, alu.getTelefono());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alumno creado exitosamente.");
            st.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, no se pudo realizar la operacion, error: "+e.toString());
        }
        
        
        
        

    }

    public void consultarAlumno(JTable paramtablaAlumno) {
        Conection con = new Conection();
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
        paramtablaAlumno.setRowSorter(ordenarTabla);
        String sql = "select * from alumnos";
        modelo.addColumn("id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Telefono");
        String[] datos = new String[4];

        try {
            Statement st = con.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(5);
                modelo.addRow(datos);

            }
            paramtablaAlumno.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se pudo realizar la operacion, error: "+e.toString());
        }

    }

    public void modificarAlumno() {
        Conection conexion = new Conection();
        conexion.conectar();
        Scanner leer = new Scanner(System.in);
        try {
            System.out.println("Ingrese el ID del alumno");
            int id = leer.nextInt();
            leer.nextLine();
            System.out.println("Ingresa el Telefono del alumno");
            String telefono = leer.nextLine();
            System.out.println("Ingresa el E-mail del alumno");
            String mail = leer.nextLine();

            //String sql = "SELECT * FROM `usuarios` WHERE `usuario`=`"+user+"` ";
            String sql = "UPDATE `alumnos` SET `Telefono` = '" + telefono + "',`E-Mail` ='" + mail + "'  WHERE `ID_Alumno` = '" + id + "' ";

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
            System.out.println("no se pudo modificar, error: " + e.toString());
        }

    }

}
