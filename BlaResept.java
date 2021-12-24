public class BlaResept extends Resept {
    
    public BlaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit) {
       super(legemiddel, utskrivendeLege, p, reit);

    //    Pris beregnes heller i metoden prisAaBetale
    //    legemiddel.settNyPris(legemiddel.hentPris()*0.25);
    }

    @Override
    public String farge() { //teste
        return "blaa";
    }

    @Override
    public double prisAaBetale() { //teste
        return hentLegemiddel().hentPris() * 0.25;
    }
}