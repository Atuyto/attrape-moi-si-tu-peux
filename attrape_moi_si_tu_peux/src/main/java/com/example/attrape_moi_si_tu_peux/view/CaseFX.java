package com.example.attrape_moi_si_tu_peux.view;
import com.example.attrape_moi_si_tu_peux.Case;
import com.example.attrape_moi_si_tu_peux.Rocher;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class CaseFX {
    Case laCase;
    Rectangle leCarre;
    Image imgPierre = new Image(getClass().getResource("/Img/pierre.png").toExternalForm());
    Group gp ;
    public CaseFX(Case laCase, int x, int y){
        this.laCase = laCase;
        leCarre = new Rectangle(70,70);
        leCarre.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 3;");
        this.leCarre.setX(x);
        this.leCarre.setY(y);
        gp = new Group();
        gp.getChildren().add(this.leCarre);

        if (this.laCase.getContenu() instanceof Rocher){
            ImageView pierre = new ImageView(imgPierre);
            pierre.setFitHeight(50); pierre.setFitWidth(50);
            pierre.setX(x+5); pierre.setY(y+5);
            gp.getChildren().add(pierre);
        }

    }

    public Group getGp() {
        return gp;
    }

}
