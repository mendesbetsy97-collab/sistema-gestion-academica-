package co.edu.sena.sgac.dao;
import co.edu.sena.sgac.config.DatabaseConnection;
import co.edu.sena.sgac.model.Estudiante;
import java.sql.*; import java.util.*;
public class EstudianteDAO {
    public List<Estudiante> listar() throws SQLException {
        List<Estudiante> lista=new ArrayList<>(); String sql="SELECT * FROM estudiante ORDER BY id DESC";
        try(Connection c=DatabaseConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql); ResultSet r=p.executeQuery()){while(r.next()) lista.add(mapear(r));} return lista;
    }
    public Estudiante buscarPorId(int id) throws SQLException {
        try(Connection c=DatabaseConnection.getConnection(); PreparedStatement p=c.prepareStatement("SELECT * FROM estudiante WHERE id=?")){p.setInt(1,id);try(ResultSet r=p.executeQuery()){if(r.next())return mapear(r);}} return null;
    }
    public boolean insertar(Estudiante e) throws SQLException {
        String sql="INSERT INTO estudiante(documento,nombres,apellidos,correo,programa) VALUES(?,?,?,?,?)";
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){datos(p,e);return p.executeUpdate()==1;}
    }
    public boolean actualizar(Estudiante e) throws SQLException {
        String sql="UPDATE estudiante SET documento=?,nombres=?,apellidos=?,correo=?,programa=? WHERE id=?";
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){datos(p,e);p.setInt(6,e.getId());return p.executeUpdate()==1;}
    }
    public boolean eliminar(int id) throws SQLException {
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement("DELETE FROM estudiante WHERE id=?")){p.setInt(1,id);return p.executeUpdate()==1;}
    }
    private void datos(PreparedStatement p,Estudiante e)throws SQLException{p.setString(1,e.getDocumento());p.setString(2,e.getNombres());p.setString(3,e.getApellidos());p.setString(4,e.getCorreo());p.setString(5,e.getPrograma());}
    private Estudiante mapear(ResultSet r)throws SQLException{return new Estudiante(r.getInt("id"),r.getString("documento"),r.getString("nombres"),r.getString("apellidos"),r.getString("correo"),r.getString("programa"));}
}
