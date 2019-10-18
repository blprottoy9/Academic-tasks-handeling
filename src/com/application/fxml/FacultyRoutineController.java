package com.application.fxml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import com.database.*;
import com.detailclass.FacultyRoutineDetails;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FacultyRoutineController extends BackMenuController{
		private String userId;

		private String year;
		double x=0;
		double y=0;
		private String[] weekDays={"Sun","Mon","Tues","Wed","Thrus"};
		private String[] weekDaysTime={"","","","",""};
		int index;
	    @FXML
	    private VBox vBoxTop;
	    @FXML
	    private VBox vBoxButtom;

	  	@FXML
	    private TableView<FacultyRoutineDetails> tableViewFacultyRoutine;

	    @FXML
	    private TableColumn<FacultyRoutineDetails, String> dayColumn;

	    @FXML
	    private TableColumn<FacultyRoutineDetails, String> timeColumn;

	    @FXML
	    private Button backFacRouButton;
	    @FXML
	    private VBox vBoxCenter;

	    @FXML
	    private ObservableList<FacultyRoutineDetails>data;
	    @FXML
		private void initialize(){
	    	vBoxTop.setOpacity(0.9f);
	    	vBoxButtom.setOpacity(0.9f);
	    	//vBoxCenter.setOpacity(0.9f);
	    	data=FXCollections.observableArrayList();
			DatabaseTable detTabObj=new LogTable();
			detTabObj.connect();
			detTabObj.retrive(null);
			userId=detTabObj.retriveString;
			Date d=new Date();
		    year=Integer.toString(d.getYear()+1900);

		    detTabObj=new FacultyCoursesTablr();
			detTabObj.connect();
			String queryCondition="Id='"+userId+"' AND Years= '"+year+"'";
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
						index=0;
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
			index=0;
			while(index<5)
			{
				data.add(new FacultyRoutineDetails(weekDays[index],weekDaysTime[index]));
				index++;
			}

			detTabObj.closeDb();

			dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
			timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
			tableViewFacultyRoutine.setItems(null);
			tableViewFacultyRoutine.setItems(data);
		}
	    @FXML
		void mouseDragged(MouseEvent dragEvent) {
			Node node=(Node)dragEvent.getSource();
			Stage facRouStage=(Stage)node.getScene().getWindow();
			facRouStage.setX(dragEvent.getScreenX()-x);
			facRouStage.setY(dragEvent.getScreenY()-y);
		}

		@FXML
		void mousePressed(MouseEvent pressedEvent) {
			x=pressedEvent.getSceneX();
			y=pressedEvent.getSceneY();
		}
}
