package com.application;

import java.io.IOException;

import com.application.fxml.AdmController;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminMenu {

	public void runAdmMenu(Event admEvent) throws IOException
	{

		
		FXMLLoader loader = new FXMLLoader();
		Parent admRoot=loader.load(getClass().getResource("/com/application/fxml/Admin.fxml").openStream());
		AdmController controller=(AdmController)loader.getController();
		controller.setLabel("Admin");
		Scene admScene = new Scene(admRoot);
		admScene.getStylesheets().add(getClass().getResource("/com/application/css/admin.css").toExternalForm());

		Stage admStage=(Stage)((Node)admEvent.getSource()).getScene().getWindow();
		admStage.setTitle("Student Menu");
		admStage.setScene(admScene);
		admStage.show();
		
	}
}
