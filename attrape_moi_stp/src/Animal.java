
public class Animal {
    private int mouvementPossible;
    private int vision;
    private Labyrinthe leLabyrinthe;
    private Case Lacase;

    public Animal(int mouvementPossible, Labyrinthe leLabyrinthe, Case Lacase){
        this.mouvementPossible = mouvementPossible;
        this.vision = 5;
        this.leLabyrinthe = leLabyrinthe;
        this.Lacase = Lacase;
    }
}
