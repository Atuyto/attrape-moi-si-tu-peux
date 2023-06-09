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

    public static ArrayList<ArrayList<Integer>> dj(Labyrinthe maze, ArrayList<Integer> start) {
        int rows = maze.getX();
        int cols = maze.getY();

        ArrayList<ArrayList<Integer>> distances = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(Integer.MAX_VALUE);
            }
            distances.add(row);
        }
        distances.get(start.get(0)).set(start.get(1), 0);

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        queue.add(new int[]{0, start.get(0), start.get(1)});

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentDistance = current[0];
            int x = current[1];
            int y = current[2];

            if (currentDistance > distances.get(x).get(y)) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isValidCell(maze, nx, ny)) {
                    int weight = 1;
                    int newDistance = distances.get(x).get(y) + weight;

                    if (newDistance < distances.get(nx).get(ny)) {
                        distances.get(nx).set(ny, newDistance);
                        queue.add(new int[]{newDistance, nx, ny});
                    }
                }
            }
        } return distances;
    }
    private static boolean isValidCell(Labyrinthe maze, int x, int y) {
        int rows = maze.getX();
        int cols = maze.getY();
        return x >= 0 && x < rows && y >= 0 && y < cols;
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
        ArrayList<Integer> l = new ArrayList<>();
        l.add(0);
        l.add(0);

        System.out.println(Dijkstra.dj(lab,l));

    }
}