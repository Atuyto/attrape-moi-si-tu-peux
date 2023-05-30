package com.example.attrape_moi_si_tu_peux.view;
import com.example.attrape_moi_si_tu_peux.*;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class CaseFX {

    private final GameUI gameUI;
    private final int x;
    private final int y;
    private final Group gp ;
    private int scale;
    private Image[] lesImages;
    private ImageView imageView;
    private ImageView imageViewAnim;
    private final Case laCase;
    private boolean sortie;
    private final boolean border;
    public Image[] lesAnimaux;

    public CaseFX(GameUI gameUI,Case laCase, int x, int y){
        this.gameUI         = gameUI;
        gp                  = new Group();
        this.x              = x;
        this.y              = y;
        this.laCase         = laCase;
        this.border         = false;
        this.creerCase();


    }
    public CaseFX(GameUI gameUI,Case laCase, int x, int y, boolean border){
        this.gameUI         = gameUI;
        gp                  = new Group();
        this.x              = x;
        this.y              = y;
        this.laCase         = laCase;
        this.border         = border;
        this.creerCase();
        if(this.getBorder()){
            this.getGp().setOnMouseClicked(mouseEvent -> this.sortiClick());
        }
    }
    public void creerCase(){
        this.lesImages      = new Image[4];
        this.lesImages[0]   = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Rocher.png").toExternalForm());
        this.lesImages[1]   = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Herbe.png").toExternalForm());
        this.lesImages[2]   = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Cactus.png").toExternalForm());
        this.lesImages[3]   = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Marguerite.png").toExternalForm());
        this.lesAnimaux     = new Image[2];
        this.lesAnimaux[0]  = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Loup.png").toExternalForm());
        this.lesAnimaux[1]  = new Image(getClass().getResource("/com.example.attrape_moi_si_tu_peux/Mouton.png").toExternalForm());
        this.imageViewAnim  = new ImageView();
        this.imageView      = new ImageView();
        this.sortie         = false;
        this.scale          = 45;
        Rectangle leCarre   = new Rectangle(this.scale, this.scale);



        imageView.setFitHeight(this.scale);
        imageView.setFitWidth(this.scale);
        imageView.setX(this.x ); imageView.setY(this.y);

        imageViewAnim.setFitHeight(this.scale);
        imageViewAnim.setFitWidth(this.scale);
        imageViewAnim.setX(this.x ); imageViewAnim.setY(this.y);


        leCarre.setX(this.x);
        leCarre.setY(this.y);
        gp.getChildren().add(leCarre);
        leCarre.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 3;");

        System.out.println(this.laCase);
        imageView.setImage(this.laCase.getContenu() instanceof Rocher ? this.lesImages[0] :
                this.laCase.getContenu() instanceof  Herbe ? this.lesImages[1]  :
                        this.laCase.getContenu() instanceof  Cactus ? this.lesImages[2]  :
                                this.laCase.getContenu() instanceof Marguerite ? this.lesImages[3]  : null);
        gp.getChildren().add(imageView);
        gp.getChildren().add(imageViewAnim);

    }
    public Group getGp() {
        return gp;
    }

    public void setElement(){
        int index = imageView.getImage().getUrl().equals(this.lesImages[0].getUrl()) ? 0 :
                imageView.getImage().getUrl().equals(this.lesImages[1].getUrl()) ? 1 :
                        imageView.getImage().getUrl().equals(this.lesImages[2].getUrl()) ?  2 :
                                imageView.getImage().getUrl().equals(this.lesImages[3].getUrl()) ? 3 : 0 ;
        if(index == 3){index =0;} else index +=1;
        this.imageView.setImage(this.lesImages[index]);
        this.laCase.setContenu(index == 0 ? new Rocher() : index == 1 ? new Herbe() : index == 2 ? new Cactus() : new Marguerite());
    }

    public void manger(){
        this.laCase.getAnimal().manger();
        this.imageView.setImage(null);
    }

    public void repousser(){
        this.laCase.regeneration();
        if(this.laCase.getContenu()!=null){
            if(this.laCase.getContenu() instanceof Herbe) {
                this.imageView.setImage(this.lesImages[1]);
            }
            if(this.laCase.getContenu() instanceof Marguerite) {
                this.imageView.setImage(this.lesImages[3]);
            }
            if(this.laCase.getContenu() instanceof Cactus) {
                this.imageView.setImage(this.lesImages[2]);
            }
        }
    }

    public void deleteAnimal(){
        this.imageViewAnim.setImage(null);
    }
    public void afficherAnimal() {
        if (this.laCase.getAnimal() instanceof Loup) {
            this.imageViewAnim.setImage(this.lesAnimaux[0]);

        }

        if (this.laCase.getAnimal() instanceof Mouton) {
            this.imageViewAnim.setImage(this.lesAnimaux[1]);

        }
    }

    public void setAnimaux() {
        int nbAnimaux = this.laCase.getLeLabyrinthe().getLesAnimaux().size();
        Animal animal;
        if (this.laCase.getAnimal() !=null && (nbAnimaux == 2 || nbAnimaux ==1 )) {
            if(this.laCase.getAnimal() !=null &&  (this.laCase.getLeLabyrinthe().getLesAnimaux().size() ==2 || this.laCase.getLeLabyrinthe().getLesAnimaux().size() ==1)){
                this.imageViewAnim.setImage(null);
                int lastIndex = this.laCase.getLeLabyrinthe().getLesAnimaux().indexOf(this.getLaCase().getAnimal());
                this.getLaCase().getLeLabyrinthe().getLesAnimaux().remove(lastIndex);
                this.laCase.setAnimal(null);
            }
        }
        else if( ! (this.laCase.getContenu() instanceof Rocher)){
            if (nbAnimaux == 0) {
                animal = new Mouton(this.getLaCase().getLeLabyrinthe());
                this.imageViewAnim.setImage(this.lesAnimaux[1]);
                this.laCase.setAnimal(animal);
                this.getLaCase().getLeLabyrinthe().setLesAnimaux(animal);

            }
            if (nbAnimaux == 1) {
                if (this.laCase.getLeLabyrinthe().getLesAnimaux().get(0) instanceof Mouton) {
                    animal = new Loup(this.getLaCase().getLeLabyrinthe());
                    this.imageViewAnim.setImage(this.lesAnimaux[0]);
                    this.laCase.setAnimal(animal);
                    this.getLaCase().getLeLabyrinthe().setLesAnimaux(animal);

                }
                if (this.laCase.getLeLabyrinthe().getLesAnimaux().get(0) instanceof Loup) {
                    animal = new Mouton(this.laCase.getLeLabyrinthe());
                    this.imageViewAnim.setImage(this.lesAnimaux[1]);
                    this.laCase.setAnimal(animal);
                    this.getLaCase().getLeLabyrinthe().setLesAnimaux(animal);
                }
            }
        }
    }


    public void sortiClick(){
        if(this.gameUI.getNbsorti() != 1){
            this.gameUI.afficherTitle();
            this.setSortie();
            this.setElement();
            this.gameUI.setNbsorti(1);
            this.laCase.setSortie(true);
        }
        if(this.gameUI.getNbsorti() == 1 && this.getSortie()){
            this.setElement();
            if(this.laCase.getContenu() instanceof Rocher){
                this.gameUI.messageSetSortie();
                this.setSortie();
                this.gameUI.setNbsorti(0);
                this.laCase.setSortie(false);
            }
        }
    }
    public boolean checkSortie() {
        if (this.border&&(!(this.laCase.getContenu() instanceof Rocher))) {
            System.out.println(true);
            this.setSortie();
            this.gp.setOnMouseClicked(null);
            return true;
        } else {
            return false;
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

    public int getScale() {
        return scale;
    }
}
