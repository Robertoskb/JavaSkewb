package br.com.javaskewb.Controller.parts.base;

import java.util.ArrayList;

public abstract class Part<P> implements Subject{
    private P part;
    private SkewbColor color = SkewbColor.DEFAULT;

    private final ArrayList<Observer> observers = new ArrayList<>();

    public Part(P part, SkewbColor color){
        setPart(part);
        setColor(color);
    }

    @Override
    public void notifyUpdateColor(){
        for (Observer observer: observers){
            observer.updateColor(color);
        }
    }

    @Override
    public void addObserver(Observer observer){
        observers.add(observer);
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

        notifyUpdateColor();
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }
}
