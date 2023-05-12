public class Mouton extends Animal {
   private boolean enFuite;
    private int nbHerbe;
    private int nbMargurite;
    private int nbCactus;
    public Mouton(Labyrinthe leLabyrinthe) {
        super(2, leLabyrinthe);
        this.enFuite = false;
        this.nbHerbe = 0;
        this.nbCactus = 0;
        this.nbMargurite = 0;
    }
}
