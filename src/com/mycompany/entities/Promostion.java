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
public class Promostion {

    private int idRef;
    private int pourcentage;
    private String dateStart;
    private String dateEnd;
    private int refHebergement;

    public Promostion(int idRef, int pourcentage, String dateStart, String dateEnd, int refHebergement) {
        this.idRef = idRef;
        this.pourcentage = pourcentage;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.refHebergement = refHebergement;
    }

    public Promostion(int pourcentage, String dateStart, String dateEnd, int refHebergement) {
        this.pourcentage = pourcentage;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.refHebergement = refHebergement;
    }

    public Promostion() {
    }

    public Promostion(int pourcentage, String dateStart, String dateEnd) {
        this.pourcentage = pourcentage;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getIdRef() {
        return idRef;
    }

    public void setIdRef(int idRef) {
        this.idRef = idRef;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
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

    public int getRefHebergement() {
        return refHebergement;
    }

    public void setRefHebergement(int refHebergement) {
        this.refHebergement = refHebergement;
    }

    @Override
    public String toString() {
        return "Promostion{" + "idRef=" + idRef + ", pourcentage=" + pourcentage + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", refHebergement=" + refHebergement + '}';
    }

}
