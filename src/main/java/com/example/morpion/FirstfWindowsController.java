package com.example.morpion;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FirstfWindowsController{

    @FXML
    private Button btnCancel;

    //Action pour jouer avec la machine
    @FXML
    public void playWhithMachine(ActionEvent actionEvent) {

        Parent option;
        try {
            option = FXMLLoader.load(getClass().getResource("humanVsIa.fxml"));
            Scene scene1 = new Scene(option);
            Stage stage1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage1.setTitle("Tic-Tac-Toe");

            stage1.setScene(scene1);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Action pour jouer humain contre humain
    @FXML
    public void playWhithHuman(ActionEvent actionEvent) {
        Parent optionIA;
        try {
            optionIA = FXMLLoader.load(getClass().getResource("humanvshuman.fxml"));
            Scene scene1 = new Scene(optionIA);
            Stage stage1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage1.setTitle("Tic-Tac-Toe");
            stage1.setScene(scene1);
            stage1.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void about(ActionEvent event) {
        JOptionPane jOption;
        jOption = new JOptionPane();
        jOption.showMessageDialog(null,"Tic-tac-toe, also called Morpion (by analogy with the game of Morpion)\n and oxo in Belgium, is a game of reflection practiced by two players,\n turn by turn, the aim of which is to create the first alignment.", "About the game", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    public void help(ActionEvent event) {
        JOptionPane jOption;
        jOption = new JOptionPane();
        String message = "Two players compete. They must each in turn fill a box of the grid with the \n symbol assigned to them: O or X. The winner is the one who manages to align \n three identical symbols, horizontally, vertically or diagonally.\n ";    jOption.showMessageDialog(null,message, "About the game", JOptionPane.INFORMATION_MESSAGE);
    }

    // fermer l'application
    @FXML
    public void quit(ActionEvent event) {
        JOptionPane jof;
        //Affiche la boite de dialogue pour quitter le jeux
        jof = new JOptionPane();
        Frame frame;
        frame = new Frame("exit");
        // Si on choisit oui  alors on quitte le jeu, sinon on reste dans le jeu
        if(jof.showConfirmDialog(frame, "Voulez-vous quitter le jeux?","Information!",jof.YES_NO_OPTION) == jof.YES_NO_OPTION) {
            System.exit(0);
        }
    }
   // revenire à la page precédente
    @FXML
    public void cancel(ActionEvent actionEvent){
        try {
            Parent home = FXMLLoader.load(getClass().getResource("Acueil.fxml"));
            Scene scene1 = new Scene(home);
            Stage stage1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage1.setTitle("Tic-Tac-Toe");

            stage1.setScene(scene1);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     // pour aller à la page de réglage
    @FXML
    public void setting(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("setting.fxml"));
        Stage stage=new Stage();
        stage.setTitle("Settings");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
     //pour aller à la page de gestion des models
    @FXML
    public void model(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("model-view.fxml"));
        Stage stage=new Stage();
        stage.setTitle("Models");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    //**************** deux fonction pour les annimation

    //transition des rotations
    public static void rotationTransition(Button btn) {
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000),btn);
        rotateTransition.setByAngle(180f);
        rotateTransition.setCycleCount(4);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();
         }
    public static void fendu(Button btn){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), btn);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(2);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
         }


}
