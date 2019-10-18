package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailTable extends DatabaseTable{

	private String statusPerson;
	private String name;
	private String contractNo;
	@Override
	public void insert(String queryAttributeValues) {}

	@Override
	public void delete(String queryCondition) {}

	@Override
	public void update(String queryAttributeValues, String queryCondition) {}

	@Override
	public boolean count(String queryCondition) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void retrive(String queryCondition) {
		sql="select * from Detail where "+queryCondition;
		//sql="select '"+queryAttribute+"' from Detail where "+queryCondition;
		//sql="select * from Detail where Id='admin'";
		try {
			stmpt=con.prepareStatement(sql);
			//rs=stmpt.executeQuery(sql);
			rs=stmpt.executeQuery();
			//select * from Detail where Id='admin'admin

			while(rs.next())
				{
				statusPerson=rs.getString("Status_Person");
				name=rs.getString(1);
				contractNo=rs.getString(4);
				}
			retriveString=statusPerson;
			retriveString2=name;
			retriveString3=contractNo;
			//System.out.print(sql);
			System.out.print(statusPerson);

		} catch (SQLException sqlException) {
			//System.out.print(sql);
			//sqlException.printStackTrace();
		}
		closeDb();
		//return null;
	}






}
