package ba.unsa.etf.rpr.tutorijal02;

import static java.lang.Math.abs;

public class Interval {
    private double donjaGranica, gornjaGranica;
    private boolean donjaUkljucena, gornjaUkljucena;
    private final static double EPS = 1e-6;

    private static boolean jednakost(double x, double y) {
        return ((abs(x - y)) <= ((abs(x) + abs(y)) * EPS));
    }

    public double getDonjaGranica() {
        return donjaGranica;
    }

    public double getGornjaGranica() {
        return gornjaGranica;
    }

    public boolean isDonjaUkljucena() {
        return donjaUkljucena;
    }

    public boolean isGornjaUkljucena() {
        return gornjaUkljucena;
    }

    public Interval(double donjaGranica, double gornjaGranica, boolean donjaUkljucena, boolean gornjUkljucena) {
        if (donjaGranica > gornjaGranica)
            throw new IllegalArgumentException("Neispravne granice");
        this.donjaGranica = donjaGranica;
        this.gornjaGranica = gornjaGranica;
        this.donjaUkljucena = donjaUkljucena;
        this.gornjaUkljucena = gornjUkljucena;
    }

    public Interval() {
        this.donjaGranica = 0;
        this.gornjaGranica = 0;
        this.donjaUkljucena = false;
        this.gornjaUkljucena = false;
    }

    public boolean isNull () {
        return  (donjaGranica < EPS && gornjaGranica < EPS && !donjaUkljucena && !gornjaUkljucena);
    }

    public boolean isIn (double tacka) {
        if (donjaUkljucena && jednakost(tacka, donjaGranica))
            return true;
        if (gornjaUkljucena && jednakost(tacka, gornjaGranica))
            return true;
        return (tacka > donjaGranica && tacka < gornjaGranica);
    }

    public Interval intersect (final Interval interval2) {
        if (isIn(interval2.donjaGranica)) {
            if (isIn(interval2.gornjaGranica))
                return interval2;
            else
                return new Interval(interval2.donjaGranica, gornjaGranica, interval2.donjaUkljucena, gornjaUkljucena);
        }
        else if (isIn(interval2.gornjaGranica))
            return new Interval(donjaGranica, interval2.gornjaGranica, donjaUkljucena, interval2.gornjaUkljucena);

        else if (interval2.isIn(gornjaGranica))
            return this;
        return new Interval();
    }

    public static Interval intersect (final Interval interval1, final Interval interval2) {
        if (interval1.isIn(interval2.donjaGranica)) {
            if (interval1.isIn(interval2.gornjaGranica))
                return interval2;
            else
                return new Interval(interval2.donjaGranica, interval1.gornjaGranica, interval2.donjaUkljucena, interval1.gornjaUkljucena);
        }
        else if (interval1.isIn(interval2.gornjaGranica))
            return new Interval(interval1.donjaGranica, interval2.gornjaGranica, interval1.donjaUkljucena, interval2.gornjaUkljucena);

        else if (interval2.isIn(interval1.gornjaGranica))
            return interval1;
        return new Interval();
    }

    @Override
    public String toString () {
        StringBuilder str = new StringBuilder();

        if (donjaUkljucena)
            str.append("[");
        else
            str.append("(");
        if (!isNull()) {

            if (jednakost(donjaGranica, 0))
                str.append(0 + ",");
            else
                str.append(donjaGranica + ",");

            if (jednakost(gornjaGranica, 0))
                str.append(donjaGranica);
            else
                str.append(gornjaGranica);
        }
        if (gornjaUkljucena)
            str.append("]");
        else
            str.append(")");
        return str.toString();
    }


    @Override
    public boolean equals (Object objekat) {
        if (objekat instanceof Interval) {
            return (jednakost(donjaGranica,((Interval) objekat).getDonjaGranica())
                    && jednakost(gornjaGranica, ((Interval) objekat).getGornjaGranica())
                    && donjaUkljucena == ((Interval) objekat).isDonjaUkljucena()
                    && gornjaUkljucena == ((Interval) objekat).isGornjaUkljucena());
        }
        throw new IllegalArgumentException("GRESKA!");
    }

}
