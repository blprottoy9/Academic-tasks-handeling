package com.application.fxml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import com.database.*;
import com.detailclass.StuRoutineDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StuRoutineMenuController extends BackMenuController{
	double x=0;
	double y=0;
	private String userId;
	private String year;
	private String[] weekDays={"Sun","Mon","Tues","Wed","Thrus"};
	private String[] weekDaysTime={"","","","",""};
	int index;
    @FXML
    private VBox vBoxCenter;
    @FXML
    private VBox vBoxButtom;
    @FXML
    private VBox vBoxTop;
	@FXML
	private TableView<StuRoutineDetails> stuRouTableview;

	@FXML
	private TableColumn<StuRoutineDetails, String> weekDaysTableColumn;

	@FXML
	private TableColumn<StuRoutineDetails, String> timeTableColumns;

	@FXML
	private Button stuRouBackButton;
	@FXML
	public ObservableList<StuRoutineDetails>data;
	@FXML
	private void initialize(){
    	vBoxTop.setOpacity(0.9f);
    	vBoxButtom.setOpacity(0.9f);
    	//vBoxCenter.setOpacity(0.9f);
		data=FXCollections.observableArrayList();
		getStuId();
		Date d=new Date();
		year=Integer.toString(d.getYear()+1900);
		setRoutine();

		}
	private void setRoutine()
	{
		DatabaseTable detTabObj=new StudentCoursesTable();
		detTabObj.connect();
		String queryCondition="ID='"+userId+"' AND Years= '"+year+"'";
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
			data.add(new StuRoutineDetails(weekDays[index],weekDaysTime[index]));
			index++;
		}

		detTabObj.closeDb();

		weekDaysTableColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
		timeTableColumns.setCellValueFactory(new PropertyValueFactory<>("time"));
		stuRouTableview.setItems(null);
		stuRouTableview.setItems(data);
	}

    private void getStuId()
    {
		DatabaseTable detTabObj=new LogTable();
		detTabObj.connect();
		detTabObj.retrive(null);
		userId=detTabObj.retriveString;
		detTabObj.closeDb();
    }
    @FXML
	void mouseDragged(MouseEvent dragEvent) {
		Node node=(Node)dragEvent.getSource();
		Stage stuRouStage=(Stage)node.getScene().getWindow();
		stuRouStage.setX(dragEvent.getScreenX()-x);
		stuRouStage.setY(dragEvent.getScreenY()-y);
	}

	@FXML
	void mousePressed(MouseEvent pressedEvent) {
		x=pressedEvent.getSceneX();
		y=pressedEvent.getSceneY();
	}
}
