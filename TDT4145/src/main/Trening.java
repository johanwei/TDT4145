package main;

import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Trening extends Connector {
	
	public Trening() {
		Connector.connect();
	}

	public void leggInnTrening(TextField treningID, TextField dato, TextField tidspunkt, TextField varighet, TextField personligForm, TextField prestasjon, Label datoOutput, Label tidspunktOutput, Label varighetOutput, Label personligFormOutput, Label prestasjonOutput, Label generalOutput) {
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
		 } catch (SQLException e){
			 e.printStackTrace();
		 }
	}
}