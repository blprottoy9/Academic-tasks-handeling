package com.database;

import java.sql.SQLException;

public class LogTable extends DatabaseTable{
	public String status;
	public String id;

	@Override
	public void retrive(String queryCondition) {
		if(queryCondition==null)
		{
		sql="select * from log";
		//sql="select '"+queryAttribute+"' from Detail where "+queryCondition;
		//sql="select * from Detail where Id='admin'";
		try {
			stmpt=con.prepareStatement(sql);
			//rs=stmpt.executeQuery(sql);
			rs=stmpt.executeQuery();

			//select * from Detail where Id='admin'admin

			while(rs.next())
				{
				status=rs.getString(1);
				id=rs.getString(2);
				}
			retriveString=status;
			retriveString2=id;
			//System.out.print(sql);
			System.out.print(status);

		} catch (SQLException sqlException) {
			//System.out.print(sql);
			//sqlException.printStackTrace();
		}

		closeDb();
		}
		//return null;

	}

	@Override
	public void insert(String queryAttributeValues) {
		sql="INSERT INTO log (ID, Status)"+queryAttributeValues;

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

		if(queryCondition==null)
		{
			sql="delete from log";
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
	}

	@Override
	public void update(String queryAttributeValues, String queryCondition) {}

	@Override
	public boolean count(String queryCondition) {
		// TODO Auto-generated method stub
		return false;
	}

}
