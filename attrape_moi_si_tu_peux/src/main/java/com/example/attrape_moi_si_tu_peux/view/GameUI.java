package com.example.attrape_moi_si_tu_peux.view;

import com.example.attrape_moi_si_tu_peux.Labyrinthe;
import com.example.attrape_moi_si_tu_peux.controller.EventGameUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameUI extends Stage{

    private EventGameUI eventGameUI;

    public void GameUI(){
        Labyrinthe lab          = new Labyrinthe(10, 10);
        Group gpLab             = new Group();
        Group gpLeft            = new Group();
        Group gpRight           = new Group();
        CaseFX[][] caseFX       = new CaseFX[lab.getX()][lab.getY()];
        VBox vboxtext           = new VBox();
        BorderPane pane         = new BorderPane();
        VBox vboxButton         = new VBox();

        Text herbeManger        = new Text("Herbe mangé");
        Text cactusManger       = new Text("Cactus mangé");
        Text margueriteManger   = new Text("Marguerite Mangé");
        Text titleTop           = new Text("Attrrape moi si tu peux !! ");

        Button buttonEditer     = new Button("Editer labyrinthe");
        Button buttonPause      = new Button("Mettre pause");
        Button buttonSave       = new Button("Sauvegarder labyrinthe");
        Button buttonRetour     = new Button("Retour");


        int x = 0;
        for (int i = 0 ; i < lab.getX() ; i++){
            int y = 0;
            for (int j = 0 ; j < lab.getY() ; j++){
                caseFX[i][j] = new CaseFX(lab.getLesCases()[i][j], x, y );
                gpLab.getChildren().add(caseFX[i][j].getGp());
                y += 60;
            }
            x+= 60;
        }


        herbeManger.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        cactusManger.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        margueriteManger.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        titleTop.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        buttonEditer.setFont(Font.font("Verdana", 20 ));
        buttonPause.setFont(Font.font("Verdana", 20 ));
        buttonSave.setFont(Font.font("Verdana", 20 ));
        buttonRetour.setFont(Font.font("Verdana", 20 ));


        vboxButton.getChildren().addAll(buttonEditer,buttonPause,buttonSave);
        vboxtext.getChildren().addAll(herbeManger, cactusManger, margueriteManger);
        vboxtext.setSpacing(25);
        gpLeft.getChildren().add(vboxtext);

        gpRight.getChildren().add(vboxButton);



        // Evenement des différents boutton

        buttonRetour.setOnMouseClicked(eventGameUI);

        pane.setLeft(gpLeft);
        pane.setTop(titleTop);
        pane.setCenter(gpLab);
        pane.setRight(gpRight);
        pane.setBottom(buttonRetour);
        pane.setPadding(new Insets(20,50,20,20));
        BorderPane.setAlignment(titleTop, Pos.CENTER);
        BorderPane.setAlignment(buttonRetour,Pos.BOTTOM_RIGHT);
        BorderPane.setAlignment(gpLeft, Pos.CENTER);
        BorderPane.setAlignment(gpLab, Pos.CENTER);
        BorderPane.setAlignment(gpRight, Pos.CENTER);
        BorderPane.setMargin(gpLeft, new Insets(25));


        Scene sc = new Scene(pane, 1200,800);
        this.setScene(sc);
    }

    public void setEventGameUI(EventGameUI eventGameUI) {this.eventGameUI = eventGameUI; this.GameUI();}

}
