import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Labyrinthe {
    private final int x;
    private final int y;
    private int nb_tour;
    private Case[][] lesCases;
    private Animal[] lesAnimaux;


    public Labyrinthe(){
        this.x          = 10;
        this.y          = 10;
        this.nb_tour    = 0;
        this.lesCases   =  new  Case[this.x][this.y];
        this.lesAnimaux =  new Animal[2];
        this.genererGrille();

    }
    public Labyrinthe(int x, int y){
        this.x = x;
        this.y = y;
        this.nb_tour = 0;
        this.lesCases =  new  Case[this.x][this.y];
        this.lesAnimaux =  new Animal[2];
        this.genererGrille();
    }


    public void genererGrille()
    {
        List<Integer> givenList = Arrays.asList(1, 2, 3, 4, 5,6);
        Random rand = new Random();

        for(int i = 0 ; i< this.x ; i++) {
            for(int j = 0 ; j<this.y ; j++) {
                int randomElement = givenList.get(rand.nextInt(givenList.size()));
                if(i == 0 || j == 0 || i == this.x -1 || j == this.y -1) {
                    this.lesCases[i][j] = new Case(this, new Rocher(), null);
                }
                else {
                    if( randomElement == 1) {
                        this.lesCases[i][j] = new Case(this,  new Rocher() , null);
                    }
                    else {
                        this.lesCases[i][j] = new Case(this);
                    }
                }
            }
        }
    }

    public void ajouterAnimal(Animal animal, int x, int y){
        this.getLesCases()[x][y].setAnimal(animal);
        animal.setLaCase(this.getLesCases()[x][y]);
    }

    public void afficher() {
        for(int i = 0 ; i< this.x ; i++) {
            for(int j = 0 ; j<this.y ; j++) {
                System.out.print(lesCases[i][j].toString() + "\t");}
            System.out.print("\n");
            }
        }

    public int getNb_tour() {
        return nb_tour;
    }
    public void setNb_tour( int i ){
        this.nb_tour = i;
    }

    public Case[][] getLesCases() {return this.lesCases;}

    public int[] getPosition(Animal animal)
    {
        int[] p = new int[2];

        for(int i = 0 ; i< this.x ; i++) {
            for(int j = 0 ; j<this.y ; j++) {
                if (this.lesCases[i][j].getAnimal() == animal) {
                    p[0] = i ; p[1] = j;
                    break;}
            }
        }
        return p;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
