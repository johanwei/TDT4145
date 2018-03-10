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

public class MainController extends Connector implements Initializable {
	
	//TRENING
	@FXML public TextField treningID;
	@FXML public TextField dato;
	@FXML public TextField tidspunkt;
	@FXML public TextField varighet;
	@FXML public TextField personligForm;
	@FXML public TextField prestasjon;
	@FXML public Button leggInnTrening;
	@FXML public Label datoOutput;
	@FXML public Label tidspunktOutput;
	@FXML public Label varighetOutput;
	@FXML public Label personligFormOutput;
	@FXML public Label prestasjonOutput;
	@FXML public Label generalOutput;
	
	//TRENINGSØKT
	
	
	//RESULTATLOGG
	
	
	//ØVELSEGRUPPE
	
	
	//TRENINGSVARIGHET
	
	public MainController() {
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
		dato.setOnMouseClicked(event-> { 
			datoOutput.setOpacity(0);});
		tidspunkt.setOnMouseClicked(event-> { 
			tidspunktOutput.setOpacity(0);});
		varighet.setOnMouseClicked(event-> { 
			varighetOutput.setOpacity(0);});
		personligForm.setOnMouseClicked(event-> { 
			personligFormOutput.setOpacity(0);});
		prestasjon.setOnMouseClicked(event-> { 
			prestasjonOutput.setOpacity(0);});
	}
	
	@FXML
    private void handleTrening(ActionEvent event) throws IOException, SQLException {
        leggInnTrening();
    }

	public void leggInnTrening() throws SQLException {
		Trening trening = new Trening();
		trening.leggInnTrening(this.treningID, this.dato, this.tidspunkt, this.varighet, this.personligForm, this.prestasjon, this.datoOutput, this.tidspunktOutput, this.varighetOutput, this.personligFormOutput, this.prestasjonOutput, this.generalOutput);
	}

}