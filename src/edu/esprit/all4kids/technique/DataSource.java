/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.all4kids.technique;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Ourabi
 */
public class DataSource {
    private String url = "jdbc:mysql://localhost:3306/allforkids2";
    private String login = "root";
    private String password = "";
    private Connection connection;
    private static DataSource dataSource;

    public DataSource() {
        try {
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("connection établie");
        } catch (Exception e) {
            System.out.println("connection non établie");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataSource getInstance() {
        if (dataSource == null) {
            dataSource = new DataSource();
        }
        return dataSource;
    }
    
}
