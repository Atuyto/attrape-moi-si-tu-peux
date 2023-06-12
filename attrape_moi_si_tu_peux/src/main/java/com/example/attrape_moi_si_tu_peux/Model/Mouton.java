package com.example.attrape_moi_si_tu_peux.Model;

import java.util.ArrayList;

public class Mouton extends Animal {
    private boolean enFuite;
    private int nbHerbe;
    private int nbMargurite;
    private int nbCactus;

    public Mouton(Labyrinthe leLabyrinthe) {
        super(2, leLabyrinthe);
        this.enFuite        = false;
        this.nbHerbe        = 0;
        this.nbCactus       = 0;
        this.nbMargurite    = 0;
    }

    public void manger() {
        int tourPasse = this.getLeLabyrinthe().getNb_tour();
        if (this.getLaCase().getContenu() instanceof Herbe) { // On vérifie si le contenu de la case est une herbe
            this.nbHerbe += 1;
            this.getLaCase().setContenu(null);
            this.getLaCase().setTourPasse(tourPasse);  // On commence le compteur pour savoir dans combien de temps le végétal va pouvoir repousser
            this.setMouvementPossible(2);
        }
        if
        (this.getLaCase().getContenu() instanceof Marguerite) { // On vérifie si le contenu de la case est une marguerite
            this.nbMargurite += 1;
            this.getLaCase().setContenu(null);
            this.getLaCase().setTourPasse(tourPasse); // On commence le compteur pour savoir dans combien de temps le végétal va pouvoir repousser
            this.setMouvementPossible(4);
        }
        if
        (this.getLaCase().getContenu() instanceof Cactus) { // On vérifie si le contenu de la case est un com.example.attrape_moi_si_tu_peux.Model.Cactus
            this.nbCactus += 1;
            this.getLaCase().setContenu(null);
            this.getLaCase().setTourPasse(tourPasse);  // On commence le compteur pour savoir dans combien de temps le végétal va pouvoir repousser
            this.setMouvementPossible(1);
        }

    }

    public boolean isEnFuite() {
        return enFuite;
    }

    public int getNbHerbe() {
        return nbHerbe;
    }

    public int getNbMargurite() {
        return nbMargurite;
    }

    public int getNbCactus() {
        return nbCactus;
    }
    public String reperer() {
        Case[][] C      = this.getLeLabyrinthe().getLesCases(); /* Les cases du labyrinthe */
        boolean a       = false;
        boolean b       = false;
        ArrayList<Case> c = new ArrayList<Case>(); /* Regroupe les cases éloignées de maximum 5 et/ou avant rocher */
        int position[] = this.getLeLabyrinthe().getPosition(this); /* On récupère la position du mouton */
        for (int i = 0; i < 6; i++) {
            if (position[1] + i < (this.getLeLabyrinthe().getY()) - 1) /*Condition d'accession aux tests sur tableau */ {
                if (!(C[position[0]][position[1] + i].getContenu() instanceof Rocher) && !a) {
                    c.add(C[position[0]][position[1] + i]);
                } else {
                    a = true;/* permettra de bloquer la vue après avoir vu le rocher*/
                }
            }
            if (position[0] + i < (this.getLeLabyrinthe().getX()) - 1) /*Condition d'accession aux tests sur tableau */ {
                if (!(C[position[0] + i][position[1]].getContenu() instanceof Rocher) && !b) {
                    c.add(C[position[0] + i][position[1]]);
                } else {
                    b = true;
                }
            }
        }
        a = false;
        b = false; /*réinitialisation des compteurs*/
        for (int j = 0; j < 6; j++) {
            if (position[1] - j > 0) /*Condition d'accession aux tests sur tableau */ {
                if (!(C[position[0]][position[1] - j].getContenu() instanceof Rocher) && !a) {
                    c.add(C[position[0]][position[1] - j]);
                } else {
                    a = true;/* permettra de bloquer la vue après avoir vu le rocher */
                }
            }
            if (position[0] - j > 0) /*Condition d'accession aux tests sur tableau */ {
                if (!(C[position[0] - j][position[1]].getContenu() instanceof Rocher) && !b) {
                    c.add(C[position[0] - j][position[1]]);
                } else {
                    b = true;
                }
            }
        }
        for (Case k : c) {
            if (k.getAnimal() instanceof Loup) { /* Contrôle des cases éloignées maximum de 5 */
                this.enFuite = true; /* Le mouton bien que stupide tient à sa vie et fuit ! */
                int pos2[] = this.getLeLabyrinthe().getPosition(k.getAnimal());
                if (position[0] > pos2[0]) {
                    return "N";
                } else if (position[0] < pos2[0]) {
                    return "S";
                } else if (position[1] > pos2[1]) {
                    return "O";
                } else if (position[1] < pos2[1]) {
                    return "E";
                }
            }
        }
        return "";
    }
}


