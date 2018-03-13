package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Ovelse extends Connector {

	public Ovelse() {
		Connector.connect();
	}
	//Test
	
	public void ovelseGruppe(TextField ovelseGruppeNavn) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = String.format("INSERT INTO `OvelseGruppe` VALUES ('%s')", ovelseGruppeNavn.getText());
		stmt.executeUpdate(sql);
	}
	
	
	public void ovelseMedApparat(TextField ovelseNavn, TextField apparatNavn, TextField ovelseGruppeNavn, TextArea beskrivelse, Label apparatOutput) throws SQLException{
		if (notExists("Apparat", "ApparatNavn", apparatNavn.getText())){
			new Apparat().leggTilApparat(apparatNavn, beskrivelse, apparatOutput);
		}
		if (notExists("OvelseGruppe", "OvelseGruppeNavn", ovelseGruppeNavn.getText())){
			ovelseGruppe(ovelseGruppeNavn);
		}
		if (notExists("Ovelse", "OvelseNavn", ovelseNavn.getText())) {
			Statement stmt = conn.createStatement();
			String sql = String.format("INSERT INTO Ovelse VALUES ('%s', '%s')", ovelseNavn.getText(), ovelseGruppeNavn.getText());
			stmt.executeUpdate(sql);
		} else {
			throw new IllegalArgumentException("Denne øvelsen eksisterer allerede!");
		}
		Statement stmt1 = conn.createStatement();
		String sql1 = String.format("INSERT INTO OvelseMedApparat VALUES ('%s', '%s', '%s')", ovelseNavn.getText(), apparatNavn.getText(), ovelseGruppeNavn.getText());
		stmt1.executeUpdate(sql1);
	}
	
	
	public void ovelseUtenApparat(TextField ovelseNavn, TextArea beskrivelse, TextField ovelseGruppeNavn) throws SQLException {
		if (notExists("OvelseGruppe", "OvelseGruppeNavn", ovelseGruppeNavn.getText())){
			ovelseGruppe(ovelseGruppeNavn);
		}
		if (notExists("Ovelse", "OvelseNavn", ovelseNavn.getText())) {
			Statement stmt = conn.createStatement();
			String sql = String.format("INSERT INTO Ovelse VALUES ('%s', '%s')", ovelseNavn.getText(), ovelseGruppeNavn.getText());
			stmt.executeUpdate(sql);
		} else {
			throw new IllegalArgumentException("Denne øvelsen eksisterer allerede!");
		}
		Statement stmt1 = conn.createStatement();
		String sql1 = String.format("INSERT INTO OvelseUtenApparat VALUES ('%s', '%s', '%s')", ovelseNavn.getText(), beskrivelse.getText(), ovelseGruppeNavn.getText());
		stmt1.executeUpdate(sql1);
	}
	
	
	public boolean notExists(String table, String attribute, String name) throws SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s='%s'", table, attribute, name);
		PreparedStatement stmt = Connector.conn.prepareStatement(query);
		System.out.println(query);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			return false;
		}
		return true;
	}
	
	
	public void oppdaterOvelse(ListView<String> ovelseGruppe) throws SQLException {
		ovelseGruppe.getItems().clear();
		String query = String.format("SELECT OvelseGruppeNavn FROM OvelseGruppe");
		PreparedStatement stmt = Connector.conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			ovelseGruppe.getItems().add(rs.getString(1));
		}
	}
	
	
	public void ovelser(ListView<String> ovelseGruppe, ListView<String> ovelse) throws SQLException {
		ovelse.getItems().clear();
		String gruppe = ovelseGruppe.getSelectionModel().getSelectedItem();
		String query = String.format("SELECT * FROM Ovelse");
		PreparedStatement stmt = Connector.conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			if (gruppe.equals(rs.getString(2))) {
				ovelse.getItems().add(rs.getString(1));
			}
		}
		
	}
}



