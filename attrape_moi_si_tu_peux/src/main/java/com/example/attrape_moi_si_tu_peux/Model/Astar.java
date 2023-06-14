package com.example.attrape_moi_si_tu_peux.Model;

import java.util.*;

public class Astar {
    private Labyrinthe lab;
    private int[] depart;
    private int[] arriver;
    private int[][] as;

    public Astar(Labyrinthe labyrinthe, int[] depart, int[] arriver) {
        this.lab = labyrinthe;
        this.depart = depart;
        this.arriver = arriver;
        this.as = initPoids();



    }

    public List<int[]> astarRes(){
        int[][] poids = setWeight(arriver, as);
        return retrouverChemin(poids, depart, arriver);
    }

    public int[][] initPoids() {
        int[][] poidsLab = new int[lab.getX()][lab.getY()];
        int[] pointL = new int[2];
        for (int i = 0; i < lab.getX(); i++) {
            for (int j = 0; j < lab.getY(); j++) {
                if (lab.getLesCases()[i][j].getContenu() instanceof Rocher || (lab.getLesCases()[i][j].getAnimal() instanceof Loup && lab.getLesCases()[depart[0]][depart[1]].getAnimal() instanceof Mouton) ) {
                    poidsLab[i][j] = Integer.MAX_VALUE;
                    if(lab.getLesCases()[i][j].getAnimal() instanceof Loup){
                        pointL = new int[]{i,j};
                    }


                } else {
                    poidsLab[i][j] = -1;
                }
            }
        }
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int x = pointL[0];
        int y = pointL[1];
        for(int i = 0 ; i<4 ; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(nextX >= 0 && nextX < lab.getX() && nextY >=0 && nextY < lab.getY()) {
                poidsLab[nextX][nextY] = Integer.MAX_VALUE;
            }
        }


        poidsLab[arriver[0]][arriver[1]] = 0;
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
        chemin.remove(0);
        return chemin;
    }

    public static void main(String[] args) {
        Labyrinthe lab = new Labyrinthe();
        lab.genererGrilleAleatoire();
        lab.getLesCases()[0][5].setSortie(true);
        Astar astar = new Astar(lab,new int[]{8,2}, lab.getSortie() );
        List<int[]> chemin = astar.astarRes();



    }
}
