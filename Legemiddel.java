

abstract public class Legemiddel {

    private String navn;
    private int ID;
    private double pris;
    private double mengdeVirkestoff;
    private static int lopenummer = 0;

    public Legemiddel(String navn, double pris, double mengdeVirkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.mengdeVirkestoff = mengdeVirkestoff;
        this.ID = lopenummer++;
    }

    public String hentNavn() {
        return navn;
    }

    public int hentId() {
        return ID;
    }

    public double hentPris() {
        return pris;
    }

    public double hentVirkestoff() {
        return mengdeVirkestoff;
    }

    public void settNyPris(double nyPris) {
        pris = nyPris;
    }

    public void printInfo() {
        System.out.println("------------------------");
        System.out.println("Navn:" + navn);
        System.out.println("ID:" + ID);
        System.out.println("Mengde virkestoff:" + mengdeVirkestoff);
        System.out.println("Pris:" + pris);
    }
}