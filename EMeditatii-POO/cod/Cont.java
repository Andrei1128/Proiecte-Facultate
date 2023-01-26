package cod;

import java.io.Serializable;
import java.util.Arrays;

public class Cont implements Serializable {
    private String id;
    private char[] pw;

    Cont(String id, char[] pw) {
        this.id = id;
        this.pw = pw;
    }

    public String getId() {
        return id;
    }

    public boolean compareTo(char[] pw) {
        return Arrays.equals(this.pw, pw);
    }
}
