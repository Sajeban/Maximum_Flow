package sample;

import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javax.swing.*;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.MaxFlow.getrandom;

public class Controller extends JFrame implements Initializable {
    public Button graphgenerate;
    public Pane graphpane;
    public Button calMx;
    public Pane flowgraph;
    mxGraphComponent graphComponent;
    int grapHMatrix[][] = new int[][]{
            {0, 16, 13, 0, 0, 0},
            {0, 0, 0, 12, 0, 0},
            {0, 4, 0, 0, 14, 0},
            {0, 0, 9, 0, 0, 20},
            {0, 0, 0, 7, 0, 4},
            {0, 0, 0, 0, 0, 0}
    };


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        graphVisualizer();

    }

    public void generate(ActionEvent actionEvent) {
        JFrame jFrame = new JFrame();
        graphpane.getChildren().clear();
        int grapHMatrix2[][] = new int[][]{
                {0, 16, 13, 0},
                {0, 0, 0, 12},
                {0, 4, 0, 0},
                {0, 0, 9, 0},


        };
        grapHMatrix = genrategraph();
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        Object[] vs = new Object[grapHMatrix.length];
        for (int i = 0; i < grapHMatrix.length; i++) {
            vs[i] = graph.insertVertex(parent, null, i, 500, 40 * (i + 20), 80, 30, "fillColor=blue");


        }

        graph.getModel().beginUpdate();
        try {
            for (int i = 0; i < grapHMatrix.length; i++)
                for (int j = 0; j < grapHMatrix.length; j++) {
                    if (grapHMatrix[i][j] > 0) {


                        graph.insertEdge(parent, null, grapHMatrix[i][j], vs[i], vs[j]);
                    }
                }
        } finally {
            graph.getModel().endUpdate();
        }

        graphComponent = new mxGraphComponent(graph);
        // mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        // layout.execute(graphAdapter.getDefaultParent());

        new mxCircleLayout(graph).execute(graph.getDefaultParent());
        // new mxHierarchicalLayout(graph).execute(graph.getDefaultParent());
        // new mxParallelEdgeLayout(graph).execute(graph.getDefaultParent());

        SwingNode s = new SwingNode();
        s.setContent(graphComponent);
        System.out.println("Hiiiiiiiiiiiiiiiiii");

        graphpane.getChildren().add(s);
        System.out.println("Hii222222222222");

    }

    public void calculateMaxFlow(ActionEvent actionEvent) {
        int[][] maxflowgraph = MaxFlow.findMaxFlow(new int[6][6]);


        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        Object[] vs = new Object[maxflowgraph.length];
        for (int i = 0; i < maxflowgraph.length; i++) {
            vs[i] = graph.insertVertex(parent, null, i, 500, 40 * (i + 20), 80, 30, "fillColor=blue");


        }
        graph.getModel().beginUpdate();
        try {
            for (int i = 0; i < maxflowgraph.length; i++)
                for (int j = 0; j < maxflowgraph.length; j++) {
                    if (maxflowgraph[i][j] > 0) {


                        graph.insertEdge(parent, null, maxflowgraph[i][j], vs[i], vs[j]);
                    }
                }
        } finally {
            graph.getModel().endUpdate();
        }

        graphComponent = new mxGraphComponent(graph);
        // mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        // layout.execute(graphAdapter.getDefaultParent());
        JPanel jPanel = new JPanel();
        new mxCircleLayout(graph).execute(graph.getDefaultParent());
        // new mxHierarchicalLayout(graph).execute(graph.getDefaultParent());
        // new mxParallelEdgeLayout(graph).execute(graph.getDefaultParent());
        jPanel.add(graphComponent);
        SwingNode s = new SwingNode();
        s.setContent(jPanel);
        System.out.println("Hellooooooo");
        flowgraph.getChildren().add(s);
        System.out.println("Hellooooooo2");
        System.out.println("dffkfn  ");
    }

    public static int[][] genrategraph() {

        int random = getrandom(12, 6);
        System.out.print(random + "  ");
        System.out.println();
        int autoGraph[][] = new int[random][random];
        for (int i = 0; i < random - 1; i++) {
            int count = 0;
            for (int j = 1; j < random; j++) {

                if (i != j) {
                    if (autoGraph[j][i] == 0) {
                        autoGraph[i][j] = getrandom(20, 5);
                        count++;


                    }
                }
            }
        }
        int dividebytwo = random / 2;
//        for (int i = 0; i < random; i++) {
//            int count = 0;
//            for (int j = 0; j < autoGraph[i].length; j++) {
//                //  System.out.print(autoGraph[j][i] + " ");
//                if (autoGraph[j][i] > 0) {
//                    count++;
//                } else {
//                    continue;
//                }
//                if (count > dividebytwo) {
//                    autoGraph[j][i] = 0;
//                }
//            }
//            // System.out.println();
//        }

        for (int i = 0; i < random; i++) {
            for (int j = 0; j < autoGraph[i].length; j++) {
                System.out.print(autoGraph[i][j] + " ");
            }
            System.out.println();
        }


        return autoGraph;
    }


    void graphVisualizer() {
        int[][] maxflowgraph = MaxFlow.findMaxFlow(new int[6][6]);


        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        Object[] vs = new Object[maxflowgraph.length];
        for (int i = 0; i < maxflowgraph.length; i++) {
            vs[i] = graph.insertVertex(parent, null, i, 500, 40 * (i + 20), 80, 30, "fillColor=blue");


        }
        graph.getModel().beginUpdate();
        try {
            for (int i = 0; i < maxflowgraph.length; i++)
                for (int j = 0; j < maxflowgraph.length; j++) {
                    if (maxflowgraph[i][j] > 0) {


                        graph.insertEdge(parent, null, maxflowgraph[i][j], vs[i], vs[j]);
                    }
                }
        } finally {
            graph.getModel().endUpdate();
        }

        graphComponent = new mxGraphComponent(graph);
        // mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        // layout.execute(graphAdapter.getDefaultParent());
        JPanel jPanel = new JPanel();
        new mxCircleLayout(graph).execute(graph.getDefaultParent());
        // new mxHierarchicalLayout(graph).execute(graph.getDefaultParent());
        // new mxParallelEdgeLayout(graph).execute(graph.getDefaultParent());
        jPanel.add(graphComponent);
        SwingNode s = new SwingNode();
        s.setContent(jPanel);
        System.out.println("Hellooooooo");
        flowgraph.getChildren().add(s);
        System.out.println("Hellooooooo2");
        System.out.println("dffkfn  ");


    }
}
