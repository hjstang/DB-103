import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
       int antall = Integer.parseInt(seOktFelt.getText());
       Connection myConn = new Main().connect();
       List<Treningsokt> resultater = AdminController.getNTreningsokter(myConn, antall);
       System.out.println("De " + antall + "siste treningsøktene: ");
       for (Treningsokt resultat : resultater) {
    	   System.out.println(resultat);
       }
       
	}
	
	@FXML 
	public void handleVelg2Button() throws SQLException, Exception {
		
		List<String> input = Arrays.asList(seResultatloggFelt.getText().split(","));
		Connection myConn = new Main().connect();
		List<String> dateString1 = Arrays.asList(input.get(0).split("."));
		List<String> dateString2 = Arrays.asList(input.get(1).split("."));
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
		String ovelsesGruppe = seØvelseFelt.getText();
       Connection myConn = new Main().connect();
       List<Ovelse> ovelserIGruppe = new ArrayList();
       List<Ovelsesgruppe> grupper = AdminController.getOvelsesgruppe(myConn);
       for (Ovelsesgruppe ovelsesgruppe : grupper) {
    	   		if (ovelsesgruppe.equals(ovelsesGruppe)) {
    	   			ovelserIGruppe = ovelsesgruppe.getOvelser();
    	   			
    	   		}
       }
       System.out.println("Øvelsene i" + ovelsesGruppe + "er: ");
       for (Ovelse ovelse : ovelserIGruppe) {
    	   System.out.println(ovelse);
       }
       
	}
	
	@FXML
	Button returnButton;
	
	@FXML
	public void handleReturnButton() throws SQLException, Exception {
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) returnButton.getScene().getWindow();
        
        //HovedsideController controller= new HovedsideController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Hovedside.fxml"));
            
        //loader.setController(controller); //Smeller den kontrolleren inn i fxmlfilen

        root = (Parent) loader.load();
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	
}
