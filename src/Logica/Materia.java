package Logica;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class Materia {
    
    private String nombre;
    private int fkCarrera;
    private int curso;
    private int fkDocente;

    public Materia() {
    }

    public Materia(String nombre, int fkCarrera, int curso, int fkDocente) {
        this.nombre = nombre;
        this.fkCarrera = fkCarrera;
        this.curso = curso;
        this.fkDocente = fkDocente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFkCarrera() {
        return fkCarrera;
    }

    public void setFkCarrera(int fkCarrera) {
        this.fkCarrera = fkCarrera;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getFkDocente() {
        return fkDocente;
    }

    public void setFkDocente(int fkDocente) {
        this.fkDocente = fkDocente;
    }
    
    public void crearMateria (Materia materia){
            Conection conexion = new Conection();
            conexion.conectar();
            try {
            String sql = "INSERT INTO `materias`(`ID_Materia`, `Nombre`, `FK_Carrera`, `Curso`, `FK_Docente`) VALUES (NULL, ?, ?, ?, ?,);";
            PreparedStatement statement = conexion.conn.prepareStatement(sql);
            statement.setString(1, materia.getNombre());
            statement.setInt(2, materia.getFkCarrera());
            statement.setInt(3, materia.getCurso());
            statement.setInt(4, materia.getFkDocente());

            statement.executeUpdate();
            System.out.println("materia creado exitosamente");
            statement.close();
            
                
        } catch (Exception e) {
        }
    
    }
    public void consultarMateria(){
    Conection conexion = new Conection();
    conexion.conectar();
    
        try {
            String sql ="SELECT materias.Nombre,materias.ID_Materia,docentes.nombre,docentes.apellido,carreras.Nombre "
                    + "FROM materias "
                    + "INNER JOIN carreras ON materias.FK_Carrera=carreras.ID_Carrera "
                    + "INNER JOIN docentes ON materias.FK_Docente=docentes.ID_Docente;";
            Statement st= conexion.conn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            
            while (rs.next()) {
                System.out.println("El ID de la materia es: "+rs.getInt("ID_Materia"));
                System.out.println("Nombre de la materia: "+rs.getString("materias.nombre"));
                System.out.println("Nombre y Apellido del docente: "+rs.getString("docentes.nombre")+" "+rs.getString("docentes.Apellido"));
                System.out.println("Carrera: "+rs.getString("carreras.Nombre"));
                System.out.println("---------------------------------------------------------");
                
            }
            rs.close();
            st.close();
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    
    
    
    }
    
    
}
