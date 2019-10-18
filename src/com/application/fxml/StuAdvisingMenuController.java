package com.application.fxml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.database.CourseScheduleTable;
import com.database.CourseTable;
import com.database.DatabaseTable;
import com.database.FacultyCoursesTablr;
import com.database.FacultyEvaStatusTable;
import com.database.FacultyMarkingStatusTable;
import com.database.FacultyRateTable;
import com.database.LogTable;
import com.database.StudentCoursesTable;
import com.database.StudentResTable;
import com.detailclass.CourseDetails;
import com.detailclass.StuDetails;
import com.detailclass.StuRoutineDetails;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;

public class StuAdvisingMenuController extends BackMenuController{
		private int stuYear;
		private String year;
		private String stuId;
		private String selectedCourse;
		private String selectedDropCourse;
		private float stuRes;
		private int stuCredits;
		private String stuCourseCredits;
		double x=0;
		double y=0;
		private String[] weekDays={"Sun","Mon","Tues","Wed","Thrus"};
	    @FXML
	    private SplitPane splitPane;
	    @FXML
	    private VBox vBoxButtom;
	    @FXML
	    private VBox vBoxTop;

		@FXML
	    private TableView<CourseDetails> courseAddRetakeTableView;

	    @FXML
	    private TableColumn<CourseDetails,String> courseColumn;

	    @FXML
	    private Button addButton;

	    @FXML
	    private Button dropButton;

	    @FXML
	    private TableView<CourseDetails> courseDropTableview;

	    @FXML
	    private TableColumn<CourseDetails, String> courseDropColumn;

	    @FXML
	    private TableView<StuRoutineDetails> timeTableview;

	    @FXML
	    private TableColumn<StuRoutineDetails, String> weekDayColumn;

	    @FXML
	    private TableColumn<StuRoutineDetails, String> timeColumn;

	    @FXML
	    private Button backButton;
	    private ObservableList<CourseDetails>dataCourseIdAddRetake;
	    private ObservableList<CourseDetails>dataCourseIdDrop;;
	    private ObservableList<StuRoutineDetails>dataCourseIdtime;
	    @FXML
		private void initialize(){
	    		vBoxTop.setOpacity(0.9f);
	    		vBoxButtom.setOpacity(0.9f);
	    		splitPane.setOpacity(0.9f);
	    		dataCourseIdAddRetake=FXCollections.observableArrayList();
	    		dataCourseIdDrop=FXCollections.observableArrayList();
	    		dataCourseIdtime=FXCollections.observableArrayList();
	    		getStuId();
				Date d=new Date();
			    year=Integer.toString(d.getYear()+1901);
			    int month=d.getMonth();
			    if(month==1)
			    {

			    	stuYear=Integer.parseInt(year)-(Integer.parseInt(stuId.substring(0, 2))+2000);
		    		System.out.println(stuYear);


			    }
			    else
			    {
			    	stuYear=Integer.parseInt(year)-(Integer.parseInt(stuId.substring(0, 2))+2000)+1;
			    	System.out.println(stuYear);


			    }
			    setAddTable();
			    setDropTable();
			    courseAddRetakeTableView.sort();
				courseDropTableview.sort();
			    updateRoutine();


		}
	    @FXML
		void mouseDragged(MouseEvent dragEvent) {
			Node node=(Node)dragEvent.getSource();
			Stage stuAdvStage=(Stage)node.getScene().getWindow();
			stuAdvStage.setX(dragEvent.getScreenX()-x);
			stuAdvStage.setY(dragEvent.getScreenY()-y);
		}

