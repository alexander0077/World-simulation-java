package projekt_java_AS.rosliny;

import projekt_java_AS.Roslina;
import projekt_java_AS.Punkt;
import projekt_java_AS.Swiat;

import java.awt.*;

public class Guarana extends Roslina {
    static final int SILA_GUARANY = 0;
    static final int INICJATYWA_GUARANY = 0;
    static final int SZANSA_NA_ROZPRZESTRZENIANIE_GUARANY = 5; //wartosc w procentach
    private static final Color KOLOR_GUARANY = Color.PINK;

    public Guarana(Swiat swiat, Punkt pozycja, int turaUrodzenia)
    {
        super(GUARANA, swiat, pozycja, turaUrodzenia, SILA_GUARANY, INICJATYWA_GUARANY, SZANSA_NA_ROZPRZESTRZENIANIE_GUARANY, KOLOR_GUARANY);
    }

    public String nazwa(){
        return "Guarana";
    }
}

