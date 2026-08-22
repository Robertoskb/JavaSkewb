package br.com.javaskewb.core.Patterns.Methods;

import br.com.javaskewb.core.Patterns.Methods.EG2.EG2Cases;
import br.com.javaskewb.core.Patterns.Methods.FS.FF.FFCases;
import br.com.javaskewb.core.Patterns.Methods.FS.FL.FLCases;
import br.com.javaskewb.core.Patterns.Methods.NS.NSCases;
import br.com.javaskewb.core.Patterns.base.Cases;

import java.util.ArrayList;
import java.util.List;

public class Methods {
    private final NSCases NS = new NSCases();
    private final EG2Cases EG2 = new EG2Cases();
    private final FLCases FL = new FLCases();
    private final FFCases FF = new FFCases();

    private final ArrayList<Cases<?>> METHODS = new ArrayList<>(List.of(NS, EG2, FL, FF));

    public ArrayList<Cases<?>> getMETHODS() {
        return METHODS;
    }

    public NSCases getNS() {
        return NS;
    }

    public EG2Cases getEG2() {
        return EG2;
    }
}
