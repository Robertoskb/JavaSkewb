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

    @FXML
    private Button moveMode;

    private SkewbBase skewbBase;

    private boolean wcaMode = false;

    public void initialize() throws IOException {
        skewbBase = new SkewbBase();
        skewbBase.getSkewb().toAdvanced();
        moveMode.setText("WCA");

        skewbBase.changeVisibility();

        skewbContainer.getChildren().add(skewbBase);

        addButtons();

        isSolved();
    }

    public void addButtons(){
        movesContainer.getChildren().clear();
        List<String> keys = skewbBase.getSkewb().getMoves().getNotation().keySet().stream().sorted().toList();
        for (String move: keys){
            Button button = new Button(move);

            button.setOnMouseClicked(e -> {
                skewbBase.applyScramble(move);
                isSolved();
            });

            movesContainer.getChildren().add(button);
        }
    }

    public void changeVisibility(){
        skewbBase.changeVisibility();
    }

    public void isSolved(){
        scrambleText.setText(skewbBase.getSkewb().isSolved()? "Solved": "Solving");
    }

    public void changeMoveMode(){
        if (wcaMode) {
            skewbBase.getSkewb().toAdvanced();
            moveMode.setText("WCA");
        } else {
            skewbBase.getSkewb().toWCA();
            moveMode.setText("Advanced");
        }

        addButtons();

        wcaMode = !wcaMode;
    }
}
