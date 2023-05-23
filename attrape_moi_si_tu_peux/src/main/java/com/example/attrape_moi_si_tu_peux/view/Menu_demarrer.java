package com.example.attrape_moi_si_tu_peux.view;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu_demarrer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage0) {
        Group gp = new Group();
        Scene scene = new Scene (gp, 1000, 800);
        stage0.setTitle("Attrape moi si tu peux !");
        Font f = new Font("Arial", 28);
        Button b0 = new Button( "Jouer");
        Button b1 = new Button("Options");
        b0.setFont(f); b1.setFont(f);
        b0.setLayoutX(400); b1.setLayoutX(400);
        b0.setLayoutY(275); b1.setLayoutY(475);
        b0.setPrefSize(200, 100); b1.setPrefSize(200, 100);
        gp.getChildren().add(b0); gp.getChildren().add(b1);
        b0.setOnMouseClicked((new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                stage0.close();
                interface1 i1 = new interface1();
                i1.start(stage0);
            }
        }));
        stage0.setScene(scene);
        stage0.show();
    }
}
