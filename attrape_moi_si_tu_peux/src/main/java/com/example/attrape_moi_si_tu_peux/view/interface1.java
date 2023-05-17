package com.example.attrape_moi_si_tu_peux.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class interface1 extends Application {
    @Override
    public void start(Stage stage) {

        Rectangle carre = new Rectangle(70,70);
        carre.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 3;");
        Group gp = new Group(carre);
        Scene sc = new Scene(gp, 500,500);

       ;
        stage.setTitle("attrape moi si tu peux !");
        stage.setScene(sc);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
