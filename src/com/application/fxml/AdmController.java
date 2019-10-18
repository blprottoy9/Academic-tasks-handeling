package com.application.fxml;


import java.io.IOException;

import com.application.*;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AdmController extends MainController{
	double x=0;
	double y=0;
    @FXML
    private Label admUid;
    @FXML
    private HBox hBoxLabel;

    @FXML
    private HBox hBoxCA;

    @FXML
    private HBox hBoxSC;

    @FXML
    private HBox hBoxAdv;
    @FXML
    private HBox hBoxCP;
    @FXML
    private HBox hBoxLogout;
    @FXML
    private Button cAcc;
    @FXML
    private Button admSetCouButton;
    @FXML
    private Button adv;
    @FXML
    private Button chaPwd;
    @FXML
    private Button admLogout;

	@FXML
	private void initialize(){

		hBoxLabel.setOpacity(0.9f);
		hBoxCA.setOpacity(0.9f);
		hBoxSC.setOpacity(0.9f);
		hBoxAdv.setOpacity(0.9f);
		hBoxCP.setOpacity(0.9f);
		hBoxLogout.setOpacity(0.9f);

	}
    @FXML
	void mouseDragged(MouseEvent dragEvent) {
		Node node=(Node)dragEvent.getSource();
		Stage admStage=(Stage)node.getScene().getWindow();
		admStage.setX(dragEvent.getScreenX()-x);
		admStage.setY(dragEvent.getScreenY()-y);
	}

	@FXML
	void mousePressed(MouseEvent pressedEvent) {
		x=pressedEvent.getSceneX();
		y=pressedEvent.getSceneY();
	}
	public void getCreateAccMenu(ActionEvent createAccConEvent) throws IOException {


		CreateAccMenu createAcc=new  CreateAccMenu();
		createAcc.runCreateAccMenu(createAccConEvent);


	}
	public void setLabel(String uId)
	{
		admUid.setText(uId);

	}
	public void getAdmAdvMenu(ActionEvent admAdvConEvent) throws IOException {
		AdminAdvising admAdv=new  AdminAdvising();
		admAdv.runAdmAdvMenu(admAdvConEvent);


	}
	public void getAdminSetCourseMenu(ActionEvent admSetCourseConEvent) throws IOException {
		AdminSetCourseMenu admSetCourse=new  AdminSetCourseMenu();
		admSetCourse.runAdminSetCourseMenu(admSetCourseConEvent);


	}

}
