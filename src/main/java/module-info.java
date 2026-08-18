module JavaSkewb {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens br.com.javaskewb.DataManager.Manager to com.google.gson;

    opens br.com.javaskewb.Controller to javafx.fxml, javafx.graphics;
    exports br.com.javaskewb.Controller;
    exports br.com.javaskewb;
    exports br.com.javaskewb.core.Cube;
    exports br.com.javaskewb.core.Solution;
    exports br.com.javaskewb.core.Solution.utils;
    exports br.com.javaskewb.core.Mapping.Parts;
    exports br.com.javaskewb.core.Mapping.Moves;
    exports br.com.javaskewb.core.Mapping.Moves.Matrices;
    exports br.com.javaskewb.Controller.Components.parts;
    opens br.com.javaskewb.Controller.Components.parts to javafx.fxml, javafx.graphics;
    exports br.com.javaskewb.Controller.Components.parts.base;
    opens br.com.javaskewb.Controller.Components.parts.base to javafx.fxml, javafx.graphics;
    exports br.com.javaskewb.Controller.Components;
    opens br.com.javaskewb.Controller.Components to javafx.fxml, javafx.graphics;
    exports br.com.javaskewb.DataManager.utils;
    exports br.com.javaskewb.core.Patterns.base;
}