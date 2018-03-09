package main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Trening extends Connector implements Initializable {
	
	@FXML private TextField treningID;
	@FXML private TextField dato;
	@FXML private TextField tidspunkt;
	@FXML private TextField varighet;
	@FXML private TextField personligForm;
	@FXML private TextField prestasjon;
	@FXML private Button leggInnTrening;
	@FXML private Label lagtTilOutput;
	
	public Trening() {
		Connector.connect();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		leggInnTrening.setOnAction((event) -> { 
			try {
				handleTrening(event);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}});
		treningID.setOnMouseClicked(event-> { 
			lagtTilOutput.setOpacity(0);});
		dato.setOnMouseClicked(event-> { 
			lagtTilOutput.setOpacity(0);});
		tidspunkt.setOnMouseClicked(event-> { 
			lagtTilOutput.setOpacity(0);});
		varighet.setOnMouseClicked(event-> { 
			lagtTilOutput.setOpacity(0);});
		personligForm.setOnMouseClicked(event-> { 
			lagtTilOutput.setOpacity(0);});
		prestasjon.setOnMouseClicked(event-> { 
			lagtTilOutput.setOpacity(0);});
	}
	
	@FXML
    private void handleTrening(ActionEvent event) throws IOException, SQLException {
        leggInnTrening();
    }

	public void leggInnTrening() throws SQLException {
		 Statement stmt = conn.createStatement();
		 String sql = String.format("INSERT INTO `Trening` VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", treningID.getText(), dato.getText(), tidspunkt.getText(), varighet.getText(), personligForm.getText(), prestasjon.getText());
		 stmt.executeUpdate(sql);
		 System.out.println("Trening lagt til!");
		 lagtTilOutput.setOpacity(1);
	}

}