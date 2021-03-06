import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HentDBInfo {

	public HentDBInfo() {
		
	}
	
	@FXML
	Button velg1, velg2, velg3;
	
	@FXML
	TextField seOktFelt, seResultatloggFelt, seØvelseFelt;
	
	
	@FXML 
	public void handleVelg1Button() throws SQLException, Exception {
		String resultat = "Dato \t\t Tidspunkt \t Varighet \t Form \t Prestasjon \t Notat\n";
       int antall = Integer.parseInt(seOktFelt.getText());
       System.out.println(antall);
       Connection myConn = ConnectionSQL.connect();
       List<Treningsokt> treningsokter = AdminController.getNTreningsokter(myConn, antall);
       //System.out.println("De " + antall + "siste treningsøktene: ");
       for (Treningsokt treningsokt : treningsokter) {
    	   resultat += treningsokt.getDato().toString() + "\t";
  		resultat += treningsokt.getTidspunkt().toString() + "            ";
  		resultat += treningsokt.getVarighet() + "\t\t  ";
    		resultat += treningsokt.getPersonligForm() + "\t\t";
    		resultat += treningsokt.getPrestasjon() + "\t\t\t";
    		resultat += treningsokt.getNotat() + "\n";
    	   
    	   
       }
       System.out.println(resultat);
	}
	
	
	@FXML 
	public void handleVelg2Button() throws SQLException, Exception {
		
		List<String> input = Arrays.asList(seResultatloggFelt.getText().split(","));
		Connection myConn = ConnectionSQL.connect();
		List<String> dateString1 = Arrays.asList(input.get(0).split("-"));
		List<String> dateString2 = Arrays.asList(input.get(1).split("-"));
		int day1 = Integer.parseInt(dateString1.get(0));
		int month1 = Integer.parseInt(dateString1.get(1));
		int year1 = Integer.parseInt(dateString1.get(2));
		int day2 = Integer.parseInt(dateString2.get(0));
		int month2 = Integer.parseInt(dateString2.get(1));
		int year2 = Integer.parseInt(dateString2.get(2));
       Date dateStart = new Date(year1, month1, day1);
       Date dateEnd = new Date(year2, month2, day2);
       String resultatLogg = AdminController.getOvelsesResultat(myConn, dateStart, dateEnd);
       System.out.println(resultatLogg);
       
	}
	
	
	@FXML 
	public void handleVelg3Button() throws SQLException, Exception {
		String resultat = "Øvelsesgruppe \t Øvelse\n";
       Connection myConn = ConnectionSQL.connect();
       List<Ovelsesgruppe> grupper = AdminController.getOvelsesgruppe(myConn);
       for (Ovelsesgruppe ovelsesgruppe : grupper) {
    	   		for (Ovelse øvelse : ovelsesgruppe.getOvelser()) {
    	   			resultat += ovelsesgruppe.getNavn() + "\t\t\t" + øvelse.getNavn() + "\n";
    	   		}
 
       }
       System.out.println(resultat);
       }
       
	
	@FXML
	Button returnButton;
	
	@FXML
	public void handleReturnButton() throws SQLException, Exception {
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) returnButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Hovedside.fxml"));
        root = (Parent) loader.load();
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	
}
