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



}
