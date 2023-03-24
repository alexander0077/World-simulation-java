package projekt_java_AS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;

import static projekt_java_AS.main.*;

public class Interface_gry extends JFrame implements KeyListener, ActionListener {

    public static final int ROZMIAR_X = 20;
    public static final int ROZMIAR_Y = 20;
    private static final int X_INTERFEJSU = 900;
    private static final int Y_INTERFEJSU = 600;
    public static final int X_MAPY = 600;
    public static final int Y_MAPY = 600;
    public static final int SZEROKOSC_PRZYCISKU_NOWEJ_TURY = 130;
    public static final int WYSOKOSC_PRZYCISKU_NOWEJ_TURY = 60;
    public static final int X_PRZYCISKU_NOWEJ_TURY = X_MAPY + (((X_INTERFEJSU - X_MAPY) - SZEROKOSC_PRZYCISKU_NOWEJ_TURY) / 2);
    public static final int Y_PRZYCISKU_NOWEJ_TURY = Y_MAPY - 100;
    static final int GORA = 101;
    static final int DOL = 102;
    static final int PRAWO = 103;
    static final int LEWO = 104;

    Canvas canvas;
    JTextArea komentarze, interfejs;

    JButton wczytaj_gre_button, zapisz_gre_button;
    JLabel text;
    JComboBox<String> menu;
    Swiat swiat;

    public Interface_gry() {
        setSize(X_INTERFEJSU, Y_INTERFEJSU);
        setTitle("Programowanie obiektowe projekt 2 - Aleksander Sarzyniak");

        initUI();

        addKeyListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setFocusable(true);

    }


    JTextArea textArea;
    void initUI() {
        swiat = new Swiat(ROZMIAR_X, ROZMIAR_Y, this);
        canvas = new Canvas(X_MAPY, Y_MAPY, swiat);
        canvas.setBounds(0, 0, X_MAPY, Y_MAPY);

        add(canvas);

        /*menu = new JComboBox<String>();
        menu.setBounds(200, 400, 100, 50);
        menu.addItem("Wolf");
        menu.addItem("Owca");
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                System.out.println(cb.getSelectedItem());
            }
        });
        add(menu);*/
        interfejs = new JTextArea();
        interfejs.setBounds(X_MAPY,  10, X_INTERFEJSU - X_MAPY - 10, 90);
        interfejs.setText(getInterfaceText());
        interfejs.setBackground(Color.ORANGE);
        interfejs.setLineWrap(true);
        interfejs.setEditable(false);
        interfejs.setWrapStyleWord(true);
        interfejs.setMargin(new Insets(5, 5, 5, 5));


        komentarze = new JTextArea();
        komentarze.setBounds(X_MAPY,  100, X_INTERFEJSU - X_MAPY - 10, Y_MAPY);
        komentarze.setText(swiat.getKomentarze());
        komentarze.setLineWrap(true);
        komentarze.setEditable(false);
        komentarze.setWrapStyleWord(true);
        komentarze.setMargin(new Insets(5, 5, 5, 5));
        add(komentarze);
        add(interfejs);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(swiat != null){
            int klawisz = e.getKeyCode();
            if (klawisz == KeyEvent.VK_ENTER) {
                //przejscie do nastepnej rundy
            } else if (swiat.getCzyCzlowiekZyje()) {
                if (klawisz == KeyEvent.VK_UP) {
                    swiat.getCzlowiek().setKierunekRuchu(GORA);
                } else if (klawisz == KeyEvent.VK_DOWN) {
                    swiat.getCzlowiek().setKierunekRuchu(DOL);
                } else if (klawisz == KeyEvent.VK_LEFT) {
                    swiat.getCzlowiek().setKierunekRuchu(LEWO);
                } else if (klawisz == KeyEvent.VK_RIGHT) {
                    swiat.getCzlowiek().setKierunekRuchu(PRAWO);
                } else if (klawisz == KeyEvent.VK_P) {
                    swiat.getCzlowiek().setCzyChceUzycSupermocy(true);
                }
            } else if (!swiat.getCzyCzlowiekZyje()) {
                swiat.setKomentarze(swiat.getKomentarze() + "Czlowiek umarl, nie mozesz im wiecej sterowac \n");
                return;
            }

            swiat.wykonajTure();
            canvas.zamalujMape();
            canvas.rysujOrganizmy();
            interfejs.setText(getInterfaceText());
            komentarze.setText(swiat.getKomentarze());
            swiat.setKomentarze("");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private String getInterfaceText(){
        return "Autor: Aleksander Sarzyniak, 188843 \nNumer tury:" + swiat.getNumerTury() + "\nRuch czlowiekiem: strzalki\nUzycie umiejetnosci specjalnej czlowieka: 'n' \n";
    }
}
