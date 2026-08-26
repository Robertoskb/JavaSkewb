package br.com.javaskewb.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenManager {
    private Stage stage;
    private static ScreenManager screenManager;
    private final ScreenLoader screenLoader;

    private ScreenManager(){
        screenLoader = new ScreenLoader();
    }

    public static ScreenManager getInstance(){
        if (screenManager == null)
            screenManager = new  ScreenManager();
        return screenManager;
    }

    public void setScene(String fxml) throws IOException {
        Parent root = screenLoader.load(fxml);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setMaximized(true);
    }

    public void show(){
        stage.show();
    }

    public FXMLLoader loader(String fxml){
        return screenLoader.loader(fxml);
    }

    public Parent load(String fxml) throws IOException {
        return screenLoader.load(fxml);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }


}
