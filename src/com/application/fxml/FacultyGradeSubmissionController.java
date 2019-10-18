package com.application.fxml;

import java.sql.SQLException;
import java.util.Date;

import com.database.*;
import com.detailclass.CourseDetails;
import com.detailclass.StuDetails;
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

public class FacultyGradeSubmissionController extends BackMenuController{
	double x=0;
	double y=0;
	private String facId;
	private String stuStatus;
	private float facStuRes;
	private float facStuPreviousGrade;
	private int facStuCredits;
	private String facCourseCredits;
	private String facStuId;
	private String year;
	private String facCourseId;
	private ObservableList<String> grdetList=FXCollections.observableArrayList("4.00","3.75","3.5","3.25","3.00","2.75","2.50","2.25","2.00","0.00");
    @FXML
    private SplitPane spiltPane;
	@FXML
    private TableView<CourseDetails> courseTableView;

    @FXML
    private TableColumn<CourseDetails, String> courseTableColumn;

    @FXML
    private TableView<StuDetails> stuIdTableView;

    @FXML
    private TableColumn<StuDetails, String> stuIdcokumn;

    @FXML
    private Button applyButton;
    @FXML
    private Button updateButton;

    @FXML
    private JFXComboBox<String> gradeChoiceBox;
    @FXML
    private VBox vBoxButtom;
    @FXML
    private VBox vBoxTop;

    @FXML
    private Button backButton;
    private ObservableList<CourseDetails>dataCourseId;
    private ObservableList<StuDetails>dataStuId;
    @FXML
	private void initialize(){

	    	vBoxTop.setOpacity(0.9f);
	    	vBoxButtom.setOpacity(0.9f);
	    	spiltPane.setOpacity(0.9f);
    		dataCourseId=FXCollections.observableArrayList();
    		getFacId();
			Date d=new Date();
		    year=Integer.toString(d.getYear()+1900);

		    DatabaseTable detTabObj=new FacultyCoursesTablr();
			detTabObj.connect();
			String queryCondition="Id='"+facId+"' AND Years= '"+year+"'";
			detTabObj.retrive(queryCondition);
			try {
				while(detTabObj.retriveResultset.next())
				{

					dataCourseId.add(new CourseDetails(detTabObj.retriveResultset.getString(2)));
				}

				}catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}

			detTabObj.closeDb();

			courseTableColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
			courseTableView.setItems(null);
			courseTableView.setItems(dataCourseId);
		}
    @FXML
	void mouseDragged(MouseEvent dragEvent) {
		Node node=(Node)dragEvent.getSource();
		Stage facGradeSubStage=(Stage)node.getScene().getWindow();
		facGradeSubStage.setX(dragEvent.getScreenX()-x);
		facGradeSubStage.setY(dragEvent.getScreenY()-y);
	}

	@FXML
	void mousePressed(MouseEvent pressedEvent) {
		x=pressedEvent.getSceneX();
		y=pressedEvent.getSceneY();
	}
    @FXML
    private void getSelectedCourseItem(MouseEvent mouseGradeEvent)
    {
    	CourseDetails courseDetailsObj=	courseTableView.getSelectionModel().getSelectedItem();
    	if(courseDetailsObj!=null)
    	{
    		String selectedCourseCode=courseDetailsObj.getCourseCode();
    		loadStuId(selectedCourseCode);
    	}
    }
    @FXML
    private void getSelectedStuItem(MouseEvent mouseStuIdEvent)
    {
    	StuDetails stuDetailsObj=	stuIdTableView.getSelectionModel().getSelectedItem();
    	if(stuDetailsObj!=null)
    	{
    		String grade="0.00";
    		gradeChoiceBox.setValue(grade);
    		gradeChoiceBox.setItems(grdetList);
    		facStuId=stuDetailsObj.getStuId();

    		//loadStuId(selectedCourseCode);
    	}
    }
    public void loadStuId(String courseId){
    	dataStuId=FXCollections.observableArrayList();
    	facCourseId=courseId;
	    DatabaseTable detTabObj=new StudentCoursesTable();
		detTabObj.connect();
		String queryCondition="Course_ID='"+courseId+"' AND Years= '"+year+"'";
		detTabObj.retrive(queryCondition);
		try {
			while(detTabObj.retriveResultset.next())
			{

				dataStuId.add(new StuDetails(detTabObj.retriveResultset.getString(1)));
			}

			}catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		detTabObj.closeDb();

		stuIdcokumn.setCellValueFactory(new PropertyValueFactory<>("stuId"));
		stuIdTableView.setItems(null);
		stuIdTableView.setItems(dataStuId);
    }
    private void getFacId()
    {
		DatabaseTable detTabObj=new LogTable();
		detTabObj.connect();
		detTabObj.retrive(null);
		facId=detTabObj.retriveString;
		detTabObj.closeDb();
    }
    public void pushData(ActionEvent insertEvent)
    {
    	String givenGrade=(String)gradeChoiceBox.getValue();

    	if(getStatusMarking().equals("Not Complete"))
    	{
    		updateStuCourses(Float.valueOf(givenGrade.trim()).floatValue());
    		getStuGpa();
    		getCourseCredits();
    		calculateGpa(Float.valueOf(givenGrade.trim()).floatValue());

    		//updateStudentInfo();
    		updateMarkingStatus();
    		//updateFacEva();
    		setAlertSuccess();

    	}

    	else
    	{
    		setAlertFailed();
    	}

    }
    public void updateData(ActionEvent updateEvent)
    {
    	String givenGrade=(String)gradeChoiceBox.getValue();

    	if(getStatusMarking().equals("Complete"))
    	{
    		getStuGpa();
    		getCourseCredits();
    		getStuCoursesInfo();
    		float res=(facStuRes*facStuCredits-facStuPreviousGrade*Float.valueOf(facCourseCredits.trim()).floatValue());
    		//System.out.println(res);
    		res=res+Float.valueOf(givenGrade.trim()).floatValue()*Float.valueOf(facCourseCredits.trim()).floatValue();
    		//System.out.println(res);
    		res=res/facStuCredits;
    		System.out.println(res);
    		int totalCredits=facStuCredits;
    		updateStuCourses(Float.valueOf(givenGrade.trim()).floatValue());
    		updateStuGpa(res,totalCredits);
    		setAlertUpdateSuccess();

    	}

    	else
    	{
    		setAlertUpadateFailed();
    	}




    }

    private void updateFacEva()
    {
    	int facTotalCount=0;
    	DatabaseTable detTabObj=new FacultyRateTable();
		detTabObj.connect();
		String queryAttributeValues="Count1='"+facTotalCount+"'";
		String queryCondition="ID='"+facId+"'";
		detTabObj.update(queryAttributeValues,queryCondition);
    }
    private void updateStuGpa(float gpa,int credits)
    {
    	System.out.println(gpa);
		System.out.println(credits);
    	DatabaseTable detTabObj=new StudentResTable();
		detTabObj.connect();
		String queryAttributeValues="CGPA='"+gpa+"', Complete_Credits='"+credits+"'";
		String queryCondition="ID='"+facStuId+"'";
		detTabObj.update(queryAttributeValues,queryCondition);
    }

    private void updateStuCourses(float grade)
    {
    	DatabaseTable detTabObj=new StudentCoursesTable();
		detTabObj.connect();
		String queryAttributeValues="Grade='"+grade+"'";
		String queryCondition="ID='"+facStuId+"' AND Course_ID='"+facCourseId+"' AND Years='"+year+"'";
		detTabObj.update(queryAttributeValues,queryCondition);
    }
    private void updateMarkingStatus()
    {
    	String markingStatus="Complete";
    	DatabaseTable detTabObj=new FacultyMarkingStatusTable();
		detTabObj.connect();
		String queryAttributeValues="Status='"+markingStatus+"'";
		String queryCondition="ID='"+facStuId+"' AND Course_ID='"+facCourseId+"' AND Years='"+year+"'";
		detTabObj.update(queryAttributeValues,queryCondition);
    }
    private void getStatusStu()
    {
    	DatabaseTable detTabObj=new StudentInfoTable();
		detTabObj.connect();
		String queryCondition="ID='"+facStuId+"'";
		detTabObj.retrive(queryCondition);
		try {
			while(detTabObj.retriveResultset.next())
			{
				stuStatus=detTabObj.retriveResultset.getString(3);

			}
		}catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		detTabObj.closeDb();
    }
    private void getStuCoursesInfo()
    {
    	DatabaseTable detTabObj=new StudentCoursesTable();
		detTabObj.connect();
		String queryCondition="ID='"+facStuId+"' AND Course_ID='"+facCourseId+"' AND Years='"+year+"'";
		detTabObj.retrive(queryCondition);
		try {
			while(detTabObj.retriveResultset.next())
			{
				facStuPreviousGrade=detTabObj.retriveResultset.getFloat(4);


			}
		}catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		detTabObj.closeDb();
    }
    private void getStuGpa()
    {
    	DatabaseTable detTabObj=new StudentResTable();
		detTabObj.connect();
		String queryCondition="ID='"+facStuId+"'";
		detTabObj.retrive(queryCondition);
		try {
			while(detTabObj.retriveResultset.next())
			{
				facStuRes=detTabObj.retriveResultset.getFloat(2);
				facStuCredits=detTabObj.retriveResultset.getInt(3);

			}
		}catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		detTabObj.closeDb();
    }
    private void getCourseCredits()
    {
    	DatabaseTable detTabObj=new CourseTable();
		detTabObj.connect();
		String queryCondition="Course_ID='"+facCourseId+"'";
		detTabObj.retrive(queryCondition);
		try {
			while(detTabObj.retriveResultset.next())
			{

				facCourseCredits=detTabObj.retriveResultset.getString(4);

			}
		}catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		detTabObj.closeDb();
    }
    private String getStatusMarking()
    {
    	DatabaseTable detTabObj=new FacultyMarkingStatusTable();
    	String condition="null";
		detTabObj.connect();
		String queryCondition="ID='"+facStuId+"' AND Course_ID='"+facCourseId+"' AND Years='"+year+"'";
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
    @FXML
	public void setAlertFailed() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Failed");
		alert.setContentText("Marking is already Done");
		alert.showAndWait();
	}
    @FXML
	public void setAlertUpadateFailed() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Failed");
		alert.setContentText("Marking is not Done");
		alert.showAndWait();
	}
    @FXML
	public void setAlertSuccess() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Successful");
		alert.setContentText("Insert is done. Thank you.");
		alert.showAndWait();
	}
    @FXML
   	public void setAlertUpdateSuccess() {
   		Alert alert=new Alert(Alert.AlertType.INFORMATION);
   		alert.setTitle("Successful");
   		alert.setContentText("Update is done. Thank you.");
   		alert.showAndWait();
   	}
	public void calculateGpa(float grade)
	{
		float res=(grade*Float.valueOf(facCourseCredits.trim()).floatValue()+facStuRes*facStuCredits)/(facStuCredits+Float.valueOf(facCourseCredits.trim()).floatValue());
		int totalCredits=facStuCredits+Integer.parseInt(facCourseCredits);
		//System.out.println(res);
		//System.out.println(totalCredits);
		updateStuGpa(res,totalCredits);
	}
}