		@FXML
		void mousePressed(MouseEvent pressedEvent) {
			x=pressedEvent.getSceneX();
			y=pressedEvent.getSceneY();
		}
	    public void updateRoutine(){

	    	String[] weekDaysTime={"","","","",""};
	    	weekDayColumn.setCellValueFactory(new PropertyValueFactory<StuRoutineDetails,String>("day"));
			timeColumn.setCellValueFactory(new PropertyValueFactory<StuRoutineDetails,String>("time"));
	    	DatabaseTable detTabObj=new StudentCoursesTable();
			detTabObj.connect();
			String queryCondition="ID='"+stuId+"' AND Years= '"+year+"'";
			detTabObj.retrive(queryCondition);
			try {
				while(detTabObj.retriveResultset.next())
				{

					DatabaseTable detTabObj1=new CourseScheduleTable();
					detTabObj1.connect();
					queryCondition="Course_ID='"+detTabObj.retriveResultset.getString(2)+"' AND Years= '"+year+"'";
					detTabObj1.retrive(queryCondition);
					ResultSet res1=detTabObj1.retriveResultset;
					while(res1.next())
					{
						int index=0;
						while(index<5)
						{
							if(weekDays[index].equals(res1.getString(3)))
							{
								weekDaysTime[index]+="  "+res1.getString(4)+"{"+detTabObj.retriveResultset.getString(2)+")";
							}


							index++;
						}
					}

						detTabObj1.closeDb();
					}

				}catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
			int index=0;
			while(index<5)
			{
				dataCourseIdtime.add(new StuRoutineDetails(weekDays[index],weekDaysTime[index]));
				index++;
			}

			detTabObj.closeDb();



			timeTableview.setItems(null);
			timeTableview.setItems(dataCourseIdtime);
	    }
	    @FXML
	    private void selectCourseForAdd(MouseEvent event)
	    {
	    	CourseDetails courseDetailsObj=	courseAddRetakeTableView.getSelectionModel().getSelectedItem();
	    	selectedCourse=courseDetailsObj.getCourseCode();

	    }
	    @FXML
	    private void selectCourseForDrop(MouseEvent event)
	    {
	    	CourseDetails courseDetailsObj=	courseDropTableview.getSelectionModel().getSelectedItem();
	    	selectedDropCourse=courseDetailsObj.getCourseCode();

	    }
	    public void addEvent(ActionEvent event)
	    {
	    	String tempYear=null;
	    	if(selectedCourse!=null && getCount())
	    	{
		    	DatabaseTable detTabObj=new StudentCoursesTable();
				detTabObj.connect();
				String queryCondition="ID='"+stuId+"' and Course_ID='"+selectedCourse+"' and Grade='"+0.0+"'";
				detTabObj.retrive(queryCondition);
				try {
					if(detTabObj.retriveResultset.next())
					{
							tempYear=detTabObj.retriveResultset.getString(3);


					}

				} catch (Exception e) {
					setAlertFailedAddRetake();
				}

				detTabObj.closeDb();
				if(tempYear!=null)
				{
					getStuGpa();
					getCourseCredits(selectedCourse);
					int totalCredits=stuCredits-Integer.parseInt(stuCourseCredits);
					float res=stuRes*stuCredits/totalCredits;
					updateStuGpa(res,totalCredits);

					updateStuCourseTable(-1,tempYear);
				}
				insertStudentCourse();
				insertFacultyEvaStatus();
				insertFacultyMarkingStatus();



				dataCourseIdDrop.add(courseAddRetakeTableView.getSelectionModel().getSelectedItem());
				dataCourseIdAddRetake.remove(courseAddRetakeTableView.getSelectionModel().getSelectedItem());
				courseAddRetakeTableView.sort();
				courseDropTableview.sort();
				timeTableview.getItems().removeAll(dataCourseIdtime);
		    	updateRoutine();
				selectedCourse=null;
	    	}
	    	else{
	    		setAlertFailedAddRetake();
	    	}

	    }
	    public void dropEvent(ActionEvent event)
	    {
	    	String tempYear=null;

	    	if(selectedDropCourse!=null)
	    	{
		    	DatabaseTable detTabObj=new StudentCoursesTable();
				detTabObj.connect();
				String queryCondition="ID='"+stuId+"' and Course_ID='"+selectedDropCourse+"' and Grade='"+-1.0+"'";
				detTabObj.retrive(queryCondition);
				try {
					if(detTabObj.retriveResultset.next())
					{
						tempYear=detTabObj.retriveResultset.getString(3);


					}

				} catch (Exception e) {
					setAlertFailedDrop();
				}
				detTabObj.closeDb();
				if(tempYear!=null)
				{
					getStuGpa();
					getCourseCredits(selectedDropCourse);
					int totalCredits=stuCredits+Integer.parseInt(stuCourseCredits);
					float res=stuRes*stuCredits/totalCredits;
					updateStuGpa(res,totalCredits);
					updateStuCourseTable(0,tempYear);
				}
				deleteFacultyEvaStatus();
				deleteFacultyMarkingStatus();
				deleteStudentCourse();

				dataCourseIdAddRetake.add(courseDropTableview.getSelectionModel().getSelectedItem());
				timeTableview.getItems().removeAll(dataCourseIdtime);

				updateRoutine();
				dataCourseIdDrop.remove(courseDropTableview.getSelectionModel().getSelectedItem());
				courseAddRetakeTableView.sort();
				courseDropTableview.sort();
				selectedDropCourse=null;
	    	}
	    	else
	    	{
	    		setAlertFailedDrop();
	    	}

	    }
	    private boolean getCount()
	    {
	    	DatabaseTable detTabObj=new StudentCoursesTable();
			detTabObj.connect();
			String queryValues="Course_ID='"+selectedCourse+"' AND Years='"+year+"'";


	    	boolean status=detTabObj.count( queryValues);
	    	return status;
	    }
	    private void setAddTable()
	    {
	    	DatabaseTable detTabObj=new CourseTable();
			detTabObj.connect();
			String queryCondition="Years<'"+stuYear+"'";
			detTabObj.retrive(queryCondition);
			try {
				while(detTabObj.retriveResultset.next())
				{
					if(avaibilityOfCourse(detTabObj.retriveResultset.getString(2)))
					{
						if(getNotCompleteCourses(detTabObj.retriveResultset.getString(2)))
						{
							dataCourseIdAddRetake.add(new CourseDetails(detTabObj.retriveResultset.getString(2)));
						}
					}

				}


				}catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
			detTabObj.closeDb();
			detTabObj=new StudentCoursesTable();
			detTabObj.connect();
			queryCondition="ID='"+stuId+"'";
			detTabObj.retrive(queryCondition);
			try {
				while(detTabObj.retriveResultset.next())
				{

					if(avaibilityOfCourse(detTabObj.retriveResultset.getString(2)))					{
						if(getStatusMarking(detTabObj.retriveResultset.getString(2),detTabObj.retriveResultset.getString(3)).equals("Complete") && detTabObj.retriveResultset.getFloat(4)==0.0)

						{
							dataCourseIdAddRetake.add(new CourseDetails(detTabObj.retriveResultset.getString(2)));

						}
					}
				}

				}catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}

