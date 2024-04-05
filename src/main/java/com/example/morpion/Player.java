package com.example.morpion;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Player {
    public String name;
    public String username;
    public int score;

    public String pawn;
    public boolean start = false;
    public ArrayList<Player> listehistorique;

    public ArrayList<Player> getListehistorique() {
        return listehistorique;
    }

    public void setListehistorique(ArrayList<Player> listehistorique) {
        this.listehistorique = listehistorique;
    }




    public LocalDate date;
    public String getDate;

    public Player() {
        this.listehistorique=new ArrayList<Player>();
    }

    public Player(String name, int score, boolean start) {

        this.name = name;
        this.score = score;
        this.start = start;
        this.listehistorique=new ArrayList<Player>();
    }


    public String getName() {
        return name;
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPawn() {
        return pawn;
    }

    public void setPawn(String pawn) {
        this.pawn = pawn;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDate() {
        return date;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void writehistorique(Player player) {
        Player j;

        try {
            FileInputStream fileIn = new FileInputStream("resources/Historique/" + player.getName());
            ObjectInputStream ois = new ObjectInputStream(fileIn);

            j = (Player) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            // Le fichier n'existe pas ou il est vide
        }

        // On écrit ensuite l'objet dans le fichier
        try {
            FileOutputStream fos = new FileOutputStream("resources/Historique/" + player.getName());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(player);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Player> readhistorique(Player player) {
        List<Player> joueurs = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("resources/Historique/" + player.getName());
            ObjectInputStream in = new ObjectInputStream(fileIn);


            while (true) {
                try {
                    Object obj = in.readObject();
                    Player joueur = (Player) obj;
                    joueurs.add(joueur);
                } catch (EOFException e) {
                    break;
                }
            }

            for (Player joueur : joueurs) {
                System.out.println(joueurs.size());
                System.out.println("Nom : " + joueur.getName());
                System.out.println("Score : " + joueur.getScore());
                System.out.println(joueur.date);


            }
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (ArrayList<Player>) joueurs;
    }

    public Player redahistorique(Player p) {
        Player joueur = new Player();
        try {
            FileInputStream fileIn = new FileInputStream("resources/Historique/" + this.getName());
            ObjectInputStream in = new ObjectInputStream(fileIn);
            joueur = (Player) in.readObject();
            System.out.println(joueur.toString());
            in.close();
            fileIn.close();
            System.out.println("echec");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return joueur;
    }

    public void Creathistorique() {

        File fichier = new File("resources/Historique/" + this.getName());

        if (!fichier.exists()) {
            try {
                fichier.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Le fichier existe déjà !");
        }
    }

    public void addliste(ArrayList<Player> l) {

        for (Player p : l) {
            this.listehistorique.add(p);
        }
    }
}