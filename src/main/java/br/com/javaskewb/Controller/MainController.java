package br.com.javaskewb.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainController {
    @FXML
    private BorderPane root;

    @FXML
    private VBox scrambleContainer;

    @FXML
    private Text scrambleText;

    @FXML
    private VBox skewbContainer;

    @FXML
    private HBox movesContainer;

    private SkewbBase skewbBase;

    public void initialize() throws IOException {
        skewbBase = new SkewbBase();

        skewbContainer.getChildren().add(skewbBase);

        skewbBase.getSkewb().toAdvanced();
        List<String> keys = skewbBase.getSkewb().getMoves().getNotation().keySet().stream().sorted().toList();

        for (String move: keys){
            Button button = new Button(move);

            button.setOnMouseClicked(e -> skewbBase.applyScramble(move));

            movesContainer.getChildren().add(button);
        }
    }

    public void changeVisibility(){
        skewbBase.changeVisibility();
    }
}
