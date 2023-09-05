
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conection {
    private static final String URL = "jdbc:mysql://localhost:3306/bd_instituto";
    private static final String USUARIO = "root";
    private static final String PASS="";
    Connection conn = null;

    public Conection() {
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public Connection conectar(){
        try {
            conn=DriverManager.getConnection(URL,USUARIO,PASS);
            JOptionPane.showMessageDialog(null, "Conexión realizada con exito!: ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no se pudo mostrar los registros, error: "+e.toString());

        }
    return conn;
    
    }
    
}
