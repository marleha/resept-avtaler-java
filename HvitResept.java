
public class HvitResept extends Resept {

    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit) {
        super(legemiddel, utskrivendeLege, p, reit);
    }
    
    @Override
    public String farge() {  //teste
        return "hvit";
    }

    @Override
    public double prisAaBetale() { //teste
        return hentLegemiddel().hentPris();
    }
}   