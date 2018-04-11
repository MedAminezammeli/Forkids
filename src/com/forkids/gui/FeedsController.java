/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.gui;

import com.forkids.entities.Commentaire;
import com.forkids.entities.Produit;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import rss.FeedMessage;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class FeedsController implements Initializable {

    @FXML
    private JFXListView<Label> FeedsLis;
    private List<FeedMessage> myFeedMessages;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
    
    
    
      public void remplirListView(List<FeedMessage> feedMessages) {
        FeedsLis.getItems().clear();
        for (FeedMessage c : feedMessages) {
            Label lbl = new Label(c.getTitle() + "  ,url :" + c.getLink() + "  ,Autheur :" + c.getAuthor() + "\n");
            lbl.setPrefSize(500, 100);
            FeedsLis.getItems().add(lbl);
        }
       // listComments.refresh();
    }
    
}
