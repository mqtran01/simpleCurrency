import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Michael on 18/07/2017.
 */
public class Handler extends Application {

    public static void main(String[] args) {
        System.out.println("Hello world");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ViewCurrency.fxml"));

        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }
}
