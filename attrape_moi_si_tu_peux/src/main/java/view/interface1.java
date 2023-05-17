package view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class interface1 extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Rectangle carre = new Rectangle(50,50, 50,50);

        Group gp = new Group(carre);
        Scene sc = new Scene(gp, 500,500);

       ;
        stage.setTitle("attrape moi si tu peux !");
        stage.setScene(sc);
        stage.show();
    }

    public static void main(String[] args){
        Application.launch(args);
    }
}
