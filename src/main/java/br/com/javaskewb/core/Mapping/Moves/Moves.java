package br.com.javaskewb.core.Mapping.Moves;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Mapping.Parts.Center;
import br.com.javaskewb.core.Mapping.Parts.Corner;

import java.util.ArrayList;
import java.util.HashMap;

abstract public class Moves {
    protected State state;
    protected boolean updateState;

    public Moves(){
        if (getNotation().isEmpty())
            fill();
    }

    public Moves(State state, boolean updateState){
        setState(state);
        setUpdateState(updateState);

        if (getNotation().isEmpty())
            fill();
    }

    protected abstract void fill();

    private ArrayList<CentersFaces> getAllMatrices(ArrayList<String> scramble){
        ArrayList<CentersFaces> allMatrices = new ArrayList<>();
        for (String s: scramble)
            allMatrices.add(getNotation().get(s));

        return allMatrices;
    }
    public CentersFaces getMove(String move){
        return getNotation().get(move);
    }

    public static int[][] mulMatrices(int[][] matrix1, int[][] matrix2){
        int[][] matrix = new int[matrix1.length][matrix2[0].length];

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {
                    matrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return matrix;
    }

    public static CentersFaces mulCenterFaces(CentersFaces centersFaces1, CentersFaces centersFaces2){
        int[][] centers, faces;

        centers = mulMatrices(centersFaces1.getCentersMatrix(), centersFaces2.getCentersMatrix());
        faces = mulMatrices(centersFaces1.getFacesMatrix(), centersFaces2.getFacesMatrix());

        return new CentersFaces(centers, faces);
    }

    public static int[][] invertMatrix(int[][] matrix){
        int[][] new_matrix = new int[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                new_matrix[j][i] = matrix[i][j];
            }
        }

        return new_matrix;
    }

    public static CentersFaces invertMove(CentersFaces move){
        int[][] centers, faces;

        centers = move.getCentersMatrix();
        faces = move.getFacesMatrix();

        return new CentersFaces(invertMatrix(centers), invertMatrix(faces));
    }

    public State applyMove(String move){
        CentersFaces matrices = getMove(move);
        State newState;

        newState = move(matrices);

        return newState;
    }

    public static ArrayList<Integer> moveCenters(int[][] matrix, ArrayList<Integer> centers){
        ArrayList<Integer> arrayCenters = new ArrayList<>();

        for (int[] ints : matrix) {
            int sum = 0;

            for (int k = 0; k < ints.length; k++) {
                sum += ints[k] * centers.get(k);
            }

            arrayCenters.add(sum);
        }

        return arrayCenters;
    }

    public static ArrayList<Integer> moveFaces(int[][] matrix, ArrayList<Integer> faces){
        ArrayList<Integer> arrayFaces = new ArrayList<>();

        for (int[] ints : matrix) {
            int sum = 0;

            for (int k = 0; k < ints.length; k++) {
                sum += ints[k] * faces.get(k);
            }

            arrayFaces.add(sum);

        }

        return arrayFaces;
    }

    public State move(CentersFaces centersFaces){
        return move(state, centersFaces, updateState);
    }

    public static State move(State state, CentersFaces centersFaces, boolean updateState){
        ArrayList<Integer> centers, faces;
        int[][] centersMatrix, facesMatrix;

        centersMatrix = centersFaces.getCentersMatrix();
        facesMatrix = centersFaces.getFacesMatrix();

        centers = moveCenters(centersMatrix, state.getIntCenters());
        faces = moveFaces(facesMatrix, state.getFaces());

        ArrayList<Center> arrayCenters = new ArrayList<>();
        ArrayList<Corner> arrayCorners = new ArrayList<>();

        int cont;

        cont = 0;
        for (int c: centers)
            arrayCenters.add(new Center(cont++, c));

        cont = 0;
        for (int i=0; i<24; i+=3)
            arrayCorners.add(new Corner(cont++, new int[]{faces.get(i), faces.get(i + 1), faces.get(i + 2)}));

        if (updateState){
            state.setCenters(arrayCenters);
            state.setCorners(arrayCorners);

            return state;
        }

        return new State(arrayCenters, arrayCorners);
    }

    public static State move(State state, CentersFaces centersFaces){
        return move(state, centersFaces, false);
    }

    public int getCost(String move){
        return 1;
    }

    public static int[][] getCenterMatrix(){
        int[][] matrix = new int[6][6];

        for (int i = 0; i < 6; i++) {
            matrix[i][i] = 1;
        }

        return matrix;
    }

    public static int[][] getFacesMatrix(){
        int[][] matrix = new int[24][24];

        for (int i = 0; i < 24; i++) {
            matrix[i][i] = 1;
        }

        return matrix;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isUpdateState() {
        return updateState;
    }

    public void setUpdateState(boolean updateState) {
        this.updateState = updateState;
    }

    public abstract HashMap<String, CentersFaces> getNotation();
}
