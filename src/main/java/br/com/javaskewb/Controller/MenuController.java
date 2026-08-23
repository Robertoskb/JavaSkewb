package br.com.javaskewb.Controller;

import br.com.javaskewb.ui.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button btnAbout;

    @FXML
    private Button btnLearn;

    @FXML
    private Button btnTimer;

    private final ScreenManager screenManager = ScreenManager.getInstance();

    @FXML
    void toAbout(ActionEvent event) {
    }

    @FXML
    void toCases(ActionEvent event) throws IOException {
        screenManager.setScene("cases.fxml");
    }

    @FXML
    void toTimer(ActionEvent event) throws IOException {
        screenManager.setScene("timer.fxml");
    }

}
