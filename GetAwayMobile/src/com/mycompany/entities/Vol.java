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
public class Vol {
    
    int num_vol,nbr_placedispo;
    String ville_depart,ville_arrivee;
    String date_depart,date_arrivee;
    float prix;

    public Vol() {
    }

    public Vol(int num_vol, int nbr_placedispo, String ville_depart, String ville_arrivee, String date_depart, String date_arrivee, float prix) {
        this.num_vol = num_vol;
        this.nbr_placedispo = nbr_placedispo;
        this.ville_depart = ville_depart;
        this.ville_arrivee = ville_arrivee;
        this.date_depart = date_depart;
        this.date_arrivee = date_arrivee;
        this.prix = prix;
    }

    public int getNum_vol() {
        return num_vol;
    }

    public int getNbr_placedispo() {
        return nbr_placedispo;
    }

    public String getVille_depart() {
        return ville_depart;
    }

    public String getVille_arrivee() {
        return ville_arrivee;
    }

    public String getDate_depart() {
        return date_depart;
    }

    public String getDate_arrivee() {
        return date_arrivee;
    }

    public float getPrix() {
        return prix;
    }

    public void setNum_vol(int num_vol) {
        this.num_vol = num_vol;
    }

    public void setNbr_placedispo(int nbr_placedispo) {
        this.nbr_placedispo = nbr_placedispo;
    }

    public void setVille_depart(String ville_depart) {
        this.ville_depart = ville_depart;
    }

    public void setVille_arrivee(String ville_arrivee) {
        this.ville_arrivee = ville_arrivee;
    }

    public void setDate_depart(String date_depart) {
        this.date_depart = date_depart;
    }

    public void setDate_arrivee(String date_arrivee) {
        this.date_arrivee = date_arrivee;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Vol{" + "num_vol=" + num_vol + ", nbr_placedispo=" + nbr_placedispo + ", ville_depart=" + ville_depart + ", ville_arrivee=" + ville_arrivee + ", date_depart=" + date_depart + ", date_arrivee=" + date_arrivee + ", prix=" + prix + '}';
    }

    
    
}
