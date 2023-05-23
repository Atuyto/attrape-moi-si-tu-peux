package com.example.attrape_moi_si_tu_peux.controller;

import com.example.attrape_moi_si_tu_peux.view.GameUI;
import com.example.attrape_moi_si_tu_peux.view.Menu_demarrer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Objects;

public class EventGameUI implements EventHandler {
    private Menu_demarrer menu;
    private GameUI gameUI;

    public EventGameUI(Menu_demarrer menu) {
        this.menu = menu;
    }

    @Override
    public void handle(Event event) {
        if((event.getSource() instanceof Button)&&(event.getSource().toString().contains("Jouer"))){
            gameUI = new GameUI();
            gameUI.setEventGameUI(this);
            gameUI.show();
            menu.close();
        }
        if((event.getSource() instanceof Button)&&(event.getSource().toString().contains("Retour"))){
            gameUI.close();
            menu.open();
        }
        if(Objects.equals(((Button) event.getSource()).getId(), "Edition")){
            if (gameUI.getEdition()) {
                ((Button) event.getSource()).setText("Editer labyrinthe");
                gameUI.desactiverEdition();
            } else {
                gameUI.activerEdition();
                ((Button) event.getSource()).setText("Arreter edition");

            }

        }

    }
}
