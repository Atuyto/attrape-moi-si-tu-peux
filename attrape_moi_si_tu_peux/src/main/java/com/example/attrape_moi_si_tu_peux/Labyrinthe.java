package com.example.attrape_moi_si_tu_peux;

import com.example.attrape_moi_si_tu_peux.Animal;
import com.example.attrape_moi_si_tu_peux.Case;
import com.example.attrape_moi_si_tu_peux.Rocher;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Labyrinthe {
    private final int x;
    private final int y;
    private int nb_tour;
    private Case[][] lesCases;
    private Animal[] lesAnimaux;


    public Labyrinthe(){
        this.x              = 10;
        this.y              = 10;
        this.nb_tour        = 0;
        this.lesCases       =  new  Case[this.x][this.y];
        this.lesAnimaux     =  new Animal[2];
        this.genererGrille();

    }
    public Labyrinthe(int x, int y){
        this.x              = x;
        this.y              = y;
        this.nb_tour        = 0;
        this.lesCases       =  new  Case[this.x][this.y];
        this.lesAnimaux     =  new Animal[2];
        this.genererGrille();
    }


    public void genererGrille()
    {

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


    public void ajouterAnimal(Animal animal, int x, int y){
        if (!(this.getLesCases()[x][x].getContenu() instanceof Rocher)) {
            this.getLesCases()[x][y].setAnimal(animal);
        }
    }

    public void afficher() {
        for(int i = 0 ; i< this.x ; i++) {
            for(int j = 0 ; j<this.y ; j++) {
                System.out.print(lesCases[i][j].toString() + "\t");
                }
            System.out.print("\n");
            }
        }

    public String toString() {
        String string = "";
        for(int i = 0 ; i< this.x ; i++) {
            for (int j = 0; j < this.y; j++) {
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
                }
            }
            string += "\n";
        }
        return string;
    }

    public void sauvegarder_labyrinthe(String s, String path) {
        Files.write(Paths.get(path), s.getBytes());
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

}
