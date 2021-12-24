/**
 * Pasient
 */
public class Pasient {
    private String pasientNavn;
    private String fodselnr;
    private int pasientID;
    private Stabel<Resept> utskrevneResepter;
    private static int lopenummer = 0;

    public Pasient (String pasientNavn, String fodselnr) {
        this.pasientNavn = pasientNavn;
        this.fodselnr = fodselnr;
        this.pasientID = lopenummer++;
        this.utskrevneResepter = new Stabel<Resept>();
    }

    public String hentNavn() {
        return pasientNavn;
    }

    public String hentFodselnr() {
        return fodselnr;
    }

    public void leggTilResept(Resept resept) {
        utskrevneResepter.leggPaa(resept);
    }

    public int hentID() {
        return pasientID;
    }

    public Resept[] hentReseptliste() {
        Resept[] tmpliste = new Resept[utskrevneResepter.stoerrelse()];
        int i = 0;
        for (Resept r : utskrevneResepter) {
            tmpliste[i++] = r;
        }
        return tmpliste;
    }
} 


// Det skal både være mulig å legge til nye resepter og hente ut hele
// reseptlisten.
