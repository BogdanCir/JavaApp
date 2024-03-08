package com.example.javaapp;

import domain.Entitate;
import repository.*;
import service.Service;
import domain.Entitate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.Repository;
import repository.TextFileRepository;
import service.Service;

import java.io.IOException;

public class HelloApplication extends Application {



    @Override
    public void start(Stage stage) {
//        Repository<Entitate> repo = new Repository<>();
        repository.TextFileRepository repo = new TextFileRepository("entitati.txt");
        Service service = new Service(repo);
        try {
            service.add(1, "BMW");
            service.add(2, "Dacia");
            service.add(3, "Opel");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        VBox mainVerticalBox = new VBox();
        mainVerticalBox.setPadding(new Insets(10));

        ObservableList<Entitate> entitati = FXCollections.observableArrayList(service.getAllEntitati());
        ListView<Entitate> listView = new ListView<>();
        listView.setItems(entitati);
        mainVerticalBox.getChildren().add(listView);


        GridPane entitateGridPane = new GridPane();
        entitateGridPane.setPadding(new Insets(10,0,0,0));
        Label p1Label = new Label("p1");
//        p1Label.setText("parametrul1:");
        p1Label.setPadding(new Insets(0,10,0,0));
        TextField p1TextField = new TextField();
        Label p2Label = new Label("p2");
//        p2Label.setText("parametrul2:");
        p2Label.setPadding(new Insets(0,10,0,0));
        TextField p2TextField = new TextField();

//        PUNEM GRIDUL IN BOXUL PRINCIPAL
        entitateGridPane.add(p1Label, 0, 0);
        entitateGridPane.add(p1TextField, 1, 0);
        entitateGridPane.add(p2Label, 0, 1);
        entitateGridPane.add(p2TextField, 1, 1);
        mainVerticalBox.getChildren().add(entitateGridPane);

        HBox buttonsHorizontalBox = new HBox();
        buttonsHorizontalBox.setPadding(new Insets(10, 0, 0, 0));
        Button addButton = new Button("Adauga");
        buttonsHorizontalBox.getChildren().add(addButton);
        mainVerticalBox.getChildren().add(buttonsHorizontalBox);


        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try{
                    int p1 = Integer.parseInt(p1TextField.getText());
                    String p2 = p2TextField.getText();
                    service.add(p1, p2);
//                FACEM UN POP-UP CU EROAREA IN CAZ CA NU S-A PUTUT ADAUGA
                }catch (Exception e){
                    Alert errorPopUp = new Alert(Alert.AlertType.ERROR);
                    errorPopUp.setTitle("Error");
                    errorPopUp.setContentText(e.getMessage());
                    errorPopUp.show();
                }
            }
        });

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Entitate e = listView.getSelectionModel().getSelectedItem();
                p1TextField.setText(Integer.toString(e.getP1()));
                p2TextField.setText(e.getP2());
            }
        });


        Button deleteButton = new Button("Delete");
        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int p1 = Integer.parseInt(p1TextField.getText());
                service.delete(p1);
            }
        });
        Button updateButton = new Button("Update");
        updateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int p1 = Integer.parseInt(p1TextField.getText());
                String p2 = p2TextField.getText();
                service.update(p1, p2);
            }
        });
        buttonsHorizontalBox.getChildren().add(updateButton);
        buttonsHorizontalBox.getChildren().add(deleteButton);


//        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        HelloController hc = new HelloController(service);
        loader.setController(hc);

        Scene scene = null;
        try {
            scene = new Scene(loader.load(), 320, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Entitate app!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}