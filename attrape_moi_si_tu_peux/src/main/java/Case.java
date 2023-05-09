public class Case {
    private boolean accessible;
    private boolean est_vide;
    private int tourPasse;
    private Labyrinthe leLabyrinthe;
    private Element contenu;
    private Animal animal;
    public Case(Labyrinthe leLabyrinthe,Element contenu ,Animal animal){
        this.est_vide = false;
        this.accessible = false;
        this.tourPasse = 0;
        this.leLabyrinthe = leLabyrinthe;
        this.contenu = contenu;
        this.animal = animal ;
    }
}
