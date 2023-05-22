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

public class Menu_demarrer {

    public Menu_demarrer(Stage stage){
            Group gp = new Group();
            Scene scene = new Scene (gp, 1000, 800);

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
                    GameUI gameUI = new GameUI(stage);
                }
            }));
            stage.setScene(scene);
        }
}


