import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class conSQL {
	//må legge inn url her
	private final String dbURL= "";
	 
	private Connection getConnection() throws SQLException{
		return (Connection) DriverManager.getConnection(dbURL);
	}
	
	
	
	@Override
	public void createNewNurse(Nurse nurse) throws SQLException{
		try {
			Statement stmt = getStatement();
			String faculty = nurse.getFaculty();
			String query = "INSERT INTO helsesoster(brukernavn, passord, fakultet, fornavn"
					+ ", etternavn, email, telefonNr) VALUES ('" + nurse.getUsername() + "', '" +
					nurse.getPassword() + "', " + switchInsert(faculty) + ", '" + nurse.getFirstName()
					+ "', '" + nurse.getSecondName() + "', '" + nurse.getEmail() + "', " + 
					nurse.getPhoneNumber() + ");";
			
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
	public Nurse getNurse(String username) throws SQLException {
			
			ResultSet rs = null;
			Nurse nurse = new Nurse(username);
			try {
				String query = "SELECT * FROM helsesoster WHERE brukernavn='" + username +"';";
				Statement stmt = getStatement();
				
				if(stmt.execute(query)) {
					rs = stmt.getResultSet();
					
					if (!rs.isBeforeFirst() ) {    
					    System.out.println("No data"); 
					    throw new IllegalStateException("Denne brukeren eksisterer ikke i databasen"); //Dette er burde vi endre på slik at den kanskje returnerer null isteden
					} 
				}
			}
			catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
			
			while(rs.next()) {
				String password = rs.getString("passord");
				nurse.setPassword(password);
				
				Integer faculty = rs.getInt("fakultet");
				nurse.setFaculty(switchFakultetIDtoName(faculty));
				
				String firstName = rs.getString("fornavn");
				nurse.setFirstName(firstName);
				
				String secondName = rs.getString("etternavn");
				nurse.setSecondName(secondName);
				
				String email = rs.getString("email");
				nurse.setEmail(email);
				
				String phoneNumber = rs.getString("telefonNr");
				nurse.setPhoneNumber(phoneNumber);
			}
			
			return nurse; 
		}
	
	

	
	
	
	
	
}
