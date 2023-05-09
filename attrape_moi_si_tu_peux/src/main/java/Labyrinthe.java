import java.util.ArrayList;

public class Labyrinthe {
    private int x;
    private int y;
    private int nb_tour;
    private ArrayList<Case> lesCases;
    private ArrayList<Animal> lesAnimaux;

    public Labyrinthe(int x, int y, ArrayList<Case> lesCases, ArrayList<Animal> lesAnimaux ){
        this.x = x;
        this.y = y;
        this.nb_tour = 0;
        this.lesCases =  new ArrayList<Case>();
        this.lesAnimaux =  new ArrayList<Animal>();

    }
}
