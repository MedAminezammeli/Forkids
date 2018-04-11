/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.gui;

import com.jfoenix.controls.JFXButton;
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
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.ButtonType;
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
public class MesPromotionsController implements Initializable {
    
    private ObservableList<Promotion> data =FXCollections.observableArrayList();
    private List<Promotion> MyList;
    IPromotionService prom= new PromotionService();
    private Promotion SelectedItem;
    Stage mainStage;
    private String path="";
    
    @FXML
    private JFXListView<Label> PromotionListView;
    @FXML
    private JFXTextField IdProduitField;
    @FXML
    private JFXTextField DescriptionField;
    @FXML
    private JFXTextField ReductionField;
    @FXML
    private ImageView PromotionImageView;
    @FXML
    private DatePicker DateFinField;
    @FXML
    private DatePicker DateDebutField;
    @FXML
    private JFXButton ModifierPB;
    @FXML
    private JFXButton SupprimerPB;
    @FXML
    private JFXButton UploadImg;
    @FXML
    private JFXHamburger Hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label descLabel;
    @FXML
    private Label dateDebLabel;
    @FXML
    private Label dateFinLabel;
    @FXML
    private Label RedLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label ImgLabel;
    @FXML
    private ImageView BACK;

    
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
        String path="file:C:/Users/amine/Documents/NetBeansProjects/Forkids/src/src/desconto-web-500x330.jpg";
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
        
        ModifierPB.setVisible(false);
        UploadImg.setVisible(false);
        SupprimerPB.setVisible(false);
        ImgLabel.setVisible(false);
        RedLabel.setVisible(false);
        dateDebLabel.setVisible(false);
        dateFinLabel.setVisible(false);
        descLabel.setVisible(false);
        idLabel.setVisible(false);
        DateDebutField.setVisible(false);
        DateFinField.setVisible(false);
        DescriptionField.setVisible(false);
        IdProduitField.setVisible(false);
        ReductionField.setVisible(false);
        
                
                
        Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
                DateDebutField.setDayCellFactory(dayCellFactory);
                DateFinField.setDayCellFactory(dayCellFactory);
        List<Promotion> promotions = new ArrayList<Promotion>();
        promotions=prom.AfficherPromotion();
        data.clear();
        data.addAll(promotions);
        MyList=prom.AfficherPromotion();
        remplirListView();
    }    

    @FXML
    private void RemplirInputs(MouseEvent event) {
        ModifierPB.setVisible(true);
        UploadImg.setVisible(true);
        SupprimerPB.setVisible(true);
        ImgLabel.setVisible(true);
        RedLabel.setVisible(true);
        dateDebLabel.setVisible(true);
        dateFinLabel.setVisible(true);
        descLabel.setVisible(true);
        idLabel.setVisible(true);
        DateDebutField.setVisible(true);
        DateFinField.setVisible(true);
        DescriptionField.setVisible(true);
        IdProduitField.setVisible(true);
        ReductionField.setVisible(true);
        int index = PromotionListView.getSelectionModel().getSelectedIndex();
        SelectedItem = MyList.get(index);
        DescriptionField.setText(SelectedItem.getDescriptionPromotion());
        //DateDebutField.setDayCellFactory((Callback<DatePicker, DateCell>) Date.valueOf(SelectedItem.getDateDebutPromotion()));
        //DateFinField.setDayCellFactory((Callback<DatePicker, DateCell>) Date.valueOf(SelectedItem.getDateFinPromotion()));
        ReductionField.setText(""+SelectedItem.getReduction());
        IdProduitField.setText(""+SelectedItem.getIdProduit());
        Image img = new Image(SelectedItem.getPhotoPromotion(), 388 , 309, true, true);
            PromotionImageView.imageProperty().unbind();
            PromotionImageView.setImage(img);
            
            path=SelectedItem.getPhotoPromotion();
    }

    @FXML
    private void ModifierPromotion(ActionEvent event) throws IOException {
        int reduction=Integer.parseInt(ReductionField.getText());
         int id_produit=Integer.parseInt(IdProduitField.getText());
        Promotion p= new Promotion();
        p.setDescriptionPromotion(DescriptionField.getText());
         p.setDateDebutPromotion(DateDebutField.getValue());
         p.setDateFinPromotion(DateFinField.getValue());
         p.setReduction(reduction);
         p.setPhotoPromotion(path);
         p.setIdProduit(id_produit);
        prom.ModifierPromotion(p,SelectedItem.getIdPromotion() );
        Parent homePage = FXMLLoader.load(getClass().getResource("MesPromotions.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void SupprimerPromotion(ActionEvent event) throws IOException {
        IProduitService iproduit = new ProduitService();
        
        
                if (SelectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainStage);
            alert.setTitle("Suppression ");
            alert.setHeaderText("Aucune promotion selectionné");
            alert.setContentText("Veuillez sélectionner une promotion");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(mainStage);
            alert.setTitle("Confirmation de la suppression");
            alert.setHeaderText("Supprimer promotion");
            alert.setContentText("Veuillez Confirmer la suppression");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    prom.SupprimerPromotion(SelectedItem.getIdPromotion()) ;
                    
                }
            });
        }
                Parent homePage = FXMLLoader.load(getClass().getResource("MesPromotions.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
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

    private void remplirListView() {
        PromotionListView.getItems().clear();
        for(Promotion p : MyList)
        {
            Label lbl = new Label("Date debut :"+p.getDateDebutPromotion().toString()+"\n Date fin : "+p.getDateFinPromotion().toString()+"\n Description :"+p.getDescriptionPromotion()+  "\n"
            );
            String img=p.getPhotoPromotion();
            
                Image image=new Image(img,150,100,true,true);
            
            lbl.setPrefSize(500, 100);
            
                
                ImageView imageView = new ImageView();
                imageView.imageProperty().unbind();
                imageView.setImage(image);
                imageView.setFitWidth(150);
                imageView.setFitHeight(100);
                PromotionListView.getItems().add(lbl);
                lbl.setGraphic(imageView);
        }
        
            
            
        
            } 
    }
    

