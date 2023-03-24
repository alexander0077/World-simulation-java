package projekt_java_AS.zwierzaki;

import projekt_java_AS.Punkt;
import projekt_java_AS.Swiat;
import projekt_java_AS.Zwierze;

import java.awt.*;

public class Zolw extends Zwierze {
    static final int ZASIEG_RUCHU_ZOLWIA = 1;
    static final int SZANSA_NA_RUCH_ZOLWIA = 4;
    static final int SILA_ZOLWIA = 2;
    static final int INICJATYWA_ZOLWIA = 1;
    private static final Color KOLOR_ZOLWIA = Color.DARK_GRAY;

    public Zolw(Swiat swiat, Punkt pozycja, int turaUrodzenia)
    {
        super(ZOLW, swiat, pozycja, turaUrodzenia, SILA_ZOLWIA, INICJATYWA_ZOLWIA, KOLOR_ZOLWIA);
        this.zasieg_ruchu = ZASIEG_RUCHU_ZOLWIA;
        this.szansa_na_ruch = SZANSA_NA_RUCH_ZOLWIA;

    }

    public String nazwa(){
        return "Zolw";
    }
}
