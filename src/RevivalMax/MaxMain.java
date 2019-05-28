package RevivalMax;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MaxMain extends Application{
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MaxFx.fxml"));
        primaryStage.setTitle("Maxmimum Flow");
        primaryStage.setScene(new Scene(root, 1400, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
