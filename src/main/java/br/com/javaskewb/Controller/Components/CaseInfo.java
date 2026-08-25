package br.com.javaskewb.Controller.Components;

import br.com.javaskewb.DataManager.Manager.Manager;
import br.com.javaskewb.DataManager.Manager.StateInfo;
import br.com.javaskewb.core.Cube.Skewb;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Mapping.Moves.AdvancedMoves;
import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Solution.Solution;
import br.com.javaskewb.core.Solution.utils.Scramble;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

public class CaseInfo extends VBox {

    @FXML private Label lblName;
    @FXML private Label lblScramble;
    @FXML private ToggleButton btnFav;
    @FXML private ComboBox<String> cbStatus;
    @FXML private VBox algorithmsList;
    @FXML private TextField txtNewAlg;
    @FXML private FlowPane notationContainer;
    @FXML private Button btnAddAlg;
    @FXML private Button btnSave;

    @FXML private StackPane skewbContainer;

    private final SkewbBase skewbBase = new SkewbBase();

    private final AdvancedMoves moves = new AdvancedMoves();

    private final ArrayList<String> arrayMoves = new ArrayList<>();

    private final Manager manager = Manager.getInstance();

    private StateInfo stateInfo;

    private final HashSet<String> algorithms = new HashSet<>();
    private final Solution solution = new Solution();

    public CaseInfo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/javaskewb/ui/components/CaseInfo.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        skewbBase.getSkewb().toAdvanced();

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.setVisible(false);
        skewbContainer.getChildren().add(skewbBase);

        cbStatus.getItems().addAll("Desconhecido", "Aprendendo", "Aprimorando", "Conhecido");

        txtNewAlg.setEditable(false);

        Set<String> notation = new TreeSet<>(moves.getNotation().keySet());
        for (String move: notation){
            Button button = new Button(move);

            button.setOnMouseClicked(event -> applyMove(move));

            notationContainer.getChildren().add(button);
        }

        Button sButon = new Button("S");
        Button hButon = new Button("H");

        List<String> s = List.of("r'",  "R", "r", "R'");
        List<String> h = List.of("R", "r'", "R'", "r");

        sButon.setOnMouseClicked(event -> s.forEach(this::applyMove));

        hButon.setOnMouseClicked(event -> h.forEach(this::applyMove));

        notationContainer.getChildren().add(sButon);
        notationContainer.getChildren().add(hButon);

        Button button = new Button("⬅");
        button.setOnMouseClicked(event -> {
            if (!arrayMoves.isEmpty()){
                String move = arrayMoves.removeLast();
                updateNewAlg();
                CentersFaces centersFaces = moves.invertMove(move);
                skewbBase.applyMove(centersFaces);
                updateSolved();
            }
        });

        cbStatus.valueProperty().addListener((observable, oldValue, newValue) -> {
            setSaveButton();
        });
        notationContainer.getChildren().add(button);
    }

    private void applyMove(String move) {
        if (!skewbBase.getSkewb().isSolved()){
            arrayMoves.add(move);
            updateNewAlg();
            skewbBase.applyScramble(move);
            updateSolved();
        }
    }

    public void setSaveButton(){
        boolean value = algorithms.equals(stateInfo.getAlgorithms()) &&
                        btnFav.isSelected() == stateInfo.isFavorite() &&
                        stateInfo.getStatus().equals(cbStatus.getValue());
        btnSave.setDisable(value);
    }

    private void updateNewAlg(){
        txtNewAlg.setText(String.join(" ", arrayMoves));
    }

    public void show(Case skewbCase){
        if (this.isVisible())
            return;

        Skewb caseSkewb = skewbCase.getCaseSkewb();
        skewbBase.setSkewb(caseSkewb);

        Scramble scramble = solution.findScramble(caseSkewb.getSolvedState(), caseSkewb.getState());
        lblScramble.setText("Scramble: " + scramble);

        stateInfo = manager.getStateInfo(skewbCase.applyCase(State.getSolvedState(), true));

        lblName.setText(skewbCase.getName());

        cbStatus.setValue(stateInfo.getStatus());

        for (String algorithm: stateInfo.getAlgorithms()) {
            addAlgorithm(algorithm);
        }

        btnFav.setSelected(stateInfo.isFavorite());
        toggleFavorite();

        btnAddAlg.setDisable(true);
        this.setVisible(true);
        this.setOpacity(0);

        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), this);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();
    }

    private void updateSolved(){
        if (algorithms.contains(txtNewAlg.getText().trim()))
            return;
        btnAddAlg.setDisable(!skewbBase.getSkewb().isSolved());
    }

    @FXML
    private void close(){
        algorithmsList.getChildren().clear();
        algorithms.clear();
        txtNewAlg.clear();
        arrayMoves.clear();
        stateInfo = null;

        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), this);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(e -> this.setVisible(false));
        fade.play();
    }

    @FXML
    private void toggleFavorite() {
        if (btnFav.isSelected()) {
            btnFav.setText("★");
        } else {
            btnFav.setText("☆");
        }

        setSaveButton();
    }

    @FXML
    private void addNewAlgorithm() {
        skewbBase.reset();
        updateSolved();

        String newAlg = txtNewAlg.getText().trim();
        if (!newAlg.isEmpty()) {
            addAlgorithm(newAlg);
            txtNewAlg.clear();
            arrayMoves.clear();
        }
    }

    public void addAlgorithm(String alg) {
        HBox algRow = new HBox(5);
        algRow.setAlignment(Pos.CENTER_LEFT);

        Label lblAlg = new Label("• " + alg);
        lblAlg.setTextFill(Color.web("#d4d4d8"));
        lblAlg.setWrapText(true);
        HBox.setHgrow(lblAlg, Priority.ALWAYS);

        Button btnDel = new Button("x");
        btnDel.setStyle("-fx-background-color: transparent; -fx-text-fill: #ef4444; -fx-cursor: hand; -fx-padding: 0 5 0 5; -fx-font-weight: bold;");
        btnDel.setOnAction(e -> {
            algorithmsList.getChildren().remove(algRow);
            algorithms.remove(alg);
            setSaveButton();
        });

        algRow.getChildren().addAll(lblAlg, btnDel);
        algorithmsList.getChildren().add(algRow);
        algorithms.add(alg);
        setSaveButton();
    }

    @FXML
    private void save(){
        stateInfo.setFavorite(btnFav.isSelected());

        stateInfo.getAlgorithms().clear();
        stateInfo.getAlgorithms().addAll(algorithms);
        stateInfo.setStatus(cbStatus.getValue());

        if (manager.save())
            setSaveButton();
    }
}