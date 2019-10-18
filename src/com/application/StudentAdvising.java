package com.application;

import java.awt.event.ActionEvent;
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

public class StudentAdvising {


	public void runStuAdvMenu(Event stuAdvEvent) throws IOException
	{

		Parent stuAdvRoot=FXMLLoader.load(getClass().getResource("/com/application/fxml/Student Advising Menu.fxml"));
		Scene stAdvScene = new Scene(stuAdvRoot);
		stAdvScene.getStylesheets().add(getClass().getResource("/com/application/css/stu adv.css").toExternalForm());

		Stage stuAdvStage=(Stage)((Node)stuAdvEvent.getSource()).getScene().getWindow();
		stuAdvStage.setTitle("Student Advising Menu");
		stuAdvStage.setScene(stAdvScene);
		stuAdvStage.show();
	}

}
