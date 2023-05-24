package com.example.attrape_moi_si_tu_peux.view;

import com.example.attrape_moi_si_tu_peux.controller.EventGameUI;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu_demarrer {
    private GameUI gameUI;

    private EventGameUI eventGameUI;
    private Stage stagelocal;



    public Menu_demarrer(Stage stage){
        VBox vBox               = new VBox();
        BorderPane pane         = new BorderPane();
        Button buttonPlay       = new Button( "Jouer");
        Button buttonOption     = new Button("Options");
        Button buttonQuit       = new Button("Quitter");
        Scene scene             = new Scene (pane, 1000, 800);

        buttonPlay.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        buttonOption.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        buttonQuit.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));

        vBox.getChildren().addAll(buttonPlay, buttonOption, buttonQuit);
        pane.setCenter(vBox);

        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        BorderPane.setAlignment(vBox, Pos.CENTER);



        eventGameUI = new EventGameUI(this);
        buttonPlay.setOnMouseClicked(eventGameUI);
        buttonQuit.setOnMouseClicked(MouseEvent -> stage.close());

        stage.setScene(scene);
        stagelocal=stage;
    }

    public void close(){
        stagelocal.close();
    }

    public void open(){
        stagelocal.show();
    }

    public EventGameUI getEventGameUI() {
        return eventGameUI;
    }

}


