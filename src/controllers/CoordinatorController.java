/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author islam-bilisim
 */
public class CoordinatorController implements Initializable {

    @FXML
    private Label totalfamid;
    @FXML
    private Label famservedid;
    @FXML
    private Label famnservid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    
    @FXML
    private void btnmanagefam(ActionEvent event) {
        openWindow("views/family.fxml","Manage Families");
    }

    
    @FXML
    private void btnaidDist(ActionEvent event) {
        openWindow("views/distribution.fxml","Aid Distribution"); 
    }

  
    @FXML
    private void btnprof(ActionEvent event) {
        openWindow("views/profile.fxml","My Profile");
    }

    @FXML
    private void btnlogout(ActionEvent event) {
        try {
        
            Parent p = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
            Stage stage = (Stage) p.getScene().getWindow();
            stage.setScene(new Scene(p));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    

  private void openWindow(String fileFXML,String title) {
       try{
           Parent p = FXMLLoader.load(getClass().getResource(fileFXML));
           Stage stage = new Stage();
           stage.setTitle(title);
           stage.setScene(new Scene(p));
           stage.show();
           
       }catch(IOException ex){
           ex.printStackTrace();
       }
    }
}


