package br.com.javaskewb.Controller.Components;

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

    public CaseCard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/javaskewb/view/components/CaseCard.fxml"));
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
}