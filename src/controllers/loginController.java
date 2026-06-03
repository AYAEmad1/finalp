/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAO.userDAO;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.user;

/**
 *
 * @author islam-bilisim
 */
public class loginController implements Initializable {

    @FXML
    private TextField txtnameid, txtpassid;
    @FXML
    private Button loginid, exitid;
    @FXML
    private Label lbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl.setText("Loaded Successfully!");
    }

    @FXML
    private void btnlogin(ActionEvent event) {

        String username = txtnameid.getText().trim();
        String password = txtpassid.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "WARNING", "error you cannot login");
            return;
        }

        user u = userDAO.login(username, password);

        if (u != null) {
            if (u.getRole().equalsIgnoreCase("admin")) {
                openDashboard("adminDashboard.fxml");
            } else {
                openDashboard("coordinatorDashboard.fxml");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "ERROR", "Wrong username or password!!");
        }

    }

    @FXML
    private void btnexit(ActionEvent event) {
        Stage stage = (Stage) exitid.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnreset(ActionEvent event) {
        txtnameid.clear();
        txtpassid.clear();
    }

    private void showAlert(AlertType ERROR, String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void openDashboard(String fxmlFile) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) loginid.getScene().getWindow();
            stage.setScene(new Scene(p));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "ERROR", "Cannot load screen" + fxmlFile);
        }
    }

}
