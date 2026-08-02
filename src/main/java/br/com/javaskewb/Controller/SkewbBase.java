package br.com.javaskewb.Controller;

import br.com.javaskewb.Controller.parts.CenterPart;
import br.com.javaskewb.Controller.parts.CenterSlot;
import br.com.javaskewb.Controller.parts.FacePart;
import br.com.javaskewb.Controller.parts.FaceSlot;
import br.com.javaskewb.Cube.Skewb;
import br.com.javaskewb.Mapping.Parts.Center;
import br.com.javaskewb.Mapping.Parts.Corner;
import br.com.javaskewb.Mapping.State;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkewbBase extends StackPane{

    @FXML private Polygon center1;
    @FXML private Polygon center2;
    @FXML private Polygon center3;
    @FXML private Polygon center4;
    @FXML private Polygon center5;
    @FXML private Polygon center6;

    @FXML private Polygon face1;
    @FXML private Polygon face2;
    @FXML private Polygon face3;
    @FXML private Polygon face4;
    @FXML private Polygon face5;
    @FXML private Polygon face6;
    @FXML private Polygon face7;
    @FXML private Polygon face8;
    @FXML private Polygon face9;
    @FXML private Polygon face10;
    @FXML private Polygon face11;
    @FXML private Polygon face12;
    @FXML private Polygon face13;
    @FXML private Polygon face14;
    @FXML private Polygon face15;
    @FXML private Polygon face16;
    @FXML private Polygon face17;
    @FXML private Polygon face18;
    @FXML private Polygon face19;
    @FXML private Polygon face20;
    @FXML private Polygon face21;
    @FXML private Polygon face22;
    @FXML private Polygon face23;
    @FXML private Polygon face24;

    @FXML private StackPane root;

    @FXML private Group skewbGroup;

    private ArrayList<Polygon> centers;
    private ArrayList<Polygon> faces;

    private final ArrayList<CenterSlot> centerSlots = new ArrayList<>();
    private final ArrayList<FaceSlot> faceSlots = new ArrayList<>();

    private final ArrayList<Corner> corners = new ArrayList<>();

    private static final double BASE_WIDTH = 805;
    private static final double BASE_HEIGHT = 606;

    private final Skewb skewb;

    private boolean invisiblePolygons = false;

    public SkewbBase() throws IOException {
        skewb = new Skewb();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/javaskewb/view/SkewbBase.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        loader.load();
    }

    public SkewbBase(State state) throws IOException {
        skewb = new Skewb(state);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/javaskewb/view/SkewbBase.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        loader.load();
    }

    public void initialize(){
        centers = new ArrayList<>(List.of(center1, center2, center3, center4, center5, center6));
        faces = new ArrayList<>(List.of(
                face1, face2, face3,
                face4, face5, face6,
                face7, face8, face9,
                face10, face11, face12,
                face13, face14, face15,
                face16, face17, face18,
                face19, face20, face21,
                face22, face23, face24
        ));

        update();

        this.setMinSize(0, 0);

        double aspectRatio = BASE_HEIGHT / BASE_WIDTH;

        this.prefHeightProperty().bind(this.widthProperty().multiply(aspectRatio));

        root.widthProperty().addListener((obs, oldV, newV) -> resize());
        root.heightProperty().addListener((obs, oldV, newV) -> resize());

        skewbGroup.setAutoSizeChildren(false);
        resize();
    }

    public void update(){
        for (int i = 0; i < centers.size(); i++) {
            Center center = new Center(i, skewb.getState().getCenters().get(i).getValue());
            CenterPart centerPart = new CenterPart(center);
            centerSlots.add(new CenterSlot(i, centerPart, centers.get(i)));

            Polygon polygon = centerSlots.get(i).getPolygon();

            final int finalI = i;
            // polygon.setOnMouseClicked(s -> setCenterColor(finalI));

        }

        for (int i = 0; i < faces.size(); i++) {
            FacePart facePart = new FacePart(skewb.getState().getFaces().get(i));
            faceSlots.add(new FaceSlot(i, facePart, faces.get(i)));

            Polygon polygon = faceSlots.get(i).getPolygon();

            final int finalI = i;
            //polygon.setOnMouseClicked(s -> setFaceColor(finalI));
        }
    }

    public void changeVisibility(){
        int[] centers = {3, 4, 5};
        int[] faces = {5, 7, 14, 16, 13, 23, 8, 10, 12, 15, 18, 21};

        invisiblePolygons = !invisiblePolygons;

        double opacity = invisiblePolygons ? 0.0 : 1.0;
        for (int center: centers)
            centerSlots.get(center).getPolygon().setOpacity(opacity);

        for (int face: faces)
            faceSlots.get(face).getPolygon().setOpacity(opacity);
    }

    public void applyScramble(String scramble){
        skewb.applyScramble(scramble);
        update();
    }

    public void applyScramble(ArrayList<String> scramble){
        skewb.applyScramble(scramble);
        update();
    }

    public void applyScrambleAnimation(String scramble, Runnable runnable){
        int cont = 0;
        int time = 1000;

        String[] scrambleList = scramble.split(" ");

        PauseTransition superPause = new PauseTransition(Duration.millis(time * scrambleList.length + 100));
        superPause.setOnFinished(e -> runnable.run());
        superPause.play();

        for (String move: scrambleList){
            PauseTransition pause = new PauseTransition(Duration.millis(time + cont));
            pause.setOnFinished(e -> {
                applyScramble(move);
            });
            pause.play();
            cont += time;
        }
    }

    public void setCenterColor(int slot){
        centerSlots.get(slot).getPart().alterColorDefault();
    }

    public void setFaceColor(int slot){
        faceSlots.get(slot).getPart().alterColorDefault();
    }

    private void resize() {
        if (root.getWidth() == 0 || root.getHeight() == 0) {
            return;
        }

        double scale = Math.min(
                root.getWidth() / BASE_WIDTH,
                root.getHeight() / BASE_HEIGHT
        );

        skewbGroup.setScaleX(scale);
        skewbGroup.setScaleY(scale);
    }

    public StackPane getRoot() {
        return root;
    }

    public void setRoot(StackPane root) {
        this.root = root;
    }

    public Group getSkewbGroup() {
        return skewbGroup;
    }

    public void setSkewbGroup(Group skewbGroup) {
        this.skewbGroup = skewbGroup;
    }

    public ArrayList<Polygon> getCenters() {
        return centers;
    }

    public void setCenters(ArrayList<Polygon> centers) {
        this.centers = centers;
    }

    public ArrayList<Polygon> getFaces() {
        return faces;
    }

    public void setFaces(ArrayList<Polygon> faces) {
        this.faces = faces;
    }

    public ArrayList<CenterSlot> getCenterSlots() {
        return centerSlots;
    }

    public ArrayList<FaceSlot> getFaceSlots() {
        return faceSlots;
    }

    public ArrayList<Corner> getCorners() {
        return corners;
    }

    public Skewb getSkewb() {
        return skewb;
    }
}
