package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

public class Treningsvarighet extends Connector{
	
	
	public void calculateTreningsVarighet(DatePicker treningsvarighetFra, DatePicker treningsvarighetTil, TextArea treningsvarighetOutput) throws SQLException {
		treningsvarighetOutput.setText("");
		Date fra = java.sql.Date.valueOf(treningsvarighetFra.getValue());
		Date til = java.sql.Date.valueOf(treningsvarighetTil.getValue());
		List <Integer> treningsvarigheter = new ArrayList<Integer>();
		
		String sql = String.format("SELECT Varighet, Dato FROM Trening");
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
        		if (rs.getDate(2).after(fra) && rs.getDate(2).before(til)) {
        			treningsvarigheter.add(rs.getInt(1));
        		}
        }
        
        treningsvarighetOutput.appendText("Antall treningsøkter i valgt tidsintervall: " + treningsvarigheter.size());
        treningsvarighetOutput.appendText("\n");
        
        int total = 0;
        for (int i = 0; i < treningsvarigheter.size(); i++) {
        		total += treningsvarigheter.get(i);
        }
        treningsvarighetOutput.appendText("Gjennomsnittlig varighet på treninger i denne perioden: " + (total/treningsvarigheter.size()) + " minutter");
	}
	
}
