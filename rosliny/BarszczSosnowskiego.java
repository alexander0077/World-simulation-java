package projekt_java_AS.rosliny;

import projekt_java_AS.Roslina;
import projekt_java_AS.Punkt;
import projekt_java_AS.Swiat;

import java.awt.*;
import java.util.Random;

public class BarszczSosnowskiego extends Roslina {
    static final int SILA_BARSZCZU_SOSNOWSKIEGO = 10;
    static final int INICJATYWA_BARSZCZU_SOSNOWSKIEGO = 0;
    static final int SZANSA_NA_ROZPRZESTRZENIANIE_BARSZCZU_SOSNOWSKIEGO = 5; //wartosc w procentach
    private static final Color KOLOR_BARSZCZU = Color.YELLOW;

    public BarszczSosnowskiego(Swiat swiat, Punkt pozycja, int turaUrodzenia)
    {
        super(BARSZCZ_SOSNOWSKIEGO, swiat, pozycja, turaUrodzenia, SILA_BARSZCZU_SOSNOWSKIEGO, INICJATYWA_BARSZCZU_SOSNOWSKIEGO, SZANSA_NA_ROZPRZESTRZENIANIE_BARSZCZU_SOSNOWSKIEGO, KOLOR_BARSZCZU);
    }

    public String nazwa(){
        return "Barszcz Sosnowskiego";
    }

    @Override
    public void akcja(){
        Random gen = new Random();
        int tmp = gen.nextInt(100);
        if(tmp < SZANSA_NA_ROZPRZESTRZENIANIE_BARSZCZU_SOSNOWSKIEGO) rozprzestrzenianie();
        int x = this.getPozycja().getX();
        int y = this.getPozycja().getY();
        for(int i = -1; i < 2; i ++){
            for(int j = -1; j < 2; j++){
                int tmpx = x + i;
                int tmpy = y + j;
                if(tmpy < getSwiat().getRozmiarY() && tmpy > -1 && tmpx > -1 && tmpx < getSwiat().getRozmiarX()){
                    Punkt cel = new Punkt(tmpx, tmpy);
                    if(getSwiat().getCzyPoleJestZajete(cel)){
                        if(getSwiat().getCoJestNaPolu(cel).getTypOrganizmu() < 6){
                            getSwiat().setKomentarze(getSwiat().getKomentarze() + getSwiat().getCoJestNaPolu(cel).nazwa() + " pojawia sie za blisko Barszczu Sosnowskiego na polu [" + getSwiat().getCoJestNaPolu(cel).getPozycja().getX() + "," + getSwiat().getCoJestNaPolu(cel).getPozycja().getY() + "] i umiera \n");
                            getSwiat().usunOrganizm(getSwiat().getCoJestNaPolu(cel));
                        }
                    }
                }
            }
        }
    }

}
