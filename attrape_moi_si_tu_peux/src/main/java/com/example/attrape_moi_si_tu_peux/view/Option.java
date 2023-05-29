package com.example.attrape_moi_si_tu_peux.view;

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
    public Option() {

        ComboBox<Integer> cb = new ComboBox<>();
        ComboBox<Integer> cb1 = new ComboBox<>();
        Group gp = new Group();
        CheckBox chb = new CheckBox();
        CheckBox chb1 = new CheckBox();
        Text titleTop = new Text("Veuillez choisir les dimensions du labyrinthe");
        Text titleMiddle = new Text("Quels rôles voulez vous jouer ? ");
        Button b1 = new Button("Importer labyrinthe");
        BorderPane p = new BorderPane();
        cb.getItems().addAll(10,11,12,13,14,15);
        cb1.getItems().addAll(10,11,12,13,14,15);
        gp.getChildren().addAll(cb,cb1,titleTop,titleMiddle,b1,chb,chb1);
        p.setBottom(chb);
        p.setBottom(chb1);
        p.setCenter(gp);
        p.setTop(titleTop);
        p.setCenter(titleMiddle);
        p.setBottom(b1);
        BorderPane.setAlignment(chb,Pos.CENTER);
        BorderPane.setAlignment(chb1,Pos.CENTER);
        BorderPane.setAlignment(titleTop,Pos.CENTER);
        BorderPane.setAlignment(titleMiddle,Pos.CENTER);
        BorderPane.setAlignment(b1,Pos.CENTER);
        titleTop.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR,20));
        titleMiddle.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR,20));
        b1.setFont(Font.font("Verdana",FontWeight.BOLD,FontPosture.REGULAR,20));
        b1.setTranslateY(-100);
        p.setRight(cb);
        BorderPane.setAlignment(cb,Pos.CENTER);
        p.setLeft(cb1);
        BorderPane.setAlignment(cb1,Pos.CENTER);
        BorderPane.setMargin(cb1,new Insets(150));
        BorderPane.setMargin(titleTop, new Insets(100));
        cb1.setTranslateY(-225);
        cb.setTranslateY(-225);
        cb.setTranslateX(-150);
        titleMiddle.setTranslateX(-150);
        titleMiddle.setTranslateY(-150);
        BorderPane.setAlignment(chb,Pos.BOTTOM_RIGHT);
        BorderPane.setAlignment(chb1,Pos.BOTTOM_LEFT);


        Scene scene = new Scene(p, 1000, 800);
        this.setScene(scene);
        this.setTitle("Attrape moi si tu peux ");

        }

}

