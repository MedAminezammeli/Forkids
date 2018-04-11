/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.IServices;

import com.forkids.entities.Commentaire;
import com.forkids.entities.Produit;
import com.forkids.entities.User;
import java.util.List;

/**
 *
 * @author amine
 */
public interface ICommentaireService {
     public void AjouterCommenatire(Commentaire commentaire);
     public List<Commentaire> afficherProduitCommentairelist(Produit p);
    
}
