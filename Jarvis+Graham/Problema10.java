package lab91;

import java.util.*;

public class Problema10 extends Problema {
    Object punct[];
    int size;
    ArrayList<Punct> listaPuncteInfasuratoare = new ArrayList<Punct>();

    Problema10(ArrayList<Punct> listaPuncte) {
        size = listaPuncte.size();
        punct = listaPuncte.toArray();
    }

    public int orientare(Punct p, Punct q, Punct r) {
        int rez = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);
        if (rez == 0)
            return 0;
        if (rez > 0)
            return 1;
        else
            return 2;
    }

    public ArrayList<Punct> rezolva() {
        if (size < 3)
            return null;
        int l = 0;
        for (int i = 1; i < size; i++)
            if (((Punct) punct[i]).x < ((Punct) punct[l]).x)
                l = i;
        int q, p = l;
        do {
            listaPuncteInfasuratoare.add((Punct) punct[p]);
            q = (p + 1) % size;
            for (int i = 0; i < size; i++)
                if (orientare((Punct) punct[p], (Punct) punct[i], (Punct) punct[q]) == 2)
                    q = i;
            p = q;
        } while (p != l);
        return listaPuncteInfasuratoare;
    }
}