


import javax.swing.*;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import sample.MaxFlow;

import java.awt.*;

import static sample.MaxFlow.getrandom;

public class JUST extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -2707712944901661771L;
    public Button findmaxflow;
    public FlowPane leftpane;
    public FlowPane rightpane;

    public JUST() {

        super("Hello, World!");
//        int grapHMatrix[][] = new int[][]{
//                {0, 16, 13, 0, 0, 0},
//                {0, 0, 0, 12, 0, 0},
//                {0, 4, 0, 0, 14, 0},
//                {0, 0, 9, 0, 0, 20},
//                {0, 0, 0, 7, 0, 4},
//                {0, 0, 0, 0, 0, 0}
//        };
//        int grapHMatrix2[][] = new int[][]{
//                {0, 16, 13, 0},
//                {0, 0, 0, 12},
//                {0, 4, 0, 0},
//                {0, 0, 9, 0},
//
//
//        };
        int grapHMatrix[][] = genrategraph();
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

        leftpane = new FlowPane();
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        // mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        // layout.execute(graphAdapter.getDefaultParent());
        new mxCircleLayout(graph).execute(graph.getDefaultParent());
        // new mxHierarchicalLayout(graph).execute(graph.getDefaultParent());
        // new mxParallelEdgeLayout(graph).execute(graph.getDefaultParent());
        getContentPane().add(graphComponent);


    }

    public static void main(String[] args) {

        JUST frame = new JUST();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 320);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.setVisible(true);
        JPanel jPanel = new JPanel(new GridLayout());
        JButton button = new JButton();
        jPanel.add(button);
        frame.add(jPanel);

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
}
