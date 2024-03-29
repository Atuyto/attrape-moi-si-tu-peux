package com.example.attrape_moi_si_tu_peux.view;

import com.example.attrape_moi_si_tu_peux.Model.*;
import com.example.attrape_moi_si_tu_peux.controller.EventGameUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

import java.util.*;

public class GameUI extends Stage{

    private EventGameUI eventGameUI;
    private final int x;
    private final int y;
    private boolean edition;
    private final Labyrinthe lab;
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

    private Text herbeManger;
    private Text cactusManger;
    private Text margueriteManger;
    private List<int[]> chemin;
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
        this.sc                     = new Scene(pane);
        this.setFullScreen(true);

        VBox vboxtext               = new VBox();
        VBox vboxButton             = new VBox();

        herbeManger            = new Text("Herbe mangé " + 0);
        cactusManger           = new Text("Cactus mangé "+ 0);
        margueriteManger       = new Text("Marguerite Mangé " + 0);

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

        buttonRetour.setPadding(new Insets(10, 10, 10 ,10));
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

        for(int i = 0; i<lab.getX();i++){
            for(int j = 0; j<lab.getY(); j++){
                if(lab.getLesCases()[i][j].getSortie()){
                    this.nbsorti ++;
                    break;
                }
            }
        }
        if(nbsorti==1){
            afficherTitle();
        }


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
                //int scale = this.caseFX[i][j].getScale();

