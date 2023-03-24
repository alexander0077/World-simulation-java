package projekt_java_AS;

import projekt_java_AS.Punkt;
import projekt_java_AS.Swiat;
import projekt_java_AS.Zwierze;

import java.awt.*;

public class Czlowiek extends Zwierze {
    static final int ZASIEG_RUCHU_CZLOWIEKA = 1;
    static final int SZANSA_NA_RUCH_CZLOWIEKA = 4;
    static final int SILA_CZLOWIEKA = 5;
    static final int INICJATYWA_CZLOWIEKA = 4;
    static final int NONE = 99;
    static final int GORA = 101;
    static final int DOL = 102;
    static final int PRAWO = 103;
    static final int LEWO = 104;
    static final int CZAS_LADOWANIA_SUPERMOCY = 5;
    static final int CZAS_DZIALANIA_SUPERMOCY = 5;
    private static final Color KOLOR_CZLOWIEKA = Color.BLUE;

    public Czlowiek(Swiat swiat, Punkt pozycja, int turaUrodzenia)
    {
        super(CZLOWIEK, swiat, pozycja, turaUrodzenia, SILA_CZLOWIEKA, INICJATYWA_CZLOWIEKA, KOLOR_CZLOWIEKA);
        this.zasieg_ruchu = ZASIEG_RUCHU_CZLOWIEKA;
        this.szansa_na_ruch = SZANSA_NA_RUCH_CZLOWIEKA;
        this.data_uzycia_supermocy = -11;
        this.czy_supermoc_dziala = false;
        this.chce_uzyc_supermocy = false;
    }

    public String nazwa(){
        return "Czlowiek";
    }

    @Override
    public Punkt nowyPunkt(Punkt stary_punkt){
        int q = 0;
        while ( q == 0){
            int vecX = 0;
            int vecY= 0;
            switch(getKierunekRuchu()){
                case GORA:
                    vecY = -1;
                    break;
                case DOL:
                    vecY = 1;
                    break;
                case PRAWO:
                    vecX = 1;
                    break;
                case LEWO:
                    vecX = -1;
                    break;
                default: break;
            }
            if((stary_punkt.getX() + vecX <= getSwiat().getRozmiarX() - 1) && (stary_punkt.getX() + vecX >= 0) &&
                    (stary_punkt.getY() + vecY <= getSwiat().getRozmiarY() - 1) && (stary_punkt.getY() + vecY >= 0)){
                stary_punkt.setX(stary_punkt.getX() + vecX);
                stary_punkt.setY(stary_punkt.getY() + vecY);
                q = 1;
            }
            else q = 1;
        }
        return stary_punkt;
    }

    @Override
    public void akcja()
    {
        if(getCzyChceUzycSupermocy()){
            handlerMocy();
            chce_uzyc_supermocy = false;
        }
        else{

            if(getSwiat().getCzyCzlowiekZyje()){
                Punkt nowy_punkt = nowyPunkt(getPozycja());

                if(getSwiat().getCzyPoleJestZajete(nowy_punkt)){
                    kolizja(getSwiat().getCoJestNaPolu(nowy_punkt));
                }
                else {
                    przesunOrganizm(nowy_punkt);
                    getSwiat().setKomentarze(getSwiat().getKomentarze() + nazwa() + " przemieszcza sie na pozycje [" + this.getPozycja().getX() + "," + this.getPozycja().getY() + "] \n");
                }

                if(getSwiat().getNumerTury() - data_uzycia_supermocy < CZAS_DZIALANIA_SUPERMOCY){
                    if(czy_supermoc_dziala) {
                        getSwiat().setKomentarze(getSwiat().getKomentarze() + "Tarcza Alzura jest jeszcze dostepna przez " + (CZAS_DZIALANIA_SUPERMOCY - getSwiat().getNumerTury() + data_uzycia_supermocy) +" rund \n");
                    }
                }
                else if (getSwiat().getNumerTury() - data_uzycia_supermocy == CZAS_DZIALANIA_SUPERMOCY){
                    getSwiat().setKomentarze(getSwiat().getKomentarze() + "Tarcza Alzura wlasnie przestala dzialac \n");
                    czy_supermoc_dziala = false;
                }

            }
        }
    }

    public void handlerMocy(){
        int kiedy_uzyta_ostatnio = getSwiat().getNumerTury() - data_uzycia_supermocy;

        if(getSwiat().getCzyCzlowiekZyje()){
            if(!czy_supermoc_dziala){
                if(kiedy_uzyta_ostatnio >= CZAS_LADOWANIA_SUPERMOCY + CZAS_DZIALANIA_SUPERMOCY) {
                    czy_supermoc_dziala = true;
                    data_uzycia_supermocy = getSwiat().getNumerTury();
                    getSwiat().setKomentarze(getSwiat().getKomentarze() + "Tarcza Alzura zostala wlaczona \n");
                }
                else {
                    getSwiat().setKomentarze(getSwiat().getKomentarze() + "Tarcza Alzura bedzie dostepna za " + (CZAS_LADOWANIA_SUPERMOCY + CZAS_DZIALANIA_SUPERMOCY - kiedy_uzyta_ostatnio) + " ruchow \n");
                }
            }
            else getSwiat().setKomentarze(getSwiat().getKomentarze() + "Czlowiek nie moze uzyc supermocy, moc juz dziala \n");
        }
        else
            getSwiat().setKomentarze(getSwiat().getKomentarze() + "Czlowiek nie moze uzyc supermocy, bo nie zyje \n");
    }

}
