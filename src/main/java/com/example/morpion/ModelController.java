package com.example.morpion;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static java.nio.file.Files.delete;


public class ModelController implements Initializable{
    @FXML
    public ListView list;

    @FXML
    private TextField mod;

    @FXML
    private Button btnQuit;

    public ModelController() {
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //pour les modeles deja appris dans la ListeView
        File repertoire = new File("C:\\Users\\pc\\IdeaProjects\\sifaoufatai\\src\\main\\resources\\com\\example\\morpion\\models");
        String liste[] = repertoire.list();
        if (liste != null) {
            for (int i = 0; i < liste.length; i++) {
                list.getItems().add(liste[i]);
            }
        }
    }
    //supprimer une liste des fichiers sélectionné
    private void suprression(ObservableList<String> listefilename) {

        String directoryPath = "C:\\Users\\pc\\IdeaProjects\\sifaoufatai\\src\\main\\resources\\com\\example\\morpion\\models";

        File directory = new File(directoryPath);

        // VÃ©rifier si le rÃ©pertoire existe
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Le rÃ©pertoire spÃ©cifiÃ© n'existe pas.");
        }

        // Supprimer les fichiers
        for (String fileName : listefilename) {
            File file = new File(directory, fileName);
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    System.out.println("Le fichier " + fileName + " a Ã©tÃ© supprimÃ©.");
                } else {
                    System.out.println("Impossible de supprimer le fichier " + fileName);
                }
            } else {
                System.out.println("Le fichier " + fileName + " n'existe pas dans le rÃ©pertoire spÃ©cifiÃ©.");
            }
        }
    }
    public void changed(ObservableValue<? extends String> Observable , String oldVal, String newVal){
        ObservableList<String> itemsToRemove = list.getSelectionModel().getSelectedItems();


    }
    /*Action pour supprimer les models
     il fait appelle a la fonction suppression qui supprime des liste de fichiers
    */
    @FXML
    public void delete(ActionEvent event) {
        String mod ="";
        ObservableList<String> lISTED;
        lISTED=list.getSelectionModel().getSelectedItems();
        suprression(lISTED);

        list.getItems().removeAll(lISTED);


        for(String s:lISTED) System.out.println(s);
    }

    @FXML
    public void exit(ActionEvent actionEvent){
         Stage stage = (Stage) btnQuit.getScene().getWindow();
         stage.close();
    }



}