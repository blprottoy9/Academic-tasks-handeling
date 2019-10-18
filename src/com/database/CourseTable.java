package com.database;

import java.sql.SQLException;

public class CourseTable extends DatabaseTable{

	@Override
	public void retrive(String queryCondition) {
		if(queryCondition==null)
		{
		sql="select * from 	course";
			try {
				stmpt=con.prepareStatement(sql);
				//rs=stmpt.executeQuery(sql);
				rs=stmpt.executeQuery();
				retriveResultset=rs;
				System.out.println(sql);

			} catch (SQLException sqlException) {
				//System.out.print(sql);
				sqlException.printStackTrace();
			}


		}
		else
		{
			sql="select * from 	course Where "+queryCondition;
			try {
				stmpt=con.prepareStatement(sql);
				//rs=stmpt.executeQuery(sql);
				rs=stmpt.executeQuery();
				retriveResultset=rs;
				System.out.print(sql);

			} catch (SQLException sqlException) {
				//System.out.print(sql);
				sqlException.printStackTrace();
			}

		}

	}

	@Override
	public void insert(String queryAttributeValues) {
		// TODO Auto-generated method stub

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
