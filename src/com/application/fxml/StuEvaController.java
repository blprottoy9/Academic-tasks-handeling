package com.application.fxml;

import java.io.IOException;

import com.application.StudentAdvising;
import com.application.StudentFacEvaluation;

import javafx.event.ActionEvent;

public class StuEvaController {


	public void getStuEvaMenu(ActionEvent stuEvaConevent) throws IOException {
		StudentFacEvaluation stuEva=new  StudentFacEvaluation();
		stuEva.runStuEvaMenu(stuEvaConevent);


	}
}
