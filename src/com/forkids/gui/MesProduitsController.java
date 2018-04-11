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
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.forkids.IServices.IProduitService;
import com.forkids.Srevices.ProduitService;

import com.forkids.entities.Produit;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedOutput;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Anisb
 */
public class MesProduitsController implements Initializable {

    private String path = "";

    @FXML
    private JFXButton ModifierProduit;
    @FXML
    private JFXButton SupprimerProduit;

    @FXML
    private TextField NomProduit;
    @FXML
    private TextField PrixProduit;
    @FXML
    private TextField Description;
    @FXML
    private TextField PrixPromotion;
    private static Produit SelectedItem;
    private List<Produit> MyList;
    private List<Produit> MyListPromotion;
    private List<Produit> MyListRecherche;
    IProduitService prod = new ProduitService();
    private ObservableList<Produit> data = FXCollections.observableArrayList();
    Stage mainStage;
    @FXML
    private JFXListView<Label> listview;
    @FXML
    private ImageView ImageModif;
    @FXML
    private JFXButton upload;
    private VBox InputsVBOX;
    @FXML
    private JFXTextField RechercherField;
    @FXML
    private JFXToggleButton AfficherProduitsPromotionTPB;
    @FXML
    private JFXToggleButton ExportExcelPB;
    @FXML
    private JFXHamburger Hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label nomProduitLabel;
    @FXML
    private Label prixProduitLabel;
    @FXML
    private Label descProduitLabel;
    @FXML
    private Label prixPromoProduitLabel;
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
    private ImageView rssButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String path = "file:/C:/Users/amine/Documents/NetBeansProjects/Forkids/src/src/1425834768gris-dynamique.jpg";
        Image img = new Image(path, 388, 309, true, true);
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
        Description.setVisible(false);
        NomProduit.setVisible(false);
        PrixProduit.setVisible(false);
        PrixPromotion.setVisible(false);
        descProduitLabel.setVisible(false);
        nomProduitLabel.setVisible(false);
        prixProduitLabel.setVisible(false);
        prixPromoProduitLabel.setVisible(false);
        ModifierProduit.setVisible(false);
        SupprimerProduit.setVisible(false);
        upload.setVisible(false);
        Description.setVisible(false);
        NomProduit.setVisible(false);
        PrixProduit.setVisible(false);
        PrixPromotion.setVisible(false);

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
                System.out.println("Tile pressed ");
                makeRss();
                event.consume();
            }
        });

    }

    @FXML
    private void ModifierProduit(ActionEvent event) throws IOException {
        Double prix = Double.parseDouble(PrixProduit.getText());
        Double prixPromo = Double.parseDouble(PrixPromotion.getText());
        Produit p = new Produit();
        p.setNomProduit(NomProduit.getText());
        p.setPrixProduit(prix);
        p.setPrixPromo(prixPromo);
        p.setImage(path);
        p.setTypeProduit(SelectedItem.getTypeProduit());
        p.setDescription(Description.getText());

        prod.ModifierProduit(p, SelectedItem.getIdProduit());
        Parent homePage = FXMLLoader.load(getClass().getResource("MesProduits.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void SupprimerProduit(ActionEvent event) throws IOException {
        IProduitService iproduit = new ProduitService();

        if (SelectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainStage);
            alert.setTitle("Suppression ");
            alert.setHeaderText("Aucun produit selectionné");
            alert.setContentText("Veuillez sélectionner un produit");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(mainStage);
            alert.setTitle("Confirmation de la suppression");
            alert.setHeaderText("Supprimer produit");
            alert.setContentText("Veuillez Confirmer la suppression");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    iproduit.SupprimerProduit(SelectedItem.getIdProduit());

                }
            });
        }
        Parent homePage = FXMLLoader.load(getClass().getResource("MesProduits.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
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
    private void remplirInputs(MouseEvent event) {
        Description.setVisible(true);
        NomProduit.setVisible(true);
        PrixProduit.setVisible(true);
        PrixPromotion.setVisible(true);
        descProduitLabel.setVisible(true);
        nomProduitLabel.setVisible(true);
        prixProduitLabel.setVisible(true);
        prixPromoProduitLabel.setVisible(true);
        ModifierProduit.setVisible(true);
        SupprimerProduit.setVisible(true);
        upload.setVisible(true);
        int index = listview.getSelectionModel().getSelectedIndex();
        SelectedItem = MyList.get(index);
        //ProductId.set(SelectedItem.getIdProduit());
        NomProduit.setText(SelectedItem.getNomProduit());
        PrixProduit.setText(SelectedItem.getPrixProduit().toString());
        Description.setText(SelectedItem.getDescription());
        PrixPromotion.setText(SelectedItem.getPrixPromo().toString());
        Image img = new Image(SelectedItem.getImage(), 388, 309, true, true);
        ImageModif.imageProperty().unbind();
        ImageModif.setImage(img);
        path = SelectedItem.getImage();
        ModifierProduit.setVisible(true);
        SupprimerProduit.setVisible(true);
        upload.setVisible(true);

    }

    @FXML
    private void uploadModif(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(ImageModif.getScene().getWindow());
        if (file != null) {
            Image img = new Image(file.toURI().toString(), 388, 309, true, true);
            ImageModif.imageProperty().unbind();
            ImageModif.setImage(img);

        }
        path = file.toURI().toURL().toString();
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

    @FXML
    private void AfficherProduitsPromotion(ActionEvent event) {

        if (AfficherProduitsPromotionTPB.isSelected()) {
            listview.getItems().clear();
            MyListPromotion = prod.GetProduitPromotion();
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

    private void saveXLSFile(File file) {//fichier excel code
        try {

            IProduitService pro = new ProduitService();
            FileOutputStream fileOut;
            fileOut = new FileOutputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet workSheet = workbook.createSheet("sheet 0");
            HSSFRow row1 = workSheet.createRow((short) 0);
            row1.createCell(0).setCellValue("id produit");
            row1.createCell(1).setCellValue("nom produit");
            row1.createCell(2).setCellValue("type produit");
            row1.createCell(3).setCellValue("prix produit");
            row1.createCell(4).setCellValue("description");
            row1.createCell(5).setCellValue("prix promotion");

            HSSFRow row2;

            ResultSet rs = pro.GetAllProductExcel();
            while (rs.next()) {
                int a = rs.getRow();
                row2 = workSheet.createRow((short) a);
                row2.createCell(0).setCellValue(rs.getInt(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getString(3));
                row2.createCell(3).setCellValue(rs.getDouble(4));
                row2.createCell(4).setCellValue(rs.getString(5));
                row2.createCell(5).setCellValue(rs.getDouble(6));

            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();

            TrayNotification tn = new TrayNotification("NOUVEAU FICHIER EXCEL", "Le fichier Excel à été généré avec succé", NotificationType.SUCCESS);
            tn.showAndDismiss(Duration.seconds(1));
        } catch (SQLException | IOException e) {
            TrayNotification tn = new TrayNotification("NOUVEAU FICHIER EXCEL", "Le fichier Excel à été généré avec succé", NotificationType.ERROR);
            tn.showAndDismiss(Duration.seconds(1));
            System.err.println(e);

        }
    }

    @FXML
    private void ExportExcel(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(*.xls)", "*.xls");
        chooser.getExtensionFilters().add(filter);
        // Show save dialog
        File file = chooser.showSaveDialog(ExportExcelPB.getScene().getWindow());
        if (file != null) {
            saveXLSFile(file);

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

    public void makeRss() {

        System.out.println("debut");
        try {
            String feedType = "rss_2.0";
            String fileName = "rssFeed.xml";
            SyndFeed feed = new SyndFeedImpl();
            feed.setFeedType(feedType);

            feed.setTitle("Product AllForKids List");
            feed.setLink("https://allforkids.com");
            feed.setDescription("RSS for our products");

            List entries = new ArrayList();
            
            
            for (Produit produit : MyList) {
                
           
            SyndEntry entry;
            SyndContent description;
            entry = new SyndEntryImpl();
            entry.setTitle(produit.getNomProduit());
            entry.setLink("https://allforkids.com");

            description = new SyndContentImpl();
            description.setType("text/plain");
            description.setValue(produit.getDescription());
            entry.setDescription(description);

            entries.add(entry);
            
             }
                    
                    
            feed.setEntries(entries);

            Writer writer = new FileWriter(fileName);
            SyndFeedOutput output = new SyndFeedOutput();
            output.output(feed, writer);
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void statproduit(MouseEvent event) throws IOException {
        
    Parent page1 = FXMLLoader.load(getClass().getResource("/com/forkids/gui/BackendAcceuil.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Forkids - Home Admin");
                        stage.show();
                        } 

}


