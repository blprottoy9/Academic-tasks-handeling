package com.database;

import java.sql.SQLException;

public class CourseScheduleTable extends DatabaseTable{

	@Override
	public void retrive(String queryCondition) {
		if(queryCondition==null)
		{
		sql="select * from Course_Schedule";
		//sql="select '"+queryAttribute+"' from Detail where "+queryCondition;
		//sql="select * from Detail where Id='admin'";
		try {
			stmpt=con.prepareStatement(sql);
			//rs=stmpt.executeQuery(sql);
			rs=stmpt.executeQuery();
			retriveResultset=rs;

		} catch (SQLException sqlException) {
			//System.out.print(sql);
			//sqlException.printStackTrace();
		}


		}
		else
		{
			sql="select * from Course_Schedule Where "+queryCondition;
			//sql="select '"+queryAttribute+"' from Detail where "+queryCondition;
			//sql="select * from Detail where Id='admin'";
			try {
				stmpt=con.prepareStatement(sql);
				//rs=stmpt.executeQuery(sql);
				rs=stmpt.executeQuery();
				retriveResultset=rs;

			} catch (SQLException sqlException) {
				//System.out.print(sql);
				//sqlException.printStackTrace();
			}
		}


	}

	@Override
	public void insert(String queryAttributeValues) {
		sql="INSERT INTO Course_Schedule (Course_ID, Years, Day, Time)"+queryAttributeValues;

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
		sql="delete from Course_Schedule Where "+queryCondition;
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
