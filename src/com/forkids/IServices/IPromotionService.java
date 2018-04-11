/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.IServices;

import com.forkids.entities.Promotion;
import java.util.List;

/**
 *
 * @author Anisb
 */
public interface IPromotionService {
    public void AjouterPromotion(Promotion p);
    public void ModifierPromotion(Promotion p,int id);
    public List<Promotion> AfficherPromotion();
    public void SupprimerPromotion(int id);
}
