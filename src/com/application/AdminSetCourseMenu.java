package com.application;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminSetCourseMenu {

	public void runAdminSetCourseMenu(Event admSetCourseEvent) throws IOException
	{

		Parent admSetCourseRoot=FXMLLoader.load(getClass().getResource("/com/application/fxml/Admin Set Course Menu.fxml"));
		Scene admSetCourseScene = new Scene(admSetCourseRoot);
		admSetCourseScene.getStylesheets().add(getClass().getResource("/com/application/css/setCourse.css").toExternalForm());
		Stage admSetCourseStage=(Stage)((Node)admSetCourseEvent.getSource()).getScene().getWindow();
		admSetCourseStage.setTitle("Set Course Menu");
		admSetCourseStage.setScene(admSetCourseScene);
		admSetCourseStage.show();
	}
}
