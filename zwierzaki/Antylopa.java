package projekt_java_AS.zwierzaki;

import projekt_java_AS.Punkt;
import projekt_java_AS.Swiat;
import projekt_java_AS.Zwierze;

import java.awt.*;

public class Antylopa extends Zwierze {
    static final int ZASIEG_RUCHU_ANTYLOPY = 2;
    static final int SZANSA_NA_RUCH_ANTYLOPY = 1;
    static final int SILA_ANTYLOPY = 4;
    static final int INICJATYWA_ANTYLOPY = 4;
    private static final Color KOLOR_ANTYLOPY = Color.MAGENTA;

    public Antylopa(Swiat swiat, Punkt pozycja, int turaUrodzenia)
    {
        super(ANTYLOPA, swiat, pozycja, turaUrodzenia, SILA_ANTYLOPY, INICJATYWA_ANTYLOPY, KOLOR_ANTYLOPY);
        this.zasieg_ruchu = ZASIEG_RUCHU_ANTYLOPY;
        this.szansa_na_ruch = SZANSA_NA_RUCH_ANTYLOPY;

    }

    public String nazwa(){
        return "Antylopa";
    }
}
