import javafx.scene.shape.VertexFormat;

public class Animal {
    private int mouvementPossible;
    private int vision;
    private Labyrinthe leLabyrinthe;
    private Case Lacase;

    private boolean affame;

    public Animal(int mouvementPossible, Labyrinthe leLabyrinthe, Case Lacase){
        this.vision = 5;
        this.mouvementPossible = mouvementPossible;
        this.leLabyrinthe = leLabyrinthe;
        this.Lacase = Lacase;
        this.affame = true;

    }
    public boolean manger(){
        if(this.affame){
            return false;
            this.affame = true;
        }
    }
}
