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


    public boolean isEnFuite() {
        return enFuite;
    }

    public int getNbHerbe() {
        return nbHerbe;
    }

    public int getNbMargurite() {
        return nbMargurite;
    }

    public int getNbCactus() {
        return nbCactus;
    }

    public void setEnFuite(boolean enFuite) {
        this.enFuite = enFuite;
    }

    public void setNbHerbe(int nbHerbe) {
        this.nbHerbe = nbHerbe;
    }

    public void setNbMargurite(int nbMargurite) {
        this.nbMargurite = nbMargurite;
    }

    public void setNbCactus(int nbCactus) {
        this.nbCactus = nbCactus;
    }
}
