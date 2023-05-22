package com.example.attrape_moi_si_tu_peux.view;
import com.example.attrape_moi_si_tu_peux.Case;
import com.example.attrape_moi_si_tu_peux.Element;
import com.example.attrape_moi_si_tu_peux.Rocher;
import com.example.attrape_moi_si_tu_peux.Herbe;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class CaseFX {
    Case laCase;
    Rectangle leCarre;
    Image imgPierre = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Rocher.png").toExternalForm());
    Image imgHerbe = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Herbe.png").toExternalForm());
    int x;
    int y;
    Group gp ;
    public CaseFX(Case laCase, int x, int y){
        this.laCase = laCase;
        leCarre = new Rectangle(70,70);
        leCarre.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 3;");
        this.leCarre.setX(x); this.x = x;
        this.leCarre.setY(y); this.y = y;
        gp = new Group();
        gp.getChildren().add(this.leCarre);

        if (this.laCase.getContenu() instanceof Rocher){
            ImageView pierre = new ImageView(imgPierre);
            pierre.setFitHeight(70); pierre.setFitWidth(70);
            pierre.setX(x); pierre.setY(y);
            gp.getChildren().add(pierre);
        }
        if (this.laCase.getContenu() instanceof Herbe) {
            ImageView herbe = new ImageView(imgHerbe);
            herbe.setFitHeight(70);
            herbe.setFitWidth(70);
            herbe.setX(x);
            herbe.setY(y);
            gp.getChildren().add(herbe);
        }
    }

    public void change(Element e) {
        ImageView pierre = new ImageView(imgPierre);
        pierre.setFitHeight(70); pierre.setFitWidth(70);
        pierre.setX(x); pierre.setY(y);
        gp.getChildren().set(1, pierre);
    }

    public Group getGp() {
        return gp;
    }

}
