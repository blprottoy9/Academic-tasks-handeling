package com.application;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class AdminAdvising{

	public void runAdmAdvMenu(Event admAdvEvent) throws IOException
	{

		Parent admAdvRoot=FXMLLoader.load(getClass().getResource("/com/application/fxml/Advising Menu.fxml"));
		Scene admAdvScene = new Scene(admAdvRoot);
		admAdvScene.getStylesheets().add(getClass().getResource("/com/application/css/admadv.css").toExternalForm());
		Stage admAdvStage=(Stage)((Node)admAdvEvent.getSource()).getScene().getWindow();
		admAdvStage.setTitle("Advising/Faculty Evaluation Menu");
		admAdvStage.setScene(admAdvScene);
		admAdvStage.show();
	}
}
