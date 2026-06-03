/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import DAO.userDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author islam-bilisim
 */
public class AdmindashController implements Initializable {

    @FXML
    private TextField txtadminid;
    @FXML
    private Button logid;
    @FXML
    private Label totalcorid,lbladminid;
    @FXML
    private Label totaluid;
    @FXML
    private Label totalorgid;
    @FXML
    private Label totalfamid;
    @FXML
    private Label servid;
    @FXML
    private Label noservid;
    @FXML
    private Button managorgid;
    @FXML
    private Button managuid;
    @FXML
    private Button managfamid;
    @FXML
    private Button viewid;

 
    private userDAO ud = new userDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    


  

    @FXML
    private void btnlogout(ActionEvent event) {
        openWindow("/views/login.fxml","login");
    }

    @FXML
    private void btnorg(ActionEvent event) {
        openWindow("/views/organization.fxml","Manage Organization");
    }

    @FXML
    private void btnuser(ActionEvent event) {
        openWindow("/views/users.fxml","Manage User");
    }

    @FXML
    private void btnfam(ActionEvent event) {
        openWindow("/views/family.fxml","Manage Families");
    }

    @FXML
    private void btnaid(ActionEvent event) {
        openWindow("/views/aidDistribution.fxml","View Aid Destribution");
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
