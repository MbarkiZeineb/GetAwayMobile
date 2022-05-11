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
public class Category {

    private int idCateg;
    private String nomCateg;

    public Category(int idCateg, String nomCateg) {
        this.idCateg = idCateg;
        this.nomCateg = nomCateg;
    }

    public Category() {
    }

    public Category(String nomCateg) {
        this.nomCateg = nomCateg;
    }

    public int getIdCateg() {
        return idCateg;
    }

    public void setIdCateg(int idCateg) {
        this.idCateg = idCateg;
    }

    public String getNomCateg() {
        return nomCateg;
    }

    public void setNomCateg(String nomCateg) {
        this.nomCateg = nomCateg;
    }

    @Override
    public String toString() {
        return "Category{" + "idCateg=" + idCateg + ", nomCateg=" + nomCateg + '}';
    }

}
