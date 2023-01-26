package cod;

import java.io.Serializable;

public class Certificat implements Serializable {
    private String titlu;
    private String cale;

    Certificat(String titlu, String cale) {
        this.titlu = titlu;
        this.cale = cale;
    }

    public String toString() {
        return titlu+"\n"+cale;
    }

    public String getCale() {
        return cale;
    }
}
