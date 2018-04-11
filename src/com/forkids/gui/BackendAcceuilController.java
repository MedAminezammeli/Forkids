/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.gui;

import com.forkids.Srevices.ProduitService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class BackendAcceuilController implements Initializable {

    @FXML
    private Button stat;
    @FXML
    private Button promo;
    @FXML
    private Button prod;
    @FXML
    private AnchorPane main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void getStats(ActionEvent event) {

        main.getChildren().add(createPieChart());

    }

    @FXML
    private void getPromos(ActionEvent event) {
    }

    @FXML
    private void getProducts(ActionEvent event) {
    }

    public Chart createPieChart() {
        ProduitService etabService = new ProduitService();

 ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Vetements"+Math.round(etabService.Pourcentage("vetements")*100)+"%", Math.round(etabService.Pourcentage("vetements")*100)),
                new PieChart.Data("Nourriture"+Math.round(etabService.Pourcentage("nourriture")*100)+"%", Math.round(etabService.Pourcentage("nourriture")*100)),
                new PieChart.Data("Jeux"+Math.round(etabService.Pourcentage("jeux")*100)+"%", Math.round(etabService.Pourcentage("jeux")*100)),
                new PieChart.Data("Accessoires"+Math.round(etabService.Pourcentage("accessoires")*100)+"%", Math.round(etabService.Pourcentage("accessoires")*100)));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported Products");

        return chart;

    }

}
