package com.database;

import java.sql.SQLException;

public class StudentCoursesTable extends DatabaseTable{

	@Override
	public void retrive(String queryCondition) {
		if(queryCondition==null)
		{
		sql="select * from Student_Course";
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
		else if(queryCondition!=null)
		{
			sql="select * from Student_Course Where "+queryCondition;
			//sql="select '"+queryAttribute+"' from Detail where "+queryCondition;
			//sql="select * from Detail where Id='admin'";
			try {
				stmpt=con.prepareStatement(sql);
				//rs=stmpt.executeQuery(sql);
				rs=stmpt.executeQuery();
				retriveResultset=rs;
				System.out.print(sql);
			} catch (SQLException sqlException) {
				//System.out.print(sql);
				//sqlException.printStackTrace();
			}
		}

	}

	@Override
	public void insert(String queryAttributeValues) {
		sql="INSERT INTO Student_Course (ID, Course_ID, Years, Grade)"+queryAttributeValues;

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

		sql="delete from Student_Course Where "+queryCondition;
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
		sql="update Student_Course set "+queryAttributeValues+" where "+queryCondition;
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
		boolean status=false;
		sql="select count(ID) from Student_Course where "+queryCondition;
		//sql="select '"+queryAttribute+"' from Detail where "+queryCondition;
		//sql="select * from Detail where Id='admin'";
		try {
			stmpt=con.prepareStatement(sql);
			//rs=stmpt.executeQuery(sql);
			rs=stmpt.executeQuery();
			//retriveResultset=rs;
			if(rs.next())
			{

				System.out.println("count:"+rs.getInt(1));
				if(rs.getInt(1)<20)
				{
					status=true;
				}
			}
		} catch (SQLException sqlException) {
			//System.out.print(sql);
			//sqlException.printStackTrace();
		}
		closeDb();
		return status;
	}



}
