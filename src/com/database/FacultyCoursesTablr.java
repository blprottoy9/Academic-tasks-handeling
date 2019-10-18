package com.database;

import java.sql.SQLException;

public class FacultyCoursesTablr extends DatabaseTable{

	@Override
	public void retrive(String queryCondition) {
		if(queryCondition==null)
		{
		sql="select * from Faculty_courses";

		try {
			stmpt=con.prepareStatement(sql);
			//rs=stmpt.executeQuery(sql);
			rs=stmpt.executeQuery();
			retriveResultset=rs;

		} catch (SQLException sqlException) {

			sqlException.printStackTrace();
		}


		}
		else if(queryCondition!=null)
		{
			sql="select * from Faculty_courses Where "+queryCondition;

			try {
				stmpt=con.prepareStatement(sql);
				//rs=stmpt.executeQuery(sql);
				rs=stmpt.executeQuery();
				retriveResultset=rs;
				System.out.print(sql);
			} catch (SQLException sqlException) {

				sqlException.printStackTrace();
			}
		}


	}

	@Override
	public void insert(String queryAttributeValues) {
		sql="INSERT INTO faculty_courses (Id, Course_ID, Years)"+queryAttributeValues;

		try {
			stmpt=con.prepareStatement(sql);
			//stmpt.executeUpdate(sql);
			stmpt.executeUpdate();
		} catch (SQLException sqlException) {
			//System.out.print(sql);
			sqlException.printStackTrace();
		}
		closeDb();


	}

	@Override
	public void delete(String queryCondition) {
		sql="delete from faculty_courses Where "+queryCondition;
		try {
			stmpt=con.prepareStatement(sql);
			//stmpt.executeUpdate(sql);
			stmpt.executeUpdate();
		} catch (SQLException sqlException) {
			//System.out.print(sql);
			sqlException.printStackTrace();
		}
		closeDb();
	}

	@Override
	public void update(String queryAttributeValues, String queryCondition) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean count(String queryCondition) {
		// TODO Auto-generated method stub
		return false;
	}

}
