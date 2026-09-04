package br.com.javaskewb.Controller.Components.parts;

import br.com.javaskewb.Controller.Components.parts.base.Part;
import br.com.javaskewb.Controller.Components.parts.base.SkewbColor;
import br.com.javaskewb.core.Mapping.Parts.Center;

import java.util.HashMap;
import java.util.Map;

public class CenterPart extends Part<Center> {
    public CenterPart(Center center){
        super(center, SkewbColor.getColoById(center.getValue()));
    }

}
