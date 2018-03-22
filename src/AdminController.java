
	import java.sql.Connection;
	import java.sql.Date;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Time;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	
	
public class AdminController {

		
		//1. Kravspesifikasjon
		
		public static void settInnTreningsokt(Connection myConn, Date date, Time time, int duration, int personligForm, int prestasjon, String notat )throws SQLException{
			String preQueryStatement = "INSERT INTO Treningsøkt (Dato, Tidspunkt, Varighet, PersonligForm, Prestasjon, Notat) VALUES (?,?,?,?,?,?)";
			PreparedStatement preparedStatement = myConn.prepareStatement(preQueryStatement);
			
			preparedStatement.setDate(1, date);
			preparedStatement.setTime(2, time);
			preparedStatement.setInt(3, duration);
			preparedStatement.setInt(4, personligForm);
			preparedStatement.setInt(5, prestasjon);
			preparedStatement.setString(6, notat);
			preparedStatement.execute();
			
		}
		
		public static void settInnOvelsesgruppe(Connection myConn, String navn) throws SQLException {
			String preQueryStatement = "INSERT INTO Øvelsesgruppe (Navn) VALUES (?)";
			PreparedStatement preparedStatement = myConn.prepareStatement(preQueryStatement);
			preparedStatement.setString(1, navn);
			preparedStatement.execute();
		}
		
		public static void settinnOvelse(Connection myConn, String navn, String beskrivelse) throws SQLException {
			String preQueryStatement = "INSERT INTO FriØvelse (Navn, Beskrivelse) VALUES (?,?)";
			PreparedStatement preparedStatement = myConn.prepareStatement(preQueryStatement);
			preparedStatement.setString(1, navn);
			preparedStatement.setString(2, beskrivelse);
			preparedStatement.execute();
		}
		
		public static void settInnApparat(Connection myConn, String navn, String beskrivelse) throws SQLException{
			String preQueryStatement = "INSERT INTO Apparat (ApparatNavn, Beskrivelse) VALUES (?,?)";
			PreparedStatement preparedStatement = myConn.prepareStatement(preQueryStatement);
			preparedStatement.setString(1, navn);
			preparedStatement.setString(2, beskrivelse);
			preparedStatement.execute();
		}
		
		public static void settInnOvelsePaaApparat(Connection myConn,String ovelseNavn, String ApparatNavn) throws SQLException{
			//Begge er fremmednøkkler til sin entitet
			String preQueryStatement = "INSERT INTO ApparatØvelse (ovelseNavn, apparatNavn) VALUES (?,?)";
			PreparedStatement preparedStatement = myConn.prepareStatement(preQueryStatement);
			preparedStatement.setString(1, ovelseNavn);
			preparedStatement.setString(2, ApparatNavn);
			preparedStatement.execute();
		}
		
		public static void settInnGruppeMedOvelse(Connection myConn,String gruppeNavn,String ovelseNavn) throws SQLException{
			//Begge er fremmednøkkler til sin entitet
			String preQueryStatement = "INSERT INTO Øvelsesgruppe (Øvelsesgruppe.Navn, FriØvelse.Navn) VALUES (?,?)";
			PreparedStatement preparedStatement = myConn.prepareStatement(preQueryStatement);
			preparedStatement.setString(1, gruppeNavn);
			preparedStatement.setString(2, ovelseNavn);
			preparedStatement.execute();
		}
		
		public static void settinnTreningsoktMedOvelse(Connection myConn,Date treningsoktDato,String ovelsesNavn,int antallKilo, int antallSet) throws SQLException{
			//Begge er fremmednøkkler til sin entitet
			String preQueryStatement = "INSERT INTO workoutcontainsexercise (Dato, Navn, AntallKilo, AntallSett) VALUES (?,?,?,?)";
			PreparedStatement preparedStatement = myConn.prepareStatement(preQueryStatement);
			preparedStatement.setDate(1, treningsoktDato);
			preparedStatement.setString(2, ovelsesNavn);
			preparedStatement.setInt(3, antallKilo); 
			preparedStatement.setInt(4, antallSet);
			preparedStatement.execute();
		}
		
		
	//2. Kravspesifikasjon
		
