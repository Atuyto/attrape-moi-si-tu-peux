import java.util.ArrayList;

public class Loup extends Animal {
    private boolean enChasse;

    public Loup(Labyrinthe leLabyrinthe) {
        super(3, leLabyrinthe);
        this.enChasse = false;
    }

    public void manger() {
        if (this.getLaCase().getAnimal() instanceof Mouton) {
            this.enChasse = false;
            /*return true;*/
        }
        /*return false;*/
    }

    public boolean reperer() {
        Case[][] C = this.getLeLabyrinthe().getLesCases(); /* Les cases du labyrinthe */
        boolean a = false;
        boolean b = false;
        ArrayList<Case> c = new ArrayList<Case>(); /* Regroupe les cases éloignées de maximum 5 et/ou avant rocher */
        int position[] = this.getLeLabyrinthe().getPosition(this); /* On récupère la position du loup */
        for (int i = 0; i < 6; i++) {
            if (position[1] + i >= (this.getLeLabyrinthe().getY()) - 1) /*Condition de non accession aux tests sur tableau */ {}
                else if (!(C[position[0]][position[1] + i].getContenu() instanceof Rocher) && (a == false)) {
                    c.add(C[position[0]][position[1] + i]);
                } else {
                    a = true;/* permettra de bloquer la vue après avoir vu le rocher*/
                }
            if (position[0] + i >= (this.getLeLabyrinthe().getX()) - 1) /*Condition de non accession aux tests sur tableau */ {}
                else if (!(C[position[0] + i][position[1]].getContenu() instanceof Rocher) && (b == false)) {
                    c.add(C[position[0] + i][position[1]]);
                } else {
                    b = true;
                }
            }
        a = false;
        b = false; /*réinitialisation des compteurs*/
        for (int j = 0; j < 6; j++) {
            if (position[1] - j <= 0) /*Condition de non accession aux tests sur tableau */ {}
                else if (!(C[position[0]][position[1] - j].getContenu() instanceof Rocher) && (a == false)) {
                    c.add(C[position[0]][position[1] - j]);
                } else {
                    a = true;/* permettra de bloquer la vue après avoir vu le rocher */
            }
            if (position[0] - j <= 0) /*Condition de non accession aux tests sur tableau */ {}
                else if (!(C[position[0] - j][position[1]].getContenu() instanceof Rocher) && (b == false)) {
                    c.add(C[position[0] - j][position[1]]);
                } else {
                    b = true;
                }
            }
        for (Case k : c) {
            if (k.getAnimal() instanceof Mouton) { /* Contrôle des cases éloignées maximum de 5 */
                this.enChasse = true; /* Le loup se met au travail et part à la chasse ! */
                return true;
            }
        }
        return false;
    }
}
