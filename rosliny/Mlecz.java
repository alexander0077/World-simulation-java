package projekt_java_AS.rosliny;

import projekt_java_AS.Roslina;
import projekt_java_AS.Punkt;
import projekt_java_AS.Swiat;

import java.awt.*;

public class Mlecz extends Roslina {
    static final int SILA_MLECZA = 0;
    static final int INICJATYWA_MLECZA = 0;
    static final int SZANSA_NA_ROZPRZESTRZENIANIE_MLECZA = 3; //wartosc w procentach
    private static final Color KOLOR_MLECZA = Color.GREEN;

    public Mlecz(Swiat swiat, Punkt pozycja, int turaUrodzenia)
    {
        super(MLECZ, swiat, pozycja, turaUrodzenia, SILA_MLECZA, INICJATYWA_MLECZA, SZANSA_NA_ROZPRZESTRZENIANIE_MLECZA, KOLOR_MLECZA);
    }

    public String nazwa(){
        return "Mlecz";
    }
}
