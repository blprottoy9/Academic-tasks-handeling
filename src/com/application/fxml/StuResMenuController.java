package com.application.fxml;


import java.sql.SQLException;

import com.database.*;
import com.detailclass.CourseDetails;
import com.detailclass.StuResultDetails;
import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StuResMenuController extends BackMenuController{
	double x=0;
	double y=0;
	private ObservableList<String> coursetList=FXCollections.observableArrayList();;
	private String stuCredits;
	private String stuCourseId;
	private String stuId;
    @FXML
    private VBox vBocCenter;
    @FXML
    private VBox vBoxButtom;
    @FXML
    private VBox vBoxTop;
    @FXML
    private TableView<StuResultDetails> stuResTableView;

    @FXML
    private TableColumn<StuResultDetails, String> stuResCourseCode;

    @FXML
    private TableColumn<StuResultDetails, String> stuResCgpa;
    @FXML
    private TableColumn<StuResultDetails, String> stuResCredits;

    @FXML
    private JFXComboBox<String> stuResChoiceBox;

    @FXML
    private Button stuResApplyButton;

    @FXML
    private Button stuResBackButton;
    @FXML
    private ObservableList<StuResultDetails>data;
    @FXML
   	private void initialize(){
    	vBoxTop.setOpacity(0.9f);
    	vBoxButtom.setOpacity(0.9f);
    	//vBocCenter.setOpacity(0.9f);
    	DatabaseTable detTabObj=new LogTable();
    	detTabObj.connect();
    	detTabObj.retrive(null);
		stuId=detTabObj.retriveString;


    	detTabObj=new StudentCoursesTable();
		detTabObj.connect();
		String queryCondition="ID='"+stuId+"'";
		detTabObj.retrive(queryCondition);
		try {
			while(detTabObj.retriveResultset.next())
			{
				//System.out.print("d");
				if(detTabObj.retriveResultset.getFloat(4)!=0 &&detTabObj.retriveResultset.getFloat(4)!=-1)
				{
					coursetList.add(detTabObj.retriveResultset.getString(2));
				}
				else if(detTabObj.retriveResultset.getFloat(4)==0&& getStatusMarking(detTabObj.retriveResultset.getString(2),detTabObj.retriveResultset.getString(3)).equals("Complete"))
				{
					coursetList.add(detTabObj.retriveResultset.getString(2));
				}
			}
		} catch (SQLException sqlException) {
			// TODO Auto-generated catch block
			sqlException.printStackTrace();
		}
		detTabObj.closeDb();
		coursetList.add("Total");
		stuResChoiceBox.setValue("Total");
		stuResChoiceBox.setItems(coursetList);
    }
    @FXML
	void mouseDragged(MouseEvent dragEvent) {
		Node node=(Node)dragEvent.getSource();
		Stage stuResStage=(Stage)node.getScene().getWindow();
		stuResStage.setX(dragEvent.getScreenX()-x);
		stuResStage.setY(dragEvent.getScreenY()-y);
	}

	@FXML
	void mousePressed(MouseEvent pressedEvent) {
		x=pressedEvent.getSceneX();
		y=pressedEvent.getSceneY();
	}
    public void getStuRes(ActionEvent stuResEvent)
    {
    	data=FXCollections.observableArrayList();
    	if((String)stuResChoiceBox.getValue()=="Total")
    	{
    		getStuTotalResult();
    	}
    	else
    	{
    		getStuCourseResult();
    	}
    }
    public void getStuCourseResult()
    {
    	stuCourseId=(String)stuResChoiceBox.getValue();
    	System.out.println(stuCourseId);
    	DatabaseTable detTabObj=new CourseTable();
    	detTabObj.connect();
    	String queryCondition="Course_ID='"+stuCourseId+"'";
    	detTabObj.retrive(queryCondition);

    	try {

			while(detTabObj.retriveResultset.next())
			{
				stuCredits=Integer.toString(detTabObj.retriveResultset.getInt(4));
			}
		} catch (SQLException sqlException) {
			// TODO Auto-generated catch block
			sqlException.printStackTrace();
		}
    	detTabObj.closeDb();
    	detTabObj=new StudentCoursesTable();
    	detTabObj.connect();
    	queryCondition="ID='"+stuId+"' AND Course_ID='"+stuCourseId+"'";
		detTabObj.retrive(queryCondition);

		try {
			while(detTabObj.retriveResultset.next())
			{
				data.add(new StuResultDetails(stuCourseId,Float.toString(detTabObj.retriveResultset.getFloat(4)),stuCredits));
			}
		} catch (SQLException sqlException) {
			// TODO Auto-generated catch block
			sqlException.printStackTrace();
		}
		detTabObj.closeDb();
		stuResCourseCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
		stuResCgpa.setCellValueFactory(new PropertyValueFactory<>("gpa"));
		stuResCredits.setCellValueFactory(new PropertyValueFactory<>("credits"));
		stuResTableView.setItems(null);
		stuResTableView.setItems(data);
    }
    private String getStatusMarking(String courseId,String year)
    {
    	DatabaseTable detTabObj=new FacultyMarkingStatusTable();
    	String condition="null";
		detTabObj.connect();
		String queryCondition="ID='"+stuId+"' AND Course_ID='"+courseId+"' AND Years='"+year+"'";
		detTabObj.retrive(queryCondition);
		try {
			while(detTabObj.retriveResultset.next())
			{
				 condition=detTabObj.retriveResultset.getString(4);

			}
		}catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		detTabObj.closeDb();
		 return condition;
    }
    public void getStuTotalResult()
    {
    	 Object[] courseIds;
    	 courseIds = (Object[])coursetList.toArray();
    	 DatabaseTable detTabObj;
    	 String queryCondition;
    	 for(int index=0;index<courseIds.length;index++)
    	 {
    		 	stuCourseId=courseIds[index].toString();
    		  	detTabObj=new CourseTable();
    	    	detTabObj.connect();
    	    	queryCondition="Course_ID='"+stuCourseId+"'";
    	    	detTabObj.retrive(queryCondition);

    	    	try {

    				while(detTabObj.retriveResultset.next())
    				{
    					stuCredits=Integer.toString(detTabObj.retriveResultset.getInt(4));
    				}
    			} catch (SQLException sqlException) {
    				// TODO Auto-generated catch block
    				sqlException.printStackTrace();
    			}
    	    	detTabObj.closeDb();
    	    	detTabObj=new StudentCoursesTable();
    	    	detTabObj.connect();
    	    	queryCondition="ID='"+stuId+"' AND Course_ID='"+stuCourseId+"'";
    			detTabObj.retrive(queryCondition);

    			try {
    				while(detTabObj.retriveResultset.next())
    				{
    					data.add(new StuResultDetails(stuCourseId,Float.toString(detTabObj.retriveResultset.getFloat(4)),stuCredits));
    				}
    			} catch (SQLException sqlException) {
    				// TODO Auto-generated catch block
    				sqlException.printStackTrace();
    			}
    			detTabObj.closeDb();
    	 }

 		detTabObj=new StudentResTable();
 		detTabObj.connect();
 		queryCondition="ID='"+stuId+"'";
 		detTabObj.retrive(queryCondition);
 		try {
				while(detTabObj.retriveResultset.next())
				{
					data.add(new StuResultDetails("Total:",Float.toString(detTabObj.retriveResultset.getFloat(2)),Integer.toString(detTabObj.retriveResultset.getInt(3))));

				}
				detTabObj.closeDb();
			} catch (SQLException sqlException) {
				// TODO Auto-generated catch block
				sqlException.printStackTrace();
			}
    	stuResCourseCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
 		stuResCgpa.setCellValueFactory(new PropertyValueFactory<>("gpa"));
 		stuResCredits.setCellValueFactory(new PropertyValueFactory<>("credits"));
 		stuResTableView.setItems(null);
 		stuResTableView.setItems(data);
    	//System.out.print(courseIds[0]);
    }
}
