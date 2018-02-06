/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Tansportpublic;
import allforkids.util.Config;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author slim
 */
public class ServiceTansportpublic {

    static Config ds = Config.getInstance();

    public void insrerTansportpublic(Tansportpublic t) {
        try {
            String req = "INSERT INTO tansportpublic VALUES(?,?,?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, t.getNom_transp());
            ste.setString(2, t.getTime());
            ste.setString(3, t.getSatation());
            ste.setString(4, t.getTrajet());
            ste.setString(5, t.getType());
            ste.setFloat(6, t.getFraix());

            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTansportpublic(Tansportpublic t, String id) {
        try {
            String req = "UPDATE tansportpublic SET  time= ? ,satation=?,trajet = ? ,type=? , fraix=? WHERE nom_transp = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setString(1, t.getTime());
            ste.setString(2, t.getSatation());
            ste.setString(3, t.getTrajet());
            ste.setString(4, t.getType());
            ste.setFloat(5, t.getFraix());
            ste.setString(6, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteTansportpublic(String id) {
        try {
            String req = "DELETE FROM tansportpublic WHERE nom_transp = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Tansportpublic> selectTansportpublic() {
        List<Tansportpublic> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM tansportpublic ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Tansportpublic(
                                result.getString("nom_transp"),
                                result.getString("time"),
                                result.getString("satation"),
                                result.getString("trajet"),
                                result.getString("type"),
                                result.getFloat("fraix")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
