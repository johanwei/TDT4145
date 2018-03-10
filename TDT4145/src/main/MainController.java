package main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
	@FXML public TextField øvelseGruppeNavn;
	@FXML public Button øvelseGruppeNavnButton;
	
	@FXML public TextField øvelseMedApparatNavn;
	@FXML public TextField øvelseMedApparatApparatNavn;
	@FXML public TextField øvelseMedApparatGruppeNavn;
	@FXML public TextArea øvelseMedApparatBeskrivelse;
	@FXML public Button øvelseMedApparatButton;
	
	@FXML public TextField øvelseUtenApparatNavn;
	@FXML public TextField øvelseUtenApparatGruppeNavn;
	@FXML public TextArea øvelseUtenApparatBeskrivelse;
	@FXML public Button øvelseUtenApparatButton;
	
	//APPARAT
	@FXML public TextField apparatNavn;
	@FXML public TextArea apparatBeskrivelse;
	@FXML public Button apparatButton;
	@FXML public Label apparatOutput;
	
	//TRENINGSVARIGHET
	
	public MainController() {
		Connector.connect();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//TRENING
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
		
		//TRENINGSØKT
		
		//RESULTATLOGG
		
		//ØVELSEGRUPPE
		øvelseGruppeNavnButton.setOnAction((event) -> { 
			try {
				handleØvelseGruppeNavn(event);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}});
		øvelseMedApparatButton.setOnAction((event) -> { 
			try {
				handleØvelseMedApparat(event);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}});
		øvelseUtenApparatButton.setOnAction((event) -> { 
			try {
				handleØvelseUtenApparat(event);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}});
		
		//APPARAT
		apparatButton.setOnAction((event) -> { 
			try {
				handleApparat(event);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}});
		apparatOutput.setOpacity(0);
		
		//TRENINGSVARIGHET
	}
	
	//TRENING
	@FXML
    private void handleTrening(ActionEvent event) throws IOException, SQLException {
        leggInnTrening();
    }

	public void leggInnTrening() throws SQLException {
		Trening trening = new Trening();
		trening.leggInnTrening(this.treningID, this.dato, this.tidspunkt, this.varighet, this.personligForm, this.prestasjon, this.datoOutput, this.tidspunktOutput, this.varighetOutput, this.personligFormOutput, this.prestasjonOutput, this.generalOutput);
	}
	
	//TRENINGSØKT
	
	
	//RESULTATLOGG
	
	
	//ØVELSEGRUPPE
	@FXML
	private void handleØvelseGruppeNavn(ActionEvent event) throws IOException, SQLException {
		LeggInnØvelseGruppeNavn();
	}
	
	public void LeggInnØvelseGruppeNavn() throws SQLException{
		Ovelse ovelse = new Ovelse();
		ovelse.ovelseGruppe(this.øvelseGruppeNavn);
	}
	
	@FXML
	private void handleØvelseMedApparat(ActionEvent event) throws IOException, SQLException {
		LeggInnØvelseMedApparat();
	}
	
	public void LeggInnØvelseMedApparat() throws SQLException{
		Ovelse ovelse = new Ovelse();
		ovelse.ovelseMedApparat(this.øvelseMedApparatNavn, this.øvelseMedApparatApparatNavn, this.øvelseMedApparatGruppeNavn, this.øvelseMedApparatBeskrivelse, this.apparatOutput);
	}
	
	@FXML
	private void handleØvelseUtenApparat(ActionEvent event) throws IOException, SQLException {
		LeggInnØvelseUtenApparat();
	}
	
	public void LeggInnØvelseUtenApparat() throws SQLException{
		Ovelse ovelse = new Ovelse();
		ovelse.ovelseUtenApparat(this.øvelseUtenApparatNavn, this.øvelseUtenApparatBeskrivelse, this.øvelseUtenApparatGruppeNavn);
	}
	
	//APPARAT
	@FXML
	private void handleApparat(ActionEvent event) throws IOException, SQLException {
		leggInnApparat();
	}
	
	public void leggInnApparat() throws SQLException{
		Apparat apparat = new Apparat();
		apparat.leggTilApparat(this.apparatNavn, this.apparatBeskrivelse, this.apparatOutput);
	}
	
	//TRENINGSVARIGHET
	
	
	
	
	
	

}