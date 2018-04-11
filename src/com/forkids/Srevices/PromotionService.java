/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.Srevices;

import com.forkids.IServices.IPromotionService;
import com.forkids.entities.Produit;
import com.forkids.entities.Promotion;
import com.forkids.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anisb
 */
public class PromotionService implements IPromotionService{
     private static Connection conn=DataSource.getInstance().getConnection();
    private static Statement st;
    private static ResultSet r;
    private static PreparedStatement pst;

    @Override
    public void AjouterPromotion(Promotion p) {
    
        try {
            String requete="insert into promotion (DescriptionPromotion,DateDebutPromotion , DateFinPromotion, Reduction,PhotoPromotion,IdProduit)"
                    + " values (?,?,?,?,?,?)";
           
            pst=conn.prepareStatement(requete);
           
            
            pst.setString(1,p.getDescriptionPromotion());
            pst.setDate(2,Date.valueOf(p.getDateDebutPromotion()));
          
            pst.setDate(3,Date.valueOf(p.getDateFinPromotion()));
            pst.setInt(4,p.getReduction());
            pst.setString(5,p.getPhotoPromotion());
            pst.setInt(6,p.getIdProduit());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PromotionService.class.getName()).log(Level.SEVERE, null, ex);
        
    }
    }

    @Override
    public List<Promotion> AfficherPromotion() {
        String req = "select * from promotion";
        List<Promotion> promotions = new ArrayList<>();
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               
                Promotion p= new Promotion (rs.getInt(1),rs.getString(2), rs.getDate(3).toLocalDate(),rs.getDate(4).toLocalDate(), rs.getInt(5),rs.getString(6),rs.getInt(7));
                promotions.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return promotions;
    }

    @Override
    public void SupprimerPromotion(int id) {
        String req="Delete from promotion where IdPrommotion="+id+"";
        try {
            st=conn.createStatement();
            st.executeUpdate(req);
        }  catch (SQLException ex) {
            Logger.getLogger(PromotionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ModifierPromotion(Promotion p, int id) {
        try {
    
          String req="update promotion set DescriptionPromotion='"+p.getDescriptionPromotion()+"', DateDebutPromotion='"+Date.valueOf(p.getDateDebutPromotion())+"' , DateFinPromotion='"+Date.valueOf(p.getDateFinPromotion())+ 
                  "', Reduction="+p.getReduction()+" ,PhotoPromotion='"+p.getPhotoPromotion()+"',IdProduit="+p.getIdProduit()+" where IdPrommotion="+id+"";
        
            st=conn.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PromotionService.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
}
