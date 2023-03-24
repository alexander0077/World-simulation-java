package projekt_java_AS;

import projekt_java_AS.Organizm;
import projekt_java_AS.Punkt;
import projekt_java_AS.Spawn;

import java.awt.*;
import java.util.Random;

public abstract class Zwierze extends Organizm {

    protected int zasieg_ruchu;
    protected int szansa_na_ruch;

    protected Zwierze(short typOrganizmu, Swiat swiat, Punkt pozycja, int turaUrodzenia, int sila, int inicjatywa, Color kolor) {
        super(typOrganizmu, swiat, pozycja, turaUrodzenia, sila, inicjatywa, kolor);
    }

    @Override
    public void akcja() {
        Random gen = new Random();
        int tmp = gen.nextInt(getSzansaNaRuch());
        if (tmp == 0) {
            for (int i = 0; i < getZasiegRuchu(); i++) {
                Punkt nowy_punkt = nowyPunkt(getPozycja());
                if (getSwiat().getCzyPoleJestZajete(nowy_punkt)) {
                    if (this.czyMozeUciecOdWalki(nowy_punkt)) ucieczkaOdWalki(nowy_punkt);
                    else kolizja(getSwiat().getCoJestNaPolu(nowy_punkt));
                } else {
                    przesunOrganizm(nowy_punkt);
                    if (i + 1 == zasieg_ruchu) {
                        getSwiat().setKomentarze(getSwiat().getKomentarze() + nazwa() + " przemieszcza sie na pozycje [" + this.getPozycja().getX() + "," + this.getPozycja().getY() + "] \n");
                    }
                }
            }
        }
    }

    @Override
    public void kolizja(Organizm inny){
        if(inny.getTypOrganizmu() == this.getTyp()){
            if(this.getTyp() != CZLOWIEK){
                rozmnazanie(inny);
            }
        }
    else {
            if(czyOdbijeAtak(inny)) odbijAtak(inny);
            else if(this.getSila() < inny.getSila()) {
                if(Roslina.czyRoslinaJestTrujaca(inny)) {
                    zwierzeSieStrulo(inny);
                }
                else{
                    getSwiat().usunOrganizm(this);
                    getSwiat().setKomentarze(getSwiat().getKomentarze() + inny.nazwa() + " (sila: " + inny.getSila() + ") zabija " + this.nazwa() + " (sila: " + this.getSila() + ") na polu " + "[" + inny.getPozycja().getX() + "," + inny.getPozycja().getY() + "] \n");
                }
            }
        else {
                Punkt tmp = inny.getPozycja();
                if(inny.getTypOrganizmu() < 6){
                        getSwiat().setKomentarze(getSwiat().getKomentarze() + this.nazwa() + " (sila: " + this.getSila() + ") zabija " + inny.nazwa() + " (sila: " + inny.getSila() + ") na polu " + "[" + tmp.getX() + "," + tmp.getY() + "] \n");
                }
                else {
                    getSwiat().setKomentarze(getSwiat().getKomentarze() + this.nazwa() + " zjada " + inny.nazwa() + " na polu [" + tmp.getX() + "," + tmp.getY() + "]");
                    if(Roslina.getCzyRoslinaWzmacnia(inny)) organizmSieWzmacnia();
                    getSwiat().setKomentarze(getSwiat().getKomentarze() + "\n");
                }
                getSwiat().usunOrganizm(inny);
                przesunOrganizm(tmp);

            }
        }
    }

    public void organizmSieWzmacnia(){
        this.setSila(this.getSila() + 3);
        getSwiat().setKomentarze(getSwiat().getKomentarze() + ", jego sila wzrasta o 3 i wynosi: " + this.getSila());
    }

    public boolean czyMozeUciecOdWalki(Punkt nowy_punkt){
        if(this.getTypOrganizmu() == ANTYLOPA && getSwiat().getCoJestNaPolu(nowy_punkt).getTypOrganizmu() < 5) return true;
    else return false;
    }

    public void ucieczkaOdWalki(Punkt nowy_punkt){
        Random gen = new Random();
        int tmp2 = gen.nextInt(2);
        if(tmp2 == 0){
            kolizja(getSwiat().getCoJestNaPolu(nowy_punkt));
        }
        else {
            Punkt nowy_punkt_po_uniku = getSwiat().wolnePoleObok(nowy_punkt);
            przesunOrganizm(nowy_punkt_po_uniku);
            getSwiat().setKomentarze(getSwiat().getKomentarze() + "Antylopa unika walki z " + getSwiat().getCoJestNaPolu(nowy_punkt).nazwa() + " na polu [" + nowy_punkt.getX() + "," + nowy_punkt.getY() + "] i ucieka na pole " + "[" + this.getPozycja().getX() + "," + this.getPozycja().getY() + "] \n");
        }
    }

