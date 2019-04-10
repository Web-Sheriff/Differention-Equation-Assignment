package Project;

public class Euler extends Grid{

    double[] y;

    public Euler(double X, double x0, double y0, int n) {
        super(X, x0, y0, n);
        y = new double[n+1];
    }

    public void EulerMethod() {
        x[0] = x0;
        y[0] = y0;
        for (int i = 1; i <= n; i++) {
            y[i] = y[i-1]+h* RightHandSide.RHS(x[i-1],y[i-1]);
        }
     }
}
