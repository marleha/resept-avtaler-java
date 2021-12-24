

public class LegemiddelB extends Legemiddel {

   private int vanedannendeStyrke;

    public LegemiddelB(String navn, double pris, double mengdeVirkestoff, int vanedannendeStyrke) {
        super(navn, pris, mengdeVirkestoff);
        this.vanedannendeStyrke = vanedannendeStyrke;
    }

    public int hentVanedannendeStyrke() {
        return vanedannendeStyrke;
    }
}