package br.com.javaskewb.core.Mapping.Moves;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Matrices.MatrixSwap;
import br.com.javaskewb.core.Cube.State;

import java.util.HashMap;

public class WCAMoves extends Moves {
    private static final HashMap<String, CentersFaces> notation = new HashMap<>();

    public WCAMoves() {
        super();
    }
    public WCAMoves(State state, boolean updateState) {
        super(state, updateState);
    }

    @Override
    protected void fill() {
        notation.put("R", R());
        notation.put("R'", invertMove(notation.get("R")));

        notation.put("B", B());
        notation.put("B'", invertMove(notation.get("B")));

        notation.put("U", U());
        notation.put("U'", invertMove(notation.get("U")));

        notation.put("L", L());
        notation.put("L'", invertMove(notation.get("L")));
    }

    private CentersFaces R() {
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

    private CentersFaces L() {
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


    private CentersFaces B() {
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

    private CentersFaces U() {
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

    @Override
    public HashMap<String, CentersFaces> getNotation() {
        return notation;
    }
}