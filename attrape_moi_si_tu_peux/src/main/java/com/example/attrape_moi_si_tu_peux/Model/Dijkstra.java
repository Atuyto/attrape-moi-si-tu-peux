package com.example.attrape_moi_si_tu_peux.Model;

import java.util.*;
import java.util.PriorityQueue;

public class Dijkstra {

    private Labyrinthe lab;
    private List<List<List<Integer>>> listeCoordonnees;
    private Case depart;

    private Case sortie;

    public Dijkstra(Labyrinthe labyrinthe, Case depart) {
        this.lab = labyrinthe;
        this.listeCoordonnees = new ArrayList<>();
        this.depart = depart;
    }
    public int[] getSortie(){
        int x = 0;
        int y = 0;
        for(int i = 0 ; i< lab.getX() ; i++){
            for (int j = 0; j<lab.getY(); j++){
                if(this.lab.getLesCases()[i][j].getSortie()){
                    x= i;
                    y =j;
                    break;
                }
            }
        }
        return new int[]{x,y};
    }

    public List<List<Integer>> voisins(List<List<List<Integer>>> liste) {
        List<List<Integer>> listVoisinsExplorer = new ArrayList<>();
        List<List<Integer>> listVoisins = new ArrayList<>();
        for (int i = 0; i < lab.getX(); i++) {
            for (int j = 0; j < lab.getY(); j++) {
                for (int k = 0; k < listVoisins.size(); k++) {
                    if (!(listVoisinsExplorer.contains(listVoisins.get(k)))) {
                        if (lab.getLesCases()[liste.get(i).get(j).get(0) - 1][liste.get(i).get(j).get(1)].isAccessible() && i < lab.getX() && j != 0) {
                            List<Integer> l = new ArrayList<>();
                            l.add(liste.get(i).get(j).get(0) - 1);
                            l.add(liste.get(i).get(j).get(1));
                            listVoisins.add(l);
                            listVoisinsExplorer.add(l);
                        }
                        if (lab.getLesCases()[liste.get(i).get(j).get(0) + 1][liste.get(i).get(j).get(1)].isAccessible() && i < lab.getX() - 1 && j < lab.getY() - 1) {
                            List<Integer> l = new ArrayList<>();
                            l.add(liste.get(i).get(j).get(0) + 1);
                            l.add(liste.get(i).get(j).get(1));
                            listVoisins.add(l);
                            listVoisinsExplorer.add(l);
                        }
                        if (lab.getLesCases()[liste.get(i).get(j).get(0)][liste.get(i).get(j).get(1) - 1].isAccessible() && j != 0 && i < lab.getX() - 1) {
                            List<Integer> l = new ArrayList<>();
                            l.add(liste.get(i).get(j).get(0));
                            l.add(liste.get(i).get(j).get(1) - 1);
                            listVoisins.add(l);
                            listVoisinsExplorer.add(l);
                        }
                        if (lab.getLesCases()[liste.get(i).get(j).get(0)][liste.get(i).get(j).get(1) + 1].isAccessible() && j < lab.getY() - 1 && i != 0) {
                            List<Integer> l = new ArrayList<>();
                            l.add(liste.get(i).get(j).get(0));
                            l.add(liste.get(i).get(j).get(1) + 1);
                            listVoisins.add(l);
                            listVoisinsExplorer.add(l);
                        }
                    }
                }
            }
        }
        System.out.println(listVoisinsExplorer);
        System.out.println(listVoisins);
        return listVoisins;
    }


    public int[][] setWeight(){
        int[][] poidLab;
        poidLab = new int[lab.getX()][lab.getY()];
        for(int i = 0; i < lab.getX(); i++){
            for(int j = 0;j<lab.getY() ; j++){
                if(lab.getLesCases()[i][j].getContenu() instanceof Rocher){
                    poidLab[i][j] = lab.getX()* lab.getY();
                }
                else {
                    poidLab[i][j] = 0;
                }
            }
        }
        int[] sorti = getSortie();
        poidLab[sorti[0]][sorti[1]] = 0;
        

        return poidLab;
    }

    public void pel(List<int[]> adj, int[] sorti){
        PriorityQueue<int[]> file = new PriorityQueue<>();
        PriorityQueue<int[]> marques = new PriorityQueue<>();
        file.add(sorti);
        marques.add(sorti);
        while (!file.isEmpty()){
            int[] tab = file.poll();
            for(int i = 0; i < adj.size(); i++){
                if(i )
            }
        }
    }




    private boolean isValidCell(int x, int y) {
        int rows = lab.getX();
        int cols = lab.getY();
        return x >= 0 && x < rows && y >= 0 && y < cols && lab.getLesCases()[x][y].isAccessible();
    }

    public void getListeCoordonnees() {
        int i;
        int j;
        for (i = 0; i < lab.getX(); i++) {
            List<List<Integer>> liste2 = new ArrayList<>();
            for (j = 0; j < lab.getY(); j++) {
                List<Integer> liste1 = new ArrayList<>();
                liste1.add(i);
                liste1.add(j);
                liste2.add(liste1);
            }
            listeCoordonnees.add(liste2);
        }
        //afficher();
    }

    public void afficher() {
        int i;
        int j;
        for (i = 0; i < listeCoordonnees.size(); i++) {
            for (j = 0; j < listeCoordonnees.get(0).size(); j++) {
                System.out.print(listeCoordonnees.get(i).get(j) + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Labyrinthe lab = new Labyrinthe();
        lab.genererGrilleAleatoire();
        lab.getLesCases()[5][0].setSortie(true);
        Dijkstra dijkstra = new Dijkstra(lab, lab.getLesCases()[5][0]);
        int[][] dj = dijkstra.setWeight();
        for(int i = 0; i < lab.getX(); i++) {
            for (int j = 0; j < lab.getY(); j++) {
                System.out.print(dj[i][j] +"\t");
            }
            System.out.println();
        }
    }
}