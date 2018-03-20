
public class AdminController {

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

	public class AdminController{
		
		//1. Kravspesifikasjon
		
		public static void settInnTreningsokt(Connection myConn, Date date, Time time, int duration, int personligForm, int prestasjon, String notat )throws SQLException{
			String preQueryStatement = "INSERT INTO Treningsokter (Dato, Tidspunkt, Varighet, PersonligForm, Prestasjon, Notat) VALUES (?,?,?,?,?,?)";
			PreparedStatement prepStat = myConn.prepareStatement(preQueryStatement);
			
			prepStat.setDate(1,dato);
			prepStat.setTime(2, tidspunkt);
			prepStat.setInt(3, varighet);
			prepStat.setInt(4, personligForm);
			prepStat.setInt(5, prestasjon);
			prepStat.setString(6, notat);
			prepStat.execute();
			
		}
		
		public static void settInnOvelsesgruppe(Connection myConn, String navn) throws SQLException {
			String preQueryStatement = "INSERT INTO Ovelsesgruppe (Navn) VALUES (?)";
			PreparedStatement prepStat = myConn.prepareStatement(preQueryStatement);
			prepStat.setString(1, navn);
			prepStat.execute();
		}
		
		
		public static void settinnOvelse(Connection myConn, String navn, String beskrivelse) throws SQLException {
			String preQueryStatement = "INSERT INTO Ovelse (Navn, Beskrivelse) VALUES (?,?)";
			PreparedStatement prepStat = myConn.prepareStatement(preQueryStatement);
			prepStat.setString(1, navn);
			prepStat.setString(2, beskrivelse);
			prepStat.execute();
		}
		
		public static void settInnApparat(Connection myConn, String navn, String beskrivelse) throws SQLException{
			String preQueryStatement = "INSERT INTO Apparat (Navn, Beskrivelse) VALUES (?,?)";
			PreparedStatement prepStat = myConn.prepareStatement(preQueryStatement);
			prepStat.setString(1, navn);
			prepStat.setString(2, beskrivelse);
			prepStat.execute();
		}
		
		public static void settInnOvelsePaaApparat(Connection myConn,String navn,String ApparatNavn) throws SQLException{
			//Begge er fremmednøkkler til sin entitet
			String preQueryStatement = "INSERT INTO exerciseonmachine (ovelseNavn, apparatNavn) VALUES (?,?)";
			PreparedStatement prepStat = myConn.prepareStatement(preQueryStatement);
			prepStat.setString(1, ovelseNavn);
			prepStat.setString(2, apparatNavn);
			prepStat.execute();
		}
		
		public static void insertGroupContainsExercise(Connection myConn,String groupName,String ovelseNavn) throws SQLException{
			//Begge er fremmednøkkler til sin entitet
			String preQueryStatement = "INSERT INTO groupcontainsexersice (Ovelsesgruppe.Navn, Ovelse.Navn) VALUES (?,?)";
			PreparedStatement prepStat = myConn.prepareStatement(preQueryStatement);
			prepStat.setString(1, gruppeNavn);
			prepStat.setString(2, ovelseNavn);
			prepStat.execute();
		}
		
		public static void insertWorkoutContainsExercise(Connection myConn,Date treingsDato,String ovelsesName,int antallKilo, int antallSet) throws SQLException{
			//Begge er fremmednøkkler til sin entitet
			String preQueryStatement = "INSERT INTO workoutcontainsexercise (Dato, Navn, AntallKilo, AntallSett) VALUES (?,?,?,?)";
			PreparedStatement prepStat = myConn.prepareStatement(preQueryStatement);
			prepStat.setDate(1, TreningsoktDato);
			prepStat.setString(2, OvelsesNavn);
			prepStat.setInt(3, antallKilo); 
			prepStat.setInt(4, antallSet);
			prepStat.execute();
		}
		
		
	//2. Kravspesifikasjon
		
