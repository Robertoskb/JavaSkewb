package br.com.javaskewb.Controller.parts;

import br.com.javaskewb.Controller.parts.base.Part;
import br.com.javaskewb.Controller.parts.base.SkewbColor;
import br.com.javaskewb.Mapping.Parts.Center;

import java.util.HashMap;
import java.util.Map;

public class CenterPart extends Part<Center> {
    private static final HashMap<Integer, SkewbColor> skewbColors = new HashMap<>(
            Map.of(0, SkewbColor.DEFAULT,

                    1, SkewbColor.WHITE,
                    2, SkewbColor.RED,
                    3, SkewbColor.GREEN,

                    4, SkewbColor.YELLOW,
                    5, SkewbColor.BLUE,
                    6, SkewbColor.ORANGE
            )
    );

    public CenterPart(Center center){
        super(center, skewbColors.get(center.getValue()+1));
    }
}
