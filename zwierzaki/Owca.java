package projekt_java_AS.zwierzaki;

import projekt_java_AS.Punkt;
import projekt_java_AS.Swiat;
import projekt_java_AS.Zwierze;

import java.awt.*;

public class Owca extends Zwierze {
    static final int ZASIEG_RUCHU_OWCY = 1;
    static final int SZANSA_NA_RUCH_OWCY = 1;
    static final int SILA_OWCY = 4;
    static final int INICJATYWA_OWCY = 4;
    private static final Color KOLOR_OWCY = Color.WHITE;

    public Owca(Swiat swiat, Punkt pozycja, int turaUrodzenia)
    {
        super(OWCA, swiat, pozycja, turaUrodzenia, SILA_OWCY, INICJATYWA_OWCY, KOLOR_OWCY);
        this.zasieg_ruchu = ZASIEG_RUCHU_OWCY;
        this.szansa_na_ruch = SZANSA_NA_RUCH_OWCY;

    }

    public String nazwa(){
        return "Owca";
    }
}
