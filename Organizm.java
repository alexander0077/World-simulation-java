package projekt_java_AS;

import java.awt.*;
import java.util.Random;

public abstract class Organizm {
    public static final short CZLOWIEK = 0;
    public static final short WILK = 1;
    public static final short OWCA = 2;
    public static final short LIS = 3;
    public static final short ZOLW = 4;
    public static final short ANTYLOPA = 5;
    public static final short TRAWA = 6;
    public static final short MLECZ = 7;
    public static final short GUARANA = 8;
    public static final short WILCZE_JAGODY = 9;
    public static final short BARSZCZ_SOSNOWSKIEGO = 10;

    protected static final int LICZBA_GATUNKOW = 10; /** ILOSC GATUNKOW DO ZMIENIANIA **/
    protected static final int MINIMALNY_WIEK_ROZMNAZANIA = 0;

    private int sila;
    private int inicjatywa;
    private int tura_urodzenia;
    private boolean czy_zyje;
    private int kierunek_ruchu;
    int szansa_na_rozprzestrzenianie;
    private Punkt pozycja;
    private short typ;
    private Swiat swiat;
    boolean czy_supermoc_dziala;
    int data_uzycia_supermocy;
    boolean chce_uzyc_supermocy;
    private Color kolor;

    public abstract void akcja();

    public abstract void kolizja(Organizm other);

    public abstract String nazwa();

    public void rysuj(Graphics g){
        g.setColor(getKolor());
        g.fillRect(getPozycja().getX() * Interface_gry.X_MAPY / Interface_gry.ROZMIAR_X, getPozycja().getY() * Interface_gry.Y_MAPY / Interface_gry.ROZMIAR_Y, Interface_gry.X_MAPY / Interface_gry.ROZMIAR_X, Interface_gry.Y_MAPY / Interface_gry.ROZMIAR_Y);
    }

    public static int losowyTyp() //losuje typ oprocz cyberowcy oraz czlowieka
    {
        Random gen = new Random();
        int tmp = gen.nextInt(LICZBA_GATUNKOW) + 1;
        return tmp;
    }

    public Organizm(short typOrganizmu, Swiat swiat, Punkt pozycja, int tura_urodzenia, int sila, int inicjatywa, Color kolor)
    {
        this.typ = typOrganizmu;
        this.swiat = swiat;
        this.pozycja = pozycja;
        this.tura_urodzenia = tura_urodzenia;
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        czy_zyje = true;
        this.kolor = kolor;
    }

    public void przesunOrganizm(Punkt gdzie)
    {
        swiat.getTablica()[pozycja.getY()][pozycja.getX()] = null;
        swiat.getTablica()[gdzie.getY()][gdzie.getX()] = this;
        pozycja.setX(gdzie.getX());
        pozycja.setY(gdzie.getY());
    }

    /** GETERY I SETERY **/

    public int getSzansaNaRozprzestrzenienie(){
        return szansa_na_rozprzestrzenianie;
    }

    public void setSzansaNaRozprzestrzenienie(int szansa_na_rozprzestrzenianie){
        this.szansa_na_rozprzestrzenianie = szansa_na_rozprzestrzenianie;
    }

    public void setCzyZyje(boolean zyje) {
        czy_zyje = zyje;
    }

    public boolean getCzyZyje() {
        return czy_zyje;
    }

    public short getTypOrganizmu()
    {
        return typ;
    }

    public void setTypOrganizmu(short typOrganizmu)
    {
        this.typ = typOrganizmu;
    }

    public void setKierunekRuchu(int kierunek){
        this.kierunek_ruchu = kierunek;
    }

    public int getKierunekRuchu() {
        return kierunek_ruchu;
    }

    public Punkt getPozycja()
    {
        return new Punkt(pozycja.getX(), pozycja.getY());
    }

    public void setPozycja(int x, int y)
    {
        pozycja.setX(x);
        pozycja.setY(y);
    }

    public Swiat getSwiat()
    {
        return swiat;
    }

    public void setSwiat(Swiat swiat)
    {
        this.swiat = swiat;
    }

    public int getTuraUrodzenia()
    {
        return tura_urodzenia;
    }

    public void setTuraUrodzenia(int tura_urodzenia)
    {
        this.tura_urodzenia = tura_urodzenia;
    }


    public int getSila()
    {
        return sila;
    }

    public void setSila(int sila)
    {
        this.sila = sila;
    }

    public int getInicjatywa()
    {
        return inicjatywa;
    }

    public void setInicjatywa(int inicjatywa)
    {
        this.inicjatywa = inicjatywa;
    }

    public boolean getCzySupermocDziala(){
        return czy_supermoc_dziala;
    }

    public int getDataUzyciaSupermocy() {
        return data_uzycia_supermocy;
    }

    public int getTyp() {
        return typ;
    }

    public boolean getCzyChceUzycSupermocy(){
        return chce_uzyc_supermocy;
    }

    public void setCzyChceUzycSupermocy(boolean czy){
        chce_uzyc_supermocy = czy;
    }

    public void setDataUzyciaSupermocy(int data){
        data_uzycia_supermocy = data;
    }

    public void setCzySupermocDziala(boolean czy){
        czy_supermoc_dziala = czy;
    }

    public Color getKolor(){ return kolor; }

}

