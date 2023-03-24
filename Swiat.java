package projekt_java_AS;

import com.sun.org.apache.xpath.internal.operations.Bool;
import projekt_java_AS.Organizm;
import projekt_java_AS.Punkt;
import projekt_java_AS.Spawn;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static java.lang.Math.floor;
import static projekt_java_AS.Organizm.*;

public class Swiat {
    private static final int ZAPELNIENIE_SWIATA_NA_WEJSCIU = 20;


    private int rozmiar_X, rozmiar_Y;
    private int numer_tury;
    private boolean czy_czlowiek_zyje;
    private boolean czy_koniec_gry;
    private Organizm[][] tablica;
    private ArrayList<Organizm> organizmy;
    private Organizm czlowiek;
    private Interface_gry gra;
    private String komentarze;

    public Swiat(int rozmiar_X, int rozmiar_Y, Interface_gry gra) {
        this.rozmiar_Y = rozmiar_Y;
        this.rozmiar_X = rozmiar_X;
        numer_tury = 0;
        czy_koniec_gry = false;
        czy_czlowiek_zyje = true;
        organizmy = new ArrayList<>();
        this.gra = gra;

        //stworzenie tablicy skaznikow na organizmy
        tablica = new Organizm[rozmiar_Y][rozmiar_X];
        for (int i = 0; i < rozmiar_Y; i++) {
            for (int j = 0; j < rozmiar_X; j++) {
                tablica[i][j] = null;
            }
        }

        generujSwiat();
    }

    public Swiat() {
        this.rozmiar_Y = 0;
        this.rozmiar_X = 0;
        numer_tury = 0;
        czy_czlowiek_zyje = true;
        czy_koniec_gry = false;
    }

    public void generujSwiat() {

        double ile_organizmow = floor(rozmiar_X * rozmiar_Y * ZAPELNIENIE_SWIATA_NA_WEJSCIU / 100);
        Punkt spawn_pozycja = losujWolnePole();
        Organizm tmp;
        tmp = Spawn.stworzOrganizm (CZLOWIEK, this, spawn_pozycja);
        dodajOrganizm(tmp);
        czlowiek = tmp;

        for (int i = 0; i < ile_organizmow - 1; i++) {
            spawn_pozycja = losujWolnePole();
            if ((spawn_pozycja.getX() == rozmiar_X + 1) && (spawn_pozycja.getY() == rozmiar_Y + 1)) {
                return;
            } else {
                tmp = Spawn.stworzOrganizm(losowyTyp(), this, spawn_pozycja);

                dodajOrganizm(tmp);
            }
        }

    }

    public Punkt losujWolnePole() {
        int wolne_pole = 0;
        int i = 0;
        int j = 0;
        Punkt tmp = new Punkt();

        while (i < rozmiar_Y && wolne_pole == 0) {
            while (j < rozmiar_X && wolne_pole == 0) {
                if (tablica[i][j] == null) {
                    wolne_pole++;
                }
                j++;
            }
            i++;
        }

        if (wolne_pole != 0) {
            int q = 0;
            while (q == 0) {
                Random gen = new Random();
                int x = gen.nextInt(rozmiar_X);
                int y = gen.nextInt(rozmiar_Y);
                if (tablica[y][x] == null) {
                    tmp.setY(y);
                    tmp.setX(x);
                    q = 1;
                }
            }
        } else {
            tmp.setY(rozmiar_Y + 1);
            tmp.setX(rozmiar_X + 1);
        }
        return tmp;
    }

    public void wykonajTure(){
        sortujVector();
        for (int i = 0; i < organizmy.size(); i++) {
            if ((organizmy.get(i).getCzyZyje()) && (organizmy.get(i).getTuraUrodzenia() < this.getNumerTury())) organizmy.get(i).akcja();
        }

        for (int i = 0; i < organizmy.size(); i++) {
            if (!organizmy.get(i).getCzyZyje()) {
                organizmy.remove(i);
                i--;
            }
        }

        numer_tury++;
    }

