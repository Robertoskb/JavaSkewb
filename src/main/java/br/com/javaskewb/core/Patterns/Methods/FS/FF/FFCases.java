package br.com.javaskewb.core.Patterns.Methods.FS.FF;

import br.com.javaskewb.core.Mapping.Moves.FLMoves;
import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.Methods.FS.FSSubCases;
import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Patterns.base.Cases;
import br.com.javaskewb.core.Solution.BFSSkewb;
import br.com.javaskewb.core.Solution.utils.Scramble;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FFCases extends Cases<FFCase> {
    private static final ArrayList<FFCase> ZeroMoveCases = new ArrayList<>();
    private static final ArrayList<FFCase> OneMoveCases = new ArrayList<>();
    private static final ArrayList<FFCase> TwoMoveCases = new ArrayList<>();
    private static final ArrayList<FFCase> ThreeMoveCases = new ArrayList<>();
    private static final ArrayList<FFCase> FourMoveCases = new ArrayList<>();
    private static final ArrayList<FFCase> FiveMoveCases = new ArrayList<>();
    private static final ArrayList<FFCase> SixMoveCases = new ArrayList<>();

    private static final ArrayList<ArrayList<FFCase>> CaseByMoves = new ArrayList<>(List.of(
            ZeroMoveCases, OneMoveCases, TwoMoveCases, ThreeMoveCases,
            FourMoveCases, FiveMoveCases, SixMoveCases
    ));

    private static final ArrayList<Cases<?>> subCases = new ArrayList<>();

    private static final ArrayList<FFCase> cases = fill();

    public FFCases() {
        super("FF");
    }

    public static ArrayList<FFCase> fill(){
        ArrayList<FFCase> cases = new ArrayList<>();
        FLMoves flMoves = new FLMoves();
        ArrayList<ArrayList<Scramble>> ArrayScramble = BFSSkewb.getFFScrambles(flMoves);

        FFCase zeroMove = new FFCase("0 M Case", new CentersFaces(Moves.getCenterMatrix(), Moves.getFacesMatrix()));
        cases.add(zeroMove);
        ZeroMoveCases.add(zeroMove);
        for (int i = 1; i < 7; i++) {
            Set<Case> variants = new HashSet<>();
            ArrayList<Scramble> scrambles = ArrayScramble.get(i);

            int count = 0;
            for (Scramble scramble: scrambles){
                CentersFaces centersFaces = new CentersFaces(Moves.getCenterMatrix(), Moves.getFacesMatrix());

                for (String move: scramble){
                    centersFaces = Moves.mulCenterFaces(flMoves.getMove(move), centersFaces);
                }

                FFCase flCase = new FFCase(i + " M Case " + count++, centersFaces);
                if (!variants.contains(flCase)) {
                    CaseByMoves.get(i).add(flCase);
                    cases.add(flCase);
                }

                else
                    continue;
                variants.addAll(flCase.getCasesVariants());
            }
        }

        int cont = 0;
        for (ArrayList<FFCase> flCases: CaseByMoves){
            subCases.add(new FSSubCases<>(cont++ + " M", flCases));
        }

        subCases.removeFirst();

        return cases;
    }

    @Override
    public ArrayList<FFCase> getCases() {
        return cases;
    }

    @Override
    public ArrayList<Cases<?>> getSubCases() {
        return subCases;
    }

    public FFCase getRandomFFByMoves(int moves){
        ArrayList<FFCase> flMoves = CaseByMoves.get(moves);

        return flMoves.get(random.nextInt(0, flMoves.size()));
    }

    public ArrayList<FFCase> getZeroMoveCases() {
        return ZeroMoveCases;
    }

    public ArrayList<FFCase> getOneMoveCases() {
        return OneMoveCases;
    }

    public ArrayList<FFCase> getTwoMoveCases() {
        return TwoMoveCases;
    }

    public ArrayList<FFCase> getThreeMoveCases() {
        return ThreeMoveCases;
    }

    public ArrayList<FFCase> getFourMoveCases() {
        return FourMoveCases;
    }

    public ArrayList<FFCase> getFiveMoveCases() {
        return FiveMoveCases;
    }
    public ArrayList<FFCase> getSixMoveCases() {
        return SixMoveCases;
    }


    public ArrayList<ArrayList<FFCase>> getCaseByMoves() {
        return CaseByMoves;
    }
}
