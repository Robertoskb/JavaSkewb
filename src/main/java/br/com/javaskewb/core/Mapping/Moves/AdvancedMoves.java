package br.com.javaskewb.core.Mapping.Moves;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Matrices.MatrixSwap;
import br.com.javaskewb.core.Cube.State;

import java.util.ArrayList;
import java.util.HashMap;

public class AdvancedMoves extends Moves{
    private static final HashMap<String, CentersFaces> notation = fill();
    private static final HashMap<String, Integer> costs = new HashMap<>();

    public AdvancedMoves(){
        super();
    }

    public AdvancedMoves(State state, boolean updateState) {
        super(state, updateState);
    }

    private static HashMap<String, CentersFaces> fill() {
        HashMap<String, CentersFaces> notation = new HashMap<>();
        notation.put("r", r());
        notation.put("r'", invertMove(notation.get("r")));

        notation.put("R", R());
        notation.put("R'", invertMove(notation.get("R")));

        notation.put("l", l());
        notation.put("l'", invertMove(notation.get("l")));

        notation.put("L", L());
        notation.put("L'", invertMove(notation.get("L")));

        notation.put("F", F());
        notation.put("F'", invertMove(notation.get("F")));

        notation.put("f", f());
        notation.put("f'", invertMove(notation.get("f")));

        notation.put("b", b());
        notation.put("b'", invertMove(notation.get("b")));

        notation.put("B", B());
        notation.put("B'", invertMove(notation.get("B")));

        notation.put("x", x());
        notation.put("x'", invertMove(notation.get("x")));

        notation.put("y", y());
        notation.put("y'", invertMove(notation.get("y")));

        notation.put("z", z());
        notation.put("z'", invertMove(notation.get("z")));

        notation.put("x2", x2());
        notation.put("y2", y2());
        notation.put("z2", z2());

        return notation;
    }

    @Override
    public int getCost(String move){
        return costs.getOrDefault(move, 1);
    }

    private static CentersFaces x(){
        MatrixSwap centers, faces;

        centers = new MatrixSwap(getCenterMatrix());
        faces = new MatrixSwap(getFacesMatrix());

        centers.swap(3, 1);
        centers.swap(1, 5);
        centers.swap(5, 4);
        centers.swap(4, 3);

        // red
        faces.swap(2, 12);
        faces.swap(12, 23);
        faces.swap(23, 21);
        faces.swap(21, 2);

        // orange
        faces.swap(6, 8);
        faces.swap(8, 15);
        faces.swap(15, 17);
        faces.swap(17, 6);

        // green
        faces.swap(5, 7);
        faces.swap(3, 10);
        faces.swap(18, 4);
        faces.swap(20, 1);

        // white
        faces.swap(7, 14);
        faces.swap(4, 9);
        faces.swap(1, 11);
        faces.swap(10, 24);

        // blue
        faces.swap(9, 13);
        faces.swap(11, 22);
        faces.swap(24, 19);
        faces.swap(14, 16);

        // yellow
        faces.swap(16, 5);
        faces.swap(19, 3);
        faces.swap(22, 20);
        faces.swap(13, 18);    


        return new CentersFaces(centers.getMatrix(), faces.getMatrix());
    }

    public static CentersFaces y(){
        MatrixSwap centers, faces;

        centers = new MatrixSwap(getCenterMatrix());
        faces = new MatrixSwap(getFacesMatrix());

        centers.swap(2, 3);
        centers.swap(3, 6);
        centers.swap(6, 5);
        centers.swap(5, 2);

        // white (1, 4, 7, 10)
        faces.swap(1, 4);
        faces.swap(4, 7);
        faces.swap(7, 10);
        faces.swap(10, 1);

        // yellow (13, 16, 19, 22)
        faces.swap(13, 22);
        faces.swap(22, 19);
        faces.swap(19, 16);
        faces.swap(16, 13);

        // top (2 3 5 6 8 9 11 12)
        faces.swap(2, 5);
        faces.swap(3, 6);
        faces.swap(6, 9);
        faces.swap(5, 8);
        faces.swap(8, 11);
        faces.swap(9, 12);
        faces.swap(11, 2);
        faces.swap(12, 3);

        // bottom (21, 20 18, 17, 15, 14, 24, 23)
        faces.swap(21, 18);
        faces.swap(20, 17);
        faces.swap(18, 15);
        faces.swap(17, 14);

        faces.swap(15, 24);
        faces.swap(14, 23);
        faces.swap(24, 21);
        faces.swap(23, 20);

        return new CentersFaces(centers.getMatrix(), faces.getMatrix());
    }

    private static CentersFaces z(){
        // x y x'

        ArrayList<CentersFaces> sequence = new ArrayList<>();
        sequence.add(x());
        sequence.add(y());
        sequence.add(invertMove(x()));

        return applySequence(sequence);
    }

    private static CentersFaces x2(){
        ArrayList<CentersFaces> sequence = new ArrayList<>();
        sequence.add(x());
        sequence.add(x());

        return applySequence(sequence);
    }

