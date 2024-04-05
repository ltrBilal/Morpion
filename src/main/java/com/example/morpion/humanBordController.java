package com.example.morpion;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class humanBordController   implements Initializable {
    @FXML
    private Button bouton1, bouton2, bouton3, bouton4, bouton5, bouton6, bouton7, bouton8, bouton9;
    @FXML
    private Label nom1, nom2, score1, score2, tourJoueur, pion1, pion2, labelWiner;
    @FXML
    private Button btnQuit;

    private List<Label> infosJoueur1 = new ArrayList<>();
    private List<Label> infosJoueur2 = new ArrayList<>();
    private List<Button> listButtons = new ArrayList<>();

    public int cpt1 = 0, cpt2 = 0;
    private String commencer = "";
    private boolean fin = false;

    Player j1 = humanvshumanControler.player1;
    Player j2 = humanvshumanControler.player2;
    static Stage stage;
    public static Player winner ;

    @FXML //quitter la partie / revenir sur la page ou il y a le choix de mode de jeux
    public void quitterPartie(ActionEvent event) {
        JOptionPane jof;
        jof = new JOptionPane();
        Frame frame;
        frame = new Frame("exit");
        if (jof.showConfirmDialog(frame, "Voulez-vous quitter la partie?", "Information!", jof.YES_NO_OPTION) == jof.YES_NO_OPTION) {
            Parent quitter;
            try {
                quitter = FXMLLoader.load(getClass().getResource("FirstWindows.fxml"));
                Scene configurationScene = new Scene(quitter);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(configurationScene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //la fonction d'initialisation
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        j1.Creathistorique();
        j1.writehistorique(j1);
        j2.Creathistorique();
        j2.writehistorique(j2);

        infosJoueur1.add(nom1);
        infosJoueur1.add(score1);
        infosJoueur1.add(pion1);
        infosJoueur2.add(nom2);
        infosJoueur2.add(score2);
        infosJoueur2.add(pion2);

        if (j1.isStart() == true) {
            tourJoueur.setText(j1.getName().concat(" it's your turn"));
        } else if (j2.isStart() == true) {
            tourJoueur.setText(j2.getName().concat(" it's your turn"));
        }


        nom1.setText(j1.getName());
        score1.setText(String.valueOf(j1.getScore()));

        nom2.setText(j2.getName());
        score2.setText(String.valueOf(j2.getScore()));

        listButtons.add(bouton1);
        listButtons.add(bouton2);
        listButtons.add(bouton3);
        listButtons.add(bouton4);
        listButtons.add(bouton5);
        listButtons.add(bouton6);
        listButtons.add(bouton7);
        listButtons.add(bouton8);
        listButtons.add(bouton9);
    }

    //cette fonction permet de choisir le tour du joueur qui doit jouer
    public void choisirJoueur() {
        if (j1.isStart()) {
            tourJoueur.setText(j2.getName().concat(" it's your turn"));
            commencer = j1.getPawn();
        }

        if (j2.isStart()) {
            tourJoueur.setText(j1.getName().concat(" it's your turn"));
            commencer = j2.getPawn();
        }
    }

    //cette fonction permet de definir les boutons gagnante
    public void gameScore(Button b1, Button b2, Button b3) {
        if (b1.getText() == b2.getText() && b2.getText() == b3.getText() && !b3.getText().isEmpty()) {

            ArrayList<Player> listeh = new ArrayList<>();
            Player myplayer= new Player();

            fin = true;
            tourJoueur.setText("Game Over");

            String val = b1.getText();
            int score = 0;
            List<Label> p = new ArrayList<>();

            if (val.equalsIgnoreCase("O")) {
                cpt1++;
                score = cpt1;
                p = infosJoueur1;
            } else {
                cpt2++;
                score = cpt2;
                p = infosJoueur2;
            }

            if (j1.getPawn() == val) {
                j1.setScore(score);
                p.get(0).setText(j1.getName());
                p.get(1).setText(j1.getScore() + "");

                winner = j1;
                winner.setDate(LocalDate.now());
                myplayer =winner.redahistorique(winner);

                winner.addliste(myplayer.getListehistorique());
                winner.writehistorique(winner);

                labelWiner.setText("The winner is " + j1.getName());

            } else if (j2.getPawn() == val) {
                j2.setScore(score);
                p.get(0).setText(j2.getName());
                p.get(1).setText(j2.getScore() + "");

                winner = j2;
                winner.setDate(LocalDate.now());
                myplayer =winner.redahistorique(winner);

                winner.addliste(myplayer.getListehistorique());
                winner.writehistorique(winner);

                labelWiner.setText("The winner is " + j2.getName());
            }
            else{
                labelWiner.setText("Draw");
            }

            labelWiner.setStyle("-fx-text-fill: blue;-fx-font-size: 29px;");

            RotateTransition rt = new RotateTransition(Duration.seconds(1), labelWiner);
            rt.setByAngle(360);
            rt.setAutoReverse(true);

            ScaleTransition st = new ScaleTransition(Duration.seconds(1), labelWiner);
            st.setToX(2);
            st.setToY(2);

            rt.play();
            st.play();

            FirstfWindowsController.rotationTransition(b1);
            FirstfWindowsController.rotationTransition(b2);
            FirstfWindowsController.rotationTransition(b3);

            FirstfWindowsController.fendu(b1);
            FirstfWindowsController.fendu(b2);
            FirstfWindowsController.fendu(b3);
        }


    }

    //cette fonction permet de definir les boutons gagnante
    public void gagner() {
        gameScore(bouton1, bouton2, bouton3);
        gameScore(bouton4, bouton5, bouton6);
        gameScore(bouton7, bouton8, bouton9);

        gameScore(bouton1, bouton4, bouton7);
        gameScore(bouton2, bouton5, bouton8);
        gameScore(bouton3, bouton6, bouton9);

        gameScore(bouton1, bouton5, bouton9);
        gameScore(bouton3, bouton5, bouton7);
    }

    //cette fonction est appliquer sur les boutons pour pouvoir jouer
    @FXML
    public void jouer(ActionEvent event) {
        Button btn;
        btn = (Button) event.getSource();

        if (btn.getText() != "O" && btn.getText() != "X" && !fin) {
            choisirJoueur();
            btn.setText(commencer);
            gagner();

            j1.setStart(!j1.isStart());
            j2.setStart(!j2.isStart());

            if (btn.getText() == "O") {
                btn.setStyle("-fx-text-fill: green;-fx-font-size: 34px;");
            }
            if (btn.getText() == "X") {
                btn.setStyle("-fx-text-fill: red;-fx-font-size: 34px;");
            }
        }

    }
    // Action pour rejouer
    @FXML
    public void reprendre(ActionEvent event) {
        labelWiner.setText("");
        fin= false ;
        for(Button b :listButtons) {b.setText("");b.setStyle("");}
        if(j1.start){

            tourJoueur.setText(j1.getName().concat(" it's your turn "));
        }
        else {
        tourJoueur.setText(j2.getName().concat(" it's your turn "));}

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
  // regarder l'historique de jeu
    @FXML
    public void historique(ActionEvent actionEvent) {

        Parent option;
        try {
            option = FXMLLoader.load(getClass().getResource("historique.fxml"));
            Scene scene1 = new Scene(option);
            Stage stage1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage1.setTitle("Historic");

            stage1.setScene(scene1);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}