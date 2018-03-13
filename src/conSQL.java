import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class conSQL {
	//må legge inn url her
	private final String dbURL= "";
	private Connection con;
	 
	private Connection getConnection() throws SQLException{
		this.con = (Connection) DriverManager.getConnection(dbURL);
		return con;
	}
	
	
	/*
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
	
	*/

    public void addApparat(String navn, String beskrivelse) {
        try {
            Statement stmnt = (Statement) con.createStatement();
            stmnt.executeUpdate("insert into Apparat(Navn, Beskrivelse) values('" + navn + "', '" + beskrivelse + "')");
            System.out.println("Lagde nytt apparat.");
            stmnt.close();
        } catch(Exception e) {
            System.out.println("Kunne ikke lage apparat.");
            e.printStackTrace();
        }
    }

    public void addØvelseFri(String navn, String beskrivelse) {
        try {
            Statement stmnt = (Statement) con.createStatement();
            stmnt.executeUpdate("insert into Øvelse(Navn) values('" + navn + "')");
            ResultSet foreignKeyRS = stmnt.executeQuery("select last_insert_id()");
            foreignKeyRS.next();
            int foreignKey = foreignKeyRS.getInt(1);
            stmnt.executeUpdate("insert into ØvelseFri(ØvelseID, Beskrivelse) values('" + foreignKey + "','" + beskrivelse + "')");
            System.out.println("Lagde ny friøvelse.");
            stmnt.close();
        } catch(Exception e) {
            System.out.println("Kunne ikke lage ny øvelse.");
            e.printStackTrace();
        }
    }

    public void addØvelseApparat(String navn, String apparatNavn, int vekt, int sett) {
        try {
            Statement stmnt = (Statement) con.createStatement();
            stmnt.executeUpdate("insert into Øvelse(Navn) values('" + navn + "')");
            ResultSet foreignKeyRS = stmnt.executeQuery("select last_insert_id()");
            foreignKeyRS.next();
            int foreignKey = foreignKeyRS.getInt(1);
            ResultSet foreignKeyApparatRS = stmnt.executeQuery("select ApparatID from Apparat where Navn = '" + apparatNavn + "'");
            foreignKeyApparatRS.next();
            int foreignKeyApparat = foreignKeyApparatRS.getInt(1);
            stmnt.executeUpdate("insert into ØvelseApparat(ØvelseID, ApparatID, Vekt, Sett) values('" + foreignKey + "','" + foreignKeyApparat + "','" + vekt + "','" + sett + "')");
            System.out.println("Lagde ny apparatøvelse.");
            stmnt.close();
        } catch(Exception e) {
            System.out.println("Kunne ikke lage ny øvelse.");
            e.printStackTrace();
        }
    }
    
    public void addØkt(int brukerID, int varighet, int personligForm, int personligPrestasjon) {
    	try {
    		Statement stmnt = (Statement) con.createStatement();
    		stmnt.executeUpdate("insert into Økt(BrukerID, DatoTid, Varighet, PersonligForm, PersonligPrestasjon) values ('" + brukerID + "', now(),'" + varighet + "','" + personligForm + "','" + personligPrestasjon + "')");
    	
    	} catch(Exception e) {
            System.out.println("Kunne ikke lage ny økt."); 
            e.printStackTrace();
        }
    }

    public String test() {
        return "Test";
    }
	
	
	
	
	
}
