package com.application;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Login extends Application{
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root= FXMLLoader.load(getClass().getResource("/com/application/fxml/LoginMenu.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/application/css/login.css").toExternalForm());

			//primaryStage.setTitle("Login Menu");
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void run() {
		launch();
	}
	public void runLoginMenu(Event loginEvent) throws IOException
	{
		try {
			Parent loginRoot=FXMLLoader.load(getClass().getResource("/com/application/fxml/LoginMenu.fxml"));
			Scene loginScene = new Scene(loginRoot);
			loginScene.getStylesheets().add(getClass().getResource("/com/application/css/login.css").toExternalForm());
			Stage loginStage=(Stage)((Node)loginEvent.getSource()).getScene().getWindow();
			//loginStage.setTitle("Login Menu");

			loginStage.setScene(loginScene);
			//loginStage.initStyle(StageStyle.TRANSPARENT);
			loginStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
