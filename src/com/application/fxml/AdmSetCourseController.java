package com.application.fxml;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.database.*;

import com.detailclass.CourseDetails;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdmSetCourseController extends BackMenuController {
	private ObservableList<String> timetList=FXCollections.observableArrayList("8.00Am-10.00Am","10.00Am-12.00Pm","1.00Pm-3.00Pm","3.00Pm-5.00Pm","None");
	private String date;
	private String year;
	double x=0;
	double y=0;
	private int count=0;
	private String[] day= {"Sun","Mon","Tues","Wed","Thrus"};
	private String[] dayTime= {"None","None","None","None","None"};
    @FXML
    private VBox hBoxButtom;
    @FXML
    private SplitPane spilltPane;
	@FXML
    private DatePicker yearDatePicker;
	@FXML
    private TableView<CourseDetails> tableview;

    @FXML
    private TableColumn<CourseDetails, String> courseCodeColumn;

    @FXML
    private Label courseCodeLabel;

    @FXML
    private Button backButton;
    @FXML
    private TextField facTextField;

    @FXML
    private ChoiceBox sunChoicebox;

    @FXML
    private ChoiceBox monChoicebox;

    @FXML
    private ChoiceBox tuesChoicebox;

    @FXML
    private ChoiceBox wedChoicebox;

    @FXML
    private ChoiceBox thuChoicebox;
    @FXML
    private HBox hBoxTop;

    @FXML
    private ObservableList<CourseDetails>data;
    @FXML
	private void initialize(){
    	hBoxTop.setOpacity(0.9f);
    	spilltPane.setOpacity(0.9f);
		hBoxButtom.setOpacity(0.9f);
		DatabaseTable detTabObj=new CourseTable();
		detTabObj.connect();
		//String queryCondition="ID='"+userId+"'";
		data=FXCollections.observableArrayList();
		detTabObj.retrive(null);
		ResultSet res1=detTabObj.retriveResultset;
		try {
			while(res1.next())
			{
				data.add(new CourseDetails(res1.getString(2)));
				//System.out.println(res1.getString(2));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		detTabObj.closeDb();
		courseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
		tableview.setItems(null);
		tableview.setItems(data);
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
	public void set()
	{
		String time="None";
		sunChoicebox.setValue(time);
		sunChoicebox.setItems(timetList);
		monChoicebox.setValue(time);
		monChoicebox.setItems(timetList);
		tuesChoicebox.setValue(time);
		tuesChoicebox.setItems(timetList);
		wedChoicebox.setValue(time);
		wedChoicebox.setItems(timetList);
		thuChoicebox.setValue(time);
		thuChoicebox.setItems(timetList);

	}
    @FXML
    private void getSelectedItem(MouseEvent mouseEvent)
    {
    	CourseDetails courseDetailsObj=	tableview.getSelectionModel().getSelectedItem();
    	if(courseDetailsObj==null)
    	{
    		courseCodeLabel.setText(null);
    	}
    	else
    	{

    		set();
    		String selectedCourseCode=courseDetailsObj.getCourseCode();
    		courseCodeLabel.setText(selectedCourseCode);


    	}


    }
    public void getYear(ActionEvent yearEvent)
    {
    	date=yearDatePicker.getValue().toString();
		year=date.substring(0, 4);
		//System.out.println(date);
    }
    public void setCourses(ActionEvent courseEvent)
    {

    	String facId=facTextField.getText();

		String queryValues;
		String resCourseId;
		boolean flag=true;
		setDay();
		DatabaseTable detTabObj=new FacultyInfoTable();
		detTabObj.connect();
		String queryCondition="ID='"+facId+"'";
		String res1=detTabObj.search( "faculty_info",queryCondition);
		DatabaseTable detTabObj1=new CourseScheduleTable();;
		detTabObj1.connect();
		queryCondition="Course_ID='"+courseCodeLabel.getText()+"' AND Years= '"+year+"'";
		String res2=detTabObj1.search( "Course_Schedule",queryCondition);
		if(res1=="true" && res2!="true")
		{

			String res3;
			detTabObj=new FacultyCoursesTablr();
			detTabObj.connect();
			queryCondition="Id='"+facId+"' AND Years= '"+year+"'";
			detTabObj.retrive(queryCondition);
			try {
				while(detTabObj.retriveResultset.next())
				{
					resCourseId=detTabObj.retriveResultset.getString(2);
					detTabObj1=new CourseScheduleTable();
					detTabObj1.connect();
					int index1=0;
					queryCondition="Course_ID='"+resCourseId+"' AND Years= '"+year+"'";
					detTabObj1.retrive(queryCondition);

					while(detTabObj1.retriveResultset.next())
					{
						index1=0;
						res2=detTabObj1.retriveResultset.getString(4);
						res3=detTabObj1.retriveResultset.getString(3);
						//System.out.print(res2);
						while(index1<5){

							if((res3.equals(day[index1]) &&res2.equals(dayTime[index1])))
							{
								System.out.println(res2+" "+dayTime[index1]);
								System.out.println(res3+" "+day[index1]);
								System.out.print(res2);
								flag=false;
								break;
							}
							index1++;
						}
						if(flag==false)
						{
							break;
						}
					}
					detTabObj1.closeDb();
					if(flag==false)
					{
						break;
					}

				}
				detTabObj.closeDb();

			} catch (Exception exception) {
				// TODO Auto-generated catch block
				exception.printStackTrace();
			}
			if(flag==true)
			{
				if(courseCodeLabel.getText()!=null &&year!=null ){

					int index=0;
					while(index<5)
					{
						System.out.println(day[index]+" "+dayTime[index]);
						if(day[index]!="None")
						{

							detTabObj=new CourseScheduleTable();
							detTabObj.connect();
							queryValues="VALUES ('"+courseCodeLabel.getText()+"', '"+year+"', '"+day[index]+"', '"+dayTime[index]+"')";
							//String queryValues="VALUES "+userId+", "+password+")";
							System.out.println(day[index]+" "+dayTime[index]);
							detTabObj.insert( queryValues);


						}
						index++;
					}
					detTabObj=new FacultyCoursesTablr();
					detTabObj.connect();
					queryValues="VALUES ('"+facId+"', '"+courseCodeLabel.getText()+"', '"+year+"')";
					//String queryValues="VALUES "+userId+", "+password+")";
					detTabObj.insert( queryValues);

					setAlertSuccess();

				}
				else
				{
					setAlertFillAllInform();
				}
			}
			else
			{
				setAlertTime();
			}
		}
		else
		{
			setAlertNoMatchUser();
		}
		set();
		courseCodeLabel.setText(null);
    }
    public void deleteCourse(ActionEvent courseEvent)
    {
    	if(courseCodeLabel.getText()!=null && year!=null)
    	{
    		if(getNotCompleteCourses())
    		{
    			deleteFacultyMarkingStatus();
    			deleteFacultyEvaStatus();
    			deleteStudentCourse();
    			deleteFacultyCourseData();
    			deleteCourseSchedule();
    			setAlertDeleteSuccess();
    		}
    		else
    		{
    			setAlertDeleteNotPossible();
    		}
    	}
    	else
    	{
    		setAlertDeleteNotPossible();
    	}
    }
    private boolean getNotCompleteCourses()
    {
    	boolean status=false;
    	DatabaseTable detTabObj=new CourseScheduleTable();
		detTabObj.connect();
		String queryCondition="Course_ID='"+courseCodeLabel.getText()+"' AND Years='"+year+"'";
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
    private void deleteStudentCourse()
    {
    	DatabaseTable detTabObj=new StudentCoursesTable();
		detTabObj.connect();
		String queryValues="Course_ID='"+courseCodeLabel.getText()+"' AND Years='"+year+"'";
		detTabObj.delete( queryValues);
		detTabObj.closeDb();
    }
    private void deleteFacultyMarkingStatus()
    {

    	DatabaseTable detTabObj=new FacultyMarkingStatusTable();
		detTabObj.connect();
		String queryValues="Course_ID='"+courseCodeLabel.getText()+"' AND Years='"+year+"'";
		detTabObj.delete( queryValues);
		detTabObj.closeDb();
    }
    private void deleteFacultyEvaStatus()
    {

    	DatabaseTable detTabObj=new FacultyEvaStatusTable();
		detTabObj.connect();
		String queryValues="Course_ID='"+courseCodeLabel.getText()+"' AND Years='"+year+"'";
		detTabObj.delete( queryValues);
		detTabObj.closeDb();
    }
    private void deleteFacultyCourseData()
    {

    	DatabaseTable detTabObj=new FacultyCoursesTablr();
		detTabObj.connect();
		String queryValues="Course_ID='"+courseCodeLabel.getText()+"' AND Years='"+year+"'";
		detTabObj.delete( queryValues);
		detTabObj.closeDb();
    }
    private void deleteCourseSchedule()
    {
    	DatabaseTable detTabObj=new CourseScheduleTable();
		detTabObj.connect();
		String queryValues="Course_ID='"+courseCodeLabel.getText()+"' AND Years='"+year+"'";
		detTabObj.delete( queryValues);
		detTabObj.closeDb();
    }
	@FXML
	public void setAlertNoMatchUser() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Failed");
		alert.setContentText("Id invalid or Course is already set");
		alert.showAndWait();
	}
	@FXML
	public void setAlertFillAllInform() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Failed");
		alert.setContentText("Information Missing");
		alert.showAndWait();
	}
	@FXML
	public void setAlertTime() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Failed");
		alert.setContentText("Time Dont Match");
		alert.showAndWait();
	}
	@FXML
	public void setAlertSuccess() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("SuccessFul");
		alert.setContentText("Insert Data Successful");
		alert.showAndWait();
	}
	@FXML
	public void setAlertDeleteSuccess() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("SuccessFul");
		alert.setContentText("Delete Data Successful");
		alert.showAndWait();
	}
	@FXML
	public void setAlertDeleteNotPossible() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("UnsuccessFul");
		alert.setContentText("Delete Data not possible");
		alert.showAndWait();
	}
	public void setDay()
	{
		if((String)sunChoicebox.getValue()!="None")
		{
			dayTime[0]=(String)sunChoicebox.getValue();
			day[0]="Sun";
			count++;
		}
		else{
			day[0]="None";
		}
		if((String)monChoicebox.getValue()!="None")
		{
			dayTime[1]=(String)monChoicebox.getValue();
			day[1]="Mon";
			count++;
		}
		else{
			day[1]="None";
		}
		if((String)tuesChoicebox.getValue()!="None")
		{
			dayTime[2]=(String)tuesChoicebox.getValue();
			day[2]="Tues";
			count++;
		}
		else{
			day[2]="None";
		}
		if((String)wedChoicebox.getValue()!="None")
		{
			dayTime[3]=(String)wedChoicebox.getValue();
			day[3]="Wed";
			count++;
		}
		else{
			day[3]="None";
		}
		if((String)thuChoicebox.getValue()!="None")
		{
			dayTime[4]=(String)thuChoicebox.getValue();
			day[4]="Thrus";
			count++;
		}
		else{
			day[4]="None";
		}
	}

}
