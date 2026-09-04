package br.com.javaskewb.ui;

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
    }

}
