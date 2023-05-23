package com.example.attrape_moi_si_tu_peux.view;
import com.example.attrape_moi_si_tu_peux.*;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class CaseFX {
    private final int x;
    private final int y;
    private final Group gp ;
    private int scale;
    private final Image imgPierre = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Rocher.png").toExternalForm());
    private final Image imgHerbe = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Herbe.png").toExternalForm());
    private final Image imgCactus = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Cactus.png").toExternalForm());
    private final Image imgMarguerite = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Marguerite.png").toExternalForm());
    public CaseFX(Case laCase, int x, int y){
        gp                  = new Group();
        this.x              = x;
        this.y              = y;
        this.scale          = 60;
        Rectangle leCarre   = new Rectangle(this.scale, this.scale);
        leCarre.setX(x);
        leCarre.setY(y);
        gp.getChildren().add(leCarre);
        leCarre.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 3;");


        if (laCase.getContenu() instanceof Rocher){
            ImageView pierre = new ImageView(imgPierre);
            pierre.setFitHeight(this.scale); pierre.setFitWidth(this.scale);
            pierre.setX(x); pierre.setY(y);
            gp.getChildren().add(pierre);
        }
        if (laCase.getContenu() instanceof Herbe) {
            ImageView herbe = new ImageView(imgHerbe);
            herbe.setFitHeight(this.scale);
            herbe.setFitWidth(this.scale);
            herbe.setX(x);
            herbe.setY(y);
            gp.getChildren().add(herbe);
        }

        if (laCase.getContenu() instanceof Marguerite){
            ImageView pierre = new ImageView(imgMarguerite);
            pierre.setFitHeight(this.scale); pierre.setFitWidth(this.scale);
            pierre.setX(x); pierre.setY(y);
            gp.getChildren().add(pierre);
        }
        if (laCase.getContenu() instanceof Cactus) {
            ImageView herbe = new ImageView(imgCactus);
            herbe.setFitHeight(this.scale);
            herbe.setFitWidth(this.scale);
            herbe.setX(x);
            herbe.setY(y);
            gp.getChildren().add(herbe);
        }
    }

    public void change(Element e) {
        ImageView pierre = new ImageView(imgPierre);
        pierre.setFitHeight(this.scale); pierre.setFitWidth(this.scale);
        pierre.setX(x); pierre.setY(y);
        gp.getChildren().set(1, pierre);
    }

    public Group getGp() {
        return gp;
    }

    public void setScale(int scale){this.scale = scale;}

}
