public class Vegetal extends Element{
    private int bonusMalus;

    public Vegetal(int bonusMalus) {
        super();
        this.bonusMalus = bonusMalus;
    }

    public int getBonusMalus() {
        if (this instanceof Marguerite) {
            return 2;
        }
        else if (this instanceof Cactus) {
            return -1;
        }
        return 0;
    }
}
