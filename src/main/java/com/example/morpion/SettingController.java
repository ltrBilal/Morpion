package com.example.morpion;

import ia.Config;
import ia.ConfigFileLoader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class SettingController implements Initializable  {
    public TextField text1;
    public TextField text2;
    public TextField text3;
    public TextField text4;
    public TextField text5;
    public TextField text6;
    public TextField text7;
    public TextField text8;
    public TextField text9;

    @FXML
    private Button btnApply;

    @FXML
    private Button btnBack;

    @FXML
    protected void onHelloButtonClick() {
        initialise();
    }

    protected void initialise(){
        ConfigFileLoader configFileLoader= new ConfigFileLoader();
        configFileLoader.loadConfigFile("resources/config.txt");
        Config config1 ;Config config2 ;Config config3;
        config1=configFileLoader.get("F");
        config2=configFileLoader.get("D");
        config3=configFileLoader.get("M");// cest pour text 7 à 9

        configFileLoader.toString();

        text1.setText(String.valueOf(config1.hiddenLayerSize));
        text2.setText(String.valueOf(config1.learningRate));
        text3.setText(String.valueOf(config1.numberOfhiddenLayers));

        //dificile
        text4.setText(String.valueOf(config2.hiddenLayerSize));
        text5.setText(String.valueOf(config2.learningRate));
        text6.setText(String.valueOf(config2.numberOfhiddenLayers));

        // Moyen
        text7.setText(String.valueOf(config3.hiddenLayerSize));
        text8.setText(String.valueOf(config3.learningRate));
        text9.setText(String.valueOf(config3.numberOfhiddenLayers));
    }

    public void update(ActionEvent actionEvent) {
        String text1update = text1.getText();
        String text2update = text2.getText();
        String text3update = text3.getText();
        String text4update = text4.getText();
        String text5update = text5.getText();
        String text6update = text6.getText();
        String text7update = text7.getText();
        String text8update = text8.getText();
        String text9update = text9.getText();

        String str1 = "F"+":"+text1update+":"+text2update+":"+text3update;
        String str2 = "D"+":"+text4update+":"+ text5update+":"+text6update;
        String str3= "M"+":"+text7update+":" + text8update+":"+text9update;

        try {
            FileWriter fw = new FileWriter("resources/config.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str1);
            bw.newLine(); // ajoute une nouvelle ligne
            bw.write(str2);
            bw.newLine();
            bw.write(str3);
            bw.newLine();
            bw.close();
            fw.close();

            Stage stage = (Stage) btnApply.getScene().getWindow();
            stage.close();

        } catch (IOException e) {
            System.out.println("Une erreur est survenue: " + e.getMessage());
        }

    }


    public void back(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialise();

    }
}