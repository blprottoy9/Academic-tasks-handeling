package com.application;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FacultyStudentInfoMenu {

	public void runFacultyStudentInfoMenu(Event facultyStuInfoEvent) throws IOException
	{

		Parent facultyStuInfoRoot=FXMLLoader.load(getClass().getResource("/com/application/fxml/Faculty Student Info.fxml"));
		Scene facultyStuInfoScene = new Scene(facultyStuInfoRoot);
		facultyStuInfoScene.getStylesheets().add(getClass().getResource("/com/application/css/fac stu info.css").toExternalForm());

		Stage facultyStuInfoStage=(Stage)((Node)facultyStuInfoEvent.getSource()).getScene().getWindow();
		facultyStuInfoStage.setTitle("Faculty Student Info Menu");
		facultyStuInfoStage.setScene(facultyStuInfoScene);
		facultyStuInfoStage.show();
	}
}
