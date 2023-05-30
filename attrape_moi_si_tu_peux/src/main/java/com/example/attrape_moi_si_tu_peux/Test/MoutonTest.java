package com.example.attrape_moi_si_tu_peux.Test;

import com.example.attrape_moi_si_tu_peux.Model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoutonTest {
    protected Labyrinthe laby;
    Mouton m ;
    @BeforeEach
    void init() {
        laby = new Labyrinthe(8, 8);
        laby.genererGrille();
         m = new Mouton(laby);
    }
    @AfterEach
    void reset() {
        laby = new Labyrinthe(8, 8);
        laby.genererGrille();
    }
    @Test
    void manger() {
        laby.getLesCases()[1][1].setContenu(new Herbe());
        laby.ajouterAnimal(m, 1, 1);
        m.manger();
        assertTrue(m.getMouvementPossible() == 2 );

    }@Test
    void manger2() {
        laby.getLesCases()[1][1].setContenu(new Marguerite());
        laby.ajouterAnimal(m, 1, 1);
        m.manger();
        assertTrue(m.getMouvementPossible() == 4 );

    }
    @Test
    void manger3() {
        laby.getLesCases()[1][1].setContenu(new Cactus());
        laby.ajouterAnimal(m, 1, 1);
        m.manger();
        assertTrue(m.getMouvementPossible() == 1 );

    }
}