package br.com.javaskewb.Controller;

import br.com.javaskewb.Mapping.Solve.AdvancedMoves;
import br.com.javaskewb.Mapping.Solve.WCAMoves;
import br.com.javaskewb.Solution.FindSolution;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    @FXML
    private Button solveButton;

    private SkewbBase skewbBase;

    private FindSolution findSolution;

    private AdvancedMoves advancedMoves = new AdvancedMoves();
    private WCAMoves wcaMoves = new WCAMoves();

    private boolean wcaMode = false;

    public void initialize() throws IOException {
        skewbBase = new SkewbBase();
        skewbBase.getSkewb().toAdvanced();
        moveMode.setText("WCA");

        findSolution = new FindSolution(advancedMoves, skewbBase.getSkewb().getSolvedStates());

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

    public void changeButtonsDisable(){
        solveButton.setDisable(!solveButton.isDisable());
        moveMode.setDisable(!moveMode.isDisable());
        for (Node node: movesContainer.getChildren()){
            node.setDisable(!node.isDisabled());
        }
    }

    public void autoSolve(){
        changeButtonsDisable();

        String scramble = String.join(" ", findSolution.find(skewbBase.getSkewb().getState()));

        scrambleText.setText("Self Solution: " + scramble);

        skewbBase.applyScrambleAnimation(scramble, () -> {
            changeButtonsDisable();

            isSolved();
        });

    }

    public void changeVisibility(){
        skewbBase.changeVisibility();
    }

    public void isSolved(){
        boolean solved = skewbBase.getSkewb().isSolved();

        solveButton.setDisable(solved);

        scrambleText.setText(solved? "Solved": "Solving");
    }

    public void changeMoveMode(){
        if (wcaMode) {
            skewbBase.getSkewb().toAdvanced();
            findSolution.setMoves(advancedMoves);
            moveMode.setText("WCA");
        } else {
            skewbBase.getSkewb().toWCA();
            findSolution.setMoves(wcaMoves);
            moveMode.setText("Advanced");
        }

        addButtons();

        wcaMode = !wcaMode;
    }
}
