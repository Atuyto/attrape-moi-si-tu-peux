package com.example.attrape_moi_si_tu_peux.Model;

public abstract class Animal {
    private int mouvementPossible;
    private int vision;
    private Labyrinthe leLabyrinthe;
    private Case laCase;


    public Animal(int mouvementPossible, Labyrinthe leLabyrinthe){
        this.mouvementPossible  = mouvementPossible;
        this.vision             = 5;
        this.leLabyrinthe       = leLabyrinthe;
    }


    public int[] seDeplacer(int nbdep, String orientation) {
        int x = this.leLabyrinthe.getPosition(this)[0];
        int y = this.leLabyrinthe.getPosition(this)[1];
        int i = 0;
        int deplacementsRestants = nbdep;

        while (i != nbdep) {
            int deplacement = Math.min(5, deplacementsRestants);  // Déplacement maximal possible

            if (orientation.equals("O")) {
                int tmpy = y - deplacement;
                if (isAccessibleAndNoLoup(x, tmpy)) {
                    moveAnimal(x, y, x, tmpy);
                    y = tmpy;
                    deplacementsRestants -= deplacement;
                }
            }
            else if (orientation.equals("S")) {
                int tmpx = x + deplacement;
                if (isAccessibleAndNoLoup(tmpx, y)) {
                    moveAnimal(x, y, tmpx, y);
                    x = tmpx;
                    deplacementsRestants -= deplacement;
                }
            }
            else if (orientation.equals("E")) {
                int tmpy = y + deplacement;
                if (isAccessibleAndNoLoup(x, tmpy)) {
                    moveAnimal(x, y, x, tmpy);
                    y = tmpy;
                    deplacementsRestants -= deplacement;
                }
            }
            else if (orientation.equals("N")) {
                int tmpx = x - deplacement;
                if (isAccessibleAndNoLoup(tmpx, y)) {
                    moveAnimal(x, y, tmpx, y);
                    x = tmpx;
                    deplacementsRestants -= deplacement;
                }
            }

            i += deplacement;
        }

        return new int[]{x, y};
    }


    private boolean isWithinManhattanDistance(int x, int y, int distance) {
        int animalX = this.leLabyrinthe.getPosition(this)[0];
        int animalY = this.leLabyrinthe.getPosition(this)[1];

        int manhattanDistance = Math.abs(x - animalX) + Math.abs(y - animalY);
        return manhattanDistance <= distance;
    }

    private boolean isAccessibleAndNoLoup(int x, int y) {
        if (x >= 0 && x < this.leLabyrinthe.getX() && y >= 0 && y < this.leLabyrinthe.getY()) {
            if (this.getLeLabyrinthe().getLesCases()[x][y].isAccessible()) {
                Animal animal = this.getLeLabyrinthe().getLesCases()[x][y].getAnimal();
                return !(animal instanceof Loup);
            }
        }
        return false;
    }

    private void moveAnimal(int currentX, int currentY, int newX, int newY) {
        this.getLeLabyrinthe().getLesCases()[newX][newY].setAnimal(this);
        this.getLeLabyrinthe().getLesCases()[currentX][currentY].setAnimal(null);
        this.laCase = this.getLeLabyrinthe().getLesCases()[newX][newY];
    }

    public int getMouvementPossible() {
        return mouvementPossible;
    }
    public abstract void manger( );

    public Labyrinthe getLeLabyrinthe() {
        return leLabyrinthe;
    }

    public int getVision() {
        return vision;
    }

    public Case getLaCase(){ return this.laCase;}
    public void setMouvementPossible(int mouvementPossible) {
        this.mouvementPossible = mouvementPossible;
    }

    public void setLaCase(Case laCase) {
        this.laCase = laCase;
    }
}
