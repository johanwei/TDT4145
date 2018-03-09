package main;

import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Apparat extends Connector {
	
	@FXML private TextField apparatNavn;
	@FXML private TextArea beskrivelse;

	public Apparat() {
		Connector.connect();
	}
	
	public void leggTilApparat() throws SQLException {
		 Statement stmt = conn.createStatement();
		 String sql = String.format("INSERT INTO `Apparat` VALUES ('%s', '%s')", apparatNavn.getText(), beskrivelse.getText());
		 stmt.executeUpdate(sql);
	}	
}