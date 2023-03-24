package projekt_java_AS;

import projekt_java_AS.Organizm;
import projekt_java_AS.Punkt;
import projekt_java_AS.zwierzaki.*;
import projekt_java_AS.rosliny.*;

import static projekt_java_AS.Organizm.*;

public class Spawn {
    public static Organizm stworzOrganizm
            (int typ, Swiat swiat, Punkt pozycja) {
        switch (typ) {
            case WILK:
                return new Wilk(swiat, pozycja, swiat.getNumerTury());
            case OWCA:
                return new Owca(swiat, pozycja, swiat.getNumerTury());
            case LIS:
                return new Lis(swiat, pozycja, swiat.getNumerTury());
            case ZOLW:
                return new Zolw(swiat, pozycja, swiat.getNumerTury());
            case ANTYLOPA:
                return new Antylopa(swiat, pozycja, swiat.getNumerTury());
            case CZLOWIEK:
                return new Czlowiek(swiat, pozycja, swiat.getNumerTury());
            case TRAWA:
                return new Trawa(swiat, pozycja, swiat.getNumerTury());
            case MLECZ:
                return new Mlecz(swiat, pozycja, swiat.getNumerTury());
            case GUARANA:
                return new Guarana(swiat, pozycja, swiat.getNumerTury());
            case WILCZE_JAGODY:
                return new WilczeJagody(swiat, pozycja, swiat.getNumerTury());
            case BARSZCZ_SOSNOWSKIEGO:
                return new BarszczSosnowskiego(swiat, pozycja, swiat.getNumerTury());
            default:
                return null;//UNDEFINED TYP
        }
    }
}
