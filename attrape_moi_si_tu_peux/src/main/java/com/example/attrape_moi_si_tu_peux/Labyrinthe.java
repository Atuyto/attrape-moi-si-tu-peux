package com.example.attrape_moi_si_tu_peux;

import com.example.attrape_moi_si_tu_peux.Animal;
import com.example.attrape_moi_si_tu_peux.Case;
import com.example.attrape_moi_si_tu_peux.Rocher;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        this.lesCases       =  new  Case[this.x][this.y];
        this.lesAnimaux     = new ArrayList<>();
        this.genererGrille();

    }
    public Labyrinthe(int x, int y){
        this.x              = x;
        this.y              = y;
        this.nb_tour        = 0;
        this.lesCases       =  new  Case[this.x][this.y];
        this.lesAnimaux     = new ArrayList<>();
        this.genererGrille();
    }

    public void genererGrille() {
        for(int i = 0 ; i< this.x ; i++) {
            for(int j = 0 ; j<this.y ; j++) {
                if(i == 0 || j == 0 || i == this.x -1 || j == this.y -1) {
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
        for(int i = 1 ; i< this.x -1; i++) {
            for(int j = 1 ; j<this.y -1; j++) {
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
        if (!(this.getLesCases()[x][y].getContenu() instanceof Rocher)) {
            this.getLesCases()[x][y].setAnimal(animal);
            animal.setLaCase(getLesCases()[x][y]);
        }
    }


    public String toString() {
        String string = "";
        for(int i = 0 ; i < this.y ; i++) {
            for (int j = 0; j < this.x; j++) {
                if (lesCases[i][j].getAnimal() instanceof Mouton) {
                    string += "m";
                } else if (lesCases[i][j].getAnimal() instanceof Loup) {
                    string += "l";
                } else if ((lesCases[i][j].getContenu() instanceof Herbe) && ((i==0) || (i==this.x-1) || (j == 0) || (j == this.y))) {
                    string += "s";
                } else if (lesCases[i][j].getContenu() instanceof Herbe) {
                    string += "h";
                } else if (lesCases[i][j].getContenu() instanceof Rocher) {
                    string += "x";
                } else if (lesCases[i][j].getContenu() instanceof Cactus) {
                    string += "c";
                } else if (lesCases[i][j].getContenu() instanceof Marguerite) {
                    string += "m";
                } else if (lesCases[i][j].getContenu() == null) {
                    string += "v";
                }
            }
            string += "\n";
        }
        return string;
    }

    public void sauvegarderLabyrinthe() {
        try {
            FileWriter fw = new FileWriter("labyrinthe.txt");

            fw.write(this.toString());
            fw.close();}
        catch (IOException ex)
        {ex.printStackTrace();}
    }

    public String openLab() {
        String string = "";
        this.x = 0;
        int y = 0;
        try {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream("labyrinthe.txt");
            Scanner scanner = new Scanner(file);

            //renvoie true s'il y a une autre ligne à lire
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine()+"\n";
                string += line;
                y++;

                if (this.x == 0) {
                    this.x = line.length()-1;

                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.y = y;
        this.lesCases = new Case[this.y][this.x];
        return string;
    }
    public void genererGrilleSauve(String s) {

        int i = 0;
        int j = 0;
        for (int n = 0; n < s.length(); n++) {

            if (s.charAt(n) == 'x') {
                this.lesCases[j][i] = new Case(this, new Rocher());
            } else if (s.charAt(n) == 'M') {
                this.lesCases[j][i] = new Case(this, new Herbe());
                this.lesCases[j][i].setAnimal(new Mouton(this));
            } else if (s.charAt(n) == 'L') {
                this.lesCases[j][i] = new Case(this, new Herbe());
                this.lesCases[j][i].setAnimal(new Loup(this));
            } else if (s.charAt(n) == 'h') {
                this.lesCases[j][i] = new Case(this, new Herbe());
            } else if (s.charAt(n) == 'm') {
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
        this.nb_tour = i;
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
