/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author louay
 */
public class Hebergement {

    private int reference;
    private String paye;
    private String adress;
    private float prix;
    private String description;
    private String photo;
    private String dateStart;
    private String dateEnd;
    private int contact;
    private int nbrDetoile;
    private int nbrSuite;
    private int nbrParking;
    private String modelCaravane;
    private int idCateg;
    private int offreur;

    public Hebergement() {
    }

    public Hebergement(int reference, String paye, String adress, float prix, String description, String photo, String dateStart, String dateEnd, int contact, int nbrDetoile, int nbrSuite, int nbrParking, String modelCaravane, int idCateg, int offreur) {
        this.reference = reference;
        this.paye = paye;
        this.adress = adress;
        this.prix = prix;
        this.description = description;
        this.photo = photo;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.contact = contact;
        this.nbrDetoile = nbrDetoile;
        this.nbrSuite = nbrSuite;
        this.nbrParking = nbrParking;
        this.modelCaravane = modelCaravane;
        this.idCateg = idCateg;
        this.offreur = offreur;
    }

    public Hebergement(int reference, String paye, String adress, float prix, String description, String photo, String dateStart, String dateEnd, int contact, int nbrDetoile, int nbrSuite, int nbrParking, String modelCaravane) {
        this.reference = reference;
        this.paye = paye;
        this.adress = adress;
        this.prix = prix;
        this.description = description;
        this.photo = photo;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.contact = contact;
        this.nbrDetoile = nbrDetoile;
        this.nbrSuite = nbrSuite;
        this.nbrParking = nbrParking;
        this.modelCaravane = modelCaravane;
    }

    public Hebergement(String paye, String adress, float prix, String description, String photo, String dateStart, String dateEnd, int contact, int nbrDetoile, int nbrSuite, int nbrParking, String modelCaravane, int idCateg) {
        this.paye = paye;
        this.adress = adress;
        this.prix = prix;
        this.description = description;
        this.photo = photo;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.contact = contact;
        this.nbrDetoile = nbrDetoile;
        this.nbrSuite = nbrSuite;
        this.nbrParking = nbrParking;
        this.modelCaravane = modelCaravane;
        this.idCateg = idCateg;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getPaye() {
        return paye;
    }

    public void setPaye(String paye) {
        this.paye = paye;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public int getNbrDetoile() {
        return nbrDetoile;
    }

    public void setNbrDetoile(int nbrDetoile) {
        this.nbrDetoile = nbrDetoile;
    }

    public int getNbrSuite() {
        return nbrSuite;
    }

    public void setNbrSuite(int nbrSuite) {
        this.nbrSuite = nbrSuite;
    }

    public int getNbrParking() {
        return nbrParking;
    }

    public void setNbrParking(int nbrParking) {
        this.nbrParking = nbrParking;
    }

    public String getModelCaravane() {
        return modelCaravane;
    }

    public void setModelCaravane(String modelCaravane) {
        this.modelCaravane = modelCaravane;
    }

    public int getIdCateg() {
        return idCateg;
    }

    public void setIdCateg(int idCateg) {
        this.idCateg = idCateg;
    }

    public int getOffreur() {
        return offreur;
    }

    public void setOffreur(int offreur) {
        this.offreur = offreur;
    }

    @Override
    public String toString() {
        return "Hebergement{" + "reference=" + reference + ", paye=" + paye + ", adress=" + adress + ", prix=" + prix + ", description=" + description + ", photo=" + photo + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", contact=" + contact + ", nbrDetoile=" + nbrDetoile + ", nbrSuite=" + nbrSuite + ", nbrParking=" + nbrParking + ", modelCaravane=" + modelCaravane + ", idCateg=" + idCateg + ", offreur=" + offreur + '}';
    }

}
