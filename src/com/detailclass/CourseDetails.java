package com.detailclass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CourseDetails {
	private final StringProperty courseCode;
	public CourseDetails(String courseCode) {
		this.courseCode=new SimpleStringProperty(courseCode);
		System.out.println(courseCode);

	}
	public String getCourseCode(){
		return courseCode.get();
	}
	public void setCourseCode(String value)
	{
		 courseCode.set(value);
	}
	public StringProperty courseCodeProperty()
	{
		return courseCode;
	}

}
