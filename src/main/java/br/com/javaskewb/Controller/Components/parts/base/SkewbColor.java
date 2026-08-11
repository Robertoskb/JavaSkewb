package br.com.javaskewb.Controller.Components.parts.base;

public enum SkewbColor {

    DEFAULT(-1, "#D3D3D3"),

    WHITE(0, "#FFFFFF"),
    RED(1, "#F04A4A"),
    ORANGE(2, "#F6A93B"),
    YELLOW(3, "#F4F04D"),
    BLUE(4, "#3B82F6"),
    GREEN(5, "#4CAF50");

    private final int id;
    private final String hex;

    SkewbColor(int id, String hex) {
        this.id = id;
        this.hex = hex;
    }

    public static SkewbColor getColoById(int id){
        for (SkewbColor color: SkewbColor.values()){
            if (color.getId() == id){
                return color;
            }
        }

        return SkewbColor.DEFAULT;
    }

    public String getHex() {
        return hex;
    }

    public int getId(){
        return id;
    }
}
