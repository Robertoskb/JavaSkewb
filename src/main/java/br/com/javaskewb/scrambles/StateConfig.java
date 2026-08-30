package br.com.javaskewb.scrambles;

import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Solution.Solution;
import br.com.javaskewb.core.Solution.utils.Scramble;
import br.com.javaskewb.scrambles.base.Config;
import br.com.javaskewb.scrambles.base.FLMethodConfig;

public class StateConfig {
    private State state;
    private FLMethodConfig config;
    private Scramble scramble;

    private final Solution solution = new Solution();

    public StateConfig(State state, FLMethodConfig config){
        setState(state);
        setConfig(config);
        findScramble();
    }

    private void findScramble(){
        config.applyCases(state);
        scramble = solution.findScramble(state);
    }

    public Scramble getScramble() {
        return scramble;
    }

    public FLMethodConfig getConfig() {
        return config;
    }

    public void setConfig(FLMethodConfig config) {
        this.config = config;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
