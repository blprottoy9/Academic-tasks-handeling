package com.application;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FacultyGradeSubMenu {

	public void runFacultyGradeSubMenu(Event facultyGradeSubEvent) throws IOException
	{

		Parent facultyGradeSubRoot=FXMLLoader.load(getClass().getResource("/com/application/fxml/Faculty Grade Submission Menu.fxml"));
		Scene facultyGradeSubScene = new Scene(facultyGradeSubRoot);
		facultyGradeSubScene.getStylesheets().add(getClass().getResource("/com/application/css/fac grade sub.css").toExternalForm());

		Stage facultyGradeSubStage=(Stage)((Node)facultyGradeSubEvent.getSource()).getScene().getWindow();
		facultyGradeSubStage.setTitle("Faculty Grade Submission Menu");
		facultyGradeSubStage.setScene(facultyGradeSubScene);
		facultyGradeSubStage.show();
	}
}
