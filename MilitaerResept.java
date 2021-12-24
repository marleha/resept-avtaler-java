
public class MilitaerResept extends HvitResept {

    public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit) {
        super(legemiddel, utskrivendeLege, p, reit);
    }

    @Override
    public double prisAaBetale() { //teste
        // return hentLegemiddel().hentPris() * 0;
        return 0;
    }
}