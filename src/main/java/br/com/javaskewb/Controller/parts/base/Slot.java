package br.com.javaskewb.Controller.parts.base;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public abstract class Slot<P extends Part<?>> implements Observer{
    int id;
    P part;
    Polygon polygon;

    public Slot(int id, P part, Polygon polygon){
        setId(id);
        setPolygon(polygon);
        setPart(part);

        part.addObserver(this);
    }

    @Override
    public void updateColor(SkewbColor color){
        polygon.setFill(Color.web(color.getHex()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public P getPart() {
        return part;
    }

    public void setPart(P part) {
        this.part = part;
        polygon.setFill(Color.web(part.getColor().getHex()));
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }
}
