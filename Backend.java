package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Backend {
    ObservableList<Purchase> purchaseObjectsList = FXCollections.observableArrayList();
    //ArrayList<Purchase> purchaseObjectsList = new ArrayList<>();
    Double purchaseTotal;
    //Double grandTotal = 0.0;
    //Double incomeTotal = 0.0;
    Double balance = 0.00;
    File file = new File("Purchases.txt");



    public void addIncome(double income) {
        this.balance += income;
        System.out.println("Income was added!");
    } //Keep

    public void addPurchase(Purchase purchase) {
        purchaseObjectsList.add(purchase);
        this.balance -= purchase.getPrice();
    } //Keep


    public String printPurchases() {
        String str = "";
        for (int i = 0; i < purchaseObjectsList.size(); i++) {
            str += this.purchaseObjectsList.get(i).print() + "\n";
        }
        return str;
    } //Keep

    public String printTypePurchases(String type) {
        String str = "";
        for (int i = 0; i < purchaseObjectsList.size(); i++) {
            if (purchaseObjectsList.get(i).print().contains(type)) {
                str += this.purchaseObjectsList.get(i).print() + "\n";
            }
        }
        return str;
    } //Keep

    public double sumOfPurchases(ArrayList<Purchase> arr) {
        double sum = 0;

        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i).getPrice();
        }


        return sum;
    } //Keep

    public double sumOfPurchasesType(ArrayList<Purchase> arr, String type) {
        double sum = 0;

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getType().equals(type)) {
                sum += arr.get(i).getPrice();
            }
        }

        return sum;
    } //Keep

    public double getBalance() {

        return this.balance;
    } //Keep.

    public void savePurchases() {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println(balance);
            for (int i = 0; i < purchaseObjectsList.size(); i++) {
                writer.print(purchaseObjectsList.get(i).print() + "\n");

                System.out.println("Purchase line was saved!");
            }
        } catch (Exception e) {
            System.out.println("An error has occurred saving purchases.");
            System.out.println(e.getMessage());
        }
    }  //Keep

    public void loadPurchases() {
        try (Scanner fileScanner = new Scanner(file)) {
            this.balance = Double.parseDouble(fileScanner.next());
            while (fileScanner.hasNext()) {
                Purchase loadPurchase = new Purchase(fileScanner.next(), fileScanner.next(), fileScanner.nextDouble());
                purchaseObjectsList.add(loadPurchase);
                System.out.println("Purchase loaded!");
            }
        } catch (Exception e) {
            System.out.println("An error has occurred loading purchases.");
            System.out.println(e.getMessage());
        }
    } //Keep

    public int purchaseTypeNum(ArrayList<Purchase> arr, String type) {
        int count = 0;

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getType().contains(type)) {
                count++;
            }
        }
        System.out.println("Number of " + type + " elements: " + count);
        return count;
    }




}
