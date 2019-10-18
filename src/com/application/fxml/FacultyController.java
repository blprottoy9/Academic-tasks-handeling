package com.application.fxml;

import java.io.IOException;

import com.application.FacultyGradeSubMenu;
import com.application.FacultyRoutineMenu;
import com.application.FacultyStudentInfoMenu;
import com.application.StudentAdvising;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FacultyController extends MainController {
	double x=0;
	double y=0;
    @FXML
    private HBox hBoxLabel;

    @FXML
    private Label facUid;

    @FXML
    private HBox hBoxRoutine;

    @FXML
    private Button facRou;

    @FXML
    private HBox hBoxGrade;

    @FXML
    private Button facSubGrade;

    @FXML
    private HBox hBoxStu;

    @FXML
    private HBox hBoxCP;

    @FXML
    private Button facChaPass;

    @FXML
    private HBox hBoxLogOut;

    @FXML
    private Button facLogout;
	@FXML
	private void initialize(){

		hBoxLabel.setOpacity(0.9f);
		hBoxCP.setOpacity(0.9f);
		hBoxGrade.setOpacity(0.9f);
		hBoxLogOut.setOpacity(0.9f);
		hBoxRoutine.setOpacity(0.9f);
		hBoxStu.setOpacity(0.9f);


	}
    @FXML
	void mouseDragged(MouseEvent dragEvent) {
		Node node=(Node)dragEvent.getSource();
		Stage facStage=(Stage)node.getScene().getWindow();
		facStage.setX(dragEvent.getScreenX()-x);
		facStage.setY(dragEvent.getScreenY()-y);
	}

	@FXML
	void mousePressed(MouseEvent pressedEvent) {
		x=pressedEvent.getSceneX();
		y=pressedEvent.getSceneY();
	}
	public void getFacultyRoutineMenu(ActionEvent facultyRouConEvent) throws IOException {
		FacultyRoutineMenu facultyRou=new  FacultyRoutineMenu();
		facultyRou.runFacultyRoutineMenu(facultyRouConEvent);


	}
	public void setLabel(String uId)
	{
		facUid.setText(uId);

	}
	public void getFacultyStudentInfoMenu(ActionEvent facultyStuInfoConEvent) throws IOException {
		FacultyStudentInfoMenu facultyStuInfo=new  FacultyStudentInfoMenu();
		facultyStuInfo.runFacultyStudentInfoMenu(facultyStuInfoConEvent);


	}
	public void getFacultyGradeSubMenu(ActionEvent facultyGradeSubConEvent) throws IOException {
		FacultyGradeSubMenu facultyGradeSub=new  FacultyGradeSubMenu();
		facultyGradeSub.runFacultyGradeSubMenu(facultyGradeSubConEvent);


	}
}
