/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author TheBoss'07
 */
public class Activite {

       
   public int RefAct;
   private String Nom;
   private String Descrip;
   private String Duree;
   private int NbrPlace;
   private String Date;
   private String Type;
   private String Location;
   private float Prix;
   private String image;

    public Activite(int RefAct, String Nom, String Descrip, String Duree, int NbrPlace, String Date, String Type, String Location, float Prix, String image) {
        this.RefAct = RefAct;
        this.Nom = Nom;
        this.Descrip = Descrip;
        this.Duree = Duree;
        this.NbrPlace = NbrPlace;
        this.Date = Date;
        this.Type = Type;
        this.Location = Location;
        this.Prix = Prix;
        this.image = image;
    }

    public Activite(int RefAct) {
        this.RefAct = RefAct;
    }

    public Activite() {
    }

    public Activite(String Nom, String Descrip, String Duree, int NbrPlace, String Date, String Type, String Location, float Prix, String image) {
        this.Nom = Nom;
        this.Descrip = Descrip;
        this.Duree = Duree;
        this.NbrPlace = NbrPlace;
        this.Date = Date;
        this.Type = Type;
        this.Location = Location;
        this.Prix = Prix;
        this.image = image;
    }

    public int getRefAct() {
        return RefAct;
    }

    public void setRefAct(int RefAct) {
        this.RefAct = RefAct;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getDescrip() {
        return Descrip;
    }

    public void setDescrip(String Descrip) {
        this.Descrip = Descrip;
    }

    public String getDuree() {
        return Duree;
    }

    public void setDuree(String Duree) {
        this.Duree = Duree;
    }

    public int getNbrPlace() {
        return NbrPlace;
    }

    public void setNbrPlace(int NbrPlace) {
        this.NbrPlace = NbrPlace;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Activite{" + "Nom=" + Nom + ", Descrip=" + Descrip + ", Duree=" + Duree + ", NbrPlace=" + NbrPlace + ", Date=" + Date + ", Type=" + Type + ", Location=" + Location + ", Prix=" + Prix + ", image=" + image + '}';
    }

   
   


   

    
   
   
}

