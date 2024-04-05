package com.example.morpion;

import  com.example.morpion.ProgessBarContoller;
import ia.Config;
import ia.ConfigFileLoader;
import ia.MultiLayerPerceptron;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class humanVsIaController {
    @FXML
    public TextField nom;
    public RadioButton difficile;
    public RadioButton facile, medium;
    static Player human = new Player();
    static Player computer = new Player();
    public static String level;
    public static  String  path;
    public static int h, lh;
    public static double lr;
    public  static MultiLayerPerceptron mlp;

    String chemin = "C:\\Users\\pc\\IdeaProjects\\sifaoufatai\\src\\main\\resources\\com\\example\\morpion\\models";

    @FXML
    public void setting(ActionEvent event) {
        Parent nouveauJeux;
        try {
            nouveauJeux = FXMLLoader.load(getClass().getResource("setting.fxml"));
            Scene configurationScene = new Scene(nouveauJeux);
            Stage stage = new Stage();
            stage.setTitle("Setting");
            stage.setScene(configurationScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void model(ActionEvent event) {
        Parent nouveauJeux;
        try {
            nouveauJeux = FXMLLoader.load(getClass().getResource("model-view.fxml"));
            Scene configurationScene = new Scene(nouveauJeux);
            Stage stage = new Stage();
            stage.setTitle("Models");
            stage.setScene(configurationScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void help(ActionEvent actionEvent) {
        JOptionPane jOption;
        jOption = new JOptionPane();
        jOption.showMessageDialog(null, "Pour jouer, vous devriez cliquer sur le menu Réglage pour regler le niveau de l'IA.\n donner le nom du joueur .\n vous devriez choisir le niveau Facile ou Difficile à jouer.\n Cliquer sur le bouton Commencer pour commencer\n ", "Aide", JOptionPane.INFORMATION_MESSAGE);
    }
    @FXML
    public void about(ActionEvent event) {
        JOptionPane jOption;
        jOption = new JOptionPane();
        jOption.showMessageDialog(null,"Tic-tac-toe, also called Morpion (by analogy with the game of Morpion)\n and oxo in Belgium, is a game of reflection practiced by two players,\n turn by turn, the aim of which is to create the first alignment.", "About the game", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
     /* on essyae de remplir les champs des objet Player avec les action de l'utilisateurs
      on choisi en meme temps le model correspondant
      */

    public void selectNiveau(ActionEvent event) {
        computer.setName("Computer ");
        if (nom.getText().isEmpty()) human.setName("Player ");
        else human.setName(nom.getText());
        computer.setPawn("O");
        human.setPawn("X");
        human.setStart(true);
        computer.setStart(false);


        if (difficile.isSelected()) {
            facile.setSelected(false);
            medium.setSelected(false);
            level = "D";
            ConfigFileLoader configFileLoader = new ConfigFileLoader();
            configFileLoader.loadConfigFile("resources/config.txt");
            Config config;
            config = configFileLoader.get("D");
            h = config.hiddenLayerSize;
            lr = config.learningRate;
            lh = config.numberOfhiddenLayers;



        } else if (facile.isSelected()) {
            difficile.setSelected(false);
            medium.setSelected(false);
            level = "F";
            ConfigFileLoader configFileLoader = new ConfigFileLoader();
            configFileLoader.loadConfigFile("resources/config.txt");
            Config config;
            config = configFileLoader.get("F");
            h = config.hiddenLayerSize;
            lr = config.learningRate;
            lh = config.numberOfhiddenLayers;

        }else{
            facile.setSelected(false);
            difficile.setSelected(false);
            level = "M";
            ConfigFileLoader configFileLoader = new ConfigFileLoader();
            configFileLoader.loadConfigFile("resources/config.txt");
            Config config;
            config = configFileLoader.get("M");
            h = config.hiddenLayerSize;
            lr = config.learningRate;
            lh = config.numberOfhiddenLayers;
        }
    }

    // lorsque l'utisateur lance le jeux
    @FXML
    public void commencerJeux(ActionEvent event) {
            //verification dans le fichier config.txt
          Boolean r = checkModel(h , lr, lh );

            if(r) {	//le fichier existe il nous load le chemin du fichier et ouvre la fenetre Joueur vs IA
                Parent nouveauJeux;
                try {
                     path = chemin+"//mlp_"+h+"_"+lr+"_"+lh+".srl";
                     mlp = MultiLayerPerceptron.load(path);

                    nouveauJeux = FXMLLoader.load(getClass().getResource("humanVsComputerBoard.fxml"));
                    Scene configurationScene = new Scene(nouveauJeux);
                    //cette ligne permet de recuperer les infos
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(configurationScene);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else if(!r ) {	//le fichier n'existe pas il recupere le chemin et lance la fenetre d'apprentissage
                path = chemin+"//mlp_"+h+"_"+lr+ "_" +lh+".srl";
                Parent apprentissage;
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Progessbar.fxml"));
                    Stage stage=new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("learn");
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    // verification si le model existe ou pas
    public Boolean checkModel(int h, double lr, int lh) {
        String path = chemin+"//mlp_"+h+"_"+lr+ "_" +lh+".srl";
        File file = new File(path);

        if (file.exists()) {
            return true;
        } else return false;
    }


    @FXML
    public void cancel(ActionEvent actionEvent){
        try {
            Parent home = FXMLLoader.load(getClass().getResource("FirstWindows.fxml"));
            Scene scene1 = new Scene(home);
            Stage stage1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage1.setTitle("You want to play against : ");

            stage1.setScene(scene1);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

  /*  public void createFile(int h, double lr, int lh) throws IOException {

        try {
            path = chemin+"//mlp_"+h+"_"+lr+ "_" +lh+".srl";
            File file = new File(path);

            Boolean b;
            b = file.createNewFile();
            if (!b) {
                System.out.println("le fichier existe déja ");
            }
            if (b) System.out.println("le fichier à été créér ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

}
