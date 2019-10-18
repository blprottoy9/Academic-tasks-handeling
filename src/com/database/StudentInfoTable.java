package com.database;

import java.sql.SQLException;

import javax.management.loading.PrivateClassLoader;

public class StudentInfoTable extends DatabaseTable{
	private String dept;
	private String years;
	@Override
	public void retrive(String queryCondition) {
		sql="select * from Student_Info where "+queryCondition;
		//sql="select '"+queryAttribute+"' from Detail where "+queryCondition;
		//sql="select * from Detail where Id='admin'";
		try {
			stmpt=con.prepareStatement(sql);
			//rs=stmpt.executeQuery(sql);
			rs=stmpt.executeQuery();

			//select * from Detail where Id='admin'admin

			while(rs.next())
				{

				dept=rs.getString(2);
				years=rs.getString(3);
				}
			retriveString=dept;
			retriveString2=years;

			//System.out.print(sql);
			//System.out.print(statusPerson);

		} catch (SQLException sqlException) {
			//System.out.print(sql);
			//sqlException.printStackTrace();
		}
		closeDb();
		//return null;
	}

	@Override
	public void delete(String queryCondition) {}

	@Override
	public void update(String queryAttributeValues, String queryCondition) {
	sql="update Student_Info set "+queryAttributeValues+" where "+queryCondition;
	try {
		stmpt=con.prepareStatement(sql);
		//stmpt.executeUpdate(sql);
		stmpt.executeUpdate();
		System.out.print(sql);

	} catch (SQLException sqlException) {
		System.out.print(sql);
		//sqlException.printStackTrace();
	}
	closeDb();
}

	@Override
	public boolean count(String queryCondition) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void insert(String queryAttributeValues) {
		sql="INSERT INTO Student_Info (ID,Dept,Years)"+queryAttributeValues;

		try {
			stmpt=con.prepareStatement(sql);
			//stmpt.executeUpdate(sql);
			stmpt.executeUpdate();

		} catch (SQLException sqlException) {
			System.out.print(sql);
			//sqlException.printStackTrace();
		}
		closeDb();

	}


}
