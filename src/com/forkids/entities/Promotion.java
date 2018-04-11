/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.entities;

import java.time.LocalDate;

/**
 *
 * @author amine
 */
public class Promotion {
     private int IdPromotion;
    private String DescriptionPromotion;
    private LocalDate DateDebutPromotion;
    private LocalDate DateFinPromotion;
    private int Reduction;
    private String PhotoPromotion;
    private int IdProduit;

    public Promotion() {
    }

    public Promotion(int IdPromotion, String DescriptionPromotion, LocalDate DateDebutPromotion, LocalDate DateFinPromotion, int Reduction, String PhotoPromotion, int IdProduit) {
        this.IdPromotion = IdPromotion;
        this.DescriptionPromotion = DescriptionPromotion;
        this.DateDebutPromotion = DateDebutPromotion;
        this.DateFinPromotion = DateFinPromotion;
        this.Reduction = Reduction;
        this.PhotoPromotion = PhotoPromotion;
        this.IdProduit = IdProduit;
    }

    public int getIdPromotion() {
        return IdPromotion;
    }

    public void setIdPromotion(int IdPromotion) {
        this.IdPromotion = IdPromotion;
    }

    public String getDescriptionPromotion() {
        return DescriptionPromotion;
    }

    public void setDescriptionPromotion(String DescriptionPromotion) {
        this.DescriptionPromotion = DescriptionPromotion;
    }

    public LocalDate getDateDebutPromotion() {
        return DateDebutPromotion;
    }

    public void setDateDebutPromotion(LocalDate DateDebutPromotion) {
        this.DateDebutPromotion = DateDebutPromotion;
    }

    public LocalDate getDateFinPromotion() {
        return DateFinPromotion;
    }

    public void setDateFinPromotion(LocalDate DateFinPromotion) {
        this.DateFinPromotion = DateFinPromotion;
    }

    public int getReduction() {
        return Reduction;
    }

    public void setReduction(int Reduction) {
        this.Reduction = Reduction;
    }

    public String getPhotoPromotion() {
        return PhotoPromotion;
    }

    public void setPhotoPromotion(String PhotoPromotion) {
        this.PhotoPromotion = PhotoPromotion;
    }

    public int getIdProduit() {
        return IdProduit;
    }

    public void setIdProduit(int IdProduit) {
        this.IdProduit = IdProduit;
    }

}
