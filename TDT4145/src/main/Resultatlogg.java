package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.DatePicker;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Resultatlogg extends Connector {
	
	public Resultatlogg() {
		Connector.connect();
	}
	
	public List<String> getOvelser() {
		List <String> ovelseListe = new ArrayList<String>();
		try {
			String sql = String.format("SELECT OvelseNavn FROM Ovelse");
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	        		ovelseListe.add(rs.getString("OvelseNavn"));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ovelseListe;
	}
	
	public ObservableList<ResultatloggObjekt> getData(ComboBox<String> resultatloggComboBox, DatePicker resultatloggFra, DatePicker resultatloggTil) throws SQLException, ParseException {
		//Hent ut treningsID fra TreningsOvelse hvor OvelseNavn = ComboBox.getSelectionModel().getSelectedItem()
		List <Integer> treningIdListe = new ArrayList<Integer>();
		String sql1 = String.format("SELECT TreningID FROM TreningOvelse WHERE OvelseNavn ='" + resultatloggComboBox.getSelectionModel().getSelectedItem() + "'");
        PreparedStatement stmt1 = conn.prepareStatement(sql1);
        ResultSet rs1 = stmt1.executeQuery();
        while (rs1.next()) {
        		treningIdListe.add(rs1.getInt(1));
        }
		
		//Join TreningsOvelse og TreningsNotat på TreningsID, Join denne med Trening, på TreningsID og Dato mellom resultatloggFra og resultatloggTil
        ObservableList<ResultatloggObjekt> resultatloggObjekter = FXCollections.observableArrayList();
        
        Date fra = java.sql.Date.valueOf(resultatloggFra.getValue());
		Date til = java.sql.Date.valueOf(resultatloggTil.getValue());
        
        String sql2 = String.format("SELECT Trening.TreningID, OvelseNavn, Dato, AntallKilo, AntallSett, AntallReps, Notat FROM TreningOvelse JOIN TreningNotat ON TreningOvelse.TreningID = TreningNotat.TreningID JOIN Trening ON TreningOvelse.TreningID = Trening.TreningID");
        PreparedStatement stmt2 = conn.prepareStatement(sql2);
        ResultSet rs2 = stmt2.executeQuery();
        while (rs2.next()) {
        		if (rs2.getDate(3).after(fra) && rs2.getDate(3).before(til)) {
        			for (int i = 1; i <= treningIdListe.size(); i++) {
        				if (rs2.getInt(1) == treningIdListe.get(i-1) && rs2.getString(2).equals(resultatloggComboBox.getSelectionModel().getSelectedItem())) {
        					resultatloggObjekter.add(new ResultatloggObjekt(rs2.getInt(1), rs2.getString(2), rs2.getDate(3).toString(), rs2.getInt(4), rs2.getInt(5), rs2.getInt(6), rs2.getString(7)));
        				}
        			}
        		}
        }
        return resultatloggObjekter;
	}		
	
	public boolean validateInput(ComboBox<String> resultatloggComboBox, Label resultatloggComboBoxOutput, Label resultatloggFraOutput, Label resultatloggTilOutput) {
		resultatloggComboBoxOutput.setOpacity(0);
		if (!resultatloggComboBox.getSelectionModel().isEmpty()) {
			return true;
		}
		else {
			resultatloggComboBoxOutput.setOpacity(100);
		}
		return false;
	}
}