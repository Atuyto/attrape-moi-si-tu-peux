import java.util.ArrayList;

public class Loup extends Animal{
    private boolean enChasse;

    public Loup(Labyrinthe leLabyrinthe, Case Lacase) {
        super(3, leLabyrinthe, Lacase);
        this.enChasse = false;
    }
    public void manger(){
        if(this.getLaCase().getContenu() instanceof Mouton) {
            this.enChasse = false;
            /*return true;*/
        }
        /*return false;*/
    }

    public boolean reperer(){
        Case[][] C = this.leLabyrinthe.getLesCases(); /* Les cases du labyrinthe */
        boolean a = false;
        boolean b = false;
        ArrayList<Case> c = new ArrayList<Case>(); /* Regroupe les cases éloignées de maximum 5 et/ou avant rocher */
        position[] = this.leLabyrinthe.getPosition(this); /* On récupère la position du loup */
        }
        for (int i = 0; i < 6; i++) {
            if ((i != 0) {
                if ((Case[position[0]][position[1]+i] instanceof Rocher) == false)||(a == false)||(i != (this.leLabyrinthe.getY())-1) {
                c.add(this.C[position[0]][position[1]+i]);
            }
                else {
                    a = true;/* permettra de bloquer la vue après avoir vu le rocher*/
                }
            if ((Case[position[0]+i][position[1]] instanceof Rocher) == false)||(b == false)||(i != (this.leLabyrinthe.getX())-1)) {
                c.add(this.C[position[0]+i][position[1]]);
            }
                else {
                    b = true;
            }
        }
        a = false; b = false; /*réinitialisation des compteurs*/
        for (int i = 0; i < 6; i++) {
            if ((i != 0) {
                if ((Case[position[0]][position[1]-i] instanceof Rocher) == false)||(a == false)||(i != 0)) {
                    c.add(this.C[position[0]][position[1]-i]);
                }
                else {
                    a = true;/* permettra de bloquer la vue après avoir vu le rocher */
                }
                if ((Case[position[0]-i][position[1]] instanceof Rocher) == false)||(b == false)||(i != 0)) {
                    c.add(this.C[position[0]-i][position[1]]);
                }
                else {
                    b = true;
                }
            }
        }
        for (Case i: c) {
            if (i instanceof Mouton) { /* Contrôle des cases éloignées maximum de 5 */
                this.enChasse = true; /* Le loup se met au travail et part à la chasse ! */
                return true;
            }
        }
        return false;
    }
}
