/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.Srevices;

import com.forkids.IServices.IProduitService;
import com.forkids.entities.Produit;
import com.forkids.entities.User;
import com.forkids.utils.DataSource;
import java.sql.Connection;
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
public class ProduitService implements IProduitService {

    private static Connection conn = DataSource.getInstance().getConnection();
    private static Statement st;
    private static ResultSet r;
    private static PreparedStatement pst;

    @Override
    public void AjouterProduit(Produit p) {
        try {
            String requete = "insert into produit (idProduit,nomProduit, typeProduit , prixProduit, description,prixPromo,idPropriete,image,nbrlike,nbrdislike) values (?,?,?,?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(requete);

            pst.setInt(1, p.getIdProduit());
            pst.setString(2, p.getNomProduit());
            pst.setString(3, p.getTypeProduit());
            pst.setDouble(4, p.getPrixProduit());
            pst.setString(5, p.getDescription());
            pst.setDouble(6, p.getPrixPromo());
            pst.setInt(7, p.getIdPropriete());
            pst.setString(8, p.getImage());
            pst.setInt(9, p.getNbrlike());
            pst.setInt(10, p.getNbrdislike());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void ModifierProduit(Produit p, int id) {
        try {

            String req = "update produit set nomProduit='" + p.getNomProduit() + "', typeProduit='" + p.getTypeProduit() + "' , prixProduit=" + p.getPrixProduit()
                    + ", description='" + p.getDescription() + "' ,prixPromo=" + p.getPrixPromo() + ",idPropriete=" + p.getIdPropriete() + ", image='" + p.getImage() + "' ,nbrlike=" + p.getNbrlike() + ",nbrdislike=" + p.getNbrdislike() + "  where idProduit=" + id + "";

            st = conn.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Produit> AfficherProduit() {
        String req = "select * from produit";
        List<Produit> produits = new ArrayList<>();
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Produit p = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
                //System.out.println(rs.getInt(1));
                produits.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produits;
    }

    @Override
    public void SupprimerProduit(int id) {
        String req = "Delete from produit where idProduit=" + id + "";
        try {
            st = conn.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Produit> RechercherProduit(String parameter, List<String> values) {
        String req="";
       
            if (values.contains("Type") && values.contains("Nom")) {
                req = "select * from produit where nomProduit like '" + parameter + "%' or typeProduit like '" + parameter + "%'";
            } else if (values.contains("Type")) {
                req = "select * from produit where  typeProduit like '" + parameter + "%'";

            } else {
                req = "select * from produit where nomProduit like '" + parameter + "%'";

            }
        
        List<Produit> produits = new ArrayList<>();
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Produit p = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
                //System.out.println(rs.getInt(1));
                produits.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produits;
    }

    @Override
    public List<Produit> GetProduitPromotion() {
        String req = "select p.* from produit p join promotion pr where p.IdProduit=pr.IdProduit";
        List<Produit> produits = new ArrayList<>();
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Produit p = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
                //System.out.println(rs.getInt(1));
                produits.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produits;
    }

    @Override
    public ResultSet GetAllProductExcel() {
        ResultSet rs = null;
        String req = "select * from produit";

        try {
            pst = conn.prepareStatement(req);
            rs = pst.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Produit getProduitById(int id) {
        Produit p = new Produit();
        try {
            pst = conn.prepareStatement("Select * from produit where IdProduit=?");
            pst.setString(1, String.valueOf(id));
            r = pst.executeQuery();

            while (r.next()) {
                p.setIdProduit(r.getInt(0));
                p.setNomProduit(r.getString("NomProduit"));

                return p;
            }
        } catch (Exception e) {
            return null;
        }

        return p;

    }

    public float Pourcentage(String type) {
        float res=0;
        float total=0;
        try {
            PreparedStatement pst = conn.prepareStatement("Select * from produit where TypeProduit=?");
            pst.setString(1, type);
            ResultSet r = pst.executeQuery();

            while (r.next()) {
               res++;
             
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
                try {
            PreparedStatement pst2 = conn.prepareStatement("Select * from produit");
            ResultSet r2 = pst2.executeQuery();

            while (r2.next()) {
               total++;
             
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
                return res/total;
    }

}
