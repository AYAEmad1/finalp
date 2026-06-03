/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ghads;

import controllers.loginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;

/**
 *
 * @author islam-bilisim
 */
public class GHADS extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        
        Parent root = FXMLLoader.load(getClass().getResource("/views/ManagUsersController.fxml")); 
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("User Management System");
        stage.show();
    }
   
}
    
    
    
    

    
    
    
