public class Loup extends Animal{
    private boolean enChasse;

    public Loup(int mouvementPossible, Labyrinthe leLabyrinthe, Case Lacase) {
        super(mouvementPossible, leLabyrinthe, Lacase);
        this.enChasse = false;
    }

    public void chasser() {
        this.enChasse = true;
    }

    public boolean manger() {

    }

    public boolean reperer(String orientation) {

    }

    public void seDeplacer(int nbCase, String orientation) {

    }
}
