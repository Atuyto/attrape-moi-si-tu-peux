package com.example.attrape_moi_si_tu_peux.Model;

import com.example.attrape_moi_si_tu_peux.Model.Labyrinthe;

import java.util.*;

public class Astar {
    private Labyrinthe lab;
    private int[] depart;
    private int[] arrivee;
    private int[][] poids;

    public Astar(Labyrinthe labyrinthe, int[] depart) {
        this.lab = labyrinthe;
        this.depart = depart;
        this.arrivee = lab.getSortie();
        this.poids = initPoids();
    }

    public int[][] initPoids() {
        int[][] poidsLab = new int[lab.getX()][lab.getY()];

        for (int i = 0; i < lab.getX(); i++) {
            for (int j = 0; j < lab.getY(); j++) {
                if (lab.getLesCases()[i][j].getContenu() instanceof Rocher) {
                    poidsLab[i][j] = lab.getX() * lab.getY();
                } else {
                    poidsLab[i][j] = -1;
                }
            }
        }

        poidsLab[arrivee[0]][arrivee[1]] = 0;
        return poidsLab;
    }

    public int[][] setWeight(int[] sorti, int[][] poids){

        Queue<int[]> file = new ArrayDeque<>();
        file.add(sorti);

        while(!file.isEmpty()){

            int[] premier = file.poll();
            int x = premier[0];
            int y = premier[1];

            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};


            for(int i = 0 ; i<4 ; i++){
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if(nextX >= 0 && nextX < lab.getX() && nextY >=0 && nextY < lab.getY()){
                    if(poids[nextX][nextY]== -1){
                        file.add(new int[]{nextX, nextY});
                        poids[nextX][nextY] = poids[x][y] + 1;
                    }
                }
            }

        }
        return poids;
    }


    public List<int[]> retrouverChemin(int[][] poids, int[] coordDepart, int[] coordArrivee) {
        List<int[]> chemin = new ArrayList<>();
        int[] courante = coordDepart;
        chemin.add(courante);

        while (!(courante[0] == coordArrivee[0] && courante[1] == coordArrivee[1])) {
            int x = courante[0];
            int y = courante[1];
            int poidsCourant = poids[x][y];
            int[] prochaineCase = null;

            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};

            int distanceMin = Integer.MAX_VALUE;

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (nextX >= 0 && nextX < poids.length && nextY >= 0 && nextY < poids[0].length) {
                    int poidsVoisin = poids[nextX][nextY];

                    if (poidsVoisin == poidsCourant - 1) {
                        int distanceEstimee = Math.abs(nextX - coordArrivee[0]) + Math.abs(nextY - coordArrivee[1]);

                        if (distanceEstimee < distanceMin) {
                            distanceMin = distanceEstimee;
                            prochaineCase = new int[]{nextX, nextY};
                        }
                    }
                }
            }

            if (prochaineCase != null) {
                chemin.add(prochaineCase);
                courante = prochaineCase;
            } else {
                break;
            }
        }
        return chemin;
    }
}
