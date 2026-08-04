module JavaSkewb {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.com.javaskewb.Controller to javafx.fxml, javafx.graphics;
    exports br.com.javaskewb.Controller;
    exports br.com.javaskewb;
    exports br.com.javaskewb.Cube;
    exports br.com.javaskewb.Solution;
    exports br.com.javaskewb.Solution.utils;
    exports br.com.javaskewb.Mapping.Parts;
    exports br.com.javaskewb.Mapping;
    exports br.com.javaskewb.Mapping.Solve;
    exports br.com.javaskewb.Mapping.Solve.Matrices;
    exports br.com.javaskewb.Controller.Components.parts;
    opens br.com.javaskewb.Controller.Components.parts to javafx.fxml, javafx.graphics;
    exports br.com.javaskewb.Controller.Components.parts.base;
    opens br.com.javaskewb.Controller.Components.parts.base to javafx.fxml, javafx.graphics;
    exports br.com.javaskewb.Controller.Components;
    opens br.com.javaskewb.Controller.Components to javafx.fxml, javafx.graphics;
}