    public boolean czyOdbijeAtak(Organizm inny){
        if((inny.getTypOrganizmu() == ZOLW) && this.getSila() < 5) return true;
    else if(inny.getTypOrganizmu() == CZLOWIEK){
            if(getSwiat().getCzlowiek().getCzySupermocDziala()) return true;
        else return false;
        }
        else return false;
    }

    public void odbijAtak(Organizm inny){
        if(inny.getTypOrganizmu() == ZOLW){
            getSwiat().setKomentarze(getSwiat().getKomentarze() + "Zolw odbil " + this.nazwa() + " na polu [" + this.getPozycja().getX() + "," + this.getPozycja().getY() + "] \n");
        }
        else {
            Punkt nowy = getSwiat().wolnePoleObok(inny.getPozycja()); //nie musimy wywolywac wczesniej czy wolne pole istnieje bo na pewno istnieje
            this.przesunOrganizm(nowy);
            getSwiat().setKomentarze(getSwiat().getKomentarze() + "Czlowiek za pomoca Tarczy Alzura odbil " + this.nazwa() + " na pole [" + nowy.getX() + "," + nowy.getY() + "] \n");
        }
    }

    public void zwierzeSieStrulo(Organizm inny){
        getSwiat().usunOrganizm(this);
        getSwiat().usunOrganizm(inny);
        getSwiat().setKomentarze(getSwiat().getKomentarze() + this.nazwa() + " zjada trujace " + inny.nazwa() + " na polu " + "[" + this.getPozycja().getX() + "," + this.getPozycja().getY() + "] i umiera \n");
    }

    public void rozmnazanie(Organizm inny){
        int nr_tury = getSwiat().getNumerTury();
        Swiat swiat = getSwiat();
        Punkt nowe_pole = new Punkt(-1,-1);
        if((nr_tury - this.getTuraUrodzenia() > MINIMALNY_WIEK_ROZMNAZANIA) && (nr_tury - inny.getTuraUrodzenia() > MINIMALNY_WIEK_ROZMNAZANIA)) {
            if(getSwiat().czyJestWolnePoleObok(this.getPozycja())){
                if(swiat.czyJestWolnePoleObok(this.getPozycja())){
                    Random gen = new Random();
                    int tmp = gen.nextInt(2);
                    if(tmp == 0) nowe_pole = swiat.wolnePoleObok(this.getPozycja());
                    else nowe_pole = swiat.wolnePoleObok(inny.getPozycja());
                }
           else nowe_pole = swiat.wolnePoleObok(this.getPozycja());
            }
       else if(!swiat.czyJestWolnePoleObok(this.getPozycja())){
                getSwiat().setKomentarze(getSwiat().getKomentarze() + this.nazwa() + " nie moze sie rozmnozyc bo nie ma miejsca wokol pola [" + this.getPozycja().getX() + "," + this.getPozycja().getY()
                                + "] oraz [" + inny.getPozycja().getX() + "," + inny.getPozycja().getY() + "] \n");
            }
       else nowe_pole = swiat.wolnePoleObok(inny.getPozycja());
        }
   else {
            getSwiat().setKomentarze(getSwiat().getKomentarze() + this.nazwa() + " nie moze sie rozmnozyc bo jeden z organizmow jest za mlody na polu " + "["
                            + this.getPozycja().getX() + "," + this.getPozycja().getY() + "] \n");
        }

        if(nowe_pole.getX() > -1) {
            Organizm tmp = Spawn.stworzOrganizm(this.getTypOrganizmu(), getSwiat(), nowe_pole);
            swiat.dodajOrganizm(tmp);
            getSwiat().setKomentarze(getSwiat().getKomentarze() + this.nazwa() + " narodzil sie na polu [" + nowe_pole.getX() + "," + nowe_pole.getY() + "] \n");
        }
    }

    public Punkt nowyPunkt(Punkt stary_punkt){
        int q = 0;
        while ( q == 0){
            Random gen = new Random();
            int vecX = gen.nextInt(3) - 1;
            int vecY = gen.nextInt(3) - 1;
            if(vecX != 0 && vecY != 0){
                if((stary_punkt.getX() + vecX <= getSwiat().getRozmiarX() - 1) && (stary_punkt.getX() + vecX >= 0) &&
                        (stary_punkt.getY() + vecY <= getSwiat().getRozmiarY() - 1) && (stary_punkt.getY() + vecY >= 0)){
                    stary_punkt.setX(stary_punkt.getX() + vecX);
                    stary_punkt.setY(stary_punkt.getY() + vecY);
                    q = 1;
                }
            }
        }
        return stary_punkt;
    }

    /** GETTERY I SETTERY **/

    public int getZasiegRuchu()
    {
        return zasieg_ruchu;
    }

    public void setZasiegRuchu(int zasieg)
    {
        this.zasieg_ruchu = zasieg;
    }

    public int getSzansaNaRuch() {
        return szansa_na_ruch;
    }

    public void setSzansaNaRuch(int szansa)
    {
        this.szansa_na_ruch = szansa;
    }

}
