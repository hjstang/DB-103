import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class conSQL {
	//må legge inn url her
	private final String dbURL= "";
	 
	private Connection getConnection() throws SQLException{
		return (Connection) DriverManager.getConnection(dbURL);
	}
	
	
	
@Override
public void createNewTreningsokt(Treningsokt treningsokt) throws SQLException{
	try {
		Statement stmt = getStatement();
		int treningsID = treningsokt.getTreningsID();
			int dato = treningsokt.getDato();
			int tidspunkt = treningsokt.getTidspunkt();
			int varighet = treningsokt.getVarighet();
			String infoOmOvelser = treningsokt.getInfoOmOvelser();
			int prestasjon = treningsokt.getPrestasjon();
			int personligForm = treningsokt.getPersonligForm();
			String query = "INSERT INTO Treningsøkt(TreningsID, Tato, Tidspunkt, Varighet, InfoOmØvelser, PersonligForm, Prestasjon"
					+ ") VALUES ('" + treningsokt.getTreningsID() + "', '" +
					treningsokt.getDato() + "', " + treningsokt.getTidspunkt() + ", '" + treningsokt.getVarighet()
					+ "', '" + treningsokt.getInfoOmOvelser() + "', '" + treningsokt.getPrestasjon() + "', " + 
					treningsokt.getPersonligForm() + ");";
			
			stmt.executeUpdate(query);
			System.out.println(query);
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		
	}
	
	private Statement getStatement() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Treningsokt getTreningsøkt(int treningsID) throws SQLException {
			
			ResultSet rs = null;
			Treningsokt treningsokt = new Treningsokt(1, 0, 0, 0, "test", 0, 0);
			try {
				String query = "SELECT * FROM Treningsokt WHERE TreningsID='" + treningsID +"';";
				Statement stmt = getStatement();
				
				if(stmt.execute(query)) {
					rs = stmt.getResultSet();
					
					if (!rs.isBeforeFirst() ) {    
					    System.out.println("No data"); 
					    throw new IllegalStateException("Denne treningsøkten eksisterer ikke i databasen"); //Dette er burde vi endre på slik at den kanskje returnerer null isteden
					} 
				}
			}
			catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
			
			
			return treningsokt; 
		}
	
	

	
	
	
	
	
}
