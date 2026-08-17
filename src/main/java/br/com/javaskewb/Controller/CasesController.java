package br.com.javaskewb.Controller;

import br.com.javaskewb.Controller.Components.CaseCard;
import br.com.javaskewb.Controller.Components.CaseInfo;
import br.com.javaskewb.Controller.Components.SkewbBase;
import br.com.javaskewb.Controller.utils.Pagination;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Patterns.Methods.Methods;
import br.com.javaskewb.core.Patterns.base.Case;

import br.com.javaskewb.core.Patterns.base.Cases;
import br.com.javaskewb.core.Patterns.Methods.NS.NSCases;
import br.com.javaskewb.core.Patterns.utils.TreeCases;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CasesController {

    @FXML StackPane root;

    @FXML
    HBox categoryContainer;

    @FXML
    VBox methodsContainer;

    @FXML
    private Button btnNext;

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

    private final Methods methods = new Methods();

    private Cases<?> currentCases;
    private final TreeCases treeCases = new TreeCases();
    private ArrayList<Cases<?>> currentSubCases;

    private final Pagination<CaseCard> casePagination = new Pagination<>(16);

    private final CaseInfo caseInfo = new CaseInfo();

    private final HashMap<Case, CaseCard> caseCaseCardCache = new HashMap<>();

    public CasesController() throws IOException {
    }

    public void initialize() throws IOException {
        root.getChildren().add(caseInfo);

        for (Cases<?> method: methods.getMETHODS()){
            treeCases.insertNodes(method);
            Button button = new Button(method.getName());

            button.setOnMouseClicked(event -> {
                try {
                    updateMethod(method);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            for (Cases<?> subCases: method.getSubCases())
                getCaseCards(subCases);

            methodsContainer.getChildren().add(button);
        }

        updateMethod(methods.getMETHODS().getFirst());
    }

    private void updateMethod(Cases<?> method) throws IOException {
        currentCases = method;
        while (currentCases.getSubCases().size() == 1){
            currentCases = currentCases.getSubCases().getFirst();
        }
        currentSubCases = currentCases.getSubCases();

        categoryContainer.getChildren().clear();
        updateButtons();
        ArrayList<CaseCard> nodes = getCaseCards(currentCases);
        casePagination.setArrayList(nodes);
        currentPage = 1;
        populateGrid(nodes);
    }

    private void updateButtons(){
        if (currentSubCases.isEmpty())
            return;

        categoryContainer.getChildren().clear();
        Cases<?> parent = treeCases.getParent(currentSubCases.getFirst());
        if (parent != null){
            Cases<?> parentParent = treeCases.getParent(parent);
            if (parentParent != null) {
                if (parentParent.getSubCases().size() > 1) {
                    Button button = new Button("Voltar");
                    button.setOnMouseClicked(e -> {
                        try {
                            updateCases(parentParent);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    categoryContainer.getChildren().add(button);
                }

            }
        }

        for (Cases<?> subCases: currentSubCases) {
            Button button = new Button(subCases.getName());
            if (!subCases.getCases().isEmpty())
                button.setOnMouseClicked(e -> {
                    try {
                        updateCases(subCases);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

            categoryContainer.getChildren().add(button);
        }
    }

    private void updateCases(Cases<?> cases) throws IOException {
        currentCases = cases;
        currentSubCases = currentCases.getSubCases();

        casePagination.setArrayList(getCaseCards(currentCases));
        currentPage = 1;
        populateGrid(casePagination.getPage(currentPage));

        updateButtons();
    }

    private ArrayList<CaseCard> getCaseCards(Cases<?> cases) throws IOException {
        ArrayList<CaseCard> nodes = new ArrayList<>();
        for (Case _case: cases.getCases()){
            State state;

            if (caseCaseCardCache.containsKey(_case)){
                nodes.add(caseCaseCardCache.get(_case));
            }
            else {
                state = State.getSolvedState();
                _case.applyCase(state);

                SkewbBase skewbBase = new SkewbBase(state);
                skewbBase.changeBottomDisabled();

                CaseCard caseCard = new CaseCard();
                caseCard.setSkewbComponent(skewbBase);
                caseCard.setCaseName(_case.getName());
                caseCard.setSkewbCase(_case);

                caseCaseCardCache.put(_case, caseCard);

                nodes.add(caseCard);
            }
        }



        return nodes;
    }

    public void populateGrid(List<CaseCard> components) {
        int division = casePagination.getArrayList().size() / casePagination.getPageSize();
        if (division * casePagination.getPageSize() < casePagination.getArrayList().size())
            division++;
        lblPage.setText("Página " + currentPage + " de " + division);
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
        if (casePagination.getPageSize()*(currentPage) < casePagination.getArrayList().size()) {
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

