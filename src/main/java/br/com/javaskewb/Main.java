package br.com.javaskewb;

import br.com.javaskewb.Controller.SkewbBase;
import br.com.javaskewb.Cube.Skewb;
import br.com.javaskewb.Mapping.Parts.Center;
import br.com.javaskewb.Mapping.Parts.Corner;
import br.com.javaskewb.Mapping.State;
import br.com.javaskewb.Solution.BFSSkewb;
import br.com.javaskewb.ui.ScreenLoader;
import br.com.javaskewb.ui.ScreenManager;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ScreenManager screenManager = ScreenManager.getInstance();
        stage.setMaximized(true);
        screenManager.setStage(stage);

        screenManager.setScene("Main.fxml");

        screenManager.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
