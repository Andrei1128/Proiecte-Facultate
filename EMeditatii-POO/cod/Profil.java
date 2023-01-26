package cod;

import java.io.Serializable;

public class Profil implements Serializable {
    private Persoana p;
    private Cont c;

    Profil(Persoana p, Cont c) {
        this.p = p;
        this.c = c;
    }

    public Cont getCont() {
        return c;
    }
    public Persoana getPersoana() {
        return p;
    }
}
