/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author asus
 */
public class Reclamation {
  private int idR;
    private int idC;
    private String objet;
    private String description;
    private int etat;

    public Reclamation() {
    }

    public Reclamation(int idR, int idC, String objet, String description) {
        this.idR = idR;
        this.idC = idC;
        this.objet = objet;
        this.description = description;
    }

    public Reclamation(int idR, int idC, String objet, String description, int etat) {
        this.idR = idR;
        this.idC = idC;
        this.objet = objet;
        this.description = description;
        this.etat = etat;
    }

    public Reclamation( String objet, String description, int etat,int idC) {
        this.idC = idC;
        this.objet = objet;
        this.description = description;
        this.etat = etat;
    }
   public Reclamation( String objet, String description,int idC) {
        this.idC = idC;
        this.objet = objet;
        this.description = description;
     
    }
    

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idR=" + idR + ", idC=" + idC + ", objet=" + objet + ", description=" + description + ", etat=" + etat + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
} 
