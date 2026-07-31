package br.com.javaskewb.Solution.utils;

public class Translate {
    private String real;
    private String alternative;

    private int realCorner;
    private int alternativeCorner;

    private boolean clockWise;

    public Translate(String real, String alternative, int realCorner, int alternativeCorner, boolean clockWise) {
        setReal(real);
        setAlternative(alternative);
        setRealCorner(realCorner);
        setAlternativeCorner(alternativeCorner);
        setClockWise(clockWise);
    }

    public Translate(String real, String alternative, int realCorner, int alternativeCorner) {
        setReal(real);
        setAlternative(alternative);
        setRealCorner(realCorner);
        setAlternativeCorner(alternativeCorner);
        setClockWise(!real.contains("'"));
    }

    public String getReal() {
        return real;
    }

    public void setReal(String real) {
        this.real = real;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public int getRealCorner() {
        return realCorner;
    }

    public void setRealCorner(int realCorner) {
        this.realCorner = realCorner;
    }

    public int getAlternativeCorner() {
        return alternativeCorner;
    }

    public void setAlternativeCorner(int alternativeCorner) {
        this.alternativeCorner = alternativeCorner;
    }

    public boolean isClockWise() {
        return clockWise;
    }

    public void setClockWise(boolean clockWise) {
        this.clockWise = clockWise;
    }
}
