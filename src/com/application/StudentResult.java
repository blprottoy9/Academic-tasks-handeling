package com.application;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentResult {

	public void runStuResMenu(Event stuResEvent) throws IOException
	{

		Parent stuResRoot=FXMLLoader.load(getClass().getResource("/com/application/fxml/Student Result Menu.fxml"));
		Scene stResScene = new Scene(stuResRoot);
		stResScene.getStylesheets().add(getClass().getResource("/com/application/css/stu res.css").toExternalForm());

		Stage stuResStage=(Stage)((Node)stuResEvent.getSource()).getScene().getWindow();
		stuResStage.setTitle("Student Result Menu");
		stuResStage.setScene(stResScene);
		stuResStage.show();
	}
}
