public class Application {

    public static void main(String[] arg)
    {

        Labyrinthe l1 = new Labyrinthe(10,10);
        l1.afficher();

        Loup l = new Loup(l1);
        Mouton m = new Mouton(l1);
        l1.ajouterAnimal(m, 7, 7);
        l1.afficher();
    }
}
