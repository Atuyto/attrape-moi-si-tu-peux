public class Application {

    public static void main(String[] arg)
    {

        Labyrinthe l1   = new Labyrinthe(10,10);
        Loup l          = new Loup(l1);
        Mouton m        = new Mouton(l1);

        l1.ajouterAnimal(l, 7, 7);
        l1.ajouterAnimal(m, 5, 7);
        l1.afficher();
        system.out.println(l.reperer());

        System.out.print("\n");
        l.seDeplacer(1,"E");

        l1.afficher();

    }
}
