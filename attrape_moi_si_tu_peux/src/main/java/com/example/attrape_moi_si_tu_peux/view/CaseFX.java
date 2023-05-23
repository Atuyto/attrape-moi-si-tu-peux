package com.example.attrape_moi_si_tu_peux.view;
import com.example.attrape_moi_si_tu_peux.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class CaseFX {
    private final int x;
    private final int y;
    private final Group gp ;
    private int scale;

    private Image[] lesImages;
    private ImageView imageView;

    public CaseFX(Case laCase, int x, int y){
        gp                  = new Group();
        this.x              = x;
        this.y              = y;
        this.scale          = 60;
        this.lesImages      = new Image[4];
        this.lesImages[0]   = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Rocher.png").toExternalForm());
        this.lesImages[1]   = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Herbe.png").toExternalForm());
        this.lesImages[2]   = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Cactus.png").toExternalForm());
        this.lesImages[3]   = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Marguerite.png").toExternalForm());
        Rectangle leCarre   = new Rectangle(this.scale, this.scale);
        leCarre.setX(x);
        leCarre.setY(y);
        gp.getChildren().add(leCarre);
        leCarre.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 3;");

        imageView = new ImageView();
        imageView.setFitHeight(this.scale);
        imageView.setFitWidth(this.scale);
        imageView.setX(x); imageView.setY(y);

        imageView.setImage(laCase.getContenu() instanceof Rocher ? this.lesImages[0] :
                laCase.getContenu() instanceof  Herbe ? this.lesImages[1]  :
                laCase.getContenu() instanceof  Cactus ? this.lesImages[2]  :
                laCase.getContenu() instanceof Marguerite ? this.lesImages[3]  : null);
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
        this.imageView.setImage(index == 3 ? this.lesImages[0] : this.lesImages[index +1]);

        return null;
    }

    public void setScale(int scale){this.scale = scale;}

}
