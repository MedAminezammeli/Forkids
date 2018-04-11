/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.forkids.IServices.IProduitService;
import com.forkids.Srevices.ProduitService;

import com.forkids.entities.Produit;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import static javafx.scene.Cursor.cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Anisb
 */
public class AjoutProduitController implements Initializable {
    @FXML
    private ChoiceBox<String> TypeProduit;
    @FXML
    private TextField NomProduit;
    @FXML
    private TextField PrixProduit;
    @FXML
    private TextField DescriptionProduit;
    @FXML
    private TextField IdPropriete;
    @FXML
    private TextField PrixPromo;
    @FXML
    private JFXButton btnAjout;
    private String path="";
    IProduitService prod = new ProduitService();
    @FXML
    private JFXButton loadPB;
   @FXML
    private ImageView imgviewadd ;
    @FXML
    private JFXHamburger Hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private ImageView BACK;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String path="file:C:/Users/amine/Documents/NetBeansProjects/Forkids/src/src/1425834768gris-dynamique.jpg";
       Image img = new Image(path, 388 , 309, true, true);
       BACK.setImage(img);
        try {
                VBox box = FXMLLoader.load(getClass().getResource("/com/forkids/gui/homepannel.fxml"));
                drawer.setSidePane(box);
                } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
                //Hamburger
                
                HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(Hamburger);
                burgerTask.setRate(-1);
                
                Hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e ->
                {
                    burgerTask.setRate(burgerTask.getRate() * -1);
                    burgerTask.play();
                    
                    if(drawer.isShown()) drawer.close();
                    else drawer.open();
                });
            ObservableList<String> cursor =FXCollections.observableArrayList("accessoires","vetements","jeux","nourriture");
            TypeProduit.setItems(cursor);

       
    }    

    @FXML
    private void AjouterProduit(ActionEvent event) throws IOException {
       Double prix=Double.parseDouble(PrixProduit.getText());
       Double prixPromo=Double.parseDouble(PrixPromo.getText());
       //int idProm=Integer.parseInt(PrixProduit.getText());
       Produit p= new Produit(NomProduit.getText(), TypeProduit.getValue(), prix, DescriptionProduit.getText(), prixPromo, 1,path);
       prod.AjouterProduit(p);
       {
           Parent homePage = FXMLLoader.load(getClass().getResource("/com/forkids/gui/MesProduits.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
        
       }
       
    }

    @FXML
    private void load(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(imgviewadd.getScene().getWindow());
        if (file != null) {
            Image img = new Image(file.toURI().toString(), 388 , 309, true, true);
            imgviewadd.imageProperty().unbind();
            imgviewadd.setImage(img);
            
        }
        path = file.toURI().toURL().toString();
    }

    @FXML
    private void load(MouseEvent event) {
    }

    private Object toURL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

 
    
}
