import java.sql.*;

public class ConnectionSQL {
	private final String dbURL="jdbc:mysql://mysql.stud.ntnu.no/noragk_db_treningsdagbok?user=noragk_db&password=Norag0808";

	private Connection getConnection() throws SQLException{
		return DriverManager.getConnection(dbURL);
	}
	
	private Statement getStatement() throws SQLException{
		Connection myConn=getConnection();
		return myConn.createStatement();
	}
	
	public ResultSet runQuery(String query) throws SQLException{//Kjører en generell query
		Statement myStmt=getStatement();
		return myStmt.executeQuery(query);
	}
	
	public void runUpdate(String query) throws SQLException{//Oppdaterer/endrer rader
		Statement myStmt=getStatement();
		myStmt.executeUpdate(query);
	}
	
	public String printTreningsøkt(ResultSet myRs) throws SQLException{
		String format="|%-20s|%-20s|%-20s|%-20s|%-20s|%-60s|\n";
		String out=String.format(format, "Dato","Tidspunkt","Varighet","InfoOmØvelse","PersonligForm","Prestasjon");
		out+="+---------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n";
		
		while(myRs.next()){
			out+=String.format(format,myRs.getString("Dato"),myRs.getString("Tidspunkt"),myRs.getString("Varighet"),
		myRs.getString("InfoOmØvelse"),myRs.getString("PersonligForm"),myRs.getString("Prestasjon"));
		}
		out+="+---------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n";
		return out;
	}
	
	public String printØvelse(ResultSet myRs) throws SQLException{
		String format="|%-20s|%-20s|%-20s|\n";
		String out=String.format(format, "Navn");
		out+="+--------------------------------------------------------------+\n";
		
		while(myRs.next()){
			out+=String.format(format,myRs.getString("Navn"));
		}
		out+="+--------------------------------------------------------------+\n";
		return out;
	}
	
	public String printNotat(ResultSet myRs) throws SQLException{
		String format="|%-20s|\n";
		String out="+--------------------+\n";
		out+=String.format(format, "Treningsformål", "Opplevelse");
		out+="+--------------------+\n";
		
		while(myRs.next()){
			out+=String.format(format,myRs.getString("Treningsformål"), myRs.getString("Opplevelse"));
		}
		out+="+--------------------+\n";
		return out;
	}
	
	
	
	String eks="Insert into <tabell> (navnkol1,navnkol2...,navnkoln) values ('verdikol1','verdicol2',...)";
	public void update(String table,String kolstr, String verdistr) throws SQLException{//oppdaterer verdienen i
		//gitte kollonner med gitte verdier
		String sql=table+kolstr+"values"+verdistr;
		Statement myState=getStatement();
		myState.executeUpdate(sql);
		System.out.println("Insert complete");
	}
	/*public static void main(String[] args) throws SQLException {
		ConnectionSQL q=new ConnectionSQL();
		System.out.println(q.getStatistikk());
	}*/
	

