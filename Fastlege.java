
public class Fastlege extends Lege implements Kommuneavtale {

    /**
     * Angir hvilken kommuneID en Lege er knyttet til
     */
    private int avtalenummer;

    /**
     * Konstruktoren til FastLege
     */
    public Fastlege(String legensnavn, int avtalenummer) {
        super(legensnavn);
        this.avtalenummer = avtalenummer;
    }

    public int hentAvtalenummer() {
        return avtalenummer;
    }
}

