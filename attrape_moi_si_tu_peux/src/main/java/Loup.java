import java.util.ArrayList;

public class Loup extends Animal {
    private boolean enChasse;

    public Loup(Labyrinthe leLabyrinthe) {
        super(3, leLabyrinthe);
        this.enChasse = false;
    }

    public void manger() {
        if (this.getLaCase().getAnimal() instanceof Mouton) {
            this.enChasse = false;
            /*return true;*/
        }
        /*return false;*/
    }

}
