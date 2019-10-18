package com.application.fxml;

import java.io.IOException;

import com.application.AdminMenu;
import com.application.ChangePasswordMenu;
import com.application.FacultyMenu;
import com.application.StudentMenu;
import com.database.*;
import com.jfoenix.controls.JFXPasswordField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ChangePasswordController extends BackMenuController{
	double x=0;
	double y=0;
    @FXML
    private HBox hBoxLabel;

    @FXML
    private HBox hBoxOP;

    @FXML
    private JFXPasswordField oldPasswordTextField;

    @FXML
    private HBox hBoxNP;

    @FXML
    private JFXPasswordField newPasswordTextField;

    @FXML
    private HBox hBoxCP;

    @FXML
    private JFXPasswordField confirmNewPasswordTextField;

    @FXML
    private HBox hBoxBt;

	private String id;
	private String status;
	@FXML
	private void initialize(){
		hBoxLabel.setOpacity(0.9f);
		hBoxBt.setOpacity(0.9f);
		hBoxNP.setOpacity(0.9f);
		hBoxOP.setOpacity(0.9f);
		hBoxCP.setOpacity(0.9f);


		DatabaseTable detTabObj=new LogTable();
		detTabObj.connect();

		detTabObj.retrive(null);
		id=detTabObj.retriveString;
		status=detTabObj.retriveString2;
	}
    @FXML
	void mouseDragged(MouseEvent dragEvent) {
		Node node=(Node)dragEvent.getSource();
		Stage admCPStage=(Stage)node.getScene().getWindow();
		admCPStage.setX(dragEvent.getScreenX()-x);
		admCPStage.setY(dragEvent.getScreenY()-y);
	}

	@FXML
	void mousePressed(MouseEvent pressedEvent) {
		x=pressedEvent.getSceneX();
		y=pressedEvent.getSceneY();
	}
	public void changeUserPassword(ActionEvent changeUserConEvent) throws IOException {
		initialize();
		DatabaseTable detTabObj=new LoginTable();
		detTabObj.connect();
		String queryCondition="ID= '"+id+"'";
		String res=detTabObj.search( "login",queryCondition);
		System.out.println(res);
		System.out.println(id);
		if(res=="true")
		{
			detTabObj=new LoginTable();
			detTabObj.connect();
			queryCondition="Id='"+id+"'";
			detTabObj.retrive(queryCondition);
			String password=detTabObj.retriveString;
			if(password.equals(oldPasswordTextField.getText()))
			{

				if(newPasswordTextField.getText().equals(confirmNewPasswordTextField.getText()))
				{
					detTabObj=new LoginTable();
					detTabObj.connect();
					queryCondition="ID='"+id+"' AND PASSWORD1= '"+oldPasswordTextField.getText()+"'";
					detTabObj.update(newPasswordTextField.getText(),queryCondition);
					setAlertSuccesss();
					System.out.println("Id:"+id+" status:"+status);
					if(status.equals("admin"))
					{
						AdminMenu adm=new AdminMenu();
						adm.runAdmMenu(changeUserConEvent);
					}
					else if(status.equals("Student"))
					{

						StudentMenu stu=new StudentMenu();
						stu.getId(id);
						stu.runStuMenu(changeUserConEvent);
					}
					else
					{
						FacultyMenu fac=new FacultyMenu();
						fac.getId(status+": "+id);
						fac.runFacultyMenu(changeUserConEvent);
					}
				}
				else{
					setAlertMismatch();
				}
			}
			else
			{
				setAlertOldPasswordNotFound();
			}
		}
		else{
			setAlertUserNotFound();
		}
	}

	@FXML
	public void setAlertOldPasswordNotFound() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Failed");
		alert.setContentText("Wrong old Password");
		alert.showAndWait();
	}
	@FXML
	public void setAlertSuccesss() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Successful");
		alert.setContentText("Password change Successful");
		alert.showAndWait();
	}
	@FXML
	public void setAlertUserNotFound() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Failed");
		alert.setContentText("User Not Found");
		alert.showAndWait();
	}
	@FXML
	public void setAlertMismatch() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Failed");
		alert.setContentText("New Password Mismatch");
		alert.showAndWait();
	}

}
