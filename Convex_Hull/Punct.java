package lab78;

public class Punct {

    private double x, y;
    private int nr;
    private String cut;
    private Punct d, s;

    public Punct(double x, double y, int nr) {
        this.x = x;
        this.y = y;
        this.nr = nr;
    }

    public Punct(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getNr() {
        return nr;
    }

    public void setCut(String nr) {
        cut = nr;
    }

    public String getCut() {
        return cut;
    }

    public void setD(Punct d) {
        this.d = d;
    }

    public void setS(Punct s) {
        this.s = s;
    }

    public Punct getD() {
        return d;
    }

    public Punct getS() {
        return s;
    }

    public String toString() {
        return x + " " + y;
    }
}
