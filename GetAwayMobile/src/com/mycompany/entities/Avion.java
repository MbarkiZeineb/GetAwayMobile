/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Malek
 */
public class Avion {
    private int id_avion,nbr_place,id_agence ;
    private String nom_avion,type;

    public Avion() {
    }

    public Avion(int nbr_place, String nom_avion) {
        this.nbr_place = nbr_place;
        this.nom_avion = nom_avion;
    }

    public Avion(int nbr_place, int id_agence, String nom_avion) {
        this.nbr_place = nbr_place;
        this.id_agence = id_agence;
        this.nom_avion = nom_avion;
    }
    
    

    public Avion(int id_avion, int nbr_place, int id_agence, String nom_avion, String type) {
        this.id_avion = id_avion;
        this.nbr_place = nbr_place;
        this.id_agence = id_agence;
        this.nom_avion = nom_avion;
        this.type = type;
    }

    public Avion(int nbr_place, String nom_avion, String type) {
        this.nbr_place = nbr_place;
        this.nom_avion = nom_avion;
        this.type = type;
    }

   

    public int getId_avion() {
        return id_avion;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public int getId_agence() {
        return id_agence;
    }

    public String getNom_avion() {
        return nom_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public void setId_agence(int id_agence) {
        this.id_agence = id_agence;
    }

    public void setNom_avion(String nom_avion) {
        this.nom_avion = nom_avion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Avion{" + "id_avion=" + id_avion + ", nbr_place=" + nbr_place + ", id_agence=" + id_agence + ", nom_avion=" + nom_avion + ", type=" + type + '}';
    }

  
    
    
    
    
}
