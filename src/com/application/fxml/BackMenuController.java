package com.application.fxml;

import java.io.IOException;

import com.application.AdminMenu;
import com.application.ChangePasswordMenu;
import com.application.FacultyMenu;
import com.application.StudentMenu;
import com.database.DatabaseTable;
import com.database.DetailTable;
import com.database.LogTable;

import javafx.event.ActionEvent;

public class BackMenuController {

	public void getUserMenu(ActionEvent userMenuConEvent) throws IOException {
		DatabaseTable detTabObj=new LogTable();
		detTabObj.connect();

		detTabObj.retrive(null);
		if(detTabObj.retriveString2.equals("admin")){
			AdminMenu adm=new  AdminMenu();
			adm.runAdmMenu(userMenuConEvent);
		}
		else if(detTabObj.retriveString2.equals("Student")){
			StudentMenu stu=new  StudentMenu();
			stu.getId(detTabObj.retriveString);
			stu.runStuMenu(userMenuConEvent);
		}
		else{
			FacultyMenu fac=new  FacultyMenu();
			fac.getId(detTabObj.retriveString2+": "+detTabObj.retriveString);
			fac.runFacultyMenu(userMenuConEvent);
		}


	}

}
