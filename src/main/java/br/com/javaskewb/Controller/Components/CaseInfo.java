package br.com.javaskewb.Controller.Components;

import br.com.javaskewb.DataManager.Manager.Manager;
import br.com.javaskewb.DataManager.Manager.StateInfo;
import br.com.javaskewb.core.Mapping.Moves.AdvancedMoves;
import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Patterns.base.Case;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class CaseInfo extends VBox {

    @FXML private Label lblName;
    @FXML private ToggleButton btnFav;
    @FXML private ComboBox<String> cbStatus;
    @FXML private VBox algorithmsList;
    @FXML private TextField txtNewAlg;
    @FXML private FlowPane notationContainer;
    @FXML private Button btnAddAlg;

    @FXML private StackPane skewbContainer;

    private final SkewbBase skewbBase = new SkewbBase();

    private final AdvancedMoves moves = new AdvancedMoves();

    private final ArrayList<String> arrayMoves = new ArrayList<>();

    private final Manager manager = Manager.getInstance();

    private StateInfo stateInfo;
    private Case skewbCase;

    private final ArrayList<String> algorithms = new ArrayList<>();

    public CaseInfo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/javaskewb/view/components/CaseInfo.fxml"));
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

        cbStatus.getItems().addAll("Desconheço", "Aprendendo", "Aprimorando", "Conheço");

        txtNewAlg.setEditable(false);

        Set<String> notation = new TreeSet<>(moves.getNotation().keySet());
        for (String move: notation){
            Button button = new Button(move);

            button.setOnMouseClicked(event -> {
                arrayMoves.add(move);
                updateNewAlg();
                skewbBase.applyScramble(move);
                updateSolved();
            });

            notationContainer.getChildren().add(button);
        }
        Button button = new Button("⬅");
        button.setOnMouseClicked(event -> {
            if (!arrayMoves.isEmpty()){
                String move = arrayMoves.removeLast();
                updateNewAlg();
                CentersFaces centersFaces = moves.invertMove(move);
                skewbBase.applyMove(centersFaces);
                updateSolved();
            }
                }
        );
        notationContainer.getChildren().add(button);
    }

    private void updateNewAlg(){
        txtNewAlg.setText(String.join(" ", arrayMoves));
    }

    public void show(Case skewbCase){
        if (this.isVisible())
            return;

        this.skewbCase = skewbCase;
        skewbCase.applyCase(skewbBase.getSkewb().getState());
        skewbBase.update();

        stateInfo = manager.getStateInfo(skewbBase.getSkewb().getState());

        lblName.setText(skewbCase.getName());

        cbStatus.setValue(stateInfo.getStatus());

        for (String algorithm: stateInfo.getAlgorithms()) {
            addAlgorithm(algorithm);
        }

        btnFav.setSelected(stateInfo.isFavorite());
        toggleFavorite();

        btnAddAlg.setDisable(true);
        this.setVisible(true);
    }

    private void updateSolved(){
        btnAddAlg.setDisable(!skewbBase.getSkewb().isSolved());
    }

    @FXML
    private void close(){
        skewbBase.reset();
        algorithmsList.getChildren().clear();
        algorithms.clear();
        txtNewAlg.clear();
        arrayMoves.clear();
        stateInfo = null;

        this.setVisible(false);
    }

    @FXML
    private void toggleFavorite() {
        if (btnFav.isSelected()) {
            btnFav.setText("★");
        } else {
            btnFav.setText("☆");
        }
    }

    @FXML
    private void addNewAlgorithm() {
        skewbBase.reset();

        skewbCase.applyCase(skewbBase.getSkewb().getState());
        skewbBase.update();
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
        });

        algRow.getChildren().addAll(lblAlg, btnDel);
        algorithmsList.getChildren().add(algRow);
        algorithms.add(alg);
    }

    @FXML
    private void save(){
        stateInfo.setFavorite(btnFav.isSelected());

        stateInfo.getAlgorithms().clear();
        stateInfo.getAlgorithms().addAll(algorithms);
        stateInfo.setStatus(cbStatus.getValue());

        manager.save();
    }
}