    private static CentersFaces y2(){
        ArrayList<CentersFaces> sequence = new ArrayList<>();
        sequence.add(y());
        sequence.add(y());

        return applySequence(sequence);
    }

    private static CentersFaces z2(){
        ArrayList<CentersFaces> sequence = new ArrayList<>();
        sequence.add(z());
        sequence.add(z());

        return applySequence(sequence);
    }

    private static CentersFaces applySequence(ArrayList<CentersFaces> sequence){
        int[][] centers, faces;

        centers = getCenterMatrix();
        faces = getFacesMatrix();

        for (CentersFaces centersFaces: sequence){
            centers = mulMatrices(centersFaces.getCentersMatrix(), centers);
            faces =  mulMatrices(centersFaces.getFacesMatrix(), faces);
        }

        return new CentersFaces(centers, faces);
    }

    private static CentersFaces R(){
        ArrayList<CentersFaces> sequence = new ArrayList<>();

        sequence.add(l());
        sequence.add(x());
        sequence.add(invertMove(z()));

        return applySequence(sequence);
    }

    private static CentersFaces r() {
        MatrixSwap centers, faces;

        centers = new MatrixSwap(getCenterMatrix());
        faces = new MatrixSwap(getFacesMatrix());

        centers.swap(2, 5);
        centers.swap(5, 4);
        centers.swap(4, 2);

        // corner 4
        faces.swap(10, 15);
        faces.swap(11, 13);
        faces.swap(12, 14);

        // corner 5
        faces.swap(13, 21);
        faces.swap(15, 20);
        faces.swap(14, 19);

        //corner 7
        faces.swap(19, 12);
        faces.swap(20, 10);
        faces.swap(21, 11);

        // corner 8
        faces.swap(22, 23);
        faces.swap(23, 24);
        faces.swap(24, 22);

        return new CentersFaces(centers.getMatrix(), faces.getMatrix());
    }

    private static CentersFaces L(){
        ArrayList<CentersFaces> sequence = new ArrayList<>();

        sequence.add(r());
        sequence.add(y());
        sequence.add(invertMove(x()));

        return applySequence(sequence);
    }

    private static CentersFaces l() {
        MatrixSwap centers, faces;

        centers = new MatrixSwap(getCenterMatrix());
        faces = new MatrixSwap(getFacesMatrix());

        centers.swap(3, 4);
        centers.swap(6, 3);
        centers.swap(4, 6);

        // corner 2
        faces.swap(4, 21);
        faces.swap(5, 19);
        faces.swap(6, 20);

        // corner 5
        faces.swap(13, 6);
        faces.swap(14, 4);
        faces.swap(15, 5);

        // corner 6
        faces.swap(16, 17);
        faces.swap(17, 18);
        faces.swap(18, 16);

        // corner 7
        faces.swap(19, 15);
        faces.swap(20, 13);
        faces.swap(21, 14);

        return new CentersFaces(centers.getMatrix(), faces.getMatrix());
    }


    private static CentersFaces b() {
        MatrixSwap centers, faces;

        centers = new MatrixSwap(getCenterMatrix());
        faces = new MatrixSwap(getFacesMatrix());

        centers.swap(4, 5);
        centers.swap(5, 6);
        centers.swap(6, 4);

        // corner 3
        faces.swap(7, 18);
        faces.swap(8, 16);
        faces.swap(9, 17);

        // corner 5
        faces.swap(13, 14);
        faces.swap(14, 15);
        faces.swap(15, 13);

        // corner 6
        faces.swap(16, 24);
        faces.swap(17, 22);
        faces.swap(18, 23);

        // corner 8
        faces.swap(22, 9);
        faces.swap(23, 7);
        faces.swap(24, 8);

        return new CentersFaces(centers.getMatrix(), faces.getMatrix());
    }

    private static CentersFaces B() {
        MatrixSwap centers, faces;

        centers = new MatrixSwap(getCenterMatrix());
        faces = new MatrixSwap(getFacesMatrix());

        centers.swap(1, 6);
        centers.swap(5, 1);
        centers.swap(6, 5);

        // corner 2
        faces.swap(4, 15);
        faces.swap(5, 13);
        faces.swap(6, 14);

        // corner 3
        faces.swap(7, 8);
        faces.swap(8, 9);
        faces.swap(9, 7);

        // corner 4
        faces.swap(10, 6);
        faces.swap(11, 4);
        faces.swap(12, 5);

        // corner 5
        faces.swap(13, 12);
        faces.swap(14, 10);
        faces.swap(15, 11);

        return new CentersFaces(centers.getMatrix(), faces.getMatrix());
    }

    private static CentersFaces F(){
        ArrayList<CentersFaces> sequence = new ArrayList<>();

        sequence.add(b());
        sequence.add(x());
        sequence.add(y());

        return applySequence(sequence);
    }

    private static CentersFaces f(){
        ArrayList<CentersFaces> sequence = new ArrayList<>();

        sequence.add(B());
        sequence.add(invertMove(y()));
        sequence.add(x());

        return applySequence(sequence);
    }

    @Override
    public HashMap<String, CentersFaces> getNotation() {
        return notation;
    }
}
