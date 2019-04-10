package Project;

public class ExactSolution extends IVP {

    double[] y;

    public ExactSolution(double X, double x0, double y0, int n) {
        super(X, x0, y0, n);
        y = new double[n + 1];
    }

    public void ParticularSolution() {
        findC();
        for (int i = 0; i <= n; i++) {
            y[i] = C * Math.pow(Math.E, -2 * x[i]) + 2 * x[i] - 1;
        }
    }


}
