package com.example.morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AccueilController {


    @FXML
    public void startGame(ActionEvent event) {
        Parent fenetre1;
        try {
            fenetre1 = FXMLLoader.load(getClass().getResource("FirstWindows.fxml"));
            Scene scene1 = new Scene(fenetre1);
            Stage stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage1.setTitle("Tic-Tac-Toe");

            stage1.setScene(scene1);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //pour quitter le jeux
    @FXML
    public void quitGame(ActionEvent event) {
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

}
