package com.application;

import java.io.IOException;

import com.application.fxml.FacultyController;
import com.application.fxml.FacultyRoutineController;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FacultyRoutineMenu {

	public void runFacultyRoutineMenu(Event facultyRouEvent) throws IOException
	{

		Parent facultyRouRoot=FXMLLoader.load(getClass().getResource("/com/application/fxml/Faculty Routine.fxml"));

		Scene facultyRouScene = new Scene(facultyRouRoot);
		facultyRouScene.getStylesheets().add(getClass().getResource("/com/application/css/fac routine.css").toExternalForm());

		Stage facultyRouStage=(Stage)((Node)facultyRouEvent.getSource()).getScene().getWindow();
		facultyRouStage.setTitle("Faculty Routine Menu");
		facultyRouStage.setScene(facultyRouScene);
		facultyRouStage.show();
	}
}
