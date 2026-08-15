package br.com.javaskewb.Controller;

import br.com.javaskewb.Controller.Components.CaseCard;
import br.com.javaskewb.Controller.Components.SkewbBase;
import br.com.javaskewb.core.Cube.Skewb;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Patterns.Case;
import br.com.javaskewb.core.Patterns.EG2.Sets.SetCases;
import br.com.javaskewb.core.Patterns.NS.FL.FLCase;
import br.com.javaskewb.core.Patterns.NS.FL.FLCases;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.CL.PeanutFLCases;
import br.com.javaskewb.core.Patterns.NS.NSCases;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CasesController {

    @FXML
    private ToggleButton btnEG2;

    @FXML
    private ToggleButton btnFL;

    @FXML
    private ToggleButton btnL5C;

    @FXML
    private ToggleButton btnNS;

    @FXML
    private Button btnNext;

    @FXML
    private ToggleButton btnPeanut;

    @FXML
    private ToggleButton btnPi;

    @FXML
    private Button btnPrev;

    @FXML
    private GridPane gridCases;

    @FXML
    private ToggleGroup grupoFiltros;

    @FXML
    private ToggleGroup grupoMetodos;

    @FXML
    private Label lblPage;

    public void initialize() throws IOException {
        List<Node> nodes = new ArrayList<>();

        PeanutFLCases cases = new PeanutFLCases();

        for (Case _case: cases.getCases()){
            State state = State.getSolvedState();
            _case.applyCase(state);

            SkewbBase skewbBase = new SkewbBase(state);
            skewbBase.changeBottomDisabled();

            CaseCard caseCard = new CaseCard();
            caseCard.setSkewbComponent(skewbBase);
            caseCard.setCaseName(_case.getName());

            nodes.add(caseCard);

        }

        populateGrid(nodes);
    }

    public void populateGrid(List<Node> components) {
        int index = 0;

        for (Node node : gridCases.getChildren()) {
            if (node instanceof StackPane slot) {
                slot.getChildren().clear();

                if (index < components.size()) {
                    slot.getChildren().add(components.get(index));
                } else {
                    slot.getChildren().add(new Label("Empty"));
                }
                index++;
            }
        }
    }

}

