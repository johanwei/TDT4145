package main;

import java.sql.*;

public abstract class Connector {
    protected static Connection conn;
    
    public static Connection connect(){
        try {
            String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/johanwei_prosjektdb?autoReconnect=true&useSSL=false";
            conn = DriverManager.getConnection(url, "johanwei_db", "rullekake");

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return conn;
    }

}