package lab5;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class Punct implements Comparable, Serializable {

    private double x, y;
    private Set b, a;

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

    public int compareTo(Object o) {
        if (y > ((Punct) o).getY())
            return 255;
        if ((y == ((Punct) o).getY()) && (x == ((Punct) o).getX()))
            return 0;
        return -256;
    }

    public void setA(TreeSet a) {
        this.a = a;
    }

    public void setB(TreeSet b) {
        this.b = b;
    }

    public Set getA() {
        return a;
    }

    public Set getB() {
        return b;
    }
}