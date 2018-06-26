package sells.dao;


import java.sql.*;

public class Conexoes {
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String usuario = "system";
	private static final String senha = "floggingmolly";
	private static Conexoes instancia;
	private Connection con;
	
	private Conexoes() { 
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Conexoes getInstancia() { 
		if (instancia == null) { 
			instancia = new Conexoes();
		}
		return instancia;
	}
	
	public Connection openConnection() { 
		if (con == null) { 
			try {
				con = DriverManager.getConnection(url, usuario, senha);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	
	public void closeConnection() { 
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con = null;
	}
	

}
