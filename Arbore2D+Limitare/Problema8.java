package lab78;

import java.util.*;

public class Problema8 extends Problema {

    private Object[] listaX, listaY;
    private Punct root;
    private int contor;

    public Problema8(ArrayList<Punct> listaPuncte) {
        ArrayList<Punct> temp = new ArrayList<>(listaPuncte);
        temp.sort(Comparator.comparingDouble(Punct::getX));
        listaX = temp.toArray();
        temp.sort(Comparator.comparingDouble(Punct::getY));
        listaY = temp.toArray();
        int size = listaPuncte.size();
        ((Punct) listaX[size / 2]).setCut("vert");

        boolean[] b = new boolean[size];
        for (int i = 0; i < size / 2; ++i)
            b[((Punct) listaX[i]).getNr()] = true;
        ((Punct) listaX[size / 2]).setS(ordonata(0, size / 2 - 1, 0, size - 1, b));
        b = new boolean[size];
        for (int i = size / 2 + 1; i < size; ++i)
            b[((Punct) listaX[i]).getNr()] = true;

        ((Punct) listaX[size / 2]).setD(ordonata(size / 2 + 1, size - 1, 0, size - 1, b));
        root = (Punct) listaX[size / 2];

    }

    public Punct ordonata(int xa, int ya, int xo, int yo, boolean[] bool) {
        Vector<Punct> temp = new Vector<Punct>();
        for (int i = 0; i < listaY.length; ++i) {
            if (bool[((Punct) listaY[i]).getNr()])
                temp.add((Punct) listaY[i]);
        }
        if (temp.size() == 0)
            return null;
        Punct pct = (Punct) (temp.elementAt(temp.size() / 2));
        pct.setCut("oriz");
        int nrr = pct.getNr();

        int t = 0;
        for (int i = 0; i < listaY.length; ++i)
            if (((Punct) listaY[i]).getNr() == nrr) {
                t = i;
                break;
            }

        boolean[] b = new boolean[listaY.length];
        for (int i = 0; i < temp.size() / 2; ++i)
            b[((Punct) (temp.elementAt(i))).getNr()] = true;

        pct.setS(abscisa(xa, ya, xo, t - 1, b));

        b = new boolean[listaY.length];
        for (int i = temp.size() / 2 + 1; i < temp.size(); ++i)
            b[((Punct) (temp.elementAt(i))).getNr()] = true;
        pct.setD(abscisa(xa, ya, t + 1, yo, b));
        return pct;
    }

    public Punct abscisa(int xa, int ya, int xo, int yo, boolean[] bool) {
        Vector<Punct> temp = new Vector<Punct>();
        for (int i = 0; i < listaX.length; ++i) {
            if (bool[((Punct) listaX[i]).getNr()])
                temp.add((Punct) listaX[i]);
        }
        if (temp.size() == 0)
            return null;
        Punct pct = (Punct) (temp.elementAt(temp.size() / 2));
        pct.setCut("vert");
        int nrr = pct.getNr();
        int t = -5;
        for (int i = 0; i < listaX.length; ++i)
            if (((Punct) listaX[i]).getNr() == nrr) {
                t = i;
                break;
            }

        boolean[] b = new boolean[listaX.length];
        for (int i = 0; i < temp.size() / 2; ++i)
            b[((Punct) (temp.elementAt(i))).getNr()] = true;

        pct.setS(ordonata(xa, t - 1, xo, yo, b));
        b = new boolean[listaX.length];
        for (int i = temp.size() / 2 + 1; i < temp.size(); ++i)
            b[((Punct) (temp.elementAt(i))).getNr()] = true;
        pct.setD(ordonata(t + 1, ya, xo, yo, b));
        return pct;
    }

    public String cauta(double x1, double y1, double x2, double y2) {
        contor = 0;
        caut(root, x1, y1, x2, y2);
        return "" + contor;
    }

    public void caut(Punct pct, double x1, double y1, double x2, double y2) {
        double l, r, m;
        if (pct.getCut().equals("vert")) {
            l = x1;
            r = x2;
            m = pct.getX();
        } else {
            l = y2;
            r = y1;
            m = pct.getY();
        }
        if (pct.getX() >= x1 && pct.getX() <= x2 && pct.getY() <= y1 && pct.getY() >= y2)
            contor++;
        if (m >= l) {
            if (pct.getS() != null)
                caut(pct.getS(), x1, y1, x2, y2);
        }
        if (m <= r) {
            if (pct.getD() != null)
                caut(pct.getD(), x1, y1, x2, y2);
        }
    }
}
