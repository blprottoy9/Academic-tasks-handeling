package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginTable extends DatabaseTable{

	public String password;

	@Override
	public void insert(String queryAttributeValues) {
		sql="INSERT INTO Login (ID, PASSWORD1)"+queryAttributeValues;

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

	@Override
	public void delete( String queryCondition) {}

	@Override
	public void update(String queryAttributeValues, String queryCondition) {
		sql="update Login set PASSWORD1 = '"+queryAttributeValues+"' where "+queryCondition;
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
		//"update users set fname = '"+JT_fname.getText()+"',lname = '"+JT_lname.getText()+"', age = "+JT_age.getText()+" where id = "+JT_id.getText()
	}

	@Override
	public boolean count(String queryCondition) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void retrive(String queryCondition) {
		sql="select * from Login where "+queryCondition;
		//sql="select '"+queryAttribute+"' from Detail where "+queryCondition;
		//sql="select * from Detail where Id='admin'";
		try {
			stmpt=con.prepareStatement(sql);
			//rs=stmpt.executeQuery(sql);
			rs=stmpt.executeQuery();
			//select * from Detail where Id='admin'admin

			while(rs.next())
				{
				password=rs.getString(2);
				}
			retriveString=password;
			//System.out.print(sql);
			System.out.print(password);

		} catch (SQLException sqlException) {
			//System.out.print(sql);
			//sqlException.printStackTrace();
		}
		closeDb();
	}



}
