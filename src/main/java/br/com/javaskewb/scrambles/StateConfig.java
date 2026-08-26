package br.com.javaskewb.scrambles;

import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Solution.Solution;
import br.com.javaskewb.core.Solution.utils.Scramble;
import br.com.javaskewb.scrambles.base.Config;

public class StateConfig {
    private State state;
    private FLNSConfig config;
    private Scramble scramble;

    private final Solution solution = new Solution();

    public StateConfig(State state, FLNSConfig config){
        setState(state);
        setConfig(config);
        setScramble();
    }

    private void setScramble(){
        config.applyCases(state);
        scramble = solution.findScramble(state);
    }

    public Scramble getScramble() {
        return scramble;
    }

    public FLNSConfig getConfig() {
        return config;
    }

    public void setConfig(FLNSConfig config) {
        this.config = config;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
