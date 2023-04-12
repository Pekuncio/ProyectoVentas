package Modelo;

import java.sql.*;

public class Conexion {
        // constantes para conexion a base de datos
        private static final String JDBC_URL ="jdbc:mysql://localhost:3306/sistemaventa?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	private static final String JDBC_USER ="root";
	private static final String JDBC_PASSWORD = "PekunCio199410!";
	// metodo para la conexion con la base de datos.
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}
	//Metodos para cerrar la conexion.
	public void close(Connection conn) throws SQLException {
		conn.close();
	}
	public void close (PreparedStatement stmt) throws SQLException {
		stmt.close();
	}
	public void close(ResultSet rs) throws SQLException {
		rs.close();
	}

}
