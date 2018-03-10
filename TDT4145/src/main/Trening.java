package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Trening extends Connector {
	
	public Trening() {
		Connector.connect();
	}

	public boolean leggInnTrening(TextField dato, TextField tidspunkt, TextField varighet, TextField personligForm, TextField prestasjon, Label datoOutput, Label tidspunktOutput, Label varighetOutput, Label personligFormOutput, Label prestasjonOutput, Label generalOutput) {
		 int i = 0;
		 while (i == 0) {
			 datoOutput.setOpacity(0);
			 tidspunktOutput.setOpacity(0);
			 varighetOutput.setOpacity(0);
			 personligFormOutput.setOpacity(0);
			 prestasjonOutput.setOpacity(0);
			 if (!dato.getText().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
				 datoOutput.setText("(Dato må være på formatet 'åååå-mm-dd)'");
				 datoOutput.setOpacity(100);
			 }
			 if (!tidspunkt.getText().matches("([0-9]{2}):([0-9]{2}):([0-9]{2})")) {
				 tidspunktOutput.setText("(Tidspunkt må være på formatet 'tt:mm:ss')");
				 tidspunktOutput.setOpacity(100);
			 }
			 if (varighet.getText().equals("")) {
				 varighetOutput.setText("(Varighet må ha en verdi)");
				 varighetOutput.setOpacity(100);
			 }
			 if (personligForm.getText().equals("")) {
				 personligFormOutput.setText("(Personlig form må ha en verdi)");
				 personligFormOutput.setOpacity(100);
			 }
			 if (prestasjon.getText().equals("")) {
				 prestasjonOutput.setText("(Prestasjon må ha en verdi)");
				 prestasjonOutput.setOpacity(100);
			 }
			 i = 1;
		 }
		 try {
			 Statement stmt = conn.createStatement();
			 String sql = String.format("INSERT INTO `Trening` VALUES (NULL, '%s', '%s', '%s', '%s', '%s')", dato.getText(), tidspunkt.getText(), varighet.getText(), personligForm.getText(), prestasjon.getText());
			 stmt.executeUpdate(sql);
			 generalOutput.setText("Trening har blitt lagt til!");
			 generalOutput.setOpacity(100);
			 return true;
		 } catch (SQLException e){
			 e.printStackTrace();
		 }
		 return false;
	}
	
	
	public boolean treningOvelse(TextField ovelseNavn, TextField antallKilo, TextField antallSett, TextField antallReps, Label generalOvelseOutput) throws SQLException {
		String query = String.format("SELECT TreningID FROM Trening ORDER BY TreningID DESC LIMIT 1");
		PreparedStatement stmt = Connector.conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		String treningID = "";
		if (rs.next()) {
			treningID = rs.getString(1);
		}
		Statement stmt1 = conn.createStatement();
		String sql = String.format("INSERT INTO `TreningOvelse` VALUES ('%s', '%s', '%s', '%s', '%s')", treningID, ovelseNavn.getText(), antallKilo.getText(), antallSett.getText(), antallReps.getText());
		stmt1.executeUpdate(sql);
		generalOvelseOutput.setText("Øvelse lagt til");
		generalOvelseOutput.setOpacity(100);
		return true;
	}
	
	public void treningNotat(TextArea notat) throws SQLException {
		String query = String.format("SELECT TreningID FROM Trening ORDER BY TreningID DESC LIMIT 1");
		PreparedStatement stmt = Connector.conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		String treningID = "";
		if (rs.next()) {
			treningID = rs.getString(1);
		}
		Statement stmt1 = conn.createStatement();
		String sql = String.format("INSERT INTO `TreningNotat` VALUES ('%s', '%s')", notat.getText(), treningID);
		stmt1.executeUpdate(sql);
	}
	
	public List<String> gjennomfortTrening(TextField antallTreningsOkter) throws SQLException {
		String query = String.format("SELECT * FROM Trening ORDER BY TreningID DESC");
		PreparedStatement stmt = Connector.conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		List<String> treninger = new ArrayList<>();
		int i = 0;
		while (rs.next() && i < Integer.parseInt(antallTreningsOkter.getText())) {
			String trening = "";
			trening += rs.getString(1) + "       ";
			trening += rs.getString(2)+ "            ";
			trening += rs.getString(3)+ "            ";
			trening += rs.getString(4)+ "            ";
			trening += rs.getString(5)+ "            ";
			trening += rs.getString(6)+ "              ";
			treninger.add(trening);
			i++;
		}
		String query2 = String.format("SELECT * FROM TreningNotat");
		PreparedStatement stmt2 = Connector.conn.prepareStatement(query2);
		ResultSet rs2 = stmt2.executeQuery();
		while(rs2.next()) {
			for (int j = 0; j < Integer.parseInt(antallTreningsOkter.getText()); j++) {
				if(treninger.get(j).split(" ")[0].equals(rs2.getString(2))) {
					String trening = treninger.remove(j);
					trening += " " + rs2.getString(1);
					treninger.add(j, trening);
				}
			}
		}
		return treninger;
	}
	
	public static void main(String[] args) throws SQLException {
		//new Trening().gjennomfortTrening(3);
	}
	
}