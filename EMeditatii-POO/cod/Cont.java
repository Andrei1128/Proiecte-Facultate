package cod;

import java.io.Serializable;
import java.util.Arrays;

public class Cont implements Serializable {
    private Persoana p;
    private String id;
    private char[] pw;

    Cont(Persoana p, String id, char[] pw) {
        this.p = p;
        this.id = id;
        this.pw = pw;
    }

    public Persoana getPersoana() {
        return p;
    }

    public String getId() {
        return id;
    }

    public boolean compareTo(char[] pw) {
        return Arrays.equals(this.pw, pw);
    }
}
