package RevivalMax;

import Revival.ManualController;
import com.mxgraph.swing.mxGraphComponent;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import sample.MaxFlow;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MaxMainController implements Initializable {
    public SwingNode maxflowgraph;
    public SwingNode graph;
    public Text flowtext;
    public Button residualGraphs;
    public Button augPath;
    public ImageView refresh;
    GraphVisualizer graphVisualizer;
    ArrayList<int[][]> residualgraphs;
    int[][] adjArray;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adjArray = ManualController.adjArray;
        graphVisualizer = new GraphVisualizer();
        //ManualController.adjArray;
        mxGraphComponent mxGraphComponent = ManualController.graphComponent;
        //  graph.setContent(mxGraphComponent);
        graph.setContent(graphVisualizer.completeVisualize(adjArray, false, null));
        int[][] maxFlowarray = MaxFlow.findMaxFlow(adjArray);


        mxGraphComponent maxFlowComponent = graphVisualizer.completeVisualize(maxFlowarray, true, adjArray);
        maxflowgraph.setContent(maxFlowComponent);
        flowtext.setText("Maximum Flow:" + MaxFlow.maxflow);

        residualgraphs = MaxFlow.residualgraphs;
        for (int i = 0; i < residualgraphs.size(); i++) {
            System.out.println("*******************************************");

            for (int j = 0; j < residualgraphs.get(i).length; j++) {
                for (int k = 0; k < residualgraphs.get(i).length; k++) {
                    System.out.print(residualgraphs.get(i)[j][k] + " ");
                }
                System.out.println();
            }
            System.out.println("*******************************************");
        }


        residualGraphs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JFrame f = new JFrame();//creating instance of JFrame
                JButton b = new JButton("click");//creating instance of JButton
                graphVisualizer.currentgraph = null;
                for (int i = 0; i < residualgraphs.size(); i++) {
                    mxGraphComponent residualComponent;
                    residualComponent = graphVisualizer.residuale(adjArray, residualgraphs.get(i));
                    System.out.println("-------------------------------------");
                    for (int j = 0; j < adjArray.length; j++) {
                        for (int k = 0; k < adjArray.length; k++) {

                            System.out.print(residualgraphs.get(i)[j][k] + " ");

                        }
                        System.out.println();
                    }
                    JPanel panel = new JPanel();

                    panel.add(residualComponent);
                    f.add(panel);

                }


                f.setLayout(new GridLayout(3, 3));

                f.setSize(400, 500);//400 width and 500 height
                f.setVisible(true);//making the frame visible

            }
        });
        augPath.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JFrame f = new JFrame();//creating instance of JFrame
                JButton b = new JButton("click");//creating instance of JButton
                graphVisualizer.currentgraph = null;
                for (int i = 0; i < residualgraphs.size(); i++) {
                    mxGraphComponent residualComponent;
                    residualComponent = graphVisualizer.residualVisualize(adjArray, residualgraphs.get(i));

                    JPanel panel = new JPanel();

                    panel.add(residualComponent);
                    f.add(panel);

                }


                f.setLayout(new GridLayout(3, 3));

                f.setSize(400, 500);//400 width and 500 height
                f.setVisible(true);//making the frame visible
            }
        });
    }

    public void refreshGraph(MouseEvent mouseEvent) {
        graph.setContent(graphVisualizer.completeVisualize(adjArray, false, null));
        int[][] maxFlowarray = MaxFlow.findMaxFlow(adjArray);


        mxGraphComponent maxFlowComponent = graphVisualizer.completeVisualize(maxFlowarray, true, adjArray);
        maxflowgraph.setContent(maxFlowComponent);
        flowtext.setText("Maximum Flow:" + MaxFlow.maxflow);

    }
}
