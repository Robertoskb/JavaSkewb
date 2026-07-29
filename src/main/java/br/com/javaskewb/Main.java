package br.com.javaskewb;

import br.com.javaskewb.Controller.SkewbBase;
import br.com.javaskewb.Cube.Skewb;
import br.com.javaskewb.Mapping.Parts.Center;
import br.com.javaskewb.Mapping.Parts.Corner;
import br.com.javaskewb.Mapping.State;
import br.com.javaskewb.Solution.BFSSkewb;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        SkewbBase skewbBase = new SkewbBase();

        StackPane root = new StackPane();

        root.getChildren().add(skewbBase);

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
        skewbBase.applyScrambleAnimation("U L R' B R' L R L'");

    }

    public static void main(String[] args) {
        launch(args);
    }
}
