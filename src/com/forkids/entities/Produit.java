/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.entities;

/**
 *
 * @author amine
 */
public class Produit {
    private int IdProduit ;
    private String NomProduit ;
    private String TypeProduit;
    private Double PrixProduit;
    private String Description;
    private Double PrixPromo;
    private int IdPropriete;
    private String image;
    private int nbrlike;
    private int nbrdislike;

    public Produit() {
    }

    public Produit(int IdProduit, String NomProduit, String TypeProduit, Double PrixProduit, String Description, Double PrixPromo, int IdPropriete, String image, int nbrlike, int nbrdislike) {
        this.IdProduit = IdProduit;
        this.NomProduit = NomProduit;
        this.TypeProduit = TypeProduit;
        this.PrixProduit = PrixProduit;
        this.Description = Description;
        this.PrixPromo = PrixPromo;
        this.IdPropriete = IdPropriete;
        this.image = image;
        this.nbrlike = nbrlike;
        this.nbrdislike = nbrdislike;
    }

    public Produit(String NomProduit, String TypeProduit, Double PrixProduit, String Description, Double PrixPromo, int IdPropriete, String image) {
        this.NomProduit = NomProduit;
        this.TypeProduit = TypeProduit;
        this.PrixProduit = PrixProduit;
        this.Description = Description;
        this.PrixPromo = PrixPromo;
        this.IdPropriete = IdPropriete;
        this.image = image;
    }

    public Produit(int idProduit, String text, String typeProduit, Double prix, String text0, Double prixPromo, int idPropriete, String path) {
      

    }
    
    

    public int getIdProduit() {
        return IdProduit;
    }

    public void setIdProduit(int IdProduit) {
        this.IdProduit = IdProduit;
    }

    public String getNomProduit() {
        return NomProduit;
    }

    public void setNomProduit(String NomProduit) {
        this.NomProduit = NomProduit;
    }

    public String getTypeProduit() {
        return TypeProduit;
    }

    public void setTypeProduit(String TypeProduit) {
        this.TypeProduit = TypeProduit;
    }

    public Double getPrixProduit() {
        return PrixProduit;
    }

    public void setPrixProduit(Double PrixProduit) {
        this.PrixProduit = PrixProduit;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Double getPrixPromo() {
        return PrixPromo;
    }

    public void setPrixPromo(Double PrixPromo) {
        this.PrixPromo = PrixPromo;
    }

    public int getIdPropriete() {
        return IdPropriete;
    }

    public void setIdPropriete(int IdPropriete) {
        this.IdPropriete = IdPropriete;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbrlike() {
        return nbrlike;
    }

    public void setNbrlike(int nbrlike) {
        this.nbrlike = nbrlike;
    }

    public int getNbrdislike() {
        return nbrdislike;
    }

    public void setNbrdislike(int nbrdislike) {
        this.nbrdislike = nbrdislike;
    }

    @Override
    public String toString() {
        return "Produit{" + "IdProduit=" + IdProduit + ", NomProduit=" + NomProduit + ", TypeProduit=" + TypeProduit + ", PrixProduit=" + PrixProduit + ", Description=" + Description + ", PrixPromo=" + PrixPromo + ", IdPropriete=" + IdPropriete + ", image=" + image + ", nbrlike=" + nbrlike + ", nbrdislike=" + nbrdislike + '}';
    }
    
    
}
