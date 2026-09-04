package br.com.javaskewb.Controller.Components.parts;

import br.com.javaskewb.Controller.Components.parts.base.Part;
import br.com.javaskewb.Controller.Components.parts.base.SkewbColor;

import java.util.HashMap;
import java.util.Map;

public class FacePart extends Part<Integer> {
    public FacePart(Integer part) {
        super(part, SkewbColor.getColoById(part));
    }

}