                if(i == 0 || j == 0 || i == this.x -1 || j == this.y -1){
                    this.caseFX[i][j] = new CaseFX(this, this.lab.getLesCases()[i][j],40,40, true);
                }
                else {
                    this.caseFX[i][j] = new CaseFX(this, this.lab.getLesCases()[i][j], 40,40);
                    }
                hBox.getChildren().add(this.caseFX[i][j].getGp());
                }
            vBox.getChildren().add(hBox);
            }

        double scalePlateaux = ((double) Math.max(this.lab.getX(), this.lab.getY()) / Math.min(this.lab.getX(), this.lab.getY()));
        double v = scalePlateaux > 1 ? (1 -(scalePlateaux-1))*1.4 :  scalePlateaux;

        vBox.setScaleX(v);
        vBox.setScaleY(v);

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
        List<String> orient = new ArrayList<>();
        orient.add("N");orient.add("S");orient.add("O");orient.add("E");
        final boolean[] bouger = {false};
        final Queue<int[]>[] fileCheminL = new Queue[]{new ArrayDeque<>()};
        final Queue<int[]>[] fileChemin = new Queue[]{new ArrayDeque<>()};




        this.boucle = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {

                Random random = new Random();
                Loup l = (Loup) lab.getLesAnimaux().get(1);
                Mouton m = (Mouton) lab.getLesAnimaux().get(0);



                margueriteManger.setText(" Marguerite Mangé " + m.getNbMargurite());
                herbeManger.setText("Herbe mangé " + m.getNbHerbe());
                cactusManger.setText("Cactus mangé " + m.getNbCactus());


                String choice = orient.get(random.nextInt(orient.size()));

                //condition loup
                if (lab.getNb_tour() % 2 == 0) {
                    int[] oldPos = lab.getPosition(l);
                    if(!l.getEnChasse()){
                        if(l.reperer(lab.getPosition(l), lab.getPosition(m))){
                            l.setEnChasse(true);
                        }
                        else {
                            l.seDeplacer(l.getMouvementPossible(), choice);
                            caseFX[oldPos[0]][oldPos[1]].deleteAnimal();
                            caseFX[lab.getPosition(l)[0]][lab.getPosition(l)[1]].afficherAnimal();
                            caseFX[lab.getPosition(l)[0]][lab.getPosition(l)[1]].mettreAjour();
                        }
                    }
                    else {
                        if(!l.reperer(lab.getPosition(l), lab.getPosition(m))){
                            l.setEnChasse(false);
                        }
                    }
                    if(l.getEnChasse()) {
                        oldPos = lab.getPosition(l);
                        if (!(lab.getPosition(l)[0] == lab.getPosition(m)[0] && lab.getPosition(l)[1] == lab.getPosition(m)[1])) {
                            if(!fileCheminL[0].isEmpty()){
                                fileCheminL[0].clear();
                            }
                            int[] depart = lab.getPosition(l);
                            int[] arriver = lab.getPosition(m);
                            Astar astar = new Astar(lab, depart, arriver);
                            chemin = astar.astarRes();
                            int i = 0;
                            for (int[] point : chemin) {
                                if (i % 3 == 0) {
                                    fileCheminL[0].offer(point);
                                }
                                i++;
                            }
                            if (!fileCheminL[0].contains(chemin.get(chemin.size() - 1))) {
                                fileCheminL[0].offer(chemin.get(chemin.size() - 1));
                            }
                            int[] curent = fileCheminL[0].poll();
                            assert curent != null;
                            if(chemin.size() -1 <= 2){
                                l.chasser(chemin.get(chemin.size() -1 ), oldPos);
                                caseFX[oldPos[0]][oldPos[1]].deleteAnimal();
                                caseFX[lab.getPosition(l)[0]][lab.getPosition(l)[1]].afficherAnimal();
                            }

                            if(lab.getLesCases()[curent[0]][curent[1]].getAnimal() instanceof Mouton || !lab.getLesAnimaux().contains(m) ){
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setContentText("Le loup à gagner en  ".concat(String.valueOf(lab.getNb_tour())).concat(lab.getNb_tour() > 1 ?  " tours" : "tour").concat(" !"));
                                alert.setHeaderText("Défaite !!");
                                alert.show();
                                boucle.stop();

                            }
                            else {
                                l.chasser(curent, oldPos);
                                caseFX[oldPos[0]][oldPos[1]].deleteAnimal();
                                caseFX[lab.getPosition(l)[0]][lab.getPosition(l)[1]].afficherAnimal();
                                bouger[0] = true;
                            }

                        }
                    }
                    if (oldPos[0] == lab.getPosition(l)[0] && oldPos[1] == lab.getPosition(l)[1] && !l.getEnChasse()) {
                        bouger[0] = false;
                        orient.remove(choice);
                    } else bouger[0] = true;
                }

                //Condition mouton
                else {
                    int[] oldPos = lab.getPosition(m);
                    if (!m.isEnFuite()) {
                        if (m.reperer(lab.getPosition(l), lab.getPosition(m))){
                            m.setEnFuite(true);
                        }
                        else {
                            m.seDeplacer(m.getMouvementPossible(), choice);
                            m.manger();
                            caseFX[oldPos[0]][oldPos[1]].deleteAnimal();
                            caseFX[lab.getPosition(m)[0]][lab.getPosition(m)[1]].afficherAnimal();
                            caseFX[lab.getPosition(m)[0]][lab.getPosition(m)[1]].mettreAjour();
                        }
                    }
                    if(m.isEnFuite()) {
                        if (!(lab.getSortie()[0] == lab.getPosition(m)[0] && lab.getSortie()[1] == lab.getPosition(m)[1])) {
                            if(!fileChemin[0].isEmpty()){
                                fileChemin[0].clear();
                            }
                            int[] depart = lab.getPosition(m);
                            int[] arriver = lab.getSortie();
                            Astar astar = new Astar(lab, depart, arriver);
                            chemin = astar.astarRes();

                            int i = 0;
                            for (int[] point : chemin) {
                                if(i%2 ==0) {
                                    fileChemin[0].offer(point);
                                }
                                i++;
                            }
                            if(!fileChemin[0].contains(chemin.get(chemin.size() - 1))){
                                fileChemin[0].offer(chemin.get(chemin.size() - 1));
                            }
                            m.fuit(Objects.requireNonNull(fileChemin[0].poll()),oldPos);
                            caseFX[oldPos[0]][oldPos[1]].deleteAnimal();
                            caseFX[lab.getPosition(m)[0]][lab.getPosition(m)[1]].afficherAnimal();
                            caseFX[lab.getPosition(m)[0]][lab.getPosition(m)[1]].mettreAjour();
                            bouger[0] = true;
                        }
                        else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Le Mouton à gagner en  ".concat(String.valueOf(lab.getNb_tour())).concat(lab.getNb_tour() > 1 ?  " tours" : "tour").concat(" !"));
                            alert.setHeaderText("Victoire !!");
                            alert.show();
                            boucle.stop();
                        }
                    }

                    if (oldPos[0] == lab.getPosition(m)[0] && oldPos[1] == lab.getPosition(m)[1]) {
                        bouger[0] = false;
                        orient.remove(choice);
                    } else bouger[0] = true;
                }

                for(int i = 0; i<x; i++) {
                    for (int j = 0; j < y; j++) {
                        if (lab.getLesCases()[i][j].getContenu() == null) {
                            caseFX[i][j].repousser();
                        }
                    }
                }
                if(bouger[0]){
                    lab.setNb_tour(1);
                    orient.add("N");orient.add("S");orient.add("O");orient.add("E");

                }
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
        Text titleTop = new Text("Attrape moi si tu peux !! ");
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
