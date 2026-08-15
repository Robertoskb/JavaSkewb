package br.com.javaskewb.Controller;

import br.com.javaskewb.Controller.Components.CaseCard;
import br.com.javaskewb.Controller.Components.CaseInfo;
import br.com.javaskewb.Controller.Components.SkewbBase;
import br.com.javaskewb.Controller.utils.Pagination;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Patterns.Case;

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

    @FXML StackPane root;

    @FXML
    private ToggleButton btnEG2;

    @FXML
    private ToggleButton btnAll;

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
    private ToggleGroup groupFilters;

    @FXML
    private ToggleGroup groupMethods;

    @FXML
    private Label lblPage;

    private int currentPage = 1;

    private Pagination<CaseCard> casePagination;

    private final CaseInfo caseInfo = new CaseInfo();

    public CasesController() throws IOException {
    }

    public void initialize() throws IOException {
        ArrayList<CaseCard> nodes = new ArrayList<>();
        casePagination = new Pagination<>(nodes, 16);


        NSCases cases = new NSCases();

        for (Case _case: cases.getCases()){
            State state = State.getSolvedState();
            _case.applyCase(state);

            SkewbBase skewbBase = new SkewbBase(state);
            skewbBase.changeBottomDisabled();

            CaseCard caseCard = new CaseCard();
            caseCard.setSkewbComponent(skewbBase);
            caseCard.setCaseName(_case.getName());
            caseCard.setSkewbCase(_case);

            nodes.add(caseCard);
        }

        root.getChildren().add(caseInfo);


        populateGrid(nodes);
    }

    public void populateGrid(List<CaseCard> components) {
        lblPage.setText("Página " + currentPage + " de " + casePagination.getArrayList().size()/casePagination.getPageSize());
        int index = 0;

        for (Node node : gridCases.getChildren()) {
            if (node instanceof StackPane slot) {
                slot.getChildren().clear();

                if (index < components.size()) {
                    CaseCard caseCard = getE(components, index);
                    slot.getChildren().add(caseCard);

                    caseCard.setOnMouseClicked(e -> {
                        caseInfo.show(caseCard.getSkewbCase(), null);
                    });


                } else {
                    slot.getChildren().add(new Label("Empty"));
                }
                index++;
            }
        }
    }

    private static CaseCard getE(List<CaseCard> components, int index) {
        return components.get(index);
    }

    @FXML
    private void nextPage(){
        if (casePagination.getPageSize()*(currentPage+1)  < casePagination.getArrayList().size()) {
            currentPage++;
            populateGrid(casePagination.getPage(currentPage));
        }
    }

    @FXML
    private void prevPage(){
        if (currentPage > 1) {
            currentPage--;
            populateGrid(casePagination.getPage(currentPage));
        }
    }

}

