package cod;

import java.io.Serializable;

public class Mesaj implements Serializable, ScriereCitire {
    private String emitator,numeEmitator;
    private Object continut;

    public Mesaj(String emitator,String numeEmitator, Object continut) {
        this.emitator = emitator;
        this.continut = continut;
        this.numeEmitator=numeEmitator;
    }

    public String toString() {
        StringBuilder sb;
        if (continut instanceof Intalnire) {
            sb = new StringBuilder("Cerere de meditatie de la ");
            sb.append(numeEmitator);
        } else {
            sb = new StringBuilder("Mesaj de la ");
            sb.append(numeEmitator);
        }
        return sb.toString();
    }

    public String getEmitator() {
        return emitator;
    }

    public Object getContinut() {
        return continut;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Mesaj)) {
            return false;
        }
        Mesaj otherMesaj = (Mesaj) other;
        return this.continut.toString().equals(otherMesaj.getContinut().toString())
                && this.emitator.equals(otherMesaj.getEmitator());
    }
}
