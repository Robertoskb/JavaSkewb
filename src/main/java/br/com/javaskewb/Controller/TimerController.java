package br.com.javaskewb.Controller;

import br.com.javaskewb.Controller.Components.SkewbBase;
import br.com.javaskewb.Controller.Components.parts.base.SkewbColor;
import br.com.javaskewb.core.Mapping.Moves.FLMoves;
import br.com.javaskewb.core.Solution.Solution;
import br.com.javaskewb.core.Solution.utils.Scramble;
import br.com.javaskewb.scrambles.ScrambleGenerator;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
    private Label scrambleLabel;

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


    private enum TimerState { STOPPED, HOLDING, READY, RUNNING }
    private TimerState currentState = TimerState.STOPPED;

    private static final long HOLD_THRESHOLD_MS = 300;
    private long spacePressedTime = 0;
    private long startTimeNano = 0;

    private AnimationTimer timer;
    private AnimationTimer holdCheckTimer;

    private final SkewbBase skewbBase = new SkewbBase();

    private final ScrambleGenerator scrambleGenerator = new ScrambleGenerator();

    private final Solution solution = new Solution(new FLMoves());

    private final ArrayList<Label> labels = new ArrayList<>();

    public TimerController() throws IOException {
    }

    @FXML
    public void initialize() throws IOException {
        Platform.runLater(() -> rootPane.requestFocus());

        labels.addAll(List.of(color0, color1, color2, color3, color4, color5));

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

        updateScramble();
    }

    private void updateScramble(){
        Scramble scramble = scrambleGenerator.FLNSScramble(moveSelector.getValue());

        scrambleLabel.setText(scramble.toString());

        skewbBase.reset();

        skewbBase.applyScramble(scramble);

        ArrayList<Integer> infos = solution.FLInfos(skewbBase.getSkewb().getState());
        for (int i = 0; i < 6; i++) {
            Label label = labels.get(i);
            SkewbColor skewbColor = SkewbColor.getColoById(i);
            String colorName = skewbColor.toString();

            label.setText(colorName + ": " + infos.get(i));
            label.setTextFill(Color.web(skewbColor.getHex()));
        }
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
}
