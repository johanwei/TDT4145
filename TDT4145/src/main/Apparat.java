package main;

import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Apparat extends Connector {
	
	public void leggTilApparat(TextField apparatNavn, TextArea beskrivelse, Label apparatOutput) throws SQLException {
		 Statement stmt = conn.createStatement();
		 String sql = String.format("INSERT INTO `Apparat` VALUES ('%s', '%s')", apparatNavn.getText(), beskrivelse.getText());
		 stmt.executeUpdate(sql);
		 apparatOutput.setText("Apparat lagt inn");
		 apparatOutput.setOpacity(100);
	}	
}