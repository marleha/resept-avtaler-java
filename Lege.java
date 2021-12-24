
public class Lege implements Comparable<Lege> {

    private String legensnavn;
    private Lenkeliste<Resept> utskrevneResepter;

    public Lege(String legensnavn) {
        this.legensnavn = legensnavn;
        this.utskrevneResepter = new Lenkeliste<Resept>();
    }

    /**
     * Returns the name of the doctor
     * @return The name of the doctor
     */
    public String hentNavn() {
        return legensnavn;
    }

    @Override
    public int compareTo(Lege lege) { //void
        int svar = legensnavn.compareTo(lege.hentNavn());
        return svar;
    }

    public static void main(String[] args) {
        Lege lege1 = new Lege("Dr. Paus");
        Lege lege2 = new Lege("Dr. Ueland");
        System.out.println(lege1.compareTo(lege2));
    }
    
    public void leggTilResept(Resept resept) {
        utskrevneResepter.leggTil(resept);
    }

    public Resept[] hentResepter() {
        Resept[] listeResepter = new Resept[utskrevneResepter.stoerrelse()];
        int teller = 0;
        for (Resept resept : utskrevneResepter) {
            listeResepter[teller++] = resept;
        }
        return listeResepter;
    }
}