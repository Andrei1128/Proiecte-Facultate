package lab91;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Desen extends Canvas {
    private Problema pb;
    private ArrayList<Punct> listaPuncte;
    private ArrayList<Punct> listaPuncteInfasuratoare;

    public Desen(int problema, ArrayList<Punct> listaPuncte) {
        this.listaPuncte = listaPuncte;
        if (problema == 0)
            pb = new Problema9(listaPuncte);
        else if (problema == 1)
            pb = new Problema10(listaPuncte);
        listaPuncteInfasuratoare = pb.rezolva();
        JFrame f = new JFrame("Desen");
        f.setVisible(true);
        f.setSize(650, 650);
        setSize(600, 600);
        f.add(this);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        g.drawLine(transX(-20), transY(0), transX(20), transY(0));
        g.drawLine(transX(0), transY(-20), transX(0), transY(20));
        for (Punct p : listaPuncte)
            g.fillOval(transX(p.getX()), transY(p.getY()), 5, 5);
        g.setColor(Color.RED);
        for (int i = 0; i < listaPuncteInfasuratoare.size() - 1; i++) {
            _wait();
            Punct p1 = listaPuncteInfasuratoare.get(i);
            Punct p2 = listaPuncteInfasuratoare.get(i + 1);
            g.fillOval(transX(p1.getX()), transY(p1.getY()), 4, 4);
            g.drawLine(transX(p1.getX()), transY(p1.getY()), transX(p2.getX()), transY(p2.getY()));
        }
        _wait();
        Punct p1 = listaPuncteInfasuratoare.get(listaPuncteInfasuratoare.size() - 1);
        Punct p2 = listaPuncteInfasuratoare.get(0);
        g.fillOval(transX(p1.getX()), transY(p1.getY()), 4, 4);
        g.drawLine(transX(p1.getX()), transY(p1.getY()), transX(p2.getX()), transY(p2.getY()));

    }

    public void _wait() {
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
        }
    }

    public int transX(double x) {
        return (int) (300 + x * 12);
    }

    public int transY(double y) {
        return (int) (300 - y * 12);
    }
}