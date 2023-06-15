package com.example.attrape_moi_si_tu_peux.Model;

import java.util.ArrayList;
import java.util.List;

public class Loup extends Animal {
    private boolean enChasse;

    public Loup(Labyrinthe leLabyrinthe) {
        super(3, leLabyrinthe);
        this.enChasse = false;

    }

    public boolean reperer(int[] positionLoup, int[] positionMouton) {
        Astar astar = new Astar(getLeLabyrinthe(), positionLoup, positionMouton );

        List<int[]> chemin = astar.astarRes();
        return chemin.size() <= 5;

    }
    public void chasser(int[] nextCase, int[] oldCase){
        if(getLeLabyrinthe().getLesCases()[nextCase[0]][nextCase[1]].getAnimal() instanceof  Mouton){
            getLeLabyrinthe().getLesCases()[oldCase[0]][oldCase[1]].setAnimal(null);
            getLeLabyrinthe().getLesCases()[nextCase[0]][nextCase[1]].setAnimal(this);
            this.manger();

        }
        else {
            getLeLabyrinthe().getLesCases()[oldCase[0]][oldCase[1]].setAnimal(null);
            getLeLabyrinthe().getLesCases()[nextCase[0]][nextCase[1]].setAnimal(this);
        }
    }

    public void setEnChasse(boolean enChasse) {
        this.enChasse = enChasse;
    }

    @Override
    public void manger() {
        this.getLeLabyrinthe().getLesAnimaux().remove(0);
     }

     public boolean getEnChasse(){
        return this.enChasse;
     }
}
