public class Loup extends Animal{
    private boolean enChasse;

    public Loup(Labyrinthe leLabyrinthe, Case laCase) {
        super(3, leLabyrinthe, laCase);
        this.enChasse = false;
    }
}
