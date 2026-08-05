package br.com.javaskewb.Patterns.NS;

import br.com.javaskewb.Patterns.Cases;
import br.com.javaskewb.Patterns.NS.L2L.L2LCases;
import br.com.javaskewb.Patterns.NS.L2L.LC.LCCase;
import br.com.javaskewb.Patterns.NS.L2L.LC.LCCases;
import br.com.javaskewb.Patterns.NS.L2L.Peanut.PeanutCase;

import java.util.ArrayList;

public class NSCases extends Cases<NSCase> {
    private static final ArrayList<NSCase> cases = new ArrayList<>();
    private static final ArrayList<NSCase> PeanutUCases = new ArrayList<>();
    private static final ArrayList<NSCase> PeanutFRCases = new ArrayList<>();
    private static final ArrayList<NSCase> PeanutFLCases = new ArrayList<>();
    private static final ArrayList<NSCase> PeanutBRCases = new ArrayList<>();
    private static final ArrayList<NSCase> PeanutBLCases = new ArrayList<>();

    private static final ArrayList<NSCase> PiUCases = new ArrayList<>();
    private static final ArrayList<NSCase> PiFRCases = new ArrayList<>();
    private static final ArrayList<NSCase> PiFLCases = new ArrayList<>();
    private static final ArrayList<NSCase> PiBRCases = new ArrayList<>();
    private static final ArrayList<NSCase> PiBLCases = new ArrayList<>();

    @Override
    public void fillCases() {
        if (cases.isEmpty()){
            for (NSCase nsCase: new L2LCases().getCases()){
                cases.add(nsCase);

                if (nsCase instanceof LCCase)
                    continue;

                if (nsCase instanceof PeanutCase){
                    if (checkCenter(0, nsCase)) PeanutUCases.add(nsCase);
                    if (checkCenter(1, nsCase)) PeanutFRCases.add(nsCase);
                    if (checkCenter(2, nsCase)) PeanutFLCases.add(nsCase);
                    if (checkCenter(4, nsCase)) PeanutBRCases.add(nsCase);
                    if (checkCenter(5, nsCase)) PeanutBLCases.add(nsCase);
                }

                else {
                    if (checkCenter(0, nsCase)) PiUCases.add(nsCase);
                    if (checkCenter(1, nsCase)) PiFRCases.add(nsCase);
                    if (checkCenter(2, nsCase)) PiFLCases.add(nsCase);
                    if (checkCenter(4, nsCase)) PiBRCases.add(nsCase);
                    if (checkCenter(5, nsCase)) PiBLCases.add(nsCase);
                }

            }
        }
    }


    public boolean checkCenter(int center, NSCase nsCase){
        return nsCase.getCentersFaces().getCentersMatrix()[center][0] == 1;
    }

    @Override
    public ArrayList<NSCase> getCases() {
        return cases;
    }

    public ArrayList<NSCase> getPeanutUCases() {
        return PeanutUCases;
    }

    public ArrayList<NSCase> getPeanutFRCases() {
        return PeanutFRCases;
    }

    public ArrayList<NSCase> getPeanutFLCases() {
        return PeanutFLCases;
    }

    public ArrayList<NSCase> getPeanutBRCases() {
        return PeanutBRCases;
    }

    public ArrayList<NSCase> getPeanutBLCases() {
        return PeanutBLCases;
    }

    public ArrayList<NSCase> getPiUCases() {
        return PiUCases;
    }

    public ArrayList<NSCase> getPiFRCases() {
        return PiFRCases;
    }

    public ArrayList<NSCase> getPiFLCases() {
        return PiFLCases;
    }

    public ArrayList<NSCase> getPiBRCases() {
        return PiBRCases;
    }

    public ArrayList<NSCase> getPiBLCases() {
        return PiBLCases;
    }

}
