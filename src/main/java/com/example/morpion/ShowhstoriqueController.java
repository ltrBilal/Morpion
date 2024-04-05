package com.example.morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static com.example.morpion.Player.readhistorique;
import static com.example.morpion.humanBordController.winner;

public class ShowhstoriqueController implements Initializable {
    public VBox vbox;
    public HBox hbox;
    @FXML
    public BorderPane bd;
    public Label nomm;

    public void displaye(Player p){

        VBox vbox1 = new VBox();
        ArrayList<Player> liste = readhistorique(p);

        for(Player p1 : liste) {
            HBox hbox1 = new HBox();
            Label lab1 =new Label();
            Label lab2 = new Label();
            lab1.setText(p.getScore());
            lab2.setText(p.getDate);
            hbox1.getChildren().add(lab1) ;
            hbox1.getChildren().add(lab2);


            hbox1.getChildren().add(vbox1);
        }
        bd.getChildren().add(vbox1);


    }
    public VBox createPlayerList(ArrayList<Player> liste) {
        VBox vbox = new VBox();
        vbox.setSpacing(10); // espace entre chaque élément

        for(Player p : liste) {
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER_LEFT); // alignement horizontal des éléments
            hbox.setSpacing(10); // espace entre chaque élément

            Label scoreLabel = new Label("Score : " + p.getScore());
            Label dateLabel = new Label("Date : " + p.getDate());

            hbox.getChildren().addAll(scoreLabel, dateLabel);
            vbox.getChildren().add(hbox);
        }

        return vbox;
    }
    public void display(Player p) {
        ArrayList<Player> liste = readhistorique(p);
        VBox playerList = createPlayerList(liste);
        bd.setCenter(playerList); // affichage de la liste dans le centre du BorderPane
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        display(winner);
        nomm.setText("Welcom "+winner.getName());
    }


    public void back(ActionEvent actionEvent) throws IOException {
        try {
            Parent home = FXMLLoader.load(getClass().getResource("humanvshumangameBord.fxml"));
            Scene scene1 = new Scene(home);
            Stage stage1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage1.setTitle("Morpion ");

            stage1.setScene(scene1);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
