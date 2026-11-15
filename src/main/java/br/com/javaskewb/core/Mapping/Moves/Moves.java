package br.com.javaskewb.core.Mapping.Moves;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Mapping.Parts.Center;
import br.com.javaskewb.core.Mapping.Parts.Corner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

abstract public class Moves {
    protected State state;
    protected boolean updateState;

    public Moves(){}

    public Moves(State state, boolean updateState){
        setState(state);
        setUpdateState(updateState);
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

    public CentersFaces invertMove(String move){
        return invertMove(getNotation().get(move));
    }

    public State applyMove(String move){
        CentersFaces matrices = getMove(move);
        State newState;

        newState = move(matrices);

        return newState;
    }

    private static int findSource(int[] row){
        for (int i = 0; i < row.length; i++) {
            if (row[i] != 0)
                return i;
        }

        throw new IllegalArgumentException("A linha da matriz não contém uma origem");
    }

    public State move(CentersFaces centersFaces){
        return move(state, centersFaces, updateState);
    }

    public static State move(State state, CentersFaces centersFaces, boolean updateState){
        int[][] centersMatrix = centersFaces.getCentersMatrix();
        int[][] facesMatrix = centersFaces.getFacesMatrix();

        List<Center> oldCenters = state.getCenters();
        List<Corner> oldCorners = state.getCorners();

        List<Center> arrayCenters = new ArrayList<>(centersMatrix.length);
        for (int i = 0; i < centersMatrix.length; i++) {
            int source = findSource(centersMatrix[i]);
            arrayCenters.add(new Center(i, oldCenters.get(source).getValue()));
        }

        int[] oldFaces = new int[facesMatrix[0].length];
        for (int i = 0; i < oldCorners.size(); i++) {
            List<Integer> cornerFaces = oldCorners.get(i).getFaces();
            for (int j = 0; j < 3; j++)
                oldFaces[i * 3 + j] = cornerFaces.get(j);
        }

        List<Corner> arrayCorners = new ArrayList<>(facesMatrix.length / 3);
        for (int i = 0; i < facesMatrix.length; i += 3) {
            int[] cornerFaces = new int[3];
            for (int j = 0; j < 3; j++) {
                int destination = i + j;
                int source = findSource(facesMatrix[destination]);
                cornerFaces[j] = oldFaces[source];
            }
            arrayCorners.add(new Corner(i / 3, cornerFaces));
        }

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
