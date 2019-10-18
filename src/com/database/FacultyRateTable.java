package com.database;

import java.sql.SQLException;

public class FacultyRateTable extends DatabaseTable{

	@Override
	public void retrive(String queryCondition) {
		if(queryCondition==null)
		{
		sql="select * from faculty_rate";
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
			sql="select * from faculty_rate Where "+queryCondition;
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
		sql="INSERT INTO faculty_rate (ID, Rate, Count1)"+queryAttributeValues;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void update(String queryAttributeValues, String queryCondition) {
		sql="update faculty_rate set "+queryAttributeValues+" where "+queryCondition;
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

}
