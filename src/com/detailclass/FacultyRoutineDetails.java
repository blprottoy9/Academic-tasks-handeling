package com.detailclass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FacultyRoutineDetails {
	private final StringProperty day;
	private final StringProperty time;
	public FacultyRoutineDetails(String day,String time) {
		this.day=new SimpleStringProperty(day);
		this.time=new SimpleStringProperty(time);
		System.out.println(day);

	}
	public String getDay(){
		return day.get();
	}
	public String getTime(){
		return time.get();
	}
	public void setDay(String value)
	{
		 day.set(value);
	}
	public void setTime(String value)
	{
		 time.set(value);
	}
	public StringProperty dayProperty()
	{
		return day;
	}
	public StringProperty timeProperty()
	{
		return time;
	}

}
