

public class LegemiddelA extends Legemiddel{

    private int narkotiskStyrke;

    public LegemiddelA(String navn, double pris, double mengdeVirkestoff, int narkotiskStyrke) {
        super(navn, pris, mengdeVirkestoff);
        this.narkotiskStyrke = narkotiskStyrke;
    }

    public int hentNarkotiskStyrke() {
        return narkotiskStyrke;
    }
}