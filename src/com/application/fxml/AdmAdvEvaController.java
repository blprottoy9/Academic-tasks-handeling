package com.application.fxml;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdmAdvEvaController extends BackMenuController{
	double x=0;
	double y=0;
    @FXML
    private JFXButton btAdv;

    @FXML
    private JFXButton btEva;
    @FXML
    private BorderPane advMenu;

    @FXML
    private AnchorPane ancButton;


    @FXML
    private AnchorPane ancMenu;

    @FXML
    private HBox hBoxLabel;

    @FXML
    private AnchorPane ancBorderPane;

    @FXML
    private BorderPane rightAdv;

	@FXML
	private HBox hboxEva;
	@FXML
	private HBox hboxAdv;
	@FXML
	private void initialize(){

		hBoxLabel.setOpacity(0.9f);
		advMenu.setOpacity(0.9f);
		ancBorderPane.setOpacity(0.9f);
		ancButton.setOpacity(0.9f);
		ancMenu.setOpacity(0.9f);


	}
    @FXML
	void mouseDragged(MouseEvent dragEvent) {
		Node node=(Node)dragEvent.getSource();
		Stage admAdvEvaStage=(Stage)node.getScene().getWindow();
		admAdvEvaStage.setX(dragEvent.getScreenX()-x);
		admAdvEvaStage.setY(dragEvent.getScreenY()-y);
	}

	@FXML
	void mousePressed(MouseEvent pressedEvent) {
		x=pressedEvent.getSceneX();
		y=pressedEvent.getSceneY();
	}

	public void getRadioEnDsAdvMenu(ActionEvent radioEnDsAdvEvent) throws IOException {

		//minAdvising adminAdvising=new AdminAdvising();
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(getClass().getResource("Radio enable disable button.fxml"));

		hboxEva=loader.load();
		rightAdv.setCenter(hboxEva);

	}

	public void getRadioEnDsEvaMenu(ActionEvent radioEnDsEvaMenuEvent) throws IOException {

		//minAdvising adminAdvising=new AdminAdvising();
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(getClass().getResource("Radio en ds eva button.fxml"));

		hboxAdv=loader.load();
		rightAdv.setCenter(hboxAdv);

	}
}
