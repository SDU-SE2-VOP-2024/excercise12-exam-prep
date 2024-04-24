package dk.sdu.se.vop.examprep.gui.panes;

import dk.sdu.se.vop.examprep.facade.Facade;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.util.Arrays;

public class FacadeController {
	@FXML
	private Button fillRandomBtn;
	@FXML
	private Button fillUniqueBtn;
	@FXML
	private Label outputDivisorSum;
	@FXML
	private TextArea outputArray;
	@FXML
	private TextField inputDivisor;
	@FXML
	private TextField inputMax;
	@FXML
	private TextField inputSize;

	private Facade facade;

	@FXML
	public void initialize() {
		this.facade = new Facade();
		this.fillRandomBtn.addEventHandler(ActionEvent.ACTION, this::handleFillRandomBtn);
		this.fillUniqueBtn.addEventHandler(ActionEvent.ACTION, this::handleFillUniqueBtn);
		this.inputDivisor.textProperty().addListener(this::handleDivisorInput);
	}


	private void handleDivisorInput(final Observable value) {
		if (this.inputDivisor.getText().isBlank()) return; // Allow blank for divisor (do nothing)
		try {
			final int divisor = this.parseIntFrom(this.inputDivisor, "Divisor");
			this.outputDivisorSum.setText(String.valueOf(this.facade.sumOfDivisors(divisor)));
			this.outputArray.setText(Arrays.toString(this.facade.getIntArray()));
		} catch (final IllegalArgumentException e) {
			this.reportError(e.getMessage());
		} catch (final ArithmeticException e) {
			this.reportError("Division with zero illegal");
		}
	}

	private void handleFillUniqueBtn(final ActionEvent actionEvent) {
		try {
			final int size = this.parseIntFrom(this.inputSize, "Size");
			final int max = this.parseIntFrom(this.inputMax, "Max");
			this.outputArray.setText(Arrays.toString(this.facade.fillUniqueArray(size, max)));
		} catch (final IllegalArgumentException e) {
			this.reportError(e.getMessage());
		}
	}

	private void handleFillRandomBtn(final ActionEvent actionEvent) {
		try {
			final int size = this.parseIntFrom(this.inputSize, "Size");
			final int max = this.parseIntFrom(this.inputMax, "Max");
			this.outputArray.setText(Arrays.toString(this.facade.fillArray(size, max)));
		} catch (final IllegalArgumentException e) {
			this.reportError(e.getMessage());
		}
	}

	private int parseIntFrom(final TextInputControl inputControl, final String fieldName) {
		if (inputControl.getText().isEmpty()) {
			throw new IllegalArgumentException(String.format("Field '%s' is empty", fieldName));
		}

		try {
			return Integer.parseInt(inputControl.getText());
		} catch (final NumberFormatException e) {
			throw new IllegalArgumentException(String.format("Field '%s' is not an integer", fieldName));
		}
	}

	private void reportError(final String message) {
		this.outputArray.setText(String.format("Error: %s", message));
	}
}
