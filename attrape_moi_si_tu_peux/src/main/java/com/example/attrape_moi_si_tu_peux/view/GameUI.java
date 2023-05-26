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
import javafx.scene.paint.Color;
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
    private Group gpLab;
    private Group gpLeft;
    private Group gpRight;
    private BorderPane pane;

    private int nbsorti;
    private Scene sc;

    public void GameUI(){
        lab                     = new Labyrinthe(15, 15);
        gpLab                   = new Group();
        gpLeft                  = new Group();
        gpRight                 = new Group();
        caseFX                  = new CaseFX[lab.getX()][lab.getY()];
        VBox vboxtext           = new VBox();
        pane                    = new BorderPane();
        VBox vboxButton         = new VBox();
        edition                 = false;
        nbsorti                 = 0;

        Text herbeManger        = new Text("Herbe mangé");
        Text cactusManger       = new Text("Cactus mangé");
        Text margueriteManger   = new Text("Marguerite Mangé");


        Button buttonEditer     = new Button("Editer labyrinthe");
        Button buttonPause      = new Button("Démarrer Simulation");
        Button buttonSave       = new Button("Sauvegarder labyrinthe");
        Button buttonGenererLab = new Button("Génération aléatoire");
        Button buttonRetour     = new Button("Retour");
        sc                      = new Scene(pane, 1400,900);


        herbeManger.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        cactusManger.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        margueriteManger.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));


        buttonEditer.setFont(Font.font("Verdana", 20 ));
        buttonPause.setFont(Font.font("Verdana", 20 ));
        buttonGenererLab.setFont(Font.font("Verdana", 20 ));
        buttonSave.setFont(Font.font("Verdana", 20 ));
        buttonRetour.setFont(Font.font("Verdana", 20 ));

        buttonEditer.setId("Edition");

        vboxButton.getChildren().addAll(buttonEditer, buttonGenererLab,buttonPause,buttonSave);
        vboxButton.setSpacing(15);
        vboxtext.getChildren().addAll(herbeManger, cactusManger, margueriteManger);
        vboxtext.setSpacing(25);
        gpLeft.getChildren().add(vboxtext);

        gpRight.getChildren().add(vboxButton);


        // Evenement des différents boutton

        buttonRetour.setOnMouseClicked(eventGameUI);
        buttonEditer.setOnMouseClicked(eventGameUI);
        buttonGenererLab.setOnMouseClicked(MouseEvent -> genererLab());


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

    public void setEventGameUI(EventGameUI eventGameUI) {this.eventGameUI = eventGameUI; this.GameUI();}

    public void afficherGrille(){
        int x = 0;
        for (int i = 0 ; i < lab.getX() ; i++){
            int y = 0;
            for (int j = 0 ; j < lab.getY() ; j++){
                if (i == 0 || j == 0 || i == this.lab.getX() -1 || j == this.lab.getY() -1){
                    this.caseFX[i][j] = new CaseFX(this, this.lab.getLesCases()[i][j], x, y, true);
                    this.gpLab.getChildren().add(this.caseFX[i][j].getGp());
                }
                else {
                    this.caseFX[i][j] = new CaseFX(this, this.lab.getLesCases()[i][j], x, y);
                    this.gpLab.getChildren().add(this.caseFX[i][j].getGp());
                }
                y += this.caseFX[0][0].getScale();
            }
            x += this.caseFX[0][0].getScale();
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
}
