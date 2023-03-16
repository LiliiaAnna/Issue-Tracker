//Main class for launching the application
package issuetracker;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scenes.Login;

public class IssueTracker extends Application implements Serializable {

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Login().getScene(primaryStage);

        primaryStage.setTitle("Issue Tracker Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        launch(args);
    }
}
