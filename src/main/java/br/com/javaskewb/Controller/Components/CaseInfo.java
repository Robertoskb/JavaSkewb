package br.com.javaskewb.Controller.Components;

import br.com.javaskewb.core.Patterns.Case;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

public class CaseInfo extends VBox {

    @FXML private Label lblName;
    @FXML private ToggleButton btnFav;
    @FXML private ComboBox<String> cbStatus;
    @FXML private VBox algorithmsList;
    @FXML private TextField txtNewAlg;

    @FXML private StackPane skewbContainer;

    private final SkewbBase skewbBase = new SkewbBase();

    public CaseInfo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/javaskewb/view/components/CaseInfo.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.setVisible(false);
        skewbContainer.getChildren().add(skewbBase);

        cbStatus.getItems().addAll("Desconheço", "Aprendendo", "Aprimorando", "Conheço");
        cbStatus.setValue("Desconheço");
    }

    public void show(Case skewbCase, String defaultStatus){
        if (this.isVisible())
            return;

        skewbCase.applyCase(skewbBase.getSkewb().getState());
        skewbBase.update();

        lblName.setText(skewbCase.getName());

        this.setVisible(true);
    }

    @FXML
    private void close(){
        skewbBase.reset();

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
        String newAlg = txtNewAlg.getText().trim();
        if (!newAlg.isEmpty()) {
            addAlgorithm(newAlg);
            txtNewAlg.clear();
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
        btnDel.setOnAction(e -> algorithmsList.getChildren().remove(algRow));

        algRow.getChildren().addAll(lblAlg, btnDel);
        algorithmsList.getChildren().add(algRow);
    }
}