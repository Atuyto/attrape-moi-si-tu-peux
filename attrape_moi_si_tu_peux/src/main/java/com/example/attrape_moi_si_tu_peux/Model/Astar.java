package com.example.attrape_moi_si_tu_peux.Model;

import java.util.ArrayList;
import java.util.List;

public class Astar {

    private Labyrinthe lelaby;

    private List<List<List<Integer>>> listeCoordonnees;

    private Case depart;

    private Case arrivee;


    public Astar(Labyrinthe labyrinthe,Case depart,Case arrivee) {
        this.lelaby = labyrinthe;
        this.listeCoordonnees = new ArrayList<List<List<Integer>>>();
        this.depart = depart ;
        this.arrivee = arrivee;
    }

    public void getHeuritisque(Case depart, Case arrivee) {

    }

    public void resolutionAstar() {

    }

    public void getListeCoordonnees() {
        int i;
        int j;
        for (i = 0; i < this.lelaby.getX(); i++) {
            List<List<Integer>> liste2 = new ArrayList<>();
            for (j = 0; j < this.lelaby.getY(); j++) {
                List<Integer> liste1 = new ArrayList<>();
                liste1.add(i);
                liste1.add(j);
                liste2.add(liste1);
            }
            listeCoordonnees.add(liste2);
        }
        System.out.println(listeCoordonnees);
    }


    public static void main(String[] args) {
        Labyrinthe lab = new Labyrinthe();
        Astar aStar;
        aStar = new Astar(lab,lab.getLesCases()[0][0],lab.getLesCases()[9][9]);
        aStar.getListeCoordonnees();
    }
}