public class Loup extends Animal{
    private boolean enChasse;

    public Loup(int mouvementPossible, Labyrinthe leLabyrinthe, Case Lacase) {
        super(mouvementPossible, leLabyrinthe, Lacase);
        this.enChasse = false;
    }
}
