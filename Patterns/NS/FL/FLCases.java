package br.com.javaskewb.core.Patterns.NS.FL;

import br.com.javaskewb.core.Mapping.Moves.FLMoves;
import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Patterns.base.Cases;
import br.com.javaskewb.core.Patterns.NS.NSCases;
import br.com.javaskewb.core.Solution.BFSSkewb;
import br.com.javaskewb.core.Solution.utils.Scramble;

import java.util.*;

public class FLCases extends Cases<FLCase> {
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

    private static final ArrayList<FLCase> cases = fill();

    public FLCases() {
        super("FL");
    }

    public static ArrayList<FLCase> fill(){
        ArrayList<FLCase> cases = new ArrayList<>();
        FLMoves flMoves = new FLMoves();
        ArrayList<ArrayList<Scramble>> ArrayScramble = BFSSkewb.getFLScrambles(flMoves);

        FLCase zeroMove = new FLCase("0 move case", new CentersFaces(Moves.getCenterMatrix(), Moves.getFacesMatrix()));
        cases.add(zeroMove);
        ZeroMoveCases.add(zeroMove);
        for (int i = 1; i < 8; i++) {
            Set<Case> variants = new HashSet<>();
            ArrayList<Scramble> scrambles = ArrayScramble.get(i);
            for (Scramble scramble: scrambles){
                CentersFaces centersFaces = new CentersFaces(Moves.getCenterMatrix(), Moves.getFacesMatrix());

                for (String move: scramble){
                    centersFaces = Moves.mulCenterFaces(flMoves.getMove(move), centersFaces);
                }

                FLCase flCase = new FLCase(i + " Move Case " + scramble, centersFaces);
                if (!variants.contains(flCase)) {
                    CaseByMoves.get(i).add(flCase);
                    cases.add(flCase);
                }

                else
                    continue;
                variants.addAll(flCase.getCasesVariants());
            }
        }

        return cases;
    }

    public static void main(String[] args) {
        NSCases nsCases = new NSCases();
        FLCases flCases = nsCases.getFlCases();

        int cont = 0;
        for (ArrayList<FLCase> flCaseArrayList: flCases.getCaseByMoves())
            System.out.println(cont++ + " Moves: " + flCaseArrayList.size());
    }

    @Override
    public ArrayList<FLCase> getCases() {
        return cases;
    }

    public FLCase getRandomFLByMoves(int moves){
        ArrayList<FLCase> flMoves = CaseByMoves.get(moves);

        return flMoves.get(random.nextInt(0, flMoves.size()));
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
