package com.example.attrape_moi_si_tu_peux.view;


import com.example.attrape_moi_si_tu_peux.controller.EventGameUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;



public class interface1 extends Application {
    private GameUI gameUI;
    Menu_demarrer menuDemarrer;
    private EventGameUI eventGameUI;

    private boolean simulation;

    private Timeline test;
    public interface1() {
    }

    @Override
    public void start(Stage stage) {

        menuDemarrer = new Menu_demarrer(stage);
        eventGameUI = menuDemarrer.getEventGameUI();
        gameUI = eventGameUI.getGameUI();



        stage.setTitle("Attrape moi si tu peux !");
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
