public class Case {
    private boolean accessible;
    private boolean estVide;
    private int tourPasse;
    private Labyrinthe leLabyrinthe;
    private Element contenu;
    private Animal animal;


    public Case(Labyrinthe leLabyrinthe, Element contenu , Animal animal){
        this.estVide = false;
        this.accessible = false;
        this.tourPasse = 0;
        this.leLabyrinthe = leLabyrinthe;
        this.contenu = contenu;
        this.animal = animal ;
    }



    public String toString()
    {
        return this.contenu == null ?   this.getClass().getName() + "@" + Integer.toHexString(hashCode()) : this.contenu.toString();
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }
    public void setEstVide(boolean estVide) {
        this.estVide = estVide;
    }

    public void setContenu(Element contenu) {
        this.contenu = contenu;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public boolean isEstVide() {
        return estVide;
    }

    public int getTourPasse() {
        return tourPasse;
    }

    public Element getContenu() {
        return contenu;
    }


}
