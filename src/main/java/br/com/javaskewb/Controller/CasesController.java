package br.com.javaskewb.Controller;

import br.com.javaskewb.Controller.Components.CaseCard;
import br.com.javaskewb.Controller.Components.CaseInfo;
import br.com.javaskewb.Controller.Components.SkewbBase;
import br.com.javaskewb.Controller.utils.Pagination;
import br.com.javaskewb.DataManager.Manager.Manager;
import br.com.javaskewb.DataManager.utils.BitState;
import br.com.javaskewb.DataManager.utils.SaveState;
import br.com.javaskewb.core.Cube.Skewb;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Patterns.Methods.FS.FSCase;
import br.com.javaskewb.core.Patterns.Methods.Methods;
import br.com.javaskewb.core.Patterns.base.Case;

import br.com.javaskewb.core.Patterns.base.Cases;
import br.com.javaskewb.core.Patterns.utils.TreeCases;
import br.com.javaskewb.ui.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

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
    private Button btnExport;

    @FXML
    private GridPane gridCases;

    @FXML
    private ToggleGroup groupFilters;

    @FXML
    private ToggleGroup groupMethods;

    @FXML
    private Label lblPage;

    @FXML
    private Label casesName;

    private int currentPage = 1;

    private final Methods methods = Methods.getInstance();
    private String currentMethod;

    private Cases<?> currentCases;
    private final TreeCases treeCases = new TreeCases();
    private ArrayList<Cases<?>> currentSubCases;

    private final Pagination<Integer> intPagination = new Pagination<>(16);

    private final CaseInfo caseInfo = new CaseInfo();

    private final HashMap<Integer, CaseCard> caseCaseCardCache = new HashMap<>();

    private final Manager manager = Manager.getInstance();

    private final ScreenManager screenManager = ScreenManager.getInstance();

    public CasesController() throws IOException {
    }

    public void initialize() throws IOException {
        root.getChildren().add(caseInfo);

        for (Cases<?> method: methods.getMETHODS()){
            treeCases.insertNodes(method);
            Button button = new Button(method.getName());
            button.getStyleClass().add("method-button");

            button.setOnMouseClicked(event -> {
                try {
                    updateMethod(method);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            methodsContainer.getChildren().add(button);
        }

        updateMethod(methods.getMETHODS().getFirst());

        Button button = new Button("Sair");
        button.getStyleClass().add("method-button");

        button.setOnMouseClicked(event -> {
            try {
                toMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        methodsContainer.getChildren().add(button);
    }

    private void updateMethod(Cases<?> method) throws IOException {
        currentMethod = method.getName();
        currentCases = method;
        while (currentCases.getSubCases().size() == 1){
            currentCases = currentCases.getSubCases().getFirst();
        }
        currentSubCases = currentCases.getSubCases();

        intPagination.setArrayList(new ArrayList<>(IntStream.range(0, currentCases.getCases().size()).boxed().toList()));

        categoryContainer.getChildren().clear();
        updateButtons();
        currentPage = 1;

        populateGrid(getCaseCards(currentCases, currentPage));
    }

    private void updateButtons(){
        casesName.setText(currentCases.getName());

        if (currentSubCases.isEmpty()){
            categoryContainer.getChildren().clear();
            Cases<?> parent = treeCases.getParent(currentCases);
            if (parent != null){
                Button button = new Button("Voltar");
                button.setOnMouseClicked(e -> {
                    try {
                        updateCases(parent);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                button.getStyleClass().add("category-button");
                categoryContainer.getChildren().add(button);

            }
            return;
        }

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

                    button.getStyleClass().add("category-button");
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

            button.getStyleClass().add("category-button");
            categoryContainer.getChildren().add(button);
        }
    }

    private void updateCases(Cases<?> cases) throws IOException {
        currentCases = cases;
        currentSubCases = currentCases.getSubCases();

        intPagination.setArrayList(new ArrayList<>(IntStream.range(0, currentCases.getCases().size()).boxed().toList()));
        currentPage = 1;
        populateGrid(getCaseCards(currentCases, currentPage));

        updateButtons();
    }

    private ArrayList<CaseCard> getCaseCards(Cases<?> cases, int page) throws IOException {
        ArrayList<CaseCard> nodes = new ArrayList<>();

        ArrayList<Integer> sub = intPagination.getPage(page);

        for (Case _case: cases.getCases().subList(sub.getFirst(), sub.getLast()+1)){
            if (caseCaseCardCache.containsKey(_case.getId())){
                nodes.add(caseCaseCardCache.get(_case.getId()));
            }
            else {
                SkewbBase skewbBase = new SkewbBase(_case.getCaseSkewb());
                if (! (_case instanceof FSCase))
                    skewbBase.changeBottomDisabled();

                CaseCard caseCard = new CaseCard();
                caseCard.setSkewbComponent(skewbBase);
                caseCard.setCaseName(_case.getName());
                caseCard.setSkewbCase(_case);

                caseCaseCardCache.put(_case.getId(), caseCard);

                nodes.add(caseCard);
            }
        }

        return nodes;
    }

    public void populateGrid(List<CaseCard> components) {
        int division = intPagination.getArrayList().size() / intPagination.getPageSize();
        if (division * intPagination.getPageSize() < intPagination.getArrayList().size())
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
                        caseInfo.show(caseCard.getSkewbCase());
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
    private void nextPage() throws IOException {
        if (intPagination.getPageSize()*(currentPage) < intPagination.getArrayList().size()) {
            currentPage++;
            populateGrid(getCaseCards(currentCases, currentPage));
        }
    }

    @FXML
    private void prevPage() throws IOException {
        if (currentPage > 1) {
            currentPage--;
            populateGrid(getCaseCards(currentCases, currentPage));
        }
    }

    @FXML
    private void exportCases(){
        Set<Long> subSet =  new HashSet<>();

        for (Case subCase: currentCases.getCases()){
            BitState bitState = SaveState.getBitState(subCase.applyCase(State.getSolvedState(), true));
            subSet.add(bitState.getId());
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exportar Casos");

        fileChooser.setInitialFileName(currentMethod + " " + currentCases.getName() + ".json");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos JSON","*.json"));

        File file = fileChooser.showSaveDialog(screenManager.getStage());

        if (file != null){
            boolean exported = manager.exportAlgorithms(subSet, file);
            if  (exported)
                System.out.println("Exportado com sucesso");
        }
    }

    @FXML
    private void importCases(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importar Casos");

        fileChooser.setInitialFileName(currentCases.getName() + ".json");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos JSON","*.json"));

        File file = fileChooser.showOpenDialog(screenManager.getStage());

        if (file != null){
             boolean imported = manager.importAlgorithms(file);

            if  (imported)
                System.out.println("Importado com sucesso");
        }
    }

    @FXML
    public void toMenu() throws IOException {
        screenManager.setScene("Main.fxml");
    }

}

