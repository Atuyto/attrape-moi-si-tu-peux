package com.example.attrape_moi_si_tu_peux.controller;

import com.example.attrape_moi_si_tu_peux.Animal;
import com.example.attrape_moi_si_tu_peux.view.GameUI;
import com.example.attrape_moi_si_tu_peux.view.Menu_demarrer;
import com.example.attrape_moi_si_tu_peux.view.Option;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Objects;

public class EventGameUI implements EventHandler {
    private Menu_demarrer menu;
    private GameUI gameUI;

    private Option option;

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
                gameUI.activerEdition();
            } else {
                gameUI.activerEdition();
                ((Button) event.getSource()).setText("Arreter edition");
            }
        }
        if(Objects.equals(((Button) event.getSource()).getId(), "Edition animal")){
            if (gameUI.getLab().getLesAnimaux().size() != 2) {
                ((Button) event.getSource()).setText("Enregistrer emplacement");
                gameUI.activerAddAnimal();
            }
            else {
                gameUI.activerAddAnimal();
                ((Button) event.getSource()).setText("Ajouter animaux");
            }
        }
        if((event.getSource() instanceof Button)&&(event.getSource().toString().contains("Génération aléatoire"))){
            this.gameUI.getLab().getLesAnimaux().clear();
            this.gameUI.genererLab();
        }

        if((event.getSource() instanceof Button)&&(event.getSource().toString().contains("Démarrer Simulation"))){
            this.gameUI.simulation();
        }
        if((event.getSource() instanceof Button)&&(event.getSource().toString().contains("Options"))){
            option = new Option();
            option.show();
            menu.open();
        }

    }



    public GameUI getGameUI() {
        return gameUI;
    }
}
