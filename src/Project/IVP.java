package Project;

public class IVP extends Grid {

    double C;

    public IVP(double X, double x0, double y0, int n) {
        super(X, x0, y0, n);
    }

    public void findC() {
        C = (y0 - 2 * x0 + 1) * Math.pow(Math.E, 2 * x0);
    }

}
