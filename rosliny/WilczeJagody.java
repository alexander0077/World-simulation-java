package projekt_java_AS.rosliny;

import projekt_java_AS.Roslina;
import projekt_java_AS.Punkt;
import projekt_java_AS.Swiat;

import java.awt.*;

public class WilczeJagody extends Roslina {
    static final int SILA_WILCZYCH_JAGOD = 99;
    static final int INICJATYWA_WILCZYCH_JAGOD = 0;
    static final int SZANSA_NA_ROZPRZESTRZENIANIE_WILCZYCH_JAGOD = 5; //wartosc w procentach
    private static final Color KOLOR_JAGOD = Color.RED;

    public WilczeJagody(Swiat swiat, Punkt pozycja, int turaUrodzenia)
    {
        super(WILCZE_JAGODY, swiat, pozycja, turaUrodzenia, SILA_WILCZYCH_JAGOD, INICJATYWA_WILCZYCH_JAGOD, SZANSA_NA_ROZPRZESTRZENIANIE_WILCZYCH_JAGOD, KOLOR_JAGOD);
    }

    public String nazwa(){
        return "Wilcze Jagody";
    }
}