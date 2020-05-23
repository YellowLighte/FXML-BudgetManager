package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PurchaseTableView implements Initializable {

    //Configuring the table
    @FXML private TableView<Purchase> tableView;
    @FXML private TableColumn<Purchase, String> purchaseNameColumn;
    @FXML private TableColumn<Purchase, Double> purchasePriceColumn;
    @FXML private TableColumn<Purchase, String> purchaseTypeColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        purchaseNameColumn.setCellValueFactory(new PropertyValueFactory<Purchase, String>("name"));
        purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<Purchase, Double>("price"));
        purchaseTypeColumn.setCellValueFactory(new PropertyValueFactory<Purchase, String>("type"));

        tableView.setItems(Controller.backend.purchaseObjectsList);
    }

    @FXML
    private void handleBackToMain(ActionEvent e) throws IOException {
        System.out.println("Back button clicked!");
        Parent addPurchaseParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene addPurchaseScene = new Scene(addPurchaseParent);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        window.setScene(addPurchaseScene);
    }



}
