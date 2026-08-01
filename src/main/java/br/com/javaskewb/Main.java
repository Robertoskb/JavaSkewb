package br.com.javaskewb;

import br.com.javaskewb.Cube.Skewb;
import br.com.javaskewb.Mapping.Solve.AdvancedMoves;
import br.com.javaskewb.Mapping.Solve.WCAMoves;
import br.com.javaskewb.Mapping.State;
import br.com.javaskewb.Solution.FindSolution;
import br.com.javaskewb.ui.ScreenManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
//        SkewbTranslation translation = new SkewbTranslation();
//        // "U' B L B' L R' L B'"
//        for (String scramble: translation.translate("R B"))
//            System.out.println(scramble + "\n");
    }

}
