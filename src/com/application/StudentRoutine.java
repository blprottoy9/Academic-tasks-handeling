package com.application;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentRoutine {

	public void runStuRtnMenu(Event stuRtnEvent) throws IOException
	{

		Parent stuRtnRoot=FXMLLoader.load(getClass().getResource("/com/application/fxml/Student Routine Menu.fxml"));
		Scene stRtnScene = new Scene(stuRtnRoot);
		stRtnScene.getStylesheets().add(getClass().getResource("/com/application/css/stu rou.css").toExternalForm());

		Stage stuRtnStage=(Stage)((Node)stuRtnEvent.getSource()).getScene().getWindow();
		stuRtnStage.setTitle("Student Routine Menu");
		stuRtnStage.setScene(stRtnScene);
		stuRtnStage.show();
	}
}
