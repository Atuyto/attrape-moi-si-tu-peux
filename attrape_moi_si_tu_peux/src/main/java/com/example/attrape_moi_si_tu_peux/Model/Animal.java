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

    public int[] seDeplacer(int nbdep, String orientation){
        int x               = this.leLabyrinthe.getPosition(this)[0];
        int y               = this.leLabyrinthe.getPosition(this)[1];
        int i               = 0;
        while(i < nbdep){
            if (x + 1 > 0 || this.getLeLabyrinthe().getX()-1 < x + 1 || y + 1 > 0 || this.getLeLabyrinthe().getY()-1 < y + 1 ) {
                if (orientation.equals("O")) {
                   int tmpy = y-1;
                    if (this.getLeLabyrinthe().getLesCases()[x][tmpy].isAccessible() && !(this.getLeLabyrinthe().getLesCases()[x][tmpy].getAnimal() instanceof Mouton)) {
                        this.getLeLabyrinthe().getLesCases()[x][tmpy].setAnimal(this);
                        this.getLeLabyrinthe().getLesCases()[x][y].setAnimal(null);
                        y = tmpy;
                    }
                    if (this instanceof Loup && this.getLeLabyrinthe().getLesCases()[x][tmpy].getAnimal() instanceof Mouton){
                        this.manger();
                        this.getLeLabyrinthe().getLesCases()[x][y].setAnimal(null);

                    }
                }
                if (orientation.equals(("E"))) {
                   int tmpy = y+1;
                    if (this.getLeLabyrinthe().getLesCases()[x][tmpy].isAccessible() && !(this.getLeLabyrinthe().getLesCases()[x][tmpy].getAnimal() instanceof Mouton) ) {
                        this.getLeLabyrinthe().getLesCases()[x][tmpy].setAnimal(this);
                        this.getLeLabyrinthe().getLesCases()[x][y].setAnimal(null);
                        y = tmpy;
                    }
                    if (this instanceof Loup && this.getLeLabyrinthe().getLesCases()[x][tmpy].getAnimal() instanceof Mouton){
                        this.manger();
                        this.getLeLabyrinthe().getLesCases()[x][y].setAnimal(null);

                    }
                }
                if (orientation.equals("S")) {
                    int tmpx = x+1;
                    if (this.getLeLabyrinthe().getLesCases()[tmpx][y].isAccessible() && !(this.getLeLabyrinthe().getLesCases()[tmpx][y].getAnimal() instanceof Mouton) ) {
                        this.getLeLabyrinthe().getLesCases()[tmpx][y].setAnimal(this);
                        this.getLeLabyrinthe().getLesCases()[x][y].setAnimal(null);
                        x= tmpx;
                    }
                    if (this instanceof Loup && this.getLeLabyrinthe().getLesCases()[tmpx][y].getAnimal() instanceof Mouton){
                        this.manger();
                        this.getLeLabyrinthe().getLesCases()[x][y].setAnimal(null);

                    }
                }
                if (orientation.equals("N")) {
                    int tmpx = x-1;
                    if (this.getLeLabyrinthe().getLesCases()[tmpx][y].isAccessible() && !(this.getLeLabyrinthe().getLesCases()[tmpx][y].getAnimal() instanceof Mouton) ) {
                        this.getLeLabyrinthe().getLesCases()[tmpx][y].setAnimal(this);
                        this.getLeLabyrinthe().getLesCases()[x][y].setAnimal(null);
                        x= tmpx;
                    }
                    if (this instanceof Loup && this.getLeLabyrinthe().getLesCases()[tmpx][y].getAnimal() instanceof Mouton){
                        this.manger();
                        this.getLeLabyrinthe().getLesCases()[x][y].setAnimal(null);

                    }
                }
                this.laCase = this.getLeLabyrinthe().getLesCases()[x][y];
                i++;
            }

        }
        return new int[]{x , y};
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
