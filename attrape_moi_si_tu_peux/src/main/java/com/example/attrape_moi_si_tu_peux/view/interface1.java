package com.example.attrape_moi_si_tu_peux.view;


import javafx.application.Application;
import javafx.stage.Stage;



public class interface1 extends Application {
    @Override
    public void start(Stage stage) {

        Menu_demarrer menuDemarrer = new Menu_demarrer(stage);


        stage.setTitle("Attrape moi si tu peux !");
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
