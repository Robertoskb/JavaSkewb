package br.com.javaskewb.core.Cube;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Mapping.Parts.Center;
import br.com.javaskewb.core.Mapping.Parts.Corner;
import br.com.javaskewb.core.Mapping.Moves.AdvancedMoves;

import java.util.*;

public class State {
    private static final Random random = new Random();
    private static final List<CentersFaces> perspectives = perspectivesCenterFaces();

    private List<Center> centers;
    private List<Corner> corners;
    int perspective;

    public State(List<Center> centers, List<Corner> corners){
        setCenters(centers);
        setCorners(corners);
    }

    public static State getSolvedState(){
        ArrayList<Center> new_centers = new ArrayList<>(6);
        ArrayList<Corner> new_corners = new ArrayList<>(8);

        for (int i=0; i<6; i++)
            new_centers.add(new Center(i));

        int[][] faces = {
                {0, 1, 2}, {0, 2, 5}, {0, 5, 4}, {0, 4, 1},
                {3, 4, 5}, {3, 5, 2}, {3, 2, 1}, {3, 1, 4},
        };
        for (int i = 0; i < 8; i++) {
            new_corners.add(new Corner(i, faces[i]));
        }

        return new State(new_centers, new_corners);
    }

    public static List<State> generatePerspectivesStates(State baseState){
        List<State> states = new ArrayList<>(24);

        int cont = 0;
        for (CentersFaces centersFaces: perspectives) {
            State newState = Moves.move(baseState, centersFaces, false);
            newState.setPerspective(cont++);
            states.add(newState);
        }

        return states;
    }

    private static List<CentersFaces> perspectivesCenterFaces(){
        List<CentersFaces> centersFacesList = new Stack<>();

        String[] moves = "x y z x' y' z'".split(" ");

        Stack<CentersFaces> queue = new Stack<>();
        queue.add(new CentersFaces(Moves.getCenterMatrix(), Moves.getFacesMatrix()));

        AdvancedMoves advancedMoves = new AdvancedMoves();

        while (!queue.isEmpty()){
            CentersFaces centersFaces = queue.removeLast();
            for (String move: moves){
                CentersFaces newCentersFaces = Moves.mulCenterFaces(advancedMoves.getMove(move), centersFaces);

                if (!centersFacesList.contains(newCentersFaces)){
                    centersFacesList.add(newCentersFaces);
                    queue.add(newCentersFaces);
                }

            }
        }

        return centersFacesList;
    }

    public static State getRandomPerspective(){
        return generatePerspectivesStates(getSolvedState()).get(random.nextInt(0, 24));
    }

    public static List<State> getSolvedPerspectives(){
        return generatePerspectivesStates(State.getSolvedState());
    }

    public static State getPerspective(int perspective){
        return generatePerspectivesStates(getSolvedState()).get(perspective);
    }

    public State cloneState(){
        ArrayList<Center> new_centers = new ArrayList<>();
        ArrayList<Corner> new_corners = new ArrayList<>();

        for (int i=0; i<6; i++)
            new_centers.add(new Center(centers.get(i).getId(), centers.get(i).getValue()));

        for (int i=0; i<8; i++)
            new_corners.add(new Corner(corners.get(i).getId(), corners.get(i).getFaces()));


        return new State(new_centers, new_corners);
    }

    public void maskLayer(int layer){
        if (layer < 0 || layer > 5)
            return;

        for (Center center: centers)
            if (center.getValue() != layer)
                center.setValue(-1);

        for (Corner corner: corners){
            boolean find = false;
            for (int value: corner.getFaces())
                if (value == layer){
                    find = true;
                    break;
                }
            if (!find)
                corner.setFaces(new int[] {-1, -1, -1});

        }
    }


    public void maskFace(int side){
        if (side < 0 || side > 5)
            return;

        maskLayer(side);

        for (Corner corner: corners){
            for (int i = 0; i < 3; i++) {
                int value = corner.getFaces().get(i);
                if (value != side) {
                    corner.getFaces().set(i, -1);
                }
            }
        }

    }

    public LinkedHashMap<State, State> getMaskLayers(){
        LinkedHashMap<State, State> states = new LinkedHashMap<>();

        State solved = State.getSolvedState();
        for (int i = 0; i < 6; i++) {
            State state = this.cloneState();
            State newSolved = solved.cloneState();

            state.maskLayer(i);
            newSolved.maskLayer(i);

            states.put(state, newSolved);
        }

        return states;
    }

    @Override
    public boolean equals(Object obj){
        if (!obj.getClass().isAssignableFrom(State.class))
            return false;
        State other = (State) obj;
        return centers.equals(other.getCenters()) && corners.equals(other.getCorners());
    }

    public List<Integer> getFaces(){
        List<Integer> faces = new ArrayList<>();
        for (Corner corner: corners)
            faces.addAll(corner.getFaces());

        return faces;
    }

    public List<Integer> getIntCenters(){
        List<Integer> centers = new ArrayList<>();

        for (Center center: this.centers)
            centers.add(center.getValue());

        return centers;
    }

    public ArrayList<Integer> getIdCorners(){
        ArrayList<Integer> ids = new ArrayList<>();

        for (Corner corner: corners)
            ids.add(corner.getId());

        return ids;
    }

    public int getPerspective() {
        return perspective;
    }

    public void setPerspective(int perspective) {
        this.perspective = perspective;
    }

    public List<Center> getCenters() {
        return centers;
    }

    public void setCenters(List<Center> centers) {
        this.centers = centers;
    }

    public List<Corner> getCorners() {
        return corners;
    }

    public void setCorners(List<Corner> corners) {
        this.corners = corners;
    }

    @Override
    public String toString(){
        return "Centros: " + centers + "\n" + "Cantos: " + corners;
    }

    @Override
    public int hashCode(){
        return Objects.hash(centers, corners);
    }
}
