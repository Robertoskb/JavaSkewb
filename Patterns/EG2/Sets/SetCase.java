package br.com.javaskewb.core.Patterns.EG2.Sets;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Matrices.MatrixSwap;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.EG2.EG2Case;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.base.CLCase;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.PeanutCase;
import br.com.javaskewb.core.Patterns.NS.L2L.L2LCase;

public class SetCase extends EG2Case {
    private final L2LCase l2lCase;

    public SetCase(L2LCase l2l) {
        super(l2l.getName(), Moves.mulCenterFaces(getEG2CenterFaces(), flip(l2l)));
        this.l2lCase = l2l;
    }

    private static CentersFaces flip(L2LCase l2LCase){
        CentersFaces centersFaces = l2LCase.getCentersFaces();
        if (l2LCase instanceof CLCase)
            centersFaces.setFacesMatrix(Moves.mulMatrices(PeanutCase.getFaces(), centersFaces.getFacesMatrix()));
        return centersFaces;
    }

    private static CentersFaces getEG2CenterFaces(){
        MatrixSwap faces;

        faces = new MatrixSwap(Moves.getFacesMatrix());

        faces.swap(4, 10);
        faces.swap(10, 4);
        faces.swap(5, 11);
        faces.swap(11, 5);
        faces.swap(6, 12);
        faces.swap(12, 6);

        faces.swap(13, 19);
        faces.swap(19, 13);
        faces.swap(14, 20);
        faces.swap(20, 14);
        faces.swap(15, 21);
        faces.swap(21, 15);

        return new CentersFaces(Moves.getCenterMatrix(), faces.getMatrix());
    }

    public L2LCase getL2lCase() {
        return l2lCase;
    }
}
