package br.com.javaskewb.scrambles.base;

import br.com.javaskewb.core.Patterns.Methods.FS.FL.FLCase;
import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Patterns.base.Cases;

public interface FLMethodConfig extends Config{
    Case getFlCase();
    int getPerspective();

    void randomConfig(int flMoves);
    void randomConfig(Case flCase);

}
