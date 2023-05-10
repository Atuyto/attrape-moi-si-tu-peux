
public class Animal {
    private int mouvementPossible;
    private int vision;
    private Labyrinthe leLabyrinthe;
    private Case laCase;


    public Animal(int mouvementPossible, Labyrinthe leLabyrinthe, Case laCase){
        this.mouvementPossible = mouvementPossible;
        this.vision = 5;
        this.leLabyrinthe = leLabyrinthe;
        this.laCase = laCase;
    }

    public int getMouvementPossible() {
        return mouvementPossible;
    }

    public int getVision() {
        return vision;
    }

}
