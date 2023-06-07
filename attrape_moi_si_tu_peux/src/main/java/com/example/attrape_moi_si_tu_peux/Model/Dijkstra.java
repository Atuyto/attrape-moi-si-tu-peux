package com.example.attrape_moi_si_tu_peux.Model;

import java.util.*;
import java.util.PriorityQueue;


public class Dijkstra {

    private Labyrinthe lab;

    private List<List<List<Integer>>> listeCoordonnees;

    private Case depart;

    public Dijkstra(Labyrinthe labyrinthe, Case depart) {
        this.lab = labyrinthe;
        this.listeCoordonnees = new ArrayList<List<List<Integer>>>();
        this.depart = depart;
    }

    public List<List<Integer>> voisins(List<Integer> liste) {
        List<List<Integer>> list = new ArrayList<>();
        if(lab.getLesCases()[liste.get(0)-1][liste.get(1)].isAccessible()) {
            List<Integer> l = new ArrayList<>();
            l.add(liste.get(0) - 1);
            l.add(liste.get(1));
            list.add(l);
        }
        if(lab.getLesCases()[liste.get(0)+1][liste.get(1)].isAccessible()){
            List<Integer> l = new ArrayList<>();
            l.add(liste.get(0)+1);
            l.add(liste.get(1));
            list.add(l);
        }
        if(lab.getLesCases()[liste.get(0)][liste.get(1)-1].isAccessible()){
            List<Integer> l = new ArrayList<>();
            l.add(liste.get(0));
            l.add(liste.get(1)-1);
            list.add(l);
        }
        if(lab.getLesCases()[liste.get(0)][liste.get(1)+1].isAccessible()) {
            List<Integer> l = new ArrayList<>();
            l.add(liste.get(0));
            l.add(liste.get(1) + 1);
            list.add(l);
        }
        return list;
    }



    public void getListeCoordonnees () {
        int i;
        int j;
        for (i = 0; i < this.lab.getX(); i++) {
            List<List<Integer>> liste2 = new ArrayList<>();
            for (j = 0; j < this.lab.getY(); j++) {
                List<Integer> liste1 = new ArrayList<>();
                liste1.add(i);
                liste1.add(j);
                liste2.add(liste1);
            }
            listeCoordonnees.add(liste2);
        }
        afficher();
    }
    public void afficher(){
        int i;
        int j;
        for (i = 0; i < listeCoordonnees.size(); i++) {
            for (j = 0; j < listeCoordonnees.get(0).size(); j++) {
                System.out.print(listeCoordonnees.get(i).get(j) +"\t");
            }
            System.out.println();
        }
    }



    public static void main(String[] args) {
        Labyrinthe lab = new Labyrinthe();
        Dijkstra dijkstra;
        dijkstra = new Dijkstra(lab,lab.getLesCases()[0][0]);
        dijkstra.getListeCoordonnees();

    }
}