package com.example.Record_Shop.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    static String url;
    static String username;
    static String password;

    public static void getProperties() throws IOException {
        DBConfig dbConfig = new DBConfig();
        url = dbConfig.getUrl();
        username = dbConfig.getUsername();
        password = dbConfig.getPassword();
    }

    public static Connection connect() throws SQLException, IOException {
        getProperties();
        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        try (var connection = DB.connect()){
            if(connection != null)
            {
                System.out.println("Connection succeeded!");
            }
            else
            {
                // this should never really occur as any problem connecting should trigger a SQLException
                System.out.println("Connection failed!");
            }
        } catch (SQLException | IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
