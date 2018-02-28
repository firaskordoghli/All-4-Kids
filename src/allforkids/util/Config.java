/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author slim
 */
public class Config {

    /**
     *
     * @author slim
     */
    static Config instance;

    public static Config getInstance() {
        if (instance == null) {
            return instance = new Config();
        }
        return instance;
    }
    private String url = "jdbc:mysql://localhost:3306/allforkids2";
    private String user = "root";
    private String password = "";
    private Connection connection;

    public Config() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
