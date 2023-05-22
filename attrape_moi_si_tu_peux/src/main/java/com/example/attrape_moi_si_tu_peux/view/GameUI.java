package com.example.attrape_moi_si_tu_peux.view;

import com.example.attrape_moi_si_tu_peux.Labyrinthe;
import com.example.attrape_moi_si_tu_peux.Rocher;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameUI {

    public GameUI(Stage stage){
        Labyrinthe lab = new Labyrinthe(10, 10);
        CaseFX caseFX[][] = new CaseFX[lab.getX()][lab.getY()];
        int x = 150;
        Group gp = new Group();
        for (int i = 0 ; i < lab.getX() ; i++){
            int y = 50;
            for (int j = 0 ; j < lab.getY() ; j++){
                caseFX[i][j] = new CaseFX(lab.getLesCases()[i][j], x, y );
                gp.getChildren().add(caseFX[i][j].getGp());
                y += 70;
            }
            x+= 70;
        }

        lab.getLesCases()[3][3].setContenu(new Rocher());
        caseFX[3][3].change(null);
        Scene sc = new Scene(gp, 1000,800);

        stage.setScene(sc);
    }
}
