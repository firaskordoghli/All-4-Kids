/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Listeattente;
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
public class ServiceListeattente {
       static Config ds = Config.getInstance();

    public void insrerListeattente(Listeattente l) {
        try {
            String req = "INSERT INTO liste_attente VALUES(?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, l.getNum());
            ste.setInt(2, l.getId_user());
            ste.setInt(3, l.getId_evenmment());
     
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateListeattente(Listeattente l, int id, int id2) {
        try {
            String req = "UPDATE liste_attente SET  num= ?  WHERE  id_evenmment= ? and id_user = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
             
            ste.setInt(1, l.getNum());
            ste.setInt(2, id );
            ste.setInt(3, id2);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteListeattente(int id, int id2) {
        try {
            String req = "DELETE FROM liste_attente WHERE id_user = ? and id_evenmment=?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
             ste.setInt(2, id2);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Listeattente> selectListeattente() {
        List<Listeattente> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM liste_attente ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Listeattente(
                                result.getInt("num"),
                                result.getInt("id_user"),
                                result.getInt("id_evenmment")
                              
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
