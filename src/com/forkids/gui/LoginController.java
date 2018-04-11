/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.gui;

import com.forkids.utils.Authentification;
import com.forkids.utils.DataSource;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.forkids.entities.User;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class LoginController implements Initializable {
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
            return matcher.find();
    }
    

    @FXML
    private JFXTextField emailtxt;
    @FXML
    private JFXPasswordField passwordtxt;
    @FXML
    private JFXButton loginbnt;
    @FXML
    private JFXButton registerbtn;
    @FXML
    private Text state;
    @FXML
    private JFXSpinner sp;

     /**
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(DataSource.getInstance().getConnection()!=null) state.setText("Connection Established to server");
        else {
            state.setText("Connection problem !");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Connection problem !");
            alert.setHeaderText("Cannot establish connexion to server");
            alert.setContentText("Please verify your connexion");

            alert.showAndWait();
            
        }
        
        sp.setVisible(false);
    }    

    @FXML
    private void recheck(KeyEvent event) {
    }

    @FXML
    private void login(ActionEvent event) {
        User user;
      Authentification session=Authentification.getSession();
        
        
        if (emailtxt.getText().length()!=0 && passwordtxt.getText().length()!=0){
            sp.setVisible(true);
            user = session.login(emailtxt.getText().toLowerCase(),passwordtxt.getText());
                if(user.getRole().contains("ROLE_ADMIN"))
                {
                    
                  //Open Home
                        try {
                        Parent page1 = FXMLLoader.load(getClass().getResource("/com/forkids/gui/Home.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Forkids - Home");
                        stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(com.forkids.gui.HomeController.class.getName()).log(Level.SEVERE, null, ex);
                             }
                }
            
            else 
                    if (user.getRole().contains("ROLE_MEDECIN")){
                        try {
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
            else{
                System.out.println("wrong parameters login");
                
            }                
        }
        
        sp.setVisible(false);
    }
    
    

    @FXML
    private void showRegister(MouseEvent event) {
    }

    @FXML
    private void forgot(MouseEvent event) {
    }
    
}
