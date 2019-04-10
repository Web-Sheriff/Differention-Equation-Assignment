package Project;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class Controller {

    public NumberAxis x;
    public NumberAxis y;
    public LineChart graph;
    public LineChart graph_l_e;
    public LineChart graph_g_e;
    public Button methods;
    public Button global_errors;
    public TextField X;
    public TextField x0;
    public TextField y0;
    public TextField n;
    public TextField n0;
    public TextField N;

    public void initialize() {

        methods.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            getGraph(Double.parseDouble(x0.getText()), Double.parseDouble(X.getText()),
                    Double.parseDouble(y0.getText()),Integer.parseInt(n.getText()));
            getLError(Double.parseDouble(x0.getText()), Double.parseDouble(X.getText()),
                    Double.parseDouble(y0.getText()),Integer.parseInt(n.getText()));
        });
        global_errors.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> getGError(Double.parseDouble(x0.getText()), Double.parseDouble(X.getText()),
                Double.parseDouble(y0.getText()),Integer.parseInt(n.getText()), Integer.parseInt(n0.getText()),Integer.parseInt(N.getText())));
    }

    public void getGraph(double x0, double X, double y0, int n) {
        graph.getData().clear();

        Euler euler = new Euler(X,x0,y0,n);
        IEuler iEuler = new IEuler(X,x0,y0,n);
        RungeKutta rungeKutta = new RungeKutta(X,x0,y0,n);
        ExactSolution exactSolution = new ExactSolution(X,x0,y0,n);

        euler.EulerMethod();
        iEuler.IEulerMethod();
        rungeKutta.RungeKuttaMethod();
        exactSolution.ParticularSolution();

        XYChart.Series eulerGraph = new XYChart.Series();
        XYChart.Series iEulerGraph = new XYChart.Series();
        XYChart.Series rungeKuttaGraph = new XYChart.Series();
        XYChart.Series exactGraph = new XYChart.Series();

        eulerGraph.setName("Euler");
        iEulerGraph.setName("Improved Euler");
        rungeKuttaGraph.setName("Runge-Kutta");
        exactGraph.setName("Exact Solution");

        for (int i = 0; i < euler.x.length; i++) {
            eulerGraph.getData().add(new XYChart.Data<>(euler.x[i],euler.y[i]));
            iEulerGraph.getData().add(new XYChart.Data<>(iEuler.x[i],iEuler.y[i]));
            rungeKuttaGraph.getData().add(new XYChart.Data<>(rungeKutta.x[i],rungeKutta.y[i]));
            exactGraph.getData().add(new XYChart.Data<>(exactSolution.x[i],exactSolution.y[i]));
        }

        graph.getData().add(eulerGraph);
        graph.getData().add(iEulerGraph);
        graph.getData().add(rungeKuttaGraph);
        graph.getData().add(exactGraph);


    }

    public void getLError(double x0, double X, double y0, int n) {
        graph_l_e.getData().clear();

        ExactSolution exactSolution = new ExactSolution(X,x0,y0,n);
        exactSolution.ParticularSolution();

        Euler euler = new Euler(X,x0,y0,n);
        euler.EulerMethod();
        LocalError localErrorEuler = new LocalError(euler.x, exactSolution.y, euler.y);
        localErrorEuler.findError();

        IEuler iEuler = new IEuler(X,x0,y0,n);
        iEuler.IEulerMethod();
        LocalError localErrorIEuler = new LocalError(iEuler.x, exactSolution.y, iEuler.y);
        localErrorIEuler.findError();

        RungeKutta rungeKutta = new RungeKutta(X,x0,y0,n);
        rungeKutta.RungeKuttaMethod();
        LocalError localErrorRungeKutta = new LocalError(rungeKutta.x, exactSolution.y, rungeKutta.y);
        localErrorRungeKutta.findError();

        XYChart.Series eulerGraph = new XYChart.Series();
        XYChart.Series iEulerGraph = new XYChart.Series();
        XYChart.Series rungeKuttaGraph = new XYChart.Series();

        eulerGraph.setName("Euler");
        iEulerGraph.setName("Improved Euler");
        rungeKuttaGraph.setName("Runge-Kutta");

        for (int i = 0; i < euler.x.length; i++) {
            eulerGraph.getData().add(new XYChart.Data<>(localErrorEuler.x[i],localErrorEuler.error[i]));
            iEulerGraph.getData().add(new XYChart.Data<>(localErrorIEuler.x[i],localErrorIEuler.error[i]));
            rungeKuttaGraph.getData().add(new XYChart.Data<>(localErrorRungeKutta.x[i],localErrorRungeKutta.error[i]));
        }

        graph_l_e.getData().add(eulerGraph);
        graph_l_e.getData().add(iEulerGraph);
        graph_l_e.getData().add(rungeKuttaGraph);
    }

    public void getGError(double x0, double X, double y0, int n, int n0, int N) {
        graph_g_e.getData().clear();

        ExactSolution exactSolution = new ExactSolution(X,x0,y0,n);
        exactSolution.ParticularSolution();

        Euler euler = new Euler(X,x0,y0,n);
        euler.EulerMethod();
        GlobalError globalErrorEuler = new GlobalError(n0,N,euler.x0,euler.y0,euler.X);
        globalErrorEuler.findErrorEuler();

        IEuler iEuler = new IEuler(X,x0,y0,n);
        iEuler.IEulerMethod();
        GlobalError globalErrorIEuler = new GlobalError(n0,N,iEuler.x0,iEuler.y0,iEuler.X);
        globalErrorIEuler.findIEulerError();

        RungeKutta rungeKutta = new RungeKutta(X,x0,y0,n);
        rungeKutta.RungeKuttaMethod();
        GlobalError globalErrorRungeKutta = new GlobalError(n0,N,rungeKutta.x0, rungeKutta.y0, rungeKutta.X);
        globalErrorRungeKutta.findRungeKuttaError();


        XYChart.Series eulerGraph = new XYChart.Series();
        XYChart.Series iEulerGraph = new XYChart.Series();
        XYChart.Series rungeKuttaGraph = new XYChart.Series();

        eulerGraph.setName("Euler");
        iEulerGraph.setName("Improved Euler");
        rungeKuttaGraph.setName("Runge-Kutta");

        for (int i = 0; i < globalErrorEuler.n.length; i++) {
            eulerGraph.getData().add(new XYChart.Data<>(globalErrorEuler.n[i],globalErrorEuler.error[i]));
            iEulerGraph.getData().add(new XYChart.Data<>(globalErrorIEuler.n[i],globalErrorIEuler.error[i]));
            rungeKuttaGraph.getData().add(new XYChart.Data<>(globalErrorRungeKutta.n[i],globalErrorRungeKutta.error[i]));
        }

        graph_g_e.getData().add(eulerGraph);
        graph_g_e.getData().add(iEulerGraph);
        graph_g_e.getData().add(rungeKuttaGraph);
    }

}
