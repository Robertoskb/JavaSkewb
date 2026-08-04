package br.com.javaskewb.Controller.Components.parts;

import br.com.javaskewb.Controller.Components.parts.base.Slot;
import javafx.scene.shape.Polygon;

public class FaceSlot extends Slot<FacePart> {
    public FaceSlot(int id, FacePart part, Polygon polygon) {
        super(id, part, polygon);
    }
}