			detTabObj.closeDb();

    		detTabObj=new CourseTable();
			detTabObj.connect();
			queryCondition="Years='"+stuYear+"'";
			detTabObj.retrive(queryCondition);
			try {
				while(detTabObj.retriveResultset.next())
				{
					if(avaibilityOfCourse(detTabObj.retriveResultset.getString(2)))
					{

						dataCourseIdAddRetake.add(new CourseDetails(detTabObj.retriveResultset.getString(2)));


					}
				}

				}catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}

			detTabObj.closeDb();
			courseColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
			courseAddRetakeTableView.setItems(null);
			courseAddRetakeTableView.setItems(dataCourseIdAddRetake);

	    }
	    private boolean avaibilityOfCourse(String cId)
	    {
	    	boolean status=false;

	    	DatabaseTable detTabObj=new CourseScheduleTable();
			detTabObj.connect();
			String queryCondition="Course_ID='"+cId+"' AND Years='"+year+"'";
			detTabObj.retrive(queryCondition);
			try {
				if(detTabObj.retriveResultset.next())
				{
					status=true;

				}
			}catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
			detTabObj.closeDb();
	    	return status;
	    }
	    private void getStuId()
	    {
			DatabaseTable detTabObj=new LogTable();
			detTabObj.connect();
			detTabObj.retrive(null);
			stuId=detTabObj.retriveString;
			detTabObj.closeDb();
	    }
	    private void setDropTable()
	    {
	    	dataCourseIdDrop.clear();
	    	DatabaseTable detTabObj=new StudentCoursesTable();
			detTabObj.connect();
			String queryCondition="ID='"+stuId+"' AND Years='"+year+"'";
			detTabObj.retrive(queryCondition);
			try {
				while(detTabObj.retriveResultset.next())
				{

					dataCourseIdDrop.add(new CourseDetails(detTabObj.retriveResultset.getString(2)));

				}

				}catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
			detTabObj.closeDb();
			courseDropColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
			courseDropTableview.setItems(null);
			courseDropTableview.setItems(dataCourseIdDrop);

	    }
	    private String getStatusMarking(String cId,String cYear)
	    {
	    	DatabaseTable detTabObj=new FacultyMarkingStatusTable();
	    	String condition="null";
			detTabObj.connect();
			String queryCondition="ID='"+stuId+"' AND Course_ID='"+cId+"' AND Years='"+cYear+"'";
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
	    private boolean getNotCompleteCourses(String cId)
	    {
	    	boolean status=true;
	    	DatabaseTable detTabObj=new StudentCoursesTable();
			detTabObj.connect();
			String queryCondition="ID='"+stuId+"' AND Course_ID='"+cId+"'";
			detTabObj.retrive(queryCondition);
			try {
				if(detTabObj.retriveResultset.next())
				{
					status=false;

				}
			}catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
			detTabObj.closeDb();
			return status;
	    }
	    private void updateStuCourseTable(float grade,String cYear)
	    {
	    	if(selectedCourse!=null)
	    	{
	    	DatabaseTable detTabObj=new StudentCoursesTable();
			detTabObj.connect();
			String queryAttributeValues="Grade='"+grade+"'";
			String queryCondition="ID='"+stuId+"' and Course_ID='"+selectedCourse+"' and Years='"+cYear+"'";
			detTabObj.update(queryAttributeValues,queryCondition);
			detTabObj.closeDb();
	    	}
	    	if(selectedDropCourse!=null){
	    		DatabaseTable detTabObj=new StudentCoursesTable();
				detTabObj.connect();
				String queryAttributeValues="Grade='"+grade+"'";
				String queryCondition="ID='"+stuId+"' and Course_ID='"+selectedDropCourse+"' and Years='"+cYear+"'";
				detTabObj.update(queryAttributeValues,queryCondition);
				detTabObj.closeDb();
	    	}
	    }
	    private void insertStudentCourse()
	    {
	    	DatabaseTable detTabObj=new StudentCoursesTable();
			detTabObj.connect();
			String queryValues="VALUES ('"+stuId+"', '"+selectedCourse+"', '"+year+"', '"+0+"')";
			detTabObj.insert( queryValues);
			detTabObj.closeDb();
	    }
	    private void insertFacultyMarkingStatus()
	    {
	    	String status="Not Complete";
	    	DatabaseTable detTabObj=new FacultyMarkingStatusTable();
			detTabObj.connect();
			String queryValues="VALUES ('"+stuId+"', '"+selectedCourse+"', '"+year+"', '"+status+"')";
			detTabObj.insert( queryValues);
			detTabObj.closeDb();
	    }
	    private void insertFacultyEvaStatus()
	    {
	    	String status="Not Complete";
	    	DatabaseTable detTabObj=new FacultyEvaStatusTable();
			detTabObj.connect();
			String queryValues="VALUES ('"+stuId+"', '"+selectedCourse+"', '"+year+"', '"+status+"')";
			detTabObj.insert( queryValues);
			detTabObj.closeDb();
	    }
	    private void deleteStudentCourse()
	    {
	    	DatabaseTable detTabObj=new StudentCoursesTable();
			detTabObj.connect();
			String queryValues="ID='"+stuId+"' AND Course_ID='"+selectedDropCourse+"' AND Years='"+year+"'";
			detTabObj.delete( queryValues);
			detTabObj.closeDb();
	    }
	    private void deleteFacultyMarkingStatus()
	    {
	    	String status="Not Complete";
	    	DatabaseTable detTabObj=new FacultyMarkingStatusTable();
			detTabObj.connect();
			String queryValues="ID='"+stuId+"' AND Course_ID='"+selectedDropCourse+"' AND Years='"+year+"' AND Status='"+status+"'";
			detTabObj.delete( queryValues);
			detTabObj.closeDb();
	    }
	    private void deleteFacultyEvaStatus()
	    {
	    	String status="Not Complete";
	    	DatabaseTable detTabObj=new FacultyEvaStatusTable();
			detTabObj.connect();
			String queryValues="ID='"+stuId+"' AND Course_ID='"+selectedDropCourse+"' AND Years='"+year+"' AND Status='"+status+"'";
			detTabObj.delete( queryValues);
			detTabObj.closeDb();
	    }
	    private void updateStuGpa(float gpa,int credits)
	    {
	    	System.out.println(gpa);
			System.out.println(credits);
	    	DatabaseTable detTabObj=new StudentResTable();
			detTabObj.connect();
			String queryAttributeValues="CGPA='"+gpa+"', Complete_Credits='"+credits+"'";
			String queryCondition="ID='"+stuId+"'";
			detTabObj.update(queryAttributeValues,queryCondition);
	    }
	    private void getStuGpa()
	    {
	    	DatabaseTable detTabObj=new StudentResTable();
			detTabObj.connect();
			String queryCondition="ID='"+stuId+"'";
			detTabObj.retrive(queryCondition);
			try {
				while(detTabObj.retriveResultset.next())
				{
					stuRes=detTabObj.retriveResultset.getFloat(2);
					stuCredits=detTabObj.retriveResultset.getInt(3);

				}
			}catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
			detTabObj.closeDb();
	    }
	    private void getCourseCredits(String courseId)
	    {
	    	DatabaseTable detTabObj=new CourseTable();
			detTabObj.connect();
			String queryCondition="Course_ID='"+courseId+"'";
			detTabObj.retrive(queryCondition);
			try {
				while(detTabObj.retriveResultset.next())
				{

					stuCourseCredits=detTabObj.retriveResultset.getString(4);

				}
			}catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
			detTabObj.closeDb();
	    }
	    @FXML
		public void setAlertFailedDrop() {
			Alert alert=new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Failed");
			alert.setContentText("Drop Not Possible");
			alert.showAndWait();
		}
	    @FXML
		public void setAlertFailedAddRetake() {
			Alert alert=new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Failed");
			alert.setContentText("Add/Retake Not Possible");
			alert.showAndWait();
		}
}
