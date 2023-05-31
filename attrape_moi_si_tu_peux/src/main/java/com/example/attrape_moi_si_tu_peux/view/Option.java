package com.example.attrape_moi_si_tu_peux.view;

import com.example.attrape_moi_si_tu_peux.controller.EventGameUI;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.function.BiPredicate;

public class Option extends Stage {
    private EventGameUI eventGameUI;
    private int xchoix;

    private int ychoix;
    public void Option() {

        ChoiceBox<Integer> cb        = new ChoiceBox<>();
        ChoiceBox<Integer> cb1       = new ChoiceBox<>();
        Group gp                    = new Group();
        CheckBox chb                = new CheckBox("Loup");
        CheckBox chb1               = new CheckBox("Mouton");
        Text titleTop               = new Text("Veuillez choisir les dimensions du labyrinthe");
        Text textRole               = new Text("Quels rôles voulez vous jouer ? (en construction)  ");
        Button saveOption           = new Button("Sauvegarder option");
        Button buttonImport           = new Button("Importer Labyrinthe");
        Text title                  = new Text("Option");
        Button buttonRetour         = new Button("Retour");
        BorderPane pane             = new BorderPane();

        HBox hBoxDimension          = new HBox();
        HBox hBoxPos                = new HBox();
        VBox vBoxMain               = new VBox();

        titleTop.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR,20));
        title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR,20));
        textRole.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR,20));
        saveOption.setFont(Font.font("Verdana",FontWeight.BOLD,FontPosture.REGULAR,20));
        buttonRetour.setFont(Font.font("Verdana",FontWeight.BOLD,FontPosture.REGULAR,20));
        buttonImport.setFont(Font.font("Verdana",FontWeight.BOLD,FontPosture.REGULAR,20));

        cb.getItems().addAll(10,11,12,13,14,15);
        cb1.getItems().addAll(10,11,12,13,14,15);

        vBoxMain.setSpacing(20);

        cb.setScaleX(1.5); cb.setScaleY(1.5);
        cb1.setScaleX(1.5); cb1.setScaleY(1.5);
        chb.setScaleX(1.5); chb.setScaleY(1.5);
        chb1.setScaleX(1.5); chb1.setScaleY(1.5);

        hBoxDimension.getChildren().addAll(cb, cb1);
        hBoxPos.getChildren().addAll(chb, chb1);
        vBoxMain.getChildren().addAll(titleTop, hBoxDimension, textRole, hBoxPos, saveOption, buttonImport);
        hBoxPos.setSpacing(400);
        hBoxDimension.setSpacing(400);
        gp.getChildren().add(vBoxMain);

        saveOption.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xchoix = cb.getValue();
                ychoix = cb1.getValue();
            }
        });
        pane.setTop(title);
        pane.setCenter(gp);
        pane.setBottom(buttonRetour);



        BorderPane.setAlignment(vBoxMain, Pos.CENTER);
        BorderPane.setAlignment(buttonRetour, Pos.BOTTOM_RIGHT);
        BorderPane.setAlignment(title, Pos.CENTER);


        buttonRetour.setOnMouseClicked(eventGameUI);
        buttonImport.setOnMouseClicked(eventGameUI);


        Scene scene = new Scene(pane, 1000, 800);
        this.setScene(scene);
        this.setTitle("Attrape moi si tu peux ");

    }

    public void setEventGameUI(EventGameUI eventGameUI) {
        this.eventGameUI = eventGameUI;
        this.Option();
    }

    public int getXchoix() {
        return xchoix;
    }

    public int getYchoix() {
        return ychoix;
    }
}
