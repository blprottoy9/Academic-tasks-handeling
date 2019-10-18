package com.detailclass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StuDetails {
	private final StringProperty stuId;
	public StuDetails(String stuId) {
		this.stuId=new SimpleStringProperty(stuId);
		System.out.println(stuId);

	}
	public String getStuId(){
		return stuId.get();
	}
	public void setStuId(String value)
	{
		 stuId.set(value);
	}
	public StringProperty stuIdProperty()
	{
		return stuId;
	}
}
