module JavaSkewb {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.com.javaskewb.Controller to javafx.fxml, javafx.graphics;
    exports br.com.javaskewb.Controller;
    exports br.com.javaskewb;
    exports br.com.javaskewb.Cube;
    exports br.com.javaskewb.Mapping.parts;
    exports br.com.javaskewb.Controller.parts;
    opens br.com.javaskewb.Controller.parts to javafx.fxml, javafx.graphics;
    exports br.com.javaskewb.Controller.parts.base;
    opens br.com.javaskewb.Controller.parts.base to javafx.fxml, javafx.graphics;
}