/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.gui;

import com.forkids.IServices.IProduitService;
import com.forkids.Srevices.ProduitService;
import com.forkids.entities.Produit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rss.RSSFeedParser;
import rss.Feed;
import rss.FeedMessage;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class UserInterfaceController implements Initializable {

    private String path = "";
    private static Produit SelectedItem;
    private List<Produit> MyList;
    private List<Produit> MyListRecherche;
    IProduitService prod = new ProduitService();
    private ObservableList<Produit> data = FXCollections.observableArrayList();
    Stage mainStage;
    @FXML
    private JFXListView<Label> listview;
    @FXML
    private ImageView ImageModif;
    private VBox InputsVBOX;
    @FXML
    private JFXTextField RechercherField;
    @FXML
    private JFXHamburger Hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private ImageView BACK;
    @FXML
    private CheckBox check1;
    @FXML
    private CheckBox check2;

    private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();
    private IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);
    private final int maxNumSelected = 2;
    @FXML
    private JFXToggleButton AfficherProduitsPromotionTPB;
    @FXML
    private ImageView rssButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String path = "file:/C:/Users/amine/Documents/NetBeansProjects/Forkids/src/src/1425834768gris-dynamique.jpg";
        Image img = new Image(path, 388, 309, true, true);
        BACK.setImage(img);

        try {
            VBox box = FXMLLoader.load(getClass().getResource("homepannel.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(UserInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Hamburger

        HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(Hamburger);
        burgerTask.setRate(-1);

        Hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e
                -> {
            burgerTask.setRate(burgerTask.getRate() * -1);
            burgerTask.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });

        List<Produit> produits = new ArrayList<Produit>();
        produits = prod.AfficherProduit();
        data.clear();
        data.addAll(produits);
        MyList = prod.AfficherProduit();

        remplirListView();
        //listview.setItems(data);

        configureCheckBox(check1);
        configureCheckBox(check2);

        numCheckBoxesSelected.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            if (newSelectedCount.intValue() >= maxNumSelected) {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(true));

            } else {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(false));

            }
        });

        rssButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                RSSFeedParser parser = new RSSFeedParser("https://www.gamereactor.fr/rss/rss.php?texttype=4");
                Feed feed = parser.readFeed();
                System.out.println(feed);
                for (FeedMessage message : feed.getMessages()) {
                    System.out.println(message);

                }
                
                try {
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/forkids/gui/Feeds.fxml"));
                Parent page1 = (Parent) loader.load();
                FeedsController feedsController = loader.getController();
                feedsController.remplirListView(feed.getMessages());
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Forkids - Home");
                stage.show();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
               

                event.consume();
            }
        });

    }

    private void remplirListView() {
        listview.getItems().clear();
        for (Produit p : MyList) {
            Label lbl = new Label("Nom produit : " + p.getNomProduit() + "\n Description :" + p.getDescription() + "\n Type produit : " + p.getTypeProduit() + "\n Prix produit : " + p.getPrixProduit().toString() + "\n");
            String img = p.getImage();

            Image image = new Image(img, 150, 100, true, true);

            lbl.setPrefSize(500, 100);

            ImageView imageView = new ImageView();
            imageView.imageProperty().unbind();
            imageView.setImage(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(100);
            listview.getItems().add(lbl);
            lbl.setGraphic(imageView);
        }

    }

    @FXML
    private void remplirInputs(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/forkids/gui/ProductDetails.fxml"));
        Parent page1 = (Parent) loader.load();
        ProductDetailsController detailsController = loader.getController();
        int index = listview.getSelectionModel().getSelectedIndex();
        Produit p = new Produit();
        p = MyList.get(index);
        detailsController.InitializeDetails(p);

        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Forkids - Home");
        stage.show();

    }

    @FXML
    private void RechercherProduit(KeyEvent event) {
        List<String> values = new ArrayList<>();
        for (CheckBox selectedCheckBoxe : selectedCheckBoxes) {
            values.add(selectedCheckBoxe.getText());
        }
        MyListRecherche = prod.RechercherProduit(RechercherField.getText(), values);
        listview.getItems().clear();
        for (Produit p : MyListRecherche) {
            Label lbl = new Label("Nom produit : " + p.getNomProduit() + "\n Description :" + p.getDescription() + "\n Type produit : " + p.getTypeProduit() + "\n Prix produit : " + p.getPrixProduit().toString() + "\n");
            String img = p.getImage();

            Image image = new Image(img, 150, 100, true, true);

            lbl.setPrefSize(500, 100);

            ImageView imageView = new ImageView();
            imageView.imageProperty().unbind();
            imageView.setImage(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(100);
            listview.getItems().add(lbl);
            lbl.setGraphic(imageView);
        }
    }

    private void configureCheckBox(CheckBox checkBox) {

        if (checkBox.isSelected()) {
            selectedCheckBoxes.add(checkBox);
        } else {
            unselectedCheckBoxes.add(checkBox);
        }

        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                unselectedCheckBoxes.remove(checkBox);
                selectedCheckBoxes.add(checkBox);
            } else {
                selectedCheckBoxes.remove(checkBox);
                unselectedCheckBoxes.add(checkBox);
            }

        });

    }

    @FXML
    private void AfficherProduitsPromotion(ActionEvent event) {
        if (AfficherProduitsPromotionTPB.isSelected()) {
            listview.getItems().clear();
            List<Produit> MyListPromotion = prod.GetProduitPromotion();
            for (Produit p : MyListPromotion) {
                Label lbl = new Label("Nom produit : " + p.getNomProduit() + "\n Description :" + p.getDescription() + "\n Type produit : " + p.getTypeProduit() + "\n Prix produit : " + p.getPrixProduit().toString() + "\n");
                String img = p.getImage();

                Image image = new Image(img, 150, 100, true, true);

                lbl.setPrefSize(500, 100);

                ImageView imageView = new ImageView();
                imageView.imageProperty().unbind();
                imageView.setImage(image);
                imageView.setFitWidth(150);
                imageView.setFitHeight(100);
                listview.getItems().add(lbl);
                lbl.setGraphic(imageView);
            }
        } else {
            remplirListView();
        }

    }
}
