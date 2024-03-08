package domain;

import java.io.Serializable;

public class Entitate implements Serializable {
    int p1;
    String p2;

    public Entitate(int p1, String p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public int getP1() {
        return p1;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    @Override
    public String toString() {
        return "Entitate{" +
                "p1=" + p1 +
                ", p2='" + p2 + '\'' +
                '}';
    }
}
