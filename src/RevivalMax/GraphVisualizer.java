package RevivalMax;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

import javax.swing.*;
import java.util.Hashtable;

public class GraphVisualizer {
    static int[][] currentgraph;

    public mxGraphComponent residualVisualize(int[][] flowgraph, int[][] residualgraph) {

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        Object[] vs = new Object[flowgraph.length];
        int a = 65;

        for (int i = 0; i < flowgraph.length; i++) {

            if (i == 0) {
                vs[i] = graph.insertVertex(parent, "S", "S", 500, 40 * (i + 20), 50, 50, "shape=ellipse;perimeter=100");
            } else if (i == flowgraph.length - 1) {

                vs[i] = graph.insertVertex(parent, "T", "T", 500, 40 * (i + 20), 50, 50, "shape=ellipse;perimeter=100");

            } else {

                //v0=graph.insertVertex(graph.getDefaultParent(), null,from, 0, 0,300,100,"shape=ellipse;perimeter=100;whiteSpace=wrap;fillColor=green")
                vs[i] = graph.insertVertex(parent, String.valueOf((char) a), String.valueOf((char) a), 500, 40 * (i + 20), 50, 50, "shape=ellipse");

                a++;
            }

        }


        graph.getModel().beginUpdate();
        try {
            for (int i = 0; i < flowgraph.length; i++)
                for (int j = 0; j < flowgraph.length; j++) {
                    if (flowgraph[i][j] > 0) {
                        if (flowgraph[i][j] != residualgraph[i][j]) {
                            int value = flowgraph[i][j] - residualgraph[i][j];
                            if (value < 0) {
                                graph.insertEdge(parent, null, "0/" + flowgraph[i][j], vs[i], vs[j]);

                            } else if (currentgraph != null) {

                                if (currentgraph[i][j] != residualgraph[i][j]) {
                                    graph.insertEdge(parent, null, value + "/" + flowgraph[i][j], vs[i], vs[j], "dashed=1;strokeColor=green;fontColor=#FF0000");

                                } else {
                                    graph.insertEdge(parent, null, value + "/" + flowgraph[i][j], vs[i], vs[j], "strokeColor=blue");

                                }
                            } else {
                                graph.insertEdge(parent, null, value + "/" + flowgraph[i][j], vs[i], vs[j], "dashed=1;strokeColor=green;strokeColor=green");
                            }
                        } else {
                            graph.insertEdge(parent, null, "0/" + flowgraph[i][j], vs[i], vs[j]);
                        }

                    }
                }
        } finally {
            graph.getModel().endUpdate();
            mxStylesheet stylesheet = graph.getStylesheet();
            Hashtable<String, Object> style = new Hashtable<String, Object>();
            style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_DOUBLE_ELLIPSE);
            style.put(mxConstants.STYLE_OPACITY, 50);
            style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
            stylesheet.putCellStyle("ROUNDED", style);
            graph.setGridEnabled(true);
            graph.setStylesheet(stylesheet);
            mxGraphComponent graphComponent = new mxGraphComponent(graph);
            // mxCircleLayout layout = new mxCircleLayout(graphAdapter);
            // layout.execute(graphAdapter.getDefaultParent());
            currentgraph = residualgraph;
            // new mxCircleLayout(graph).execute(graph.getDefaultParent());
            mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
            layout.setOrientation(SwingConstants.WEST);
            layout.execute(graph.getDefaultParent());
            layout.setIntraCellSpacing(10);
            return graphComponent;
            // new mxParallelEdgeLayout(graph).execute(graph.getDefaultParent());
        }


    }

    public mxGraphComponent completeVisualize(int[][] maxflowgraph, boolean max, int[][] adjArray) {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        Object[] vs = new Object[maxflowgraph.length];
        int a = 65;
        String styleCustom = "";
        if (max) {
            styleCustom = "fontColor=#FF0000";
        } else {
            styleCustom = "fontColor=blue";
        }
        for (int i = 0; i < maxflowgraph.length; i++) {

            if (i == 0) {

                vs[i] = graph.insertVertex(parent, "S", "S", 500, 40 * (i + 20), 50, 50, "shape=ellipse;perimeter=100;");
            } else if (i == maxflowgraph.length - 1) {

                vs[i] = graph.insertVertex(parent, "T", "T", 500, 40 * (i + 20), 50, 50, "shape=ellipse;perimeter=100");

            } else {

                //v0=graph.insertVertex(graph.getDefaultParent(), null,from, 0, 0,300,100,"shape=ellipse;perimeter=100;whiteSpace=wrap;fillColor=green")
                vs[i] = graph.insertVertex(parent, String.valueOf((char) a), String.valueOf((char) a), 500, 40 * (i + 20), 50, 50, "shape=ellipse");

                a++;
            }

        }


        graph.getModel().beginUpdate();
        try {
            for (int i = 0; i < maxflowgraph.length; i++)
                for (int j = 0; j < maxflowgraph.length; j++) {
                    if (maxflowgraph[i][j] > 0) {


                        graph.insertEdge(parent, null, maxflowgraph[i][j], vs[i], vs[j], styleCustom);
                    }

                }
        } finally {
            graph.getModel().endUpdate();
            mxStylesheet stylesheet = graph.getStylesheet();
            Hashtable<String, Object> style = new Hashtable<String, Object>();
            style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_DOUBLE_ELLIPSE);
            style.put(mxConstants.STYLE_OPACITY, 50);
            style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
            stylesheet.putCellStyle("ROUNDED", style);
            graph.setGridEnabled(true);
            graph.setStylesheet(stylesheet);
            mxGraphComponent graphComponent = new mxGraphComponent(graph);
            // mxCircleLayout layout = new mxCircleLayout(graphAdapter);
            // layout.execute(graphAdapter.getDefaultParent());

            // new mxCircleLayout(graph).execute(graph.getDefaultParent());
            mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
            layout.setOrientation(SwingConstants.WEST);
            layout.execute(graph.getDefaultParent());
            layout.setIntraCellSpacing(10);
            return graphComponent;
            // new mxParallelEdgeLayout(graph).execute(graph.getDefaultParent());


        }
    }


    public mxGraphComponent residuale(int[][] flowgraph, int[][] residualgraph) {

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        Object[] vs = new Object[flowgraph.length];
        int a = 65;
        vs[0] = graph.insertVertex(parent, "S", "S", 500, 40 * (5 + 20), 50, 50, "shape=ellipse;perimeter=100");

        for (int i = 1; i < flowgraph.length-1; i++) {


                //v0=graph.insertVertex(graph.getDefaultParent(), null,from, 0, 0,300,100,"shape=ellipse;perimeter=100;whiteSpace=wrap;fillColor=green")
                vs[i] = graph.insertVertex(parent, String.valueOf((char) a), String.valueOf((char) a), 500, 40 * (i + 20), 50, 50, "shape=ellipse");

                a++;


        }
        vs[flowgraph.length-1] = graph.insertVertex(parent, "T", "T", 500, 40 * (5 + 20), 50, 50, "shape=ellipse;perimeter=100");



        graph.getModel().beginUpdate();
        try {
            for (int i = 0; i < flowgraph.length; i++)
                for (int j = 0; j < flowgraph.length; j++) {

                    int value = flowgraph[i][j] - residualgraph[i][j];

                    if (residualgraph[i][j] > 0)
                        graph.insertEdge(parent, null, residualgraph[i][j] , vs[i], vs[j]);


                }
        } finally {
            graph.getModel().endUpdate();
            mxStylesheet stylesheet = graph.getStylesheet();
            Hashtable<String, Object> style = new Hashtable<String, Object>();
            style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_DOUBLE_ELLIPSE);
            style.put(mxConstants.STYLE_OPACITY, 50);
            style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
            stylesheet.putCellStyle("ROUNDED", style);
            graph.setGridEnabled(true);
            graph.setStylesheet(stylesheet);
            mxGraphComponent graphComponent = new mxGraphComponent(graph);
            // mxCircleLayout layout = new mxCircleLayout(graphAdapter);
            // layout.execute(graphAdapter.getDefaultParent());
            currentgraph = residualgraph;
            // new mxCircleLayout(graph).execute(graph.getDefaultParent());
            mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
            layout.setOrientation(SwingConstants.WEST);
            layout.execute(graph.getDefaultParent());
            layout.setIntraCellSpacing(10);
            return graphComponent;
            // new mxParallelEdgeLayout(graph).execute(graph.getDefaultParent());
        }


    }
}