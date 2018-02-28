/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Ville;
import allforkids.util.Config;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author FATNASSI
 */
public class ServiceVille {
    static Config ds = Config.getInstance();
    
    public ObservableList<Ville> selectById(int id) throws SQLException {
        ObservableList<Ville> list = FXCollections.observableArrayList();
        try {
            String req = "select * from ville WHERE id_region=? ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            
            ste.setInt(1, id);
            
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Ville(
                        result.getInt("id_ville"),        
                        result.getInt("id_region"),
                        result.getString("nom_ville")
                        )
                );
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }
}
