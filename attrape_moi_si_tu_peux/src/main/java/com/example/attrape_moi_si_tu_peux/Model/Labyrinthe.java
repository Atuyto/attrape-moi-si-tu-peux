package com.example.attrape_moi_si_tu_peux.Model;

import java.io.*;
import java.util.*;

public class Labyrinthe {
    private int x;
    private int y;
    private int nb_tour;
    private Case[][] lesCases;
    private ArrayList<Animal> lesAnimaux;


    public Labyrinthe(){
        this.x              = 10;
        this.y              = 10;
        this.nb_tour        = 0;
        this.lesCases       =  new  Case[this.y][this.x];
        this.lesAnimaux     = new ArrayList<>();
        this.genererGrille();

    }
    public Labyrinthe(int x, int y){
        this.x              = x;
        this.y              = y;
        this.nb_tour        = 0;
        this.lesCases       =  new  Case[this.y][this.x];
        this.lesAnimaux     = new ArrayList<>();
        this.genererGrille();
    }

    public void genererGrille() {
        for(int i = 0 ; i< this.y ; i++) {
            for(int j = 0 ; j<this.x ; j++) {
                if(i == 0 || j == 0 || i == this.y -1 || j == this.x -1) {
                    this.lesCases[i][j] = new Case(this, new Rocher());
                }
                else {
                    this.lesCases[i][j] = new Case(this, new Herbe());
                }
            }
        }
    }
    public void genererGrilleAleatoire() {
        Random random = new Random();
        for(int i = 1 ; i< this.y -1; i++) {
            for(int j = 1 ; j<this.x -1; j++) {
                int randomIndex = random.nextInt(6);
                int nbVoisinRoche = 0;
                if (this.lesCases[i+1][j].getContenu() instanceof Rocher){ nbVoisinRoche += 1;}
                if(this.lesCases[i-1][j].getContenu() instanceof Rocher) { nbVoisinRoche += 1;}
                if(this.lesCases[i+1][j-1].getContenu() instanceof Rocher) { nbVoisinRoche += 1;}
                if(this.lesCases[i-1][j+1].getContenu() instanceof Rocher) { nbVoisinRoche +=1; }
                if (randomIndex == 1 && nbVoisinRoche <3){
                    this.lesCases[i][j] = new Case(this, new Rocher());
                }
                else this.lesCases[i][j].regeneration();

            }
        }
    }


    public void ajouterAnimal(Animal animal, int x, int y){
        if (!(this.getLesCases()[y][x].getContenu() instanceof Rocher)) {
            this.getLesCases()[y][x].setAnimal(animal);
            animal.setLaCase(getLesCases()[x][y]);
        }
    }


    public String toString() {
        StringBuilder string = new StringBuilder();
        for(int i = 0 ; i < this. getY(); i++) {
            for (int j = 0; j < this.getX(); j++) {
                if (this.lesCases[i][j].getAnimal() instanceof Mouton) {
                    string.append("m");
                } else if (this.lesCases[j][i].getAnimal() instanceof Loup) {
                    string.append("l");
                } else if (this.lesCases[j][i].getSortie()){
                    string.append("s");
                } else if (this.lesCases[j][i].getContenu() instanceof Herbe) {
                    string.append("h");
                } else if (this.lesCases[j][i].getContenu() instanceof Rocher) {
                    string.append("x");
                } else if (this.lesCases[j][i].getContenu() instanceof Cactus) {
                    string.append("c");
                } else if (this.lesCases[j][i].getContenu() instanceof Marguerite) {
                    string.append("f");
                }
            }
            string.append("\n");
        }
        return String.valueOf(string);
    }

    public void sauvegarderLabyrinthe() {
        try {
            FileWriter fw = new FileWriter("labyrinthe.txt");
            System.out.println(toString());
            fw.write(this.toString());
            fw.close();}
        catch (IOException ex)
        {ex.printStackTrace();}
    }





