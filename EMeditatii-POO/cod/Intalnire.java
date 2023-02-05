package cod;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Intalnire implements Serializable {
    private String locatie, pret, materie, elev, profesor;
    private Date data;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
    private boolean reviewed=false;

    public Intalnire(String materie, Date data, String locatie, String pret, String elev, String profesor) {
        this.data = data;
        this.materie = materie;
        this.locatie = locatie;
        this.pret = pret;
        this.elev = elev;
        this.profesor = profesor;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(materie);
        sb.append(" pe data de ");
        String[] s =formatter.format(data).split(" ");
        sb.append(s[0]);
        sb.append(" la ora  ");
        sb.append(s[1]);
        return sb.toString();
        
    }
    public String getInfo(){
        StringBuilder sb = new StringBuilder("Materie: ");
        sb.append(materie);
        sb.append("\nData: ");
        String[] s = formatter.format(data).split(" ");
        sb.append(s[0]);
        sb.append("\nOra: ");
        sb.append(s[1]);
        sb.append("\nLocatie: ");
        sb.append(locatie);
        sb.append("\nPret: ");
        sb.append(pret);
        sb.append(" lei\n");
        return sb.toString();
    }

    public String getElev() {
        return elev;
    }
    public void setReviewed() {
        this.reviewed = true;
    }

    public String getProfesor() {
        return profesor;
    }
    public Date getData() {
        return data;
    }
    public boolean getReviewed(){
        return reviewed;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Intalnire)) {
            return false;
        }
        Intalnire otherIntalnire = (Intalnire) other;
        return this.toString().equals(otherIntalnire.toString()) && this.profesor.equals(otherIntalnire.getProfesor())
                && this.elev.equals(otherIntalnire.getElev());
    }
}
