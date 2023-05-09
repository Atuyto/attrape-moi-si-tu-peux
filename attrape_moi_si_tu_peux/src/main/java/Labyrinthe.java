public class Labyrinthe {
    private int x;
    private int y;
    private int nb_tour;
    private Case[][] lesCases;
    private Animal[] lesAnimaux;


    public Labyrinthe(){
        this.x = 10;
        this.y = 10;
        this.nb_tour = 0;
        this.lesCases =  new  Case[this.x][this.y];
        this.lesAnimaux =  new Animal[2];

    }
    public Labyrinthe(int x, int y){
        this.x = x;
        this.y = y;
        this.nb_tour = 0;
        this.lesCases =  new  Case[this.x][this.y];
        this.lesAnimaux =  new Animal[2];

    }

    public void genererGrille()
    {
        for(int i = 0 ; i< this.x ; i++)
        {
            for(int j = 0 ; j<this.y ; j++)
            {
                if(i == 0 || j == 0 || i == this.x || j == this.y) {
                    this.lesCases[i][j] = new Case(this, new Rocher(), null);
                }
                else {
                    this.lesCases[i][j] = new Case(this, null, null);
                }
            }
        }

    }

    public arrayList<Case> resolutionLabyrinthe () {
        return new arrayList<Case>();
    }

    public void getSortie() {

    }

    public int getNbTour() {
        return this.nb_tour;
    }

    public arrayList<Case> getlesCases() {
        return this.lesCases;
    }
}
