package com.application;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateAccMenu {

	public void runCreateAccMenu(Event createAccEvent) throws IOException
	{

		Parent createAccRoot=FXMLLoader.load(getClass().getResource("/com/application/fxml/Create Account Menu .fxml"));
		
		Scene createAccScene = new Scene(createAccRoot);
		createAccScene.getStylesheets().add(getClass().getResource("/com/application/css/create acc.css").toExternalForm());
		Stage createAccStage=(Stage)((Node)createAccEvent.getSource()).getScene().getWindow();
		createAccStage.setTitle("Create Account Menu");
		createAccStage.setScene(createAccScene);
		createAccStage.show();
	}
}
