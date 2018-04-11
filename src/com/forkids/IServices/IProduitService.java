/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.IServices;

import com.forkids.entities.Produit;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Anisb
 */
public interface IProduitService {
    public void AjouterProduit(Produit p);
    public void ModifierProduit(Produit p,int id);
    public Produit getProduitById(int id);
    public List<Produit> AfficherProduit();
    public void SupprimerProduit(int id);
    public List<Produit> RechercherProduit(String parameter,List<String> values);
    public List<Produit> GetProduitPromotion();
    public ResultSet GetAllProductExcel();
}