		public static List<Treningsokter> getNWorkouts(Connection conn, int n) throws SQLException{
			List<Treningsokter> okter = new ArrayList<Treningsokter>();
			
			
			String stmt = "select * from Treningsokter order by dato desc limit ?";
			PreparedStatement prepStat = conn.prepareStatement(stmt);
			prepStat.setInt(1, n);
			ResultSet rs = prepStat.executeQuery();
			while(rs.next()) {
				Treningsokt okt = new Treningsokter(rs.getDate("dato"), rs.getTime("tidspunt"), rs.getInt("varighet"), rs.getInt("personligForm"), rs.getInt("prestasjon"), rs.getString("notat"));
				okter.add(okt);
			}
			
			for (Treningsokter okt : okter) {
				Date id = okt.getDato();
				stmt = "select * from workoutcontainsexercise where dato = ?";
				prepStat = conn.prepareStatement(stmt);
				prepStat.setDate(1, id);
				while(rs.next()) {
					Ovelse ovelse = new Ovelse(rs.getString("exersicename"),rs.getInt("kilo"),rs.getInt("sett"));
					
					//Hen Ovelse beskrivelse
					String tmpStmt = "Select * from Ovelse where navn = ?";
					PreparedStatement pr = conn.prepareStatement(tmpStmt);
					pr.setString(1, rs.getString("ovelsesNavn"));
					ResultSet tmpRes = pr.executeQuery();
					ovelse.setDescription(tmpRes.getString("beskrivelse"));
					
					//Hvis øvelsen gjøres på en maskin, hent apparatinfo
					//hmm
					
					//Legg til øvelse i Treningsokter
					okt.addExercise(ovelse);
				}
				
			}
			return okter;
		}
			
		
		///////////////////////////Kravspesifikasjon 3///////////////////////////
		
		public static String getExerciseResult(Connection myConn, Date dateStart,Date dateEnd) throws SQLException{
			//TODO - BETWEEN FUNKER IKKE HER MED DATOENE...
			String min = "'" + dateStart.getYear() + "-" + (dateStart.getMonth()+1) + "-" + dateStart.getDate() + "'";
			String max = "'" + dateEnd.getYear() + "-" + (dateEnd.getMonth()+1) + "-" + dateEnd.getDate() + "'";
	        String query = "SELECT PERSONLIGFORM, VARIGHET FROM Treningsokter WHERE DATO BETWEEN ? AND ?";
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
		
		
		///////////////////////////Kravspesifikasjon 4///////////////////////////
		
		
		public static List<Ovelsesgruppe> getExerciseGroups(Connection conn) throws SQLException{
			List<Ovelsesgruppe> ovelsesGrupper = new ArrayList<Ovelsesgruppe>();
			
			//Spørr om alle koblingene mellom en Ovelse og en gruppe
			String stmt = "Select * from groupcontainsexersice";
			PreparedStatement prepStat = conn.prepareStatement(stmt);
			ResultSet rs = prepStat.executeQuery();
			
			//Lag en map hvor  er key og ArrayList med exercisenavn er value
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
			for(String  : map.keySet()) {
				List<Ovelse> ovelser = new ArrayList<Ovelse>();
				for(String ovelseNavn : map.get()) {
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
				ovelsesGrupper.add(new Ovelsesgruppe(gruppeNavn,ovelser));
			}
			return ovelsesGrupper;
		}
		
		
		///////////////////////////Kravspesifikasjon 5///////////////////////////
		
		
		//Skal hente ut hvor mange treningsøkter en har hatt totalt
		public static int getTotalWorkouts(Connection conn) throws SQLException {
			String stmt  = "select count(dato) as total from Treningsokter";
			PreparedStatement pr = conn.prepareStatement(stmt);
			ResultSet rs = pr.executeQuery();
			return rs.next() ? rs.getInt("total") : 0;
		}
	}
	
	
	
}