    public char[][] openLab(String path) {
        char[][] matrice = new char[0][];
        BufferedReader br;
        try (BufferedReader br1 = new BufferedReader(new FileReader(path))) {
            String ligne;
            int nombreLignes = 0;
            int nombreColonnes = 0;

            // Compter le nombre de lignes et le nombre maximum de colonnes dans le fichier
            while ((ligne = br1.readLine()) != null) {
                nombreLignes++;
                nombreColonnes = Math.max(nombreColonnes, ligne.length());
            }

            // Réinitialiser le lecteur de fichier
            br1.close();
            br = new BufferedReader(new FileReader(path));

            // Créer une matrice avec les dimensions appropriées
            matrice = new char[nombreLignes][nombreColonnes];
            this.x = nombreColonnes;
            this.y = nombreLignes;

            int i = 0;
            while ((ligne = br.readLine()) != null) {
                char[] caracteres = ligne.toCharArray();
                for(int j = 0; j < caracteres.length; j++) {
                    matrice[i][j] = caracteres[j];
                }

                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrice;
    }

    public void genererGrille(char[][] c){
        for(int i = 0; i<this.getX(); i++){
            for(int j = 0; j<this.getY(); j++){
                if (c[i][j] == 'x') {
                    this.lesCases[j][i] = new Case(this, new Rocher());
                } else if (c[j][i]== 'm') {
                    this.lesCases[j][i] = new Case(this, new Herbe());
                    Mouton mouton = new Mouton(this);
                    this.ajouterAnimal(mouton, j, i);
                    this.setLesAnimaux(mouton);
                } else if (c[j][i] == 'l') {
                    this.lesCases[j][i] = new Case(this, new Herbe());
                    Loup loup = new Loup(this);
                    this.ajouterAnimal(loup, j, i);
                    this.setLesAnimaux(loup);
                } else if (c[j][i]== 'h') {
                    this.lesCases[j][i] = new Case(this, new Herbe());
                } else if (c[j][i]== 'f') {
                    this.lesCases[j][i] = new Case(this, new Marguerite());
                } else if (c[j][i]== 'c') {
                    this.lesCases[j][i] = new Case(this, new Cactus());
                } else if (c[i][j] == 's') {
                    this.lesCases[j][i] = new Case(this, new Herbe());
                    this.lesCases[j][i].setSortie(true);
                }
            }
        }

    }

    public void genererGrilleSauve(String s) {

        int i = 0;
        int j = 0;
        for (int n = 0; n < s.length(); n++) {
            if (s.charAt(n) == 'x') {
                this.lesCases[j][i] = new Case(this, new Rocher());
            } else if (s.charAt(n) == 'm') {
                this.lesCases[j][i] = new Case(this, new Herbe());
                Mouton mouton = new Mouton(this);
                this.ajouterAnimal(mouton, j, i);
                this.setLesAnimaux(mouton);
            } else if (s.charAt(n) == 'l') {
                this.lesCases[j][i] = new Case(this, new Herbe());
                Loup loup = new Loup(this);
                this.ajouterAnimal(loup, j, i);
                this.setLesAnimaux(loup);
            } else if (s.charAt(n) == 'h') {
                this.lesCases[j][i] = new Case(this, new Herbe());
            } else if (s.charAt(n) == 'f') {
                this.lesCases[j][i] = new Case(this, new Marguerite());
            } else if (s.charAt(n) == 'c') {
                this.lesCases[j][i] = new Case(this, new Cactus());
            } else if (s.charAt(n) == 's') {
                this.lesCases[j][i] = new Case(this, new Herbe());
            } else if (s.charAt(n) == '\n') {
                i = -1; j++;
            }
            i++;
        }
    }

    public int getNb_tour() {
        return nb_tour;
    }
    public void setNb_tour( int i ){
        this.nb_tour += i;
    }

    public Case[][] getLesCases() {return this.lesCases;}

    public int[] getPosition(Animal animal) {
        int[] p = new int[2];
        for(int i = 0 ; i< this.x ; i++) {
            for(int j = 0 ; j<this.y ; j++) {
                if (this.lesCases[i][j].getAnimal() == animal ) {
                    p[0] = i ; p[1] = j;
                    break;
                }
            }
        }
        return p;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public ArrayList<Animal> getLesAnimaux() {
        return this.lesAnimaux;
    }

    public boolean setLesAnimaux(Animal animal) {
        if (this.lesAnimaux.size() == 2) return false;
        else {
            this.lesAnimaux.add(animal);
        };
        return true;
    }

}
