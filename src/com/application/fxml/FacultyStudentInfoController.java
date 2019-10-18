package com.application.fxml;


import java.sql.SQLException;

import com.database.*;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FacultyStudentInfoController extends BackMenuController{
	double x=0;
	double y=0;
	private String stuId;
	private String stuName;
	private String stuDept;
	private String stuContractNo;
	private String stuYear;
	private float stuCgpa;
	private int stuCredits;
    @FXML
    private VBox vBoxTop;

    @FXML
    private VBox vBoxButtom;
    @FXML
    private JFXTextField idStuInfoTextField;

    @FXML
    private Button searchIdStuInfoButton;

    @FXML
    private TextArea stuInfoTextArea;

    @FXML
    private Button backMenuStuInfoButton;
    @FXML
    private VBox vBoxCenter;
    @FXML
	private void initialize(){
    	vBoxTop.setOpacity(0.9f);
    	vBoxButtom.setOpacity(0.9f);
    	vBoxCenter.setOpacity(0.9f);
    }
    @FXML
	void mouseDragged(MouseEvent dragEvent) {
		Node node=(Node)dragEvent.getSource();
		Stage admSetCourseStage=(Stage)node.getScene().getWindow();
		admSetCourseStage.setX(dragEvent.getScreenX()-x);
		admSetCourseStage.setY(dragEvent.getScreenY()-y);
	}

	@FXML
	void mousePressed(MouseEvent pressedEvent) {
		x=pressedEvent.getSceneX();
		y=pressedEvent.getSceneY();
	}
    public void searchFacStuInfo(ActionEvent stuInfoConEvent)
    {
    	stuId=idStuInfoTextField.getText();
    	if(stuId!=null)
    	{
    		DatabaseTable detTabObj=new StudentInfoTable();
    		detTabObj.connect();
    		String queryCondition="ID='"+stuId+"'";
    		String res=detTabObj.search( "Student_Info",queryCondition);
    		if(res=="true")
    		{
    			detTabObj=new DetailTable();
        		detTabObj.connect();
        		detTabObj.retrive(queryCondition);
        		stuName=detTabObj.retriveString2;
        		stuContractNo=detTabObj.retriveString3;

        		detTabObj=new StudentInfoTable();
        		detTabObj.connect();
        		detTabObj.retrive(queryCondition);
        		stuDept=detTabObj.retriveString;
        		stuYear=detTabObj.retriveString;

        		detTabObj=new StudentResTable();
        		detTabObj.connect();
        		detTabObj.retrive(queryCondition);
        		try {
					while(detTabObj.retriveResultset.next())
					{
						stuCgpa=detTabObj.retriveResultset.getFloat(2);
						stuCredits=detTabObj.retriveResultset.getInt(3);
					}
					detTabObj.closeDb();
				} catch (SQLException sqlException) {
					// TODO Auto-generated catch block
					sqlException.printStackTrace();
				}
        		stuInfoTextArea.setText("Student Name: "+stuName+"\nStudent Id: "+stuId+"\nStudent Dept: "+stuDept+"\nStudent Contract No: "+stuContractNo+"\nCGPA: "+stuCgpa+"\nComplete Credits: "+stuCredits);

    		}
    		else
    		{
    			setAlertIdNotFound();
    		}
    	}
    	else
    	{
    		setAlertDataNotFound();
    	}
    }

	@FXML
	public void setAlertDataNotFound() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Failed");
		alert.setContentText("Please Give Necessary Info");
		alert.showAndWait();

	}

	@FXML
	public void setAlertIdNotFound() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Failed");
		alert.setContentText("Invalid Id");
		alert.showAndWait();
	}
}
