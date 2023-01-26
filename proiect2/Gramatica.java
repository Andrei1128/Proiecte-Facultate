package proiect2;

import java.io.*;
import java.util.*;

public class Gramatica {

    private char start;
    private ArrayList<Productie> listaProductii = new ArrayList<>();

    Gramatica(String nume_fis) throws Exception {

        BufferedReader buf = new BufferedReader(new FileReader(nume_fis));
        start = buf.readLine().charAt(0);
        while (true) {
            String linie = buf.readLine();
            if (linie == null)
                break;
            String tbl[] = linie.split(" ");
            String tbl1 = tbl.length > 1 ? tbl[1] : "";
            Productie p = new Productie(tbl[0], tbl1);
            listaProductii.add(p);
        }
        buf.close();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Productie p : listaProductii)
            sb.append(p.toString() + "\n");
        return sb.toString();
    }

    public void lema1() {
        boolean stop = false;
        HashSet neterminale = new HashSet<>();
        for (Productie p : listaProductii) {
            if (p.getDreapta().matches("[a-z]*")) {
                p.lema1 = true;
                neterminale.add(p.getStanga());
            }
        }
        if (neterminale.isEmpty())
            return;
        while (!stop) {
            stop = true;
            for (Productie p : listaProductii) {
                if (!p.lema1 && p.getDreapta().matches("([a-z]|" + neterminale.toString().replace(", ", "") + ")*")) {
                    neterminale.add(p.getStanga());
                    p.lema1 = true;
                    stop = false;
                }
            }
        }
    }

    public void lema2() {
        boolean stop = false;
        HashSet neterminale = new HashSet<>();
        neterminale.add(start);
        while (!stop) {
            stop = true;
            for (Productie p : listaProductii) {
                if (!p.lema2 && (p.getStanga()).matches(neterminale.toString().replace(", ", ""))) {
                    p.lema2 = true;
                    for (char c : p.getDreapta().toCharArray())
                        if (c >= 'A' && c <= 'Z')
                            neterminale.add(c);
                    stop = false;
                }
            }
        }
    }

    public void lema3() {
        boolean stop = false;
        HashSet Nnul = new HashSet<>();
        for (int i = 0; i < listaProductii.size(); i++)
            if (listaProductii.get(i).getDreapta().isEmpty()) {
                Nnul.add(listaProductii.get(i).getStanga());
                listaProductii.remove(i);
                i--;
            }
        while (!stop) {
            stop = true;
            for (int i = 0; i < listaProductii.size(); i++)
                if (!listaProductii.get(i).lema3
                        && listaProductii.get(i).getDreapta().matches(Nnul.toString().replace(", ", "") + "+")) {
                    Nnul.add(listaProductii.get(i).getStanga());
                    listaProductii.get(i).lema3 = true;
                    stop = false;
                }
        }
        generator(Nnul);
    }

    public void generator(HashSet Nnul) {
        HashSet<String> listaProductiiPrim = new HashSet<>();
        for (Productie p : listaProductii) {
            boolean flag = false;
            for (char c : p.getDreapta().toCharArray()) {
                String s = "" + c;
                if (s.matches(Nnul.toString().replace(", ", ""))) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                String dreapta = p.getDreapta();
                int[] v = new int[dreapta.length()];
                ArrayList<Integer> l = new ArrayList<>();
                for (int i = 0; i < dreapta.length(); i++) {
                    String s = "" + dreapta.charAt(i);
                    if (s.matches(Nnul.toString().replace(", ", ""))) {
                        l.add(i);
                    }
                }
                if (l.size() == 1) {
                    StringBuilder sb = new StringBuilder(dreapta);
                    sb.deleteCharAt(l.get(0));
                    if (sb.toString().length() != 0) {
                        sb.append(p.getStanga());
                        listaProductiiPrim.add(sb.toString());
                    }
                } else {
                    int k = l.size() - 1;
                    v[l.get(0)] = 1;
                    int Stop = 0;
                    while (Stop != 1) {
                        StringBuilder sb = new StringBuilder(dreapta);
                        int n = 0;
                        Stop = stop(v, l);
                        for (int i = 0; i < l.size(); i++)
                            if (v[l.get(i)] == 1) {
                                sb.deleteCharAt(l.get(i) - n);
                                n++;
                            }
                        if (sb.toString().length() != 0) {
                            sb.append(p.getStanga());
                            listaProductiiPrim.add(sb.toString());
                        }
                        if (k > -1 && v[l.get(k)] == 1) {
                            v[l.get(0)] = 1;
                            k--;
                        } else {
                            for (int j = 0; j <= k; j++) {
                                if (v[l.get(j)] == 1) {
                                    v[l.get(j)] = 0;
                                    v[l.get(j + 1)] = 1;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        for (String s : listaProductiiPrim)
            try {
                String st = "" + s.charAt(s.length() - 1);
                String dr = s.substring(0, s.length() - 1);
                listaProductii.add(new Productie(st, dr));
            } catch (Exception e) {
            }
    }

    public int stop(int[] v, ArrayList<Integer> l) {
        for (int i : l)
            if (v[i] == 0)
                return 0;
        return 1;
    }
}
