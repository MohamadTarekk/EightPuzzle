package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private GameWindowController gameWindowController = new GameWindowController();

    @Override
    public void start(Stage primaryStage){
        gameWindowController.initialize(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
