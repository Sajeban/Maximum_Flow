package Revival;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class MyGraph {
    private int[][] adjMatrix;
    mxGraph graph;
    Object parent;
    Object[] vs;
    int node;

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    private ArrayList<String> arrayList;

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public MyGraph(int node) {
        this.node = node;
        graph = new mxGraph();
        parent = graph.getDefaultParent();
        Object[] vs = new Object[node];
        this.adjMatrix = new int[node][node];
    }

    public MyGraph(int node, String random) {
        this.node = node;
        graph = new mxGraph();
        parent = graph.getDefaultParent();
        Object[] vs = new Object[node];
        this.adjMatrix = randomGraph(node);


    }

    public static int getrandom(int max, int min) {

        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;

    }

    public int[][] randomGraph(int random) {
        int autoGraph[][] = new int[random][random];
        for (int i = 0; i < random - 1; i++) {
            int count = 0;
            for (int j = 1; j < random; j++) {

                if (i != j) {
                    if ((i == 0||i==1) && j == node - 1) {
                        autoGraph[i][j] = 0;
                    } else
                        autoGraph[i][j] = getrandom(20, 5);


                }
            }
        }


        for (int i = 0; i < random; i++) {
            for (int j = 0; j < autoGraph[i].length; j++) {
                System.out.print(autoGraph[i][j] + " ");
            }
            System.out.println();
        }


        long start = System.currentTimeMillis();
        return autoGraph;

    }


    void insertAllVertex() {
        int nodeNumber = node;
        int a = 65;
        String letter = "";
        Object[] vs = new Object[nodeNumber];
        arrayList = new ArrayList<String>();
        for (int i = 0; i < nodeNumber; i++) {

            if (i == 0) {
                letter = "S";
                vs[i] = graph.insertVertex(parent, letter, letter, 500, 40 * (i + 20), 50, 50, "shape=ellipse;perimeter=20");
            } else if (i == nodeNumber - 1) {
                letter = "T";
                vs[i] = graph.insertVertex(parent, letter, letter, 500, 40 * (i + 20), 50, 50, "shape=ellipse;perimeter=50");

            } else {
                letter = String.valueOf((char) a);
                //v0=graph.insertVertex(graph.getDefaultParent(), null,from, 0, 0,300,100,"shape=ellipse;perimeter=100;whiteSpace=wrap;fillColor=green")
                vs[i] = graph.insertVertex(parent, letter, letter, 500, 40 * (i + 20), 50, 50, "shape=ellipse;perimeter=50");

                a++;
            }
            arrayList.add(letter);

        }


    }

    void addVertex() {

        int[][] newGraph = new int[node + 1][node + 1];
        for (int i = 0; i < node + 1 - 2; i++) {
            for (int j = 0; j < node + 1 - 2; j++) {
                newGraph[i][j] = this.adjMatrix[i][j];

            }
        }
        for (int i = 0; i < node + 1 - 2; i++) {

            newGraph[i][node] = this.adjMatrix[i][node - 1];

        }
        System.out.println("New gRAPH");
        for (int i = 0; i < newGraph.length; i++) {
            for (int j = 0; j < newGraph.length; j++) {
                System.out.print(newGraph[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("oLD gRAPH");
        for (int i = 0; i < node; i++) {
            for (int j = 0; j < node; j++) {
                System.out.print(this.adjMatrix[i][j] + " ");
            }
            System.out.println();
        }

        this.adjMatrix = newGraph;
        this.node = adjMatrix.length;
        this.graph = new mxGraph();
        graph = new mxGraph();
        parent = graph.getDefaultParent();
        this.vs = new Object[node];

        this.insertAllVertex();
        this.insertAlledges();
    }


    public int[][] getAdjMatrix() {
        for (int i = 0; i < node; i++) {
            System.out.println("-----------------------------------");
            for (int j = 0; j < node; j++) {
                System.out.print(this.adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");
        return adjMatrix;
    }

    public void setAdjMatrix(int[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    int[][] testFunction(int[][] sourcearr, int k) {

        System.out.println("source Array ");
        for (int i = 0; i < sourcearr.length; i++) {
            for (int j = 0; j < sourcearr.length; j++) {
                System.out.print(sourcearr[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("source Array ");
        int rows = sourcearr.length;
        int cols = sourcearr.length;
        int[][] matrix = sourcearr;

        int counter = 0;


        //keep copy of original matrix
        int[][] matrixCopy = matrix.clone();

        //Assume
        int rowToRemove = k;
        int colToRemove = k;


        // re-initialise matrix with dimension i-1 , j-1
        matrix = new int[rows - 1][cols - 1];
        counter = 0;

        //row and column counter for the new matrix
        int tmpX = -1;
        int tmpY = -1;


        //re-populate new matrix by searching through the original copy of matrix, while skipping useless row and column
        // works only for 1 row and 1 column in a 2d array but by changing the conditional statement we can make it work for n number of rows or columns in a 2d array.
        for (int i = 0; i < rows; i++) {
            tmpX++;
            if (i == rowToRemove) {
                tmpX--;
            }
            tmpY = -1;
            for (int j = 0; j < cols; j++) {


                tmpY++;
                if (j == colToRemove) {
                    tmpY--;
                }

                if (i != colToRemove && j != colToRemove) {
                    counter++;
                    matrix[tmpX][tmpY] = matrixCopy[i][j];

                    System.out.println(counter + " :" + matrix[tmpX][tmpY]);
                }


            }

        }
        System.out.println("Matrix Array ");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Matrix Array ");
        return matrix;
    }


    int[][] deleteVertex(String s) {
        int size = this.adjMatrix.length - 1;
        int vertex = (s.charAt(0)) - 64;
        System.out.println("After deleting ");
        this.adjMatrix = testFunction(this.adjMatrix, vertex);
        for (int i = 0; i < this.adjMatrix.length; i++) {
            for (int j = 0; j < this.adjMatrix.length; j++) {
                System.out.print(this.adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
        this.node = adjMatrix.length;
        this.graph = new mxGraph();
        graph = new mxGraph();
        parent = graph.getDefaultParent();
        this.vs = new Object[node];

        this.insertAllVertex();
        this.insertAlledges();
        return this.adjMatrix;
    }

    void insertAlledges() {
        String start = "";
        String end = "";
        int capacity = 5;
        for (int i = 0; i < this.node; i++) {

            for (int j = 0; j < this.node; j++) {
                if (this.adjMatrix[i][j] > 0) {
                    {
                        capacity = adjMatrix[i][j];
                        if (i == 0) {
                            start = "S";

                        } else {
                            start = String.valueOf((char) (i + 64));

                        }
                        if (j == node - 1) {
                            end = "T";
                        } else {
                            end = String.valueOf((char) (j + 64));
                        }
                        System.out.println("Start; " + start + " end: " + end + "=" + capacity);
                        graph.insertEdge(parent, null, capacity, ((mxGraphModel) graph.getModel()).getCell(start),
                                ((mxGraphModel) graph.getModel()).getCell(end), "fontColor=#FF0000;");

                    }
                }
            }


        }
    }

    void insertEdge(String start, String end, String capacity) {

        System.out.println(end);
        Object[] edges = graph.getEdgesBetween(((mxGraphModel) graph.getModel()).getCell(start),
                ((mxGraphModel) graph.getModel()).getCell(end));

        for (Object edge : edges) {
            graph.getModel().remove(edge);
        }
        graph.insertEdge(parent, null, capacity, ((mxGraphModel) graph.getModel()).getCell(start),
                ((mxGraphModel) graph.getModel()).getCell(end), "fontColor=#FF0000;");


        int arrayStart;
        int arrayEnd;
        if (start.equals("S")) {
            System.out.println("start: S ");
            arrayStart = 0;
        } else {
            System.out.println((char) ((end.charAt(0)) - 64));
            arrayStart = (start.charAt(0)) - 64;

        }
        if (end.equals("T")) {
            arrayEnd = node - 1;
        } else {
            arrayEnd = (end.charAt(0)) - 64;

        }
        int capac = Integer.valueOf(capacity);

        System.out.println(capacity);
        System.out.println(arrayStart + " - " + arrayEnd + " : " + capac);
        this.adjMatrix[arrayStart][arrayEnd] = capac;


    }

    void deleteEdge(String start, String end) {
        Object[] edges = graph.getEdgesBetween(((mxGraphModel) graph.getModel()).getCell(start), ((mxGraphModel) graph.getModel()).getCell(end));

        for (Object edge : edges) {
            graph.getModel().remove(edge);

            int arrayStart;
            int arrayEnd;
            if (start.equals("S")) {
                System.out.println("start: S ");
                arrayStart = 0;
            } else {
                System.out.println((char) ((end.charAt(0)) - 64));
                arrayStart = (start.charAt(0)) - 64;

            }
            if (end.equals("T")) {
                arrayEnd = node - 1;
            } else {
                arrayEnd = (end.charAt(0)) - 64;

            }
            System.out.println("deleteing start : " + start + "end: " + end);

            this.adjMatrix[arrayStart][arrayEnd] = 0;
            this.adjMatrix[arrayEnd][arrayStart] = 0;
            System.out.println("After deleting");
            for (int i = 0; i < node; i++) {
                for (int j = 0; j < node; j++) {
                    System.out.print(this.adjMatrix[i][j]+"\t");
                }
                System.out.println();
            }
            System.out.println("After deleting");
        }


    }

    JPanel visualizeGraph() {
        mxStylesheet stylesheet = graph.getStylesheet();
        Hashtable<String, Object> style = new Hashtable<String, Object>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_DOUBLE_ELLIPSE);
        style.put(mxConstants.STYLE_OPACITY, 50);
        style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
        stylesheet.putCellStyle("ROUNDED", style);
        graph.setGridEnabled(true);
        graph.setStylesheet(stylesheet);
        graph.setGridSize(1);
        graph.setAllowDanglingEdges(true);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        // mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        // layout.execute(graphAdapter.getDefaultParent());

        // new mxCircleLayout(graph).execute(graph.getDefaultParent());
        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.setOrientation(SwingConstants.WEST);
        layout.execute(graph.getDefaultParent());

        JPanel panel = new JPanel();
        panel.setSize(1000, 500);
        panel.add(graphComponent);

        return panel;
    }
}
