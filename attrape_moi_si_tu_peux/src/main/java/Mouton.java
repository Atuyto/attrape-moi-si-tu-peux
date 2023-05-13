public class Mouton extends Animal {
   private boolean enFuite;
    private int nbHerbe;
    private int nbMargurite;
    private int nbCactus;

    public Mouton(Labyrinthe leLabyrinthe) {
        super(2, leLabyrinthe);
        this.enFuite        = false;
        this.nbHerbe        = 0;
        this.nbCactus       = 0;
        this.nbMargurite    = 0;
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

    public void manger(){
        if(this.getLaCase().getContenu() instanceof Herbe) {
            this.nbHerbe += 1;
            this.setMouvementPossible(2);}
        else if
            (this.getLaCase().getContenu() instanceof Marguerite) {
            this.nbMargurite += 1 ;
            this.setMouvementPossible(4);}
        else if
            (this.getLaCase().getContenu() instanceof Cactus) {
            this.nbCactus += 1;
            this.setMouvementPossible(1);}
        else{
            this.setMouvementPossible(2);
            }
        }
    }


