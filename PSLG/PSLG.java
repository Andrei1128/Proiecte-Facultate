package lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;

public class PSLG implements Serializable {
    private Desen canvas;
    private ArrayList<Punct> listaPuncte;
    private ArrayList<Muchie> listaMuchii;
    private Algoritm algoritm;
    public String nume;

    PSLG(String nume_fis) throws Exception {
        listaPuncte = new ArrayList<Punct>();
        listaMuchii = new ArrayList<Muchie>();
        canvas = new Desen();
        BufferedReader buf = new BufferedReader(new FileReader(nume_fis));
        while (true) {
            String linie = buf.readLine();
            if (linie.equalsIgnoreCase(""))
                break;
            String str[] = linie.split(",");
            listaPuncte.add(new Punct(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
        }
        while (true) {
            String linie = buf.readLine();
            if (linie == null)
                break;
            String str[] = linie.split(" ");
            listaMuchii.add(new Muchie(Integer.parseInt(str[0]),
                    listaPuncte.get(Integer.parseInt(str[1]) - 1),
                    listaPuncte.get(Integer.parseInt(str[2]) - 1),
                    Integer.parseInt(str[3]),
                    Integer.parseInt(str[4]),
                    Integer.parseInt(str[5]),
                    Integer.parseInt(str[6])));
            algoritm = new Algoritm(listaPuncte.toArray(new Punct[0]), listaMuchii.toArray(new Muchie[0]));
        }
        buf.close();
    }
    public Desen getCanvas() {
        return canvas;
    }

    public Algoritm getAlgoritm() {
        return algoritm;
    }
    @Override
    public String toString() {
        return nume;
    }
}
