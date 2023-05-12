
public class Animal {
    private int mouvementPossible;
    private int vision;
    private Labyrinthe leLabyrinthe;
    private Case laCase;


    public Animal(int mouvementPossible, Labyrinthe leLabyrinthe){
        this.mouvementPossible = mouvementPossible;
        this.vision = 5;
        this.leLabyrinthe = leLabyrinthe;

    }

    public void setMouvementPossible(int mouvementPossible) {
        this.mouvementPossible = mouvementPossible;
    }

    public void setLaCase(Case laCase) {
        this.laCase = laCase;
    }

    public void seDeplacer(int nbCase, String orientation)
    {
        int[] position = this.leLabyrinthe.getPosition(this);
        switch (orientation){
            case "N" :
                this.leLabyrinthe.getLesCases()[position[0]+nbCase][position[1]].setAnimal(this);
                this.leLabyrinthe.getLesCases()[position[0]][position[1]].setAnimal(null);
                this.setLaCase(this.leLabyrinthe.getLesCases()[position[0]+nbCase][position[1]]);
            case "S" :
                this.leLabyrinthe.getLesCases()[position[0]-nbCase][position[1]].setAnimal(this);
                this.leLabyrinthe.getLesCases()[position[0]][position[1]].setAnimal(null);
                this.setLaCase(this.leLabyrinthe.getLesCases()[position[0]-nbCase][position[1]]);
            case "E" :
                this.leLabyrinthe.getLesCases()[position[0]][position[1]+nbCase].setAnimal(this);
                this.leLabyrinthe.getLesCases()[position[0]][position[1]].setAnimal(null);
                this.setLaCase(this.leLabyrinthe.getLesCases()[position[0]][position[1]+nbCase]);

            case  "O" :
                this.leLabyrinthe.getLesCases()[position[0]][position[1]-nbCase].setAnimal(this);
                this.leLabyrinthe.getLesCases()[position[0]][position[1]].setAnimal(null);
                this.setLaCase(this.leLabyrinthe.getLesCases()[position[0]][position[1]-nbCase]);
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
