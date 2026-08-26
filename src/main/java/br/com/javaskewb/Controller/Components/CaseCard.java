package br.com.javaskewb.Controller.Components;

import br.com.javaskewb.core.Patterns.base.Case;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CaseCard extends VBox {

    @FXML
    private Label caseNameLabel;

    @FXML
    private StackPane skewbContainer;

    private Case skewbCase;

    public CaseCard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/javaskewb/ui/components/CaseCard.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        loader.load();
    }

    @FXML
    public void initialize() {
    }

    public void setCaseName(String name) {
        if (caseNameLabel != null) {
            caseNameLabel.setText(name);
        }
    }

    public void setSkewbComponent(Node skewbNode) {
        if (skewbContainer != null && skewbNode != null) {
            skewbContainer.getChildren().clear();
            skewbContainer.getChildren().add(skewbNode);
        }
    }

    public void setSkewbCase(Case skewbCase) {
        this.skewbCase = skewbCase;
    }

    public Case getSkewbCase() {
        return skewbCase;
    }
}