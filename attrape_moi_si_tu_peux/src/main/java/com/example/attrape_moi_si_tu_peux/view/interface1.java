package com.example.attrape_moi_si_tu_peux.view;

import com.example.attrape_moi_si_tu_peux.Case;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class interface1 extends Application {
    @Override
    public void start(Stage stage) {

        //Rectangle carre = new Rectangle(70,70);
        //carre.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 3;");

        Labyrinthe lab = new Labyrinthe();
        CaseFX caseFX[][] = new CaseFX[lab.getX()][lab.getY()];
        int x = 150;
        Group gp = new Group();
        for (int i = 0 ; i < lab.getX() ; i++){
            int y = 50;
            for (int j = 0 ; j < lab.getY() ; j++){
                caseFX[i][j] = new CaseFX(lab.getLesCases()[i][j], x, y );
                gp.getChildren().add(caseFX[i][j].getLeCarre());
                y += 70;
            }
            x+= 70;
        }



        Scene sc = new Scene(gp, 1000,800);

       ;
        stage.setTitle("attrape moi si tu peux !");
        stage.setScene(sc);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
