package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPurchaseWindow implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    Button btnSubmitIncome;

    @FXML
    TextField txtPurchaseName, txtPurchasePrice;

    @FXML
    VBox addPurchaseMenu = new VBox();

    @FXML
    RadioButton rbFood, rbClothes, rbEntertainment, rbOther;

    @FXML
    ToggleGroup purchaseGroup;

    @FXML
    private void handleAddPurchase() {
        System.out.println("Add purchase button clicked!");
        double purchasePrice = Double.parseDouble(txtPurchasePrice.getText());
        Purchase purchase = new Purchase(txtPurchaseName.getText(),purchaseGroup.getSelectedToggle().toString(), purchasePrice);
        Controller.backend.addPurchase(purchase);
        System.out.println("Purchase created!");
        txtPurchaseName.setText("");
        txtPurchasePrice.setText("");
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
