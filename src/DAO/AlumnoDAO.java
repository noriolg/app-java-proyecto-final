package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.PreparedStatement;

import dominio.Alumno;

public class AlumnoDAO {

    private static AlumnoDAO usuarioDAO;
    private Connection con;
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    // Date en mysql es '0000-00-00' 'YYYY-MM-DD'

    public static AlumnoDAO getInstance() throws SQLException, ClassNotFoundException
    {
        if(usuarioDAO!=null)
        {
            if(!usuarioDAO.isActiva())
                usuarioDAO = new AlumnoDAO();
        }
        else
            usuarioDAO = new AlumnoDAO();
        return usuarioDAO;
    }

    private boolean isActiva() throws SQLException
    {
        return con.isValid(0);
    }

    // Método para hacer una consulta segura de matrícula
    public boolean anadirAlumno(Alumno alumno)
    {
        boolean insercionOk;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/icarus", USER, PASSWORD);
            PreparedStatement ps = con.prepareStatement("INSERT INTO alumnos (usuario, contrasena, nombre, apellidos, codigo, email, telefono, etapa, curso)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, alumno.getUsuario());
            ps.setString(2, alumno.getContrasena());
            ps.setString(3, alumno.getNombre());
            ps.setString(4, alumno.getApellidos());
            ps.setInt(5, alumno.getCodigoPostal());
            ps.setString(6, alumno.getEmail());
            ps.setString(7, alumno.getTelefono());
            ps.setString(8, alumno.getEtapa());
            ps.setInt(9, alumno.getCurso());

            int i = ps.executeUpdate();
            con.close();
            insercionOk = true;
        }catch(SQLIntegrityConstraintViolationException e){
            System.out.println("Fallo en AlumnoDAO linea 57");
            System.out.println(e);
            insercionOk = false;


        } catch (SQLException e) {
            System.out.println("Fallo en UsuarioDAO linea 61");
            e.printStackTrace();
            insercionOk = false;
            //System.err.format("Mensaje SQL:  \n", e.getSQLState(),e.getMessage());
            //response.sendError(500, "Error en el acceso a la base de datos");
        } catch (ClassNotFoundException e) {
            //response.sendError(500, e.toString() );
            insercionOk = false;
            e.printStackTrace();
        }
        return insercionOk;
    }

    // Estos hay que repasarlos, para ver qué queremos conseguir cuando obtenemos un alumno.
    public Collection<Alumno> obtenerAlumno(Alumno alumno) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/icarus", USER, PASSWORD);
        PreparedStatement ps = con.prepareStatement("SELECT * FROM alumnos WHERE  usuario = ? AND password = ? ");
        ps.setString(1, alumno.getUsuario());
        ps.setString(2, alumno.getContrasena());
        ResultSet rs = ps.executeQuery();
        Collection<Alumno> alumnos = resultSetToCollection(rs);
        rs.close();
        return alumnos;
    }

    // Recorre el resultset y devuelve una colección de Vehículos
    private Collection<Alumno> resultSetToCollection(ResultSet rs){
        Collection<Alumno> coleccion = new ArrayList<Alumno>();
        try {
            while(rs.next())
            {
                Alumno alu;
                alu = new Alumno(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
                coleccion.add(alu);
            }
            return coleccion;
        }catch(SQLException e) {
            //En el caso de que rs esté vacío se devuelve una colección vacía. Esto se comprobará en recepción
            return coleccion;
        }
    }

    // Cierra la conexión iniciada por la instancia de VehiculoDAO
    public void close() throws SQLException, ClassNotFoundException
    {
        con.close();
    }


}
