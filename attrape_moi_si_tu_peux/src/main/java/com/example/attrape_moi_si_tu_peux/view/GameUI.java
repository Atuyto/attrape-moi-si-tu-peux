package com.example.attrape_moi_si_tu_peux.view;

import com.example.attrape_moi_si_tu_peux.Labyrinthe;
import com.example.attrape_moi_si_tu_peux.controller.EventGameUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameUI extends Stage{

    private EventGameUI eventGameUI;
    private boolean edition;
    private Labyrinthe lab;
    private CaseFX[][] caseFX;


    public void GameUI(){
        lab                     = new Labyrinthe(10, 10);
        Group gpLab             = new Group();
        Group gpLeft            = new Group();
        Group gpRight           = new Group();
        caseFX                  = new CaseFX[lab.getX()][lab.getY()];
        VBox vboxtext           = new VBox();
        BorderPane pane         = new BorderPane();
        VBox vboxButton         = new VBox();
        edition                 = false;

        Text herbeManger        = new Text("Herbe mangé");
        Text cactusManger       = new Text("Cactus mangé");
        Text margueriteManger   = new Text("Marguerite Mangé");
        Text titleTop           = new Text("Attrrape moi si tu peux !! ");

        Button buttonEditer     = new Button("Editer labyrinthe");
        Button buttonPause      = new Button("Démarrer Simulation");
        Button buttonSave       = new Button("Sauvegarder labyrinthe");
        Button buttonRetour     = new Button("Retour");


        int x = 0;
        for (int i = 0 ; i < lab.getX() ; i++){
            int y = 0;
            for (int j = 0 ; j < lab.getY() ; j++){
                caseFX[i][j] = new CaseFX(lab.getLesCases()[i][j], x, y);
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

        buttonEditer.setId("Edition");

        vboxButton.getChildren().addAll(buttonEditer,buttonPause,buttonSave);
        vboxtext.getChildren().addAll(herbeManger, cactusManger, margueriteManger);
        vboxtext.setSpacing(25);
        gpLeft.getChildren().add(vboxtext);

        gpRight.getChildren().add(vboxButton);


        // Evenement des différents boutton

        buttonRetour.setOnMouseClicked(eventGameUI);
        buttonEditer.setOnMouseClicked(eventGameUI);


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

        pane.requestLayout();



        Scene sc = new Scene(pane, 1200,800);
        this.setScene(sc);
    }

    public void setEventGameUI(EventGameUI eventGameUI) {this.eventGameUI = eventGameUI; this.GameUI();}

    public void activerEdition(){
        this.setEdition();
        for (int i = 1; i < lab.getX()-1 ; i++) {
            for (int j = 1; j < lab.getY()-1; j++)  {
                CaseFX c = caseFX[i][j];
                c.getGp().setOnMousePressed(mouseEvent -> c.click());

            }
        }

    }
    public void desactiverEdition(){
        this.setEdition();
        for (int i = 1; i < lab.getX()-1 ; i++) {
            for (int j = 1; j < lab.getY()-1; j++) {
                CaseFX c = caseFX[i][j];
                c.getGp().setOnMousePressed(null);

            }
        }
    }

    public void setEdition() {
        this.edition = ! this.edition;
    }
    public boolean getEdition(){
        return this.edition;
    }
}
