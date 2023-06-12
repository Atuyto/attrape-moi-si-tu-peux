package com.example.attrape_moi_si_tu_peux.Model;

import java.util.*;

public class Dijkstra {

    private Labyrinthe lab;
    public Dijkstra(Labyrinthe labyrinthe) {
        this.lab = labyrinthe;
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

    public int[][] initPoids() {
        int[][] poidsLab = new int[lab.getX()][lab.getY()];
        int maxPoids = lab.getX() * lab.getY(); // Maximum poids pour les cases inaccessibles

        for (int i = 0; i < lab.getX(); i++) {
            for (int j = 0; j < lab.getY(); j++) {
                if (!lab.getLesCases()[i][j].isAccessible()) {
                    poidsLab[i][j] = maxPoids;
                } else {
                    poidsLab[i][j] = -1;
                }
            }
        }

        int[] sortie = getSortie();
        poidsLab[sortie[0]][sortie[1]] = 0;

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

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (nextX >= 0 && nextX < lab.getX() && nextY >= 0 && nextY < lab.getY()) {
                    int poidsVoisin = poids[nextX][nextY];

                    if (lab.getLesCases()[nextX][nextY].isAccessible() && poidsVoisin == poidsCourant - 1) {
                        prochaineCase = new int[]{nextX, nextY};
                        break;
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
        chemin.add(getSortie());
        return chemin;
    }






    public static void main(String[] args) {
        Labyrinthe lab = new Labyrinthe();
        lab.genererGrilleAleatoire();
        lab.getLesCases()[0][5].setSortie(true);
        Dijkstra dijkstra = new Dijkstra(lab);
        int[][] dj = dijkstra.initPoids();
        int[][] poids = dijkstra.setWeight(dijkstra.getSortie(), dj );

        for(int i = 0; i < lab.getX(); i++) {
            for (int j = 0; j < lab.getY(); j++) {
                System.out.print(poids[i][j] +"\t");
            }
            System.out.println();
        }
        List<int[]> chemin = dijkstra.retrouverChemin(poids, new int[]{8, 8}, dijkstra.getSortie());

        for (int[] point : chemin) {
            System.out.println(Arrays.toString(point));
        }

    }

}