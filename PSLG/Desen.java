package lab5;

import java.awt.*;
import java.io.Serializable;

class Desen extends Canvas implements Serializable {

    private boolean drawPoli = false,
            mouseStart = false,
            mouseSelect = false;
    private int mouseX, mouseY;
    private double Mx, My;
    private Algoritm a;
    private int fata;

    public Desen() {
        setSize(500, 500);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.gray);
        g2.setStroke(new BasicStroke(2));

        g2.drawLine(calcX(-20), calcY(0), calcX(20), calcY(0));
        g2.drawString("", calcX(21), calcY(-1));
        g2.drawLine(calcX(20) - 3, calcY(0) - 3, calcX(20), calcY(0));
        g2.drawLine(calcX(20) - 3, calcY(0) + 3, calcX(20), calcY(0));

        g2.drawLine(calcX(0), calcY(-20), calcX(0), calcY(20));
        g2.drawString("", calcX(-1), calcY(21));
        g2.drawLine(calcX(0) + 3, calcY(20) + 3, calcX(0), calcY(20));
        g2.drawLine(calcX(0) - 3, calcY(20) + 3, calcX(0), calcY(20));

        if (drawPoli) {
            g2.setColor(Color.black);
            Muchie[] dcel = a.getDCEL();
            for (int i = 0; i < dcel.length; ++i) {
                g2.drawLine(calcX((int) dcel[i].getV1().getX()),
                        calcY((int) dcel[i].getV1().getY()),
                        calcX((int) dcel[i].getV2().getX()),
                        calcY((int) dcel[i].getV2().getY()));
            }
        }

        if (mouseSelect) {
            g.setFont(new Font("Dialog", Font.BOLD, 14));
            Muchie[] dcel = a.getDCEL();
            for (int i = 0; i < dcel.length; ++i) {
                g.setColor(Color.red);
                if ((dcel[i].getF1() == fata) || (dcel[i].getF2() == fata))
                    g.drawLine(calcX((int) dcel[i].getV1().getX()),
                            calcY((int) dcel[i].getV1().getY()),
                            calcX((int) dcel[i].getV2().getX()),
                            calcY((int) dcel[i].getV2().getY()));
                g.setColor(Color.black);
                g.drawString("Punctul M(" + Mx + "," + My + ") se afla in fata nr. " + fata, calcX(-12), calcY(22));
            }
        }
        
        g.drawString("x", mouseX, mouseY);
    }

    public int getFata() {
        return fata;
    }

    public int calcX(int x) {
        return 250 + x * 10;
    }

    public int calcY(int y) {
        return 250 - y * 10;
    }

    public boolean mouseDown(Event e, int x, int y) {
        if (mouseStart) {
            mouseX = x;
            mouseY = y;
            Mx = (mouseX - 250) / 10.0;
            My = (250 - mouseY) / 10.0;
            mouseSelect = true;
            fata = a.cauta(Mx, My);
            repaint();
        }
        return true;
    }

    public void setAlgoritm(Algoritm a) {
        this.a = a;
        drawPoli = true;
        mouseStart = true;
        repaint();
    }
}
