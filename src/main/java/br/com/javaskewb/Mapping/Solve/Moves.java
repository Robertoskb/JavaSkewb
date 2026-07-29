package br.com.javaskewb.Mapping.Solve;

import br.com.javaskewb.Mapping.Solve.Matrices.CentersFaces;
import br.com.javaskewb.Mapping.State;
import br.com.javaskewb.Mapping.Parts.Center;
import br.com.javaskewb.Mapping.Parts.Corner;

import java.util.ArrayList;
import java.util.HashMap;

abstract public class Moves {
    protected State state;
    protected boolean updateState;
    HashMap<String, CentersFaces> moves = new HashMap<>();

    public Moves(State state, boolean updateState){
        setState(state);
        setUpdateState(updateState);
        fill();
    }

    protected abstract void fill();

    private ArrayList<CentersFaces> getAllMatrices(ArrayList<String> scramble){
        ArrayList<CentersFaces> allMatrices = new ArrayList<>();
        for (String s: scramble)
            allMatrices.add(moves.get(s));

        return allMatrices;
    }
    public CentersFaces getMove(String move){
        return moves.get(move);
    }

    public int[][] mulMatrices(int[][] matrix1, int[][] matrix2){
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

    public int[][] invertMatrix(int[][] matrix){
        int[][] new_matrix = new int[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                new_matrix[j][i] = matrix[i][j];
            }
        }

        return new_matrix;
    }

    public CentersFaces invertMove(CentersFaces move){
        int[][] centers, faces;

        centers = move.getCentersMatrix();
        faces = move.getFacesMatrix();

        return new CentersFaces(invertMatrix(centers), invertMatrix(faces));
    }

    public State applyScramble(ArrayList<String> scramble){
        ArrayList<CentersFaces> allMatrices = getAllMatrices(scramble);
        State newState = state;

        if (!allMatrices.isEmpty()){
            int[][] centers, faces;

            centers = allMatrices.getFirst().getCentersMatrix();
            faces = allMatrices.getFirst().getFacesMatrix();

            for (int i = 1; i < allMatrices.size(); i++) {
                CentersFaces allMatrix = allMatrices.get(i);

                centers = mulMatrices(centers, allMatrix.getCentersMatrix());
                faces = mulMatrices(faces, allMatrix.getFacesMatrix());
            }

            newState = move(new CentersFaces(centers, faces));

            if (updateState)
                state = newState;
        }

        return newState;
    }

    public State applyMove(String move){
        CentersFaces matrices = getMove(move);
        State newState;

        newState = move(matrices);

        if (updateState)
            state = newState;


        return newState;
    }


    public ArrayList<Integer> moveCenters(int[][] matrix, ArrayList<Integer> centers){
        ArrayList<Integer> arrayCenters = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < 1; j++) {
                int sum = 0;

                for (int k = 0; k < matrix[i].length; k++) {
                    sum += matrix[i][k] * centers.get(k);
                }

                arrayCenters.add(sum);
            }
        }

        return arrayCenters;
    }

    public ArrayList<Integer> moveFaces(int[][] matrix, ArrayList<Integer> faces){
        ArrayList<Integer> arrayFaces = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < 1; j++) {
                int sum = 0;

                for (int k = 0; k < matrix[i].length; k++) {
                    sum += matrix[i][k] * faces.get(k);
                }

                arrayFaces.add(sum);
            }
        }

        return arrayFaces;
    }

    public State move(CentersFaces centersFaces){
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

        return new State(arrayCenters, arrayCorners);
    }

    protected static int[][] getCenterMatrix(){
        int[][] matrix = new int[6][6];

        for (int i = 0; i < 6; i++) {
            matrix[i][i] = 1;
        }

        return matrix;
    }

    protected static int[][] getFacesMatrix(){
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
}
