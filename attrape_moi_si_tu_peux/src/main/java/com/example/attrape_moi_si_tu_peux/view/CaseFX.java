package com.example.attrape_moi_si_tu_peux.view;
import com.example.attrape_moi_si_tu_peux.Case;
import javafx.scene.shape.Rectangle;

public class CaseFX {
    Case laCase;
    Rectangle leCarre;

    public CaseFX(Case laCase, int x, int y){
        this.laCase = laCase;
        leCarre = new Rectangle(70,70);
        leCarre.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 3;");
        this.leCarre.setX(x);
        this.leCarre.setY(y);
    }

    public Rectangle getLeCarre() {
        return leCarre;
    }
}
