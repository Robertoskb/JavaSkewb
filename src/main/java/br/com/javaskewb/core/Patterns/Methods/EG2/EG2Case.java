package br.com.javaskewb.core.Patterns.Methods.EG2;

import br.com.javaskewb.core.Mapping.Moves.AdvancedMoves;
import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Matrices.MatrixSwap;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Peanut.PeanutCase;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.base.CLCase;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.L2LCase;
import br.com.javaskewb.core.Patterns.Methods.NS.NSCase;
import br.com.javaskewb.core.Patterns.base.Case;

import java.util.Arrays;
import java.util.List;


public class EG2Case extends NSCase {
    private final Case nsCase;

    public EG2Case(Case ns) {
        super(ns.getName(), Moves.mulCenterFaces(getEG2CenterFaces(ns), flip(ns)));
        this.nsCase = ns;
    }

    public static CentersFaces flip(Case case_){
        CentersFaces centersFaces = case_.getCentersFaces().copy();
        if (case_ instanceof CLCase)
            centersFaces.setFacesMatrix(Moves.mulMatrices(PeanutCase.getFaces(), centersFaces.getFacesMatrix()));
        return centersFaces;
    }

    public static CentersFaces getEG2CenterFaces(Case case_){
        if (case_.getName().equals("U2"))
            return getEG2CenterFaces2();

        return getEG2CenterFaces1();
    }

    public static CentersFaces getEG2CenterFaces1(){
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

    public static CentersFaces getEG2CenterFaces2(){
        CentersFaces y = AdvancedMoves.y();
        CentersFaces yi = Moves.invertMove(y);
        CentersFaces centersFaces = Moves.mulCenterFaces(getEG2CenterFaces1(), y);

        return Moves.mulCenterFaces(yi, centersFaces);
    }

    public Case getNsCase() {
        return nsCase;
    }
}
