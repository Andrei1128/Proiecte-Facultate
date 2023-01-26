package lab91;

import java.io.*;
import java.util.*;

public class Citesc {
    ArrayList<Punct> listaPuncte = new ArrayList<>();
    Citesc(File nume_fis) throws Exception {
        BufferedReader buf = new BufferedReader(new FileReader(nume_fis));
        while (buf.ready()) {
            String linie = buf.readLine();
            String tbl[] = linie.split(" ");
            listaPuncte.add(new Punct(Integer.parseInt(tbl[0]), Integer.parseInt(tbl[1])));
        }
    }
    public ArrayList<Punct> getListaPuncte() {
        return listaPuncte;
    }
}
