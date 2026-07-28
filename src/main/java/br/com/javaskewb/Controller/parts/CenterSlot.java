package br.com.javaskewb.Controller.parts;

import br.com.javaskewb.Controller.parts.base.Slot;
import javafx.scene.shape.Polygon;

public class CenterSlot extends Slot<CenterPart> {
    public CenterSlot(int id, CenterPart centerPart, Polygon polygon){
        super(id, centerPart, polygon);
    }
}
