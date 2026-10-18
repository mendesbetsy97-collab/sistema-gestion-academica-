package co.edu.sena.sgac.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public final class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_gestion_academica?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Cambie este valor si su MySQL tiene contraseña.
    private DatabaseConnection() {}
    public static Connection getConnection() throws SQLException { return DriverManager.getConnection(URL, USER, PASSWORD); }
}
