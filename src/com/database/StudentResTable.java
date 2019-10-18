package com.database;

import java.sql.SQLException;

public class StudentResTable extends DatabaseTable{

	@Override
	public void retrive(String queryCondition) {
		if(queryCondition==null)
		{
		sql="select * from Student_Res";
		//sql="select '"+queryAttribute+"' from Detail where "+queryCondition;
		//sql="select * from Detail where Id='admin'";
		try {
			stmpt=con.prepareStatement(sql);
			//rs=stmpt.executeQuery(sql);
			rs=stmpt.executeQuery();
			retriveResultset=rs;
			System.out.println(sql);
		} catch (SQLException sqlException) {
			System.out.println("Error"+"sql");
			//sqlException.printStackTrace();
		}


		}
		else
		{
			sql="select * from Student_Res Where "+queryCondition;
			//sql="select '"+queryAttribute+"' from Detail where "+queryCondition;
			//sql="select * from Detail where Id='admin'";
			try {
				stmpt=con.prepareStatement(sql);
				//rs=stmpt.executeQuery(sql);
				rs=stmpt.executeQuery();
				retriveResultset=rs;
				System.out.println(sql);

			} catch (SQLException sqlException) {
				System.out.println("Error"+"sql");
				//System.out.print(sql);
				//sqlException.printStackTrace();
			}
		}

	}

	@Override
	public void insert(String queryAttributeValues) {
		sql="INSERT INTO Student_Res (ID, CGPA, Complete_Credits)"+queryAttributeValues;

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
		sql="update Student_Res set "+queryAttributeValues+" where "+queryCondition;
		try {
			stmpt=con.prepareStatement(sql);
			//stmpt.executeUpdate(sql);
			stmpt.executeUpdate();
			System.out.print(sql);

		} catch (SQLException sqlException) {
			System.out.print(sql);
			sqlException.printStackTrace();
		}
		closeDb();

	}

	@Override
	public boolean count(String queryCondition) {
		// TODO Auto-generated method stub
		return false;
	}
}
