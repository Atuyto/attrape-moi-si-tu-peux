package com.example.attrape_moi_si_tu_peux.Model;

import java.util.*;

public class Dijkstra {

    private Labyrinthe lab;
    private Case depart;

    private Case sortie;

    public Dijkstra(Labyrinthe labyrinthe, Case depart) {
        this.lab = labyrinthe;
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

    public int[][] voisins(int[][] tableauCoordonnees, int[] point) {
        int[][] voisinsExplorer = new int[lab.getX()][lab.getY()];
        int[][] voisins = new int[lab.getX() * lab.getY()][2];
        int count = 0;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int x = point[0];
        int y = point[1];

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX >= 0 && nextX < lab.getX() && nextY >= 0 && nextY < lab.getY()) {
                if (lab.getLesCases()[nextX][nextY].isAccessible() && voisinsExplorer[nextX][nextY] == 0) {
                    voisins[count][0] = nextX;
                    voisins[count][1] = nextY;
                    voisinsExplorer[nextX][nextY] = 1;
                    count++;
                }
            }
        }

        // Réduire la taille du tableau de voisins pour éliminer les cases inutilisées
        int[][] trimmedVoisins = new int[count][2];
        System.arraycopy(voisins, 0, trimmedVoisins, 0, count);

        return trimmedVoisins;
    }


    public int[][] initPoids(){
        int[][] poidLab;
        poidLab = new int[lab.getX()][lab.getY()];
        for(int i = 0; i < lab.getX(); i++){
            for(int j = 0;j<lab.getY() ; j++){
                if(lab.getLesCases()[i][j].getContenu() instanceof Rocher){
                    poidLab[i][j] = lab.getX()* lab.getY();
                }
                else {
                    poidLab[i][j] = -1;
                }
            }
        }
        int[] sorti = getSortie();
        poidLab[sorti[0]][sorti[1]] = 0;

        return poidLab;
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
    public List<int[]> retrouverChemin(int[][] poids, int[] depart, int[] arriver) {
        List<int[]> chemin = new ArrayList<>();
        int[] courante = depart;
        chemin.add(courante);

        while (!(courante[0]== arriver[0] && courante[1] == arriver[1])) {
            int x = courante[0];
            int y = courante[1];
            int poidsCourant = poids[x][y];
            int[] prochaineCase = null;

            int[][] voisins = voisins(poids, new int[]{x, y});

            for (int i = 0; i < voisins.length; i++) {
                int nextX = voisins[i][0];
                int nextY = voisins[i][1];
                int voisin = poids[nextX][nextY];
                int poidsVoisin = poids[nextX][nextY];

                if (poidsVoisin == poidsCourant - 1) {
                    prochaineCase = new int[]{voisin};
                    break;
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


    public static void main(String[] args) {
        Labyrinthe lab = new Labyrinthe();
        lab.genererGrilleAleatoire();
        lab.getLesCases()[5][0].setSortie(true);
        Dijkstra dijkstra = new Dijkstra(lab, lab.getLesCases()[5][0]);
        int[][] dj = dijkstra.initPoids();
        int[][] poids = dijkstra.setWeight(dijkstra.getSortie(), dj );

        for(int i = 0; i < lab.getX(); i++) {
            for (int j = 0; j < lab.getY(); j++) {
                System.out.print(poids[i][j] +"\t");
            }
            System.out.println();
        }
        //System.out.println(Arrays.deepToString(dijkstra.voisins(poids, new int[]{5, 2})));
        List<int[]> chemin = dijkstra.retrouverChemin(poids, new int[]{1, 9},  new int[]{5, 0} );

        for(int i = 0 ; i < chemin.size(); i++){
            System.out.println(Arrays.toString(chemin.get(i)));
        }
    }

}