package br.com.javaskewb.Controller;

import br.com.javaskewb.Controller.Components.SkewbBase;
import br.com.javaskewb.Controller.Components.parts.base.SkewbColor;
import br.com.javaskewb.core.Cube.Skewb;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.Methods.EG2.EG2Case;
import br.com.javaskewb.core.Patterns.Methods.FS.FL.FLCase;
import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Solution.Solution;
import br.com.javaskewb.core.Solution.utils.Scramble;
import br.com.javaskewb.scrambles.FLEG2Config;
import br.com.javaskewb.scrambles.FLNSConfig;
import br.com.javaskewb.scrambles.ScrambleGenerator;
import br.com.javaskewb.scrambles.StateConfig;
import br.com.javaskewb.scrambles.base.FLMethodConfig;
import br.com.javaskewb.ui.ScreenManager;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TimerController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private Spinner<Integer> moveSelector;

    @FXML
    private StackPane cubeContainer;

    @FXML
    private StackPane cubeContainer1;

    @FXML
    private Label scrambleLabel;

    @FXML
    private ComboBox<FLMethodConfig> cbMethod;

    @FXML
    private Label timerLabel;

    @FXML
    private Label color0;

    @FXML
    private Label color1;

    @FXML
    private Label color2;

    @FXML
    private Label color3;

    @FXML
    private Label color4;

    @FXML
    private Label color5;

    @FXML
    private Label color6;

    @FXML
    private Label color7;

    @FXML
    private Label color8;

    @FXML
    private Label color9;

    @FXML
    private Label color10;

    @FXML
    private Label color11;

    @FXML
    private CheckBox pin;


    private enum TimerState { STOPPED, HOLDING, READY, RUNNING }
    private TimerState currentState = TimerState.STOPPED;

    private static final long HOLD_THRESHOLD_MS = 300;
    private long spacePressedTime = 0;
    private long startTimeNano = 0;

    private AnimationTimer timer;
    private AnimationTimer holdCheckTimer;

    private final SkewbBase skewbBase = new SkewbBase();
    private final SkewbBase skewbFL = new SkewbBase();

    private Case currentFlcase;

    private final ScrambleGenerator scrambleGenerator = new ScrambleGenerator();
    private final ScreenManager screenManager = ScreenManager.getInstance();

    private final Solution solution = new Solution();

    private final List<Label> labelsNS = new ArrayList<>();
    private final List<Label> labelsEG2 = new ArrayList<>();

    public TimerController() throws IOException {
    }

    @FXML
    public void initialize() throws IOException {
        Platform.runLater(() -> rootPane.requestFocus());

        moveSelector.setEditable(false);
        pin.setOnMouseClicked(e -> rootPane.requestFocus());
        moveSelector.setOnMouseClicked(e -> rootPane.requestFocus());

        cbMethod.getItems().add(FLNSConfig.getInstance());
        cbMethod.getItems().add(FLEG2Config.getInstance());
        cbMethod.setValue(cbMethod.getItems().getFirst());

        cbMethod.setOnAction(e -> {
            rootPane.requestFocus();

            scrambleGenerator.setFlMethodConfig(cbMethod.getValue());
            updateScramble();
        });


        labelsNS.addAll(List.of(color0, color1, color2, color3, color4, color5));
        labelsEG2.addAll(List.of(color6, color7, color8, color9, color10, color11));

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long elapsedMillis = (System.nanoTime() - startTimeNano) / 1_000_000;
                timerLabel.setText(formatTime(elapsedMillis));
            }
        };

        holdCheckTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (currentState == TimerState.HOLDING) {
                    long holdDuration = System.currentTimeMillis() - spacePressedTime;
                    if (holdDuration >= HOLD_THRESHOLD_MS) {
                        currentState = TimerState.READY;
                        timerLabel.setTextFill(Color.web("#00FF66"));
                    }
                }
            }
        };

        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 7);
        moveSelector.setValueFactory(spinnerValueFactory);


        cubeContainer.getChildren().add(skewbBase);
        cubeContainer1.getChildren().add(skewbFL);

        updateScramble();
    }

    private void updateScramble(){
        StateConfig stateConfig;
        if (pin.isSelected())
            stateConfig = scrambleGenerator.randomConfig(currentFlcase);
        else
            stateConfig = scrambleGenerator.randomConfig(moveSelector.getValue());

        Scramble scramble = stateConfig.getScramble();
        State state = stateConfig.getState();
        int side = state.getCenters().get(3).getValue();

        Case flCase = stateConfig.getConfig().getFlCase();

        currentFlcase = flCase;

        int perspective = stateConfig.getConfig().getPerspective();

        scrambleLabel.setText(scramble.toString());

        skewbBase.reset();

        skewbBase.applyScramble(scramble);

        Skewb skewb = skewbFL.getSkewb();
        skewb.setState(State.getPerspective(perspective));
        skewb.getState().maskLayer(side);

        if (cbMethod.getValue() instanceof FLEG2Config)
            Moves.move(skewb.getState(), EG2Case.getEG2CenterFaces1(), true);

        flCase.applyCase(skewb.getState());
        skewbFL.update();

        List<Integer> infos = solution.FLInfos(skewbBase.getSkewb().getState());
        List<Integer> EG2Infos = solution.FLEG2Infos(skewbBase.getSkewb().getState());
        for (int i = 0; i < 6; i++) {
            Label label = labelsNS.get(i);
            Label EG2Label = labelsEG2.get(i);

            SkewbColor skewbColor = SkewbColor.getColoById(i);
            String colorName = skewbColor.toString();

            label.setText(colorName + ": " + infos.get(i));
            label.setStyle("-fx-font-weight: normal;");
            label.setTextFill(Color.web(skewbColor.getHex()));

            EG2Label.setText(colorName + ": " + EG2Infos.get(i));
            EG2Label.setStyle("-fx-font-weight: normal;");
            EG2Label.setTextFill(Color.web(skewbColor.getHex()));
        }

        labelsNS.get(side).setStyle("-fx-font-weight: bold;");
        labelsEG2.get(side).setStyle("-fx-font-weight: bold;");
    }

    @FXML
    public void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            if (currentState == TimerState.STOPPED) {
                currentState = TimerState.HOLDING;
                spacePressedTime = System.currentTimeMillis();
                timerLabel.setText("0.00");
                timerLabel.setTextFill(Color.web("#FF4444"));
                holdCheckTimer.start();
            } else if (currentState == TimerState.RUNNING) {
                stopTimer();
            }
        } else if (currentState == TimerState.RUNNING) {
            stopTimer();
        } else if (event.getCode() == KeyCode.UP) {
            moveSelector.increment();
        } else if (event.getCode() == KeyCode.DOWN) {
            moveSelector.decrement();
        } else if (event.getCode() == KeyCode.P) {
            pin.setSelected(!pin.isSelected());
    }
    }

    @FXML
    public void handleKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            if (currentState == TimerState.READY) {
                startTimer();
            } else if (currentState == TimerState.HOLDING) {
                currentState = TimerState.STOPPED;
                holdCheckTimer.stop();
                timerLabel.setTextFill(Color.WHITE);
            }
        } else if (event.getCode() == KeyCode.ENTER) {
            updateScramble();
        }
    }

    private void startTimer() {
        holdCheckTimer.stop();
        currentState = TimerState.RUNNING;
        timerLabel.setTextFill(Color.WHITE);
        startTimeNano = System.nanoTime();
        timer.start();
    }

    private void stopTimer() {
        timer.stop();
        currentState = TimerState.STOPPED;
        timerLabel.setTextFill(Color.WHITE);
    }

    private String formatTime(long millis) {
        long seconds = millis / 1000;
        long hundredths = (millis % 1000) / 10;

        if (seconds >= 60) {
            long minutes = seconds / 60;
            seconds = seconds % 60;
            return String.format("%d:%02d.%02d", minutes, seconds, hundredths);
        }
        return String.format("%d.%02d", seconds, hundredths);
    }

    @FXML
    public void toMenu() throws IOException {
        screenManager.setScene("menu.fxml");
    }
}
