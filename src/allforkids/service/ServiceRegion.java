/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Region;
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
public class ServiceRegion {

    static Config ds = Config.getInstance();

    public Region SelectRegion() {
        try {
            String req = "select * from region";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {

                Region r = new Region(
                        result.getInt("id_region"),
                        result.getString("nom_region")
                );
                return r;
            }

        } catch (Exception ex) {
            System.out.println(ex);

        }
        return null;
    }

    public ObservableList<Region> select2() throws SQLException {
        ObservableList<Region> list = FXCollections.observableArrayList();
        try {
            String req = "select * from region";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Region(
                                result.getInt("id_region"),
                                result.getString("nom_region")
                        )
                );
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public Region GetIdByNom(String nom) {
        try {
            String req = "SELECT * FROM region where nom_region=? ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setString(1, nom);

            ResultSet result = ste.executeQuery();
            while (result.next()) {

                Region u = new Region(
                        result.getInt("id_region"),
                        result.getString("nom_region")
                );
                return u;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;

    }

}
