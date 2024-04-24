package dk.sdu.se.vop.examprep;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
	public static void main(final String[] args) {
		Application.launch();
	}

	@Override
	public void start(final Stage stage) throws IOException {
		final FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
		final Scene scene = new Scene(fxmlLoader.load());
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.show();
	}
}