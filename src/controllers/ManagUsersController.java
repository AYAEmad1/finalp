/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import DAO.userDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.organization;
import models.user;

/**
 * FXML Controller class
 *
 * @author islam-bilisim
 */
public class ManagUsersController implements Initializable {

    @FXML
    private TextField txtfullname;
    @FXML
    private TextField txtusername;
    @FXML
    private TextField txtpass;
    @FXML
    private TextField txtemail;
    @FXML
    private ComboBox<String> combidrole;
    @FXML
    private ComboBox<String> combidorg;
    @FXML
    private TableView<user> tableid;
    @FXML
    private TableColumn<user, Integer> colid;
    @FXML
    private TableColumn<user, String> colfname;
    @FXML
    private TableColumn<user, String> colusername;
    @FXML
    private TableColumn<user, String> colemail;
    @FXML
    private TableColumn<user, String> colrole;
    @FXML
    private TableColumn<user, String> colorgname;
    private ObservableList<user> userlist = FXCollections.observableArrayList();
    private ObservableList<String> orglist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        combidrole.setItems(FXCollections.observableArrayList("Admin", "Coordinator"));
        colid.setCellValueFactory(new PropertyValueFactory<user, Integer>("ID"));
        colfname.setCellValueFactory(new PropertyValueFactory<user, String>("FullName"));
        colemail.setCellValueFactory(new PropertyValueFactory<user, String>("Email"));
        colrole.setCellValueFactory(new PropertyValueFactory<user, String>("Role"));
        colusername.setCellValueFactory(new PropertyValueFactory<user, String>("UsrName"));
        colorgname.setCellValueFactory(new PropertyValueFactory<user, String>("Org Name"));
        loadOrg();
        refreshTable();
    }

    private void loadOrg() {
        orglist.clear();
        orglist.add("NO ORGANIZATION");
        combidorg.setItems(orglist);
        combidorg.getSelectionModel().selectFirst();
    }

    private void refreshTable() {
        userlist.clear();
        tableid.setItems(userlist);
        tableid.getSelectionModel().clearSelection();
    }

    private ObservableList<user> userList = FXCollections.observableArrayList();

    @FXML
    private void btnadd(ActionEvent event) {
        String fname = txtfullname.getText().trim();
        String uname = txtusername.getText().trim();
        String password = txtpass.getText();
        String email = txtemail.getText().trim();
        String role = combidrole.getValue();
        String org = combidorg.getValue();

        if (fname.isEmpty() || uname.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "WARNING", "Please fill all fields!");
            return;
        }

        user newUser = new user();
        newUser.setFull_name(fname);
        newUser.setUser_name(uname);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setRole(role);
        if (org != null) {
            newUser.setOrganization((organization) orglist);
        }

        boolean success = userDAO.adduser(newUser);
        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "SUCCESS", "User added successfully!");
            refreshTable();
            clearFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "ERROR", "Failed to add user!");
        }
    }

    @FXML
    private void btnupdate(ActionEvent event) {
        user selectedUser = tableid.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert(Alert.AlertType.WARNING, "WARNING", "choose a uesr from table please!");
            return;
        }

        selectedUser.setFull_name(txtfullname.getText().trim());
        selectedUser.setUser_name(txtusername.getText().trim());
        selectedUser.setPassword(txtpass.getText());
        selectedUser.setEmail(txtemail.getText().trim());
        selectedUser.setRole(combidrole.getValue());

        String selectedOrgName = combidorg.getValue();

        if (selectedOrgName != null && !selectedOrgName.equals("NO ORGANIZATION")) {
            organization org = new organization();
            org.setName(selectedOrgName);
            selectedUser.setOrganization(org);
        } else {
            selectedUser.setOrganization(null);
        }

        boolean success = userDAO.updateuser(selectedUser);
        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "SUCCESS", "Information updated successfully");
            refreshTable();
            clearFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "ERROR", "updateing failed");
        }
    }

    // زر الحذف (Delete)
    @FXML
    private void btndel(ActionEvent event) {
        user selectedUser = tableid.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert(Alert.AlertType.WARNING, "WARNING", "choose user to delete!");
            return;
        }

        boolean success = userDAO.deleteuser(selectedUser);
        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "deleted done", "deleted user has beendone successfully");
            refreshTable();
            clearFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "ERROR", "deleting  failed");
        }
    }

    @FXML
    private void handleTableClick(MouseEvent event) {
        user selectedUser = tableid.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            txtfullname.setText(selectedUser.getFull_name());
            txtusername.setText(selectedUser.getUser_name());
            txtpass.setText(selectedUser.getPassword());
            txtemail.setText(selectedUser.getEmail());
            combidrole.setValue(selectedUser.getRole());

            if (selectedUser.getOrganization() != null) {

                combidorg.setValue(selectedUser.getOrganization().getName());
            } else {

                combidorg.setValue("NO ORGANIZATION");
            }
        }
    }

    private void clearFields() {
        txtfullname.clear();
        txtusername.clear();
        txtpass.clear();
        txtemail.clear();
        combidrole.setValue(null);
        combidorg.setValue(null);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
