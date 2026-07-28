package br.com.javaskewb.Controller.parts.base;

public abstract class Part<P> {
    private P part;
    private SkewbColor color;

    public Part(P part, SkewbColor color){
        setPart(part);
        setColor(color);
    }

    public P getPart() {
        return part;
    }

    public void setPart(P part) {
        this.part = part;
    }

    public SkewbColor getColor() {
        return color;
    }

    public void setColor(SkewbColor color) {
        this.color = color;
    }
}
