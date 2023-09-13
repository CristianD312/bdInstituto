package Logica;

import Pantalla.Inicio;
import Pantalla.Login;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conection {

    private static final String URL = "jdbc:mysql://localhost:3306/bd_instituto";
    private static final String USUARIO = "root";
    private static final String PASS = "";
    Connection conn = null;

    public Conection() {
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Connection conectar() {
        try {
            conn = DriverManager.getConnection(URL, USUARIO, PASS);
            //JOptionPane.showMessageDialog(null, "Conexión realizada con exito!: ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no se pudo mostrar los registros, error: " + e.toString());

        }
        return conn;

    }

    public void validarLogin(String user, String pass) {
        Inicio in = new Inicio();
        Login lg = new Login();
        

        try {
            String sql = "SELECT * FROM `usuarios` WHERE `usuario`= ?";
            PreparedStatement st = conectar().prepareStatement(sql);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String p = rs.getString("contraseña");
                if (pass.equals(p)) {
                    JOptionPane.showMessageDialog(null, "Correcto!");
                    
                    in.setVisible(true);
                    rs.close();
                    st.close();
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta!");
                    rs.close();
                    st.close();
                }

            }else {
            JOptionPane.showMessageDialog(null, "no se pudo encontrar al usuario");
            }

        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "hubo un error, error: "+e.toString());
        }

    }

}
