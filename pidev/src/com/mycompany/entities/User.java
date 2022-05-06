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
public class User {
    private String nom,prenom,email,role,password,numtel;
    private int id,etat;

    public User() {
    }
    
    

    public User(String nom, String prenom, String email, String role, String password, String numtel, int id, int etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
        this.password = password;
        this.numtel = numtel;
        this.id = id;
        this.etat = etat;
    }

    public User(String nom, String prenom, String email, String role, String password, String numtel, int etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
        this.password = password;
        this.numtel = numtel;
        this.etat = etat;
    }

    public User(String nom, String prenom, String email, String role, String password, String numtel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
        this.password = password;
        this.numtel = numtel;
    }

    public User(String nom, String prenom, String email, String password, String numtel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.numtel = numtel;
    }
    
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "User{" + "nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", role=" + role + ", password=" + password + ", numtel=" + numtel + ", id=" + id + ", etat=" + etat + '}';
    }
    
    
    
}
