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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Button btnAddIncome, btnAddPurchase, btnShowPurchase;

    @FXML
    VBox purchaseMenu = new VBox();

    @FXML
    Pane chartPane = new Pane();

    public static Backend backend = new Backend();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        purchaseMenu.setVisible(false);
        backend.loadPurchases();
        loadPieChartData();

        
    }


    @FXML
    private void handleAddIncome(ActionEvent e) {
        System.out.println("Add income was clicked!");
        TextInputDialog addIncome = new TextInputDialog();
        addIncome.setTitle("Add income");
        addIncome.setHeaderText("Add income");
        addIncome.setContentText("Income to add:");

        Optional<String> userInput = addIncome.showAndWait();
        if (userInput.isPresent()) {
            backend.addIncome(Double.parseDouble(addIncome.getEditor().getText()));
            System.out.println("Income add was successful!");
        }
    }

    @FXML
    private void handleAddPurchase(ActionEvent e) throws IOException {
        System.out.println("Add purchase was clicked!");
        Parent addPurchaseParent = FXMLLoader.load(getClass().getResource("AddPurchaseWindow.fxml"));
        Scene addPurchaseScene = new Scene(addPurchaseParent);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        window.setScene(addPurchaseScene);

    }

    @FXML
    private void handleShowPurchase(ActionEvent e) throws IOException {
        System.out.println("Show purchase was clicked!");
        Parent purchaseParent = FXMLLoader.load(getClass().getResource("purchaseTableView.fxml"));
        Scene purchaseScene = new Scene(purchaseParent);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        window.setScene(purchaseScene);
    }

    @FXML
    private void handleLoad(ActionEvent e) throws IOException {
        System.out.println("Load was clicked!");
        backend.loadPurchases();
    }

    @FXML
    private void handleSave(ActionEvent e) throws IOException {
        System.out.println("Save was clicked!");
        backend.savePurchases();
    }


    private void loadPieChartData() {
        chartPane.getChildren().clear();
        ObservableList<PieChart.Data> pieChart = FXCollections.observableArrayList();
        pieChart.add(new PieChart.Data("Food", backend.sumOfPurchasesType(backend.purchaseObjectsList, "Food")/backend.getBalance()));
        pieChart.add(new PieChart.Data("Clothes", backend.sumOfPurchasesType(backend.purchaseObjectsList, "Clothes")/backend.getBalance()));
        pieChart.add(new PieChart.Data("Entertainment", backend.sumOfPurchasesType(backend.purchaseObjectsList, "Entertainment")/backend.getBalance()));
        pieChart.add(new PieChart.Data("Other", backend.sumOfPurchasesType(backend.purchaseObjectsList, "Other")/backend.getBalance()));

        PieChart chart = new PieChart(pieChart);
        chartPane.getChildren().add(chart);
    }


}
