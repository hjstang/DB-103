import java.sql.*;
import java.util.Properties;

public abstract class ConnectionSQL {
	
	protected Connection conn;
	public ConnectionSQL() {
	}
	
	public Connection connect() {
		try {
			System.out.println("Trying to connect");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			// Brukernavn og passord
			Properties p = new Properties();
			p.put("user",  "noragk");
			p.put("password", "Norag0808");
			conn = DriverManager.getConnection("https://mysqladmin.it.ntnu.no/db_structure.php?server=1&db=noragk_treningsdagbok&token=57c45fd2fb6ef7d70160a6b49fd7f990", p);
			System.out.println("Connection OK");
			return conn;
		} catch (Exception e) {	
			throw new RuntimeException("Unable to connect", e);
		}
	}
} 