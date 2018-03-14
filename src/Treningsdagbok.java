import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Treningsdagbok {
	
	private Scanner scanner = new Scanner(System.scanner);
	private ConnectionSQL sql = new ConnectionSQL(); 
	
	
	public void insertionØkt() throws Exception{
		
		String query, type, answer; 
		ArrayList<String> øktData = new ArrayList<>(); 
		
		System.out.println("Du valgte insetting, hvilken dato var treningen? (yyyymmdd)"); //henter øktdata 
		øktData.add(scanner.next()); 
		
		System.out.println("Når startet økten? (hhmmss)");
		øktData.add(scanner.next()); 
		
		System.out.println("Varighet? (hhmmss)");
		øktData.add(scanner.next());
		
		System.out.println("Info om øvelser? ");
		øktData.add(scanner.next());
		
		System.out.println("Personlig form? (1-10)");
		øktData.add(scanner.next());
		
		System.out.println("Prestasjon? (1-10)");
		øktData.add(scanner.next());
		
		scanner.nextLine(); // to prevent missing a input
		
		System.out.println("Notat til økten? (JA/NEI)");
		type = scanner.next();
		if (type.equals("JA")) {
			query = "Intert into Notater values (";
			System.out.println("Trenignsformål? " );
			query += "'" + scanner.nextLine() + "', ";
			System.out.println("Opplevelse? ");
			query += scanner.next() + "," + øktData.get(0) + "," + øktData.get(1) +");";
			
			System.out.println(query);
			sql.runUpdate(query); 
			
		}
		øktData.add("'" + scanner.nextLine() + "'");	
		
		query = "insert into Økt values (";   			//lager query med dataene for økt via for-løkke
		
		for (int i = 0; i < 7; i++ ){
			
			if (i==6){
				query += øktData.get(6) + ");"; 
			} else {
				query += øktData.get(i) + ", "; 
			}
		}
		System.out.println(query);
		sql.runUpdate(query); 
		
		do{ 																							// legger til øvelser i økten 
			System.out.println("Ønsker du å legge til en friøvelse- eller apparatøvelse? (F/A)");
			answer = scanner.next(); 
			
			if (answer.equals("F")){
				addØvelseFri(øktData.get(0), øktData.get(1)); 
			} else if(answer.equals("A")){
				addØvelseApparat(øktData.get(0), øktData.get(1));
			}
			
			System.out.println("Vil du legge til flere øvelser? (ja/nei)");				// spør om innsetting skal avsluttes
			answer = scanner.next(); 
			
		} while (answer.equals("ja")); 
		
	}
	
	public void getNumber() throws Exception{
		System.out.println("Hvor mange treningsøkter ønsker du å se?" );
		
	}
	
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
            System.out.println("Ny friøvelse ble laget.");
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
            System.out.println("Ny apparatøvelse er laget.");
            stmnt.close();
        } catch(Exception e) {
            System.out.println("Kunne ikke lage ny øvelse.");
            e.printStackTrace();
        }
    }
    
    public void addØkt(int TreningsID, int varighet, int personligForm, int personligPrestasjon) {
    	try {
    		Statement stmnt = (Statement) con.createStatement();
    		stmnt.executeUpdate("insert into Treningsøkt(TreningsID, Dato, Varighet, PersonligForm, PersonligPrestasjon) values ('" + TreningsID + "', now(),'" + varighet + "','" + personligForm + "','" + personligPrestasjon + "')");
    	
    	} catch(Exception e) {
            System.out.println("Kunne ikke lage ny økt."); 
            e.printStackTrace();
        }
    }
    
    public void addØvelsesgruppe() {
    		// trenger kode!!!!!!
    }

}
