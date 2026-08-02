package br.com.javaskewb.Controller.parts.base;

public enum SkewbColor {

    DEFAULT(-1, "#d3d3d3"),
    WHITE(0, "#FFFFFF"),
    YELLOW(1,"#FFFF00"),
    RED(2,"#FF0000"),
    ORANGE(3,"#FFA500"),
    BLUE(4, "#0000FF"),
    GREEN(5, "#00FF00");

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
