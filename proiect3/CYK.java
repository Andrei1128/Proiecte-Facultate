package proiect3;

import java.io.*;
import java.util.*;

public class CYK {
    String cuv;
    int n;
    HashSet[][] rez;
    ArrayList<Productie> listaProductii;

    CYK(String nume_fis) throws Exception {
        listaProductii = new ArrayList<>();
        BufferedReader buf = new BufferedReader(new FileReader(nume_fis));
        while (buf.ready()) {
            String linie = buf.readLine();
            String tbl[] = linie.split(" ");
            Productie p = new Productie(tbl[0], tbl[1]);
            listaProductii.add(p);
        }
    }

    public void Algoritm(String cuv) {
        n = cuv.length();
        this.cuv = cuv;
        rez = new HashSet[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            rez[i][1] = new HashSet();
            String s = "" + cuv.charAt(i-1);
            for (Productie p : listaProductii) {
                if (p.dreapta.matches(s)) {
                    rez[i][1].add(p.stanga);
                }
            }
        }
        for (int j = 2; j <= n; j++) {
            for (int i = 1; i <= n - j + 1; i++) {
                rez[i][j] = new HashSet<String>();
                for (int k = 1; k <= j - 1; k++) {
                    for (Object B : rez[i][k]) {
                        for (Object C : rez[i + k][j - k]) {
                            for (Productie p : listaProductii) {
                                if (p.dreapta.matches(((String) B + (String) C)))
                                    rez[i][j].add(p.stanga);
                            }
                        }
                    }
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n-i+1; j++) {
                sb.append(rez[i][j]);
                sb.append("               \t\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
