package lab78;

import java.util.*;

public class Problema7 extends Problema {
    ArrayList<Punct> L1;
    ArrayList<Punct> L2;

    Problema7(ArrayList<Punct> listaPuncte) {
        super();
        L1 = new ArrayList<>(listaPuncte);
        L2 = new ArrayList<>(listaPuncte);
        L2.sort(Comparator.comparingDouble(Punct::getY));
        L1.sort(Comparator.comparingDouble(Punct::getX));
    }

    public String cauta(double x1, double y1, double x2, double y2) {
        int i, j, size = L1.size();
        int[][] M = new int[size + 1][size + 1];
        for (i = 0; i < size + 1; i++)
            M[i][0] = 0;
        for (j = 1; j < size + 1; j++) {
            int k = L2.indexOf(L1.get(j - 1));
            for (i = 1; i <= k; i++)
                M[i][j] = M[i][j - 1];
            for (i = k + 1; i < size + 1; i++)
                M[i][j] = M[i][j - 1] + 1;
        }
        int qB = M[cautDupaX(L1, 0, L1.size() - 1, x2)][cautDupaY(L2, 0, L2.size() - 1, y1)];
        int qA = M[cautDupaX(L1, 0, L1.size() - 1, x1)][cautDupaY(L2, 0, L2.size() - 1, y1)];
        int qC = M[cautDupaX(L1, 0, L1.size() - 1, x2)][cautDupaY(L2, 0, L2.size() - 1, y2)];
        int qD = M[cautDupaX(L1, 0, L1.size() - 1, x1)][cautDupaY(L2, 0, L2.size() - 1, y2)];
        return ""+Math.abs(qB - qA - qC + qD);
    }

    public int cautDupaY(List<Punct> listaPuncte, int start, int sfarsit, double y) {
        int mijloc = start + (sfarsit - start) / 2;
        if (sfarsit < start)
            return mijloc;
        if (listaPuncte.get(mijloc).getY() == y)
            return mijloc;
        if (listaPuncte.get(mijloc).getY() > y)
            return cautDupaY(listaPuncte, start, mijloc - 1, y);
        return cautDupaY(listaPuncte, mijloc + 1, sfarsit, y);
    }

    public int cautDupaX(List<Punct> listaPuncte, int start, int sfarsit, double x) {
        int mijloc = start + (sfarsit - start) / 2;
        if (sfarsit < start)
            return mijloc;
        if (listaPuncte.get(mijloc).getX() == x)
            return mijloc;
        if (listaPuncte.get(mijloc).getX() > x)
            return cautDupaX(listaPuncte, start, mijloc - 1, x);
        return cautDupaX(listaPuncte, mijloc + 1, sfarsit, x);
    }
}
