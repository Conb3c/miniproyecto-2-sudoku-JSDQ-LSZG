package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Main entry point of the Sudoku JavaFX application.
 * <p>
 * This class loads the main FXML view, creates the primary scene,
 * configures the application window, and starts the graphical interface.
 * </p>
 */

public class Main extends Application {
    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments passed to the application
     */

    public static void main(String[] args) {
        launch(args);
    }
    /**
     * Initializes and displays the main application window.
     *
     * @param primaryStage the primary stage provided by the JavaFX runtime
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                       "/com/example/miniproyecto2sudokujsdqlszg/GameView.fxml"
                )
        );
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
