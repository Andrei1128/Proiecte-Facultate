package cod;

import java.io.Serializable;
import java.util.HashSet;

public class Persoana implements Serializable {
    private String poza;
    private String nume;
    private String prenume;
    private HashSet<String> materii;

    Persoana(String nume, String prenume) {
        this.nume = nume;
        this.prenume = prenume;
        poza = "altele\\poza.png";
        materii = new HashSet<>();
    }

    public String getPoza() {
        return poza;
    }
    public HashSet<String> getMaterii() {
        return materii;
    }

    public String getNume() {
        return nume;
    }
    public String getNumePrenume() {
        return nume+" "+prenume;
    }

    public String getPrenume() {
        return prenume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }
    public boolean adaugaMaterie(String materie){
        return materii.add(materie);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(nume);
        sb.append(" ");
        sb.append(prenume);
        return sb.toString();
    }
}
