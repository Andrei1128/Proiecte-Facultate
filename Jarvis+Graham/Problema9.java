package lab91;

import java.util.*;

public class Problema9 extends Problema {
    private final int MAX = Integer.MAX_VALUE, MIN = Integer.MIN_VALUE;
    private ArrayList<Punct> listaPuncte;
    private Punct[] Puncte;
    private boolean check = false;
    private Punct pivot;
    private int pivotX, pivotY;

    public Problema9(ArrayList<Punct> p) {
        listaPuncte = new ArrayList<Punct>(p);
        Puncte = new Punct[listaPuncte.size()];
        translatare();
        for (int i = 0; i < listaPuncte.size(); i++)
            Puncte[i] = listaPuncte.get(i);
        Arrays.sort(Puncte);
    }

    public ArrayList<Punct> rezolva() {
        double det = 0;
        int max = 0, i = 0, y = 0, m = 0, n = 0, maxy = MIN;
        for (i = 0; i < Puncte.length; ++i) {
            if (maxy < (Puncte[i]).getY()) {
                maxy = (Puncte[i]).getY();
                max = i;
            }
        }
        Punct[] aux = new Punct[Puncte.length];
        for (i = max; i < Puncte.length; ++i)
            aux[n++] = Puncte[i];
        for (i = 0; i < max; ++i)
            aux[n++] = Puncte[i];
        Puncte = aux;
        i = 0;
        do {
            if (i == Puncte.length)
                i = 0;
            if (i <= Puncte.length - 3) {
                det = calcDet((Puncte[i]).getX(), (Puncte[i]).getY(), (Puncte[i + 1]).getX(), (Puncte[i + 1]).getY(),
                        (Puncte[i + 2]).getX(), (Puncte[i + 2]).getY());
                m = i + 1;
            } else if (i == Puncte.length - 2) {
                det = calcDet((Puncte[i]).getX(), (Puncte[i]).getY(), (Puncte[i + 1]).getX(), (Puncte[i + 1]).getY(),
                        (Puncte[0]).getX(), (Puncte[0]).getY());
                m = i + 1;
                y = 1;
                check = true;
            } else if (i == Puncte.length - 1) {
                det = calcDet((Puncte[i]).getX(), (Puncte[i]).getY(), (Puncte[0]).getX(), (Puncte[0]).getY(),
                        (Puncte[1]).getX(), (Puncte[1]).getY());
                m = 0;
                y = 2;
            }
            if (det <= 0) {
                Punct[] temp = new Punct[Puncte.length - 1];
                for (int p = 0; p < m; ++p)
                    temp[p] = Puncte[p];
                for (int p = m + 1; p < Puncte.length; ++p)
                    temp[p - 1] = Puncte[p];
                Puncte = new Punct[temp.length];
                for (int index = 0; index < Puncte.length; ++index)
                    Puncte[index] = temp[index];
                i = i - 1;
                if (i == -1)
                    i = 0;
            } else
                i++;
        } while (y != 2 && !check);
        i = Puncte.length - 2;
        if (i >= 0)
            if (calcDet((Puncte[i]).getX(), (Puncte[i]).getY(), (Puncte[i + 1]).getX(), (Puncte[i + 1]).getY(),
                    (Puncte[0]).getX(), (Puncte[0]).getY()) <= 0) {
                Punct[] temp = new Punct[Puncte.length - 1];
                for (int k = 0; k < temp.length; ++k)
                    temp[k] = Puncte[k];
                Puncte = new Punct[temp.length];
                for (int k = 0; k < temp.length; ++k)
                    Puncte[k] = temp[k];
            }
        ArrayList<Punct> rez = new ArrayList<>();
        int index;
        for (index = 0; index < Puncte.length; index++)
            (Puncte[index]).translatare(-pivotX, -pivotY);
        index--;
        for (; index > -1; index--)
            rez.add(Puncte[index]);
        return rez;
    }
    
    public void translatare() {
        calcG();
        Iterator<Punct> it = listaPuncte.iterator();
        ArrayList<Punct> listaTemp = new ArrayList<Punct>();
        while (it.hasNext()) {
            Punct pp = it.next();
            Punct p = new Punct(pp.getX(), pp.getY());
            pivotX = 300 - (int) pivot.getX();
            pivotY = 300 - (int) pivot.getY();
            p.translatare(pivotX, pivotY);
            listaTemp.add(p);
        }
        listaPuncte = listaTemp;
        pivot.setX(0);
        pivot.setY(0);
    }

    public void calcG() {
        double miny1 = 0, minx2 = 0, maxx3 = 0;
        double minx1 = MAX, miny2 = MAX, maxy3 = MIN;
        for (Punct p : listaPuncte) {
            if (minx1 > p.getX()) {
                minx1 = p.getX();
                miny1 = p.getY();
            }
            if (miny2 > p.getY()) {
                minx2 = p.getX();
                miny2 = p.getY();
            }
            if (maxy3 < p.getY()) {
                maxx3 = p.getX();
                maxy3 = p.getY();
            }
        }
        pivot = new Punct((int) Math.round((minx1 + minx2 + maxx3) / 3), (int) Math.round((miny1 + miny2 + maxy3) / 3));
    }


    public double calcDet(double x1, double y1, double x2, double y2, double x3,
            double y3) {
        x1 = (x1 + pivotX - 300) / 12.0;
        x2 = (x2 + pivotX - 300) / 12.0;
        x3 = (x3 + pivotX - 300) / 12.0;
        y1 = (300 + pivotY - y1) / 12.0;
        y2 = (300 + pivotY - y2) / 12.0;
        y3 = (300 + pivotY - y3) / 12.0;
        return x1 * y2 + x2 * y3 + x3 * y1 - x3 * y2 - x1 * y3 - x2 * y1;
    }

}