package com.example.morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class humanvshumanControler {
    public TextField pseudo1;
    public TextField pseudo2;
    public RadioButton radioJoueur1;
    public RadioButton radioJoueur2;
    public static Player player1 = new Player();
    public static Player  player2 = new Player();

    @FXML
    private Button btnCancel;
    //quand on clique sur le boutton aide il nous affiche le boite du message d'information

    @FXML
    public void commencerJeux(ActionEvent actionEvent) {
        System.out.println(player1.getName());
        Parent newgame;
        try {
            if(!radioJoueur1.isSelected() && !radioJoueur2.isSelected()) radioJoueur1.setSelected(true);

            selectJoueur();

            newgame = FXMLLoader.load(getClass().getResource("humanvshumangameBord.fxml"));
            Scene configurationScene = new Scene(newgame);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(configurationScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   // selectionner le joueur qui commence
    public void selectJoueur() {
        player1.setName(pseudo1.getText());
        player2.setName(pseudo2.getText());

        player1.setPawn("O");
        player2.setPawn("X");
        if(radioJoueur1.isSelected()==true){
            player1.setStart(true);
            radioJoueur2.setSelected(false);
        } else if (radioJoueur2.isSelected()==true) {
            player2.setStart(true);
            radioJoueur1.setSelected(false);

        }

    }
     // revenir à la page précedente
    public void cancel(ActionEvent actionEvent) throws IOException {
        try {
            Parent home = FXMLLoader.load(getClass().getResource("FirstWindows.fxml"));
            Scene scene1 = new Scene(home);
            Stage stage1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage1.setTitle("Tic-Tac-Toe");

            stage1.setScene(scene1);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setting(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("setting.fxml"));
        Stage stage=new Stage();
        stage.setTitle("Settings");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void model(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("model-view.fxml"));
        Stage stage=new Stage();
        stage.setTitle("Models");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    // quiter l'application
    @FXML
    public void exit(ActionEvent event) {
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
