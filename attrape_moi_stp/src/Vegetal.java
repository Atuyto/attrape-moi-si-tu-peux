public class Vegetal extends Element{
    private int bonusMalus;

    public Vegetal(String imgPath, Case laCase, int bonusMalus) {
        super(imgPath, laCase);
        this.bonusMalus = bonusMalus;
    }
}
