import java.util.Objects;

public class Animal {
    private int mouvementPossible;
    private int vision;
    private Labyrinthe leLabyrinthe;
    private Case laCase;


    public Animal(int mouvementPossible, Labyrinthe leLabyrinthe){
        this.mouvementPossible  = mouvementPossible;
        this.vision             = 5;
        this.leLabyrinthe       = leLabyrinthe;


    }


    public void setMouvementPossible(int mouvementPossible) {
        this.mouvementPossible = mouvementPossible;
    }

    public void setLaCase(Case laCase) {
        this.laCase = laCase;
    }

    public void seDeplacer(int nbCase, String orientation)
    {
        int x  = this.leLabyrinthe.getPosition(this)[0];
        int y  = this.leLabyrinthe.getPosition(this)[1];
        nbCase = Objects.equals(orientation, "N") || Objects.equals(orientation, "O") ? nbCase*(-1) : nbCase;


        if(orientation.equals("N") || orientation.equals("S")){
            System.out.println(x + nbCase + ", " + y);
            if (this.leLabyrinthe.getLesCases()[x + nbCase][y].isAccessible()){
                this.leLabyrinthe.getLesCases()[x + nbCase][y].setAnimal(this);
                this.leLabyrinthe.getLesCases()[x][y].setAnimal(null);
                this.setLaCase(this.leLabyrinthe.getLesCases()[x+nbCase][y]);
            }
        }
        if(orientation.equals("E") || orientation.equals("O")) {
            if (this.leLabyrinthe.getLesCases()[x][y + nbCase].isAccessible()) {
                this.leLabyrinthe.getLesCases()[x][y + nbCase].setAnimal(this);
                this.leLabyrinthe.getLesCases()[x][y].setAnimal(null);
                this.setLaCase(this.leLabyrinthe.getLesCases()[x][y + nbCase]);
            }
        }
    }



    public int getMouvementPossible() {
        return mouvementPossible;
    }

    public int getVision() {
        return vision;
    }

    public Case getLaCase(){ return this.laCase;}
}
