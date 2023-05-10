public class Mouton extends Animal {
   private boolean enFuite;
    private int nbHerbe;
    private int nbMargurite;
    private int nbCactus;
    public Mouton(Labyrinthe leLabyrinthe, Case laCase) {
        super(2, leLabyrinthe, laCase);
        this.enFuite = false;
        this.nbHerbe = 0;
        this.nbCactus = 0;
        this.nbMargurite = 0;
    }
    public void manger(){
        if(this.clone()){

        }
    }
}
