package com.detailclass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StuResultDetails {
	private final StringProperty courseCode;
	private final StringProperty gpa;
	private final StringProperty credits;
	public StuResultDetails(String courseCode,String gpa,String credits) {
		this.courseCode=new SimpleStringProperty(courseCode);
		this.gpa=new SimpleStringProperty(gpa);
		this.credits=new SimpleStringProperty(credits);
		System.out.println(courseCode);

	}
	public String getCourseCode(){
		return courseCode.get();
	}
	public String getGpa(){
		return gpa.get();
	}
	public String getCredits(){
		return credits.get();
	}
	public void setCourseCode(String value)
	{
		 courseCode.set(value);
	}
	public void setGpa(String value)
	{
		 gpa.set(value);
	}
	public void setCredits(String value)
	{
		 credits.set(value);
	}
	public StringProperty courseCodeProperty()
	{
		return courseCode;
	}
	public StringProperty gpaProperty()
	{
		return gpa;
	}
	public StringProperty creditsProperty()
	{
		return credits;
	}
}
