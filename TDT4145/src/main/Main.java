package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../fxml/Trening.fxml"));
		primaryStage.setTitle("Trening");
		Scene scene = new Scene(root, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	/*
	public void test() throws SQLException {
		 Statement stmt = conn.createStatement();
		 ResultSet res = stmt.executeQuery("SELECT * FROM Ovelse");
		 if (res.next()) {
			 System.out.println(res.getString("OvelseNavn"));
		 }
	}*/
	
}
