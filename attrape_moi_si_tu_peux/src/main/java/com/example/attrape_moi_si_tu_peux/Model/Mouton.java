package com.example.attrape_moi_si_tu_peux.Model;

import java.util.*;

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
        Astar astar = new Astar(getLeLabyrinthe(), positionMouton);
        int[][] dj = astar.initPoids();
        int[][] poids = astar.setWeight(positionLoup, dj);
        List<int[]> chemin = astar.retrouverChemin(poids, positionMouton, positionLoup);

        System.out.println(chemin.size());
        return chemin.size() <= 5;

    }


    public void fuit(int[] nextCase, int[] oldCase){
        getLeLabyrinthe().getLesCases()[oldCase[0]][oldCase[1]].setAnimal(null);
        getLeLabyrinthe().getLesCases()[nextCase[0]][nextCase[1]].setAnimal(this);
    }

    public void setEnFuite(boolean enFuite) {
        this.enFuite = enFuite;
    }
}


