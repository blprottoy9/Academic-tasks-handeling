package com.application.fxml;

import com.database.*;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class AdmCreateAccController extends BackMenuController{
	double x=0;
	double y=0;
	private String userId;

	String years="1st Year";
	ObservableList<String> deptList=FXCollections.observableArrayList("SE","CSE","IT");
	ObservableList<String> roleList=FXCollections.observableArrayList("Student","Lecturer","Ass Professor","Asoc Professor","Professor");
    @FXML
    private HBox hBoxLabel;

    @FXML
    private HBox hBoxName;

    @FXML
    private JFXTextField cAccName;

    @FXML
    private HBox hBoxId;

    @FXML
    private JFXTextField cAccId;

    @FXML
    private HBox hBoxDept;

    @FXML
    private JFXComboBox<String> cAccDept;

    @FXML
    private HBox hBoxRole;

    @FXML
    private JFXComboBox<String> cAccRole;

    @FXML
    private HBox hBoxBt;
	@FXML
	private void initialize(){
		hBoxLabel.setOpacity(0.9f);
		hBoxBt.setOpacity(0.9f);
		hBoxDept.setOpacity(0.9f);
		hBoxId.setOpacity(0.9f);
		hBoxName.setOpacity(0.9f);
		hBoxRole.setOpacity(0.9f);
		cAccDept.setValue("SE");
		cAccDept.setItems(deptList);
		cAccRole.setValue("Student");
		cAccRole.setItems(roleList);
	}
    @FXML
	void mouseDragged(MouseEvent dragEvent) {
		Node node=(Node)dragEvent.getSource();
		Stage admCreStage=(Stage)node.getScene().getWindow();
		admCreStage.setX(dragEvent.getScreenX()-x);
		admCreStage.setY(dragEvent.getScreenY()-y);
	}

	@FXML
	void mousePressed(MouseEvent pressedEvent) {
		x=pressedEvent.getSceneX();
		y=pressedEvent.getSceneY();
	}
	public void createAcc()
	{
		userId=null;
		DatabaseTable detTabObj=new DetailTable();
		detTabObj.connect();
		userId=cAccId.getText();
		String userName=cAccName.getText();
		String dept=(String) cAccDept.getValue();
		String role=(String) cAccRole.getValue();
		String queryCondition="Id='"+userId+"' AND Name= '"+userName+"'";
		String res=detTabObj.search( "Detail",queryCondition);



		detTabObj=new LoginTable();
		detTabObj.connect();
		queryCondition="ID='"+userId+"'";
		String res1=detTabObj.search( "Login",queryCondition);
		if(res=="true" && res1=="false")
		{
			String password="iit123";
			detTabObj=new LoginTable();
			detTabObj.connect();
			String queryValues="VALUES ('"+userId+"', '"+password+"')";
			//String queryValues="VALUES "+userId+", "+password+")";
			detTabObj.insert( queryValues);

			if(role.equals("Student"))
			{
				insertStudentInfoTable();
				insertStudentResTable();
			}
			else
			{
				insertFacultyInfoTable();
				insertFacultyRateTable();
			}
			setAlertSuccess();
		}
		else
		{
			setAlertFailed();
		}
	}
    private void insertStudentInfoTable()
    {

    	DatabaseTable detTabObj=new StudentInfoTable();
		detTabObj.connect();
		String queryValues="VALUES ('"+userId+"', '"+(String) cAccDept.getValue()+"', '"+years+"')";
		detTabObj.insert( queryValues);

    }
    private void insertFacultyInfoTable()
    {
    	DatabaseTable detTabObj=new FacultyInfoTable();
		detTabObj.connect();
		String queryValues="VALUES ('"+userId+"', '"+(String) cAccDept.getValue()+"')";
		detTabObj.insert( queryValues);

    }
    private void insertStudentResTable()
    {

    	float cGpa=0;
		int comCredits=0;
		DatabaseTable detTabObj=new StudentResTable();
		detTabObj.connect();
		String queryValues="VALUES ('"+userId+"', '"+cGpa+"', '"+comCredits+"')";
		detTabObj.insert( queryValues);
    }
    private void insertFacultyRateTable()
    {
		float rate=0;
		int count=0;
		DatabaseTable detTabObj=new FacultyRateTable();
		detTabObj.connect();
		String queryValues="VALUES ('"+userId+"', '"+rate+"', '"+count+"')";
		detTabObj.insert( queryValues);
    }
	@FXML
	public void setAlertFailed() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Failed");
		alert.setContentText("Create Account Not Possible");
		alert.showAndWait();
	}
	@FXML
	public void setAlertSuccess() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("SuccessFul");
		alert.setContentText("Insert Data Successful");
		alert.showAndWait();
	}

}
