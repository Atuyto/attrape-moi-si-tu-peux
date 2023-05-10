
public class Animal {
    private int mouvementPossible;
    private int vision;
    private Labyrinthe leLabyrinthe;
    private Case laCase;

    public Animal(int mouvementPossible, Labyrinthe leLabyrinthe, Case Lacase){
        this.mouvementPossible = mouvementPossible;
        this.vision = 5;
        this.leLabyrinthe = leLabyrinthe;
        this.laCase = Lacase;
    }
}
