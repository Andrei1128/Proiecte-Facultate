package lab5;

import java.io.Serializable;
import java.util.*;

public class Algoritm implements Serializable {

    private Punct[] listaPuncte;
    private Muchie[] listaMuchii;
    private List matura;
    private List[] listaMatura;
    private Object[] lespede;
    private double x, y;
    private Muchie muchieX;

    public Algoritm(Punct[] listaPuncte, Muchie[] listaMuchii) {
        this.listaPuncte = listaPuncte;
        this.listaMuchii = listaMuchii;
        Arrays.sort(listaPuncte);
        reorienteaza();
        formeazaListe();
        matura();
    }

    private void reorienteaza() {
        for (int i = 0; i < listaMuchii.length; ++i) {
            if (((listaMuchii[i].getV1()).compareTo(listaMuchii[i].getV2())) > 0)
                listaMuchii[i].inverseaza();
        }
    }

    public void formeazaListe() {
        for (int i = 0; i < listaPuncte.length; ++i) {
            TreeSet a = new TreeSet();
            TreeSet b = new TreeSet();
            for (int j = 0; j < listaMuchii.length; ++j) {
                if (((listaMuchii[j].getV1()).compareTo(listaPuncte[i])) == 0)
                    a.add(listaMuchii[j]);
                if (((listaMuchii[j].getV2()).compareTo(listaPuncte[i])) == 0)
                    b.add(listaMuchii[j]);

            }
            listaPuncte[i].setA(a);
            listaPuncte[i].setB(b);
        }
    }

    public void matura() {
        listaMatura = new ArrayList[listaPuncte.length - 1];
        matura = new ArrayList(listaPuncte[0].getA());
        listaMatura[0] = new ArrayList(matura);
        for (int i = 1; i < listaPuncte.length - 1; ++i) {
            Iterator itb = (listaPuncte[i].getB()).iterator();
            while (itb.hasNext()) {
                Muchie mb = (Muchie) (itb.next());
                if (matura.contains(mb)) {
                    int poz = matura.indexOf(mb);
                    matura.remove(poz);
                    Iterator itc = (listaPuncte[i].getA()).iterator();

                    while (itc.hasNext()) {
                        Muchie mc = (Muchie) (itc.next());
                        if (!matura.contains(mc)) {
                            matura.add(poz++, mc);
                        }
                    }
                }
            }
            listaMatura[i] = new ArrayList(matura);
        }
    }

    public int cauta(double x, double y) {
        this.x = x;
        this.y = y;
        int rez = cautaLespede(x, y) - 1;
        if (rez > listaPuncte.length - 2)
            rez = listaPuncte.length - 2;
        if (rez < 0)
            return 0;
        lespede = listaMatura[rez].toArray();
        cautaBinar(0, lespede.length - 1);
        int F = 0;
        if (det(muchieX.getV1().getX(),
                muchieX.getV1().getY(),
                x,
                y,
                muchieX.getV2().getX(),
                muchieX.getV2().getY()) > 0)
            F = muchieX.getF2();
        else
            F = muchieX.getF1();
        return F;
    }

    public void cautaBinar(int s, int d) {
        int mijloc = (s + d) / 2;
        if (s < d) {
            if (det(((Muchie) (lespede[mijloc])).getV1().getX(),
                    ((Muchie) (lespede[mijloc])).getV1().getY(),
                    x,
                    y,
                    ((Muchie) (lespede[mijloc])).getV2().getX(),
                    ((Muchie) (lespede[mijloc])).getV2().getY()) > 0)
                cautaBinar(mijloc + 1, d);
            else
                cautaBinar(s, mijloc - 1);
        } else {
            muchieX = ((Muchie) (lespede[mijloc]));
        }
    }

    public int cautaLespede(double x, double y) {
        return ~Arrays.binarySearch(listaPuncte, new Punct(x, y));
    }

    public double det(double x1, double y1, double x2, double y2, double x3,
            double y3) {
        return x1 * y2 + x2 * y3 + x3 * y1 - y2 * x3 - y3 * x1 - y1 * x2;
    }

    public Muchie[] getDCEL() {
        return listaMuchii;
    }
}