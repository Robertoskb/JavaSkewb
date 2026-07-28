package br.com.javaskewb;

import br.com.javaskewb.Controller.SkewbBase;
import br.com.javaskewb.Cube.Skewb;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SkewbBase skewbBase = new SkewbBase();

        Skewb skewb = skewbBase.getSkewb();

        skewbBase.update();

        StackPane root = new StackPane();

        root.getChildren().add(skewbBase);

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
