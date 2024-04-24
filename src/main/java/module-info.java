module dk.sdu.se.vop.examprep {
    requires javafx.controls;
    requires javafx.fxml;


    opens dk.sdu.se.vop.examprep to javafx.fxml;
    exports dk.sdu.se.vop.examprep;
}