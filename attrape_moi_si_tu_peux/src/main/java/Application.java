public class Application {

    public static void main(String[] arg)
    {
        Labyrinthe l1 = new Labyrinthe(10,10);
        l1.genererGrille();
        l1.afficher();
    }
}
