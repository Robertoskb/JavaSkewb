package br.com.javaskewb.scrambles;

import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Patterns.Methods.NS.NSCase;
import br.com.javaskewb.scrambles.base.Config;


public class FLNSConfig extends Config {
    private final NSCase nsCase;
    private final Case flCase;
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
        private Case flCase;
        private int perspective;

        public Builder nsCase(NSCase nsCase){
            this.nsCase = nsCase;

            return this;
        }

        public Builder flCase(Case flCase){
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

    public Case getFlCase() {
        return flCase;
    }

    public int getPerspective() {
        return perspective;
    }
}
