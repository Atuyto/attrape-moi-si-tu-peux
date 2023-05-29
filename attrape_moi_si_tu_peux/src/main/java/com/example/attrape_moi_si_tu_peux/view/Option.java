package com.example.attrape_moi_si_tu_peux.view;

import com.example.attrape_moi_si_tu_peux.controller.EventGameUI;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    public void Option() {

        ComboBox<Integer> cb        = new ComboBox<>();
        ComboBox<Integer> cb1       = new ComboBox<>();
        Group gp                    = new Group();
        CheckBox chb                = new CheckBox("Loup");
        CheckBox chb1               = new CheckBox("Mouton");
        Text titleTop               = new Text("Veuillez choisir les dimensions du labyrinthe (en construction)");
        Text textRole               = new Text("Quels rôles voulez vous jouer ? (en construction)  ");
        Text title                  = new Text("Option");
        Button buttonimport         = new Button("Importer labyrinthe");
        Button buttonRetour         = new Button("Retour");
        BorderPane pane             = new BorderPane();

        HBox hBoxDimension          = new HBox();
        HBox hBoxPos                = new HBox();
        VBox vBoxMain               = new VBox();

        titleTop.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR,20));
        title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR,20));
        textRole.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR,20));
        buttonimport.setFont(Font.font("Verdana",FontWeight.BOLD,FontPosture.REGULAR,20));
        buttonRetour.setFont(Font.font("Verdana",FontWeight.BOLD,FontPosture.REGULAR,20));

        cb.getItems().addAll(10,11,12,13,14,15);
        cb1.getItems().addAll(10,11,12,13,14,15);

        vBoxMain.setSpacing(20);

        cb.setScaleX(1.5); cb.setScaleY(1.5);
        cb1.setScaleX(1.5); cb1.setScaleY(1.5);
        chb.setScaleX(1.5); chb.setScaleY(1.5);
        chb1.setScaleX(1.5); chb1.setScaleY(1.5);

        hBoxDimension.getChildren().addAll(cb, cb1);
        hBoxPos.getChildren().addAll(chb, chb1);
        vBoxMain.getChildren().addAll(titleTop, hBoxDimension, textRole, hBoxPos);
        hBoxPos.setSpacing(400);
        hBoxDimension.setSpacing(400);
        gp.getChildren().add(vBoxMain);


        pane.setTop(title);
        pane.setCenter(gp);
        pane.setBottom(buttonRetour);



        BorderPane.setAlignment(vBoxMain, Pos.CENTER);
        BorderPane.setAlignment(buttonRetour, Pos.BOTTOM_RIGHT);
        BorderPane.setAlignment(title, Pos.CENTER);


        buttonRetour.setOnMouseClicked(eventGameUI);


        Scene scene = new Scene(pane, 1000, 800);
        this.setScene(scene);
        this.setTitle("Attrape moi si tu peux ");

        }

    public void setEventGameUI(EventGameUI eventGameUI) {
        this.eventGameUI = eventGameUI;
        this.Option();
    }
}

