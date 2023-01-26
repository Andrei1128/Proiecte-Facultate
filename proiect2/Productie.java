package proiect2;

public class Productie {
    private String stanga;
    private String dreapta;
    boolean lema1 = false;
    boolean lema2 = false;
    boolean lema3 = false;

    Productie(String stanga, String dreapta) throws Exception {
        if (!(stanga.matches("[A-Z]")))
            throw new Exception(stanga + " nu este acceptat ca neterminal!");
        if (!(dreapta.matches("([a-z]|[A-Z])*")))
            throw new Exception(dreapta + " nu este acceptat ca terminal!");
        this.stanga = stanga;
        this.dreapta = dreapta;
    }

    public String getDreapta() {
        return dreapta;
    }

    public String getStanga() {
        return stanga;
    }

    public String toString() {
        return stanga + " ->   " + dreapta;// + "\t " + lema1 + " \t" + lema2;
    }
}
