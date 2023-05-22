package com.example.attrape_moi_si_tu_peux;

public class Animal {
    private int mouvementPossible;
    private int vision;
    private Labyrinthe leLabyrinthe;
    private Case laCase;


    public Animal(int mouvementPossible, Labyrinthe leLabyrinthe){
        this.mouvementPossible  = mouvementPossible;
        this.vision             = 5;
        this.leLabyrinthe       = leLabyrinthe;


    }


    public void setMouvementPossible(int mouvementPossible) {
        this.mouvementPossible = mouvementPossible;
    }

    public void setLaCase(Case laCase) {
        this.laCase = laCase;
    }

    public void seDeplacer(int nbdep, String orientation){
        int x = this.leLabyrinthe.getPosition(this)[0];
        int y = this.leLabyrinthe.getPosition(this)[1];
        int i = 0;
        boolean possible=true;
        while(i < nbdep && (possible)){
            if (x + i > 0 || this.getLeLabyrinthe().getX()-1 < x + i || y + i > 0 || this.getLeLabyrinthe().getY()-1 < y + i ){
                i++;
                if ( orientation.equals("N")) {
                    if (this.getLeLabyrinthe().getLesCases()[x - i][y].isAccessible()) {
                        this.getLeLabyrinthe().getLesCases()[x-i][y].setAnimal(this);
                        this.getLeLabyrinthe().getLesCases()[x-i+1][y].setEstVide();
                        this.getLeLabyrinthe().getLesCases()[x-i+1][y].setAnimal(null);
                    }
                    else{
                        System.out.println("impossible de se déplacer ici");
                        possible = false;
                    }
                }
                if (orientation.equals(("S"))) {
                    if (this.getLeLabyrinthe().getLesCases()[x + i][y].isAccessible()) {
                        this.getLeLabyrinthe().getLesCases()[x+i][y].setAnimal(this);
                        this.getLeLabyrinthe().getLesCases()[x+i-1][y].setEstVide();
                        this.getLeLabyrinthe().getLesCases()[x+i-1][y].setAnimal(null);
                    }
                    else {
                        System.out.println("impossible de se déplacer ici");
                        possible = false;
                    }
                }
                if (orientation.equals("E")) {
                    if (this.getLeLabyrinthe().getLesCases()[x][y + i].isAccessible()) {
                        this.getLeLabyrinthe().getLesCases()[x][y+i].setAnimal(this);
                        this.getLeLabyrinthe().getLesCases()[x][y+i-1].setEstVide();
                        this.getLeLabyrinthe().getLesCases()[x][y+i-1].setAnimal(null);
                    }
                    else{
                        System.out.println("impossible de se déplacer ici");
                        possible = false;
                    }
                }
                if (orientation.equals("O")){
                    if (this.getLeLabyrinthe().getLesCases()[x][y-i].isAccessible()){
                        this.getLeLabyrinthe().getLesCases()[x][y-i].setAnimal(this);
                        this.getLeLabyrinthe().getLesCases()[x][y-i+1].setEstVide();
                        this.getLeLabyrinthe().getLesCases()[x][y-i+1].setAnimal(null);
                        }
                    else{
                        System.out.println("impossible de se déplacer ici");
                        possible = false;
                    }
                    }
                }

            }
        }





    public int getMouvementPossible() {
        return mouvementPossible;
    }

    public Labyrinthe getLeLabyrinthe() {
        return leLabyrinthe;
    }

    public int getVision() {
        return vision;
    }

    public Case getLaCase(){ return this.laCase;}
}
