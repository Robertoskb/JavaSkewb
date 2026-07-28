package br.com.javaskewb.Controller.parts;

import br.com.javaskewb.Controller.parts.base.Slot;
import javafx.scene.shape.Polygon;

public class FaceSlot extends Slot<FacePart> {
    public FaceSlot(int id, FacePart part, Polygon polygon) {
        super(id, part, polygon);
    }
}
