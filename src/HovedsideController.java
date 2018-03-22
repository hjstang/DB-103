import java.io.IOException;
import java.sql.Connection;
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

	@FXML
	Button registrerButton;
	
	@FXML
	Button seInfoButton;
	
	@FXML
	Button krav5;
	
	@FXML 
	public void handleRegistrerButton() throws SQLException, Exception {
        Stage stage; 
        Parent root;    
        stage=(Stage) registrerButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Trening.fxml"));       
        root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML 
	public void handleKrav5Button() throws SQLException, Exception {
		Connection myConn = ConnectionSQL.connect();
		System.out.println(AdminController.getPrecentBiggerThan5(myConn));
	}
	
	@FXML 
	public void handleSeInfoButton() throws SQLException, Exception {
        Stage stage; 
        Parent root;    
        stage=(Stage) seInfoButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HentDBInfo.fxml"));       
        root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
}
