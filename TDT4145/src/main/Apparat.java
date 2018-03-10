package main;

import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Apparat extends Connector {
	
	public Apparat() {
		Connector.connect();
	}
	
	public void leggTilApparat(TextField apparatNavn, TextArea beskrivelse) throws SQLException {
		 Statement stmt = conn.createStatement();
		 String sql = String.format("INSERT INTO `Apparat` VALUES ('%s', '%s')", apparatNavn.getText(), beskrivelse.getText());
		 stmt.executeUpdate(sql);
	}	
	
	public void test() throws SQLException {
	Statement stmt = conn.createStatement();
	String sql = String.format("INSERT INTO `Trening` VALUES (NULL, '%s', '%s', '%s', '%s', '%s')", "2010-12-12", "12:12:12", "12", "11", "12");
	//String sql = String.format("INSERT INTO `Trening` VALUES (NULL, '2018-03-14', '06:00:00', '32', '23', '23')");
	stmt.executeUpdate(sql);
	 System.out.println("Trening lagt til!");
	}
	
	public static void main(String[] args) throws SQLException {
		Apparat a = new Apparat();
		a.test();
	}
}