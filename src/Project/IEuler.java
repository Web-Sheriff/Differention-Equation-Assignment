package Project;

public class IEuler extends Grid {

    double[] y;

    public IEuler(double X, double x0, double y0, int n) {
        super(X, x0, y0, n);
        y =  new double[n+1];
    }

    public void IEulerMethod() {
        x[0] = x0;
        y[0] = y0;
        double a;
        double b;

        for (int i = 1; i <= n; i++) {
            a = RightHandSide.RHS(x[i-1],y[i-1]);
            b = RightHandSide.RHS(x[i-1]+(h/2),y[i-1]+(h/2)*a);
            y[i] = y[i-1]+h*b;
        }
    }
}
