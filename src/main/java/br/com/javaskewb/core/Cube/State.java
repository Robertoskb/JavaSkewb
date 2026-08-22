package br.com.javaskewb.core.Cube;

import br.com.javaskewb.core.Mapping.Parts.Center;
import br.com.javaskewb.core.Mapping.Parts.Corner;
import br.com.javaskewb.core.Mapping.Moves.AdvancedMoves;

import java.util.*;

public class State {
    private static final Random random = new Random();
    private ArrayList<Center> centers;
    private ArrayList<Corner> corners;
    int perspective;

    private int[][] sides = {{0, 1, 2, 3}, {0, 3, 6, 7}, {0, 1, 5, 6}, {4, 5, 6, 7}, {4, 3, 2, 7}, {4, 1, 2, 5}};
    private int[][] faces = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {9, 10, 11},
            {12, 13, 14}, {15, 16, 17}, {18, 19, 20}, {21, 23, 23}
    };

    public State(ArrayList<Center> centers, ArrayList<Corner> corners){
        setCenters(centers);
        setCorners(corners);
    }

    public static State getSolvedState(){
        ArrayList<Center> new_centers = new ArrayList<>(6);
        ArrayList<Corner> new_corners = new ArrayList<>(8);

        for (int i=0; i<6; i++)
            new_centers.add(new Center(i));

        int cont = 0;
        for (int i=0; i<24; i+=3)
            new_corners.add(new Corner(cont++, new int[] {i, i+1, i+2}));

        return new State(new_centers, new_corners);
    }

    public static ArrayList<State> generatePerspectivesStates(State baseState){
        ArrayList<State> states = new ArrayList<>(24);

        String[] moves = "x y z x' y' z'".split(" ");

        ArrayList<State> queue = new ArrayList<>();
        queue.add(baseState);

        AdvancedMoves advancedMoves = new AdvancedMoves();

        int cont = 0;
        while (!queue.isEmpty()){
            advancedMoves.setState(queue.removeLast());
            for (String move: moves){
                State state = advancedMoves.applyMove(move);

                if (!states.contains(state)){
                    state.setPerspective(cont++);
                    queue.add(state);
                    states.add(state);
                }

            }
        }

        return states;
    }

    public static State getRandomPerspective(){
        return generatePerspectivesStates(getSolvedState()).get(random.nextInt(0, 24));
    }

    public static ArrayList<State> getSolvedPerspectives(){
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
            for (int id: sides[layer]){
                for (int face: faces[id]){
                    if (corner.getFaces().contains(face)){
                        find = true;
                        break;
                    }
                }
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
            for (int id: sides[side]){
                for (int face: faces[id]){
                    int index = corner.getFaces().indexOf(face);
                    if (index != -1){
                        corner.setFace(index, faces[sides[side][0]][side%3]);
                        corner.setFace(index+1, -1);
                        corner.setFace(index+2, -1);
                    }
                }
            }
        }

    }

    public LinkedHashMap<State, State> getMaskSides(){
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

    public ArrayList<Integer> getFaces(){
        ArrayList<Integer> faces = new ArrayList<>();
        for (Corner corner: corners)
            faces.addAll(corner.getFaces());

        return faces;
    }

    public ArrayList<Integer> getIntCenters(){
        ArrayList<Integer> centers = new ArrayList<>();

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

    public ArrayList<Center> getCenters() {
        return centers;
    }

    public void setCenters(ArrayList<Center> centers) {
        this.centers = centers;
    }

    public ArrayList<Corner> getCorners() {
        return corners;
    }

    public void setCorners(ArrayList<Corner> corners) {
        this.corners = corners;
    }

    public int[][] getSides() {
        return sides;
    }

    public void setSides(int[][] sides) {
        this.sides = sides;
    }

    public void setFaces(int[][] faces) {
        this.faces = faces;
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
