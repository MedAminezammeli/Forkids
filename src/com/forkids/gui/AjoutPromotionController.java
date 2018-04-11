/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.forkids.IServices.IProduitService;
import com.forkids.IServices.IPromotionService;
import com.forkids.Srevices.ProduitService;
import com.forkids.Srevices.PromotionService;

import com.forkids.entities.Produit;
import com.forkids.entities.Promotion;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author Anisb
 */
public class AjoutPromotionController implements Initializable {
    
    public static final String ACCOUNT_SID = "ACa81c765dfdd5592bc4e268d4b34b6aa4";
    public static final String AUTH_TOKEN = "22a705ed3adf57060ca4737dd7e7e5d5";
    private ObservableList<Produit> data =FXCollections.observableArrayList();
    private List<Produit> MyList;
    IProduitService prod = new ProduitService();
    IPromotionService prom= new PromotionService();
    private Produit SelectedItem;
    Stage mainStage;
    private String path="";
    @FXML
    private JFXListView<Label> ProduitListView;
    @FXML
    private JFXTextField IdProduitField;
    @FXML
    private JFXTextField DescriptionField;
    @FXML
    private DatePicker DateDebutField;
    @FXML
    private DatePicker DateFinField;
    @FXML
    private JFXTextField ReductionField;
    @FXML
    private ImageView PromotionImageView;
    @FXML
    private JFXButton AjouterPromotionPB;
    @FXML
    private JFXButton uploadImgPB;
    @FXML
    private JFXHamburger Hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private ImageView BACK;

    
    
        // Factory to create Cell of DatePicker
    private Callback<DatePicker, DateCell> getDayCellFactory() {
 
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
 
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
 
                        // Disable Monday, Tueday, Wednesday.
                        if (item.getDayOfYear()<LocalDate.now().getDayOfYear()) {
                            setDisable(true);
                            setStyle("-fx-background-color: #bfbfbf;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String path="file:/C:/Users/amine/Documents/NetBeansProjects/Forkids/src/src/1425834768gris-dynamique.jpg";
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
        
        Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
                DateDebutField.setDayCellFactory(dayCellFactory);
                DateFinField.setDayCellFactory(dayCellFactory);
        List<Produit> produits = new ArrayList<Produit>();
        produits=prod.AfficherProduit();
        data.clear();
        data.addAll(produits);
        MyList=prod.AfficherProduit();
        remplirListView();
    }    

    @FXML
    private void AjouterPromotion(ActionEvent event) throws IOException {
         if(DateDebutField.getValue()==null ||DateFinField.getValue()==null){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainStage);
            alert.setTitle("ERROR ");
            alert.setHeaderText("Date valide requise");
            alert.setContentText("Veuillez sélectionner une date valide");
            alert.showAndWait();   
        }
         int reduction=Integer.parseInt(ReductionField.getText());
         int id_produit=Integer.parseInt(IdProduitField.getText());
         Promotion p = new Promotion();
         p.setDescriptionPromotion(DescriptionField.getText());
         p.setDateDebutPromotion(DateDebutField.getValue());
         p.setDateFinPromotion(DateFinField.getValue());
         p.setReduction(reduction);
         p.setPhotoPromotion(path);
         p.setIdProduit(id_produit);
         prom.AjouterPromotion(p);
         

         //SMSSender.sendSMS("Votre promotion est bien ajouté", "+21623635830");
         {
           Parent homePage = FXMLLoader.load(getClass().getResource("/com/forkids/gui/MesPromotions.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
        
       }
    }

    private void remplirListView() {
        ProduitListView.getItems().clear();
        for(Produit p : MyList)
        {
            Label lbl = new Label(
                     p.getDescription() + "\n"
            );
            String img=p.getImage();
            
                Image image=new Image(img,150,100,true,true);
            
            lbl.setPrefSize(500, 100);
            
                
                ImageView imageView = new ImageView();
                imageView.imageProperty().unbind();
                imageView.setImage(image);
                imageView.setFitWidth(150);
                imageView.setFitHeight(100);
                ProduitListView.getItems().add(lbl);
                lbl.setGraphic(imageView);
        }
        
    }

    @FXML
    private void GetId(MouseEvent event) {
        int index = ProduitListView.getSelectionModel().getSelectedIndex();
        SelectedItem = MyList.get(index);
        System.out.println(SelectedItem.getIdProduit());
        IdProduitField.setText(""+SelectedItem.getIdProduit());
        
    }

    @FXML
    private void LoadImg(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(PromotionImageView.getScene().getWindow());
        if (file != null) {
            Image img = new Image(file.toURI().toString(), 388 , 309, true, true);
            PromotionImageView.imageProperty().unbind();
            PromotionImageView.setImage(img);
            
        }
        path = file.toURI().toURL().toString();
    }
    
}