    public void sortujVector(){
        Collections.sort(organizmy, new Comparator<Organizm>() {
            @Override
            public int compare(Organizm pierwszy, Organizm drugi) {
                if (pierwszy.getInicjatywa() != drugi.getInicjatywa())
                    return Integer.valueOf(drugi.getInicjatywa()).compareTo(pierwszy.getInicjatywa());
                else
                    return Integer.valueOf(pierwszy.getTuraUrodzenia()).compareTo(drugi.getTuraUrodzenia());
            }
        });
    }

    public boolean czyJestWolnePoleObok(Punkt pole){
        for(int i = -1; i < 2; i ++){
            for(int j = -1; j < 2; j++){
                int y = pole.getY() + j;
                int x = pole.getX() + i;
                if(y < rozmiar_Y  && y > -1 && x > -1 && x < rozmiar_X){
                    if(tablica[y][x] == null) return true;
                }
            }
        }
        return false;
    }

    public Punkt wolnePoleObok(Punkt poleWejsciowe){
        boolean wolne_pole = czyJestWolnePoleObok(poleWejsciowe);
        int x, y;
        if (wolne_pole){
            int q = 0;
            while (q == 0) {
                Random gen = new Random();
                x = gen.nextInt(3) - 1 + poleWejsciowe.getX();
                y = gen.nextInt(3) - 1 + poleWejsciowe.getY();
                if(y < rozmiar_Y && y > -1 && x > -1 && x < rozmiar_X){
                    if (tablica[y][x] == null){
                        Punkt tmp = new Punkt(x, y);
                        return tmp;
                    }
                }
            }
        }
        return poleWejsciowe;
    };

