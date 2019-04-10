package Project;

public class GlobalError {

    int n0;
    int N;
    double x0;
    double y0;
    int[] n;
    double X;
    double[] error;

    public GlobalError(int n0, int N, double x0, double y0, double X) {
        this.n0 = n0;
        this.N = N;
        this.x0 = x0;
        this.X = X;
        this.y0 = y0;
        n = new int[N-n0+1];
        error = new double[N-n0+1];
        for (int i = 0; i < (n.length);i++)
            n[i] = n0+i;
    }

    public void findErrorEuler() {
        System.out.println(n.length);

        for (int i = 0; i < n.length; i++) {
            Euler euler = new Euler(X, x0, y0, n[i]);
            euler.EulerMethod();
            ExactSolution exactSolution = new ExactSolution(X,x0,y0,n[i]);
            exactSolution.ParticularSolution();
            LocalError localError = new LocalError(euler.x,exactSolution.y,euler.y);
            localError.findError();
            error[i] = localError.maxError();
        }
    }

    public void findIEulerError() {

        for (int i = 0; i < n.length; i++) {
            IEuler iEuler = new IEuler(X, x0, y0, n[i]);
            iEuler.IEulerMethod();
            ExactSolution exactSolution = new ExactSolution(X,x0,y0,n[i]);
            exactSolution.ParticularSolution();
            LocalError localError = new LocalError(iEuler.x,exactSolution.y,iEuler.y);
            localError.findError();
            error[i] = localError.maxError();
        }
    }

    public void findRungeKuttaError() {

        for (int i = 0; i < n.length; i++) {
            RungeKutta rungeKutta = new RungeKutta(X, x0, y0, n[i]);
            rungeKutta.RungeKuttaMethod();
            ExactSolution exactSolution = new ExactSolution(X,x0,y0,n[i]);
            exactSolution.ParticularSolution();
            LocalError localError = new LocalError(rungeKutta.x,exactSolution.y,rungeKutta.y);
            localError.findError();
            error[i] = localError.maxError();
        }
    }


}
