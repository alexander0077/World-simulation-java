package projekt_java_AS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Canvas extends JPanel {

    Swiat swiat;

    public Canvas(int size_x, int size_y, Swiat swiat) {
        setSize(size_x, size_y);
        this.swiat = swiat;
    }

    @Override
    protected void paintComponent(Graphics g) { //paintujemy cala mape
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void rysujOrganizmy(){
        Graphics g = getGraphics();
        for(int i = 0; i < swiat.getRozmiarY(); i++){
            for(int j = 0; j < swiat.getRozmiarX(); j++){
                Punkt tmppkt = new Punkt(j, i);
                if(swiat.getCzyPoleJestZajete(tmppkt) && swiat.getCoJestNaPolu(tmppkt).getCzyZyje()){
                    swiat.getCoJestNaPolu(tmppkt).rysuj(g);
                }
                // TODO DODAWANIE NOWEGO ORGANIZMU
            }
        }
    }

    public void zamalujMape(){
        Graphics g = getGraphics();
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

}
