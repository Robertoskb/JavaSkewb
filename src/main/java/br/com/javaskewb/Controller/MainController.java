package br.com.javaskewb.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.javaskewb.Controller.Components.CaseCard;
import br.com.javaskewb.Controller.Components.SkewbBase;
import br.com.javaskewb.core.Cube.Skewb;
import br.com.javaskewb.core.Mapping.Moves.AdvancedMoves;
import br.com.javaskewb.core.Mapping.Moves.WCAMoves;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Patterns.Case;
import br.com.javaskewb.core.Patterns.NS.NSCase;
import br.com.javaskewb.core.Patterns.NS.NSCases;
import br.com.javaskewb.core.Solution.Solution;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainController {
    @FXML
    private BorderPane root;

    @FXML
    private VBox scrambleContainer;

    @FXML
    private Text scrambleText;

    @FXML
    private StackPane skewbContainer;

    @FXML
    private HBox movesContainer;

    @FXML
    private Button moveMode;

    @FXML
    private Button solveButton;

    @FXML
    private VBox leftContainer;

    @FXML
    private VBox rightContainer;

    private SkewbBase skewbBase;

    private Solution solution;

    private final AdvancedMoves advancedMoves = new AdvancedMoves();
    private final WCAMoves wcaMoves = new WCAMoves();

    private boolean wcaMode = false;

    public void initialize() throws IOException {
        State state = State.getSolvedState();

        skewbBase = new SkewbBase(state);
        skewbBase.changeDisabled();

        ArrayList<NSCase> cases = new NSCases().getCases();

        for (Case LLCase : cases) {
            State miniState = State.getSolvedState();

            LLCase.applyCase(miniState);

            SkewbBase miniBase = new SkewbBase(miniState);

            miniBase.changeBottomDisabled();

            CaseCard caseCard = new CaseCard();

            caseCard.setSkewbComponent(miniBase);
            caseCard.setCaseName(LLCase.getName());

            caseCard.setOnMouseClicked(event -> {
                LLCase.applyCase(skewbBase.getSkewb().getState());
                skewbBase.update();
                isSolved();
            });

            rightContainer.getChildren().add(caseCard);
        }


        skewbBase.getSkewb().toAdvanced();
        moveMode.setText("WCA");

        solution = new Solution(advancedMoves, skewbBase.getSkewb().getSolvedStates());


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

        Skewb skewb = skewbBase.getSkewb();
        ArrayList<String> solution = this.solution.findSolution(skewb.getState(), skewb.getSolvedStates());

        if (solution == null){
            scrambleText.setText("Solução não encontrada");
            return;
        }

        String scramble = String.join(" ", solution);

        scrambleText.setText("Auto Solution: " + scramble);

        skewbBase.applyScrambleAnimation(scramble, () -> {
            changeButtonsDisable();

            isSolved();

            scrambleText.setText(scramble);
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
            solution.setMoves(advancedMoves);
            moveMode.setText("WCA");
        } else {
            skewbBase.getSkewb().toWCA();
            solution.setMoves(wcaMoves);
            moveMode.setText("Advanced");
        }

        addButtons();

        wcaMode = !wcaMode;
    }
}
