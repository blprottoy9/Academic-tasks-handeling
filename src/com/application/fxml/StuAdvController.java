package com.application.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.application.StudentAdvising;
import com.application.StudentMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StuAdvController{


	public void getStuAdvMenu(ActionEvent stuAdvConevent) throws IOException {
		StudentAdvising stuAdv=new  StudentAdvising();
		stuAdv.runStuAdvMenu(stuAdvConevent);


	}

}
