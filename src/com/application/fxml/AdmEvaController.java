package com.application.fxml;

import java.io.IOException;

import com.database.AdvisingAndFacultyEvaluationStatus;
import com.database.DatabaseTable;
import com.jfoenix.controls.JFXRadioButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdmEvaController {
	double x=0;
	double y=0;
    @FXML
    private JFXRadioButton enEvaCheckbox;

    @FXML
    private JFXRadioButton dsEvaCheckbox;
    @FXML
	void mouseDragged(MouseEvent dragEvent) {
		Node node=(Node)dragEvent.getSource();
		Stage admEvaStage=(Stage)node.getScene().getWindow();
		admEvaStage.setX(dragEvent.getScreenX()-x);
		admEvaStage.setY(dragEvent.getScreenY()-y);
	}

	@FXML
	void mousePressed(MouseEvent pressedEvent) {
		x=pressedEvent.getSceneX();
		y=pressedEvent.getSceneY();
	}
	public void changeEvaStatus(ActionEvent evaStatusEvent) throws IOException {
		String status;
		if(enEvaCheckbox.isSelected())
		{
			status="enable";
			String item="eva";

			DatabaseTable detTabObj=new AdvisingAndFacultyEvaluationStatus();
			detTabObj.connect();
			String queryCondition="Item='"+item+"'";
			detTabObj.update(status,queryCondition);
			setAlertSuccesss();
		}
		else if(dsEvaCheckbox.isSelected())
		{
			status="disable";
			String item="eva";

			DatabaseTable detTabObj=new AdvisingAndFacultyEvaluationStatus();
			detTabObj.connect();
			String queryCondition="Item='"+item+"'";
			detTabObj.update(status,queryCondition);
			setAlertSuccesss();
		}
		else
		{
			setAlertFailed();
		}

	}
	@FXML
	public void setAlertSuccesss() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Successful");
		alert.setContentText("Update is Successful");
		alert.showAndWait();
	}
	@FXML
	public void setAlertFailed() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Failed");
		alert.setContentText("Update is Failed");
		alert.showAndWait();
	}
}

