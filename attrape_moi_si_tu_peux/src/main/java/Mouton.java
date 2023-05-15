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
        if(this.getLaCase().getContenu() instanceof Herbe) { // On vérifie si le contenu de la case est une herbe
            this.nbHerbe += 1;
            this.getLaCase().setContenu(null);
            this.getLaCase().setEstVide(true);
            this.getLaCase().setTourPasse(this.getLeLabyrinthe().getNb_tour());  // On commence le compteur pour savoir dans combien de temps le végétal va pouvoir repousser
            this.setMouvementPossible(2);}
        else if
            (this.getLaCase().getContenu() instanceof Marguerite) { // On vérifie si le contenu de la case est une marguerite
            this.nbMargurite += 1 ;
            this.getLaCase().setContenu(null);
            this.getLaCase().setEstVide(true);
            this.getLaCase().setTourPasse(this.getLeLabyrinthe().getNb_tour()); // On commence le compteur pour savoir dans combien de temps le végétal va pouvoir repousser
            this.setMouvementPossible(4);}
        else if
            (this.getLaCase().getContenu() instanceof Cactus) { // On vérifie si le contenu de la case est un Cactus
            this.nbCactus += 1;
            this.getLaCase().setContenu(null);
            this.getLaCase().setEstVide(true);
            this.getLaCase().setTourPasse(this.getLeLabyrinthe().getNb_tour());  // On commence le compteur pour savoir dans combien de temps le végétal va pouvoir repousser
            this.setMouvementPossible(1);}
        else{
            this.setMouvementPossible(2);
            }
        }
}



