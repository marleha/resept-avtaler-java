import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Legesystem {
    static SortertLenkeliste<Lege> leger;
    static Lenkeliste<Pasient> pasienter;
    static Lenkeliste<Legemiddel> legemidler;
    static Lenkeliste<Resept> resepter;

    public static void main(String[] args) {
        leger = new SortertLenkeliste<Lege>();
        pasienter = new Lenkeliste<Pasient>();
        legemidler = new Lenkeliste<Legemiddel>();
        resepter = new Lenkeliste<Resept>();
        lesFraFil();
        kommandolokke();
    }

    public static void kommandolokke() {
        while (true) {
            skrivInstruksjoner();
            int brukerValg = lesValg();
            // Skrive ut en fullstendig oversikt over personer, leger, legemidler og resepter
            // (deloppgave D2).
            if (brukerValg == 1) {
                skrivOversikt();
            } else if (brukerValg == 2) {
                // Opprette og legge til nye elementer i systemet (deloppgave D3).
                leggTil();
            } else if (brukerValg == 3) {
                // Bruke en gitt resept fra listen til en pasient (deloppgave D4).
                brukResept();
            } else if (brukerValg == 4) {
                // Skrive ut forskjellige former for statistikk (deloppgave D5).
                skrivStatistikk();
            } else if (brukerValg == 5) {
                // Skrive alle data til fil (deloppgave D7).
                skrivTilFil();
            } else if (brukerValg == 6) {
                // For å avslutte programmet
                avslutt();
            }
        }
    }

    /**
     * Brukervalg 1
     */
    private static void skrivOversikt() {
        System.out.println("Pasienter:");
        for (Pasient pasient : pasienter) {
            System.out.println("Navn: " + pasient.hentNavn() + " Fodselsnr.: " + pasient.hentFodselnr());
        }
        System.out.println("Legemidler:");
        for (Legemiddel legemiddel: legemidler) {
            System.out.print("Navn: " + legemiddel.hentNavn() + " ID: " + legemiddel.hentId() + " Pris: " + legemiddel.hentPris()  + " Mengde virkestoff: " + legemiddel.hentVirkestoff() + "mg");
            if (legemiddel instanceof LegemiddelA) {
                // Ma caste nedover for aa faa tak i narkotisk og vandedannende styrke.
                System.out.println(" Narkotiske styrke: " + ((LegemiddelA) legemiddel).hentNarkotiskStyrke());
            }
            else if (legemiddel instanceof LegemiddelB) {
                System.out.println(" Vanedannende styrke: " + ((LegemiddelB)legemiddel).hentVanedannendeStyrke());
            } 
            else if (legemiddel instanceof LegemiddelC) {
                System.out.println();
            }
        } 
        System.out.println("Leger:");
        for (Lege lege : leger) {
            System.out.println("Navn: " + lege.hentNavn());
            if (lege instanceof Fastlege) {
                // Maa caste nedover for aa faa tak i avtalenummer.
                System.out.println("Avtalenummer: " + ((Fastlege) lege).hentAvtalenummer());
            }
            System.out.println("Utskrevne resepter:");
            for(Resept r : lege.hentResepter()) {
                System.out.println("ReseptID: " + r.hentId() + " " + r.hentLegemiddel().hentNavn());
            }
        }
        System.out.println("Resepter:");
        for (Resept resept : resepter) {
            if (resept instanceof HvitResept) {
                System.out.println("Hvit resept:");
                System.out.println("Farge: " + ((HvitResept) resept).farge() + " Legemiddel: " + resept.hentLegemiddel().hentNavn() + " Id: " + resept.hentId() + " Pasient: " + resept.hentPasient().hentNavn() + " Lege: " + resept.hentLege().hentNavn() + " Reit: " + resept.hentReit() + " Pris: " + ((HvitResept) resept).prisAaBetale() + " kr");
            }
            else if (resept instanceof MilitaerResept) {
                System.out.println("Militaer resept:");
                System.out.println("Farge: " + ((MilitaerResept) resept).farge() + " Legemiddel: " + resept.hentLegemiddel().hentNavn() + " Id: " + resept.hentId() + " Pasient: " + resept.hentPasient().hentNavn() + " Lege: " + resept.hentLege().hentNavn() + " Reit: " + resept.hentReit() + " Pris: " + ((MilitaerResept) resept).prisAaBetale() + " kr");
            }
            else if (resept instanceof BlaResept) {
                System.out.println("Blaa resept:");
                System.out.println("Farge: " + ((BlaResept) resept).farge() + " Legemiddel: " + resept.hentLegemiddel().hentNavn() + " Id: " + resept.hentId() + " Pasient: " + resept.hentPasient().hentNavn() + " Lege: " + resept.hentLege().hentNavn() + " Reit: " + resept.hentReit() + " Pris: " + ((BlaResept) resept).prisAaBetale() + " kr");
            }
            else if (resept instanceof Presept) {
                System.out.println("Presept:");
                System.out.println("Farge: " + ((Presept) resept).farge() + " Legemiddel: " + resept.hentLegemiddel().hentNavn() + " Id: " + resept.hentId() + " Pasient: " + resept.hentPasient().hentNavn() + " Lege: " + resept.hentLege().hentNavn() + " Reit: " + resept.hentReit() + " Pris: " + ((Presept) resept).prisAaBetale() + " kr");
            }
        }      
    }  

    /**
     * Brukervalg 2
     */
    private static void leggTil() {
        System.out.println("1. Legg til ny pasient.");
        System.out.println("2. Legg til ny resept.");
        System.out.println("3. Legg til ny legemiddel.");
        System.out.println("4. Legg til ny lege.");
        int brukerValg = lesValg();
        switch (brukerValg) {
        case 1:
            nyPasient();
            break;
        case 2:
            nyResept();
            break;
        case 3:
            try {
                nyLegemiddel();
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
            break;
        case 4:
            try {
                nyLege();
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
            break;
        default:
            System.out.println("Ugyldig valg!");
            leggTil();
            break;
        }
    }

    private static void nyResept() {
        // Vi må ha minst en lege, ett legemiddel og en pasient for å kunne lage en resept
        if (legemidler.stoerrelse() == 0 && pasienter.stoerrelse() == 0 && leger.stoerrelse() == 0) {
            System.out.println("Vi maa ha minst én lege, ett legemiddel og én pasient for aa kunne opprette en resept.");
            return;
        }

        // Lege som skal skrive ut resepten.
        System.out.println("Hvilken lege skal skrive ut resepten?");
        int i = 1;
        for (Lege l : leger) {
            System.out.println(i++ + ". " + l.hentNavn());
        }
        int valg = lesValg();
        Lege l;
        try {
            l = leger.hent(valg - 1);
        } catch (UgyldigListeIndeks e) {
            System.out.println(e.getMessage());
            return;
        }

        // Pasient som har resepten.
        System.out.println("Hvilken pasient har resepten?");
        int o = 1;
        for (Pasient p : pasienter) {
            System.out.println(o++ + ". " + p.hentNavn());
        }
        int valg2 = lesValg();
        Pasient p = pasienter.hent(valg2 - 1);

        // Legemidlet på resepten
        System.out.println("Hvilket legemiddel er paa resepten?");
        int k = 1;
        for (Legemiddel lm : legemidler) {
            System.out.println(k++ + ". " + lm.hentNavn());
        }
        int valg3 = lesValg();
        Legemiddel lm = legemidler.hent(valg3 - 1);

        // Reit
        System.out.println("Hvor mange reit skal det vaere paa resepten?");
        int reit = lesValg();

        // Type resept
        System.out.println("Hva slags resept onsker du?");
        System.out.println("1. Hvit resept");
        System.out.println("2. Militaer resept");
        System.out.println("3. Pre resept");
        System.out.println("4. Bla resept");
        int reseptOnske = lesValg();
        Resept resept;
        switch (reseptOnske) {
        case 1:
            resept = new HvitResept(lm, l, p, reit);
			resepter.leggTil(resept);
            break;
        case 2:
            resept = new MilitaerResept(lm, l, p, reit);
			resepter.leggTil(resept);
            break;
        case 3:
            resept = new Presept(lm, l, p, reit);
            resepter.leggTil(resept);
            break;
        case 4:
            resept = new BlaResept(lm, l, p, reit);
            resepter.leggTil(resept);
            break;
        default:
            System.out.println("Ugyldig input!");
            return;
        }
        l.leggTilResept(resept);
        p.leggTilResept(resept);
    }

    /**
     * Ny lege
     */
    private static void nyLege() throws Exception {
        System.out.println("Skriv navnet til legen:");
        String legeNavn = lesValgString();
        System.out.println("Skal det være en fastlege?");
        System.out.println("1. Ja.");
        System.out.println("2. Nei, vanlig lege.");
        int valget = lesValg();
        Lege lege;
        switch (valget) {
            case 1:
                System.out.println("Hvilket kommunenr. er fastlegen tilknyttet?");
                int kommunenr = lesValg();
                lege = new Fastlege(legeNavn, kommunenr);
                break;
            case 2:
                lege = new Lege(legeNavn);
                break;
            default:
                throw new Exception("Ugyldig input!");
        }
        leger.leggTil(lege);
    }

    /**
     * Nytt legemiddel
     * @throws Exception
     */
    private static void nyLegemiddel() throws Exception {
        System.out.println("Hvilken type legemiddel onsker du?");
        System.out.println("1. Legemiddel med narkotisk styrke.");
        System.out.println("2. Legemiddel med vanedannende styrke.");
        System.out.println("3. Legemiddel uten narkotisk eller vanedannende styrke.");
        int bruker = lesValg();
        switch (bruker) {
        case 1:
            nyLegemiddelA();
            break;
        case 2:
            nyLegemiddelB();
            break;
        case 3:
            nyLegemiddelC();
            break;
        default:
            throw new Exception("Ugyldig input!");
        }
    }

    /**
     * Nytt legemiddel A
     */
    private static void nyLegemiddelA() {
        System.out.println("Skriv navnet til legemiddelet:");
        String legemiddelNavn = lesValgString();
        System.out.println("Skriv prisen til legemiddelet:");
        Double legemiddelPris = lesValgDouble();
        // System.out.println("DEBUG: LEGEMIDDELPRIS: " + legemiddelPris);
        System.out.println("Skriv legemiddelets mengde virkestoff:");
        Double mengdeVirkestoff = lesValgDouble();
        System.out.println("Skriv narkotisk styrke:");
        int narkotiskStyrke = lesValg();
        Legemiddel nylegemiddelA = new LegemiddelA(legemiddelNavn, legemiddelPris, mengdeVirkestoff, narkotiskStyrke);
        legemidler.leggTil(nylegemiddelA);
    }

    /**
     * Nytt legemiddel B
     */
    private static void nyLegemiddelB() {
        System.out.println("Skriv navnet til legemiddelet:");
        String legemiddelNavn = lesValgString();
        System.out.println("Skriv prisen til legemiddelet:");
        Double legemiddelPris = lesValgDouble();
        System.out.println("Skriv legemiddelets mengde virkestoff:");
        Double mengdeVirkestoff = lesValgDouble();
        System.out.println("Skriv vanedannende styrke:");
        int vanedannendeStyrke = lesValg();
        Legemiddel nylegemiddelB = new LegemiddelB(legemiddelNavn, legemiddelPris, mengdeVirkestoff,
                vanedannendeStyrke);
        legemidler.leggTil(nylegemiddelB);
    }

    /**
     * Nytt legemiddel C
     */
    private static void nyLegemiddelC() {
        System.out.println("Skriv navnet til legemiddelet:");
        String legemiddelNavn = lesValgString();
        System.out.println("Skriv prisen til legemiddelet:");
        Double legemiddelPris = lesValgDouble();
        System.out.println("Skriv legemiddelets mengde virkestoff:");
        Double mengdeVirkestoff = lesValgDouble();
        Legemiddel nylegemiddel = new LegemiddelC(legemiddelNavn, legemiddelPris, mengdeVirkestoff);
        legemidler.leggTil(nylegemiddel);
    }

    /**
     * Ny pasient
     */
    private static void nyPasient() {
        System.out.println("Skriv navnet til pasienten:");
        String pasientNavn = lesValgString();
        System.out.println("Skriv fodselsnummeret til pasienten:");
        String fodselNr = lesValgString();
        Pasient nypasient = new Pasient(pasientNavn, fodselNr);
        pasienter.leggTil(nypasient);
    }

    /**
     * Brukervalg 3
     */
    private static void brukResept() {
        System.out.println("Hvilken pasient vil du se resepter for?");
        int teller = 0;
        for (Pasient pasient : pasienter) {
            System.out.println(teller++ + ". " + pasient.hentNavn() + "(fnr " + pasient.hentFodselnr() + ")");
        }
        int brukervalgPasient = lesValg();
        Pasient pasient = pasienter.hent(brukervalgPasient);
        System.out.println("Valgt pasient: " + pasient.hentNavn() + "(fnr " + pasient.hentFodselnr() + ")");
        System.out.println("Hvilken resept vil du bruke?");
        teller = 0;
        for (Resept resept : pasient.hentReseptliste()) {
            Legemiddel legemiddel = resept.hentLegemiddel();
            String legemiddelnavn = legemiddel.hentNavn();
            System.out.println(teller++ + ". " + legemiddelnavn + "(" + resept.hentReit() + " reit)");
            // En annen maate: System.out.println(teller++ + ". " + resept.hentLegemiddel().hentNavn() + "(" + resept.hentReit() + " reit)");
        }
        int brukervalgResept = lesValg();
        Resept resept = pasient.hentReseptliste()[brukervalgResept];
        if (resept.bruk() == true) {
            System.out.println("Brukte resept paa " + resept.hentLegemiddel().hentNavn() + ". Antall gjenvaerende reit: " + resept.hentReit());
        } else {
            System.out.println("Kunne ikke bruke resept paa " + resept.hentLegemiddel().hentNavn() + " (ingen gjenvaerende reit).");
        }
    }

    /**
     * Brukervalg 4
     */
    private static void skrivStatistikk() {
        // Totalt antall utskrevne resepter på vanedannende legemidler.
        int teller = 0;
        for (Resept resept : resepter) {
            Legemiddel legemiddelet = resept.hentLegemiddel();
            if (legemiddelet instanceof LegemiddelB) {
                teller++;
            }
        }
        System.out.println("Totalt antall utskrevne resepter paa vanedannende legemidler: " + teller);

        // Antall vanedannende resepter utskrevne til militaere.
        teller = 0;
        for (Resept resepten : resepter) {
            if (resepten instanceof MilitaerResept) {
                Legemiddel legemiddelet = resepten.hentLegemiddel();
                if (legemiddelet instanceof LegemiddelB) {
                    teller ++;
                }
            }
        }
        System.out.println("Antall vanedannende resepter uskrevne til militaere er: " + teller);

        // List opp navnene på alle leger (i alfabetisk rekkefølge) som har skrevet ut
        // minst en resept (gyldig eller ikke gyldig) på narkotiske legemidler, og antallet
        // slike resepter per lege.
        System.out.println("Mulig misbruk av narkotika:");
        for (Lege legen : leger) {
            teller = 0;
            Resept[] reseptliste = legen.hentResepter();
            for (Resept resepten : reseptliste) {
                if (resepten.hentLegemiddel() instanceof LegemiddelA) {
                    teller++; 
                }
            }
            if (teller > 0) {
                System.out.println(legen.hentNavn() + " har skrevet ut minst en gyldig resept på et narkotisk legemiddel. Antall slike resepter er " + teller);
            }
        }

        //List opp navnene på alle pasienter som har minst en gyldig resept på narkotiske 
        // legemidler, og for disse, skriv ut antallet per pasient.
        for (Pasient pasient : pasienter) {
            Resept[] reseptlisten = pasient.hentReseptliste();
            for (Resept resept : reseptlisten) {
                teller = 0;
                if (resept.hentLegemiddel() instanceof LegemiddelA) {
                    teller++;
                }
                if (teller > 0) {
                    System.out.println(resept.hentPasient().hentNavn() + "har minst en gyldig resept paa et narkotisk legemiddel. Antallet slike resepter er " + teller);
                }
            }
        }
    } 

    /**
     * Brukervalg 5
     */
    private static void skrivTilFil() {
        //TODO
    }

    private static void lesFraFil() {
        System.out.println("Skriv inn fil aa lese fra:");
        String filnavn = lesValgString();
        File f = new File(filnavn);
        Scanner s;
        try {
		    s = new Scanner(f);
		} catch (FileNotFoundException e) {
            System.out.println("Filen " + filnavn + " finnes ikke.");
            return;
        }

        /*
         * 0: Pasienter
         * 1: Legemidler
         * 2: Leger
         * 3: Resepter
         */
        int hvavileser = 0; 
        String[] delt;
        while(s.hasNextLine()) {
            String linje = s.nextLine();
            if (linje.startsWith("# Pasienter")) {
                hvavileser = 0;
                continue;
            } else if (linje.startsWith("# Legemidler")) {
                hvavileser = 1;
                continue;
            } else if (linje.startsWith("# Leger")) {
                hvavileser = 2;
                continue;
            } else if (linje.startsWith("# Resepter")) {
                hvavileser = 3;
                continue;
            } else {
                switch (hvavileser) {
                    case 0:
                        delt  = linje.split(", ");
                        pasienter.leggTil(new Pasient(delt[0], delt[1]));
                        break;
                    case 1:
                        delt  = linje.split(", ");
                        String lmNavn = delt[0];
                        String lmType = delt[1];
                        double lmPris = Double.parseDouble(delt[2]);
                        double lmMengde = Double.parseDouble(delt[3]);

                        if (lmType.equals("a")) {
                            legemidler.leggTil(new LegemiddelA(lmNavn, lmPris, lmMengde, Integer.parseInt(delt[4])));
                        } else if (lmType.equals("b")) {
                            legemidler.leggTil(new LegemiddelB(lmNavn, lmPris, lmMengde, Integer.parseInt(delt[4])));
                        } else if (lmType.equals("c")) {
                            legemidler.leggTil(new LegemiddelC(lmNavn, lmPris, lmMengde));
                        }
                        break;
                    case 2:
                        delt  = linje.split(", ");
                        int kommunenr = Integer.parseInt(delt[1]);
                        if (kommunenr == 0) {
                            leger.leggTil(new Lege(delt[0]));
                        } else {
                            leger.leggTil(new Fastlege(delt[0], kommunenr));
                        }
                        break;
                    case 3:
                        delt  = linje.split(", ");
                        String typeResept = delt[0];
                        Legemiddel lm = null;
                        for(Legemiddel l : legemidler) {
                            if(l.hentId() == Integer.parseInt(delt[1])) {
                                lm = l;
                                break;
                            }
                        }

                        Lege lege = null;
                        String legeNavn = delt[2];
                        for (Lege l : leger) {
                            if (l.hentNavn().equals(legeNavn)) {
                                lege = l;
                                break;
                            }
                        }

                        Pasient pasient = null;
                        int persID = Integer.parseInt(delt[3]);
                        for (Pasient p : pasienter) {
                            if (p.hentID() == persID) {
                                pasient = p;
                                break;
                            }
                        }

                        int reit;
                        if (delt.length == 4) {
                            reit = 1;
                        } else {
                            reit = Integer.parseInt(delt[4]);
                        }

                        Resept resept = null;
                        if (typeResept.equals("blaa")) {
                            resept = new BlaResept(lm, lege, pasient, reit);
                        } else if (typeResept.equals("hvit")) {
                            resept = new HvitResept(lm, lege, pasient, reit);
                        } else if (typeResept.equals("prevensjon")) {
                            resept = new Presept(lm, lege, pasient, reit);
                        } else if (typeResept.equals("militaer")) {
                            resept = new MilitaerResept(lm, lege, pasient, reit);
                        }
                        pasient.leggTilResept(resept);
                        lege.leggTilResept(resept);
                        resepter.leggTil(resept);
                        break;
                }
            }
            
        }
        s.close();
    }

    /**
     * Brukervalg 6
     */
    private static void avslutt() {
        //skrivTilFil();
        System.exit(0);
    }

    private static int lesValg() {
        Scanner tastatur = new Scanner(System.in);
        int valg = 0;
        try {
            valg = Integer.parseInt(tastatur.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ugyldig input!");
        }
        // tastatur.close();
        return valg;
    }

    private static Double lesValgDouble() {
        Scanner tastatur = new Scanner(System.in);
        Double valgDouble = 0.0;
        try {
            valgDouble = Double.parseDouble(tastatur.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ugyldig input!");
        }
        // tastatur.close();
        return valgDouble;

    }

    private static String lesValgString() {
        Scanner tastatur = new Scanner(System.in);
        String brukerlinje = tastatur.nextLine();
        // tastatur.close();
        return brukerlinje;
    }

    public static void skrivInstruksjoner() {
        System.out.println("1. Skriver ut en oversikt over personer, leger, legemidler og resepter.");
        System.out.println("2. Legg til nye elementer i systemet.");
        System.out.println("3. Bruk en gitt resept fra listen til en pasient.");
        System.out.println("4. Skriv diverse statistikk.");
        System.out.println("5. Skriv data til fil for lesing senere.");
        System.out.println("6. For aa avslutte.");
    }
}