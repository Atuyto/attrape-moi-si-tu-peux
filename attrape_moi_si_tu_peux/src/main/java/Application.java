public class Application {

    public static void main(String[] arg)
    {

        Labyrinthe l1   = new Labyrinthe(10,10);
        Loup l          = new Loup(l1);
        Mouton m        = new Mouton(l1);

        l1.ajouterAnimal(l, 5, 7);
        l1.ajouterAnimal(m, 2, 7);
        l1.afficher();

        m.manger();
        l1.setNb_tour(0);
        System.out.println(l1.getNb_tour());

        System.out.print("\n"  );

        l1.setNb_tour(1);
        m.seDeplacer(1,"O");

        m.manger();

        System.out.print("\n"  );
        l1.afficher();
        System.out.println(l1.getNb_tour());
        l1.getLesCases()[2][7].regeneration();


        m.seDeplacer(1,"N");
        m.manger();
        l1.setNb_tour(2);

        System.out.print("\n"  );

        l1.afficher();
        l1.getLesCases()[2][7].regeneration();
        System.out.println(l1.getNb_tour());

        System.out.print("\n"  );
        l1.setNb_tour(3);

        l1.afficher();
        l1.getLesCases()[2][7].regeneration();
        System.out.println(l1.getNb_tour());

    }
}
