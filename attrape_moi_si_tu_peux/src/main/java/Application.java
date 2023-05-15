public class Application {

    public static void main(String[] arg)
    {

        Labyrinthe l1   = new Labyrinthe(10,10);
        Loup l          = new Loup(l1);
        Mouton m        = new Mouton(l1);

        l1.ajouterAnimal(l, 7, 7);
        l1.ajouterAnimal(m,1,1);
        l1.afficher();
        m.seDeplacer(2,"S");
        m.manger();
        m.seDeplacer(2,"E");
        System.out.println(m.getNbMargurite()+" "+ m.getNbCactus()+" "+m.getNbHerbe());



        System.out.print("\n");
        l.seDeplacer(1,"E");

        l1.afficher();

    }
}
