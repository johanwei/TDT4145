package main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController extends Connector implements Initializable {
	
	//TRENING
	@FXML public TextField treningID;
	@FXML public DatePicker dato;
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
	@FXML public TextArea notat;
	
	//TRENINGSØVELSE
	@FXML public TextField ovelseNavn;
	@FXML public TextField antallKilo;
	@FXML public TextField antallSett;
	@FXML public TextField antallReps;
	@FXML public Button leggInnOvelse;
	@FXML public Button leggInnNyTrening;
	@FXML public Label generalOvelseOutput;
	
	//TRENINGSØKT
	@FXML public TextField antallTreningsOkter;
	@FXML public Button visTreninger;
	@FXML public TableView<TreningsOktObjekt> treningOversikt;
	@FXML private TableColumn<TreningsOktObjekt, String> treningIDCol;
	@FXML private TableColumn<TreningsOktObjekt, String> datoCol;
	@FXML private TableColumn<TreningsOktObjekt, String> tidspunktCol;
	@FXML private TableColumn<TreningsOktObjekt, Integer> varighetCol;
	@FXML private TableColumn<TreningsOktObjekt, String> personligFormCol;
	@FXML private TableColumn<TreningsOktObjekt, String> prestasjonCol;
	@FXML private TableColumn<TreningsOktObjekt, String> notatCol;

	//ØVELSER
	@FXML public ListView<String> ovelseGruppe;
	@FXML public ListView<String> ovelser;
	@FXML public Button velgOvelseGruppe;
	@FXML public Button oppdaterOvelseGrupper;
	
	//RESULTATLOGG
	@FXML public ComboBox<String> resultatloggComboBox;
	@FXML public DatePicker resultatloggFra;
	@FXML public DatePicker resultatloggTil;
	@FXML public Button resultatLoggButton;
	
	@FXML public Label resultatloggComboBoxOutput;
	@FXML public Label resultatloggFraOutput;
	@FXML public Label resultatloggTilOutput;
	
	@FXML public TableView<ResultatloggObjekt> resultatloggTableView;
	@FXML public TableColumn<ResultatloggObjekt, Integer> resultatloggTableViewTreningId;
	@FXML public TableColumn<ResultatloggObjekt, String> resultatloggTableViewØvelse;
	@FXML public TableColumn<ResultatloggObjekt, String> resultatloggTableViewDato;
	@FXML public TableColumn<ResultatloggObjekt, Integer> resultatloggTableViewKilo;
	@FXML public TableColumn<ResultatloggObjekt, Integer> resultatloggTableViewSett;
	@FXML public TableColumn<ResultatloggObjekt, Integer> resultatloggTableViewReps;
	@FXML public TableColumn<ResultatloggObjekt, String> resultatloggTableViewNotat;	
	
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
	@FXML public DatePicker treningsvarighetFra;
	@FXML public DatePicker treningsvarighetTil;
	@FXML public Button treningsvarighetButton;
	@FXML public TextArea treningsvarighetOutput;
		
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
			}
		});
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
		
		
		//TRENINGSØVELSE
		leggInnOvelse.setOnAction((event) -> { 
			try {
				handleOvelse(event);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		});
		ovelseNavn.setOnMouseClicked(event-> { 
			generalOvelseOutput.setOpacity(0);;});
		
		leggInnNyTrening.setOnAction((event) -> { 
			dato.setDisable(false);
			tidspunkt.setDisable(false);
			varighet.setDisable(false);
			personligForm.setDisable(false);
			prestasjon.setDisable(false);
			leggInnTrening.setDisable(false);
			notat.setDisable(false);
			ovelseNavn.setDisable(true);
			antallKilo.setDisable(true);
			antallSett.setDisable(true);
			antallReps.setDisable(true);
			leggInnOvelse.setDisable(true);
			leggInnNyTrening.setDisable(true);
			generalOvelseOutput.setOpacity(0);
		});
		
		//TRENINGSØKT
		visTreninger.setOnAction((event) -> { 
			try {
				handleGjennomforTrening(event);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		});
		
		//ØVELSER
		oppdaterOvelseGrupper.setOnAction((event) -> { 
			try {
				handleOppdaterOvelseGruppe(event);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		});
		velgOvelseGruppe.setOnAction((event) -> { 
			try {
				handleOvelser(event);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		});
		
		//RESULTATLOGG
		resultatloggComboBox.getItems().addAll(resultatloggGetOvelser());
		resultatLoggButton.setOnAction((event) -> { 
				try {
					handleFinnResultatlogg(event);
				} catch (IOException | SQLException | ParseException e) {
					e.printStackTrace();
				}
			});
		
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
		treningsvarighetButton.setOnAction((event) -> { 
			try {
				handleTreningsVarighet(event);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}});
	}
	
	//TRENING
	@FXML
    private void handleTrening(ActionEvent event) throws IOException, SQLException {
        leggInnTrening();
        treningNotat();
    }

	public void leggInnTrening() throws SQLException {
		Trening trening = new Trening();
		if (trening.leggInnTrening(this.dato, this.tidspunkt, this.varighet, this.personligForm, this.prestasjon, this.datoOutput, this.tidspunktOutput, this.varighetOutput, this.personligFormOutput, this.prestasjonOutput, this.generalOutput)) {
			 dato.setDisable(true);
			 tidspunkt.setDisable(true);
			 varighet.setDisable(true);
			 personligForm.setDisable(true);
			 prestasjon.setDisable(true);
			 leggInnTrening.setDisable(true);
			 notat.setDisable(true);
			 ovelseNavn.setDisable(false);
			 antallKilo.setDisable(false);
			 antallSett.setDisable(false);
			 antallReps.setDisable(false);
			 leggInnOvelse.setDisable(false);
		}
	}
	
	public void treningNotat() throws SQLException {
		new Trening().treningNotat(notat);
	}
	
	//TRENINGSØVELSE
	@FXML
	private void handleOvelse(ActionEvent event) throws IOException, SQLException {
		treningOvelse();
	}
	
	public void treningOvelse() throws SQLException, IOException {
		if(new Trening().treningOvelse(ovelseNavn, antallKilo, antallSett, antallReps, generalOvelseOutput)) {
			leggInnNyTrening.setDisable(false);
		}
	}

	//TRENINGSØKT
	@FXML
	private void handleGjennomforTrening(ActionEvent event) throws IOException, SQLException {
		gjennomfortTrening();
	}
	
	@SuppressWarnings("unchecked")
	public void gjennomfortTrening() throws SQLException {
		treningOversikt.getColumns().clear();
		treningOversikt.getItems().clear();
		treningIDCol.setCellValueFactory(new PropertyValueFactory<>("treningID"));
        datoCol.setCellValueFactory(new PropertyValueFactory<>("dato"));
        tidspunktCol.setCellValueFactory(new PropertyValueFactory<>("tidspunkt"));
        varighetCol.setCellValueFactory(new PropertyValueFactory<>("varighet"));
        personligFormCol.setCellValueFactory(new PropertyValueFactory<>("personligForm"));
        prestasjonCol.setCellValueFactory(new PropertyValueFactory<>("prestasjon"));
        notatCol.setCellValueFactory(new PropertyValueFactory<>("notat"));

        treningOversikt.getColumns().addAll(treningIDCol, datoCol, tidspunktCol, varighetCol,  personligFormCol, prestasjonCol, notatCol);
        
        ObservableList<TreningsOktObjekt> listOfTreningsOkter = new Trening().gjennomfortTrening(antallTreningsOkter.getText());
        treningOversikt.getItems().addAll(listOfTreningsOkter);
}
	
	//ØVELSER
	@FXML
	private void handleOppdaterOvelseGruppe(ActionEvent event) throws IOException, SQLException {
		oppdaterOvelse();
	}
	
	@FXML
	private void handleOvelser(ActionEvent event) throws IOException, SQLException {
		ovelser();
	}
	
	public void oppdaterOvelse() throws SQLException {
		new Ovelse().oppdaterOvelse(ovelseGruppe);
	}
	
	
	public void ovelser() throws SQLException {
		new Ovelse().ovelser(ovelseGruppe, ovelser);
	}
	
	
	
	//RESULTATLOGG
	
	public List<String> resultatloggGetOvelser() {
		Resultatlogg resultatlogg = new Resultatlogg();
		return resultatlogg.getOvelser();
	}
	
	@FXML
	private void handleFinnResultatlogg(ActionEvent event) throws IOException, SQLException, ParseException {
		FinnResultatlogg(this.resultatloggComboBox, this.resultatloggFra, this.resultatloggTil, this.resultatloggComboBoxOutput, this.resultatloggFraOutput, this.resultatloggTilOutput);
	}
	
	public void FinnResultatlogg(ComboBox<String> resultatloggComboBox, DatePicker resultatloggFra, DatePicker resultatloggTil, Label resultatloggComboBoxOutput, Label resultatloggFraOutput, Label resultatloggTilOutput) throws SQLException, ParseException{
		resultatloggTableView.getItems().clear();
		resultatloggTableViewTreningId.setCellValueFactory(new PropertyValueFactory<>("treningId"));
		resultatloggTableViewØvelse.setCellValueFactory(new PropertyValueFactory<>("ovelse"));
		resultatloggTableViewDato.setCellValueFactory(new PropertyValueFactory<>("dato"));
		resultatloggTableViewKilo.setCellValueFactory(new PropertyValueFactory<>("kilo"));
		resultatloggTableViewSett.setCellValueFactory(new PropertyValueFactory<>("sett"));
		resultatloggTableViewReps.setCellValueFactory(new PropertyValueFactory<>("reps"));
		resultatloggTableViewNotat.setCellValueFactory(new PropertyValueFactory<>("notat"));
        
		Resultatlogg resultatlogg = new Resultatlogg();
		resultatlogg.validateInput(resultatloggComboBox, resultatloggComboBoxOutput, resultatloggFraOutput, resultatloggTilOutput);
		
		ObservableList<ResultatloggObjekt> resultatloggInformasjon = new Resultatlogg().getData(this.resultatloggComboBox, this.resultatloggFra, this.resultatloggTil);		
		resultatloggTableView.getItems().addAll(resultatloggInformasjon);
	}
	
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
	
	@FXML private void handleTreningsVarighet(ActionEvent event) throws IOException, SQLException {
		treningsVarighet(this.treningsvarighetFra, this.treningsvarighetTil, this.treningsvarighetOutput);
	}
	
	public void treningsVarighet(DatePicker treningsvarighetFra, DatePicker treningsvarighetTil, TextArea treningsvarighetOutput) throws SQLException {
		Treningsvarighet treningsvarighet = new Treningsvarighet();
		treningsvarighet.calculateTreningsVarighet(treningsvarighetFra, treningsvarighetTil, treningsvarighetOutput);
	}

}