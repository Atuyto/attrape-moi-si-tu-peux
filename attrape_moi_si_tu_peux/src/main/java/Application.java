public class Application {

    public static void main(String[] arg)
    {

        Labyrinthe l1   = new Labyrinthe(10,10);
        Loup l          = new Loup(l1);
        Mouton m        = new Mouton(l1);

        l1.ajouterAnimal(l, 4, 4);
        l1.afficher();


        System.out.print("\n");
        l.seDeplacer(2,"O");

        l1.afficher();

    }
}
