package br.com.javaskewb.Controller.Components.parts;

import br.com.javaskewb.Controller.Components.parts.base.Part;
import br.com.javaskewb.Controller.Components.parts.base.SkewbColor;

import java.util.HashMap;
import java.util.Map;

public class FacePart extends Part<Integer> {
    private static final HashMap<Integer, SkewbColor> skewbColors = new HashMap<>(
            Map.ofEntries(
                    Map.entry(0, SkewbColor.DEFAULT),

                    Map.entry(1, SkewbColor.WHITE),
                    Map.entry(4, SkewbColor.WHITE),
                    Map.entry(7, SkewbColor.WHITE),
                    Map.entry(10, SkewbColor.WHITE),

                    Map.entry(2, SkewbColor.RED),
                    Map.entry(12, SkewbColor.RED),
                    Map.entry(21, SkewbColor.RED),
                    Map.entry(23, SkewbColor.RED),

                    Map.entry(3, SkewbColor.GREEN),
                    Map.entry(5, SkewbColor.GREEN),
                    Map.entry(18, SkewbColor.GREEN),
                    Map.entry(20, SkewbColor.GREEN),

                    Map.entry(6, SkewbColor.ORANGE),
                    Map.entry(8, SkewbColor.ORANGE),
                    Map.entry(15, SkewbColor.ORANGE),
                    Map.entry(17, SkewbColor.ORANGE),

                    Map.entry(9, SkewbColor.BLUE),
                    Map.entry(11, SkewbColor.BLUE),
                    Map.entry(14, SkewbColor.BLUE),
                    Map.entry(24, SkewbColor.BLUE),

                    Map.entry(13, SkewbColor.YELLOW),
                    Map.entry(16, SkewbColor.YELLOW),
                    Map.entry(19, SkewbColor.YELLOW),
                    Map.entry(22, SkewbColor.YELLOW)
            )
    );

    public FacePart(Integer part) {
        super(part, skewbColors.get(part+1));
    }

    public static HashMap<Integer, SkewbColor> getSkewbColors() {
        return skewbColors;
    }
}
