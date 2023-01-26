package lab91;

public class Punct implements Comparable<Punct> {
    int x, y;

    Punct(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void translatare(int xm, int ym) {
        x = x + xm;
        y = y + ym;

    }

    public int getCadran() {
        if ((x > 300) && (y >= 300))
            return 4;
        else if ((x >= 300) && (y < 300))
            return 1;
        else if ((x <= 300) && (y > 300))
            return 3;
        else if ((x < 300) && (y <= 300))
            return 2;
        return 0;
    }

    public int compareTo(Punct p) {
        if (p.getCadran() < this.getCadran())
            return 1;
        else if ((p.getCadran() == this.getCadran()) && getDet(x, y, p.getX(), p.getY()) < 0)
            return 1;
        return -1;
    }

    public int getDet(int x, int y, int x1, int y1) {
        return x * 300 + 300 * y1 + x1 * y - 300 * x1 - y1 * x - y * 300;
    }

    public String toString() {
        return x + " " + y;
    }
}