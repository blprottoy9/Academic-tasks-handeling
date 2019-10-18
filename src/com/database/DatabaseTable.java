package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DatabaseTable {
	//Statement stmpt;
	PreparedStatement stmpt;
	String sql;
	ResultSet rs;
	Connection con;
	public String retriveString;
	public String retriveString2;
	public String retriveString3;
	public ResultSet retriveResultset;
	public void connect()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			con=DriverManager.getConnection("jdbc:sqlite:lib/course handleing.db");
			//Class.forName("com.mysql.jdbc.Driver");
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Course Handleing","root","");
		} catch (Exception connectionException) {
			System.out.print("Connection Failed");
		}
	}
	public String search(String queryTableName,String queryCondition)
	{
		String returnRes = null;
		//sql="Select '"+queryAttribute+"' from '"+queryTableName+"' where '"+queryCondition+"'";
		sql="Select * from " +queryTableName+" Where "+queryCondition;

		try {
			stmpt=con.prepareStatement(sql);
			//rs=stmpt.executeQuery(sql);
			rs=stmpt.executeQuery();
			returnRes="false";


			while(rs.next())
				{
					returnRes= "true";
					System.out.print(sql);
				}


		} catch (SQLException sqlException) {
			System.out.print(sql);
			//sqlException.printStackTrace();
		}
		closeDb();
		return returnRes;
	}
	public void closeDb()
	{
		try {
			con.close();
			System.out.println();
			System.out.println("close:"+sql);

		} catch (SQLException closeException) {
			// TODO Auto-generated catch block
			closeException.printStackTrace();
		}
	}
	public abstract void retrive(String queryCondition);
	public abstract void insert(String queryAttributeValues);
	public abstract void delete(String queryCondition);
	public abstract void update(String queryAttributeValues,String queryCondition);
	public abstract boolean count(String queryCondition);
}
