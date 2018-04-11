/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.Srevices;

import com.forkids.IServices.ICommentaireService;
import com.forkids.entities.Commentaire;
import com.forkids.entities.Produit;
import com.forkids.entities.User;
import com.forkids.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.forkids.utils.Authentification;

/**
 *
 * @author amine
 */
public class CommentaireService implements ICommentaireService {

    private static Connection conn = DataSource.getInstance().getConnection();
    private static Statement st;
    private static ResultSet r;
    private static PreparedStatement pst;

    @Override
    public void AjouterCommenatire(Commentaire commentaire) {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        try {
            String insert = "INSERT INTO commentproduit (idproduit,idUser,contenu,date)"
                    + " VALUES (?,?,?,?);";
            PreparedStatement st1 = conn.prepareStatement(insert);
            st1.setInt(1, commentaire.getProduit().getIdProduit());
            st1.setInt(2, commentaire.getUser().getId());
            st1.setString(3, commentaire.getText());
            st1.setDate(4, date);
            st1.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Commentaire> afficherProduitCommentairelist(Produit p) {
        List<Commentaire> ls = new ArrayList<Commentaire>();
        ProduitService produitService = new ProduitService();
        try {
            System.out.println(" wdfddwdw" + p.getIdProduit());
            String select = "Select * from commentproduit where idproduit=" + p.getIdProduit() + "";
            pst = conn.prepareStatement(select);
            ResultSet r = pst.executeQuery();

            while (r.next()) {
                Commentaire c = new Commentaire();
                c.setText(r.getString("contenu"));
                System.out.println(" wdfddwdw" + GetUserById(r.getInt("iduser")));
                c.setUser(GetUserById(r.getInt("iduser")));
                c.setProduit(produitService.getProduitById(r.getInt("idproduit")));
                c.setDate(r.getDate("date"));
                ls.add(c);

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
        }

        return ls;
    }

    public User GetUserById(int id) throws SQLException {

        User user = new User();
        pst = conn.prepareStatement("Select * from user where id=" + id);

        ResultSet r = pst.executeQuery();
        while (r.next()) {
            user.setId(r.getInt("id"));
            user.setUsername(r.getString("username"));
            System.out.println("User: "+user);
            return user;
            
        }
        return user;

    }
}
