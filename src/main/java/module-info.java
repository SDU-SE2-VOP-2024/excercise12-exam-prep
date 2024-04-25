module dk.sdu.se.vop.examprep {
	requires javafx.controls;
	requires javafx.fxml;
	exports dk.sdu.se.vop.examprep.gui.panes;
	exports dk.sdu.se.vop.examprep;
	opens dk.sdu.se.vop.examprep to javafx.fxml;
	opens dk.sdu.se.vop.examprep.gui.panes to javafx.fxml;
}