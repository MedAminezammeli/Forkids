/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forkids;

import com.forkids.IServices.IProduitService;
import com.forkids.Srevices.ProduitService;
import com.forkids.entities.Produit;
import java.awt.BorderLayout;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author amine
 */
public class Forkids extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException{
        
         
        Parent homePage = FXMLLoader.load(getClass().getResource("/com/forkids/gui/Login.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        primaryStage.setScene(homePage_scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);    
    }
    
}
