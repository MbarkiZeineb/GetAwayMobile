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
public class Avis {
    private int RefAvis;
    private String Message;
    private String Date;
    private int Id;
    private int RefActivite;
    private String NomClient;
    private String NomAct;
    private float Rating;

    public Avis(int RefAvis, String Message, String Date, int Id, int RefActivite, String NomClient, String NomAct, float Rating) {
        this.RefAvis = RefAvis;
        this.Message = Message;
        this.Date = Date;
        this.Id = Id;
        this.RefActivite = RefActivite;
        this.NomClient = NomClient;
        this.NomAct = NomAct;
        this.Rating = Rating;
    }

    public Avis(String Message, String Date, int Id, int RefActivite, String NomClient, String NomAct, float Rating) {
        this.Message = Message;
        this.Date = Date;
        this.Id = Id;
        this.RefActivite = RefActivite;
        this.NomClient = NomClient;
        this.NomAct = NomAct;
        this.Rating = Rating;
    }

    public Avis() {
    }

    public int getRefAvis() {
        return RefAvis;
    }

    public void setRefAvis(int RefAvis) {
        this.RefAvis = RefAvis;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getRefActivite() {
        return RefActivite;
    }

    public void setRefActivite(int RefActivite) {
        this.RefActivite = RefActivite;
    }

    public String getNomClient() {
        return NomClient;
    }

    public void setNomClient(String NomClient) {
        this.NomClient = NomClient;
    }

    public String getNomAct() {
        return NomAct;
    }

    public void setNomAct(String NomAct) {
        this.NomAct = NomAct;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float Rating) {
        this.Rating = Rating;
    }

    @Override
    public String toString() {
        return "Avis{" + "RefAvis=" + RefAvis + ", Message=" + Message + ", Date=" + Date + ", Id=" + Id + ", RefActivite=" + RefActivite + ", NomClient=" + NomClient + ", NomAct=" + NomAct + ", Rating=" + Rating + '}';
    }
    
   
    

   
    
    
}