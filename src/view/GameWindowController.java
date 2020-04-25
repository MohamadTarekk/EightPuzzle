package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameWindowController {
    private Stage window;

    public void initialize(Stage primaryStage) {
        window = primaryStage;
        try {
            // Get relative path of fxml
            Path currentRelativePath = Paths.get("");
            String path = currentRelativePath.toAbsolutePath().toString() + "/src/view/GameWindow.fxml";
            // Load fxml into scene
            Parent root = FXMLLoader.load(new File(path).toURI().toURL());
            Scene scene = new Scene(root, 300, 275);
            // Initialize stage
            window.setTitle("Hello World");
            window.setScene(scene);
            window.show();
        } catch(Exception e) {
            System.out.println("Failed to load scene.\n");
        }
    }
}
