package Revival;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ManualController implements Initializable {
    public SwingNode nodeswing;
    public Button graphgen;
    public Pane pane;
    public JFXButton nodebutton;

    public TextField capacity;
    public JFXButton addVertex;
    public ImageView refresh;
    public Button maxflow;
    public int nodeNumber;
    public static mxGraphComponent graphComponent;
    public static int[][] adjArray;
    //    public ComboBox startcombo;
//    public ComboBox endcombo;
    public JFXComboBox capacityCombo;
    public JFXComboBox nodeCombo;
    public Button addNode;
    public Button deledge;
    public Button delnode;
    public TextField nodeIndex;
    public AnchorPane rootpane;
    public JFXComboBox startcombo;
    public JFXComboBox endcombo;
    public JFXButton autoGenButton;
    public JFXTextField nodesauto;
    public JFXComboBox endopt;
    public JFXComboBox startopt;
    public Button goopt;
    public Tab optimize;
    public Button gonode;
    public Button addedgeop;
    public JFXComboBox capop;
    public Button capopgo;
    MyGraph[] myGraph;

    ObservableList<Integer> generateNumbers(int start, int end) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        for (int i = start; i <= end; i++) {

            arrayList.add(i);
        }
        System.out.println(arrayList);

        ObservableList<Integer> list = FXCollections.observableArrayList(arrayList);
        return list;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        capopgo.setVisible(false);
        capop.setVisible(false);
        gonode.setVisible(false);
        BackgroundImage myBI = new BackgroundImage(new Image("Revival//bg.png", 1500, 750, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        rootpane.setBackground(new Background(myBI));
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        nodeCombo.setItems(generateNumbers(6, 12));
        nodeCombo.setValue(6);

        myGraph = new MyGraph[1];
        addNode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                capopgo.setVisible(false);
                capop.setVisible(false);
                endopt.setVisible(false);
                gonode.setVisible(false);
                goopt.setVisible(false);
                myGraph[0].addVertex();
                nodeswing.setContent(myGraph[0].visualizeGraph());
            }
        });
        autoGenButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Yet to be done
                nodeNumber = Integer.parseInt(nodesauto.getText());
                myGraph[0] = new MyGraph(nodeNumber, "");
                myGraph[0].insertAllVertex();
                myGraph[0].insertAlledges();

                nodeswing.setContent(myGraph[0].visualizeGraph());


            }
        });
        deledge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                capopgo.setVisible(false);
                capop.setVisible(false);
                gonode.setVisible(false);
                goopt.setVisible(true);
                endopt.setVisible(true);
                ObservableList<String> list1 = FXCollections.observableArrayList(myGraph[0].getArrayList());
                ObservableList<String> list2 = FXCollections.observableArrayList(myGraph[0].getArrayList());
                startopt.setItems(list1);
                startopt.setValue("S");
                endcombo.setItems(list2);
                endopt.setValue("A");
                startopt.getItems().remove("T");
                endopt.getItems().remove("S");
                startopt.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                        ObservableList<String> list1 = FXCollections.observableArrayList(myGraph[0].getArrayList());
                        ObservableList<String> list2 = FXCollections.observableArrayList(myGraph[0].getArrayList());
                        startopt.setItems(list1);

                        endopt.setItems(list2);

                        startopt.getItems().remove("T");
                        endopt.getItems().remove("S");
                        endopt.setItems(list2);
                        String output = newValue;
                        endopt.getItems().remove(output);
                    }
                });


            }
        });
        nodebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println(nodeCombo.getValue());
                nodeNumber = (Integer) nodeCombo.getValue();

                myGraph[0] = new MyGraph(nodeNumber);
                myGraph[0].insertAllVertex();


                ObservableList<String> list1 = FXCollections.observableArrayList(myGraph[0].getArrayList());
                ObservableList<String> list2 = FXCollections.observableArrayList(myGraph[0].getArrayList());
                startcombo.setItems(list1);
                startcombo.setValue("S");
                endcombo.setItems(list2);
                endcombo.setValue("A");
                capacityCombo.setItems(generateNumbers(5, 20));
                startcombo.getItems().remove("T");
                endcombo.getItems().remove("S");
                nodeswing.setContent(myGraph[0].visualizeGraph());
                startcombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                        ObservableList<String> list1 = FXCollections.observableArrayList(myGraph[0].getArrayList());
                        ObservableList<String> list2 = FXCollections.observableArrayList(myGraph[0].getArrayList());
                        startcombo.setItems(list1);

                        endcombo.setItems(list2);

                        capacityCombo.setItems(generateNumbers(5, 20));
                        startcombo.getItems().remove("T");
                        endcombo.getItems().remove("S");
                        endcombo.setItems(list2);
                        String output = newValue;
                        endcombo.getItems().remove(output);
                    }
                });

            }
        });
        goopt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String start = String.valueOf(startopt.getValue());
                String end = String.valueOf(endopt.getValue());
                myGraph[0].deleteEdge(start, end);
                nodeswing.setContent(myGraph[0].visualizeGraph());


            }
        });
        gonode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                myGraph[0].deleteVertex(String.valueOf(startopt.getValue()));


                nodeswing.setContent(myGraph[0].visualizeGraph());

            }
        });
        delnode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                capopgo.setVisible(false);
                capop.setVisible(false);
                endopt.setVisible(false);
                gonode.setVisible(true);
                goopt.setVisible(false);
                ObservableList<String> list1 = FXCollections.observableArrayList(myGraph[0].getArrayList());
                startopt.setItems(list1);
                startopt.getItems().remove("S");


            }
        });
        addedgeop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                capopgo.setVisible(true);
                goopt.setVisible(false);
                endopt.setVisible(true);
                capop.setVisible(true);
                ObservableList<String> list1 = FXCollections.observableArrayList(myGraph[0].getArrayList());
                ObservableList<String> list2 = FXCollections.observableArrayList(myGraph[0].getArrayList());
                startopt.setItems(list1);

                endopt.setItems(list2);

                capop.setItems(generateNumbers(5, 20));
                startopt.getItems().remove("T");
                endopt.getItems().remove("S");
                endopt.getItems().remove(startcombo.getValue());
            }

    });
        capopgo.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            capopgo.setVisible(true);
            capop.setVisible(true);
            String start = String.valueOf(startopt.getValue());
            String end = (String) endopt.getValue();
            myGraph[0].insertEdge(start, end, String.valueOf(capop.getValue()));
            nodeswing.setContent(myGraph[0].visualizeGraph());


        }
    });
        addVertex.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                capopgo.setVisible(false);

                ObservableList<String> list1 = FXCollections.observableArrayList(myGraph[0].getArrayList());
                ObservableList<String> list2 = FXCollections.observableArrayList(myGraph[0].getArrayList());
                startcombo.setItems(list1);

                endcombo.setItems(list2);

                capacityCombo.setItems(generateNumbers(5, 20));
                startcombo.getItems().remove("T");
                endcombo.getItems().remove("S");
                endcombo.getItems().remove(startcombo.getValue());
                String start = (String) startcombo.getValue();
                System.out.println(start);
                String end = (String) endcombo.getValue();
                String capacity = String.valueOf(capacityCombo.getValue());
                System.out.println(end);
                myGraph[0].insertEdge(start, end, capacity);
                nodeswing.setContent(myGraph[0].visualizeGraph());
            }
        });
        maxflow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                adjArray = myGraph[0].getAdjMatrix();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/RevivalMax/MaxFx.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root);
                Stage stage = new Stage(StageStyle.UTILITY);
                stage.setScene(scene);
                stage.setTitle("Maximum Flow");
                stage.setMaximized(true);
                stage.show();

            }

        });


    }


    public void refresh(MouseEvent mouseEvent) {
        nodeswing.setContent(myGraph[0].visualizeGraph());
    }
}
