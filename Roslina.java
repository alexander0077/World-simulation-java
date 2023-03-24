package projekt_java_AS;

import java.awt.*;
import java.util.Random;

public abstract class Roslina extends Organizm {
    public Roslina(short typOrganizmu, Swiat swiat, Punkt pozycja, int turaUrodzenia, int sila, int inicjatywa, int szansa_na_rozprzestrzenianie, Color kolor)
    {
        super(typOrganizmu, swiat, pozycja, turaUrodzenia, sila, inicjatywa, kolor);
        this.szansa_na_rozprzestrzenianie = szansa_na_rozprzestrzenianie;
    }

    @Override
    public void akcja() {
        Random gen = new Random();
        int tmp = gen.nextInt(100);
        if(tmp < szansa_na_rozprzestrzenianie) rozprzestrzenianie();
    }

    @Override
    public void kolizja(Organizm inny) {}

    public void rozprzestrzenianie(){
        Punkt nowy, stary;
        stary = this.getPozycja();
        nowy = getSwiat().wolnePoleObok(stary);
        if(!(nowy == stary)){
            Organizm nowy_organizm = Spawn.stworzOrganizm(this.getTypOrganizmu(), this.getSwiat(), nowy);
            getSwiat().dodajOrganizm(nowy_organizm);
            getSwiat().setKomentarze(getSwiat().getKomentarze() + this.nazwa() + " rozprzestrzenil sie na pole [" + nowy.getX() + "," + nowy.getY() + "] \n");
        }
    }

    public static boolean getCzyRoslinaWzmacnia(Organizm roslina){
        if(roslina.getTypOrganizmu() == GUARANA) return true;
        else return false;

    }

    public static boolean czyRoslinaJestTrujaca(Organizm inny){
        if((inny.getTypOrganizmu() == WILCZE_JAGODY)||(inny.getTypOrganizmu() == BARSZCZ_SOSNOWSKIEGO)) return true;
        return false;
    }

}
