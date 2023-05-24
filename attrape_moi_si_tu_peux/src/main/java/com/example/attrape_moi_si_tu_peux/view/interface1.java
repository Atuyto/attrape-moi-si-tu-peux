package com.example.attrape_moi_si_tu_peux.view;


import com.example.attrape_moi_si_tu_peux.controller.EventGameUI;
import javafx.application.Application;
import javafx.stage.Stage;



public class interface1 extends Application {
    private GameUI gameUI;
    private EventGameUI eventGameUI;

    private boolean simulation;
    @Override
    public void start(Stage stage) {

        Menu_demarrer menuDemarrer = new Menu_demarrer(stage);
        eventGameUI = menuDemarrer.getEventGameUI();
        gameUI = eventGameUI.getGameUI();




        stage.setTitle("Attrape moi si tu peux !");
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
