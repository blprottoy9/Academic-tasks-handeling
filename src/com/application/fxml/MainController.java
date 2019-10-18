package com.application.fxml;

import java.io.IOException;

import com.application.ChangePasswordMenu;
import com.application.Login;
import com.database.DatabaseTable;
import com.database.LogTable;

import javafx.event.ActionEvent;

public class MainController {
	//public abstract void  getChaPassMenu(ActionEvent chaPassConEvent);
	public void getChaPassMenu(ActionEvent chaPassConEvent) throws IOException {
		ChangePasswordMenu stuChaPass=new  ChangePasswordMenu();
		stuChaPass.runChaPasswdMenu(chaPassConEvent);


	}

	public void getLoginMenu(ActionEvent loginConEvent) throws IOException {
		deleteLog();
		Login login=new  Login();
		login.runLoginMenu(loginConEvent);


	}

	public void deleteLog()
	{
		DatabaseTable detTabObj=new LogTable();
		detTabObj.connect();

		detTabObj.delete(null);
	}
}
