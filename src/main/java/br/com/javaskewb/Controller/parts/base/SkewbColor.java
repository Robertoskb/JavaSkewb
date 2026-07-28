package br.com.javaskewb.Controller.parts.base;

public enum SkewbColor {

    WHITE("#FFFFFF"),
    YELLOW("#FFFF00"),
    RED("#FF0000"),
    ORANGE("#FFA500"),
    BLUE("#0000FF"),
    GREEN("#00FF00"),
    DEFAULT("#d3d3d3");

    private final String hex;

    SkewbColor(String hex) {
        this.hex = hex;
    }

    public String getHex() {
        return hex;
    }
}
