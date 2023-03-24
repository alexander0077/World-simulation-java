package projekt_java_AS.zwierzaki;

import projekt_java_AS.Punkt;
import projekt_java_AS.Swiat;
import projekt_java_AS.Zwierze;

import java.awt.*;
import java.util.Random;

public class Lis extends Zwierze {
    static final int ZASIEG_RUCHU_LISA = 1;
    static final int SZANSA_NA_RUCH_LISA = 1;
    static final int SILA_LISA = 3;
    static final int INICJATYWA_LISA = 7;
    private static final Color KOLOR_LISA = Color.ORANGE;

    public Lis(Swiat swiat, Punkt pozycja, int turaUrodzenia)
    {
        super(LIS, swiat, pozycja, turaUrodzenia, SILA_LISA, INICJATYWA_LISA, KOLOR_LISA);
        this.zasieg_ruchu = ZASIEG_RUCHU_LISA;
        this.szansa_na_ruch = SZANSA_NA_RUCH_LISA;

    }

    public String nazwa(){
        return "Lis";
    }

    @Override
    public void akcja(){
        for(int i = 0; i < getZasiegRuchu(); i++){
            Punkt nowy_punkt = nowyPunkt(getPozycja());
            if(!(nowy_punkt == getPozycja())) {
                przesunOrganizm(nowy_punkt);
            }
        }
    }

    @Override
    public Punkt nowyPunkt(Punkt stary_punkt){
        boolean czy_jest_wolne_pole = false;
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                Punkt tmp = stary_punkt;
                tmp.setX(tmp.getX() + j);
                tmp.setY(tmp.getY() + i);
                if((tmp.getX() <= getSwiat().getRozmiarX() - 1) && (tmp.getX() >= 0) &&
                        (tmp.getY() <= getSwiat().getRozmiarY() - 1) && (tmp.getY() >= 0)){
                    if(!getSwiat().getCzyPoleJestZajete(tmp)) czy_jest_wolne_pole = true;
                }
            }
        }

        if(!czy_jest_wolne_pole) return stary_punkt;
        else {
            while (true){
                Random gen = new Random();
                int vecX = gen.nextInt(3) - 1;
                int vecY = gen.nextInt(3) - 1;
                if(vecX != 0 && vecY != 0){

                    if((stary_punkt.getX() + vecX <= getSwiat().getRozmiarX() - 1) && (stary_punkt.getX() + vecX >= 0) &&
                            (stary_punkt.getY() + vecY <= getSwiat().getRozmiarY() - 1) && (stary_punkt.getY() + vecY >= 0)){
                        Punkt tmp = new Punkt(stary_punkt.getX() + vecX, stary_punkt.getY() + vecY);
                        if(!getSwiat().getCzyPoleJestZajete(tmp)){
                            return tmp;
                        }
                    }
                }
            }
        }
    }

}
