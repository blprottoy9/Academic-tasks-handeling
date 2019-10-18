package com.application.fxml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import com.database.*;
import com.detailclass.CourseDetails;
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

public class StuFacEvaController extends BackMenuController{
		double x=0;
		double y=0;
		private String stuId;
		private String stuCourseId;
		private String facId=" ";
		private String facStatus=" ";
		private int facTotalCount;
		private float facRate;
		private String year;
		private ObservableList<String> grdetList=FXCollections.observableArrayList("0","1","2","3","4","5","6","7","8","9","10");
	    @FXML
	    private SplitPane splitPane;

	    @FXML
	    private VBox vBoxButtom;
	    @FXML
	    private VBox vBoxTop;

		@FXML
	    private TableView<CourseDetails> coursesTableview;

	    @FXML
	    private TableColumn<CourseDetails, String> courseTableColumn;

	    @FXML
	    private Label facIdLabel;

	    @FXML
	    private Label facEvaStatusLabel;

	    @FXML
	    private ComboBox facEvaChoiceBox;

	    @FXML
	    private Button facEvaApplyButton;

	    @FXML
	    private Button backMenuButton;
	    private ObservableList<CourseDetails>data;
	    @FXML
		private void initialize(){
    			vBoxTop.setOpacity(0.9f);
    			vBoxButtom.setOpacity(0.9f);
    			splitPane.setOpacity(0.9f);
	    		data=FXCollections.observableArrayList();
				DatabaseTable detTabObj=new LogTable();
				detTabObj.connect();
				detTabObj.retrive(null);
				stuId=detTabObj.retriveString;
				Date d=new Date();
			    year=Integer.toString(d.getYear()+1900);

			    detTabObj=new StudentCoursesTable();
				detTabObj.connect();
				String queryCondition="ID='"+stuId+"' AND Years= '"+year+"'";
				detTabObj.retrive(queryCondition);
				try {
					while(detTabObj.retriveResultset.next())
					{

						data.add(new CourseDetails(detTabObj.retriveResultset.getString(2)));
					}

					}catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}

				detTabObj.closeDb();

				courseTableColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
				coursesTableview.setItems(null);
				coursesTableview.setItems(data);
			}
	    @FXML
	    private void getSelectedItem(MouseEvent mouseEvent)
	    {
	    	CourseDetails courseDetailsObj=	coursesTableview.getSelectionModel().getSelectedItem();
	    	if(courseDetailsObj==null)
	    	{
	    		facIdLabel.setText(null);
	    		facEvaStatusLabel.setText(null);
	    	}
	    	else
	    	{

	    		String grade="0";
	    		facEvaChoiceBox.setValue(grade);
	    		facEvaChoiceBox.setItems(grdetList);
	    		String selectedCourseCode=courseDetailsObj.getCourseCode();
	    		getFacId(selectedCourseCode);
	    		getEvaStatus(selectedCourseCode);
	    		facIdLabel.setText(" "+facId);
	    		facEvaStatusLabel.setText(" "+facStatus);


	    	}


	    }
	    private void getFacId(String courseId)
	    {
	    	this.stuCourseId=courseId;
	    	DatabaseTable detTabObj=new FacultyCoursesTablr();
			detTabObj.connect();
			String queryCondition="Course_ID='"+courseId+"' AND Years= '"+year+"'";
			detTabObj.retrive(queryCondition);
			try {
				while(detTabObj.retriveResultset.next())
				{
					facId=detTabObj.retriveResultset.getString(1);
				}
			}catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
			detTabObj.closeDb();

	    }
	    private void getEvaStatus(String courseId)
	    {
	    	DatabaseTable detTabObj=new FacultyEvaStatusTable();
			detTabObj.connect();
			String queryCondition="ID='"+stuId+"' AND Course_ID='"+courseId+"' AND Years= '"+year+"'";
			detTabObj.retrive(queryCondition);
			try {
				while(detTabObj.retriveResultset.next())
				{
					facStatus=detTabObj.retriveResultset.getString(4);
				}
			}catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
			detTabObj.closeDb();
	    }
	    public void setDatabase(ActionEvent databaseEvent)
	    {
	    	String givenGrade=(String)facEvaChoiceBox.getValue();
	    	System.out.print(facStatus);
	    	if(facStatus.equals("Not Complete"))
	    	{
	    		getFacRateAndCount();
	    		if(facTotalCount==0)
	    		{
	    			if(facRate!=0)
	    			{
	    				facRate=(facRate+Float.valueOf(givenGrade.trim()).floatValue())/2;
	    			}
	    			else
	    			{
	    				facRate=Float.valueOf(givenGrade.trim()).floatValue();
	    			}
	    		}
	    		else if(facTotalCount!=0)
	    		{
	    			facRate=(facRate*facTotalCount+Float.valueOf(givenGrade.trim()).floatValue())/(facTotalCount+1);
	    		}
	    		facTotalCount=facTotalCount+1;
	    		facStatus="Complete";
	    		updateFacEva();
	    		updateFacEvaStatus();

	    		setAlertSuccess();
	    	}
	    	else
	    	{
	    		setAlertEvaComplete();
	    	}
	    }
	    private void getFacRateAndCount()
	    {
	    	DatabaseTable detTabObj=new FacultyRateTable();
			detTabObj.connect();
			String queryCondition="ID='"+facId+"'";
			detTabObj.retrive(queryCondition);
			try {
				while(detTabObj.retriveResultset.next())
				{
					facRate=detTabObj.retriveResultset.getFloat(2);
					facTotalCount=detTabObj.retriveResultset.getInt(3);
				}
			}catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
			detTabObj.closeDb();
	    }
	    private void updateFacEva()
	    {
	    	DatabaseTable detTabObj=new FacultyRateTable();
			detTabObj.connect();
			String queryAttributeValues="Rate='"+facRate+"', Count1='"+facTotalCount+"'";
			String queryCondition="ID='"+facId+"'";
			detTabObj.update(queryAttributeValues,queryCondition);
	    }
	    private void updateFacEvaStatus()
	    {
	    	System.out.println("a");
	    	DatabaseTable detTabObj=new FacultyEvaStatusTable();
			detTabObj.connect();
			String queryAttributeValues="Status='"+facStatus+"'";
			String queryCondition="ID='"+stuId+"' AND Course_ID='"+stuCourseId+"' AND Years='"+year+"'";
			detTabObj.update(queryAttributeValues,queryCondition);
	    }
	    @FXML
		public void setAlertEvaComplete() {
			Alert alert=new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Faculty Evaluation Complete");
			alert.setContentText("Faculty Evaluation is already done for this course");
			alert.showAndWait();
		}
	    @FXML
		void mouseDragged(MouseEvent dragEvent) {
			Node node=(Node)dragEvent.getSource();
			Stage stuFacEvaStage=(Stage)node.getScene().getWindow();
			stuFacEvaStage.setX(dragEvent.getScreenX()-x);
			stuFacEvaStage.setY(dragEvent.getScreenY()-y);
		}

		@FXML
		void mousePressed(MouseEvent pressedEvent) {
			x=pressedEvent.getSceneX();
			y=pressedEvent.getSceneY();
		}
	    @FXML
		public void setAlertSuccess() {
			Alert alert=new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Successful");
			alert.setContentText("Evaluation successfully complete. Thank you.");
			alert.showAndWait();
		}
}
