package com.example.attrape_moi_si_tu_peux.Model;

import java.util.ArrayList;

public class Mouton extends Animal {
    private boolean enFuite;
    private int nbHerbe;
    private int nbMargurite;
    private int nbCactus;

    public Mouton(Labyrinthe leLabyrinthe) {
        super(2, leLabyrinthe);
        this.enFuite        = false;
        this.nbHerbe        = 0;
        this.nbCactus       = 0;
        this.nbMargurite    = 0;
    }

    public void manger() {
        int tourPasse = this.getLeLabyrinthe().getNb_tour();
        if (this.getLaCase().getContenu() instanceof Herbe) { // On vérifie si le contenu de la case est une herbe
            this.nbHerbe += 1;
            this.getLaCase().setContenu(null);
            this.getLaCase().setTourPasse(tourPasse);  // On commence le compteur pour savoir dans combien de temps le végétal va pouvoir repousser
            this.setMouvementPossible(2);
        }
        if
        (this.getLaCase().getContenu() instanceof Marguerite) { // On vérifie si le contenu de la case est une marguerite
            this.nbMargurite += 1;
            this.getLaCase().setContenu(null);
            this.getLaCase().setTourPasse(tourPasse); // On commence le compteur pour savoir dans combien de temps le végétal va pouvoir repousser
            this.setMouvementPossible(4);
        }
        if
        (this.getLaCase().getContenu() instanceof Cactus) { // On vérifie si le contenu de la case est un com.example.attrape_moi_si_tu_peux.Model.Cactus
            this.nbCactus += 1;
            this.getLaCase().setContenu(null);
            this.getLaCase().setTourPasse(tourPasse);  // On commence le compteur pour savoir dans combien de temps le végétal va pouvoir repousser
            this.setMouvementPossible(1);
        }

    }

    public boolean isEnFuite() {
        return enFuite;
    }

    public int getNbHerbe() {
        return nbHerbe;
    }

    public int getNbMargurite() {
        return nbMargurite;
    }

    public int getNbCactus() {
        return nbCactus;
    }
    public boolean reperer(int[] positionLoup, int[] positionMouton) {
        int distance = calculerDistance(positionLoup, positionMouton);
        return distance <= 5;
    }

    public int calculerDistance(int[] position1, int[] position2) {
        int x1 = position1[0];
        int y1 = position1[1];
        int x2 = position2[0];
        int y2 = position2[1];

        int distanceX = Math.abs(x2 - x1);
        int distanceY = Math.abs(y2 - y1);

        return distanceX + distanceY;
    }

    public void fuit(int[] nextCase, int[] oldCase){
        getLeLabyrinthe().getLesCases()[oldCase[0]][oldCase[1]].setAnimal(null);
        getLeLabyrinthe().getLesCases()[nextCase[0]][nextCase[1]].setAnimal(this);
    }

    public void setEnFuite(boolean enFuite) {
        this.enFuite = enFuite;
    }
}


