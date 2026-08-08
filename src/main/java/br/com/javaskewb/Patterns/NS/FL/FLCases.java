package br.com.javaskewb.Patterns.NS.FL;

import br.com.javaskewb.Mapping.Solve.AdvancedMoves;
import br.com.javaskewb.Mapping.Solve.FLMoves;
import br.com.javaskewb.Mapping.Solve.Matrices.CentersFaces;
import br.com.javaskewb.Mapping.Solve.Moves;
import br.com.javaskewb.Mapping.Solve.WCAMoves;
import br.com.javaskewb.Patterns.Cases;
import br.com.javaskewb.Solution.BFSSkewb;
import br.com.javaskewb.Solution.utils.Scramble;

import java.util.ArrayList;
import java.util.List;

public class FLCases extends Cases<FLCase> {
    private static final ArrayList<FLCase> cases = new ArrayList<>();
    private static final ArrayList<FLCase> ZeroMoveCases = new ArrayList<>();
    private static final ArrayList<FLCase> OneMoveCases = new ArrayList<>();
    private static final ArrayList<FLCase> TwoMoveCases = new ArrayList<>();
    private static final ArrayList<FLCase> ThreeMoveCases = new ArrayList<>();
    private static final ArrayList<FLCase> FourMoveCases = new ArrayList<>();
    private static final ArrayList<FLCase> FiveMoveCases = new ArrayList<>();
    private static final ArrayList<FLCase> SixMoveCases = new ArrayList<>();
    private static final ArrayList<FLCase> SevenMoveCases = new ArrayList<>();

    private static final ArrayList<ArrayList<FLCase>> CaseByMoves = new ArrayList<>(List.of(
            ZeroMoveCases, OneMoveCases, TwoMoveCases, ThreeMoveCases,
            FourMoveCases, FiveMoveCases, SixMoveCases, SevenMoveCases
    ));

    @Override
    public void fillCases() {
        if (cases.isEmpty()){
            fill();
        }
    }

    public static void fill(){
        FLMoves flMoves = new FLMoves();
        ArrayList<ArrayList<Scramble>> ArrayScramble = BFSSkewb.getFLScrambles(flMoves);

        for (int i = 1; i < 8; i++) {
            ArrayList<Scramble> scrambles = ArrayScramble.get(i);
            for (Scramble scramble: scrambles){
                CentersFaces centersFaces = new CentersFaces(Moves.getCenterMatrix(), Moves.getFacesMatrix());

                for (String move: scramble){
                    centersFaces = Moves.mulCenterFaces(flMoves.getMove(move), centersFaces);
                }

                FLCase flCase = new FLCase(i + " Move Case", centersFaces);
                CaseByMoves.get(i).add(flCase);
                cases.add(flCase);
            }
        }
    }

    @Override
    public ArrayList<FLCase> getCases() {
        return cases;
    }

    public ArrayList<FLCase> getZeroMoveCases() {
        return ZeroMoveCases;
    }

    public ArrayList<FLCase> getOneMoveCases() {
        return OneMoveCases;
    }

    public ArrayList<FLCase> getTwoMoveCases() {
        return TwoMoveCases;
    }

    public ArrayList<FLCase> getThreeMoveCases() {
        return ThreeMoveCases;
    }

    public ArrayList<FLCase> getFourMoveCases() {
        return FourMoveCases;
    }

    public ArrayList<FLCase> getFiveMoveCases() {
        return FiveMoveCases;
    }

    public ArrayList<FLCase> getSixMoveCases() {
        return SixMoveCases;
    }

    public ArrayList<FLCase> getSevenMoveCases() {
        return SevenMoveCases;
    }

    public ArrayList<ArrayList<FLCase>> getCaseByMoves() {
        return CaseByMoves;
    }
}
