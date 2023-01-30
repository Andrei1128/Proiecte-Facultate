package lab78;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Desen extends Canvas implements MouseListener {
    private Problema pb;
    private ArrayList<Punct> listaPuncte;
    private ArrayList<Integer> puncteDeCautat;

    public Desen(int problema, ArrayList<Punct> listaPuncte) {
        this.listaPuncte = listaPuncte;
        if (problema == 0)
            pb = new Problema7(listaPuncte);
        else if (problema == 1)
            pb = new Problema8(listaPuncte);
        puncteDeCautat = new ArrayList<>();
        JFrame f = new JFrame("Desen");
        addMouseListener(this);
        this.enableEvents(506);
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
            g.fillOval(transX(p.getX()), transY(p.getY()), 4, 4);
        if (puncteDeCautat.size() == 2)
            g.fillOval((int) (puncteDeCautat.get(0)), (int) (puncteDeCautat.get(1)), 3, 3);
        else if (puncteDeCautat.size() == 4) {
            g.setColor(Color.RED);
            int x1, x2, y1, y2;
            x1 = puncteDeCautat.get(0);
            y1 = puncteDeCautat.get(1);
            x2 = puncteDeCautat.get(2);
            y2 = puncteDeCautat.get(3);
            g.drawLine(x1, y1, x1, y2);
            g.drawLine(x1, y1, x2, y1);
            g.drawLine(x2, y1, x2, y2);
            g.drawLine(x2, y2, x1, y2);
            g.drawString(pb.cauta((x1 - 300) / 12, (300 - y1) / 12, (x2 - 300) / 12, (300 - y2) / 12), 245, 30);
            puncteDeCautat.clear();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        puncteDeCautat.add(mouseX);
        puncteDeCautat.add(mouseY);
        repaint();
    }

    public int transX(double x) {
        return (int) (300 + x * 12);
    }

    public int transY(double y) {
        return (int) (300 - y * 12);
    }
}