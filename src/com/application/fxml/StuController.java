package com.application.fxml;

import java.io.IOException;

import com.application.ChangePasswordMenu;
import com.application.StudentAdvising;
import com.application.StudentFacEvaluation;
import com.application.StudentResult;
import com.application.StudentRoutine;
import com.database.*;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StuController extends MainController{
	double x=0;
	double y=0;
    @FXML
    private HBox hBoxLabel;
    @FXML
    private Label labelStuId;

    @FXML
    private HBox hBoxAdv;

    @FXML
    private Button stuCouAdv;

    @FXML
    private HBox hBoXEva;

    @FXML
    private Button stuFacEva;

    @FXML
    private HBox hBoxRou;

    @FXML
    private HBox hBoxSR;

    @FXML
    private Button stuRes;

    @FXML
    private HBox hBoxCP;

    @FXML
    private Button stuCouPasswd;

    @FXML
    private HBox hBoxLogOut;

    @FXML
    private Button stuLogout;

	public void setLabel(String uId)
	{
		labelStuId.setText(uId);
	}
	@FXML
	private void initialize(){

		hBoxLabel.setOpacity(0.9f);
		hBoxAdv.setOpacity(0.9f);
		hBoxCP.setOpacity(0.9f);
		hBoXEva.setOpacity(0.9f);
		hBoxLogOut.setOpacity(0.9f);
		hBoxRou.setOpacity(0.9f);
		hBoxSR.setOpacity(0.9f);
		

	}
    @FXML
	void mouseDragged(MouseEvent dragEvent) {
		Node node=(Node)dragEvent.getSource();
		Stage stuStage=(Stage)node.getScene().getWindow();
		stuStage.setX(dragEvent.getScreenX()-x);
		stuStage.setY(dragEvent.getScreenY()-y);
	}

	@FXML
	void mousePressed(MouseEvent pressedEvent) {
		x=pressedEvent.getSceneX();
		y=pressedEvent.getSceneY();
	}
	public void getStuEvaMenu(ActionEvent stuEvaConEvent) throws IOException {
		if(checkEvaStatus()=="true")
		{
			setAlertEvaDisable();
		}
		else
		{
			StudentFacEvaluation stuEva=new  StudentFacEvaluation();
			stuEva.runStuEvaMenu(stuEvaConEvent);
		}

	}


	public void getStuAdvMenu(ActionEvent stuAdvConEvent) throws IOException {
		if(checkAdvStatus()=="true")
		{
			setAlertAdvDisable();
		}
		else
		{
			StudentAdvising stuAdv=new  StudentAdvising();
			stuAdv.runStuAdvMenu(stuAdvConEvent);
		}


	}

	public void getStuResMenu(ActionEvent stuResConEvent) throws IOException {
		StudentResult stuRes=new  StudentResult();
		stuRes.runStuResMenu(stuResConEvent);


	}
	public void getStuRtnMenu(ActionEvent stuRtnConEvent) throws IOException {
		StudentRoutine stuRtn=new  StudentRoutine();
		stuRtn.runStuRtnMenu(stuRtnConEvent);


	}
	public String checkEvaStatus()
	{
		DatabaseTable detTabObj=new AdvisingFacultyEvaluationStatusTable();
		String itemId="eva";
		String status="disable";
		detTabObj.connect();
		String queryCondition="Item='"+itemId+"' AND status= '"+status+"'";
		String res=detTabObj.search( "advising_and_faculty_evaluation_status",queryCondition);
		return res;

	}
	public String checkAdvStatus()
	{
		DatabaseTable detTabObj=new AdvisingFacultyEvaluationStatusTable();
		String itemId="adv";
		String status="disable";
		detTabObj.connect();
		String queryCondition="Item='"+itemId+"' AND status= '"+status+"'";
		String res=detTabObj.search( "advising_and_faculty_evaluation_status",queryCondition);
		return res;

	}
	@FXML
	public void setAlertEvaDisable() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Faculty Evaluation Disable");
		alert.setContentText("Faculty Evaluation Disable");
		alert.showAndWait();
	}
	@FXML
	public void setAlertAdvDisable() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Advising Disable");
		alert.setContentText("Advising Disable");
		alert.showAndWait();
	}
}

