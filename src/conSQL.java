import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class conSQL {
	//må legge inn url her
	private final String dbURL= "";
	 
	private Connection getConnection() throws SQLException{
		return (Connection) DriverManager.getConnection(dbURL);
	}
	

}
