package br.com.javaskewb.scrambles.base;

import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Patterns.base.Case;

import java.util.ArrayList;

public interface Config {
    void applyCases(State state);
}
