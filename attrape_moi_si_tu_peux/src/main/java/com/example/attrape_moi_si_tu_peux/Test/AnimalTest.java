package com.example.attrape_moi_si_tu_peux.Test;

import com.example.attrape_moi_si_tu_peux.Model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    protected Labyrinthe laby;
    protected Loup l;
    protected Mouton m;

    @BeforeEach
    void init() {
        laby = new Labyrinthe(8, 8);
        laby.genererGrille();
        m= new Mouton(laby);


    }

    @AfterEach
    void reset() {
        init();
    }

    @Test
    void seDeplacerE() {
        laby.getLesCases()[1][1].setContenu(new Rocher());
        laby.ajouterAnimal(m, 1, 4);

        m.manger();
        m.seDeplacer(m.getMouvementPossible(), "E");
        assertTrue(laby.getLesCases()[1][6].getAnimal() == m);

    }
    @Test
    void seDeplacerE2() {
        laby.getLesCases()[1][1].setContenu(new Marguerite());
        laby.ajouterAnimal(m, 1, 1);
        m.manger();
        m.seDeplacer(m.getMouvementPossible(), "E");
        assertTrue(laby.getLesCases()[1][5].getAnimal() == m);
    }
    @Test
    void seDeplacerE3() {
        laby.getLesCases()[1][1].setContenu(new Cactus());
        laby.ajouterAnimal(m, 1, 1);
        m.manger();
        m.seDeplacer(m.getMouvementPossible(), "E");
        assertTrue(laby.getLesCases()[1][2].getAnimal() == m);
    }
    @Test
    void seDeplacerE4() {
        laby.getLesCases()[1][1].setContenu(new Marguerite());
        laby.getLesCases()[5][1].setContenu(new Rocher());
        laby.ajouterAnimal(m, 1, 1);
        m.manger();
        m.seDeplacer(m.getMouvementPossible(), "E");
        assertTrue(laby.getLesCases()[1][5].getAnimal() == m);
    }
    @Test
    void seDeplacerS5() {
        laby.getLesCases()[4][1].setContenu(new Rocher());
        laby.ajouterAnimal(m, 1, 1);
        m.manger();
        m.seDeplacer(m.getMouvementPossible(), "S");
        assertTrue(laby.getLesCases()[3][1].getAnimal() == m);

    }
    @Test
    void seDeplacerS6() {
        laby.getLesCases()[1][1].setContenu(new Marguerite());
        laby.ajouterAnimal(m, 1, 1);
        m.manger();
        m.seDeplacer(m.getMouvementPossible(), "S");
        assertTrue(laby.getLesCases()[5][1].getAnimal() == m);
    }
    @Test
    void seDeplacerS7() {
        laby.getLesCases()[1][1].setContenu(new Cactus());
        laby.ajouterAnimal(m, 1, 1);
        m.manger();
        m.seDeplacer(m.getMouvementPossible(), "S");

        assertTrue(laby.getLesCases()[2][1].getAnimal() == m);
    }
    @Test
    void seDeplacerS8() {
        laby.getLesCases()[1][1].setContenu(new Marguerite());
        laby.getLesCases()[5][1].setContenu(new Rocher());
        laby.ajouterAnimal(m, 1, 1);
        m.manger();
        m.seDeplacer(m.getMouvementPossible(), "S");
        assertTrue(laby.getLesCases()[4][1].getAnimal() == m);
    }
    @Test
    void seDeplacerN9() {

        laby.getLesCases()[1][1].setContenu(new Rocher());

        laby.ajouterAnimal(m, 4, 1);


        m.manger();
        m.seDeplacer(m.getMouvementPossible(), "N");

        assertTrue(laby.getLesCases()[2][1].getAnimal() == m);

    }
    @Test
    void seDeplacerN10() {
        laby.getLesCases()[5][1].setContenu(new Marguerite());
        laby.ajouterAnimal(m, 5, 1);

        m.manger();
        m.seDeplacer(m.getMouvementPossible(), "N");

        assertTrue(laby.getLesCases()[1][1].getAnimal() == m);
    }
    @Test
    void seDeplacerN11() {
        laby.getLesCases()[5][1].setContenu(new Cactus());
        laby.ajouterAnimal(m, 5, 1);
        m.manger();
        m.seDeplacer(1, "N");
        assertTrue(laby.getLesCases()[4][1].getAnimal() == m);
    }
    @Test
    void seDeplacerN12() {
        laby.getLesCases()[5][1].setContenu(new Marguerite());
        laby.getLesCases()[1][1].setContenu(new Rocher());
        laby.ajouterAnimal(m, 5, 1);
        m.manger();
        m.seDeplacer(m.getMouvementPossible(), "N");
        assertTrue(laby.getLesCases()[2][1].getAnimal() == m);
    }
    @Test
    void seDeplacerO13() {
        laby.ajouterAnimal(m, 1, 4);
        m.manger();
        m.seDeplacer(m.getMouvementPossible(), "O");
        assertTrue(laby.getLesCases()[1][2].getAnimal() == m);

    }
    @Test
    void seDeplacerO14() {
        laby.getLesCases()[1][5].setContenu(new Marguerite());
        laby.ajouterAnimal(m, 1, 5);
        m.manger();
        m.seDeplacer(m.getMouvementPossible(), "O");
        assertTrue(laby.getLesCases()[1][1].getAnimal() == m);
    }
    @Test
    void seDeplacerO15() {
        laby.getLesCases()[1][2].setContenu(new Cactus());
        laby.ajouterAnimal(m, 1, 2);
        m.manger();
        m.seDeplacer(m.getMouvementPossible(), "O");
        assertTrue(laby.getLesCases()[1][1].getAnimal() == m);
    }
    @Test
    void seDeplacerO16() {
        laby.getLesCases()[1][5].setContenu(new Marguerite());
        laby.getLesCases()[1][1].setContenu(new Rocher());
        laby.ajouterAnimal(m, 1, 5);
        m.manger();
        m.seDeplacer(m.getMouvementPossible(), "O");
        assertTrue(laby.getLesCases()[1][2].getAnimal() == m);
    }


}
