package projekt_java_AS.rosliny;

import projekt_java_AS.Roslina;
import projekt_java_AS.Punkt;
import projekt_java_AS.Swiat;

import java.awt.*;

public class Trawa extends Roslina {
    static final int SILA_TRAWY = 0;
    static final int INICJATYWA_TRAWY = 0;
    static final int SZANSA_NA_ROZPRZESTRZENIANIE_TRAWY = 8; //wartosc w procentach
    private static final Color KOLOR_TRAWY = Color.LIGHT_GRAY;

    public Trawa(Swiat swiat, Punkt pozycja, int turaUrodzenia)
    {
        super(TRAWA, swiat, pozycja, turaUrodzenia, SILA_TRAWY, INICJATYWA_TRAWY, SZANSA_NA_ROZPRZESTRZENIANIE_TRAWY, KOLOR_TRAWY);
    }

    public String nazwa(){
        return "Trawa";
    }
}
