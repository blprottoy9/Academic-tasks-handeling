package com.application;

import java.io.IOException;


import com.application.fxml.StuController;


import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentMenu{

	private String uId;
	public void getId(String uId)
	{
		this.uId=uId;
	}
	public void runStuMenu(Event stuEvent) throws IOException
	{

		FXMLLoader loader = new FXMLLoader();
		Parent stuRoot=loader.load(getClass().getResource("/com/application/fxml/Stdent Menu.fxml").openStream());
		StuController controller=(StuController)loader.getController();
		controller.setLabel("Student: "+uId);
		Scene stScene = new Scene(stuRoot);
		stScene.getStylesheets().add(getClass().getResource("/com/application/css/stu.css").toExternalForm());

		Stage stuStage=(Stage)((Node)stuEvent.getSource()).getScene().getWindow();
		stuStage.setTitle("Student Menu");
		stuStage.setScene(stScene);
		stuStage.show();
	}
}
