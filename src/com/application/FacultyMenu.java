package com.application;

import java.io.IOException;

import com.application.fxml.FacultyController;


import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FacultyMenu {
	private String uId;
	public void getId(String uId)
	{
		this.uId=uId;
	}

	public void runFacultyMenu(Event facultyEvent) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		Parent facultyRoot=loader.load(getClass().getResource("/com/application/fxml/Faculty.fxml").openStream());
		FacultyController controller=(FacultyController)loader.getController();
		controller.setLabel(uId);
		Scene facultyScene = new Scene(facultyRoot);
		facultyScene.getStylesheets().add(getClass().getResource("/com/application/css/faculty.css").toExternalForm());
		Stage facultyStage=(Stage)((Node)facultyEvent.getSource()).getScene().getWindow();
		facultyStage.setTitle("Faculty Menu");
		facultyStage.setScene(facultyScene);
		facultyStage.show();
	}
}
