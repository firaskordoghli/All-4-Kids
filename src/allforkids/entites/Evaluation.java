/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.entites;

/**
 *
 * @author slim
 */
public class Evaluation {
    private int id_eleve ;
    private int id_etablissment ;
    private String absence ;
    private String matiere;
    private float  note ;

    public Evaluation(int id_eleve, int id_etablissment, String absence, String matiere, float note) {
        this.id_eleve = id_eleve;
        this.id_etablissment = id_etablissment;
        this.absence = absence;
        this.matiere = matiere;
        this.note = note;
    }

    public Evaluation() {
    }

    public int getId_eleve() {
        return id_eleve;
    }

    public void setId_eleve(int id_eleve) {
        this.id_eleve = id_eleve;
    }

    public int getId_etablissment() {
        return id_etablissment;
    }

    public void setId_etablissment(int id_etablissment) {
        this.id_etablissment = id_etablissment;
    }

    public String getAbsence() {
        return absence;
    }

    public void setAbsence(String absence) {
        this.absence = absence;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.id_eleve;
        hash = 73 * hash + this.id_etablissment;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evaluation other = (Evaluation) obj;
        if (this.id_eleve != other.id_eleve) {
            return false;
        }
        if (this.id_etablissment != other.id_etablissment) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evaluation{" + "absence=" + absence + ", matiere=" + matiere + ", note=" + note + '}';
    }
    
    
}
