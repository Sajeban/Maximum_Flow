package HomePage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends JFrame implements Initializable {
    public Button random;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        random.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root);
                Stage stage = new Stage(StageStyle.UTILITY);
                stage.setScene(scene);
                stage.setTitle("Home Page");
                stage.show();
            }
        });
    }

}