    public void saveSwiat(String nazwa){
        try {
            nazwa +=".txt";
            File plik = new File(nazwa);
            plik.createNewFile();

            PrintWriter fout = new PrintWriter(plik);
            fout.print(rozmiar_X + " " + rozmiar_Y + " " + numer_tury + " " + czy_czlowiek_zyje + " " + czy_koniec_gry + "\n");
            for (int i = 0; i < organizmy.size(); i++) {
                fout.print(organizmy.get(i).getTypOrganizmu() + " " + organizmy.get(i).getPozycja().getX() + " " + organizmy.get(i).getPozycja().getY() + " ");
                fout.print(organizmy.get(i).getSila() + " " + organizmy.get(i).getTuraUrodzenia() + " " + organizmy.get(i).getCzyZyje() + " ");
                if (organizmy.get(i).getTypOrganizmu() > 5) { //todo BEZ TEGO!
                    fout.print(organizmy.get(i).getSzansaNaRozprzestrzenienie() + " ");
                }
                if (organizmy.get(i).getTypOrganizmu() == CZLOWIEK) {
                    fout.print(czlowiek.getKierunekRuchu() + " " + czlowiek.getCzyChceUzycSupermocy() + " " + czlowiek.getDataUzyciaSupermocy() + " " + czlowiek.getCzySupermocDziala());
                }
                fout.println();
            }
            fout.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static Swiat odtworzSwiat(String nazwa) {
        try {
            nazwa += ".txt";
            File plik = new File(nazwa);

            Scanner scanner = new Scanner(plik);
            String line = scanner.nextLine();
            String[] properties = line.split(" ");
            int rozmiar_X = Integer.parseInt(properties[0]);
            int rozmiar_Y = Integer.parseInt(properties[1]);
            Swiat nowySwiat = new Swiat(rozmiar_X, rozmiar_Y, null);
            int numer_tury = Integer.parseInt(properties[2]);
            nowySwiat.numer_tury = numer_tury;
            boolean czy_czlowiek_zyje = Boolean.parseBoolean(properties[3]);
            nowySwiat.czy_czlowiek_zyje = czy_czlowiek_zyje;
            boolean czy_koniec_gry = Boolean.parseBoolean(properties[4]);
            nowySwiat.czy_koniec_gry = czy_koniec_gry;
            nowySwiat.czlowiek = null;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                properties = line.split(" ");
                int typOrganizmu = Integer.parseInt(properties[0]);
                int x = Integer.parseInt(properties[1]);
                int y = Integer.parseInt(properties[2]);
                Organizm tmpOrganizm = Spawn.stworzOrganizm(typOrganizmu, nowySwiat, new Punkt(x, y));
                int sila = Integer.parseInt(properties[3]);
                tmpOrganizm.setSila(sila);
                int turaUrodzenia = Integer.parseInt(properties[4]);
                tmpOrganizm.setTuraUrodzenia(turaUrodzenia);
                boolean czy_zyje = Boolean.parseBoolean(properties[5]);
                tmpOrganizm.setCzyZyje(czy_zyje);

                if (typOrganizmu > 5){
                    int szansa_na_rozprzestrzenianie = Integer.parseInt(properties[6]);
                    tmpOrganizm.setSzansaNaRozprzestrzenienie(szansa_na_rozprzestrzenianie);
                }

                if (typOrganizmu == CZLOWIEK) {
                    nowySwiat.czlowiek = (Czlowiek) tmpOrganizm;
                    int kierunek_ruchu = Integer.parseInt(properties[6]);
                    nowySwiat.czlowiek.setKierunekRuchu(kierunek_ruchu);
                    boolean czy_chce_uzyc_mocy = Boolean.parseBoolean(properties[7]);
                    nowySwiat.czlowiek.setCzyChceUzycSupermocy(czy_chce_uzyc_mocy);
                    int data_uzycia_supermocy = Integer.parseInt(properties[8]);
                    nowySwiat.czlowiek.setDataUzyciaSupermocy(data_uzycia_supermocy);
                    boolean czy_supermoc_dziala = Boolean.parseBoolean(properties[9]);
                    nowySwiat.czlowiek.setCzySupermocDziala(czy_supermoc_dziala);
                }
                nowySwiat.dodajOrganizm(tmpOrganizm);
            }
            scanner.close();
            return nowySwiat;
        } catch (
                IOException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public void dodajOrganizm(Organizm organizm)
    {
        organizmy.add(organizm);
        tablica[organizm.getPozycja().getY()][organizm.getPozycja().getX()] = organizm;
    }

    public void usunOrganizm(Organizm organizm){
        tablica[organizm.getPozycja().getY()][organizm.getPozycja().getX()] = null;
        organizm.setCzyZyje(false);
        if(organizm.getTypOrganizmu() == CZLOWIEK){
            czlowiek = null;
            czy_czlowiek_zyje = false;
        }
    }

    /** GETERY I SETERY **/

    public void setKomentarze(String nowy){
        this.komentarze = nowy;
    }

    public String getKomentarze(){
        return komentarze;
    }

    public void setRozmiarX(int rozmiar_X){
        this.rozmiar_X = rozmiar_X;
    }

    public void setRozmiarY(int rozmiar_Y){
        this.rozmiar_Y = rozmiar_Y;
    }

    public int getRozmiarX(){
        return rozmiar_X;
    }

    public int getRozmiarY(){
        return rozmiar_Y;
    }

    public boolean getCzyKoniecGry(){
        return czy_koniec_gry;
    }

    public int getNumerTury(){
        return numer_tury;
    }

    public void setNumerTury(int numer) {
        this.numer_tury = numer;
    }

    public boolean getCzyCzlowiekZyje(){
        return czy_czlowiek_zyje;
    }

    public void setCzyCzlowiekZyje(boolean czy_czlowiek_zyje){
        this.czy_czlowiek_zyje = czy_czlowiek_zyje;
    }

    public void setCzyKoniecGry(boolean czy_jest_koniec_gry){
        this.czy_koniec_gry = czy_jest_koniec_gry;
    }

    public Organizm[][] getTablica()
    {
        return tablica;
    }

    public boolean getCzyPoleJestZajete(Punkt punkt)
    {
        if (tablica[punkt.getY()][punkt.getX()] == null) return false;
        else return true;
    }

    public Organizm getCoJestNaPolu(Punkt pole)
    {
        return tablica[pole.getY()][pole.getX()];
    }

    public Organizm getCzlowiek(){
        return czlowiek;
    }

    public ArrayList<Organizm> getOrganizmy(){
        return organizmy;
    }

}