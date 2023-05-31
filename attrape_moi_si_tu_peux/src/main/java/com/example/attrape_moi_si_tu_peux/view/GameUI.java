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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameUI extends Stage{

    private final int x;
    private final int y;
    private EventGameUI eventGameUI;
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

    public GameUI(int xchoix, int ychoix) {
        this.x = xchoix;
        this.y = ychoix;
        this.lab = new Labyrinthe(this.x, this.y);
        GameUI();
    }
    public GameUI(){
        this.x = 10;
        this.y = 10;
        this.lab = new Labyrinthe(this.x, this.y);
        GameUI();
    }
    public GameUI(Labyrinthe lab){
        this.x = lab.getX();
        this.y = lab.getY();
        this.lab = lab;
        GameUI();
    }

    public void GameUI(){
        gpLab                   = new Group();
        gpLeft                  = new Group();
        gpRight                 = new Group();
        caseFX                  = new CaseFX[lab.getX()][lab.getY()];
        VBox vboxtext           = new VBox();
        pane                    = new BorderPane();
        VBox vboxButton         = new VBox();
        edition                 = false;
        nbsorti                 = 0;
        nbAnimaux               = this.lab.getLesAnimaux().size();

        Text herbeManger        = new Text("Herbe mangé");
        Text cactusManger       = new Text("Cactus mangé");
        Text margueriteManger   = new Text("Marguerite Mangé");

        Button buttonAddAnimauw = new Button("Ajouter animaux");
        Button buttonEditer     = new Button("Editer labyrinthe");
        Button buttonSimu      = new Button("Démarrer Simulation");
        Button buttonSave       = new Button("Sauvegarder labyrinthe");
        Button buttonGenererLab = new Button("Génération aléatoire");
        Button buttonRetour     = new Button("Retour");
        sc                      = new Scene(pane, 1200,800);


        herbeManger.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        cactusManger.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        margueriteManger.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        buttonAddAnimauw.setFont(Font.font("Verdana", 20 ));
        buttonEditer.setFont(Font.font("Verdana", 20 ));
        buttonSimu.setFont(Font.font("Verdana", 20 ));
        buttonGenererLab.setFont(Font.font("Verdana", 20 ));
        buttonSave.setFont(Font.font("Verdana", 20 ));
        buttonRetour.setFont(Font.font("Verdana", 20 ));

        buttonEditer.setId("Edition");
        buttonAddAnimauw.setId("Edition animal");

        vboxButton.getChildren().addAll(buttonGenererLab,buttonEditer, buttonAddAnimauw, buttonSimu,buttonSave);
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

    }


    public void setEventGameUI(EventGameUI eventGameUI) {
        this.eventGameUI = eventGameUI;
        this.GameUI();}

    public void afficherGrille() {
        int x = 0;
        for (int i = 0; i < lab.getX(); i++) {
            int y = 0;
            for (int j = 0; j < lab.getY(); j++) {
                if (i == 0 || j == 0 || i == this.lab.getY() - 1 || j == this.lab.getX() - 1) {
                    this.caseFX[i][j] = new CaseFX(this, this.lab.getLesCases()[i][j], x, y, true);

                }
                else {
                    this.caseFX[i][j] = new CaseFX(this, this.lab.getLesCases()[i][j], x, y);
                }

                if(this.lab.getLesCases()[i][j].getSortie()){
                    this.caseFX[i][j].setSortie();
                    this.nbsorti = 1;
                    this.afficherTitle();
                }
                this.gpLab.getChildren().add(this.caseFX[i][j].getGp());
                this.caseFX[i][j].afficherAnimal();

                y += this.caseFX[0][0].getScale();
            }
            x += this.caseFX[0][0].getScale();
        }
    }

    public void activerEdition(){
        this.setEdition();
        for (int i = 1; i < lab.getY()-1 ; i++) {
            for (int j = 1; j < lab.getX()-1; j++)  {
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
        for (int i = 1; i < lab.getY()-1 ; i++) {
            for (int j = 1; j < lab.getX()-1; j++)  {
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

        Timeline boucle = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                ArrayList<String> orient = new ArrayList<>();
                orient.add("N");orient.add("S");orient.add("O"); orient.add("E");
                ArrayList<String> orient2 = new ArrayList<>();
                orient2.add("N");orient2.add("S");orient2.add("O"); orient2.add("E");
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
                String choiceL;
                String choiceM;
                int[] newPosL = new int[2];
                int[] newPosM = new int[2];

                if (lab.getNb_tour() % 2 == 0) {
                    choiceL = orient.get(random.nextInt(orient.size()));
                    newPosL = caseFX[loup[0]][loup[1]].getLaCase().getAnimal().seDeplacer(lab.getLesCases()[loup[0]][loup[1]].getAnimal().getMouvementPossible(), choiceL);
                    if(!(Arrays.equals(newPosL, loup))) {
                        caseFX[loup[0]][loup[1]].deleteAnimal();
                        caseFX[newPosL[0]][newPosL[1]].afficherAnimal();
                        lab.setNb_tour(1);
                    }
                    else {
                        orient.remove(orient.lastIndexOf(choiceL));
                    }
                } else {
                    choiceM = orient2.get(random.nextInt(orient.size()));
                    newPosM = caseFX[mouton[0]][mouton[1]].getLaCase().getAnimal().seDeplacer(lab.getLesCases()[mouton[0]][mouton[1]].getAnimal().getMouvementPossible(), choiceM);
                    if(!(Arrays.equals(newPosM, mouton))){
                        caseFX[mouton[0]][mouton[1]].deleteAnimal();
                        caseFX[newPosM[0]][newPosM[1]].afficherAnimal();
                        caseFX[newPosM[0]][newPosM[1]].manger();
                        lab.setNb_tour(1);
                    }
                    else {
                        orient2.remove(orient2.lastIndexOf(choiceM));
                    }

                }

                for(int i = 1; i<lab.getY()-1; i++){
                    for(int j = 1; j<lab.getX()-1; j++){
                        if(lab.getLesCases()[i][j].isEstVide()){
                            caseFX[i][j].repousser();
                        }
                    }
                }

            }
        }));

        boucle.setCycleCount(Timeline.INDEFINITE);
        boucle.play();
    }

    public void messageSetSortie(){
        Text textmsg = new Text("Veuillez séléctionner une case de sortie");
        textmsg.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        textmsg.setFill(Color.RED);
        BorderPane.setAlignment(textmsg, Pos.CENTER);
        this.pane.setTop(textmsg);

    }

    public void afficherTitle(){
        Text titleTop = new Text("Attrape moi si tu peux !! ");
        titleTop.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        BorderPane.setAlignment(titleTop, Pos.CENTER);
        this.pane.setTop(titleTop);
    }

    public void genererLab(){
        this.lab.genererGrilleAleatoire();
        this.afficherGrille();
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