	public String getRapport() throws SQLException {
		Statement myStmt=getStatement();
		//Utholdenhetsøkt
		ResultSet myRes=myStmt.executeQuery("select Dato, Tidspunkt, Varighet, InfoOmØvelse, PersonligForm, Prestasjon, "
				+ "ØvelseNavn, Tid, Avstand from noragk_db_Treningsdagbok.Treningsøkt join "
				+ "noragk_db_databaseprojekt.Treningsøkt where Dato=ØktDato And Tidspunkt=ØktTidspunkt"
				+ " and Dato >= curdate() - INTERVAL DAYOFWEEK(curdate())+6 DAY order by Dato DESC,"
				+ " PersonligForm desc limit 1;");
		String format="|%-20s|%-20s|%-20s|%20s|%20s|%-60s|%-20s|%20s|%20s|\n";
		String out="Dette er den beste utholdenhets- og styrkeøkten denne uken:\n \n";
		out+="+";
		for(int i=0;i<(8*20+60+8);i++){
			out+="-";
		}
		out+="+\n";
		out+=String.format(format, "Dato","Tidspunkt","Varighet","infoOmØvelse","PeersonligForm","Prestasjon",
				"ØvelseNavn","Tid","Avstand");
		out+="+";
		for(int i=0;i<(8*20+60+8);i++){
			out+="-";
		}
		out+="+\n";
		while(myRes.next()){
			out+=String.format(format,myRes.getString("Dato"),myRes.getString("Klokkeslett"),
					myRes.getString("Varighet"),myRes.getString("PersonligForm"),myRes.getString("Prestasjon"),
					myRes.getString("Notat"),myRes.getString("ØvelseNavn"),myRes.getString("Tid"),
					myRes.getString("Avstand"));
		}
		out+="+";
		for(int i=0;i<(8*20+60+8);i++){
			out+="-";
		}
		out+="+\n";
		out+="\n";
		out+="\n";
		out+="+";
		for(int i=0;i<(9*20+60+9);i++){
			out+="-";
		}
		out+="+\n";
		//Styrkeøkt
		ResultSet myRes1=myStmt.executeQuery("select Treningsøkt.Dato, Treningsøkt.Tidspunkt, Varighet, PersonligForm, Prestasjon,"
				+ " Notat.Treningsformål, Notat.Opplevelse from noragk_db_treningsdagbok.Treningsøkt join"
				+ " noragk_db_treningsdagbok.Notat where Økt.Dato=Notat.Dato And"
				+ " Treningsøkt.Tidspunkt=Notat. and Økt.Dato >= curdate() - INTERVAL DAYOFWEEK(curdate())+6"
				+ " DAY order by Prestasjon DESC, PersonligForm desc limit 1;");
		String format1="|%-20s|%-20s|%-20s|%-20s|%-20s|%-60s|%-20s|%20s|%20s|%20s|\n";
		out+=String.format(format1, "Dato","Klokkeslett","Varighet","PersonligForm","Prestasjon","Notat",
				"ØvelseNavn","AntallKilo","AntallSet","Belastning");
		out+="+";
		for(int i=0;i<(9*20+60+9);i++){
			out+="-";
		}
		out+="+\n";
		while(myRes1.next()){
			out+=String.format(format1,myRes1.getString("Dato"),myRes1.getString("Klokkeslett"),
					myRes1.getString("Varighet"),myRes1.getString("PersonligForm"),myRes1.getString("Prestasjon"),
					myRes1.getString("InfoOmØvelse"),myRes1.getString("Navn"),myRes1.getString("AntallKilo"),
					myRes1.getString("AntallSett"),myRes1.getString("Belasting"));
		}
		out+="+";
		for(int i=0;i<(9*20+60+9);i++){
			out+="-";
		}
		out+="+";
		return out;
	}

	public String getStatistikk() throws SQLException {
		// TODO Tabell med en rad mange kollonner 
		// antall timr, antall øker, gjen pres, gjenom form
		String out="\n";
		String format="|%20s|%20s|%20s|%20s|\n";
		out+="+";
		for (int i=0;i<(4*20+3);i++){
			out+="-";
		}
		out+="+\n";
		out+=String.format(format, "Antall timer", "Antall Økter", "Gj.snitt pres", "Gjennomsnittform")+"+";
		for (int i=0;i<(4*20+3);i++){
			out+="-";
		}
		out+="+\n";
		Statement myStmt=getStatement();
		ResultSet myRes=myStmt.executeQuery("select SUM(Varighet), count(*), avg(Prestasjon), avg(PersonligForm) from Økt where Dato >= curdate() - INTERVAL DAYOFWEEK(curdate())+30 DAY;");
		while(myRes.next()){
			String antTimer=myRes.getString("SUM(Varighet)"); 
			antTimer = antTimer.substring(0, antTimer.length()-4);
			out+=String.format(format,antTimer,myRes.getString("Count(*)"),
					myRes.getString("avg(Prestasjon)").substring(0,3),myRes.getString("avg(PersonligForm)").substring(0,3));
		}
		out+="+";
		for (int i=0;i<(4*20+3);i++){
			out+="-";
		}
		out+="+\n";
		return out;
		
	}

	
}