		public static List<Treningsokt> getNTreningsokter(Connection conn, int n) throws SQLException{
			List<Treningsokt> okter = new ArrayList<Treningsokt>();
			
			
			String stmt = "select * from Treningsøkt order by dato desc limit ?";
			PreparedStatement preparedStatement = conn.prepareStatement(stmt);
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, n);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Treningsokt okt = new Treningsokt(rs.getDate("dato"), rs.getTime("tidspunkt"), rs.getInt("varighet"), rs.getInt("personligForm"), rs.getInt("prestasjon"), rs.getString("InfoOmØvelse"));
				okter.add(okt);
			}
			
			for (Treningsokt okt : okter) {
				Date id = okt.getDato();
				stmt = "select * from workoutcontainsexercise where dato = ?";
				preparedStatement = conn.prepareStatement(stmt);
				preparedStatement.setDate(1, id);
				while(rs.next()) {
					Ovelse ovelse = new Ovelse(rs.getString("OvelseNavn"),rs.getInt("kilo"),rs.getInt("sett"));
					
					
					String tmpStmt = "Select * from Ovelse where navn = ?";
					PreparedStatement pr = conn.prepareStatement(tmpStmt);
					pr.setString(1, rs.getString("ovelsesNavn"));
					ResultSet tmpRes = pr.executeQuery();
					ovelse.setBeskrivelse(tmpRes.getString("beskrivelse"));
					
					okt.addOvelse(ovelse);
				}
			}
			return okter;
		}
		
			
		//3. Kravspesifikasjon
		
		public static String getOvelsesResultat(Connection myConn, Date dateStart,Date dateEnd) throws SQLException{
			//TODO - BETWEEN FUNKER IKKE HER MED DATOENE...
			String min = "'" + dateStart.getYear() + "-" + (dateStart.getMonth()+1) + "-" + dateStart.getDate() + "'";
			String max = "'" + dateEnd.getYear() + "-" + (dateEnd.getMonth()+1) + "-" + dateEnd.getDate() + "'";
	        String query = "SELECT PERSONLIGFORM, VARIGHET FROM Treningsokt WHERE DATO BETWEEN ? AND ?";
	        PreparedStatement preparedStatement = myConn.prepareStatement(query);
	        preparedStatement.setString(1, min);
	        preparedStatement.setString(2, max);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        int index =1;
	        int antallTimer = 0;
	        int antallPersonligeForm = 0;
	        while (resultSet.next()) {
	            System.out.println("INNNE I LOOPEN");
	            index++;
	            antallTimer += resultSet.getInt("VARIGHET");
	            antallPersonligeForm += resultSet.getInt("PERSONLIGFORM");
	            
	        }
	        int personligFormSnitt = antallPersonligeForm/index;
	        int varighetSnitt = antallTimer/index;
	        
	        String report = "I løpet av perioden på "+ index + "dager, trente du "+ antallTimer + "."+ " Gjennomsnittsøkten var på " +varighetSnitt +" timer, med et gjennomsnittlig perosnlig form på "+personligFormSnitt +".";

	        return report;
	    }
		
		
		//4. Kravspesifikasjon
		
		public static List<Ovelsesgruppe> getOvelsesgruppe(Connection conn) throws SQLException{
			List<Ovelsesgruppe> ovelsesGrupper = new ArrayList<Ovelsesgruppe>();
						
			//Spørr om alle koblingene mellom en Ovelse og en gruppe
			String stmt = "Select * from Øvelsesgruppe";
			PreparedStatement preparedStatement = conn.prepareStatement(stmt);
			ResultSet rs = preparedStatement.executeQuery();
			
			//Lag en map hvor  øvelsesgruppe er key og ArrayList med exercisenavn er value
			Map<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
			while(rs.next()) {
				if(map.containsKey(rs.getString(""))) {
					map.get(rs.getString(""));
				}
				else {
					map.put(rs.getString(""), new ArrayList<String>(Arrays.asList(rs.getString("OvelsesNavn"))));
				}
			}
			
			//Lag objekter ut av mappen
			for(String gruppenavn: map.keySet()) {
				List<Ovelse> ovelser = new ArrayList<Ovelse>();
				for(String ovelseNavn : map.get(gruppenavn)) {
					//Hen Ovelse beskrivelse
					String tmpStmt = "Select * from Ovelse where navn = ?";
					PreparedStatement pr = conn.prepareStatement(tmpStmt);
					pr.setString(1, ovelseNavn);
					ResultSet tmpRes = pr.executeQuery();
					if(tmpRes.next()) {
						Ovelse ovelse = new Ovelse(ovelseNavn,tmpRes.getString("beskrivelse"));
						ovelser.add(ovelse);
					}
					
				}
				ovelsesGrupper.add(new Ovelsesgruppe(gruppenavn,ovelser));
			}
			return ovelsesGrupper;
		}
		
		
		//Kravspesifikasjon 5
		
		public static int getTotaltAntallOkter(Connection conn) throws SQLException {
			String stmt  = "select count(dato) as total from Treningsokt";
			PreparedStatement pr = conn.prepareStatement(stmt);
			ResultSet rs = pr.executeQuery();
			return rs.next() ? rs.getInt("Totalt antall økter") : 0;
		}
	}

