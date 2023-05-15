import java.util.ArrayList;
import java.util.Random;

public class Case {
    private boolean accessible;
    private boolean estVide;
    private int tourPasse;
    private Labyrinthe leLabyrinthe;
    private Element contenu;
    private Animal animal;


    public Case(Labyrinthe leLabyrinthe, Element contenu , Animal animal){
        this.estVide        = false;
        this.accessible     = false;
        this.tourPasse      = 0;
        this.leLabyrinthe   = leLabyrinthe;
        this.contenu        = contenu;
        this.animal         = animal ;
    }
    public Case(Labyrinthe leLabyrinthe){
        this.estVide        = false;
        this.accessible     = false;
        this.tourPasse      = 0;
        this.leLabyrinthe   = leLabyrinthe;
        this.contenu        = this.regeneration();

    }


    public Element regeneration()
    {
        ArrayList<Integer> givenList = new ArrayList(); // je fais une liste de int dans lequel il y a 100 élémenet je donne 1 l'herbe, 2 le catus et 3 la marguerite
        for(int i = 1 ; i< 101 ; i++)
        {
            if (i < 50) givenList.add(1);
            if (i < 75 && i > 50) givenList.add((2));
            if (i< 100 && i > 75) givenList.add(3);
        }
        if(leLabyrinthe.getNb_tour() == 0) {
            Random rand         = new Random();
            int randomElement   = givenList.get(rand.nextInt(givenList.size()));
            this.contenu        = randomElement == 1 ? new Herbe() : randomElement ==2 ? new Marguerite() : new Cactus();



        }
        return this.getContenu();
    }

    public String toString()
    {
        return this.animal != null ? this.animal.getClass().getName(): this.contenu == null ?   this.getClass().getName()  : this.contenu.getClass().getName();
    }


    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }
    public void setEstVide() {
        this.estVide = !this.estVide;
    }

    public void setContenu(Element contenu) {
        this.contenu = contenu;
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public boolean isAccessible() {
        return !(this.contenu instanceof Rocher) || (this.accessible = false);
    }

    public boolean isEstVide() {
        return estVide;
    }

    public int getTourPasse() {
        return tourPasse;
    }

    public Animal getAnimal(){return animal;}

    public Element getContenu() {
        return contenu;
    }

    public void setTourPasse(int tourPasse) {
        this.tourPasse = tourPasse;
    }

}
