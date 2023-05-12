
public class Animal {
    private int mouvementPossible;
    private int vision;
    private Labyrinthe leLabyrinthe;
    private Case laCase;


    public Animal(int mouvementPossible, Labyrinthe leLabyrinthe){
        this.mouvementPossible = mouvementPossible;
        this.vision = 5;
        this.leLabyrinthe = leLabyrinthe;

    }




    public int getMouvementPossible() {
        return mouvementPossible;
    }

    public int getVision() {
        return vision;
    }

    public Case getLaCase(){ return this.laCase;}
}
