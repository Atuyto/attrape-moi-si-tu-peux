package com.example.attrape_moi_si_tu_peux.view;

import com.example.attrape_moi_si_tu_peux.Model.Animal;
import com.example.attrape_moi_si_tu_peux.Model.Labyrinthe;
import com.example.attrape_moi_si_tu_peux.Model.Loup;
import com.example.attrape_moi_si_tu_peux.Model.Mouton;
import com.example.attrape_moi_si_tu_peux.controller.EventGameUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class GameUI extends Stage{

    private EventGameUI eventGameUI;
    private int x;
    private int y;
    private boolean edition;
    private Labyrinthe lab;
    private CaseFX[][] caseFX;
    private Group gpLab;
    private Group gpLeft;
    private Group gpRight;
    private BorderPane pane;

    private int nbsorti;
    private Scene sc;

    private int nbAnimaux;
    private VBox vBox;
    private Timeline boucle;

    private boolean running;
    public GameUI(Labyrinthe lab) {
        this.lab    = lab;
        this.x = this.lab.getX();
        this.y = this.lab.getY();
        this.running = false;
    }

    public GameUI(){
        this.x = 10;
        this.y = 10;
        lab = new Labyrinthe(this.x, this.y);
        this.running = false;
    }

    public GameUI(int xchoix, int ychoix) {
        this.x = xchoix;
        this.y = ychoix;
        this.lab = new Labyrinthe(this.x, this.y);
        this.running = false;
    }

    public void GameUI(){
        this.gpLab                  = new Group();
        this.gpLeft                 = new Group();
        this.gpRight                = new Group();
        this.caseFX                 = new CaseFX[this.x][this.y];
        this.pane                   = new BorderPane();
        this.edition                = false;
        this.nbsorti                = 0;
        this.nbAnimaux              = this.lab.getLesAnimaux().size();
        this.vBox                   = new VBox();
        this.sc                     = new Scene(pane, 1300,900);

        VBox vboxtext               = new VBox();
        VBox vboxButton             = new VBox();

        Text herbeManger            = new Text("Herbe mangé");
        Text cactusManger           = new Text("Cactus mangé");
        Text margueriteManger       = new Text("Marguerite Mangé");

        Button buttonAddAnimauw     = new Button("Ajouter animaux");
        Button buttonEditer         = new Button("Editer labyrinthe");
        Button buttonSimu           = new Button("Démarrer Simulation");
        Button buttonSave           = new Button("Sauvegarder labyrinthe");
        Button buttonGenererLab     = new Button("Génération aléatoire");
        Button buttonRetour         = new Button("Retour");




        herbeManger.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        cactusManger.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        margueriteManger.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        buttonAddAnimauw.setFont(Font.font("Verdana", 20 ));
        buttonEditer.setFont(Font.font("Verdana", 20 ));
        buttonSimu.setFont(Font.font("Verdana", 20 ));
        buttonGenererLab.setFont(Font.font("Verdana", 20 ));
        buttonSave.setFont(Font.font("Verdana", 20 ));
        buttonRetour.setFont(Font.font("Verdana", 20 ));

        buttonEditer.setId("bEdition");
        buttonAddAnimauw.setId("bEditionAnimal");
        buttonSave.setId("bSauvegarder");
        buttonSimu.setId("bSimulation");
        buttonRetour.setId("bRetour");
        buttonGenererLab.setId("bGeneLab");

        vboxButton.getChildren().addAll(buttonGenererLab, buttonAddAnimauw,buttonEditer,buttonSimu,buttonSave);
        vboxButton.setSpacing(15);
        vboxtext.getChildren().addAll(herbeManger, cactusManger, margueriteManger);
        vboxtext.setSpacing(25);
        gpLeft.getChildren().add(vboxtext);

        gpRight.getChildren().add(vboxButton);


        // Evenement des différents boutton

        buttonRetour.setOnMouseClicked(eventGameUI);
        buttonEditer.setOnMouseClicked(eventGameUI);
        buttonAddAnimauw.setOnMouseClicked(eventGameUI);
        buttonGenererLab.setOnMouseClicked(eventGameUI);
        buttonSimu.setOnMouseClicked(eventGameUI);
        buttonSave.setOnMouseClicked(eventGameUI);



        pane.setLeft(gpLeft);

        pane.setCenter(gpLab);
        pane.setRight(gpRight);
        pane.setBottom(buttonRetour);
        pane.setPadding(new Insets(20,50,20,20));
        BorderPane.setAlignment(buttonRetour,Pos.BOTTOM_RIGHT);
        BorderPane.setAlignment(gpLeft, Pos.CENTER);
        BorderPane.setAlignment(gpLab, Pos.CENTER);
        BorderPane.setAlignment(gpRight, Pos.CENTER);
        BorderPane.setMargin(gpLeft, new Insets(25));
        messageSetSortie();


        this.setScene(sc);

        this.afficherGrille();
        this.gpLab.getChildren().add(this.vBox);

    }

    public void setEventGameUI(EventGameUI eventGameUI) {
        this.eventGameUI = eventGameUI;
        this.GameUI();}

    public void afficherGrille(){
        CaseFX caseFX;
        // Parcourir le labyrinthe et dessiner les cellules correspondantes
        for (int i = 0; i < this.x; i++) {
            HBox hBox = new HBox();
            for (int j = 0; j < this.y; j++) {
                if(i == 0 || j == 0 || i == this.x -1 || j == this.y -1){
                    this.caseFX[i][j] = new CaseFX(this, this.lab.getLesCases()[i][j],20,20, true);
                }
                else {
                    this.caseFX[i][j] = new CaseFX(this, this.lab.getLesCases()[i][j], 20,20);
                    }
                hBox.getChildren().add(this.caseFX[i][j].getGp());
                }
            vBox.getChildren().add(hBox);
            }
        }




    public void activerEdition(){
        this.setEdition();
        for (int i = 1; i < lab.getX()-1 ; i++) {
            for (int j = 1; j < lab.getY()-1; j++)  {
                CaseFX c = caseFX[i][j];
                if(this.edition){
                    c.getGp().setOnMouseClicked(mouseEvent -> c.setElement());
                }
                else {
                    c.getGp().setOnMouseClicked(null);
                }
            }
        }
    }

    public void activerAddAnimal(){
        for (int i = 1; i < lab.getX()-1 ; i++) {
            for (int j = 1; j < lab.getY()-1; j++)  {
                CaseFX c = caseFX[i][j];
                if(this.lab.getLesAnimaux().size() != 2 ){
                    c.getGp().setOnMouseClicked(mouseEvent -> c.setAnimaux());
                }
                else {
                    c.getGp().setOnMouseClicked(null);
                }
            }
        }
    }

    public void simulation(){

        this.boucle = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String[] orient = new String[]{"N", "E", "S", "O"};
                Random random = new Random();
                int[] loup = new int[2];
                int[] mouton = new int[2];
                for (Animal a : lab.getLesAnimaux()) {
                    if (a instanceof Mouton) {
                        mouton[0] = lab.getPosition(a)[0];
                        mouton[1] = lab.getPosition(a)[1];
                    }
                    if (a instanceof Loup) {
                        loup[0] = lab.getPosition(a)[0];
                        loup[1] = lab.getPosition(a)[1];
                    }
                }
                if (lab.getNb_tour() % 2 == 0) {
                    String choice = orient[random.nextInt(orient.length)];
                    int[] newPosL = caseFX[loup[0]][loup[1]].getLaCase().getAnimal().seDeplacer(lab.getLesCases()[mouton[0]][mouton[1]].getAnimal().getMouvementPossible(), choice);
                    caseFX[loup[0]][loup[1]].deleteAnimal();
                    caseFX[newPosL[0]][newPosL[1]].afficherAnimal();
                } else {
                    String choice = orient[random.nextInt(orient.length)];
                    int[] newPosM = caseFX[mouton[0]][mouton[1]].getLaCase().getAnimal().seDeplacer(lab.getLesCases()[mouton[0]][mouton[1]].getAnimal().getMouvementPossible(), choice);
                    caseFX[mouton[0]][mouton[1]].deleteAnimal();
                    caseFX[newPosM[0]][newPosM[1]].afficherAnimal();
                    caseFX[newPosM[0]][newPosM[1]].manger();

                }
                for(int i = 0; i<x; i++){
                    for(int j = 0; j<y; j++){
                        if(lab.getLesCases()[i][j].getContenu() == null){
                            caseFX[i][j].repousser();
                        }
                    }
                }
                lab.setNb_tour(1);
            }
        }));
        this.boucle.setCycleCount(Timeline.INDEFINITE);
        this.boucle.play();
    }

    public void pause(){
        this.boucle.pause();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    public void messageSetSortie(){
        Text textmsg = new Text("Veuillez séléctionner une case de sortie");
        textmsg.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        textmsg.setFill(Color.RED);
        BorderPane.setAlignment(textmsg, Pos.CENTER);
        this.pane.setTop(textmsg);

    }

    public void afficherTitle(){
        Text titleTop = new Text("Attrrape moi si tu peux !! ");
        titleTop.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        BorderPane.setAlignment(titleTop, Pos.CENTER);
        this.pane.setTop(titleTop);
    }

    public void genererLab(){
        this.lab.genererGrilleAleatoire();
        for (int i = 1; i < lab.getX()-1 ; i++) {
            for (int j = 1; j < lab.getY() - 1; j++) {
                this.caseFX[i][j].mettreAjour();
            }
        }
    }

    public void setEdition() {
        this.edition = ! this.edition;
    }

    public int getNbsorti() {
        return nbsorti;
    }

    public void setNbsorti(int nbsorti) {
        this.nbsorti = nbsorti;
    }

    public boolean getEdition(){
        return this.edition;
    }

    public Labyrinthe getLab() {
        return lab;
    }

    public int getNbAnimaux() {
        return nbAnimaux;
    }

    public void setNbAnimaux(int nbAnimaux) {
        this.nbAnimaux += nbAnimaux;
    }
}
