/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.entites;

/**
 *
 * @author FATNASSI
 */
public class Note {
    private int id_user;
    private float moyenne;

    public Note(int id_user, float moyenne) {
        this.id_user = id_user;
        this.moyenne = moyenne;
    }

    public Note() {
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }
    
    
    
}
