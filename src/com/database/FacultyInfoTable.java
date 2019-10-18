package com.database;

import java.sql.SQLException;

public class FacultyInfoTable extends DatabaseTable{

	@Override
	public void retrive(String queryCondition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(String queryAttributeValues) {
		sql="INSERT INTO Faculty_Info (ID,Dept)"+queryAttributeValues;

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
		// TODO Auto-generated method stub

	}

	@Override
	public boolean count(String queryCondition) {
		// TODO Auto-generated method stub
		return false;
	}

}
