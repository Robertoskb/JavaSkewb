package br.com.javaskewb;

import br.com.javaskewb.core.Patterns.Methods.Methods;
import br.com.javaskewb.ui.ScreenManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ScreenManager screenManager = ScreenManager.getInstance();
        screenManager.setStage(stage);

        screenManager.setScene("menu.fxml");

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
