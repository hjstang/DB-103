import java.sql.*;
import java.util.Properties;

public class ConnectionSQL {
    
    protected Connection conn;
    public ConnectionSQL() {
    }
    
    public Connection connect() {
        try {
            Class.forName(“com.mysql.jdbc.Driver”).newInstance();
            
            // Brukernavn og passord
            Properties p = new Properties();
            p.put(“user”,  “noragk”);
            p.put(“password”, “Norag0808”);
            conn = DriverManager.getConnection(“jdbc:mysql://mysql.stud.ntnu.no/noragk_db_treningsdagbok?user=noragk_db&password=Norag0808")
        }
    }
}