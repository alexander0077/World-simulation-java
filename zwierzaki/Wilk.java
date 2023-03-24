package projekt_java_AS.zwierzaki;

import projekt_java_AS.Organizm;
import projekt_java_AS.Punkt;
import projekt_java_AS.Swiat;
import projekt_java_AS.Zwierze;

import java.awt.*;

public class Wilk extends Zwierze {
    private static final int ZASIEG_RUCHU_WILKA = 1;
    private static final int SZANSA_NA_RUCH_WILKA = 1;
    private static final int SILA_WILKA = 9;
    private static final int INICJATYWA_WILKA = 5;
    private static final Color KOLOR_WILKA = Color.CYAN;

    public Wilk(Swiat swiat, Punkt pozycja, int turaUrodzenia)
    {
        super(WILK, swiat, pozycja, turaUrodzenia, SILA_WILKA, INICJATYWA_WILKA, KOLOR_WILKA);
        this.zasieg_ruchu = ZASIEG_RUCHU_WILKA;
        this.szansa_na_ruch = SZANSA_NA_RUCH_WILKA;

    }

    public String nazwa(){
        return "Wilk";
    }
}
