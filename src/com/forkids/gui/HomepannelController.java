/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.gui;

import com.jfoenix.controls.JFXButton;
import com.forkids.utils.Authentification;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class HomepannelController implements Initializable {

    @FXML
    private Text logged_as;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton logoutPB;
    @FXML
    private Label nomuserconnect;
    //private String username=Authentification.getSession().getUser().getUsername();
    @FXML
    private JFXButton AjouterProduitPB;
    @FXML
    private JFXButton MesProduitsPB;
    @FXML
    private JFXButton AjouterPromotionPB;
    @FXML
    private JFXButton AfficherPromotionsPB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //nomuserconnect.setText(username);

    }    


    @FXML
    private void HomeClick(ActionEvent event) throws IOException {
        Parent homePage = FXMLLoader.load(getClass().getResource("/com/forkids/gui/Home.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }

    @FXML
    private void Logout(ActionEvent event) {
        exit(0);
    }




    @FXML
    private void AddProduct(ActionEvent event) throws IOException {
        Parent homePage = FXMLLoader.load(getClass().getResource("/com/forkids/gui/AjoutProduit.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }

    @FXML
    private void MesProduits(ActionEvent event) throws IOException {
        Parent homePage = FXMLLoader.load(getClass().getResource("/com/forkids/gui/MesProduits.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }

    @FXML
    private void AddPromotion(ActionEvent event) throws IOException {
        Parent homePage = FXMLLoader.load(getClass().getResource("/com/forkids/gui/AjoutPromotion.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }

    @FXML
    private void AfficherPromotions(ActionEvent event) throws IOException {
        Parent homePage = FXMLLoader.load(getClass().getResource("/com/forkids/gui/MesPromotions.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }
    
}
