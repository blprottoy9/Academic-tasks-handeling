package com.database;

import java.sql.SQLException;

public class AdvisingAndFacultyEvaluationStatus extends DatabaseTable{

	@Override
	public void retrive(String queryCondition) {
		// TODO Auto-generated method stub

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
		sql="update advising_and_faculty_evaluation_status set status = '"+queryAttributeValues+"' where "+queryCondition;
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
