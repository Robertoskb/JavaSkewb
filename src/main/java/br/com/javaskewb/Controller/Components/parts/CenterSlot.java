package br.com.javaskewb.Controller.Components.parts;

import br.com.javaskewb.Controller.Components.parts.base.Slot;
import javafx.scene.shape.Polygon;

public class CenterSlot extends Slot<CenterPart> {
    public CenterSlot(int id, CenterPart centerPart, Polygon polygon){
        super(id, centerPart, polygon);
    }
}
