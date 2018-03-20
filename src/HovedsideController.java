import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HovedsideController {
	
	
	public HovedsideController() {
	}

	//Tilbakeknapp
	@FXML
	Button registrerButton;
	
	@FXML 
	public void handleRegistrerButton() throws SQLException, Exception {
        Stage stage; 
        Parent root;    
        stage=(Stage) registrerButton.getScene().getWindow();
        HovedsideController controller= new HovedsideController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Trening.fxml"));       
        loader.setController(controller);
        root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
}
