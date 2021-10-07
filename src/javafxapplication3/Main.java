import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.Model_Labb5;
import view.View_Labb5;

/**
 * Creates a window and ties model, view and controller together.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // create model, view and controller, and tie them together
        Model_Labb5 model = new Model_Labb5();
        View_Labb5 view = new View_Labb5(model); // also creates the controller

        // create the window, add the view, and show it
        Scene scene = new Scene(view, 1080, 640);
        primaryStage.setTitle("Image editor");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
