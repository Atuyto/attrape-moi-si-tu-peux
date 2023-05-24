package com.example.attrape_moi_si_tu_peux.view;
import com.example.attrape_moi_si_tu_peux.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.atomic.AtomicBoolean;

public class CaseFX {

    private GameUI gameUI;
    private final int x;
    private final int y;
    private final Group gp ;
    private int scale;
    private Image[] lesImages;
    private ImageView imageView;
    private Case laCase;
    private boolean sortie;
    private boolean border;

    public CaseFX(GameUI gameUI,Case laCase, int x, int y){
        this.gameUI              = gameUI;
        gp                  = new Group();
        this.x              = x;
        this.y              = y;
        this.scale          = 60;
        this.laCase         = laCase;
        this.border         = false;
        creerCase();

    }
    public CaseFX(GameUI gameUI,Case laCase, int x, int y, boolean border){
        this.gameUI              = gameUI;
        gp                  = new Group();
        this.x              = x;
        this.y              = y;
        this.scale          = 60;
        this.laCase         = laCase;
        this.border         = border;
        this.creerCase();
        if(this.getBorder()){
            this.getGp().setOnMouseClicked(mouseEvent -> this.setClicked());
        }




    }
    public void creerCase(){

        this.lesImages      = new Image[4];
        this.lesImages[0]   = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Rocher.png").toExternalForm());
        this.lesImages[1]   = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Herbe.png").toExternalForm());
        this.lesImages[2]   = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Cactus.png").toExternalForm());
        this.lesImages[3]   = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Marguerite.png").toExternalForm());
        Rectangle leCarre   = new Rectangle(this.scale, this.scale);
        sortie              = false;
        leCarre.setX(x);
        leCarre.setY(y);
        gp.getChildren().add(leCarre);
        leCarre.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 3;");

        imageView = new ImageView();
        imageView.setFitHeight(this.scale);
        imageView.setFitWidth(this.scale);
        imageView.setX(x); imageView.setY(y);

        imageView.setImage(this.laCase.getContenu() instanceof Rocher ? this.lesImages[0] :
                this.laCase.getContenu() instanceof  Herbe ? this.lesImages[1]  :
                        this.laCase.getContenu() instanceof  Cactus ? this.lesImages[2]  :
                                this.laCase.getContenu() instanceof Marguerite ? this.lesImages[3]  : null);
        gp.getChildren().add(imageView);



    }
    public Group getGp() {
        return gp;
    }

    public EventHandler<? super MouseEvent> setElement(){
        int index = imageView.getImage().getUrl().equals(this.lesImages[0].getUrl()) ? 0 :
                imageView.getImage().getUrl().equals(this.lesImages[1].getUrl()) ? 1 :
                imageView.getImage().getUrl().equals(this.lesImages[2].getUrl()) ?  2 :
                imageView.getImage().getUrl().equals(this.lesImages[3].getUrl()) ? 3 : 0 ;
        if(index == 3){index =0;} else index ++;
        this.imageView.setImage(this.lesImages[index]);
        this.laCase.setContenu(index == 0 ? new Rocher() : index == 1 ? new Herbe() : index == 2 ? new Cactus() : new Marguerite());
        return null;
    }

    public void setClicked(){
        System.out.println(this.gameUI.getNbsorti());
        if(this.gameUI.getNbsorti() != 1){
            this.setSortie();
            setElement();
            this.gameUI.setNbsorti(1);
        }
        if(this.gameUI.getNbsorti() == 1 && this.getSortie()){
            setElement();
            if(this.laCase.getContenu() instanceof Rocher){
                this.setSortie();
                this.gameUI.setNbsorti(0);
            }

        }

    }

    public boolean getBorder(){
        return this.border;
    }

    public boolean getSortie(){
        return this.sortie;
    }
    public void setSortie() {
        this.sortie = !this.sortie;
    }

    public void setScale(int scale){this.scale = scale;}

    public Case getLaCase() {
        return laCase;
    }
}
