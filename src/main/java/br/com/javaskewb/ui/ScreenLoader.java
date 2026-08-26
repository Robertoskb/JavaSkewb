package br.com.javaskewb.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ScreenLoader {
    public FXMLLoader loader(String fxml){
        String basePath = "/br/com/javaskewb/ui/";

        return new FXMLLoader(getClass().getResource(basePath + fxml));
    }

    public Parent load(String fxml) throws IOException {
        FXMLLoader loader = loader(fxml);

        return loader.load();
    }

}
