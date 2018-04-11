/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.entities;

import java.util.Date;

/**
 *
 * @author amine
 */
public class Commentaire {
    private int id;
    private String text;
    private Produit produit;
    private User user;
    private Date date;

    public Commentaire() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    
    public Commentaire(int id, String text, Produit produit, User user,Date date) {
        this.id = id;
        this.text = text;
        this.produit = produit;
        this.user = user;
        this.date = date;
    }
    
    
    
    
}
