package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double donjaGranica, gornjaGranica;
    private boolean donjaUkljucena, gornjaUkljucena;
    private final static double EPS = 1e-6;

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
        return (donjaGranica < tacka && gornjaGranica > tacka);
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


}
