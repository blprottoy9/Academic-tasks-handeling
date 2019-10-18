package com.application;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StudentFacEvaluation{

	public void runStuEvaMenu(Event stuEvaEvent) throws IOException
	{

		Parent stuEvaRoot=FXMLLoader.load(getClass().getResource("/com/application/fxml/Student Faculty Evaluation Menu.fxml"));
		Scene stEvaScene = new Scene(stuEvaRoot);
		stEvaScene.getStylesheets().add(getClass().getResource("/com/application/css/stu eva.css").toExternalForm());

		Stage stuEvaStage=(Stage)((Node)stuEvaEvent.getSource()).getScene().getWindow();
		stuEvaStage.setTitle("Student Faculty Evaluation Menu");
		stuEvaStage.setScene(stEvaScene);
		stuEvaStage.show();
	}
}
