package com.application.fxml;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.application.AdminMenu;
import com.application.FacultyMenu;
import com.application.StudentFacEvaluation;
import com.application.StudentMenu;
import com.database.*;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;


public class LoginControler {
	double x=0;
	double y=0;

	Media media;
	MediaPlayer player;
	Thread mediaThread;

	@FXML
	private ImageView imageId;
    @FXML
    private MediaView mediaView;
	@FXML
	private JFXPasswordField pwd;

	@FXML
	private JFXTextField uid;
	@FXML
	private HBox hBoxUser;
    @FXML
    private HBox hBoxPass;
    @FXML
    private HBox hBoxButton;

	@FXML
	private AnchorPane alertPane;
	@FXML
	private void initialize(){

		media= new Media(new File(new File("Video/bg.mp4").getAbsolutePath()).toURI().toString());
		player=new MediaPlayer(media);
		mediaView.setMediaPlayer(player);
		runThread();
		Image img=new Image("/com/application/css/Image/iit.JPG");
		imageId.setImage(img);
		hBoxUser.setOpacity(0.9f);
		hBoxPass.setOpacity(0.9f);
		hBoxButton.setOpacity(0.9f);

	}
	private void runThread()
	{
		try
		{
			mediaThread = new Thread(new Runnable() {

				@Override
				public void run() {

					while(true) {
						if(player.getCurrentTime().equals(player.getTotalDuration())){
							player.stop();
							player.seek(Duration.ZERO);
							player.play();
							System.out.println("l");
						}
						else{
							System.out.println(player.getCurrentTime());
						}

					}
				}
			});
		}catch(Exception threadException){
			threadException.printStackTrace();
		}
		runPlayer();
		mediaThread.start();
	}
	private void runPlayer()
	{
		player.play();
	}
	@FXML
	void mouseDragged(MouseEvent dragEvent) {
		Node node=(Node)dragEvent.getSource();
		Stage loginStage=(Stage)node.getScene().getWindow();
		loginStage.setX(dragEvent.getScreenX()-x);
		loginStage.setY(dragEvent.getScreenY()-y);
	}

	@FXML
	void mousePressed(MouseEvent pressedEvent) {
		x=pressedEvent.getSceneX();
		y=pressedEvent.getSceneY();
	}
	public void getUserMenu(ActionEvent userConEvent) throws IOException {

		DatabaseTable detTabObj=new LoginTable();
		detTabObj.connect();
		String userId=uid.getText();
		String password=pwd.getText();
		String queryCondition="ID='"+userId+"' AND PASSWORD1= '"+password+"'";
		String res=detTabObj.search( "Login",queryCondition);

		if(res=="true")
		{
			detTabObj=new DetailTable();
			detTabObj.connect();
			queryCondition="Id='"+userId+"'";

			detTabObj.retrive(queryCondition);
			System.out.print(detTabObj.retriveString);
			if(detTabObj.retriveString.equals("admin"))
			{
				setLog(userId,"admin");
				AdminMenu adm=new  AdminMenu();
				mediaThread.stop();
				adm.runAdmMenu(userConEvent);

			}
			else if(detTabObj.retriveString.equals("Student"))
			{
				setLog(userId,"Student");
				StudentMenu stu=new StudentMenu();
				stu.getId(userId);
				mediaThread.stop();
				stu.runStuMenu(userConEvent);

			}

			else
			{
				setLog(userId,detTabObj.retriveString);
				FacultyMenu fac=new  FacultyMenu();
				fac.getId(detTabObj.retriveString+": "+userId);
				mediaThread.stop();
				fac.runFacultyMenu(userConEvent);

			}

		}

		else
		{
			setAlertFailed();
		}


	}
	@FXML
	public void setAlertSuccessful() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Succeessful");
		alert.setContentText("Login Succeessful");
		alert.showAndWait();
	}
	@FXML
	public void setAlertFailed() {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Failed");
		alert.setContentText("Login Failed");
		alert.showAndWait();
	}
	public void setLog(String uId,String status)
	{
		DatabaseTable detTabObj=new LogTable();
		detTabObj.connect();
		String queryValues="VALUES ('"+uId+"', '"+status+"')";
		//String queryValues="VALUES "+userId+", "+password+")";
		detTabObj.insert( queryValues);
	}
	public void destroyMenu(ActionEvent userDesEvent)
	{
		Stage loginStage=(Stage)((Node)userDesEvent.getSource()).getScene().getWindow();
		mediaThread.stop();
		loginStage.close();
	}


}