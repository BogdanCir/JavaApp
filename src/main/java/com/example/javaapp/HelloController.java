package com.example.javaapp;
import domain.Entitate;
import service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class HelloController {
    @FXML
    private TextField minP1TextField;
    @FXML
    private TextField maxP1TextField;
    @FXML
    private TextField p1TextField;
    @FXML
    private TextField p2TextField;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    private TextField p2Label;
    @FXML
    private ListView<Entitate> listView;
    Service service;

    ObservableList<Entitate> entitati;
    public HelloController(Service service){
        this.service = service;
    }

    public void initialize(){
        entitati = FXCollections.observableArrayList(service.getAllEntitati());
//        entitati = service.getAllEntitati().stream().sorted()
        listView.setItems(entitati);
    }

    @FXML
    void onAddBtnClicked(MouseEvent event){
        try{
            int p1 = Integer.parseInt(p1TextField.getText());
            String p2 = p2TextField.getText();
            service.add(p1, p2);
            entitati.setAll(service.getAllEntitati());
        } catch (Exception e){
            Alert errorPopUp = new Alert(Alert.AlertType.ERROR);
            errorPopUp.setTitle("Error");
            errorPopUp.setContentText(e.getMessage());
            errorPopUp.show();
        }
    }
    @FXML
    void onUpdateBtnClicked(MouseEvent event){
        try{
            int p1 = Integer.parseInt(p1TextField.getText());
            String p2 = p2TextField.getText();
            service.update(p1, p2);
            entitati.setAll(service.getAllEntitati());
        } catch (Exception e){
            Alert errorPopUp = new Alert(Alert.AlertType.ERROR);
            errorPopUp.setTitle("Error");
            errorPopUp.setContentText(e.getMessage());
            errorPopUp.show();
        }
    }
    @FXML
    void onDeleteBtnClicked(MouseEvent event){
        try{
            int p1 = Integer.parseInt(p1TextField.getText());
            String p2 = p2TextField.getText();
            service.delete(p1);
            entitati.setAll(service.getAllEntitati());
        } catch (Exception e){
            Alert errorPopUp = new Alert(Alert.AlertType.ERROR);
            errorPopUp.setTitle("Error");
            errorPopUp.setContentText(e.getMessage());
            errorPopUp.show();
        }
    }

    @FXML
    void onListViewClicked(MouseEvent event){
        Entitate entitate = listView.getSelectionModel().getSelectedItem();
        p1TextField.setText(Integer.toString(entitate.getP1()));
        p2TextField.setText(entitate.getP2());
    }



    @FXML
    void onFilterButtonClicked(MouseEvent event) {
        try {
            int minP1 = Integer.parseInt(minP1TextField.getText());
            int maxP1 = Integer.parseInt(maxP1TextField.getText());

            ObservableList<Entitate> filteredEntitati = FXCollections.observableArrayList();

            for (Entitate entitate : entitati) {
                if (entitate.getP1() >= minP1 && entitate.getP1() <= maxP1) {
                    filteredEntitati.add(entitate);
                }
            }

            listView.setItems(filteredEntitati);
        } catch (NumberFormatException e) {
            Alert errorPopUp = new Alert(Alert.AlertType.ERROR);
            errorPopUp.setTitle("Error");
            errorPopUp.setContentText("Please enter valid numbers for filtering.");
            errorPopUp.show();
        }
    }



}