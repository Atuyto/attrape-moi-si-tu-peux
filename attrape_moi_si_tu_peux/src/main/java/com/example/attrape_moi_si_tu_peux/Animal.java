package com.example.attrape_moi_si_tu_peux;

import com.example.attrape_moi_si_tu_peux.view.Labyrinthe;

public class Animal {
    private int mouvementPossible;
    private int vision;
    private Labyrinthe leLabyrinthe;
    private Case laCase;


    public Animal(int mouvementPossible, Labyrinthe leLabyrinthe){
        this.mouvementPossible  = mouvementPossible;
        this.vision             = 5;
        this.leLabyrinthe       = leLabyrinthe;


    }


    public void setMouvementPossible(int mouvementPossible) {
        this.mouvementPossible = mouvementPossible;
    }

    public void setLaCase(Case laCase) {
        this.laCase = laCase;
    }

    public void seDeplacer(int nbdep, String orientation) {
        int x = this.leLabyrinthe.getPosition(this)[0];
        int y = this.leLabyrinthe.getPosition(this)[1];
        for (int i = 0; i < nbdep+1; i++) {
            if (orientation.equals("N")) {
                if (this.leLabyrinthe.getLesCases()[x - i][y].isAccessible()) {
                    this.leLabyrinthe.getLesCases()[x - i][y].setAnimal(this);
                    this.leLabyrinthe.getLesCases()[x][y].setAnimal(null);
                    this.leLabyrinthe.getLesCases()[x - i + 1][y].setAnimal(null);
                    this.setLaCase(this.leLabyrinthe.getLesCases()[x - i][y]);
                }
            }
            if (orientation.equals("S")) {
                if (this.leLabyrinthe.getLesCases()[x + i][y].isAccessible()) {
                    this.leLabyrinthe.getLesCases()[x + i][y].setAnimal(this);
                    this.leLabyrinthe.getLesCases()[x + i -1][y].setAnimal(null);
                    this.setLaCase(this.leLabyrinthe.getLesCases()[x + i][y]);
                }
            }
            if (orientation.equals("E")) {
                if (this.leLabyrinthe.getLesCases()[x][y + i].isAccessible()) {
                    this.leLabyrinthe.getLesCases()[x][y + i].setAnimal(this);
                    this.leLabyrinthe.getLesCases()[x][y].setAnimal(null);
                    this.leLabyrinthe.getLesCases()[x][y + i - 1].setAnimal(null);
                    this.setLaCase(this.leLabyrinthe.getLesCases()[x][y + i]);
                }
            }
            if (orientation.equals("O")) {
                if (this.leLabyrinthe.getLesCases()[x][y - i].isAccessible()) {
                    this.leLabyrinthe.getLesCases()[x][y - i].setAnimal(this);
                    this.leLabyrinthe.getLesCases()[x][y].setAnimal(null);
                    this.leLabyrinthe.getLesCases()[x][y -i +1].setAnimal(null);
                    this.setLaCase(this.leLabyrinthe.getLesCases()[x][y - i]);
                }
            }
        }
    }



    public int getMouvementPossible() {
        return mouvementPossible;
    }

    public Labyrinthe getLeLabyrinthe() {
        return leLabyrinthe;
    }

    public int getVision() {
        return vision;
    }

    public Case getLaCase(){ return this.laCase;}
}
