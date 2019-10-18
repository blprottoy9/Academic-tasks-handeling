package com.application;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ChangePasswordMenu {

	public void runChaPasswdMenu(Event chaPasswdEvent) throws IOException
	{

		Parent chaPasswdRoot=FXMLLoader.load(getClass().getResource("/com/application/fxml/Change Password Menu.fxml"));
		Scene chaPasswdScene = new Scene(chaPasswdRoot);
		chaPasswdScene.getStylesheets().add(getClass().getResource("/com/application/css/cp.css").toExternalForm());
		Stage chaPasswdStage=(Stage)((Node)chaPasswdEvent.getSource()).getScene().getWindow();
		chaPasswdStage.setTitle("Change Password Menu");
		chaPasswdStage.setScene(chaPasswdScene);
		chaPasswdStage.show();
	}
}
