package br.com.javaskewb.core.Patterns.Methods.FS.FF;

import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Mapping.Moves.FLMoves;
import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.Methods.FS.FSSubCases;
import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Patterns.base.Cases;
import br.com.javaskewb.core.Solution.BFSSkewb;
import br.com.javaskewb.core.Solution.utils.Scramble;

import java.util.*;

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
        HashMap<State, Scramble> bfs = BFSSkewb.getFFScrambles();

        Set<Case> variants = new HashSet<>();

        int[] count = new int[8];

        for (Scramble scramble: bfs.values()) {
            int size = scramble.size();

            CentersFaces centersFaces = new CentersFaces(Moves.getCenterMatrix(), Moves.getFacesMatrix());

            for (String move: scramble){
                centersFaces = Moves.mulCenterFaces(flMoves.getMove(move), centersFaces);
            }

            FFCase ffCase = new FFCase(size + " M Case " + count[size], centersFaces);
            if (!variants.contains(ffCase)) {
                count[size]++;
                CaseByMoves.get(size).add(ffCase);
            }
            else
                continue;
            variants.addAll(ffCase.getCasesVariants());
        }

        int cont = 0;
        for (ArrayList<FFCase> flCases: CaseByMoves){
            cases.addAll(flCases);
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
    public List<Cases<?>> getSubCases() {
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
