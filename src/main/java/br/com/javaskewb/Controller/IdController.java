package br.com.javaskewb.Controller;

import br.com.javaskewb.Controller.Components.SkewbBase;
import br.com.javaskewb.DataManager.Manager.LongStates;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Solution.Solution;
import br.com.javaskewb.core.utils.StateRank;
import br.com.javaskewb.ui.ScreenManager;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.List;
import java.util.OptionalLong;

public class IdController {

    private static final List<String> STATUS_STYLES = List.of(
            "id-status-info",
            "id-status-valid",
            "id-status-invalid"
    );

    @FXML
    private TextField txtId;

    @FXML
    private Button btnGenerate;

    @FXML
    private Button btnRandom;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblScramble;

    @FXML
    private StackPane skewbContainer;

    private final ScreenManager screenManager = ScreenManager.getInstance();

    private final Solution solution = new Solution();

    private final State solvedState = State.getSolvedState();

    @FXML
    private void initialize() {
        setIdControlsDisabled(true);

        Task<Boolean> loadIds = new Task<>() {
            @Override
            protected Boolean call() {
                return LongStates.hasIds();
            }
        };

        loadIds.setOnSucceeded(event -> {
            if (loadIds.getValue()) {
                setIdControlsDisabled(false);
                setStatus("Digite um ID ou gere um ID aleatório.", "id-status-info");
            } else {
                setStatus("Nenhum ID válido foi encontrado em idsWCA.bin.", "id-status-invalid");
            }
        });

        loadIds.setOnFailed(event ->
                setStatus("Não foi possível carregar idsWCA.bin.", "id-status-invalid")
        );

        Thread loaderThread = new Thread(loadIds, "ids-wca-loader");
        loaderThread.setDaemon(true);
        loaderThread.start();
    }

    @FXML
    private void generateFromInput() {
        String text = txtId.getText().trim();

        if (text.isEmpty()) {
            showInvalidId("Digite um ID para gerar o estado.");
            return;
        }

        try {
            showState(Long.parseLong(text));
        } catch (NumberFormatException exception) {
            showInvalidId("O ID deve ser um número inteiro.");
        }
    }

    @FXML
    private void generateRandomId() {
        OptionalLong randomId = LongStates.getRandomId();

        if (randomId.isEmpty()) {
            showInvalidId("Nenhum ID válido foi encontrado em idsWCA.bin.");
            return;
        }

        long id = randomId.getAsLong();
        txtId.setText(Long.toString(id));
        showState(id);
    }

    private void showState(long id) {
        boolean isValid = LongStates.contains(id);
        lblScramble.setVisible(isValid);
        if (!isValid) {
            showInvalidId("ID inválido: ele não está no arquivo de IDs válidos.");
        }

        try {
            State state = StateRank.createState(id);
            SkewbBase skewbBase = new SkewbBase(state);
            skewbContainer.getChildren().setAll(skewbBase);
            if (isValid) {
                setStatus("ID válido. Estado gerado com sucesso.", "id-status-valid");
                lblScramble.setText("Scramble: " + solution.findScramble(solvedState, state));
            }
        } catch (IOException | RuntimeException exception) {
            System.out.println(exception.getMessage());
            showInvalidId("Não foi possível gerar o estado para este ID.");
        }
    }

    private void showInvalidId(String message) {
        Label placeholder = new Label("Informe um ID válido para visualizar o Skewb.");
        placeholder.getStyleClass().add("id-preview-placeholder");
        skewbContainer.getChildren().setAll(placeholder);
        setStatus(message, "id-status-invalid");
    }

    private void setStatus(String message, String styleClass) {
        lblStatus.setText(message);
        lblStatus.getStyleClass().removeAll(STATUS_STYLES);
        lblStatus.getStyleClass().add(styleClass);
    }

    private void setIdControlsDisabled(boolean disabled) {
        txtId.setDisable(disabled);
        btnGenerate.setDisable(disabled);
        btnRandom.setDisable(disabled);
    }

    @FXML
    private void toMenu() throws IOException {
        screenManager.setScene("menu.fxml");
    }
}
