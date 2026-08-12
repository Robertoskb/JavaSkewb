package br.com.javaskewb.scrambles;

import br.com.javaskewb.core.Patterns.NS.FL.FLCase;
import br.com.javaskewb.core.Patterns.NS.NSCase;
import br.com.javaskewb.core.Solution.utils.Scramble;
import br.com.javaskewb.scrambles.base.Config;


public class FLNSConfig extends Config {
    private final NSCase nsCase;
    private final FLCase flCase;
    private final int perspective;

    private FLNSConfig(Builder builder){
        nsCase = builder.nsCase;
        flCase = builder.flCase;

        cases.add(nsCase);
        cases.add(flCase);

        perspective = builder.perspective;
    }

    public static class Builder{
        private NSCase nsCase;
        private FLCase flCase;
        private int perspective;

        public Builder nsCase(NSCase nsCase){
            this.nsCase = nsCase;

            return this;
        }

        public Builder flCase(FLCase flCase){
            this.flCase = flCase;

            return this;
        }

        public Builder perspective(int perspective){
            this.perspective = perspective;

            return this;
        }

        public FLNSConfig build(){
            return new FLNSConfig(this);
        }
    }

    public NSCase getNsCase() {
        return nsCase;
    }

    public FLCase getFlCase() {
        return flCase;
    }

    public int getPerspective() {
        return perspective;
    }
}
