package com.application.fxml;
import java.io.IOException;

import com.application.AdminAdvising;
import com.database.*;
import com.database.DatabaseTable;
import com.database.LoginTable;
import com.jfoenix.controls.JFXRadioButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdmAdvController{
	double x=0;
	double y=0;
    @FXML
    private JFXRadioButton enAdvCheckbox;


    @FXML
    private JFXRadioButton dsAdvCheckbox;
    @FXML
	void mouseDragged(MouseEvent dragEvent) {
		Node node=(Node)dragEvent.getSource();
		Stage admAdvStage=(Stage)node.getScene().getWindow();
		admAdvStage.setX(dragEvent.getScreenX()-x);
		admAdvStage.setY(dragEvent.getScreenY()-y);
	}

	@FXML
	void mousePressed(MouseEvent pressedEvent) {
		x=pressedEvent.getSceneX();
		y=pressedEvent.getSceneY();
	}

	public void changeAdvStatus(ActionEvent advStatusEvent) throws IOException {
		String status;
		if(enAdvCheckbox.isSelected())
		{
			status="enable";
			String item="adv";

			DatabaseTable detTabObj=new AdvisingAndFacultyEvaluationStatus();
			detTabObj.connect();
			String queryCondition="Item='"+item+"'";
			detTabObj.update(status,queryCondition);
			setAlertSuccesss();
		}
		else if(dsAdvCheckbox.isSelected())
		{
			status="disable";
			String item="adv";

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
