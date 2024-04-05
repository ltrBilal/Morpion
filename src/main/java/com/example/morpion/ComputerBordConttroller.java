package com.example.morpion;

        import javafx.animation.RotateTransition;
        import javafx.animation.ScaleTransition;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.stage.Stage;
        import javafx.util.Duration;

        import javax.swing.*;
        import java.awt.*;
        import java.io.IOException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.ResourceBundle;

public class ComputerBordConttroller implements Initializable {
    public Button bouton1,bouton2,bouton3,bouton4,bouton5,bouton6,bouton7, bouton8,bouton9;
    @FXML
    private Label nom1, nom2, score1, score2, tourJoueur, pion1, pion2, labelWiner;
    @FXML
    private Button btnQuitter;
    static String Pawn ="";
    public int cpt1 = 0, cpt2 = 0;
    Boolean fin = false ;
    static ia.MultiLayerPerceptron net ;
    public  static double[] inputs = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    public  static double outputs[];

    private List<Label> infojour1 = new ArrayList<>();
    private List<Label> infoIa = new ArrayList<>();
    private List<Button> listButtons = new ArrayList<>();
    static Player human =humanVsIaController.human;
    static Player ia =humanVsIaController.computer;
    static  String path =humanVsIaController.path;




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
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(configurationScene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // On essaie de faire jouer la machiene  simultanement avec  l'humain
    public void choisirJoueur() {
        boolean find= false ;

        if(human.isStart()) {
            //remplissage du label qui montre le jouer courant
            tourJoueur.setText(human.getName().concat(" it's your turn "));

            if (fin==false ) {
                Pawn = ia.getPawn();
                //je recupere la case avec la probabilité plus grand    outputs = net.forwardPropagation(inputs);
                System.out.println(Arrays.toString(outputs));
                //recupreation du tableau des probaabilité trié en ordre decroissant
                double tab[] = getOutputs(outputs);
                int i = 0;
                /*
                  ici la machine joue suite au jeu de lhumain
                 je verifie si le 1er case de mon tableau est vide dans ce cas je voue si non jexploire les autres case de mon tableau et ainsi de suite
                 */
                while (!find && i < tab.length) {
                    if (tab[i] == 0 && bouton1.getText() == "") {
                        bouton1.setText(Pawn);
                        inputs[0]=-1;
                        find = true;
                    } else if (tab[i] == 1 && bouton2.getText() == "") {
                        bouton2.setText(Pawn);
                        inputs[1]=-1;
                        find = true;
                    } else if (tab[i] == 2 && bouton3.getText() == "") {
                        bouton3.setText(Pawn);
                        find = true;
                        inputs[2]=-1;
                    } else if (tab[i] == 3 && bouton4.getText() == "") {
                        bouton4.setText(Pawn);
                        inputs[3]=-1;
                        find = true;
                    } else if (tab[i] == 4 && bouton5.getText() == "") {
                        bouton5.setText(Pawn);
                        inputs[4]=-1;
                        find = true;
                    } else if (tab[i] == 5 && bouton6.getText() == "") {
                        bouton6.setText(Pawn);
                        inputs[5]=-1;
                        find = true;
                    } else if (tab[i] == 6 && bouton7.getText() == "") {
                        bouton7.setText(Pawn);
                        inputs[6]=-1;
                        find = true;
                    } else if (tab[i] == 7 && bouton8.getText() == "") {
                        bouton8.setText(Pawn);
                        inputs[7]=-1;
                        find = true;
                    } else if (tab[i] == 8 && bouton9.getText() == "") {
                        bouton9.setText(Pawn);
                        inputs[8]=-1;
                        find = true;
                    }
                    i++;

                }
                //apres le jeu de la machine on verifie sil ya un gagnat ou pas
                checkWin();

            }
        }

    }
    // la description des scenario gagnant avec les bouton gagant
    public void gameScore(Button b1, Button b2, Button b3) {
        if (b1.getText() == b2.getText() && b2.getText() == b3.getText() && !b3.getText().isEmpty()) {

            fin = true;
            tourJoueur.setText("Game Over");
            String val = b1.getText();
            int score = 0;
            List<Label> p = new ArrayList<>();

            if (val.equalsIgnoreCase("X")) {
                cpt1++;
                score = cpt1;
                p = infojour1;
            } else if(val.equalsIgnoreCase("O"))  {
                cpt2++;
                score = cpt2;
                p = infoIa;

            }

            if (ia.getPawn() == val) {
                ia.setScore(score);
                p.get(0).setText(ia.getName());
                p.get(1).setText(ia.getScore());


                labelWiner.setText("You lost");

            } else if (human.getPawn() == val) {
                human.setScore(score);
                p.get(0).setText(human.getName());
                System.out.println(p.get(0)) ;System.out.println(p.get(1));

                p.get(1).setText(human.getScore());


                labelWiner.setText("You Won");
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
    // on verifie tout les possiblité de gain possible avec les 8 possibilité
    public void checkWin(){
        gameScore(bouton1,bouton2,bouton3);
        gameScore(bouton4,bouton5,bouton6);
        gameScore(bouton7,bouton8,bouton9);
        gameScore(bouton1,bouton4,bouton7);
        gameScore(bouton2,bouton5,bouton8);
        gameScore(bouton3,bouton6,bouton9);
        gameScore(bouton1,bouton5,bouton9);
        gameScore(bouton3,bouton5,bouton7);


    }
    @FXML

    public void reprendre(ActionEvent event) {
        fin= false ;
        labelWiner.setText("");
        for(Button b :listButtons) {b.setText("");b.setStyle("");}
        if(human.start){
            tourJoueur.setText(human.getName().concat(" it's your turn "));
        }
        else {
            tourJoueur.setText(" IA's turn ");}
    }
    // ici je joue effectivement avec l'humain
    @FXML
    public void jouer(ActionEvent event){
        //recuper des info du bouton cliqué
        Button btn;
        btn = (Button) event.getSource();

        if(btn.getText() != "O" && btn.getText() != "X" && !fin) {
            int i=-1 ;
            // si le bouton est libre je pose mon pion là
            btn.setText(human.getPawn());
            for(Button B : listButtons){if (B.equals(btn) ) i =listButtons.indexOf(B); }
            checkWin();
            //mise a jour des case de mon tableau de bord du jeu le 1 signifie que kla ces est pris par lhumain
            inputs[i]=1;
            choisirJoueur();

            if(btn.getText() == "O") {
                btn.setStyle("-fx-text-fill: green;-fx-font-size: 34px;");
            }else if(btn.getText() == "X") {
                btn.setStyle("-fx-text-fill: red;-fx-font-size: 34px;");
            }
        }}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        infojour1.add(nom1);
        infojour1.add(score1);

        infojour1.add(pion1);

        // les labels de l'ia
        infoIa.add(nom2);
        infoIa.add(score2);
        infoIa.add(pion2);

        if(human.isStart()) {tourJoueur.setText(human.getName().concat(" it's your turn"));}
        if(human.getName().isEmpty()) human.setName("Player");
        nom1.setText(human.getName());

        score1.setText(human.getScore());
        nom2.setText(ia.getName());

        score2.setText(String.valueOf(ia.getScore()));
        listButtons.add(bouton1);
        listButtons.add(bouton2);
        listButtons.add(bouton3);
        listButtons.add(bouton4);
        listButtons.add(bouton5);  ;
        listButtons.add(bouton6);
        listButtons.add(bouton7);
        listButtons.add(bouton8);
        listButtons.add(bouton9);

        path=humanVsIaController.path;
        net =humanVsIaController.mlp;
        inputs=new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        outputs=net.forwardPropagation(inputs);
        System.out.println(path);   System.out.println(Arrays.toString(outputs));



    }

    //je recupere lindice du plus grand nombre de mon tableau
    int getindiceMaxvalueofBord(double[]outputs){
        double max= outputs[0];
        int indice=0;
        for(int i = 0; i< outputs.length; i++){
            if( max< outputs[i]){
                max =outputs[i];
                indice =i;
            }

        }
        return indice ;
    }
    //ici je recupere un tableau trie par odre croissant des intice de outpout;
    public double [] getOutputs(double [] outputs){
        double [] Tab = new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};

        for(int i =0; i<outputs.length;i++ ){
            int j = getindiceMaxvalueofBord(outputs);
            Tab[i]=j;
            outputs[j]=0;
        }
        return Tab;

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
        String message = "Two players compete. They must each in turn fill a box of the grid with the \n symbol assigned to them: O or X. The winner is the one who manages to align \n three identical symbols, horizontally, vertically or diagonally.\n ";    jOption.showMessageDialog(null,message, "Help", JOptionPane.INFORMATION_MESSAGE);
    }


}