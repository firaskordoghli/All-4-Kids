/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Rejoindre;
import allforkids.util.Config;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author FATNASSI
 */
public class ServiceRejoindre {

    static Config ds = Config.getInstance();

    public void insrerRejoindre(int a, int b) {
        try {

            String req = "INSERT INTO `rejoindre` (`id_etablissement`, `id_user`, `verification` ) VALUES (?,?,'Non valide');";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, a);
            ste.setInt(2, b);

            ste.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public Rejoindre SelectIfDejaExiste(int a,int b) {
        
        try {
            String req = "SELECT * FROM rejoindre where id_etablissement=? AND id_user=? ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setInt(1, a);
            ste.setInt(2, b);

            ResultSet result = ste.executeQuery();
            while (result.next()) {

                Rejoindre r = new Rejoindre(
                        result.getInt("id_etablissement"),
                        result.getInt("id_user"),
                        result.getString("verification")
                );return r ;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;

    }
    public Rejoindre GetIdUserById(int id) {
        try {
            String req = "SELECT * FROM rejoindre where id_etablissement=? ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setInt(1, id);

            ResultSet result = ste.executeQuery();
            while (result.next()) {

                Rejoindre r = new Rejoindre(
                        result.getInt("id_etablissement"),
                        result.getInt("id_user"),
                        result.getString("verification")
                );
                return r;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;

    }
}
