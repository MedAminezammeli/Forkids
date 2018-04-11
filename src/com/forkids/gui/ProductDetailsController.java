/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.gui;

import com.forkids.Srevices.CommentaireService;
import com.forkids.entities.Commentaire;
import com.forkids.entities.Produit;
import com.forkids.utils.Authentification;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class ProductDetailsController implements Initializable {

    @FXML
    private ListView<Label> listComments;
    @FXML
    private JFXTextField textComment;
    @FXML
    private JFXButton btnComment;
    @FXML
    private Label productName;
    @FXML
    private Label ProductDetail;

    private List<Commentaire> MyList;
    CommentaireService commentaireService = new CommentaireService();

    Produit p;
    @FXML
    private ImageView productImage;
    @FXML
    private ImageView retourboutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 

    }

    public void InitializeDetails(Produit p) {
        this.p = p;
        productName.setText(p.getNomProduit());
        ProductDetail.setText(p.getDescription());
        String path = p.getImage();
        Image img = new Image(path, 388, 309, true, true);
        productImage.setImage(img);
        MyList = commentaireService.afficherProduitCommentairelist(p);
        remplirListView();
        textComment.clear();
        
        
    }

    
       
          
    private void remplirListView() {
        listComments.getItems().clear();
        for (Commentaire c : MyList) {
            Label lbl = new Label(c.getText() + "  ,By User :" + c.getUser().getUsername() + "  ,Date :" + c.getDate() + "\n");
            lbl.setPrefSize(500, 100);
            listComments.getItems().add(lbl);
        }
       // listComments.refresh();
    }

    @FXML
    private void AddComment(ActionEvent event) {

        String commentaire = textComment.getText();
        Commentaire c = new Commentaire();
        c.setUser(Authentification.getSession().getUser());
        c.setProduit(p);
        c.setText(commentaire);
        commentaireService.AjouterCommenatire(c);
        MyList = commentaireService.afficherProduitCommentairelist(p);
        remplirListView();
        textComment.clear();

    }

    public Produit getP() {
        return p;
    }

    public void setP(Produit p) {
        this.p = p;
    }

    @FXML
    private void retourproduit(MouseEvent event) throws IOException {
        try{
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/forkids/gui/UserInterface.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Forkids - Home");
                        stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(com.forkids.gui.HomeController.class.getName()).log(Level.SEVERE, null, ex);
                             }
    
}
}
