package Project;

public class Grid {
    double X;
    double x0;
    double y0;
    double h;
    double[] x;
    int n;

    public Grid(double X, double x0, double y0, int n) {
        this.X = X;
        this.x0 = x0;
        this.y0 = y0;
        this.h = (X-x0)/n;
        this.n = n;
        x = new double[n+1];
        for (int i = 0; i <= n; i++){
            x[i] = i*h+x0;
        }
    }

}
