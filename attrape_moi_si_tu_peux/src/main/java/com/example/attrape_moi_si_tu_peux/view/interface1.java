package com.example.attrape_moi_si_tu_peux.view;

import com.example.attrape_moi_si_tu_peux.Case;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import com.example.attrape_moi_si_tu_peux.Labyrinthe;
import com.example.attrape_moi_si_tu_peux.Rocher;


public class interface1 extends Application {
    @Override
    public void start(Stage stage) {

        Menu_demarrer menuDemarrer = new Menu_demarrer(stage);



        stage.setTitle("attrape moi si tu peux !");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
